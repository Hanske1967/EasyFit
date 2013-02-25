package be.fortemaison.webweights.model;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public class Product {

    private Integer id;

    private String name;

    private Unit unit;

    private Double amount;

    private Double point;

    private String description;

    private Boolean favorite = false;

    /**
     *
     */
    public Product () {
        //
    }

    /**
     * @param name
     * @param unit
     * @param point
     */
    public Product (String name, Unit unit, Double amount, Double point, Boolean favorite, String description) {
        this.name = name;
        this.unit = unit;
        this.amount = amount;
        this.point = point;
        this.favorite = favorite;
        this.description = description;
    }

    /**
     * @param name
     * @param unit
     * @param point
     */
    public Product (Integer id, String name, Unit unit, Double amount, Double point, Boolean favorite, String description) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.amount = amount;
        this.point = point;
        this.favorite = favorite;
        this.description = description;
    }

    public Boolean isFavorite () {
        return favorite;
    }

    public Boolean getFavorite () {
        return favorite;
    }

    public void setFavorite (Boolean favorite) {
        this.favorite = favorite;
    }

    public Double getAmount () {
        return amount;
    }

    public void setAmount (Double amount) {
        this.amount = amount;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
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

    public Unit getUnit () {
        return unit;
    }

    public void setUnit (Unit unit) {
        this.unit = unit;
    }

    public Double getPoint () {
        return point;
    }

    public void setPoint (Double point) {
        this.point = point;
    }

}
