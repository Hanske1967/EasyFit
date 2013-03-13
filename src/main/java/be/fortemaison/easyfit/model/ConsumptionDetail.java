package be.fortemaison.easyfit.model;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 13/02/13
 * Time: 22:06
 * To change this template use File | Settings | File Templates.
 */
public class ConsumptionDetail extends CommonAncestor {

    private int type;

    private Double amount;

    private Consumption consumption;

    private ProductAncestor product;

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

    public ProductAncestor getProduct () {
        return product;
    }

    public void setProduct (ProductAncestor product) {
        this.product = product;
    }

    public Double getPoints () {
        Double result = null;
        if (this.amount == null
                || this.product.getAmount() == null
                || this.product.getPoints() == null) {
            result = Double.NaN;
        }

        result = this.amount * this.product.getPoints() / this.product.getAmount();

        if (this.product.getMaxPoints() != null && this.product.getMaxPoints() > 0) {
            if (result > this.product.getMaxPoints().doubleValue()) {
                result = this.product.getMaxPoints().doubleValue();
            }
        }

        return result;
    }


}
