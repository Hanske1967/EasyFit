package be.fortemaison.webweights.form;

import be.fortemaison.webweights.model.Consumption;
import be.fortemaison.webweights.model.ConsumptionDetailType;
import be.fortemaison.webweights.util.IConstants;
import be.fortemaison.webweights.util.Utils;
import org.joda.time.DateMidnight;
import org.springframework.format.annotation.NumberFormat;

import java.text.DateFormat;
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

    @NumberFormat(pattern = IConstants.DECIMAL_PATTERN)
    private Double points;

    private List<ConsumptionDetailForm> consumptionDetailForms = new ArrayList<ConsumptionDetailForm>();

    private int typeIndex = 1;

    private String[] sectionTitle;

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
        return "Agenda " + DateFormat.getDateInstance().format(this.date);
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

        return Utils.NUMBER_FORMATTER.format(total);
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


}
