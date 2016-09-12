using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Planets.DrawingComponents;

namespace Planets.Commands
{
    public abstract class Command
    {
        public Drawing TargetDrawing { get; set; }

        public abstract void Execute();
        public abstract void Undo();
        public abstract Command Clone();

    }
}
