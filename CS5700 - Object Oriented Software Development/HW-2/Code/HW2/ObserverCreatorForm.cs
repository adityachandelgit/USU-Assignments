using System;
using System.Windows.Forms;

namespace HW2
{
    public partial class ObserverCreatorForm : Form
    {
        public ObserverCreatorForm()
        {
            InitializeComponent();
        }
        public string ObserverTitle
        {
            get { return titleTextBox.Text; }
            set { titleTextBox.Text = value; }
        }

        private void CreaetionButton_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.OK;
        }

        private void CancelButton_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
        }

        public string ObserverType
        {
            get { return (listTypeRadioButton.Checked) ? "L" : "G"; }
        }
    }
}
