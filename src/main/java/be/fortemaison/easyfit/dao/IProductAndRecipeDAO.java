package be.fortemaison.easyfit.dao;

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

    public List<ProductAncestor> findAll ();

    public List<ProductAncestor> findByName (String name);

    public List<ProductAncestor> findFavorites ();

    public List<ProductAncestor> findByCategory (ProductCategory category);

    public List<ProductAncestor> findByNameAndCategory (String queryName, ProductCategory category);

    public ProductAncestor findById (Integer key);

}

