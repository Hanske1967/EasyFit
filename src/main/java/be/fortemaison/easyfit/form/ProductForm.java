package be.fortemaison.easyfit.form;

import be.fortemaison.easyfit.model.Product;
import be.fortemaison.easyfit.model.ProductAncestor;
import be.fortemaison.easyfit.model.ProductCategory;
import be.fortemaison.easyfit.model.Unit;
import be.fortemaison.easyfit.util.Utils;
import org.springframework.format.annotation.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class ProductForm {

    protected Integer id;

    protected String name;

    protected Integer unitId;

    protected String unitLabel;

    @NumberFormat(pattern = "###.#")
    protected Double amount;

    @NumberFormat(pattern = "###.#")
    protected Double points;

    protected Integer maxPoints;

    protected String description;

    protected Boolean shared = Boolean.TRUE;

    protected Integer categoryId;

    protected String categoryLabel;


    /**
     *
     */
    public ProductForm () {
        //
    }

    public ProductForm (final ProductAncestor product) {
        this.id = product.getId();
        this.name = product.getName();
        this.unitId = product.getUnit().getId();
        this.unitLabel = product.getUnit().getName();
        this.amount = product.getAmount();
        this.points = product.getPoints();
        this.maxPoints = product.getMaxPoints();
        this.description = product.getDescription();
        this.shared = product.isShared();
        this.categoryId = product.getCategory() == null ? null : product.getCategory().getId();
        this.categoryLabel = product.getCategory() == null ? "" : product.getCategory().getName();
    }

    public Integer getMaxPoints () {
        return maxPoints;
    }

    public void setMaxPoints (Integer maxPoints) {
        this.maxPoints = maxPoints;
    }

    public String getCategoryLabel () {
        return categoryLabel;
    }

    public void setCategoryLabel (String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public Integer getCategoryId () {
        return categoryId;
    }

    public void setCategoryId (Integer categoryId) {
        this.categoryId = categoryId;
    }


    public String getUnitLabel () {
        return unitLabel;
    }

    public void setUnitLabel (String unitLabel) {
        this.unitLabel = unitLabel;
    }

    public Integer getUnitId () {
        return unitId;
    }

    public void setUnitId (Integer unitId) {
        this.unitId = unitId;
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

    public Double getAmount () {
        return amount;
    }

    public void setAmount (Double amount) {
        this.amount = amount;
    }

    public String getAmountLabel () {
        return this.amount == null ? "" : Utils.NUMBER_FORMATTER.format(this.amount);
    }

    public Double getPoints () {
        return points;
    }

    public void setPoints (Double point) {
        this.points = point;
    }

    public String getPointsLabel () {
        return this.points == null ? "" : Utils.NUMBER_FORMATTER.format(this.points);
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public boolean getShared () {
        return shared;
    }

    public Boolean getFavorite () {
        return shared;
    }

    public void setShared (Boolean shared) {
        this.shared = shared;
    }

    /**
     * @return
     */
    public ProductAncestor getProduct () {
        Unit unit = new Unit(unitId, unitLabel, null);

        ProductCategory category = new ProductCategory(categoryLabel);
        category.setId(categoryId);

        Product product = new Product(name, unit, amount, points, shared, description);
        product.setId(id);
        product.setCategory(category);
        product.setMaxPoints(maxPoints);

        return product;
    }

}
