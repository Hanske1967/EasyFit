package be.fortemaison.easyfit.form;

import be.fortemaison.easyfit.model.Consumption;
import be.fortemaison.easyfit.model.ConsumptionDetailType;
import be.fortemaison.easyfit.util.Utils;
import org.joda.time.DateMidnight;
import org.springframework.format.annotation.NumberFormat;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 3/02/13
 * Time: 23:01
 * To change this template use File | Settings | File Templates.
 */
public class ConsumptionForm {

    private Integer id;

    private ConsumptionDetailType type;

    private Date date;

    @NumberFormat(pattern = Utils.DECIMAL_PATTERN)
    private Double points;

    private List<ConsumptionDetailForm> consumptionDetailForms = new ArrayList<ConsumptionDetailForm>();

    private int typeIndex = 1;

    private String[] sectionTitle;

    private Integer dayPoints;

    private Integer extraPoints;

    private Double dayPointsLeft;

    private Double extraPointsLeft;

    private Double excercisePoints;

    private Double excercisePointsLeft;

    /**
     *
     */
    public ConsumptionForm (Consumption consumption) {
        this.id = consumption.getId();
        this.date = consumption.getDate();
        this.points = consumption.getPoints();
    }

    /**
     * @param date
     */
    public ConsumptionForm (Date date) {
        this.date = date;
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

    public int getTypeIndex () {
        return typeIndex;
    }

    public void setTypeIndex (int typeIndex) {
        this.typeIndex = typeIndex;
    }

    public void resetTypeIndex () {
        this.typeIndex = 1;
    }

    public void incrementTypeIndex () {
        this.typeIndex++;
    }

    public String getPointsLabel () {
        Double aPoints = getPoints();
        String result = Utils.NUMBER_FORMATTER.format(0.0);
        if (points != null) {
            result = Utils.NUMBER_FORMATTER.format(getPoints());
        }
        return result;
    }

    public Double getPoints () {
        return points;
    }

    public void setPoints (Double points) {
        this.points = points;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Date getDate () {
        return date;
    }

    public void setDate (Date date) {
        this.date = date;
    }

    public List<ConsumptionDetailForm> getConsumptionDetailForms () {
        return consumptionDetailForms;
    }

    public void setConsumptionDetailForms (List<ConsumptionDetailForm> consumptionDetailForms) {
        this.consumptionDetailForms = consumptionDetailForms;
    }

    public void addConsumptionDetailForm (ConsumptionDetailForm product) {
        this.consumptionDetailForms.add(product);
    }

    public void removeConsumptionDetailForm (ConsumptionDetailForm product) {
        this.consumptionDetailForms.remove(product);
    }

    public void clearConsumptionDetailForm () {
        this.consumptionDetailForms.clear();
    }

    /**
     * Get consumption from this form, WITHOUT product.
     *
     * @return consumption from this form, WITHOUT product.
     */
    public Consumption getConsumption () {
        Consumption consumption = new Consumption(date);
        consumption.setId(id);
        consumption.setPoints(points);
        return consumption;
    }

    /**
     * @return
     */
    public String getTitle () {
        return Utils.DATE_FORMATTER.format(this.date);
    }

    /**
     * @param type
     */
    public Set<ConsumptionDetailForm> getConsumptionDetails (ConsumptionDetailType type) {
        Set<ConsumptionDetailForm> result = new HashSet<ConsumptionDetailForm>();
        for (ConsumptionDetailForm detail : this.consumptionDetailForms) {
            if (detail.getType().equals(type)) {
                result.add(detail);
            }
        }

        return result;
    }

    /**
     *
     */
    public Set<ConsumptionDetailForm> getConsumptionDetailsForIndex () {
        return getConsumptionDetails(ConsumptionDetailType.get(this.typeIndex));
    }

    /**
     *
     */
    public String getConsumptionDetailTitleForIndex () {
        return this.sectionTitle[this.typeIndex - 1];
    }

    /**
     *
     */
    public String getConsumptionDetailsPointsForIndex () {
        double total = 0.0;
        Set<ConsumptionDetailForm> aDetails = getConsumptionDetailsForIndex();
        for (ConsumptionDetailForm detail : aDetails) {
            total += detail.getPoints();
        }

        String result = Utils.NUMBER_FORMATTER.format(total);
        return result;
    }

    /**
     * @param sectionTitles
     */
    public void setConsumptionDetailHeaders (String[] sectionTitles) {
        this.sectionTitle = sectionTitles;
    }

    /**
     * @return
     */
    public String getPreviousDate () {
        DateMidnight aDate = new DateMidnight(this.date.getTime());
        String result = Utils.DATE_FORMATTER.format(aDate.minusDays(1).toDate());
        return result;
    }

    public String getNextDate () {
        DateMidnight aDate = new DateMidnight(this.date.getTime());
        String result = Utils.DATE_FORMATTER.format(aDate.plusDays(1).toDate());
        return result;
    }

    public String getCurrentDate () {
        String result = Utils.DATE_FORMATTER.format(this.date);
        return result;
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

    public String getDayPointsLeftLabel () {
        return Utils.NUMBER_FORMATTER.format(dayPointsLeft);
    }

    public Double getExtraPointsLeft () {
        return extraPointsLeft;
    }

    public void setExtraPointsLeft (Double extraPointsLeft) {
        this.extraPointsLeft = extraPointsLeft;
    }

    public String getExtraPointsLeftLabel () {
        return Utils.NUMBER_FORMATTER.format(extraPointsLeft);
    }

    public String getWeekDay () {
        return Utils.WEEKDAY_DATE_FORMATTER.format(this.date);
    }

    /**
     * @return the extra points consummed (+) or points not consummed onthis day (-)
     */
    public double getDeltaPoints () {
        return this.points - this.dayPoints;
    }

    public String getDeltaPointsLabel () {
        return Utils.NUMBER_FORMATTER.format(getDeltaPoints());
    }

    public String getExcercisePointsLabel () {
        return Utils.NUMBER_FORMATTER.format(getExcercisePoints());
    }

    public String getExcercisePointsLeftLabel () {
        return Utils.NUMBER_FORMATTER.format(getExcercisePointsLeft());
    }



}
