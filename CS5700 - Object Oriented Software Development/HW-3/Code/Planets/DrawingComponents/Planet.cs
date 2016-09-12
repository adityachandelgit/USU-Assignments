using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.Drawing.Imaging;
using Planets.DrawingComponents;
using Planets.FlyWeight;

namespace Planets.DrawingComponents
{
    // NOTE: This class has several problems
    [Serializable]
    public abstract class Planet
    {
        public enum PossiblePlanets { Blue, Green, Red, Yellow, Earth, Asteroid, RedSun, BlueSun };

        protected Image myImage;
        [NonSerialized]
        protected Pen outlinePen = new Pen(Color.Gray, 1.0F);

        public Image Image { get { return myImage; } }
        public Point Location { get; set; }
        public Size Size { get; set; }
        public bool IsSelected { get; set; }

        protected Planet(String resourceName)
        {
            myImage = FlyweightBitmapFactory.GetImage(resourceName);
        }

        public static Planet Create(PossiblePlanets selectedPlanet)
        {
            Planet result = null;
            switch (selectedPlanet)
            {
                case PossiblePlanets.Blue:
                    result = new PlanetBlue() { Size = new Size(70, 70) };
                    break;
                case PossiblePlanets.Green:
                    result = new PlanetGreen() { Size = new Size(120, 120) };
                    break;
                case PossiblePlanets.Red:
                    result = new PlanetRed() { Size = new Size(65, 65) };
                    break;
                case PossiblePlanets.Yellow:
                    result = new PlanetYellow() { Size = new Size(40, 40) };
                    break;
                case PossiblePlanets.Earth:
                    result = new PlanetEarth() { Size = new Size(70, 70) };
                    break;
                case PossiblePlanets.Asteroid:
                    result = new Asteroid() { Size = new Size(35, 35) };
                    break;
                case PossiblePlanets.RedSun:
                    result = new RedSun() { Size = new Size(120, 120) };
                    break;
                case PossiblePlanets.BlueSun:
                    result = new BlueSun() { Size = new Size(80, 80) };
                    break;
                
            }
            return result;
        }

        public void Draw(Graphics graphics)
        {
            if (graphics != null && Image!=null)
                graphics.DrawImage(Image, Location.X, Location.Y, Size.Width, Size.Height);

            if (IsSelected)
            {
                if (outlinePen == null)
                {
                    outlinePen = new Pen(Color.Gray, 1.0F);
                }
                graphics.DrawRectangle(outlinePen, Location.X, Location.Y, Size.Width, Size.Height);
            }
        }

        

    }
}
