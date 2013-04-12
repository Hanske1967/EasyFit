package be.fortemaison.easyfit.dao.hibernate;

import be.fortemaison.easyfit.dao.IRecipeDAO;
import be.fortemaison.easyfit.model.Page;
import be.fortemaison.easyfit.model.Recipe;
import be.fortemaison.easyfit.util.ContextThreadLocal;
import be.fortemaison.easyfit.util.Utils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 3/02/13
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
public class RecipeHibDao implements IRecipeDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public Recipe findById (Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Recipe result = (Recipe) session.get(Recipe.class, id);
        return result;
    }

    @Transactional(readOnly = true)
    public Recipe findByIdWithDetails (Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Recipe result = (Recipe) session
                .createQuery("from Recipe r left join fetch r.recipeDetails link left join fetch link.product where r.id = :id order by r.name")
                .setInteger("id", id)
                .uniqueResult();

        return result;
    }

    @Transactional(readOnly = true)
    public Page<Recipe> findByName (String name, Integer currentPage) {
        Session session = sessionFactory.getCurrentSession();

        String param = name;
        if (!name.contains(Utils.PROCENT)) {
            StringBuilder sb = new StringBuilder(name.length() + 2);
            sb.append(Utils.PROCENT);
            sb.append(name);
            sb.append(Utils.PROCENT);
            param = sb.toString();
        }

        Long count = (Long) session.createQuery("select count(id) from Recipe r where (shared = :shared or technicalSegment.creationUser = :username) and r.name like :name order by r.name")
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

        List<Recipe> resultList = (List<Recipe>) session.createQuery("from Recipe r where (shared = :shared or technicalSegment.creationUser = :username) and r.name like :name order by r.name")
                .setString("name", param)
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .setFirstResult((currentPage - 1) * Page.PAGE_SIZE)
                .setMaxResults(Page.PAGE_SIZE)
                .list();

        Page<Recipe> resultPage = new Page(resultList);
        resultPage.setCurrentPage(currentPage);
        resultPage.setPageCount(pageCount);

        return resultPage;
    }

    @Transactional(readOnly = true)
    public Page<Recipe> findByNameWithDetails (String name, Integer currentPage) {
        Session session = sessionFactory.getCurrentSession();

        Long count = (Long) session.createQuery("select count(r.id) from Recipe r left join fetch r.recipeDetails link left join fetch link.product where (r.shared = :shared or r.technicalSegment.creationUser = :username) and r.name like :name order by r.name")
                .setString("name", name)
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .uniqueResult();

        int pageCount = new Double(count / Page.PAGE_SIZE).intValue();

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        } else if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        List<Recipe> resultList = (List<Recipe>) session.createQuery("from Recipe r left join fetch r.recipeDetails link left join fetch link.product where (r.shared = :shared or r.technicalSegment.creationUser = :username) and r.name like :name order by r.name")
                .setString("name", name)
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .setFirstResult((currentPage - 1) * Page.PAGE_SIZE)
                .setMaxResults(Page.PAGE_SIZE)
                .list();

        Page<Recipe> resultPage = new Page(resultList);
        resultPage.setCurrentPage(currentPage);
        resultPage.setPageCount(pageCount);

        return resultPage;
    }

    @Transactional(readOnly = true)
    public Page<Recipe> findAll (Integer currentPage) {
        Session session = sessionFactory.getCurrentSession();

        Long count = (Long) session.createQuery("select count(r.id) from Recipe r where (technicalSegment.creationUser = :username or shared = :shared) order by r.name")
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .uniqueResult();

        int pageCount = new Double(count / Page.PAGE_SIZE).intValue();

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        } else if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        List<Recipe> resultList = (List<Recipe>) session.createQuery("from Recipe r where (technicalSegment.creationUser = :username or shared = :shared) order by r.name")
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .setFirstResult((currentPage - 1) * Page.PAGE_SIZE)
                .setMaxResults(Page.PAGE_SIZE)
                .list();

        Page<Recipe> resultPage = new Page(resultList);
        resultPage.setCurrentPage(currentPage);
        resultPage.setPageCount(pageCount);

        return resultPage;
    }

    @Transactional(readOnly = true)
    public Page<Recipe> findAllWithDetails (Integer currentPage) {
        Session session = sessionFactory.getCurrentSession();

        Long count = (Long) session.createQuery("select count(r.id) from Recipe r left join fetch r.recipeDetails link left join fetch link.product where (r.technicalSegment.creationUser = :username or r.shared = :shared) order by r.name")
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .uniqueResult();

        int pageCount = new Double(count / Page.PAGE_SIZE).intValue();

        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        } else if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        List<Recipe> resultList = (List<Recipe>) session.createQuery("from Recipe r left join fetch r.recipeDetails link left join fetch link.product where (r.technicalSegment.creationUser = :username or r.shared = :shared) order by r.name")
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .setFirstResult((currentPage-1) * Page.PAGE_SIZE)
                .setMaxResults(Page.PAGE_SIZE)
                .list();

        Page<Recipe> resultPage = new Page(resultList);
        resultPage.setCurrentPage(currentPage);
        resultPage.setPageCount(pageCount);

        return resultPage;
    }

    @Transactional
    public void insert (Recipe recipe) {
        recipe.updatePoints();
        Session session = sessionFactory.getCurrentSession();
        session.save(recipe);
    }

    @Transactional
    public void update (Recipe recipe) {
        if (recipe.getId() == null) {
            insert(recipe);
        }
        recipe.updatePoints();
        Session session = sessionFactory.getCurrentSession();
        session.update(recipe);
    }

    @Transactional
    public void delete (Recipe recipe) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(recipe);
    }

}