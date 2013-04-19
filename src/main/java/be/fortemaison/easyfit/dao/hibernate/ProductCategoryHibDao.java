package be.fortemaison.easyfit.dao.hibernate;

import be.fortemaison.easyfit.dao.IProductCategoryDAO;
import be.fortemaison.easyfit.model.ProductCategory;
import be.fortemaison.easyfit.util.ContextThreadLocal;
import be.fortemaison.easyfit.util.Utils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 7/03/13
 * Time: 21:25
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ProductCategoryHibDao implements IProductCategoryDAO {

    @Autowired
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
        String qName = Utils.PROCENT + name + Utils.PROCENT;
        List<ProductCategory> categories = session.createQuery("from ProductCategory pc where (pc.shared = :shared or pc.technicalSegment.creationUser = :username) and pc.name like :name order by pc.name").setString("name", qName)
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .list();
        return categories;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductCategory> findAll () {
        Session session = sessionFactory.getCurrentSession();
        List<ProductCategory> categories = session.createQuery("from ProductCategory pc where (pc.shared = :shared or pc.technicalSegment.creationUser = :username)order by pc.name")
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .list();
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
        }
        ;

        Session session = sessionFactory.getCurrentSession();
        session.update(category);
    }

    @Override
    @Transactional
    public void delete (ProductCategory category) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(category);
    }

}
