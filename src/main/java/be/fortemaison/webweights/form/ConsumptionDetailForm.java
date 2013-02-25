package be.fortemaison.webweights.form;

import be.fortemaison.webweights.model.ConsumptionDetail;
import be.fortemaison.webweights.model.ConsumptionDetailType;
import be.fortemaison.webweights.util.Utils;

import java.text.MessageFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 7/02/13
 * Time: 23:12
 * To change this template use File | Settings | File Templates.
 */
public class ConsumptionDetailForm {

    private Integer id;

    private Date date;

    private Integer consumptionId;

    private ProductForm product;

    private ConsumptionDetailType type;

    private Double amount;

    /**
     *
     */
    public ConsumptionDetailForm () {

    }

    /**
     * @param detail
     */
    public ConsumptionDetailForm (ConsumptionDetail detail) {
        this.id = detail.getId();
        this.amount = detail.getAmount();
        this.type = ConsumptionDetailType.get(detail.getType());
        this.product = new ProductForm(detail.getProduct());
    }

    public Date getDate () {
        return date;
    }

    public void setDate (Date date) {
        this.date = date;
    }

    public ConsumptionDetailType getType () {
        return type;
    }

    public void setType (ConsumptionDetailType type) {
        this.type = type;
    }

    public Integer getConsumptionId () {
        return consumptionId;
    }

    public void setConsumptionId (Integer consumptionId) {
        this.consumptionId = consumptionId;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public ProductForm getProduct () {
        return product;
    }

    public void setProduct (ProductForm product) {
        this.product = product;
    }

    public Double getAmount () {
        return amount;
    }

    public void setAmount (Double amount) {
        this.amount = amount;
    }

    public double getPoints () {
        double result = this.amount == null
                || this.product.getAmount() == null
                || this.getProduct().getPoints() == null ? 0.0 : this.amount * this.product.getPoints() / this.product.getAmount();
        return result;
    }

    public String getPointsLabel () {
        return Utils.NUMBER_FORMATTER.format(getPoints());
    }

    public String getProductName () {
        return this.product == null ? "" : this.product.getName();
    }

    public String getProductUnit () {
        return this.product == null ? "" : this.product.getUnit();
    }

    public String getLabel () {
        return MessageFormat.format("{0,number,#.#} {1} {2} ", new Object[]{amount, getProductUnit(), getProductName()});
    }


}
