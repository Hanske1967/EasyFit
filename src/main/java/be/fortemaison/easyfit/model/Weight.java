package be.fortemaison.easyfit.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 7/04/13
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
public class Weight extends CommonAncestor {

    private User user;

    private Date date;

    private Double weight;

    /**
     *
     */
    public Weight () {
        //
    }

    public Weight (Integer id, Date date, Double weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public Date getDate () {
        return date;
    }

    public void setDate (Date date) {
        this.date = date;
    }

    public Double getWeight () {
        return weight;
    }

    public void setWeight (Double weight) {
        this.weight = weight;
    }
}
