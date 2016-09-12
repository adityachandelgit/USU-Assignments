namespace HW2
{
    partial class ControlForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.exitButton = new System.Windows.Forms.Button();
            this.allRacersListView = new System.Windows.Forms.ListView();
            this.columnHeader1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader3 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader33 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.otherRacerLabel = new System.Windows.Forms.Label();
            this.observersLabel = new System.Windows.Forms.Label();
            this.observedRacersLabel = new System.Windows.Forms.Label();
            this.observedRacersListView = new System.Windows.Forms.ListView();
            this.columnHeader4 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader5 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader6 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader66 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.observersListView = new System.Windows.Forms.ListView();
            this.unscribeButton = new System.Windows.Forms.Button();
            this.subscribeButton = new System.Windows.Forms.Button();
            this.createObserverButton = new System.Windows.Forms.Button();
            this.loadRacersButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // exitButton
            // 
            this.exitButton.Location = new System.Drawing.Point(824, 430);
            this.exitButton.Name = "exitButton";
            this.exitButton.Size = new System.Drawing.Size(75, 23);
            this.exitButton.TabIndex = 0;
            this.exitButton.Text = "Exit";
            this.exitButton.UseVisualStyleBackColor = true;
            this.exitButton.Click += new System.EventHandler(this.ExitButton_Click);
            // 
            // allRacersListView
            // 
            this.allRacersListView.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader1,
            this.columnHeader2,
            this.columnHeader3,
            this.columnHeader33});
            this.allRacersListView.FullRowSelect = true;
            this.allRacersListView.Location = new System.Drawing.Point(625, 45);
            this.allRacersListView.Name = "allRacersListView";
            this.allRacersListView.Size = new System.Drawing.Size(355, 368);
            this.allRacersListView.TabIndex = 1;
            this.allRacersListView.UseCompatibleStateImageBehavior = false;
            this.allRacersListView.View = System.Windows.Forms.View.Details;
            this.allRacersListView.SelectedIndexChanged += new System.EventHandler(this.OtherRacersListView_SelectedIndexChanged);
            // 
            // columnHeader1
            // 
            this.columnHeader1.Text = "Name";
            this.columnHeader1.Width = 127;
            // 
            // columnHeader2
            // 
            this.columnHeader2.Text = "BiB";
            this.columnHeader2.Width = 52;
            // 
            // columnHeader3
            // 
            this.columnHeader3.Text = "Group No.";
            this.columnHeader3.Width = 64;
            // 
            // columnHeader33
            // 
            this.columnHeader33.Text = "Group Label";
            this.columnHeader33.Width = 109;
            // 
            // otherRacerLabel
            // 
            this.otherRacerLabel.AutoSize = true;
            this.otherRacerLabel.Location = new System.Drawing.Point(622, 26);
            this.otherRacerLabel.Name = "otherRacerLabel";
            this.otherRacerLabel.Size = new System.Drawing.Size(44, 13);
            this.otherRacerLabel.TabIndex = 2;
            this.otherRacerLabel.Text = "Racers:";
            this.otherRacerLabel.Click += new System.EventHandler(this.OtherRacerLabel_Click);
            // 
            // observersLabel
            // 
            this.observersLabel.AutoSize = true;
            this.observersLabel.Location = new System.Drawing.Point(12, 26);
            this.observersLabel.Name = "observersLabel";
            this.observersLabel.Size = new System.Drawing.Size(58, 13);
            this.observersLabel.TabIndex = 3;
            this.observersLabel.Text = "Observers:";
            // 
            // observedRacersLabel
            // 
            this.observedRacersLabel.AutoSize = true;
            this.observedRacersLabel.Location = new System.Drawing.Point(231, 26);
            this.observedRacersLabel.Name = "observedRacersLabel";
            this.observedRacersLabel.Size = new System.Drawing.Size(69, 13);
            this.observedRacersLabel.TabIndex = 5;
            this.observedRacersLabel.Text = "Suscribed to:";
            // 
            // observedRacersListView
            // 
            this.observedRacersListView.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader4,
            this.columnHeader5,
            this.columnHeader6,
            this.columnHeader66});
            this.observedRacersListView.FullRowSelect = true;
            this.observedRacersListView.Location = new System.Drawing.Point(234, 45);
            this.observedRacersListView.Name = "observedRacersListView";
            this.observedRacersListView.Size = new System.Drawing.Size(338, 368);
            this.observedRacersListView.TabIndex = 6;
            this.observedRacersListView.UseCompatibleStateImageBehavior = false;
            this.observedRacersListView.View = System.Windows.Forms.View.Details;
            this.observedRacersListView.SelectedIndexChanged += new System.EventHandler(this.ObservedRacersListView_SelectedIndexChanged);
            // 
            // columnHeader4
            // 
            this.columnHeader4.Text = "Name";
            this.columnHeader4.Width = 102;
            // 
            // columnHeader5
            // 
            this.columnHeader5.Text = "Bib";
            this.columnHeader5.Width = 49;
            // 
            // columnHeader6
            // 
            this.columnHeader6.Text = "Group No.";
            this.columnHeader6.Width = 66;
            // 
            // columnHeader66
            // 
            this.columnHeader66.Text = "Group Label";
            this.columnHeader66.Width = 116;
            // 
            // observersListView
            // 
            this.observersListView.Location = new System.Drawing.Point(15, 45);
            this.observersListView.MultiSelect = false;
            this.observersListView.Name = "observersListView";
            this.observersListView.Size = new System.Drawing.Size(188, 368);
            this.observersListView.TabIndex = 7;
            this.observersListView.UseCompatibleStateImageBehavior = false;
            this.observersListView.View = System.Windows.Forms.View.List;
            this.observersListView.SelectedIndexChanged += new System.EventHandler(this.ObserversListView_SelectedIndexChanged);
            // 
            // unscribeButton
            // 
            this.unscribeButton.Location = new System.Drawing.Point(578, 172);
            this.unscribeButton.Name = "unscribeButton";
            this.unscribeButton.Size = new System.Drawing.Size(41, 23);
            this.unscribeButton.TabIndex = 8;
            this.unscribeButton.Text = ">";
            this.unscribeButton.UseVisualStyleBackColor = true;
            this.unscribeButton.Click += new System.EventHandler(this.UnscribeButton_Click);
            // 
            // subscribeButton
            // 
            this.subscribeButton.Location = new System.Drawing.Point(578, 101);
            this.subscribeButton.Name = "subscribeButton";
            this.subscribeButton.Size = new System.Drawing.Size(41, 23);
            this.subscribeButton.TabIndex = 9;
            this.subscribeButton.Text = "<";
            this.subscribeButton.UseVisualStyleBackColor = true;
            this.subscribeButton.Click += new System.EventHandler(this.SubscribeButton_Click);
            // 
            // createObserverButton
            // 
            this.createObserverButton.Location = new System.Drawing.Point(69, 430);
            this.createObserverButton.Name = "createObserverButton";
            this.createObserverButton.Size = new System.Drawing.Size(75, 23);
            this.createObserverButton.TabIndex = 11;
            this.createObserverButton.Text = "Create";
            this.createObserverButton.UseVisualStyleBackColor = true;
            this.createObserverButton.Click += new System.EventHandler(this.CreateObserverButton_Click);
            // 
            // loadRacersButton
            // 
            this.loadRacersButton.Location = new System.Drawing.Point(702, 430);
            this.loadRacersButton.Name = "loadRacersButton";
            this.loadRacersButton.Size = new System.Drawing.Size(75, 23);
            this.loadRacersButton.TabIndex = 12;
            this.loadRacersButton.Text = "Load Racers";
            this.loadRacersButton.UseVisualStyleBackColor = true;
            this.loadRacersButton.Click += new System.EventHandler(this.LoadRacersButton_Click);
            // 
            // ControlForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(992, 465);
            this.Controls.Add(this.loadRacersButton);
            this.Controls.Add(this.createObserverButton);
            this.Controls.Add(this.subscribeButton);
            this.Controls.Add(this.unscribeButton);
            this.Controls.Add(this.observersListView);
            this.Controls.Add(this.observedRacersListView);
            this.Controls.Add(this.observedRacersLabel);
            this.Controls.Add(this.observersLabel);
            this.Controls.Add(this.otherRacerLabel);
            this.Controls.Add(this.allRacersListView);
            this.Controls.Add(this.exitButton);
            this.Name = "ControlForm";
            this.Text = "ControlForm";
            this.Load += new System.EventHandler(this.ControlForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button exitButton;
        private System.Windows.Forms.ListView allRacersListView;
        private System.Windows.Forms.Label otherRacerLabel;
        private System.Windows.Forms.Label observersLabel;
        private System.Windows.Forms.Label observedRacersLabel;
        private System.Windows.Forms.ListView observedRacersListView;
        private System.Windows.Forms.ListView observersListView;
        private System.Windows.Forms.Button unscribeButton;
        private System.Windows.Forms.Button subscribeButton;
        private System.Windows.Forms.Button createObserverButton;
        private System.Windows.Forms.ColumnHeader columnHeader1;
        private System.Windows.Forms.ColumnHeader columnHeader2;
        private System.Windows.Forms.ColumnHeader columnHeader3;
        private System.Windows.Forms.ColumnHeader columnHeader33;
        private System.Windows.Forms.ColumnHeader columnHeader4;
        private System.Windows.Forms.ColumnHeader columnHeader5;
        private System.Windows.Forms.ColumnHeader columnHeader6;
        private System.Windows.Forms.ColumnHeader columnHeader66;
        private System.Windows.Forms.Button loadRacersButton;
    }
}