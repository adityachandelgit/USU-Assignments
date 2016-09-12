package problem2;

/**
 * @author vladimir kulyukin
 */
public class Partition {
    
    public static double[] partition(double from, double upto, double step) {
        if ( upto <= from ) return null;
        int n = (int)((upto - from)/step) + 1;
        double[] interval = new double[n];
       
        //System.out.println("n=" + n);
        int i;
        double curr;
        for(i = 0, curr = from; i < n; i++, curr += step) {
            //System.out.println("i=" + i + " " + "curr=" + curr);
            interval[i] = curr;
        }
        return interval;
    }
    
    public static double norm(double[] partition) {
       if ( partition.length < 2 ) return 0;
       
       int i;
       double norm = partition[1] - partition[0];
       double curr_norm = 0;
       for(i = 1; i < partition.length-1; i++) {
            curr_norm = partition[i+1] - partition[i];
            if (curr_norm > norm)
                norm = curr_norm;
       }
       return norm; 
    }
    
    public static double[] regularPartition(double from, double upto, int n) {
        if ( upto <= from ) return null;
        return Partition.partition(from, upto, (upto-from)/n);
    }
    
    public static double ithElementOfRegularPartition(double from, double upto, int n, int i) {
        if ( upto <= from ) throw new IllegalArgumentException("upto must be > than from");
        if ( i > n ) throw new IllegalArgumentException("i must be < n");
        return from + 1.0*i*(upto - from)/n;
    }
}
