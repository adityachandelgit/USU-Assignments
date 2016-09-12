using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Planets.Properties;

namespace Planets.FlyWeight
{
    public class FlyweightBitmapFactory
    {
        private static Dictionary<string, Image> resuableImages = new Dictionary<string, Image>();

        public static Image GetImage(string resourceName)
        {
            if (!resuableImages.ContainsKey(resourceName))
            {
                if (resourceName.Equals("p1"))
                {
                    resuableImages[resourceName] = Resources.p1;
                }
                else if (resourceName.Equals("p2"))
                {
                    resuableImages[resourceName] = Resources.p2;
                }
                else if (resourceName.Equals("p3"))
                {
                    resuableImages[resourceName] = Resources.p3;
                }
                else if (resourceName.Equals("p4"))
                {
                    resuableImages[resourceName] = Resources.p4;
                }
                else if (resourceName.Equals("p5"))
                {
                    resuableImages[resourceName] = Resources.p5;
                }
                else if (resourceName.Equals("a1"))
                {
                    resuableImages[resourceName] = Resources.a1;
                }
                else if (resourceName.Equals("s1"))
                {
                    resuableImages[resourceName] = Resources.s1;
                }
                else if (resourceName.Equals("s2"))
                {
                    resuableImages[resourceName] = Resources.s2;
                }
            }

            return resuableImages[resourceName];

        }


    }
}
