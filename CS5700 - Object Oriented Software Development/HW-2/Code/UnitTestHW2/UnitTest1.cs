using System;
using HW2;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace UnitTestHW2
{
    [TestClass]
    public class UnitTest1
    {

        [TestMethod]
        public void TestRacer()
        {
            Racer r = new Racer("Aditya", "Chandel", 10, 1);
            Assert.AreEqual("Aditya", r.FirstName);
            Assert.AreEqual("Chandel", r.LastName);
            Assert.AreEqual(10, r.BibNumber);
            Assert.AreEqual(1, r.RacerGroup);
        }


        [TestMethod]
        public void TestSensor()
        {
            Sensor s = new Sensor(5, 50);
            Assert.AreEqual(5, s.SensorNumber);
            Assert.AreEqual(50, s.SensorDistance);
        }

        [TestMethod]
        public void TestGroup()
        {
            RaceGroups g = new RaceGroups(2, "TheGroup", 100, 10, 5000);
            Assert.AreEqual(2, g.GroupNumber);
            Assert.AreEqual("TheGroup", g.GroupLabel);
            Assert.AreEqual(100, g.StartTime);
            Assert.AreEqual(10, g.MinBibNumber);
            Assert.AreEqual(5000, g.MaxBibNumber);
        }

        [TestMethod]
        public void TestGroupsParser()
        {
            try
            {
                // Bad path passed to the function!
                HW2.Parsers.LoadGroupsFromCsv("-0231=-3123CaefqvqergqgqgqrgGroups.csv");
                // If it gets to this line, no exception was thrown
                Assert.Fail();
            }
            catch
            {
                // ignored
            }
        }

        [TestMethod]
        public void TestSensorsParser()
        {
            try
            {
                // Bad path passed to the function!
                HW2.Parsers.LoadSensorsFromCsv("ZDXD:ASAAS!@!@!@!@@");
                // If it gets to this line, no exception was thrown
                Assert.Fail();
            }
            catch
            {
                // ignored
            }
        }

        [TestMethod]
        public void TestRacersParsers()
        {
            try
            {
                // Bad path passed to the function!
                HW2.Parsers.LoadRacersFromCsv("ZDXD:ASAAS!@!@!@!@@");
                // If it gets to this line, no exception was thrown
                Assert.Fail();
            }
            catch
            {
                // ignored
            }
        }


        [TestMethod]
        public void TestRacerObservers()
        {
            try
            {
                RacerObserver ro = new RacerObserver();
                ro.Update(new Subject());
                // If it gets to this line, no exception was thrown
                Assert.Fail();
            }
            catch
            {
                // ignored
            }
        }


        [TestMethod]
        public void TestSubject()
        {
            try
            {
                Subject sub = new Subject();
                sub.Subscribe(new RacerObserver());
                // If it gets to this line, no exception was thrown
                Assert.Fail();
            }
            catch
            {
                // ignored
            }
        }


    }
}
