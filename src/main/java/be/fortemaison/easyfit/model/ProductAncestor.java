package be.fortemaison.easyfit.model;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public class ProductAncestor extends CommonAncestor {

    protected String name;

    protected Unit unit;

    protected Double amount;

    protected Double points;

    protected Integer maxPoints;

    protected String description;

    protected ProductCategory category;

    protected Boolean shared = Boolean.TRUE;

    protected FavoriteProduct favoriteProduct;

    public FavoriteProduct getFavoriteProduct () {
        return favoriteProduct;
    }

    public void setFavoriteProduct (FavoriteProduct favoriteProduct) {
        this.favoriteProduct = favoriteProduct;
    }

    public Boolean isShared () {
        return shared;
    }

    public Boolean getShared () {
        return shared;
    }

    public void setShared (Boolean shared) {
        this.shared = shared;
    }

    public void setShared (boolean shared) {
        this.shared = shared;
    }

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
