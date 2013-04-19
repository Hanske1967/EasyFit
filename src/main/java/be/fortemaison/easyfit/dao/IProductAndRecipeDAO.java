package be.fortemaison.easyfit.dao;

import be.fortemaison.easyfit.model.Excercise;
import be.fortemaison.easyfit.model.Page;
import be.fortemaison.easyfit.model.ProductAncestor;
import be.fortemaison.easyfit.model.ProductCategory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 3/03/13
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
public interface IProductAndRecipeDAO {

    public Page<ProductAncestor> findAll (Integer currentPage);

    public Page<ProductAncestor> findByName (String name, Integer currentPage);

    public Page<ProductAncestor> findFavorites (Integer currentPage);

    public Page<ProductAncestor> findByCategory (ProductCategory category, Integer currentPage);

    public Page<ProductAncestor> findByNameAndCategory (String queryName, ProductCategory category, Integer currentPage);

    public ProductAncestor findById (Integer key);


    /**
     * Exercises
     */

    public List<Excercise> findExcercises ();

}

