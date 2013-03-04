package be.fortemaison.webweights.form;

import be.fortemaison.webweights.model.User;
import be.fortemaison.webweights.util.Utils;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class UserForm {

    private Integer id;

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
        this.id = user.getId();
        this.userName = user.getUsername();
        this.updateDate = user.getUpdateDate();
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Date getUpdateDate () {
        return updateDate;
    }

    public void setUpdateDate (Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateDateLabel () {
        return Utils.DATE_FORMATTER.format(this.updateDate);
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String name) {
        this.userName = name;
    }

    public User getUser () {
        User user = new User(userName);
        user.setId(id);
        user.setUpdateDate(updateDate);
        return user;
    }
}
