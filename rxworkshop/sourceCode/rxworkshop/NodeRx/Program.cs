using System;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Reactive;
using System.Reactive.Concurrency;
using System.Reactive.Linq;
using System.Text;

namespace NodeRx {
	class Program {
		static void Main(string[] args) {
			var eventLoop = new EventLoopScheduler();

			var server = CreateServer(port: 1001, scheduler: eventLoop)
				.Select(socket => new NetworkStream(socket))
				.SelectMany(stream => stream
					.AsyncRead(1024, eventLoop)
					.Do(bytes => Console.WriteLine("Read " + bytes.Length + " bytes"))
					.Aggregate((acc, bytes) => acc.Concat(bytes).ToArray())
					.Do(bytes => Console.WriteLine("Sending " + bytes.Length + " bytes"))
					.WriteToStream(stream, 2 << 16, eventLoop)
					.Finally(stream.Flush));

			// Create an echo server on port 1001
			using (eventLoop.Schedule(() => server.Subscribe())) {
				Console.WriteLine("Listening on port " + 1001);
				Console.WriteLine("Press any key to stop...");
				Console.ReadKey();
			}
		}

		static IObservable<Socket> CreateServer(int port, IScheduler scheduler) {
			return Observable.Create<Socket>(observer => {
				var listener = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
				listener.Bind(new IPEndPoint(IPAddress.Any, port));
				listener.Listen(100);

				var getSocket = Observable.FromAsyncPattern<Socket>(listener.BeginAccept, listener.EndAccept);
				var subscription = Observable.Defer(getSocket).Repeat().ObserveOn(scheduler).Subscribe(observer);

				return () => {
					subscription.Dispose();
					listener.Close();
				};
			});
		}
	}

	public static class Extensions {
		public static IObservable<byte[]> AsyncRead(this Stream stream, int bufferSize, IScheduler scheduler) {
			return Observable.Create<byte[]>(observer => {
				var asyncRead = Observable.FromAsyncPattern<byte[], int, int, int>(stream.BeginRead, stream.EndRead);
				var buffer = new byte[bufferSize];
				return asyncRead(buffer, 0, buffer.Length)
					.Select(bytesRead =>
						buffer.Take(bytesRead).ToArray())
					.ObserveOn(scheduler)
					.Subscribe(observer);
			});
		}

		public static IObservable<Unit> WriteToStream(this IObservable<byte[]> buffers, Stream stream) {
			var asyncWrite = Observable.FromAsyncPattern<byte[], int, int>(stream.BeginWrite, stream.EndWrite);
			return buffers
				.SelectMany(data =>
					asyncWrite(data, 0, data.Length))
				.Where(_ => false);
			// NOTE: Too many small buffers will cause a performance issue.
		}

		public static IObservable<Unit> WriteToStream(this IObservable<byte[]> buffers, Stream stream, int bufferSize, IScheduler scheduler) {
			// Solution? Buffer the size of the outgoing buffers.
			return buffers.Buffer(TimeSpan.FromMilliseconds(10), bufferSize, scheduler)
				.SelectMany(data => data)
				.WriteToStream(stream);
		}
	}
}