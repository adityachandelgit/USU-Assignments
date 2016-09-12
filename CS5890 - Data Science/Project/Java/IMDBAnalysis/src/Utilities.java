import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import com.google.common.primitives.Longs;

public class Utilities {

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static int mode(long a[]) {
		int maxValue = 0, maxCount = 0;

		for (int i = 0; i < a.length; ++i) {
			int count = 0;
			for (int j = 0; j < a.length; ++j) {
				if (a[j] == a[i])
					++count;
			}
			if (count > maxCount) {
				maxCount = count;
				maxValue = (int) a[i];
			}
		}

		return maxValue;
	}

	public static double[] meanMedianMode(ArrayList<Double> list) {

		long[] longArray = Longs.toArray(list);

		double[] doubleArray = new double[longArray.length];
		for (int i = 0; i < longArray.length; i++) {
			doubleArray[i] = (double) longArray[i];
		}

		Mean mean = new Mean();
		double meanValue = mean.evaluate(doubleArray);

		Median median = new Median();
		double medianValue = median.evaluate(doubleArray);

		int modeValue = (int) Utilities.mode(longArray);

		double[] all3 = new double[3];
		all3[0] = meanValue;
		all3[1] = medianValue;
		all3[2] = modeValue;

		return all3;

	}

	public static double[] longArrayListToDoubleArray(ArrayList<Long> longArrayList) {

		long[] longArray = Longs.toArray(longArrayList);

		double[] doubleArray = new double[longArray.length];

		for (int i = 0; i < longArray.length; i++) {
			doubleArray[i] = (double) longArray[i];
		}

		return doubleArray;

	}

