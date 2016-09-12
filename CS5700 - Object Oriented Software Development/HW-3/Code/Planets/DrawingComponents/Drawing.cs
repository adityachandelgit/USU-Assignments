using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.Drawing.Imaging;
using System.Windows.Forms;
using Planets.Properties;

namespace Planets.DrawingComponents
{
    // NOTE: This class at least one encapsulation problem that hurts its coupling and cohesion
    [Serializable]
    public class Drawing
    {
        private List<Planet> planets = new List<Planet>();
        private bool isDirty = false;
        private object myLock = new object();
        private Color backgroundColor;

        public Color BackgroundColor
        {
            get { return backgroundColor; }
            set { backgroundColor = value; }
        }

        public Planet SelectedPlanet { get; set; }
        public bool IsDirty
        {
            get { return isDirty; }
            set { isDirty = value; }
        }

        public void AddPlanet(Planet planet)
        {
            if (planet != null)
            {
                lock (myLock)
                {
                    planets.Add(planet);
                    isDirty = true;
                }
            }
        }

        public void RemovePlanet(Planet planet)
        {
            if (planet != null)
            {
                lock (myLock)
                {
                    if (SelectedPlanet == planet)
                        SelectedPlanet = null;
                    planets.Remove(planet);
                    isDirty = true;
                }
            }
        }

        public void SelectPlanetAtPosition(Point location)
        {
            if (SelectedPlanet != null)
                SelectedPlanet.IsSelected = false;

            SelectedPlanet = FindPlanetAtPosition(location);

            if (SelectedPlanet != null)
                SelectedPlanet.IsSelected = true;

            isDirty = true;
        }

        public Planet FindPlanetAtPosition(Point location)
        {
            Planet result = null;
            lock (myLock)
            {
                for (int i=planets.Count-1; i>=0; i--)
                {
                    if (location.X >= planets[i].Location.X &&
                        location.X < planets[i].Location.X + planets[i].Size.Width &&
                        location.Y >= planets[i].Location.Y &&
                        location.Y < planets[i].Location.Y + planets[i].Size.Height)
                    {
                        result = planets[i];
                        break;
                    }
                }
            }
            return result;
        }

        public void Draw(Graphics drawingPlan)
        {
            drawingPlan.Clear(backgroundColor);
            lock (myLock)
            {
                foreach (Planet t in planets)
                    t.Draw(drawingPlan);
                isDirty = false;
            }
        }

        public void SavePlanets(Color backColor)
        {
            string fileSavePath = "D:\\SavedPlanets.dat";
            List<Object> thingsToSave = new List<object>();
            thingsToSave.Add(planets);
            thingsToSave.Add(backColor);
            Utilities.FileUtilities.WriteToBinaryFile(fileSavePath, thingsToSave);
            MessageBox.Show("Binary file saved at: " + fileSavePath);
        }

        public void LoadPlanets()
        {
            List<Object> thingsToLoad = new List<object>();
            thingsToLoad = Utilities.FileUtilities.ReadFromBinaryFile<List<Object>>("D:\\SavedPlanets.dat");
            planets = (List<Planet>) thingsToLoad[0];
            backgroundColor = (Color)thingsToLoad[1];
            isDirty = true;
        }

        public void SaveToJpg(int panelWidth, int panelHeight, Panel drawingPanel)
        {
            string imageSavePath = @"D:\PlanetBMP.png";
            using (Bitmap bmp = new Bitmap(panelWidth, panelHeight))
            using (Graphics gr = Graphics.FromImage(bmp))
            {
                Color backColor = drawingPanel.BackColor;
                Brush b = new SolidBrush(backColor);
                gr.FillRectangle(b, 0, 0, drawingPanel.Width, drawingPanel.Height);
                foreach (var planet in planets)
                {
                    gr.DrawImage(planet.Image, planet.Location.X, planet.Location.Y, planet.Size.Height, planet.Size.Width);
                }
                bmp.Save(imageSavePath, ImageFormat.Png);
                MessageBox.Show("PNG file saved at: " + imageSavePath);
            }
        }

    }
}
