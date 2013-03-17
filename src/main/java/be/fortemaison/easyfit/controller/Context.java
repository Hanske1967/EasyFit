package be.fortemaison.easyfit.controller;

import be.fortemaison.easyfit.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 12/03/13
 * Time: 20:18
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Context implements Serializable {

    private User user;

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }


}
