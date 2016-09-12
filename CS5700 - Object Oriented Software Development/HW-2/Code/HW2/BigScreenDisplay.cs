using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace HW2
{
    public partial class BigScreenDisplay : RacerObserver
    {
        public BigScreenDisplay()
        {
            InitializeComponent();
        }

        protected override void RefreshDisplay()
        {
            bigScreenListView.BeginUpdate();
            bigScreenListView.Items.Clear();
            foreach (KeyValuePair<int, Racer> racer in RacersBeingObserved)
            {
                ListViewItem item = new ListViewItem(new string[]
                                                {
                                                    racer.Value.FirstName + " " + racer.Value.LastName,
                                                    racer.Value.BibNumber.ToString(),
                                                    racer.Value.MyGroup.GroupLabel,
                                                    (TimeSpan.FromMilliseconds(racer.Value.CurrentTimeStamp - racer.Value.MyGroup.StartTime)).ToString(@"hh\:mm\:ss"),
                                                    Parsers.ListSensorsDict[racer.Value.AtSensor].SensorDistance.ToString(),
                                                    racer.Value.AtSensor.ToString()
                                                });
                bigScreenListView.Items.Add(item);
            }
            bigScreenListView.EndUpdate();
        }


        private void ListDisplay_Load(object sender, EventArgs e)
        {
            this.Text = Title;
            StartRefreshTimer();
        }

    }
}
