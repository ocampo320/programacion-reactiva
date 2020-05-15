using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Reactive.Concurrency;
using System.Threading.Tasks;
using System.Threading;

namespace StockQuotes
{
    class MyHistoricalScheduler : HistoricalScheduler
    {
        public void Run()
        {
            var day = DateTime.MinValue;
            Scheduler.ThreadPool.Schedule(
                TimeSpan.FromSeconds(.1),
                self =>
                {
                    while (true)
                    {
                        var next = GetNext();
                        if (next == null) return;
                        if (!day.Equals(next.DueTime.DateTime))
                        {
                            day = next.DueTime.DateTime;
                            self(TimeSpan.FromSeconds(.1));
                            return;
                        }
                        else
                            next.Invoke();
                    }
                });
        }
    }
}
