using System.Windows.Forms;

namespace Planets
{
    partial class DrawingForm
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
            this.components = new System.ComponentModel.Container();
            this.newButton = new System.Windows.Forms.Button();
            this.repeatButton = new System.Windows.Forms.Button();
            this.refreshTimer = new System.Windows.Forms.Timer(this.components);
            this.undoButton = new System.Windows.Forms.Button();
            this.deleteButton = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.saveButton = new System.Windows.Forms.Button();
            this.loadButton = new System.Windows.Forms.Button();
            this.drawingPanel = new System.Windows.Forms.Panel();
            this.button1 = new System.Windows.Forms.Button();
            this.colorChooserButton = new System.Windows.Forms.Button();
            this.asteroidButton = new System.Windows.Forms.Button();
            this.blueSunButton = new System.Windows.Forms.Button();
            this.redSunButton = new System.Windows.Forms.Button();
            this.earthButton = new System.Windows.Forms.Button();
            this.yellowPlanetButton = new System.Windows.Forms.Button();
            this.redplanetButton = new System.Windows.Forms.Button();
            this.bluePlanetButton = new System.Windows.Forms.Button();
            this.greenPlanetButton = new System.Windows.Forms.Button();
            this.pointerButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // newButton
            // 
            this.newButton.Location = new System.Drawing.Point(682, 12);
            this.newButton.Name = "newButton";
            this.newButton.Size = new System.Drawing.Size(49, 23);
            this.newButton.TabIndex = 0;
            this.newButton.Text = "New";
            this.newButton.UseVisualStyleBackColor = true;
            this.newButton.Click += new System.EventHandler(this.newButton_Click);
            // 
            // repeatButton
            // 
            this.repeatButton.Location = new System.Drawing.Point(17, 518);
            this.repeatButton.Name = "repeatButton";
            this.repeatButton.Size = new System.Drawing.Size(51, 23);
            this.repeatButton.TabIndex = 4;
            this.repeatButton.Text = "Repeat";
            this.repeatButton.UseVisualStyleBackColor = true;
            this.repeatButton.Click += new System.EventHandler(this.repeatButton_Click);
            // 
            // refreshTimer
            // 
            this.refreshTimer.Interval = 50;
            this.refreshTimer.Tick += new System.EventHandler(this.refreshTimer_Tick);
            // 
            // undoButton
            // 
            this.undoButton.Location = new System.Drawing.Point(16, 479);
            this.undoButton.Name = "undoButton";
            this.undoButton.Size = new System.Drawing.Size(51, 23);
            this.undoButton.TabIndex = 6;
            this.undoButton.Text = "Undo";
            this.undoButton.UseVisualStyleBackColor = true;
            this.undoButton.Click += new System.EventHandler(this.undoButton_Click);
            // 
            // deleteButton
            // 
            this.deleteButton.Location = new System.Drawing.Point(17, 440);
            this.deleteButton.Name = "deleteButton";
            this.deleteButton.Size = new System.Drawing.Size(51, 23);
            this.deleteButton.TabIndex = 7;
            this.deleteButton.Text = "Delete";
            this.deleteButton.UseVisualStyleBackColor = true;
            this.deleteButton.Click += new System.EventHandler(this.deleteButton_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(78, 12);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(346, 26);
            this.label1.TabIndex = 7;
            this.label1.Text = "A: Move Left          S: Move Down       Q: Delete          Z: Increase Size\r\nD: " +
    "Move Right      W: Move Up            R: Repeat        X: Decrease Size\r\n";
            // 
            // saveButton
            // 
            this.saveButton.Location = new System.Drawing.Point(565, 12);
            this.saveButton.Name = "saveButton";
            this.saveButton.Size = new System.Drawing.Size(57, 23);
            this.saveButton.TabIndex = 10;
            this.saveButton.Text = "Save";
            this.saveButton.UseVisualStyleBackColor = true;
            this.saveButton.Click += new System.EventHandler(this.saveButton_Click);
            // 
            // loadButton
            // 
            this.loadButton.Location = new System.Drawing.Point(628, 12);
            this.loadButton.Name = "loadButton";
            this.loadButton.Size = new System.Drawing.Size(48, 23);
            this.loadButton.TabIndex = 11;
            this.loadButton.Text = "Load";
            this.loadButton.UseVisualStyleBackColor = true;
            this.loadButton.Click += new System.EventHandler(this.loadButton_Click);
            // 
            // drawingPanel
            // 
            this.drawingPanel.BackColor = System.Drawing.Color.Black;
            this.drawingPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.drawingPanel.Location = new System.Drawing.Point(81, 53);
            this.drawingPanel.Name = "drawingPanel";
            this.drawingPanel.Size = new System.Drawing.Size(650, 488);
            this.drawingPanel.TabIndex = 1;
            this.drawingPanel.Paint += new System.Windows.Forms.PaintEventHandler(this.drawingPanel_Paint);
            this.drawingPanel.MouseUp += new System.Windows.Forms.MouseEventHandler(this.drawingPanel_MouseUp);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(457, 12);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(84, 23);
            this.button1.TabIndex = 19;
            this.button1.Text = "Save as PNG";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.buttonSaveAsJpeg_Click);
            // 
            // colorChooserButton
            // 
            this.colorChooserButton.BackgroundImage = global::Planets.Properties.Resources.colormap;
            this.colorChooserButton.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.colorChooserButton.Location = new System.Drawing.Point(737, 483);
            this.colorChooserButton.Name = "colorChooserButton";
            this.colorChooserButton.Size = new System.Drawing.Size(62, 58);
            this.colorChooserButton.TabIndex = 20;
            this.colorChooserButton.UseVisualStyleBackColor = true;
            this.colorChooserButton.Click += new System.EventHandler(this.colorChooserButton_Click);
            // 
            // asteroidButton
            // 
            this.asteroidButton.BackgroundImage = global::Planets.Properties.Resources.a1;
            this.asteroidButton.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.asteroidButton.Cursor = System.Windows.Forms.Cursors.IBeam;
            this.asteroidButton.Location = new System.Drawing.Point(744, 203);
            this.asteroidButton.Name = "asteroidButton";
            this.asteroidButton.Size = new System.Drawing.Size(55, 55);
            this.asteroidButton.TabIndex = 17;
            this.asteroidButton.UseVisualStyleBackColor = true;
            this.asteroidButton.Click += new System.EventHandler(this.asteroidButton_Click);
            // 
            // blueSunButton
            // 
            this.blueSunButton.BackgroundImage = global::Planets.Properties.Resources.s2;
            this.blueSunButton.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.blueSunButton.Cursor = System.Windows.Forms.Cursors.IBeam;
            this.blueSunButton.Location = new System.Drawing.Point(744, 127);
            this.blueSunButton.Name = "blueSunButton";
            this.blueSunButton.Size = new System.Drawing.Size(55, 55);
            this.blueSunButton.TabIndex = 16;
            this.blueSunButton.UseVisualStyleBackColor = true;
            this.blueSunButton.Click += new System.EventHandler(this.blueSunButton_Click);
            // 
            // redSunButton
            // 
            this.redSunButton.BackgroundImage = global::Planets.Properties.Resources.s1;
            this.redSunButton.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.redSunButton.Cursor = System.Windows.Forms.Cursors.IBeam;
            this.redSunButton.Location = new System.Drawing.Point(744, 53);
            this.redSunButton.Name = "redSunButton";
            this.redSunButton.Size = new System.Drawing.Size(55, 55);
            this.redSunButton.TabIndex = 15;
            this.redSunButton.UseVisualStyleBackColor = true;
            this.redSunButton.Click += new System.EventHandler(this.redSunButton_Click);
            // 
            // earthButton
            // 
            this.earthButton.BackgroundImage = global::Planets.Properties.Resources.p5;
            this.earthButton.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.earthButton.Cursor = System.Windows.Forms.Cursors.IBeam;
            this.earthButton.Location = new System.Drawing.Point(13, 360);
            this.earthButton.Name = "earthButton";
            this.earthButton.Size = new System.Drawing.Size(55, 55);
            this.earthButton.TabIndex = 14;
            this.earthButton.UseVisualStyleBackColor = true;
            this.earthButton.Click += new System.EventHandler(this.earthButton_Click);
            // 
            // yellowPlanetButton
            // 
            this.yellowPlanetButton.BackgroundImage = global::Planets.Properties.Resources.p4;
            this.yellowPlanetButton.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.yellowPlanetButton.Cursor = System.Windows.Forms.Cursors.IBeam;
            this.yellowPlanetButton.Location = new System.Drawing.Point(13, 299);
            this.yellowPlanetButton.Name = "yellowPlanetButton";
            this.yellowPlanetButton.Size = new System.Drawing.Size(55, 55);
            this.yellowPlanetButton.TabIndex = 13;
            this.yellowPlanetButton.UseVisualStyleBackColor = true;
            this.yellowPlanetButton.Click += new System.EventHandler(this.yellowPlanetButton_Click);
            // 
            // redplanetButton
            // 
            this.redplanetButton.BackgroundImage = global::Planets.Properties.Resources.p3;
            this.redplanetButton.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.redplanetButton.Cursor = System.Windows.Forms.Cursors.IBeam;
            this.redplanetButton.Location = new System.Drawing.Point(13, 238);
            this.redplanetButton.Name = "redplanetButton";
            this.redplanetButton.Size = new System.Drawing.Size(55, 55);
            this.redplanetButton.TabIndex = 12;
            this.redplanetButton.UseVisualStyleBackColor = true;
            this.redplanetButton.Click += new System.EventHandler(this.redplanetButton_Click);
            // 
            // bluePlanetButton
            // 
            this.bluePlanetButton.BackgroundImage = global::Planets.Properties.Resources.p2;
            this.bluePlanetButton.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.bluePlanetButton.Cursor = System.Windows.Forms.Cursors.IBeam;
            this.bluePlanetButton.Location = new System.Drawing.Point(12, 175);
            this.bluePlanetButton.Name = "bluePlanetButton";
            this.bluePlanetButton.Size = new System.Drawing.Size(55, 55);
            this.bluePlanetButton.TabIndex = 5;
            this.bluePlanetButton.UseVisualStyleBackColor = true;
            this.bluePlanetButton.Click += new System.EventHandler(this.bluePlanetButton_Click);
            // 
            // greenPlanetButton
            // 
            this.greenPlanetButton.BackgroundImage = global::Planets.Properties.Resources.p1;
            this.greenPlanetButton.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.greenPlanetButton.Cursor = System.Windows.Forms.Cursors.IBeam;
            this.greenPlanetButton.Location = new System.Drawing.Point(13, 114);
            this.greenPlanetButton.Name = "greenPlanetButton";
            this.greenPlanetButton.Size = new System.Drawing.Size(55, 55);
            this.greenPlanetButton.TabIndex = 3;
            this.greenPlanetButton.UseVisualStyleBackColor = true;
            this.greenPlanetButton.Click += new System.EventHandler(this.greenPlanetButton_Click);
            // 
            // pointerButton
            // 
            this.pointerButton.BackgroundImage = global::Planets.Properties.Resources.PointerIcon;
            this.pointerButton.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.pointerButton.Cursor = System.Windows.Forms.Cursors.IBeam;
            this.pointerButton.Location = new System.Drawing.Point(13, 53);
            this.pointerButton.Name = "pointerButton";
            this.pointerButton.Size = new System.Drawing.Size(55, 50);
            this.pointerButton.TabIndex = 2;
            this.pointerButton.UseVisualStyleBackColor = true;
            this.pointerButton.Click += new System.EventHandler(this.pointerButton_Click);
            // 
            // DrawingForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(811, 585);
            this.Controls.Add(this.colorChooserButton);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.asteroidButton);
            this.Controls.Add(this.blueSunButton);
            this.Controls.Add(this.redSunButton);
            this.Controls.Add(this.earthButton);
            this.Controls.Add(this.yellowPlanetButton);
            this.Controls.Add(this.redplanetButton);
            this.Controls.Add(this.loadButton);
            this.Controls.Add(this.saveButton);
            this.Controls.Add(this.deleteButton);
            this.Controls.Add(this.undoButton);
            this.Controls.Add(this.bluePlanetButton);
            this.Controls.Add(this.repeatButton);
            this.Controls.Add(this.greenPlanetButton);
            this.Controls.Add(this.pointerButton);
            this.Controls.Add(this.drawingPanel);
            this.Controls.Add(this.newButton);
            this.Controls.Add(this.label1);
            this.Name = "DrawingForm";
            this.Text = "v";
            this.Load += new System.EventHandler(this.MainForm_Load);
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.MainForm_KeyDown);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Label label1;
        private System.Windows.Forms.Button newButton;
        private System.Windows.Forms.Button pointerButton;
        private System.Windows.Forms.Button greenPlanetButton;
        private System.Windows.Forms.Button repeatButton;
        private System.Windows.Forms.Button bluePlanetButton;
        private System.Windows.Forms.Timer refreshTimer;
        private System.Windows.Forms.Button undoButton;
        private System.Windows.Forms.Button deleteButton;
        private Button saveButton;
        private Button loadButton;
        private Button redplanetButton;
        private Button yellowPlanetButton;
        private Button earthButton;
        private Button redSunButton;
        private Button blueSunButton;
        private Button asteroidButton;
        private Panel drawingPanel;
        private Button button1;
        private Button colorChooserButton;
        
    }
}

