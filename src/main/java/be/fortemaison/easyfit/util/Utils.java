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

    public static final DateFormat SHORT_DATE_FORMATTER = new SimpleDateFormat(IConstants.SHORT_DATE_FORMAT);

    public static final DateFormat DATE_FORMATTER = new SimpleDateFormat(IConstants.DATE_FORMAT);

    public static final DecimalFormat NUMBER_FORMATTER = new DecimalFormat(IConstants.DECIMAL_PATTERN);

}
