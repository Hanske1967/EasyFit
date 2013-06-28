package be.fortemaison.easyfit.form;

import be.fortemaison.easyfit.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class UserForm {

    public String password;

    public String oldPassword;

    private Integer id;

    private String userName;

    private String firstName;

    private String lastName;

    private Integer targetWeight;

    private Integer dayPoints;

    private Integer extraPoints;

    private String language;

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
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.targetWeight = user.getTargetWeight();
        this.dayPoints = user.getDayPoints();
        this.extraPoints = user.getExtraPoints();
        this.language = user.getLanguage();
    }

    public String getLanguage () {
        return language;
    }

    public void setLanguage (String language) {
        this.language = language;
    }

    public String getOldPassword () {
        return oldPassword;
    }

    public void setOldPassword (String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
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
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDayPoints(dayPoints);
        user.setExtraPoints(extraPoints);
        user.setTargetWeight(targetWeight);
        user.setLanguage(language);
        return user;
    }
}
