package be.fortemaison.easyfit.form;

import be.fortemaison.easyfit.model.RecipeDetail;
import be.fortemaison.easyfit.util.Utils;

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

    private Double totalPoints;

    /**
     *
     */
    public RecipeDetailForm () {

    }

    /**
     * @param detail
     */
    public RecipeDetailForm (RecipeDetail detail) {
        this.id = detail.getId();
        this.amount = detail.getAmount();
        this.product = new ProductForm(detail.getProduct());
        this.totalPoints = detail.getPoints();
    }

    public Double getTotalPoints () {
        return totalPoints;
    }

    public void setTotalPoints (Double totalPoints) {
        this.totalPoints = totalPoints;
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

    public String getPointsLabel () {
        return this.totalPoints == null ? "" : Utils.NUMBER_FORMATTER.format(this.totalPoints);
    }


}
