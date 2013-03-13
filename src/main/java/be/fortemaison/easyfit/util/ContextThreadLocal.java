package be.fortemaison.easyfit.util;

import be.fortemaison.easyfit.controller.Context;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 12/03/13
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
public class ContextThreadLocal {

    public static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>();

    public static void set (Context context) {
        threadLocal.set(context);
    }

    public static void unset () {
        threadLocal.remove();
    }

    public static Context get () {
        return threadLocal.get();
    }

}
