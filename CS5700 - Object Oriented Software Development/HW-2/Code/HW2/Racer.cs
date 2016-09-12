namespace HW2
{
    public class Racer : Subject
    {
        public Racer(string firstName, string lastName, int bibNumber, int racerGroup)
        {
            this.FirstName = firstName;
            this.LastName = lastName;
            this.BibNumber = bibNumber;
            this.RacerGroup = racerGroup;
            this.AtSensor = 0;
            this.CurrentTimeStamp = 0;
        }

        public RaceGroups MyGroup { get; set; }

        public string FirstName { get; set; }

        public string LastName { get; set; }

        public int BibNumber { get; set; }

        public int RacerGroup { get; set; }

        public int AtSensor { get; set; }

        public long CurrentTimeStamp { get; set; }
    }
}
