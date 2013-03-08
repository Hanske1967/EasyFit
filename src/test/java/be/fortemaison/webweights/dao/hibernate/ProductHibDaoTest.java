package be.fortemaison.webweights.dao.hibernate;

import be.fortemaison.webweights.dao.IProductDAO;
import be.fortemaison.webweights.dao.IUnitDAO;
import be.fortemaison.webweights.model.Product;
import be.fortemaison.webweights.model.Unit;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 26/01/13
 * Time: 14:51
 * To change this template use File | Settings | File Templates.
 */
public class ProductHibDaoTest extends TestCase {

    ApplicationContext context;

    @Before
    public void setUp () throws Exception {
        this.context =
                new ClassPathXmlApplicationContext(new String[]{"./resources/jdbc-datasources.xml", "./resources/hibernate.xml", "./resources/hibdao.xml"});
    }

    private IProductDAO getProductDAO () {
//        return (IProductDAO) this.context.getBean(ProductHibDao.class);
        return (IProductDAO) this.context.getBean("productDAO");
    }

    @Test
    public void testFindById () throws Exception {
        Product s = getProductDAO().findById(1);
        assertNotNull(s);
    }

    @Test
    public void testFindByName () throws Exception {
        List<Product> s = getProductDAO().findByName("gouda");
        assertNotNull(s);
    }

    @Test
    public void testFindAll () throws Exception {
        List<Product> s = getProductDAO().findAll();
        assertNotNull(s);
    }

    @Test
    public void testFindFavorites () throws Exception {
        List<Product> s = getProductDAO().findFavorites();
        assertNotNull(s);
    }

    @Test
    public void testInsert () throws Exception {
        //
    }

    @Test
    public void testUpdate () throws Exception {

        Unit unit = null;

        List<Unit> units = getScaleDAO().findAll();
        if (units != null && !units.isEmpty()) {
            unit = units.iterator().next();
        }

        Product product = new Product("Test", unit, (double) 1.0, (double) 2.5, Boolean.TRUE, "description");
        getProductDAO().insert(product);

        Integer id = product.getId();
        assertNotNull(id);

        Product product2 = getProductDAO().findById(id);
        assertNotNull(product2);

        product2.setName("UpdTest");
        getProductDAO().update(product2);

        product = null;
        product = getProductDAO().findById(id);
        assertNotNull(product);

        getProductDAO().delete(product);
        product = getProductDAO().findById(id);
        assertNull(product);

    }

    @Test
    public void testDelete () throws Exception {
        //
    }

    public IUnitDAO getScaleDAO () {
        return (IUnitDAO) this.context.getBean("unitDAO");
    }
}
