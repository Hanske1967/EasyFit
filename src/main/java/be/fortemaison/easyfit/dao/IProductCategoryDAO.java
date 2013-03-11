package be.fortemaison.easyfit.dao;

import be.fortemaison.easyfit.model.ProductCategory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public interface IProductCategoryDAO {

    public ProductCategory findById (Integer id);

    public List<ProductCategory> findByName (String name);

    public List<ProductCategory> findAll ();

    public void insert (ProductCategory category);

    public void update (ProductCategory category);

    public void delete (ProductCategory category);

}
