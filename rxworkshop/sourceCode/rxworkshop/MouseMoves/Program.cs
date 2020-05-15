using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Reactive;
using System.Reactive.Linq;
using System.Windows.Forms;

namespace MouseMoves {
	class Program {
		static void Main() {
			Label lbl = new Label();
			Form frm = new Form { Controls = { lbl } };

			//frm.MouseMove += (sender, args) => {
			//    if (args.Location.X == args.Location.Y) {
			//        lbl.Text = args.Location.ToString();
			//    }
			//};
			var mouseUps = Observable.FromEventPattern<MouseEventArgs>(frm, "MouseUp");
			var mouseMoves = Observable.FromEventPattern<MouseEventHandler, MouseEventArgs>(x => frm.MouseMove += x, x => frm.MouseMove -= x);
			//var mouseMoves = Observable.FromEventPattern<MouseEventArgs>(frm, "MouseMove");
			var specificMoves = mouseUps
				.SelectMany(evt => mouseMoves)
				.Select(evt => new { evt.EventArgs.Location.X, evt.EventArgs.Location.Y });
			using (specificMoves.Subscribe(evt => lbl.Text = evt.ToString()))
				Application.Run(frm);
		}
	}
}
