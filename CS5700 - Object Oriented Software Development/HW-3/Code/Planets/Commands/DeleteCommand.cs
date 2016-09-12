using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Planets.DrawingComponents;

namespace Planets.Commands
{
    public class DeleteCommand : Command
    {
        public Planet Planet { get; set; }

        public override void Execute()
        {
            if (TargetDrawing!=null && Planet!=null)
                TargetDrawing.RemovePlanet(Planet);
        }

        public override void Undo()
        {
            if (TargetDrawing != null && Planet!=null)
                TargetDrawing.AddPlanet(Planet);
        }

        public override Command Clone()
        {
            return new DeleteCommand();         // Return an empty command that won't do anything
        }
    }
}
