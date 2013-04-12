package be.fortemaison.easyfit.dao.hibernate;

import be.fortemaison.easyfit.dao.IUnitDAO;
import be.fortemaison.easyfit.model.Unit;
import be.fortemaison.easyfit.util.ContextThreadLocal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UnitHibDao implements IUnitDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public Unit findById (Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Unit result = (Unit) session.get(Unit.class, id);
        return result;
    }

    @Transactional(readOnly = true)
    public List<Unit> findByName (String name) {
        Session session = sessionFactory.getCurrentSession();
        List<Unit> result = (List<Unit>) session.createQuery("from Unit u where u.name like :name and (u.shared = :shared or u.technicalSegment.creationUser = :username) order by name")
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .setString("name", name)
                .list();
        return result;
    }


    @Transactional(readOnly = true)
    public List<Unit> findAll () {
        Session session = sessionFactory.getCurrentSession();
        List<Unit> result = (List<Unit>) session.createQuery("from Unit u where u.shared = :shared or u.technicalSegment.creationUser = :username")
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .setBoolean("shared", Boolean.TRUE)
                .list();
        return result;
    }

    @Transactional
    public void insert (Unit unit) {
        Session session = sessionFactory.getCurrentSession();
        session.save(unit);
    }

    @Transactional
    public void update (Unit unit) {
        if (unit.getId() == null) {
            insert(unit);
        }
        Session session = sessionFactory.getCurrentSession();
        session.update(unit);
    }

    @Transactional
    public void delete (Unit unit) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(unit);
    }
}
