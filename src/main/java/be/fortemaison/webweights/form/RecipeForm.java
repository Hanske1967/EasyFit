package be.fortemaison.webweights.form;

import be.fortemaison.webweights.model.Recipe;
import be.fortemaison.webweights.util.Utils;
import org.springframework.format.annotation.NumberFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 3/02/13
 * Time: 23:01
 * To change this template use File | Settings | File Templates.
 */
public class RecipeForm {

    private Integer id;

    private String name;

    private boolean favorite;

    @NumberFormat(pattern = "###.#")
    private Double points;

    private List<RecipeDetailForm> recipeDetailForms = new ArrayList<RecipeDetailForm>();

    private String description;

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
    }

    /**
     * @param id
     * @param name
     * @param favorite
     * @param description
     */
    public RecipeForm (Integer id, String name, boolean favorite, Double point, String description) {
        this.id = id;
        this.name = name;
        this.favorite = favorite;
        this.points = point;
        this.description = description;
    }

    public Double getPoints () {
        updatePoints();
        return points;
    }

    public void setPoints (Double points) {
        this.points = points;
    }

    public String getPointsLabel () {
        return Utils.NUMBER_FORMATTER.format(getPoints());
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

    public boolean isFavorite () {
        return favorite;
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
        if (!this.recipeDetailForms.isEmpty()) {
            this.points = 0.0;
            for (RecipeDetailForm detail : this.recipeDetailForms) {
                Double morePoints = detail.getPoints();
                this.points += morePoints == null ? 0.0 : morePoints;
            }
        }
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
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
