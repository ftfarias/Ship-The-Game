package ship.ui.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class NumberFormater {
    public static final NumberFormat DOUBLE_FORMAT = new DecimalFormat("#,##0.00");
    public static final NumberFormat INTEGER_FORMAT = new DecimalFormat("#,##0");

    private static final NumberFormat CURRENCY_FORMAT = new DecimalFormat("R$ #,##0.00");
    //private static final NumberFormat CURRENCY_FORMAT = DecimalFormat.getCurrencyInstance();

    public static String format(Double value) {
        return value == null ? "-" : DOUBLE_FORMAT.format(value);
    }


}
