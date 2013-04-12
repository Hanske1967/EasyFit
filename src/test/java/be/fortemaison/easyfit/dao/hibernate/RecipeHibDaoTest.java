package be.fortemaison.easyfit.dao.hibernate;

import be.fortemaison.easyfit.dao.IRecipeDAO;
import be.fortemaison.easyfit.model.Recipe;
import be.fortemaison.easyfit.model.RecipeDetail;
import junit.framework.TestCase;
import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 4/02/13
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */
public class RecipeHibDaoTest extends TestCase {

    ApplicationContext context;

    @Before
    public void setUp () throws Exception {
        this.context =
                new ClassPathXmlApplicationContext(new String[]{"/jdbc-datasources.xml", "/hibernate.xml", "/hibdao.xml"});
    }

    private IRecipeDAO getRecipeDAO () {
        return (IRecipeDAO) this.context.getBean("recipeDAO");
    }


    @Test
    public void testFindById () throws Exception {
        Recipe recipe = getRecipeDAO().findById(3);
        assertNotNull(recipe);
        assertFalse(Hibernate.isInitialized(recipe.getRecipeDetails()));
    }

    @Test
    public void testFindByIdWithProducts () throws Exception {
        Recipe recipe = getRecipeDAO().findByIdWithDetails(3);
        assertNotNull(recipe);
        assertTrue(Hibernate.isInitialized(recipe.getRecipeDetails()));
        for (RecipeDetail product : recipe.getRecipeDetails()) {
            assertTrue(Hibernate.isInitialized(product));
        }
    }

    @Test
    public void testFindByName () throws Exception {
        List<Recipe> recipes = getRecipeDAO().findByName("croissant confituur", 1);
        assertTrue(!recipes.isEmpty());

        for (Recipe recipe : recipes) {
            assertFalse(Hibernate.isInitialized(recipe.getRecipeDetails()));
        }
    }

    @Test
    public void testFindByNameWithProducts () throws Exception {
        List<Recipe> recipes = getRecipeDAO().findByNameWithDetails("croissant confituur", 1);
        assertTrue(!recipes.isEmpty());

        for (Recipe recipe : recipes) {
            assertTrue(Hibernate.isInitialized(recipe.getRecipeDetails()));
            for (RecipeDetail product : recipe.getRecipeDetails()) {
                assertTrue(Hibernate.isInitialized(product));
            }
        }
    }

    /*
    @Test
    public void testFindFavorites () throws Exception {

    }

    @Test
    public void testFindAll () throws Exception {

    }

    @Test
    public void testInsert () throws Exception {

    }

    @Test
    public void testUpdate () throws Exception {

    }

    @Test
    public void testDelete () throws Exception {

    }

    */
}
