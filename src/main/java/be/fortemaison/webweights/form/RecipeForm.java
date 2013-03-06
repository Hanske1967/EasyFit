package be.fortemaison.webweights.form;

import be.fortemaison.webweights.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 3/02/13
 * Time: 23:01
 * To change this template use File | Settings | File Templates.
 */
public class RecipeForm extends ProductForm {

    private List<RecipeDetailForm> recipeDetailForms = new ArrayList<RecipeDetailForm>();


    /**
     *
     */
    public RecipeForm () {
        //
    }

    /**
     *
     */
    public RecipeForm (Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.favorite = recipe.isFavorite();
        this.points = recipe.getPoint();
        this.description = recipe.getDescription();
        this.unitId = recipe.getUnit().getId();
        this.unitLabel = recipe.getUnit().getName();
        this.amount = recipe.getAmount();
    }

    @Override
    public Double getPoints () {
        updatePoints();
        return points;
    }

    @Override
    public String getAmountLabel () {
        updatePoints();
        return super.getAmountLabel();
    }

    public void setFavorite (boolean favorite) {
        this.favorite = favorite;
    }

    public List<RecipeDetailForm> getRecipeDetailForms () {
        return recipeDetailForms;
    }

    public void setRecipeDetailForms (List<RecipeDetailForm> recipeDetailForms) {
        this.recipeDetailForms = recipeDetailForms;
        this.updatePoints();
    }

    public void addProduct (RecipeDetailForm product) {
        this.recipeDetailForms.add(product);
        this.updatePoints();
    }

    public void removeProduct (RecipeDetailForm product) {
        this.recipeDetailForms.remove(product);
        this.updatePoints();
    }

    public void clearProducts () {
        this.recipeDetailForms.clear();
        this.updatePoints();
    }

    private void updatePoints () {
        this.points = 0.0;
        if (!this.recipeDetailForms.isEmpty()) {
            for (RecipeDetailForm detail : this.recipeDetailForms) {
                Double morePoints = detail.getPoints();
                this.points += morePoints == null ? 0.0 : morePoints;
            }
        }
    }

    /**
     * Get recipe from this form, WITHOUT product.
     *
     * @return recipe from this form, WITHOUT product.
     */
    public Recipe getRecipe () {
        Recipe recipe = new Recipe(id, name, favorite, points, description);
        return recipe;
    }
}
