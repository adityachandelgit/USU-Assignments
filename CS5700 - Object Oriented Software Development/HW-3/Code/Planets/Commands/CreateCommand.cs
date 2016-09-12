using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

using System.Drawing;
using Planets.DrawingComponents;

namespace Planets.Commands
{
    public class CreateCommand : Command
    {
        private Planet planetAddedToDrawing = null;

        public Point Location { get; set; }
        public Planet.PossiblePlanets SelectedPlanetType { get; set; }
        
        public override void Execute()
        {
            planetAddedToDrawing = Planet.Create(SelectedPlanetType);
            planetAddedToDrawing.Location = Location;
            TargetDrawing.AddPlanet(planetAddedToDrawing);
        }

        public override void Undo()
        {
            TargetDrawing.RemovePlanet(planetAddedToDrawing);
        }

        public override Command Clone()
        {
            CreateCommand clone = this.MemberwiseClone() as CreateCommand;
            clone.Location = new Point(Location.X + 20, Location.Y + 20);
            return clone;
        }
    }
}
