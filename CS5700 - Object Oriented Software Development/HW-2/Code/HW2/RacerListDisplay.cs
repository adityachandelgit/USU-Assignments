using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace HW2
{
    public partial class RacerListDisplay : RacerObserver
    {
        public RacerListDisplay()
        {
            InitializeComponent();
        }

        private void RacerListView_SelectedIndexChanged(object sender, EventArgs e)
        {
            throw new NotImplementedException();
        }

        protected override void RefreshDisplay()
        {
            racerListView.Items.Clear();
            foreach (KeyValuePair<int, Racer> racer in RacersBeingObserved)
            {
                ListViewItem item = new ListViewItem(new string[]
                                                {
                                                    racer.Value.FirstName + " " + racer.Value.LastName,
                                                    racer.Value.BibNumber.ToString(),
                                                    racer.Value.MyGroup.GroupLabel,
                                                    (TimeSpan.FromMilliseconds(racer.Value.CurrentTimeStamp - racer.Value.MyGroup.StartTime)).ToString(@"hh\:mm\:ss"),
                                                    Parsers.ListSensorsDict[racer.Value.AtSensor].SensorDistance.ToString()
                                                });
                racerListView.Items.Add(item);
            }
        }

        private void ListDisplay_Load(object sender, EventArgs e)
        {
            Text = Title;
            StartRefreshTimer();
        }
    }
}
