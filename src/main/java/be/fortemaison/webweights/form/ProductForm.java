package be.fortemaison.webweights.form;

import be.fortemaison.webweights.model.Product;
import be.fortemaison.webweights.model.Unit;
import be.fortemaison.webweights.util.Utils;
import org.springframework.format.annotation.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class ProductForm {

    private Integer id;

    private String name;

    private String unit;

    @NumberFormat(pattern = "###.#")
    private Double amount;

    @NumberFormat(pattern = "###.#")
    private Double points;

    private String description;

    private Boolean favorite = Boolean.FALSE;

    /**
     *
     */
    public ProductForm () {
        //
    }

    /**
     * @param name
     * @param unit
     * @param amount
     * @param point
     * @param description
     * @param favorite
     */
    public ProductForm (Integer id, String name, String unit, Double amount, Double point, String description, Boolean favorite) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.amount = amount;
        this.points = point;
        this.description = description;
        this.favorite = favorite;
    }

    public ProductForm (final Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.unit = product.getUnit().getName();
        this.amount = product.getAmount();
        this.points = product.getPoint();
        this.description = product.getDescription();
        this.favorite = product.isFavorite();
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getUnit () {
        return unit;
    }

    public void setUnit (String unit) {
        this.unit = unit;
    }

    public Double getAmount () {
        return amount;
    }

    public void setAmount (Double amount) {
        this.amount = amount;
    }

    public String getAmountLabel(){
        return Utils.NUMBER_FORMATTER.format(this.amount);
    }

    public Double getPoints () {
        return points;
    }

    public void setPoints (Double point) {
        this.points = point;
    }

    public String getPointsLabel(){
        return Utils.NUMBER_FORMATTER.format(this.points);
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public Boolean getFavorite () {
        return favorite;
    }

    public void setFavorite (Boolean favorite) {
        this.favorite = favorite;
    }

    public Product getProduct() {
        return new Product(id, name, new Unit(unit), amount, points, favorite, description);
    }

}
