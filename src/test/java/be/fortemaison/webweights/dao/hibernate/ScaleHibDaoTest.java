package be.fortemaison.webweights.dao.hibernate;

import be.fortemaison.webweights.dao.IUnitDAO;
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
 * Date: 24/01/13
 * Time: 22:52
 * To change this template use File | Settings | File Templates.
 */
public class ScaleHibDaoTest extends TestCase {

    ApplicationContext context;

    @Before
    public void setUp () throws Exception {
        this.context =
                new ClassPathXmlApplicationContext(new String[]{"./resources/jdbc-datasources.xml", "./resources/hibernate.xml", "./resources/hibdao.xml"});
    }

    private IUnitDAO getScaleDAO () {
//        return (IUnitDAO) this.context.getBean(UnitHibDao.class);
        return (IUnitDAO) this.context.getBean("unitDAO");
    }

    @Test
    public void testFindById () throws Exception {
        Unit s = getScaleDAO().findById(1);
        assertNotNull(s);
    }

    @Test
    public void testFindByName () throws Exception {
        List<Unit> s = getScaleDAO().findByName("ml");
        assertTrue(!s.isEmpty());
    }

    @Test
    public void testFindAll () throws Exception {
        List<Unit> s = getScaleDAO().findAll();
        assertTrue(!s.isEmpty());
    }

    @Test
    public void testInsert () throws Exception {
        Unit s = new Unit();
        s.setName("test");
        s.setDescription("testScale " + System.currentTimeMillis());

        getScaleDAO().insert(s);
        assertNotNull(s.getId());

        s = getScaleDAO().findById(s.getId());
        getScaleDAO().delete(s);
    }

    @Test
    public void testUpdate () throws Exception {
        Unit s = new Unit();
        s.setName("test");
        s.setDescription("testScale " + System.currentTimeMillis());

        getScaleDAO().insert(s);
        assertNotNull(s.getId());

        String newDescr = "testScale updated " + System.currentTimeMillis();
        s.setDescription(newDescr);
        getScaleDAO().update(s);

        s = getScaleDAO().findById(s.getId());
        assertEquals(newDescr, s.getDescription());

        getScaleDAO().delete(s);
    }

    @Test
    public void testDelete () throws Exception {
        Unit s = new Unit();
        s.setName("test");
        s.setDescription("testScale " + System.currentTimeMillis());

        getScaleDAO().insert(s);
        assertNotNull(s.getId());

        s = getScaleDAO().findById(s.getId());
        getScaleDAO().delete(s);
    }
}
