package be.fortemaison.webweights.dao.hibernate;

import be.fortemaison.webweights.dao.IProductCategoryDAO;
import be.fortemaison.webweights.model.ProductCategory;
import be.fortemaison.webweights.util.IConstants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 7/03/13
 * Time: 21:25
 * To change this template use File | Settings | File Templates.
 */
public class ProductCategoryHibDao implements IProductCategoryDAO {

    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public ProductCategory findById (Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return (ProductCategory) session.get(ProductCategory.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductCategory> findByName (String name) {
        Session session = sessionFactory.getCurrentSession();
        String qName = IConstants.PROCENT + name + IConstants.PROCENT;
        List<ProductCategory> categories = session.createQuery("from ProductCategory pc where pc.name like :name order by pc.name").setString("name", qName).list();
        return categories;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductCategory> findAll () {
        Session session = sessionFactory.getCurrentSession();
        List<ProductCategory> categories = session.createQuery("from ProductCategory pc order by pc.name").list();
        return categories;
    }

    @Override
    @Transactional
    public void insert (ProductCategory category) {
        Session session = sessionFactory.getCurrentSession();
        session.save(category);
    }

    @Override
    @Transactional
    public void update (ProductCategory category) {
        if (category.getId() == null) {
            insert(category);
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.update(category);
        }
    }

    @Override
    @Transactional
    public void delete (ProductCategory category) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(category);
    }

    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
