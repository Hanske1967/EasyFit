package be.fortemaison.easyfit.dao;

import be.fortemaison.easyfit.model.Recipe;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public interface IRecipeDAO {

    public Recipe findById (Integer id);

    public Recipe findByIdWithDetails (Integer id);

    public List<Recipe> findByName (String name);

    public List<Recipe> findByNameWithDetails (String name);

    public List<Recipe> findAll ();

    public List<Recipe> findAllWithDetails ();

    public void insert (Recipe recipe);

    public void update (Recipe recipe);

    public void delete (Recipe recipe);

}
