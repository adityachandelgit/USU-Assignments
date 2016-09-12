package problem1;

import java.util.ArrayList;

public class OneDHaar {

	public static void orderedFastHaarWaveletTransformForNumIters(double[] sample, int num_iters) {

		// Check if num_iters is power of 2 or not
		if (!(sample.length > 0) && ((sample.length & (sample.length - 1)) == 0)) {
			return;
		}

		// Count number of sweeps
		final int powerOf2 = (int) (Math.log(sample.length) / Math.log(2.0));

		// Calculate a and c coefficients for 'num_iters' number of iterations
		ArrayList<Double> a = new ArrayList<>();
		ArrayList<Double> c = new ArrayList<>();
		for (int i = 1; i <= num_iters; i++) {
			a.clear();
			c.clear();
			// Calculate late index of a coefficient in sample signal
			int last_index = ((int) Math.pow(2.0, (double) (powerOf2 - i + 1))) - 1;
			for (int j = 0; j < last_index; j += 2) {
				double a1 = (sample[j] + sample[j + 1]) / 2.0;
				double c1 = (sample[j] - sample[j + 1]) / 2.0;
				a.add(a1);
				c.add(c1);
			}

			// Update sample signal with new values after sweep
			int size = (int) Math.pow(2.0, (double) (powerOf2 - i));
			for (int k = 0; k < size; k++) {
				sample[k] = a.get(k);
				sample[k + size] = c.get(k);
			}
		}

	}

	public static void inPlaceFastHaarWaveletTransformForNumIters(double[] sample, int num_iters) {

		// Sample length
		int len = sample.length;

		// Check if num_iters is power of 2 or not
		if (!(len > 0) && ((len & (len - 1)) == 0)) {
			return;
		}

		// Increment Index
		int index = 1;

		// Gap size
		int num_avg_element = 2;

		// Calculate in-place HWT
		double a = 0;
		double c = 0;
		// Loop for each iteration
		for (int i = 1; i <= num_iters; i++) {
			len = len / 2;
			// Loop till first half of the sample
			for (int j = 0; j < len; j++) {
				// Calculate 'a' coefficient
				a = (sample[num_avg_element * j] + sample[num_avg_element * j + index]) / 2.0;
				// Calculate 'c' coefficient
				c = (sample[num_avg_element * j] - sample[num_avg_element * j + index]) / 2.0;
				// Assign 'a' coefficient to original sample
				sample[num_avg_element * j] = a;
				// Assign 'c' coefficient to original sample
				sample[num_avg_element * j + index] = c;
			}
			index = num_avg_element;
			num_avg_element *= 2;
		}

	}

	// Utility for printing sample
	public static void displaySample(double[] sample) {
		System.out.print("Sample: ");
		for (int i = 0; i < sample.length; i++) {
			System.out.print(sample[i] + " ");
		}
		System.out.println();
	}

}
