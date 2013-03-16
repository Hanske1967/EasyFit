package be.fortemaison.easyfit.dao.hibernate;

import be.fortemaison.easyfit.dao.IProductDAO;
import be.fortemaison.easyfit.model.Product;
import be.fortemaison.easyfit.model.ProductCategory;
import be.fortemaison.easyfit.util.IConstants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
        if (StringUtils.isEmpty(name)) {
            return this.findAll();
        }

        String param = name;
        if (!name.contains(IConstants.PROCENT)) {
            StringBuilder sb = new StringBuilder(name.length() + 2);
            sb.append(IConstants.PROCENT);
            sb.append(name);
            sb.append(IConstants.PROCENT);
            param = sb.toString();
        }

        Session session = sessionFactory.getCurrentSession();
        List<Product> result = (List<Product>) session.createQuery("from Product where name like :name order by name").setString("name", param).list();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByCategory (ProductCategory category) {
        if (category == null) {
            return this.findAll();
        }

        Session session = sessionFactory.getCurrentSession();
        List<Product> result = (List<Product>) session.createQuery("from Product where category = :cat order by name").setEntity("cat", category).list();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByNameAndCategory (String name, ProductCategory category) {
        if (StringUtils.isEmpty(name) && (category == null)) {
            return this.findAll();
        }

        if (category == null) {
            return this.findByName(name);
        }

        if (StringUtils.isEmpty(name)) {
            return this.findByCategory(category);
        }

        Session session = sessionFactory.getCurrentSession();
        List<Product> result = (List<Product>) session.createQuery("from Product where name like :name and category = :cat order by name")
                .setString("name", name)
                .setEntity("cat", category)
                .list();
        return result;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll () {
        Session session = sessionFactory.getCurrentSession();
        List<Product> result = (List<Product>) session.createQuery("from Product order by name").list();
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








