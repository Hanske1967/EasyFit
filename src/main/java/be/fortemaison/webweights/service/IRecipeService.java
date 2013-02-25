package be.fortemaison.webweights.service;

import be.fortemaison.webweights.model.Recipe;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 3/02/13
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */
public interface IRecipeService {

    public Recipe findById (Integer id);

    public Recipe findByIdWithDetails (Integer id);

    public List<Recipe> findByName (String name);

    public List<Recipe> findByNameWithDetails (String name);

    public List<Recipe> findFavorites ();

    public List<Recipe> findFavoritesWithDetails ();

    public List<Recipe> findAll ();

    public List<Recipe> findAllWithdetails ();

    public void insert (Recipe recipe);

    public void update (Recipe recipe);

    public void delete (Recipe recipe);


}
