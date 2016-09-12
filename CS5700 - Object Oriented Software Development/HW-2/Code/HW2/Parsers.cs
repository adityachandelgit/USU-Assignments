using System;
using System.Collections.Generic;
using Microsoft.VisualBasic.FileIO;

namespace HW2
{
    public class Parsers
    {
        public static List<Racer> RacerList = new List<Racer>();
        public static List<RaceGroups> GroupList = new List<RaceGroups>();
        public static Dictionary<int, Sensor> ListSensorsDict = new Dictionary<int, Sensor>();
        public static Dictionary<int, Racer> ListRacersDict = new Dictionary<int, Racer>();

        public static void LoadRacersFromCsv(string inputPath)
        {
            TextFieldParser parser = new TextFieldParser(inputPath);
            parser.TextFieldType = FieldType.Delimited;
            parser.SetDelimiters(",");
            while (!parser.EndOfData)
            {
                //Process row
                string[] fields = parser.ReadFields();

                string fn = fields[0];
                string ln = fields[1];
                int bib = Convert.ToInt32(fields[2]);
                int group = Convert.ToInt32(fields[3]);

                ListRacersDict.Add(bib, new Racer(fn, ln, bib, group));
            }
            parser.Close();
        }


        public static void LoadGroupsFromCsv(string inputPath)
        {
            TextFieldParser parser = new TextFieldParser(inputPath);
            parser.TextFieldType = FieldType.Delimited;
            parser.SetDelimiters(",");
            while (!parser.EndOfData)
            {
                //Process row
                string[] fields = parser.ReadFields();

                int gn = Convert.ToInt32(fields[0]);
                string lbl = fields[1];
                long st = Convert.ToInt64(fields[2]);
                int minBib = Convert.ToInt32(fields[3]);
                int maxBib = Convert.ToInt32(fields[4]);

                RaceGroups g = new RaceGroups(gn, lbl, st, minBib, maxBib);
                GroupList.Add(g);
            }
            parser.Close();
        }


        public static void LoadSensorsFromCsv(string inputPath)
        {
            TextFieldParser parser = new TextFieldParser(inputPath);
            parser.TextFieldType = FieldType.Delimited;
            parser.SetDelimiters(",");
            while (!parser.EndOfData)
            {
                //Process row
                string[] fields = parser.ReadFields();

                int sn = Convert.ToInt32(fields[0]);
                int sg = Convert.ToInt32(fields[1]);

                ListSensorsDict.Add(sn, new Sensor(sn, sg));
            }
            parser.Close();
        }


        public static void AssignGroupToRacers()
        {
            foreach (RaceGroups groupItem in GroupList)
            {
                foreach (var racerItem in ListRacersDict)
                {
                    if (racerItem.Value.BibNumber >= groupItem.MinBibNumber &&
                        racerItem.Value.BibNumber <= groupItem.MaxBibNumber)
                    {
                        racerItem.Value.MyGroup = groupItem;
                    }
                }
            }
        }


    }
}
