package be.fortemaison.webweights.service;

import be.fortemaison.webweights.dao.IProductDAO;
import be.fortemaison.webweights.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 26/01/13
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ProductServiceImpl implements IProductService {

    private IProductDAO productDAO;

    public IProductDAO getProductDAO () {
        return productDAO;
    }

    public void setProductDAO (IProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Transactional(readOnly = true)
    public Product findById (Integer id) {
        return this.productDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> findByName (String name) {
        return this.productDAO.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Product> findFavorites () {
        return this.productDAO.findFavorites();
    }

    @Transactional(readOnly = true)
    public List<Product> findAll () {
        return this.productDAO.findAll();
    }

    @Transactional
    public void insert (Product product) {
        this.productDAO.insert(product);
    }


    @Transactional
    public void update (Product product) {
        if (product.getId() == null) {
            this.productDAO.insert(product);
        } else {
            this.productDAO.update(product);
        }
    }

    @Transactional
    public void delete (Product product) {
        this.productDAO.delete(product);
    }

    @Transactional
    public void updatePavorite (Integer id, boolean favorite){
        Product product = findById(id);
        product.setFavorite(favorite);
        update(product);
    }

}
