package be.fortemaison.webweights.model;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 28/01/13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class Recipe {

    private Integer id;

    private String name;

    private boolean favorite;

    private Double point;

    private Set<RecipeDetail> recipeDetails;

    private String description;

    public Recipe () {
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
        this.point = point;
        this.favorite = favorite;
        this.description = description;
    }

    public Double getPoint () {
        return point;
    }

    public void setPoint (Double point) {
        this.point = point;
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

    public boolean isFavorite () {
        return favorite;
    }

    public void setFavorite (boolean favorite) {
        this.favorite = favorite;
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
            this.point = 0.0;
            for (RecipeDetail detail : this.recipeDetails) {
                Double morePoints = detail.getPoints();
                this.point += morePoints == null ? 0.0 : morePoints;
            }
        }
    }
}
