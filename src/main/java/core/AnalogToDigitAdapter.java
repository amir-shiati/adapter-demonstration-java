package core;

import java.util.Arrays;


/**
 * <p>
 * Adapter - convert analog data to digit data.
 * </p>
 *
 * @author eric
 * @date Mar 8, 2016 1:07:00 PM
 */
public class AnalogToDigitAdapter implements DigitSignal {
    public static final float DEFAULT_THRESHOLD_FLOAT_TO_BIN = 1.0f; // default threshold,

    private AnalogSignal analogSignal;
    private byte[] digitData;
    private float threshold;
    private boolean cached;

    public AnalogToDigitAdapter(AnalogSignal analogSignal) {
        this(analogSignal, DEFAULT_THRESHOLD_FLOAT_TO_BIN);
    }

    public AnalogToDigitAdapter(AnalogSignal analogSignal, float threshold) {
        this.analogSignal = analogSignal;
        this.threshold = threshold;
        this.cached = false;
    }

    @Override
    public synchronized byte[] getDigit() {
        if (!cached) {
            float[] analogData = analogSignal.getAnalog();
            int len = analogData.length;
            digitData = new byte[len];

            for (int i = 0; i < len; i++) {
                digitData[i] = floatToByte(analogData[i]);
            }
        }

        return digitData;
    }

    // not supported, should set the inner analog data instead,
    @Override
    public void setDigit(byte[] digitData) {
        throw new UnsupportedOperationException();
    }

    public synchronized void setAnalogData(float[] analogData) {
        invalidCache();
        this.analogSignal.setAnalog(analogData);
    }

    public synchronized void invalidCache() {
        cached = false;
        digitData = null;
    }

    @Override
    public void printDigit() {
        System.out.println(Arrays.toString(getDigit()));
    }

    // float -> byte convert,
    private byte floatToByte(float f) {
        return (byte) (f >= threshold ? 1 : 0);
    }
}
