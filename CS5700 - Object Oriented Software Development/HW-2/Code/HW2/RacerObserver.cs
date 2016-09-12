using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace HW2
{
    public class RacerObserver : Form
    {
        protected Dictionary<int, Racer> RacersBeingObserved = new Dictionary<int, Racer>();

        protected bool RepaintNeeded = false;
        private Timer refreshTester = new Timer();
        private object myLock = new object();

        public int RefreshFrequency { get; set; }
        public string Title { get; set; }

        public virtual void Update(Subject subject)
        {
            Racer racer = subject as Racer;
            if (racer != null)
            {
                lock (myLock)
                {
                    if (!RacersBeingObserved.ContainsKey(racer.BibNumber))
                        RacersBeingObserved.Add(racer.BibNumber, racer);
                    else
                        RacersBeingObserved[racer.BibNumber] = racer;
                }
                RepaintNeeded = true;
            }
        }

        protected void StartRefreshTimer()
        {
            if (RefreshFrequency <= 0)
                RefreshFrequency = 50;

            refreshTester.Interval = RefreshFrequency;
            refreshTester.Tick += RefreshTimer_Tick;
            refreshTester.Start();
        }

        private void RefreshTimer_Tick(object sender, EventArgs e)
        {
            if (RepaintNeeded)
            {
                lock (myLock)
                {
                    RefreshDisplay();
                    RepaintNeeded = false;
                }
            }
        }

        protected virtual void RefreshDisplay() { }

    }
}
