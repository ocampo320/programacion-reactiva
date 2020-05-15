using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Reactive;
using System.Reactive.Linq;
using System.Text;

namespace NodeRxTestClient {
	class Program {
		static void Main(string[] args) {
			var ipAddress = IPAddress.Loopback;
			var port = 1001;
			var message = Encoding.ASCII.GetBytes("Hello, world!");

			var connect = Observable.Defer(() =>
				Observable.Using(() => new TcpClient(), client =>
					AsyncConnect(client, ipAddress, port)
						.Select(_ => client.GetStream())
						.SelectMany(stream => AsyncWrite(stream, message).Finally(stream.Flush))))
				.Repeat(10);

			using (connect.Subscribe()) {
				Console.WriteLine("Press ENTER to stop connecting.");
				Console.ReadLine();
			}
		}

		static IObservable<Unit> AsyncConnect(TcpClient client, IPAddress ipAddress, int port) {
			var connect = Observable.FromAsyncPattern<IPAddress, int>(client.BeginConnect, client.EndConnect);
			return Observable.Defer(() => connect(ipAddress, port));
		}

		static IObservable<Unit> AsyncWrite(Stream stream, byte[] bytes) {
			var write = Observable.FromAsyncPattern<byte[], int, int>(stream.BeginWrite, stream.EndWrite);
			return write(bytes, 0, bytes.Length);
		}
	}
}
