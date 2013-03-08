package be.fortemaison.webweights.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 13/02/13
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
public class Consumption {

    private Integer id;

    private User user;

    private Date date;

    private Double points;

    private Set<ConsumptionDetail> consumptionDetails = new HashSet<ConsumptionDetail>();

    /**
     *
     */
    public Consumption () {
        //
    }

    /**
     * @param date
     */
    public Consumption (Date date) {
        this.date = date;
    }

    public Set<ConsumptionDetail> getConsumptionDetails () {
        return consumptionDetails;
    }

    public void setConsumptionDetails (Set<ConsumptionDetail> aConsumptionDetails) {
        this.consumptionDetails = aConsumptionDetails;
    }

    public void addConsumptionDetail (ConsumptionDetail detail) {
        this.consumptionDetails.add(detail);
        detail.setConsumption(this);
    }

    public void removeConsumptionDetail (ConsumptionDetail detail) {
        this.consumptionDetails.remove(detail);
        detail.setConsumption(null);
    }

    public void clearConsumptionDetails () {
        this.consumptionDetails.clear();
        for (ConsumptionDetail detail : this.consumptionDetails) {
            detail.setConsumption(null);
        }
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
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

    public Double getPoints () {
        if (!this.consumptionDetails.isEmpty()) {
            this.points = 0.0;
            for (ConsumptionDetail detail : this.consumptionDetails) {
                Double morePoints = detail.getPoints();
                this.points += morePoints == null ? 0.0 : morePoints;
            }
        }

        return this.points;
    }

    public void setPoints (Double points) {
        this.points = points;
    }
}
