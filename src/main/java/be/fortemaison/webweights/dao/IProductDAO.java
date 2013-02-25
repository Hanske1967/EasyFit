package be.fortemaison.webweights.dao;

import be.fortemaison.webweights.model.Product;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public interface IProductDAO {

    public Product findById (Integer id);

    public List<Product> findByName (String name);

    public List<Product> findFavorites ();

    public List<Product> findAll ();

    public void insert (Product product);

    public void update (Product product);

    public void delete (Product product);

}