	public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
		double dotProduct = 0.0;
		double normA = 0.0;
		double normB = 0.0;
		for (int i = 0; i < vectorA.length; i++) {
			dotProduct += vectorA[i] * vectorB[i];
			normA += Math.pow(vectorA[i], 2);
			normB += Math.pow(vectorB[i], 2);
		}
		return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}

	/*public static void normalize(ArrayList<MovieInfo> lMoIn) {
		
		ArrayList<Double> imdbVotesArrayList = new ArrayList<>();

		for(int i = 0; i < lMoIn.size(); i++) {
			double currentVotes = lMoIn.get(i).getImdbVotes();
			if(currentVotes != -1) {
				imdbVotesArrayList.add(lMoIn.get(i).getImdbVotes());
			}
		}
		
		double maxVotes = Collections.max(imdbVotesArrayList);
		Collections.sort(imdbVotesArrayList);
		double minVotes = imdbVotesArrayList.get(0);
		
		//System.out.println("Max: " + maxVotes + "  |  " + "Min: " + minVotes);
		
		for(int i = 0; i < lMoIn.size(); i++) {
			double number = lMoIn.get(i).getImdbVotes();
			double normalizedValue = (number - minVotes) / (maxVotes - minVotes);
			lMoIn.get(i).setImdbVotesNormz(normalizedValue);
		}
		
		
		
		
		ArrayList<Double> imdbRatingArrayList = new ArrayList<>();

		for(int i = 0; i < lMoIn.size(); i++) {
			double currentRating  = lMoIn.get(i).getImdbRating();
			if(currentRating != -1) {
				imdbRatingArrayList.add(currentRating);
			}
		}
		
		double maxRating = Collections.max(imdbRatingArrayList);
		Collections.sort(imdbRatingArrayList);
		double minRating = imdbRatingArrayList.get(0);
		
		//System.out.println("Max: " + maxRating + "  |  " + "Min: " + minRating);
		
		for(int i = 0; i < lMoIn.size(); i++) {
			double number = lMoIn.get(i).getImdbRating();
			double normalizedValue = (number - minRating) / (maxRating - minRating);
			//System.out.println(normalizedValue);
			lMoIn.get(i).setImdbRatingNormz(normalizedValue);
		}
		
		
		
		ArrayList<Double> rottenRatingArrayList = new ArrayList<>();

		for(int i = 0; i < lMoIn.size(); i++) {
			double currentRottenRating  = lMoIn.get(i).getRottenTomatoRating();
			if(currentRottenRating != -1) {
				rottenRatingArrayList.add(currentRottenRating);
			}
		}
		
		double maxRottenRating = Collections.max(rottenRatingArrayList);
		Collections.sort(rottenRatingArrayList);
		double minRottenRating = rottenRatingArrayList.get(0);
		
		//System.out.println("Max: " + maxRottenRating + "  |  " + "Min: " + minRottenRating);
		
		for(int i = 0; i < lMoIn.size(); i++) {
			double number = lMoIn.get(i).getRottenTomatoRating();
			double normalizedValue = (number - minRottenRating) / (maxRottenRating - minRottenRating);
			//System.out.println(normalizedValue);
			lMoIn.get(i).setRottenTomatoRatingNormz(normalizedValue);
		}
		
		
		
		
		ArrayList<Double> usGrossArrayList = new ArrayList<>();

		for(int i = 0; i < lMoIn.size(); i++) {
			double currentUsGross  = lMoIn.get(i).getUsGross();
			if(currentUsGross != -1) {
				usGrossArrayList.add(currentUsGross);
			}
		}
		
		double maxUsGross = Collections.max(usGrossArrayList);
		Collections.sort(usGrossArrayList);
		double minUsGross = usGrossArrayList.get(0);
		
		//System.out.println("Max: " + maxUsGross + "  |  " + "Min: " + minUsGross);
		
		for(int i = 0; i < lMoIn.size(); i++) {
			double number = lMoIn.get(i).getUsGross();
			double normalizedValue = (number - minUsGross) / (maxUsGross - minUsGross);
			//System.out.println(normalizedValue);
			lMoIn.get(i).setUsGrossNormz(normalizedValue);
		}
		
		
		
		
		
		ArrayList<Double> worldGrossArrayList = new ArrayList<>();

		for(int i = 0; i < lMoIn.size(); i++) {
			double currentWorldGross  = lMoIn.get(i).getWorldwideGross();
			if(currentWorldGross != -1) {
				worldGrossArrayList.add(currentWorldGross);
			}
		}
		
		double maxWorldGross = Collections.max(worldGrossArrayList);
		Collections.sort(worldGrossArrayList);
		double minWorldGross = worldGrossArrayList.get(0);
		
		//System.out.println("Max: " + maxWorldGross + "  |  " + "Min: " + minWorldGross);
		
		for(int i = 0; i < lMoIn.size(); i++) {
			double number = lMoIn.get(i).getUsGross();
			double normalizedValue = (number - minWorldGross) / (maxWorldGross - minWorldGross);
			//System.out.println(normalizedValue);
			lMoIn.get(i).setWorldwideGrossNormz(normalizedValue);
		}
		
		
		
		
		ArrayList<Double> dvdSalesArrayList = new ArrayList<>();

		for(int i = 0; i < lMoIn.size(); i++) {
			double currentdvdSales  = lMoIn.get(i).getUsDvdSales();
			if(currentdvdSales != -1) {
				dvdSalesArrayList.add(currentdvdSales);
			}
		}
		
		double maxDvdSales = Collections.max(dvdSalesArrayList);
		Collections.sort(dvdSalesArrayList);
		double minDvdSales = dvdSalesArrayList.get(0);
		
		//System.out.println("Max: " + maxRottenRating + "  |  " + "Min: " + minRottenRating);
		
		for(int i = 0; i < lMoIn.size(); i++) {
			double number = lMoIn.get(i).getUsGross();
			double normalizedValue = (number - minDvdSales) / (maxDvdSales - minDvdSales);
			//System.out.println(normalizedValue);
			lMoIn.get(i).setUsDvdSalesNormz(normalizedValue);
		}
		
		

	}*/

}
