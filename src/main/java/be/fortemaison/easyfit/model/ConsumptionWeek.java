package be.fortemaison.easyfit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Week of consumptions
 * <strong>NOT PERSISTED</strong>
 * User: hansk_000
 * Date: 16/03/13
 * Time: 22:28
 */
public class ConsumptionWeek {

    private Date currentDate;

    //  consumption for selected day
    private Consumption currentConsumption;

    private Integer dayPoints;

    private Integer extraPoints;

    private Double dayPointsLeft;

    private Double extraPointsLeft;

    private Double excercisePoints;

    private Double excercisePointsLeft;

    private List<Consumption> consumptions = new ArrayList<Consumption>(8);

    /**
     * @param date
     */
    public ConsumptionWeek (Date date) {
        this.currentDate = date;
    }

    public Double getExcercisePointsLeft () {
        return excercisePointsLeft;
    }

    public void setExcercisePointsLeft (Double excercisePointsLeft) {
        this.excercisePointsLeft = excercisePointsLeft;
    }

    public Double getExcercisePoints () {
        return excercisePoints;
    }

    public void setExcercisePoints (Double excercisePoints) {
        this.excercisePoints = excercisePoints;
    }

    public Date getCurrentDate () {
        return currentDate;
    }

    public void setCurrentDate (Date currentDate) {
        this.currentDate = currentDate;
    }

    public Consumption getCurrentConsumption () {
        return currentConsumption;
    }

    public void setCurrentConsumption (Consumption currentConsumption) {
        this.currentConsumption = currentConsumption;
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

    public Double getDayPointsLeft () {
        return dayPointsLeft;
    }

    public void setDayPointsLeft (Double dayPointsLeft) {
        this.dayPointsLeft = dayPointsLeft;
    }

    public Double getExtraPointsLeft () {
        return extraPointsLeft;
    }

    public void setExtraPointsLeft (Double extraPointsLeft) {
        this.extraPointsLeft = extraPointsLeft;
    }

    public List<Consumption> getConsumptions () {
        return consumptions;
    }

    public void setConsumptions (List<Consumption> consumptions) {
        this.consumptions = consumptions;
    }
}
