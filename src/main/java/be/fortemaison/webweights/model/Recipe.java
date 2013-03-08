package be.fortemaison.webweights.model;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 28/01/13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class Recipe extends ProductAncestor {

    private Set<RecipeDetail> recipeDetails;

    /**
     *
     */
    public Recipe () {
        //
    }

    public Recipe (String name) {
        this.name = name;
    }

    public Recipe (String name, boolean favorite) {
        this.name = name;
        this.favorite = favorite;
    }

    public Recipe (Integer id, String name, boolean favorite, Double point, String description) {
        this.id = id;
        this.name = name;
        this.points = point;
        this.favorite = favorite;
        this.description = description;
    }

    public Set<RecipeDetail> getRecipeDetails () {
        return recipeDetails;
    }

    public void setRecipeDetails (Set<RecipeDetail> recipeDetails) {
        this.recipeDetails = recipeDetails;
    }

    public void addRecipeDetail (RecipeDetail detail) {
        this.recipeDetails.add(detail);
    }

    public void removeRecipeDetail (RecipeDetail detail) {
        this.recipeDetails.remove(detail);
    }

    public void updatePoints () {
        if (!this.recipeDetails.isEmpty()) {
            this.points = 0.0;
            for (RecipeDetail detail : this.recipeDetails) {
                Double morePoints = detail.getPoints();
                this.points += morePoints == null ? 0.0 : morePoints;
            }
        }
    }

    public Double getPoints () {
        return this.points;
    }

}
