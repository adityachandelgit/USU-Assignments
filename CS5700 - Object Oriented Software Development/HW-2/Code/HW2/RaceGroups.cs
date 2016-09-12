
namespace HW2
{
    public class RaceGroups
    {

        private int groupNumber;
        private string groupLabel;
        private long startTime;
        private int minBibNumber;
        private int maxBibNumber;

        public RaceGroups(int groupNumber, string groupLabel, long startTime, int minBibNumber, int maxBibNumber)
        {
            this.groupNumber = groupNumber;
            this.groupLabel = groupLabel;
            this.startTime = startTime;
            this.minBibNumber = minBibNumber;
            this.maxBibNumber = maxBibNumber;
        }

        public int GroupNumber
        {
            get { return groupNumber; }
        }

        public string GroupLabel
        {
            get { return groupLabel; }
        }

        public long StartTime
        {
            get { return startTime; }
        }

        public int MinBibNumber
        {
            get { return minBibNumber; }
        }

        public int MaxBibNumber
        {
            get { return maxBibNumber; }
        }
    }
}
