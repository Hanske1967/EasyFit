package be.fortemaison.webweights.dao;

import be.fortemaison.webweights.model.ProductAncestor;

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

    public ProductAncestor findById (Integer key);
}
