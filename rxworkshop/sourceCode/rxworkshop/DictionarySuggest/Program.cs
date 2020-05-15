using System;
using System.Linq;
using System.Reactive.Linq;
using System.Windows.Forms;
using DictionarySuggest.DictionarySuggestService;
using System.Reactive;

namespace DictionarySuggest
{
    class Program
    {
        static void Main(string[] args)
        {
            // Input form
            var txt = new TextBox();
            var lst = new ListBox { Top = txt.Height + 10 };
            var frm = new Form { Controls = { txt, lst } };

			var input = Observable
				.FromEventPattern(txt, "TextChanged")
				.Select(evt => ((TextBox)evt.Sender).Text)
				.Timestamp()
				.Do((Timestamped<string> evt) => Console.WriteLine(evt))
				.Select(evt => evt.Value)
				.Where(evt => evt.Length > 4)
				.DistinctUntilChanged()
				.Do(evt => Console.WriteLine(evt));

            // Dictionary service
            var service = new DictServiceSoapClient("DictServiceSoap");
            var matchInDict = Observable.FromAsyncPattern<string, string, string, DictionaryWord[]>
                (service.BeginMatchInDict, service.EndMatchInDict);

			Func<string, IObservable<DictionaryWord[]>> matchInWordNetByPrefix =
				term => matchInDict("wn", term, "prefix")
				.Delay(TimeSpan.FromMilliseconds(1 / term.Length * 10000));

            var result = from term in input
                         from words in matchInWordNetByPrefix(term)
							.TakeUntil(input)
							.Finally(() => Console.WriteLine("Disposed request for: " + term))
                         select words;

            using (result
				.OnErrorResumeNext(Observable.Empty<DictionaryWord[]>())
				.ObserveOn(lst)
				.Subscribe(words =>
                {
                    lst.Items.Clear();
                    lst.Items.AddRange(words.Select(word => word.Word).ToArray());
                }))
                Application.Run(frm);
        }
    }
}
