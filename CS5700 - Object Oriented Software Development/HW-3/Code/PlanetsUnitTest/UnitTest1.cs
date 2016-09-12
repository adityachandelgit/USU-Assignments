using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Windows.Forms;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Planets.Commands;
using Planets.DrawingComponents;
using Planets.FlyWeight;
using Planets.Utilities;

namespace PlanetsUnitTest
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void TestCreateCommand()
        {
            CreateCommand createCommand = new CreateCommand();
            Point p = new Point(10, 20);
            createCommand.Location = p;
            Assert.AreEqual(10, createCommand.Location.X);
            Assert.AreEqual(20, createCommand.Location.Y);
        }

        [TestMethod]
        public void TestDeleteCommand()
        {
            DeleteCommand deleteCommand = new DeleteCommand();
            Planet p = new PlanetBlue();
            deleteCommand.Planet = p;
            Assert.AreEqual(p, deleteCommand.Planet);

        }

        [TestMethod]
        public void TestPlanet()
        {
            var bluePlanet = new PlanetBlue();
            var bluePlanetType = bluePlanet.GetType();
            Planet p = Planet.Create(Planet.PossiblePlanets.Blue);
            Assert.IsInstanceOfType(p, bluePlanetType);
        }

        [TestMethod]
        public void TestFlyweightFactory()
        {
            Image image1 = Planets.Properties.Resources.p1;
            Image image2 = FlyweightBitmapFactory.GetImage("p1");

            Size size1 = image1.Size;
            float height1 = image1.PhysicalDimension.Height;
            float width1 = image1.PhysicalDimension.Width;


            Size size2 = image2.Size;
            float height2 = image2.PhysicalDimension.Height;
            float width2 = image2.PhysicalDimension.Width;

            Assert.AreEqual(size1, size2);
            Assert.AreEqual(height1, height2);
            Assert.AreEqual(width1, width2);
        }


        [TestMethod]
        public void TestSaveToPng()
        {
            Panel p = new Panel();
            p.BackColor = Color.Red;
            Drawing drawing = new Drawing();
            drawing.SaveToJpg(100, 100, p);
            bool doesExist = File.Exists("D:\\PlanetBMP.png");
            Assert.IsTrue(doesExist);
        }

        [TestMethod]
        public void TestFileUtilitiesSave()
        {
            try
            {
                // Bad path passed to the function!
                FileUtilities.WriteToBinaryFile("Gibberish Path", new Object(), true);
                // If it gets to this line, no exception was thrown
                Assert.Fail();
            }
            catch
            {
                // ignored
            }
        }

        [TestMethod]
        public void TestFileUtilitiesLoad()
        {
            try
            {
                // Bad path passed to the function!
                FileUtilities.ReadFromBinaryFile<List<Planet>>("Gibberish Path");
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
