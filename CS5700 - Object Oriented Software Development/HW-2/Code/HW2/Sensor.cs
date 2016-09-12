namespace HW2
{
    public class Sensor
    {
        public Sensor(int sensorNumber, int sensorDistance)
        {
            SensorNumber = sensorNumber;
            SensorDistance = sensorDistance;
        }

        public int SensorNumber { get; private set; }

        public int SensorDistance { get; private set; }
    }
}
