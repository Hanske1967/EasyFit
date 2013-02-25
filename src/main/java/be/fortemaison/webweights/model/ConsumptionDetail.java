package be.fortemaison.webweights.model;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 13/02/13
 * Time: 22:06
 * To change this template use File | Settings | File Templates.
 */
public class ConsumptionDetail {

    private Integer id;

    private int type;

    private Double amount;

    private Consumption consumption;

    private Product product;

    /**
     *
     */
    public ConsumptionDetail () {
        //
    }

    /**
     * @param type
     */
    public ConsumptionDetail (int type) {
        this.type = type;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public int getType () {
        return type;
    }

    public void setType (int type) {
        this.type = type;
    }

    public Double getAmount () {
        return amount;
    }

    public void setAmount (Double amount) {
        this.amount = amount;
    }

    public Consumption getConsumption () {
        return consumption;
    }

    public void setConsumption (Consumption consumption) {
        this.consumption = consumption;
    }

    public Product getProduct () {
        return product;
    }

    public void setProduct (Product product) {
        this.product = product;
    }


}
