using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace HW2
{
    public partial class ControlForm : Form
    {
        public static List<Racer> KnownRacers = new List<Racer>();
        private List<RacerObserver> knownDisplays = new List<RacerObserver>();
        private RacerObserver selectedObserver;

        public ControlForm()
        {
            InitializeComponent();
            
        }

        private void RefreshObversersListView()
        {
            observersListView.Items.Clear();
            foreach (RacerObserver observer in knownDisplays)
            {
                ListViewItem item = new ListViewItem(observer.Title);
                observersListView.Items.Add(item);
            }
        }

        private void RefreshRacerLists()
        {
            observedRacersListView.Items.Clear();
            //allRacersListView.Items.Clear();

            if (selectedObserver != null)
                observedRacersLabel.Text = "Subjects of " + selectedObserver.Title;
            else
                observedRacersLabel.Text = "No obverser selected";

            foreach (Racer racer in KnownRacers)
            {
                ListViewItem item = new ListViewItem(new string[] { racer.FirstName, racer.LastName, racer.BibNumber.ToString(), racer.RacerGroup.ToString() });
                item.Tag = racer;
                if (selectedObserver != null && racer.Subscribers.Contains(selectedObserver))
                    observedRacersListView.Items.Add(item);
                else
                    ;
                //allRacersListView.Items.Add(item);
            }
        }

        private void ExitButton_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void ObserversListView_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (observersListView.SelectedIndices.Count == 1)
            {
                selectedObserver = knownDisplays[observersListView.SelectedIndices[0]];
                unscribeButton.Enabled = true;
                subscribeButton.Enabled = true;
            }
            else
            {
                selectedObserver = null;
                unscribeButton.Enabled = true;
                subscribeButton.Enabled = true;
            }

            RefreshRacerLists();
        }

        private void ControlForm_Load(object sender, EventArgs e)
        {
            RefreshObversersListView();
            RefreshRacerLists();
        }

        private void SubscribeButton_Click(object sender, EventArgs e)
        {
            if (selectedObserver != null)
            {
                foreach (ListViewItem item in allRacersListView.SelectedItems)
                {
                    Subject subject = item.Tag as Subject;
                    subject.Subscribe(selectedObserver);
                }
                RefreshRacerLists();
            }
        }

        private void UnscribeButton_Click(object sender, EventArgs e)
        {
            if (selectedObserver != null)
            {
                foreach (ListViewItem item in observedRacersListView.SelectedItems)
                {
                    Subject subject = item.Tag as Subject;
                    subject.Unsubscribe(selectedObserver);
                }
                RefreshRacerLists();
            }
        }

        private void CreateObserverButton_Click(object sender, EventArgs e)
        {

            ObserverCreatorForm modalDialogForm = new ObserverCreatorForm();
            modalDialogForm.Text = "New Observer";
            modalDialogForm.ObserverTitle = string.Format("Observer #{0}", knownDisplays.Count + 1);
            if (modalDialogForm.ShowDialog() == DialogResult.OK)
            {
                RacerObserver observer;
                if (modalDialogForm.ObserverType == "L")
                    observer = new RacerListDisplay();
                else
                {
                    observer = new BigScreenDisplay();
                    foreach (var subject in Parsers.ListRacersDict.Values)
                    {
                        subject.Subscribe(observer);
                    }
                }
                    

                observer.Title = modalDialogForm.ObserverTitle;
                knownDisplays.Add(observer);
                observer.Show();

                selectedObserver = null;
                observersListView.SelectedIndices.Clear();
                RefreshObversersListView();
                RefreshRacerLists();
            }

        }

        private void OtherRacersListView_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void OtherRacerLabel_Click(object sender, EventArgs e)
        {

        }

        private void ObservedRacersListView_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void LoadRacersButton_Click(object sender, EventArgs e)
        {
            loadRacersButton.Enabled = false;
            foreach (var pair in Parsers.ListRacersDict)
            {
                Racer racer = pair.Value;
                ListViewItem item = new ListViewItem(new string[] { racer.FirstName.ToString() + " " + racer.LastName.ToString(), racer.BibNumber.ToString(), racer.RacerGroup.ToString(), racer.MyGroup.GroupLabel });
                item.Tag = racer;
                allRacersListView.Items.Add(item);
                KnownRacers.Add(racer);
            }
            
        }
    }
}
