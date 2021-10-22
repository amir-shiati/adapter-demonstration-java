package client;

import core.*;

// credits: https://github.com/LongHuu100/java-adapter-pattern
public class Main {
    private static float[] analogData = {0.2f, 1.4f, 3.12f, 0.9f};
    private static byte[] binData = {0, 1, 1, 0};
    private static float[] analogData2 = {1.2f, 1.4f, 0.12f, 0.9f};

    public static void main(String[] args) {
        AnalogSignal analogSignal = new FloatAnalogSignal(analogData);
        analogSignal.printAnalog();

        DigitSignal digitSignal = new BinDigitSignal(binData);
        digitSignal.printDigit();

        AnalogToDigitAdapter adAdapter = new AnalogToDigitAdapter(analogSignal);
        adAdapter.printDigit();

        adAdapter.setAnalogData(analogData2);
        adAdapter.printDigit();
    }
}
