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
        this.points = recipe.getPoints();
        this.description = recipe.getDescription();
        this.unitId = recipe.getUnit().getId();
        this.unitLabel = recipe.getUnit().getName();
        this.amount = recipe.getAmount();
        this.categoryId = recipe.getCategory() == null ? null : recipe.getCategory().getId();
        this.categoryLabel = recipe.getCategory() == null ? "" : recipe.getCategory().getName();
    }

    @Override
    public Double getPoints () {
        return points;
    }

    @Override
    public String getAmountLabel () {
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
    }

    public void addProduct (RecipeDetailForm product) {
        this.recipeDetailForms.add(product);
    }

    public void removeProduct (RecipeDetailForm product) {
        this.recipeDetailForms.remove(product);
    }

    public void clearProducts () {
        this.recipeDetailForms.clear();
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
