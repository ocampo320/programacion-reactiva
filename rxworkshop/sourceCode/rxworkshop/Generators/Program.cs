using System;
using System.Collections.Generic;
using System.Linq;
using System.Reactive.Linq;
using System.Reactive.Disposables;
using System.Threading;

namespace Generators {
	class Program {
		static void Main(string[] args) {
			IObservable<string> source = Observable.Create<string>(

				observer => {
					var b = new BooleanDisposable();
					new Thread(() => {
						for (var i = 0; i < 10 && !b.IsDisposed; ++i)
							observer.OnNext(i.ToString());
						observer.OnCompleted();
					}).Start();
					return b;
				});

			IDisposable subscription = source.Subscribe(
				x => Console.WriteLine("On Next: {0}", x),
				ex => Console.WriteLine("On Error: {0}", ex.Message),
				() => Console.WriteLine("On Completed")
			);

			Console.WriteLine("Press ENTER to unsubscribe...");
			Console.ReadLine();

			subscription.Dispose();
		}
	}
}
