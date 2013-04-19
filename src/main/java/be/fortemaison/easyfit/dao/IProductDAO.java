package be.fortemaison.easyfit.dao;

import be.fortemaison.easyfit.model.Page;
import be.fortemaison.easyfit.model.Product;
import be.fortemaison.easyfit.model.ProductCategory;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public interface IProductDAO {

    public Product findById (Integer id);

    public Page<Product> findByName (String name, Integer currentPage);

    public Page<Product> findByCategory (ProductCategory category, Integer currentPage);

    public Page<Product> findByNameAndCategory (String name, ProductCategory category, Integer currentPage);

    public Page<Product> findAll (Integer currentPage);

    public void insert (Product product);

    public void update (Product product);

    public void delete (Product product);

}
