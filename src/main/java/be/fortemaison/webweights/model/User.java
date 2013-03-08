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

    private String firstName;

    private String lastName;

    private Integer targetWeight;

    private Integer dayPoints;

    private Integer extraPoints;


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

    public Integer getExtraPoints () {
        return extraPoints;
    }

    public void setExtraPoints (Integer extraPoints) {
        this.extraPoints = extraPoints;
    }

    public Integer getDayPoints () {
        return dayPoints;
    }

    public void setDayPoints (Integer dayPoints) {
        this.dayPoints = dayPoints;
    }

    public Integer getTargetWeight () {
        return targetWeight;
    }

    public void setTargetWeight (Integer targetWeight) {
        this.targetWeight = targetWeight;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
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
