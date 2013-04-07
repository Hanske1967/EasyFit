package be.fortemaison.easyfit.form;

import be.fortemaison.easyfit.model.Weight;
import be.fortemaison.easyfit.util.Utils;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 7/04/13
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public class WeightForm {

    private Integer id;

    private Double weight;

    private Date date;

    private String dateStr;

    /**
     *
     */

    public WeightForm () {
        //
    }

    public WeightForm (Weight weight) {
        this.id = weight.getId();
        this.date = weight.getDate();
        this.weight = weight.getWeight();

        this.dateStr = getDateLabel();
    }

    public String getDateStr () {
        return dateStr;
    }

    public void setDateStr (String dateStr) {
        this.dateStr = dateStr;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Double getWeight () {
        return weight;
    }

    public void setWeight (Double weight) {
        this.weight = weight;
    }

    public Date getDate () {
        return date;
    }

    public void setDate (Date date) {
        this.date = date;
    }

    /**
     * @return
     */
    public String getWeightLabel () {
        return this.weight == null ? "" : Utils.NUMBER_FORMATTER.format(this.weight);
    }

    /**
     * @return
     */
    public String getDateLabel () {
        return this.date == null ? "" : Utils.SHORT_DATE_FORMATTER.format(this.date);
    }

    /**
     * @return
     */
    public Weight toWeight () {
        return new Weight(id, date, weight);
    }

}
