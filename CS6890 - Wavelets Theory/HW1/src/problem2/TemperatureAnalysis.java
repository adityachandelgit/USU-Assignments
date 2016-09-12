package problem2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import problem1.OneDHaar;

public class TemperatureAnalysis {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		ArrayList<TempData> htSignalList = doPreprocessingAndHaarTransformation(readTxtFile("beehive_temperatures.txt"));
		
		/*for (int i = 0; i < htSignalList.size(); i++) {
			System.out.print(htSignalList.get(i).getTemperature() + ", ");
		}
		System.out.println();*/
		
		
		//*****************************************************************//
		//*****************************************************************//
//------// Programmatic Analysis of the Beehive Temperature data: Research //--------//
		//*****************************************************************//
		//*****************************************************************//
		
		// Creating pyramid
		int a = 0;
		for (int i = 0; i < Math.log(htSignalList.size()) / Math.log(2); i++) {
			for (int j = 0; j < Math.pow(2, i); j++) {
				a++;
				System.out.print(htSignalList.get(a).getTemperature() + ", ");
			}
			System.out.println();
		}
		System.out.println();
		
		
		// 1. Average of the entire temperate range.
		// A) The first coefficient represents the average temperate of entire period.
		//    Average temperate: 18.850847656249996
		System.out.println("Average temperate: " + htSignalList.get(0).getTemperature());
		System.out.println();
		
		
		// 2. Greatest oscillation in temperature.
		// A) In the code below, we can see that the maximum absolute value of any wavelet (Max or Min) is 3.29
		//    which means that the greatest temperature oscillation happened in this period, from
		//    2015-05-12_07-35-38 to 2015-05-12_07-45-38
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		for (int i = 1; i < htSignalList.size(); i++) {
			double temp = htSignalList.get(i).getTemperature();
			if(temp > max) {
				max = temp;
			}
			if(temp < min) {
				min = temp;
			}
		}
		System.out.println("Max: " + max);
		System.out.println("Min: " + min);
		System.out.println();
		
		
		// 3. Period of time when temperate oscillated smallest?
		// A) Minimum absolute frequency value of HAAR transformed signal tells us the minimum oscillation,
		//    From the output we can see that there are various 0.0 values, so during this time period, the
		//    oscillation was lowest. Like 2015-05-11_12-25-38 to 2015-05-11_12-35-38, 2015-05-11_23-35-37
		//    to 2015-05-11_23-45-37, etc.
		
		
		// 4. Can you tell apart morning, daytime, evening, night?
		// A) Yes, we can distinguish among morning, day, evening and night.
		//    Morning: It can be observed that during morning (6-12 AM), there is constant increase in the 
		//             temperature (indicated by -ve frequency of HAAR transformed value).
		//    Day (Afternoon): There is slight increase in temperature (or almost constant) during this period
		//             (12 noon to 6:30 PM), indicated by lower negative value of HAAR.
		//    Evening: There is constant decrease in the temperate during (6:30 PM to 11:30 PM) indicated by 
		//             high positive frequency value of HAAR signals. 
		//    Night:   Temperature remains low and constant during this period of time (12 Midnight to 6 AM),
		//             indicated by 0.0 HAAR frequency values. 
	}

	
	
	private static ArrayList<TempData> doPreprocessingAndHaarTransformation(ArrayList<TempData> data) {
		// Calculating if the length of data is in power of 2.
		double powerOf2 = Math.log(data.size()) / Math.log(2);

		// If it is now is exact power of 2 then either pad the data to make
		// it in power of 2 or reduce it to make it in power of 2.
		if (powerOf2 != (int) powerOf2) {
			
			// Reducing the data
			int down = (int) Math.floor(powerOf2);
			for (int i = data.size() - 1; i >= Math.pow(2, down); i--) {
				data.remove(i);
			}

			// Padding the data
			/*
			 * int up = (int) (Math.floor(powerOf2) + 1); int toPad = (int)
			 * (Math.pow(2, up) - h.size()); for (int i = 0; i < toPad; i++) {
			 * h.put(String.valueOf(i), 0.0); }
			 */
		}

		ArrayList<Double> a = new ArrayList<>();

		// Adding the data to arrayList of doubles (preparing data HAAR transform)
		for (TempData d : data) {
			a.add(d.getTemperature());
		}

		// Convert arraylist of temperature data to array of temperature data
		double[] signal = convertDoubles(a);
		
		// HAAR transform the temperature data
		OneDHaar.orderedFastHaarWaveletTransformForNumIters(signal, 8);
		
		//WaveletsMadeEasyCh01.displayArray(signal);
		
		for (int i = 0; i < signal.length; i++) {
			data.get(i).setTemperature(signal[i]);
		}
		
		return data;
	}

	// Imports the txt file into TempData arrayList.
	static ArrayList<TempData> readTxtFile(String path) throws FileNotFoundException, IOException {
		ArrayList<TempData> data = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] splitted = line.split("\\s+");
				data.add(new TempData(splitted[0], Double.valueOf(splitted[1])));
			}
		}
		return data;

	}

	// Converts List of doubles to Array of doubles.
	public static double[] convertDoubles(List<Double> doubles) {
		double[] ret = new double[doubles.size()];
		Iterator<Double> iterator = doubles.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			ret[i] = iterator.next();
			i++;
		}
		return ret;
	}

}
