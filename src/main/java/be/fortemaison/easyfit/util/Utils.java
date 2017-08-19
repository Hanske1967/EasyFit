package be.fortemaison.easyfit.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 15/02/13
 * Time: 12:41
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    public static final String WEEKDAY_DATE_FORMAT = "EEE";

    public static final String SHORT_DATE_FORMAT = "dd/MM/yyyy";

    public static final String DATE_FORMAT = "E dd/MM/yyyy";

    public static final String URL_DATE_FORMAT = "dd/MM/yyyy";

    public static final String DECIMAL_PATTERN = "# ###.#;-# ###.#;0";

    public static final String PROCENT = "%";




    public static final DateFormat WEEKDAY_DATE_FORMATTER = new SimpleDateFormat(WEEKDAY_DATE_FORMAT);

    public static final DateFormat SHORT_DATE_FORMATTER = new SimpleDateFormat(SHORT_DATE_FORMAT);

    public static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    public static final DateFormat URL_DATE_FORMATTER = new SimpleDateFormat(URL_DATE_FORMAT);

    public static final DecimalFormat NUMBER_FORMATTER = new DecimalFormat(DECIMAL_PATTERN);

}
