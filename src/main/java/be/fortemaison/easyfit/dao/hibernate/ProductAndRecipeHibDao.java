package be.fortemaison.easyfit.dao.hibernate;

import be.fortemaison.easyfit.dao.IProductAndRecipeDAO;
import be.fortemaison.easyfit.model.Page;
import be.fortemaison.easyfit.model.ProductAncestor;
import be.fortemaison.easyfit.model.ProductCategory;
import be.fortemaison.easyfit.util.ContextThreadLocal;
import be.fortemaison.easyfit.util.Utils;
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
    public Page<ProductAncestor> findByName (String name, Integer currentPage) {
        String param = name;
        if (!name.contains(Utils.PROCENT)) {
            StringBuilder sb = new StringBuilder(name.length() + 2);
            sb.append(Utils.PROCENT);
            sb.append(name);
            sb.append(Utils.PROCENT);
            param = sb.toString();
        }

        Session session = sessionFactory.getCurrentSession();

        Long count = (Long) session.createQuery("select count(id) from ProductAncestor p where (shared = :shared or technicalSegment.creationUser = :username) and p.class != 'E' and p.class != 'E' and name like :name order by name")
                .setString("name", param)
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .uniqueResult();

        int pageCount = new Double(count / Page.PAGE_SIZE).intValue() + 1;

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        } else if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        List<ProductAncestor> resultList = (List<ProductAncestor>) session.createQuery("from ProductAncestor p where (shared = :shared or technicalSegment.creationUser = :username) and p.class != 'E' and p.class != 'E' and name like :name order by name")
                .setString("name", param)
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .setFirstResult((currentPage-1) * Page.PAGE_SIZE)
                .setMaxResults(Page.PAGE_SIZE)
                .list();

        Page<ProductAncestor> resultPage = new Page(resultList);
        resultPage.setCurrentPage(currentPage);
        resultPage.setPageCount(pageCount);

        return resultPage;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductAncestor> findAll (Integer currentPage) {
        Session session = sessionFactory.getCurrentSession();

        Long count = (Long) session.createQuery("select count(id) from ProductAncestor p where (shared = :shared or technicalSegment.creationUser = :username) and p.class != 'E' order by name")
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .uniqueResult();

        int pageCount = new Double(count / Page.PAGE_SIZE).intValue() + 1;

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        } else if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        List<ProductAncestor> resultList = (List<ProductAncestor>) session.createQuery("from ProductAncestor p where (shared = :shared or technicalSegment.creationUser = :username) and p.class != 'E' order by name")
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .setFirstResult((currentPage-1) * Page.PAGE_SIZE)
                .setMaxResults(Page.PAGE_SIZE)
                .list();

        Page<ProductAncestor> resultPage = new Page(resultList);
        resultPage.setCurrentPage(currentPage);
        resultPage.setPageCount(pageCount);

        return resultPage;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductAncestor> findFavorites (Integer currentPage) {
        Session session = sessionFactory.getCurrentSession();

        Long count = (Long) session.createQuery("select count(fav.id) from FavoriteProduct fav join fetch fav.productAncestor p where fav.user.id = :userid and p.class != 'E' order by p.name")
                .setInteger("userid", ContextThreadLocal.get().getUser().getId())
                .uniqueResult();

        int pageCount = new Double(count / Page.PAGE_SIZE).intValue() + 1;

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        } else if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        List<ProductAncestor> resultList = (List<ProductAncestor>) session.createQuery("from FavoriteProduct fav join fetch fav.productAncestor p where fav.user.id = :userid and p.class != 'E' order by p.name")
                .setInteger("userid", ContextThreadLocal.get().getUser().getId())
                .setFirstResult((currentPage-1) * Page.PAGE_SIZE)
                .setMaxResults(Page.PAGE_SIZE)
                .list();

        Page<ProductAncestor> resultPage = new Page(resultList);
        resultPage.setCurrentPage(currentPage);
        resultPage.setPageCount(pageCount);

        return resultPage;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductAncestor> findByCategory (ProductCategory category, Integer currentPage) {
        Session session = sessionFactory.getCurrentSession();

        Long count = (Long) session.createQuery("select count(id) from ProductAncestor p where p.class != 'E' and p.category = :category order by p.name")
                .setEntity("category", category)
                .uniqueResult();

        int pageCount = new Double(count / Page.PAGE_SIZE).intValue() + 1;

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        } else if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        List<ProductAncestor> resultList = (List<ProductAncestor>) session.createQuery("from ProductAncestor p where p.class != 'E' and p.category = :category order by p.name")
                .setEntity("category", category)
                .setFirstResult(currentPage * Page.PAGE_SIZE)
                .setFirstResult((currentPage-1) * Page.PAGE_SIZE)
                .list();

        Page<ProductAncestor> resultPage = new Page(resultList);
        resultPage.setCurrentPage(currentPage);
        resultPage.setPageCount(pageCount);

        return resultPage;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductAncestor> findByNameAndCategory (String name, ProductCategory category, Integer currentPage) {
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

        Long count = (Long) session.createQuery("select count(id) from ProductAncestor p where p.class != 'E' and p.name like :queryName and p.category = :category order by p.name")
                .setString("queryName", name)
                .setEntity("category", category)
                .uniqueResult();

        int pageCount = new Double(count / Page.PAGE_SIZE).intValue() + 1;

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        } else if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        List<ProductAncestor> resultList = (List<ProductAncestor>) session.createQuery("from ProductAncestor p where p.class != 'E' and p.name like :queryName and p.category = :category order by p.name")
                .setString("queryName", name)
                .setEntity("category", category)
                .setFirstResult(currentPage * Page.PAGE_SIZE)
                .setMaxResults(Page.PAGE_SIZE)
                .list();

        Page<ProductAncestor> resultPage = new Page(resultList);
        resultPage.setCurrentPage(currentPage);
        resultPage.setPageCount(pageCount);

        return resultPage;
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
