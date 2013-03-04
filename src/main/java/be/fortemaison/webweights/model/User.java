package be.fortemaison.webweights.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 13/02/13
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private Integer id;

    private String username;

    private Date updateDate;

    /**
     *
     */
    public User () {
        //
    }

    /**
     * @param username
     */
    public User (String username) {
        this.username = username;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public Date getUpdateDate () {
        return updateDate;
    }

    public void setUpdateDate (Date updateDate) {
        this.updateDate = updateDate;
    }
}
