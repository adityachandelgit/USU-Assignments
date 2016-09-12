using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

using Planets.Properties;
using Planets.Commands;
using Planets.DrawingComponents;

namespace Planets
{
    // NOTE: There some design problems with this class

    public partial class DrawingForm : Form
    {
        private enum Mode
        {
            None,
            Create,
            Select
        };

        private Invoker invoker = new Invoker();
        private Drawing drawing = new Drawing();
        private Mode mode = Mode.None;
        private Planet.PossiblePlanets _selectedPlanetType;

        public DrawingForm()
        {
            KeyPreview = true;
            InitializeComponent();
        }

        private void newButton_Click(object sender, EventArgs e)
        {
            drawing = new Drawing();
            drawing.Draw(drawingPanel.CreateGraphics());
        }

        private void pointerButton_Click(object sender, EventArgs e)
        {
            mode = Mode.Select;
        }

        private void bluePlanetButton_Click(object sender, EventArgs e)
        {
            mode = Mode.Create;
            _selectedPlanetType = Planet.PossiblePlanets.Green;
        }

        private void greenPlanetButton_Click(object sender, EventArgs e)
        {
            mode = Mode.Create;
            _selectedPlanetType = Planet.PossiblePlanets.Blue;
        }

        private void redplanetButton_Click(object sender, EventArgs e)
        {
            mode = Mode.Create;
            _selectedPlanetType = Planet.PossiblePlanets.Red;

        }

        private void yellowPlanetButton_Click(object sender, EventArgs e)
        {
            mode = Mode.Create;
            _selectedPlanetType = Planet.PossiblePlanets.Yellow;
        }

        private void earthButton_Click(object sender, EventArgs e)
        {
            mode = Mode.Create;
            _selectedPlanetType = Planet.PossiblePlanets.Earth;
        }

        private void redSunButton_Click(object sender, EventArgs e)
        {
            mode = Mode.Create;
            _selectedPlanetType = Planet.PossiblePlanets.RedSun;
        }

        private void blueSunButton_Click(object sender, EventArgs e)
        {
            mode = Mode.Create;
            _selectedPlanetType = Planet.PossiblePlanets.BlueSun;
        }

        private void asteroidButton_Click(object sender, EventArgs e)
        {
            mode = Mode.Create;
            _selectedPlanetType = Planet.PossiblePlanets.Asteroid;
        }


        private void drawingPanel_MouseUp(object sender, MouseEventArgs e)
        {
            if (mode == Mode.Create)
            {
                CreateCommand cmd = new CreateCommand()
                {
                    TargetDrawing = drawing,
                    Location = e.Location,
                    SelectedPlanetType = _selectedPlanetType
                };
                invoker.Do(cmd);
            }
            else if (mode == Mode.Select)
                drawing.SelectPlanetAtPosition(e.Location);
        }

        private void repeatButton_Click(object sender, EventArgs e)
        {
            invoker.Repeat();
        }

        private void undoButton_Click(object sender, EventArgs e)
        {
            invoker.Undo();
        }

        private void deleteButton_Click(object sender, EventArgs e)
        {
            if (drawing.SelectedPlanet != null)
            {
                DeleteCommand cmd = new DeleteCommand() { TargetDrawing = drawing, Planet = drawing.SelectedPlanet };
                invoker.Do(cmd);
            }
        }

        private void refreshTimer_Tick(object sender, EventArgs e)
        {
            if (drawing.IsDirty)
                drawing.Draw(drawingPanel.CreateGraphics());
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            refreshTimer.Start();
            invoker.Start();
        }

        private void saveButton_Click(object sender, EventArgs e)
        {
            drawing.SavePlanets(drawingPanel.BackColor);
        }

        private void loadButton_Click(object sender, EventArgs e)
        {
            drawing.LoadPlanets();
        }


        public void SetBg(Color selectedColor)
        {
            drawingPanel.BackColor = selectedColor;
            drawing.BackgroundColor = selectedColor;
        }

        private void drawingPanel_Paint(object sender, PaintEventArgs e)
        {

        }

        private void MainForm_KeyDown(object sender, KeyEventArgs e)
        {
            Planet p = drawing.SelectedPlanet;
            if (p != null)
            {
                if (e.KeyCode == Keys.A)
                {
                    p.Location = new Point(p.Location.X - 15, p.Location.Y);
                }
                else if (e.KeyCode == Keys.D)
                {
                    p.Location = new Point(p.Location.X + 15, p.Location.Y);
                }
                else if (e.KeyCode == Keys.W)
                {
                    p.Location = new Point(p.Location.X, p.Location.Y - 15);
                }
                else if (e.KeyCode == Keys.S)
                {
                    p.Location = new Point(p.Location.X, p.Location.Y + 15);
                }
                else if (e.KeyCode == Keys.Z)
                {
                    p.Size = new Size(p.Size.Height + 20, p.Size.Width + 20);
                }
                else if (e.KeyCode == Keys.X)
                {
                    //drawingPanel.Invalidate();
                    p.Size = new Size(p.Size.Height - 20, p.Size.Width - 20);
                }
                else if (e.KeyCode == Keys.Q)
                {
                    DeleteCommand cmd = new DeleteCommand() { TargetDrawing = drawing, Planet = drawing.SelectedPlanet };
                    invoker.Do(cmd);
                }
                else if (e.KeyCode == Keys.R)
                {
                    invoker.Repeat();
                }
                drawing.IsDirty = true;

            }
        }

        private void buttonSaveAsJpeg_Click(object sender, EventArgs e)
        {
           drawing.SaveToJpg(drawingPanel.Width, drawingPanel.Height, drawingPanel);
        }

        private void colorChooserButton_Click(object sender, EventArgs e)
        {
            ColorDialog colorDlg = new ColorDialog();
            if (colorDlg.ShowDialog() == DialogResult.OK)
            {
                drawingPanel.BackColor = colorDlg.Color;
                drawing.BackgroundColor = colorDlg.Color;
            }
            drawing.IsDirty = true;
        }


    }
}
