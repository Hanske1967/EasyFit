package be.fortemaison.easyfit.util;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 12/03/13
 * Time: 11:46
 * To change this template use File | Settings | File Templates.
 */
public final class PasswordService {

    private static final Logger LOGGER = Logger.getLogger(PasswordService.class);

    private static PasswordService instance;

    private PasswordService () {
    }

    public static synchronized PasswordService getInstance () //step 1
    {
        if (instance == null) {
            instance = new PasswordService();
        }
        return instance;
    }

    public synchronized String encrypt (String plaintext) {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(plaintext.getBytes("UTF-8"));
            byte raw[] = md.digest(); //step 4
            hash = Base64.getEncoder().encodeToString(raw);
            return hash;
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return hash;
    }

}