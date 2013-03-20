package be.fortemaison.easyfit.dao;

import be.fortemaison.easyfit.model.Product;
import be.fortemaison.easyfit.model.ProductCategory;

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

    public List<Product> findByCategory (ProductCategory category);

    public List<Product> findByNameAndCategory (String name, ProductCategory category);

    public List<Product> findAll ();

    public void insert (Product product);

    public void update (Product product);

    public void delete (Product product);


}