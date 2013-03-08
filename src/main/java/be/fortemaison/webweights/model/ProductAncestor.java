package be.fortemaison.webweights.model;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public class ProductAncestor {

    protected Integer id;

    protected String name;

    protected Unit unit;

    protected Double amount;

    protected Double points;

    protected Integer maxPoints;

    protected String description;

    protected Boolean favorite = false;

    protected ProductCategory category;

    public Integer getMaxPoints () {
        return maxPoints;
    }

    public void setMaxPoints (Integer maxPoints) {
        this.maxPoints = maxPoints;
    }

    public ProductCategory getCategory () {
        return category;
    }

    public void setCategory (ProductCategory category) {
        this.category = category;
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

    public Double getPoints () {
        return points;
    }

    public void setPoints (Double points) {
        this.points = points;
    }

}
