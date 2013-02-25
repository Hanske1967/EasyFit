package be.fortemaison.webweights.form;

import be.fortemaison.webweights.model.User;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class UserForm {

    private String userName;

    private Date updateDate;

    /**
     *
     */
    public UserForm () {
        //
    }

    /**
     *
     */
    public UserForm (final User user) {
        this.userName = user.getUsername();
    }

    public Date getUpdateDate () {
        return updateDate;
    }

    public void setUpdateDate (Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String name) {
        this.userName = name;
    }

    public User getUser () {
        return new User(userName);
    }
}
