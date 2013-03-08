package be.fortemaison.webweights.form;

import be.fortemaison.webweights.model.RecipeDetail;
import be.fortemaison.webweights.util.Utils;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 7/02/13
 * Time: 23:12
 * To change this template use File | Settings | File Templates.
 */
public class RecipeDetailForm {

    private Integer id;

    private Integer recipeId;

    private ProductForm product;

    private Double amount;

    /**
     *
     */
    public RecipeDetailForm () {

    }

    /**
     * @param id
     * @param product
     * @param amount
     */
    public RecipeDetailForm (Integer id, ProductForm product, Double amount) {
        assert (product != null);

        this.id = id;
        this.product = product;
        this.amount = amount;
    }


    /**
     * @param detail
     */
    public RecipeDetailForm (RecipeDetail detail) {
        this.id = detail.getId();
        this.amount = detail.getAmount();
        this.product = new ProductForm(detail.getProduct());
    }

    public Integer getRecipeId () {
        return recipeId;
    }

    public void setRecipeId (Integer recipeId) {
        this.recipeId = recipeId;
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

    public String getAmountLabel () {
        return Utils.NUMBER_FORMATTER.format(this.amount);
    }

    public Double getPoints () {
        return this.product.getPoints();
    }

    public String getPointsLabel () {
        Double points = getPoints();
        return points == null ? "" : Utils.NUMBER_FORMATTER.format(getPoints());
    }


}
