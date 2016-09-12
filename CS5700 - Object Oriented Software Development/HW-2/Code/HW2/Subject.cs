using System.Collections.Generic;

namespace HW2
{
    public class Subject
    {
        private object myLock = new object();
        private List<RacerObserver> subscribers = new List<RacerObserver>();

        public List<RacerObserver> Subscribers { get { return subscribers; } }

        public void Subscribe(RacerObserver observer)
        {
            lock (myLock)
            {
                if (observer != null && !subscribers.Contains(observer))
                    subscribers.Add(observer);
            }
        }

        public void Unsubscribe(RacerObserver observer)
        {
            lock (myLock)
            {
                if (subscribers.Contains(observer))
                    subscribers.Remove(observer);
            }
        }

        public void Notify()
        {
            lock (myLock)
            {
                foreach (RacerObserver observer in subscribers)
                    observer.Update(this.Clone());
            }
        }

        public virtual Subject Clone()
        {
            return MemberwiseClone() as Subject;
        }


    }
}
