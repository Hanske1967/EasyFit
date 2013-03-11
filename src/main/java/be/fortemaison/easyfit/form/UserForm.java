package be.fortemaison.easyfit.form;

import be.fortemaison.easyfit.model.User;
import be.fortemaison.easyfit.util.Utils;

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


    private String firstName;

    private String lastName;

    private Integer targetWeight;

    private Integer dayPoints;

    private Integer extraPoints;


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
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.targetWeight = user.getTargetWeight();
        this.dayPoints = user.getDayPoints();
        this.extraPoints = user.getExtraPoints();
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

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public Integer getTargetWeight () {
        return targetWeight;
    }

    public void setTargetWeight (Integer targetWeight) {
        this.targetWeight = targetWeight;
    }

    public Integer getDayPoints () {
        return dayPoints;
    }

    public void setDayPoints (Integer dayPoints) {
        this.dayPoints = dayPoints;
    }

    public Integer getExtraPoints () {
        return extraPoints;
    }

    public void setExtraPoints (Integer extraPoints) {
        this.extraPoints = extraPoints;
    }

    /**
     * @return
     */
    public User getUser () {
        User user = new User(userName);
        user.setId(id);
        user.setUpdateDate(updateDate);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDayPoints(dayPoints);
        user.setExtraPoints(extraPoints);
        ;
        user.setTargetWeight(targetWeight);
        return user;
    }
}
