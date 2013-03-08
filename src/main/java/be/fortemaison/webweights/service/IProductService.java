package be.fortemaison.webweights.service;

import be.fortemaison.webweights.model.Product;
import be.fortemaison.webweights.model.ProductCategory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 26/01/13
 * Time: 23:27
 * To change this template use File | Settings | File Templates.
 */
public interface IProductService {

    public Product findById (Integer id);

    public List<Product> findByName (String name);

    public List<Product> findByCategory (ProductCategory category);

    public List<Product> findByNameAndCategory (String name, ProductCategory category);

    public List<Product> findFavorites ();

    public List<Product> findAll ();

    public void insert (Product product);

    public void update (Product product);

    public void delete (Product product);

    public void updatePavorite (Integer id, boolean favorite);

}
