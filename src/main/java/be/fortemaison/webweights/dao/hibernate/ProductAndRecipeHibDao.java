package be.fortemaison.webweights.dao.hibernate;

import be.fortemaison.webweights.dao.IProductAndRecipeDAO;
import be.fortemaison.webweights.model.ProductAncestor;
import be.fortemaison.webweights.model.ProductCategory;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 3/03/13
 * Time: 22:35
 * To change this template use File | Settings | File Templates.
 */
public class ProductAndRecipeHibDao implements IProductAndRecipeDAO {

    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<ProductAncestor> findByName (String name) {
        Session session = sessionFactory.getCurrentSession();
        List<ProductAncestor> result = (List<ProductAncestor>) session.createQuery("from ProductAncestor r where r.name like :name order by r.name").setString("name", name).list();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductAncestor> findAll () {
        Session session = sessionFactory.getCurrentSession();
        List<ProductAncestor> result = (List<ProductAncestor>) session.createQuery("from ProductAncestor p order by p.name").list();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductAncestor> findFavorites () {
        Session session = sessionFactory.getCurrentSession();
        List<ProductAncestor> result = (List<ProductAncestor>) session.createQuery("from ProductAncestor p where p.favorite = :favorite order by p.name").setBoolean("favorite", Boolean.TRUE).list();
        return result;
    }


    @Override
    @Transactional(readOnly = true)
    public List<ProductAncestor> findByCategory (ProductCategory category) {
        Session session = sessionFactory.getCurrentSession();
        List<ProductAncestor> result = (List<ProductAncestor>) session.createQuery("from ProductAncestor p where p.category = :category order by p.name").setEntity("category", category).list();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductAncestor> findByNameAndCategory (String name, ProductCategory category) {
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
        List<ProductAncestor> result = (List<ProductAncestor>) session.createQuery("from ProductAncestor p where p.name like :queryName and p.category = :category order by p.name")
                .setString("queryName", name)
                .setEntity("category", category)
                .list();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductAncestor findById (Integer key) {
        Session session = sessionFactory.getCurrentSession();
        ProductAncestor result = (ProductAncestor) session.get(ProductAncestor.class, key);
        return result;
    }

    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
