package be.fortemaison.easyfit.dao.hibernate;

import be.fortemaison.easyfit.dao.IProductDAO;
import be.fortemaison.easyfit.model.Page;
import be.fortemaison.easyfit.model.Product;
import be.fortemaison.easyfit.model.ProductCategory;
import be.fortemaison.easyfit.util.ContextThreadLocal;
import be.fortemaison.easyfit.util.Utils;
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
    public Page<Product> findByName (String name, Integer currentPage) {
        if (StringUtils.isEmpty(name)) {
            return this.findAll(currentPage);
        }

        String param = name;
        if (!name.contains(Utils.PROCENT)) {
            StringBuilder sb = new StringBuilder(name.length() + 2);
            sb.append(Utils.PROCENT);
            sb.append(name);
            sb.append(Utils.PROCENT);
            param = sb.toString();
        }

        Session session = sessionFactory.getCurrentSession();

        Long count = (Long) session.createQuery("select count(id) from Product where (shared = :shared or technicalSegment.creationUser = :username) and name like :name order by name")
                .setString("name", param)
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .uniqueResult();

        int pageCount = new Double(count / Page.PAGE_SIZE).intValue();

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        } else if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        List<Product> resultList = (List<Product>) session.createQuery("from Product where (shared = :shared or technicalSegment.creationUser = :username) and name like :name order by name")
                .setString("name", param)
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .setFirstResult((currentPage-1) * Page.PAGE_SIZE)
                .setMaxResults(Page.PAGE_SIZE)
                .list();

        Page<Product> resultPage = new Page(resultList);
        resultPage.setCurrentPage(currentPage);
        resultPage.setPageCount(pageCount);

        return resultPage;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findByCategory (ProductCategory category, Integer currentPage) {
        if (category == null) {
            return this.findAll(currentPage);
        }

        Session session = sessionFactory.getCurrentSession();

        Long count = (Long) session.createQuery("select count(id) from Product where (shared = :shared or technicalSegment.creationUser = :username) and category = :cat order by name")
                .setEntity("cat", category)
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .uniqueResult();

        int pageCount = new Double(count / Page.PAGE_SIZE).intValue();

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        } else if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        List<Product> resultList = (List<Product>) session.createQuery("from Product where (shared = :shared or technicalSegment.creationUser = :username) and category = :cat order by name")
                .setEntity("cat", category)
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .setFirstResult((currentPage-1) * Page.PAGE_SIZE)
                .setMaxResults(Page.PAGE_SIZE)
                .list();

        Page<Product> resultPage = new Page(resultList);
        resultPage.setCurrentPage(currentPage);
        resultPage.setPageCount(pageCount);

        return resultPage;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findByNameAndCategory (String name, ProductCategory category, Integer currentPage) {
        if (StringUtils.isEmpty(name) && (category == null)) {
            return this.findAll(currentPage);
        }

        if (category == null) {
            return this.findByName(name, currentPage);
        }

        if (StringUtils.isEmpty(name)) {
            return this.findByCategory(category, currentPage);
        }

        Session session = sessionFactory.getCurrentSession();

        Long count = (Long) session.createQuery("select count(id) from Product where (shared = :shared or technicalSegment.creationUser = :username) and name like :name and category = :cat order by name")
                .setString("name", name)
                .setEntity("cat", category)
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .uniqueResult();

        int pageCount = new Double(count / Page.PAGE_SIZE).intValue();

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        } else if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        List<Product> resultList = (List<Product>) session.createQuery("from Product where (shared = :shared or technicalSegment.creationUser = :username) and name like :name and category = :cat order by name")
                .setString("name", name)
                .setEntity("cat", category)
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .setFirstResult((currentPage-1) * Page.PAGE_SIZE)
                .setMaxResults(Page.PAGE_SIZE)
                .list();

        Page<Product> resultPage = new Page(resultList);
        resultPage.setCurrentPage(currentPage);
        resultPage.setPageCount(pageCount);

        return resultPage;
    }

    @Transactional(readOnly = true)
    public Page<Product> findAll (Integer currentPage) {
        Session session = sessionFactory.getCurrentSession();

        Long count = (Long) session.createQuery("select count(id) from Product where (shared = :shared or technicalSegment.creationUser = :username) order by name")
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .uniqueResult();

        int pageCount = new Double(count / Page.PAGE_SIZE).intValue();

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        } else if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        List<Product> resultList = (List<Product>) session.createQuery("from Product where (shared = :shared or technicalSegment.creationUser = :username) order by name")
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .setFirstResult((currentPage-1) * Page.PAGE_SIZE)
                .setMaxResults(Page.PAGE_SIZE)
                .list();

        Page<Product> resultPage = new Page(resultList);
        resultPage.setCurrentPage(currentPage);
        resultPage.setPageCount(pageCount);

        return resultPage;
    }

    @Transactional
    public void insert (Product product) {
        if (product.getId() != null) {
            update(product);
        }
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

    @Transactional
    public void update (Product product) {
        if (product.getId() == null) {
            insert(product);
        }
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
    }

    @Transactional
    public void delete (Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(product);
    }

}








