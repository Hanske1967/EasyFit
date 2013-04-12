package be.fortemaison.easyfit.dao;

import be.fortemaison.easyfit.model.Page;
import be.fortemaison.easyfit.model.ProductCategory;
import be.fortemaison.easyfit.model.Recipe;

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

    public Page<Recipe> findByName (String name, Integer currentPage);

    public Page<Recipe> findByNameWithDetails (String name, Integer currentPage);

    public Page<Recipe> findByCategory (ProductCategory category, Integer currentPage);

    public Page<Recipe> findByNameAndCategory (String name, ProductCategory category, Integer currentPage);

    public Page<Recipe> findAll (Integer currentPage);

    public Page<Recipe> findAllWithDetails (Integer currentPage);

    public void insert (Recipe recipe);

    public void update (Recipe recipe);

    public void delete (Recipe recipe);

}
