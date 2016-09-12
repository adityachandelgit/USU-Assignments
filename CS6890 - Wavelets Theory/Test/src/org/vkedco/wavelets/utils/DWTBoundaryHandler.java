package org.vkedco.wavelets.utils;

/**
 * Created by Aditya on 6/4/2016.
 */
public class DWTBoundaryHandler {

    // relative mirror wrapup
    public static int rmw(int s, int e, int i) {
        if ( s > e ) throw new IllegalArgumentException("Start = " + s + " > End = " + e);
        final int n = (e - s) + 1;
        if ( i >= 0 && i < n ) {
            return s+i;
        }
        else {
            int r = Math.abs(i) % n;
            if ( i < 0 ) {
                return ( r != 0 )? s + r -1: e;
            }
            else {
                return ( r != 0 )? e - r: e;
            }
        }
    }

    static void run_rmw_test(int s, int e, int is, int ie) { for(int i = is; i <= ie; i++) { display_rmw(s, e, i); }}
    // -3  -2  -1    0      1      2       3      4      5       6      7     8 ....
    //              [a(0), a(1), a(2), a(3), a(4), a(5), a(6), a(7)]
    //                  s      e
    public static void rmw_test00() { run_rmw_test(0, 1, -4, -1); run_rmw_test(0, 1,  2,  5); }
    // -3  -2  -1    0       1       2      3       4      5      6       7    8 ....
    //               [a(0), a(1), a(2), a(3), a(4), a(5), a(6), a(7)]
    //                          s=e
    public static void rmw_test01(int s) {
        display_rmw(s, s, -1);
        display_rmw(s, s, -2);
        display_rmw(s, s, -3);
        display_rmw(s, s, 0);
        display_rmw(s, s, 1);
        display_rmw(s, s, 2);
        display_rmw(s, s, 3);
        display_rmw(s, s, 4);
    }
    //    -3   -2   -1     0      1      2      3       4     5     6     7    8 ....
    //                    [a(0), a(1), a(2), a(3), a(4), a(5), a(6), a(7)]
    //                                 s                        e
    public static void rmw_test02() { run_rmw_test(1, 4, -5, -1); run_rmw_test(1, 4, 4,  8); }

    public static void display_rmw(int s, int e, int i) {
        System.out.println("rmw(s=" + s + ",e=" + e + ",i=" + i + ")=" + DWTBoundaryHandler.rmw(s, e, i));
    }

    public static void main(String[] args) { rmw_test02(); }
}
