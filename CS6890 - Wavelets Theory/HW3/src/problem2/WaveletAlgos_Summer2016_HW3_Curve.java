
package problem2;

/**
 * @author Vladimir Kulyukin
 */
public class WaveletAlgos_Summer2016_HW3_Curve extends Function {

    public WaveletAlgos_Summer2016_HW3_Curve() {
    }

    @Override
    public double v(double x) {
        return Math.cos(5 * Math.PI * x / 512.0);
    }
}
