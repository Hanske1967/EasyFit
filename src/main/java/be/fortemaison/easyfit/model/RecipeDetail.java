package be.fortemaison.easyfit.model;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 7/02/13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class RecipeDetail {

    private Integer id;

    private Recipe recipe;

    private ProductAncestor product;

    private Double amount;

    /**
     *
     */
    public RecipeDetail () {
    }

    /**
     * @param id
     * @param recipe
     * @param product
     * @param amount
     */
    public RecipeDetail (Integer id, Recipe recipe, Product product, Double amount) {
        this.id = id;
        this.recipe = recipe;
        this.product = product;
        this.amount = amount;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Recipe getRecipe () {
        return recipe;
    }

    public void setRecipe (Recipe recipe) {
        this.recipe = recipe;
    }

    public ProductAncestor getProduct () {
        return product;
    }

    public void setProduct (ProductAncestor product) {
        this.product = product;
    }

    public Double getAmount () {
        return amount;
    }

    public void setAmount (Double amount) {
        this.amount = amount;
    }

    /**
     * @return
     */
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
