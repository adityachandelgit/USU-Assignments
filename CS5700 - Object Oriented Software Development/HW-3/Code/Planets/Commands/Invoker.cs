using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Planets.Commands
{
    public class Invoker
    {
        private Thread myThread = null;
        private bool keepGoing = false;
        private Queue<Command> commandsToBeExecuted = new Queue<Command>();
        private object queueLock = new object();
        private object historyLock = new object();

        private Stack<Command> history = new Stack<Command>();

        public Command LastCommand
        {
            get
            {
                Command cmd = PeekAtHistory();
                return cmd;
            }
        }

        public void Do(Command cmd)
        {
            lock (queueLock)
            {
                commandsToBeExecuted.Enqueue(cmd);
            }
        }

        public void Repeat()
        {
            Do(LastCommand.Clone());
        }

        public void Undo()
        {
            Command cmd = PopHistory();
            if (cmd != null)
                cmd.Undo();
        }

        public void Start()
        {
            myThread = new Thread(new ThreadStart(Process));
            keepGoing = true;
            myThread.Start();
        }

        public void Stop()
        {
            keepGoing = false;
        }

        #region Private Methods
        private void Process()
        {
            while (keepGoing)
            {
                Command cmd = GetCommand();
                if (cmd!=null)
                {
                    lock (historyLock)
                    {
                        history.Push(cmd);
                    }
                    cmd.Execute();
                }
                else
                    Thread.Sleep(1);
            }
        }

        private Command PeekAtHistory()
        {
            Command cmd = null;
            lock (historyLock)
            {
                if (history.Count > 0)
                    cmd = history.Peek();
            }
            return cmd;
        }

        private Command PopHistory()
        {
            Command cmd = null;
            lock (historyLock)
            {
                if (history.Count > 0)
                    cmd = history.Pop();
            }
            return cmd;
        }

        private Command GetCommand()
        {
            Command cmd = null;
            lock (queueLock)
            {
                if (commandsToBeExecuted.Count > 0)
                    cmd = commandsToBeExecuted.Dequeue();
            }
            return cmd;
        }
        #endregion
    }
}
