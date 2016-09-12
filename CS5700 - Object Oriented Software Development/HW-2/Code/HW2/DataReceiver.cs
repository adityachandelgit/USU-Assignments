using System.Net;
using System.Net.Sockets;
using System.Threading;

namespace HW2
{
    class DataReceiver
    {
        private UdpClient _udpClient;
        private bool _keepGoing;
        private Thread _myRunThread;

        public void Start()
        {
            _udpClient = new UdpClient(14000);
            _keepGoing = true;
            _myRunThread = new Thread(Run);
            _myRunThread.Start();
        }

        public void Stop()
        {
            _keepGoing = false;
        }


        private void Run()
        {
            while (_keepGoing)
            {
                IPEndPoint ep = new IPEndPoint(IPAddress.Any, 0);
                _udpClient.Client.ReceiveTimeout = 1000;
                try
                {
                    var messageByes = _udpClient.Receive(ref ep);
                    if (messageByes != null)
                    {
                        RacerStatus statusMessage = RacerStatus.Decode(messageByes);
                        if (statusMessage != null)
                        {

                            if (Parsers.ListRacersDict.ContainsKey(statusMessage.RacerBibNumber))
                            {
                                Parsers.ListRacersDict[statusMessage.RacerBibNumber].AtSensor = statusMessage.SensorId;
                                Parsers.ListRacersDict[statusMessage.RacerBibNumber].CurrentTimeStamp = statusMessage.Timestamp;
                                Parsers.ListRacersDict[statusMessage.RacerBibNumber].Notify();
                            }
                        }
                    }
                }
                catch (SocketException err)
                {
                    if (err.SocketErrorCode != SocketError.Interrupted && err.SocketErrorCode != SocketError.TimedOut)
                        throw;
                }
            }
        }

    }
}
