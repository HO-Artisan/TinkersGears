package ho.artisan.tgears.util;

public final class MathUtil {

    private MathUtil() {}

    public static int findN(int x) {
        if (x < 1) {
            return -1;
        }

        int highestPower = Integer.highestOneBit(x);

        int n = 0;
        while (highestPower > 1) {
            highestPower >>= 1;
            n++;
        }

        return n;
    }
}
