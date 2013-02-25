package be.fortemaison.webweights.dao.hibernate;

import be.fortemaison.webweights.dao.IRecipeDAO;
import be.fortemaison.webweights.model.Recipe;
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
                .createQuery("from Recipe r left join fetch r.recipeDetails link left join fetch link.product where r.id = ?")
                .setInteger(0, id)
                .uniqueResult();

        return result;
    }

    @Transactional(readOnly = true)
    public List<Recipe> findByName (String name) {
        Session session = sessionFactory.getCurrentSession();
        List<Recipe> result = (List<Recipe>) session.createQuery("from Recipe r where r.name like ?").setString(0, name).list();
        return result;
    }

    @Transactional(readOnly = true)
    public List<Recipe> findByNameWithDetails (String name) {
        Session session = sessionFactory.getCurrentSession();
        List<Recipe> result = session
                .createQuery("from Recipe r left join fetch r.recipeDetails link left join fetch link.product where r.name like ?")
                .setString(0, name)
                .list();

        return result;
    }

    @Transactional(readOnly = true)
    public List<Recipe> findFavorites () {
        Session session = sessionFactory.getCurrentSession();
        List<Recipe> result = (List<Recipe>) session.createQuery("from Recipe r left join fetch r.recipeDetails link left join fetch link.product where r.favorite = ?").setBoolean(0, Boolean.TRUE).list();
        return result;
    }

    @Transactional(readOnly = true)
    public List<Recipe> findFavoritesWithDetails () {
        Session session = sessionFactory.getCurrentSession();
        List<Recipe> result = session
                .createQuery("from Recipe r left join fetch r.recipeDetails link left join fetch link.product where r.favorite = ?")
                .setBoolean(0, Boolean.TRUE)
                .list();

        return result;
    }

    @Transactional(readOnly = true)
    public List<Recipe> findAll () {
        Session session = sessionFactory.getCurrentSession();
        List<Recipe> result = (List<Recipe>) session.createQuery("from Recipe").list();
        return result;
    }

    @Transactional(readOnly = true)
    public List<Recipe> findAllWithDetails () {
        Session session = sessionFactory.getCurrentSession();
        List<Recipe> result = session
                .createQuery("from Recipe r left join fetch r.recipeDetails link left join fetch link.product")
                .list();

        return result;
    }

    @Transactional
    public void insert (Recipe recipe) {
        Session session = sessionFactory.getCurrentSession();
        session.save(recipe);
    }

    @Transactional
    public void update (Recipe recipe) {
        Session session = sessionFactory.getCurrentSession();
        session.update(recipe);
    }

    @Transactional
    public void delete (Recipe recipe) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(recipe);
    }

}