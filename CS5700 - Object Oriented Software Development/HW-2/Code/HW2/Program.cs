using System.Windows.Forms;

namespace HW2
{
    class Program
    {
        static void Main(string[] args)
        {
            //Start recieving data
            DataReceiver dr = new DataReceiver();
            dr.Start();
            
            //Load CSVs
            Parsers.LoadGroupsFromCsv(@"c:\Groups.csv");
            Parsers.LoadSensorsFromCsv(@"c:\Sensors.csv");
            Parsers.LoadRacersFromCsv(@"c:\Racers.csv");
            Parsers.AssignGroupToRacers();

            //Run the form
            Application.Run(new ControlForm());
        }
        
    }
}
