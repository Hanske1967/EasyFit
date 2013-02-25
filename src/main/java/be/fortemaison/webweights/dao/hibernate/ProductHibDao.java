package be.fortemaison.webweights.dao.hibernate;

import be.fortemaison.webweights.dao.IProductDAO;
import be.fortemaison.webweights.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ProductHibDao implements IProductDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public Product findById (Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Product result = (Product) session.get(Product.class, id);
        return result;
    }

    @Transactional(readOnly = true)
    public List<Product> findByName (String name) {
        Session session = sessionFactory.getCurrentSession();
        List<Product> result = (List<Product>) session.createQuery("from Product where name like ? order by favorite desc, name").setString(0, name).list();
        return result;
    }

    @Transactional(readOnly = true)
    public List<Product> findFavorites () {
        Session session = sessionFactory.getCurrentSession();
        List<Product> result = (List<Product>) session.createQuery("from Product where favorite = ? order by favorite desc, name").setBoolean(0, Boolean.TRUE).list();
        return result;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll () {
        Session session = sessionFactory.getCurrentSession();
        List<Product> result = (List<Product>) session.createQuery("from Product order by favorite desc, name").list();
        return result;
    }

    @Transactional
    public void insert (Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

    @Transactional
    public void update (Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
    }

    @Transactional
    public void delete (Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(product);
    }

}
