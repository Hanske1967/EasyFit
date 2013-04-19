package be.fortemaison.easyfit.dao.hibernate;

import be.fortemaison.easyfit.dao.IWeightDAO;
import be.fortemaison.easyfit.model.Weight;
import be.fortemaison.easyfit.util.ContextThreadLocal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WeightHibDao implements IWeightDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public Weight findById (Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Weight result = (Weight) session.get(Weight.class, id);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Weight> findAll () {
        Session session = sessionFactory.getCurrentSession();
        List<Weight> result = (List<Weight>) session.createQuery("from Weight u where u.user = :user or u.technicalSegment.creationUser = :username order by u.date")
                .setEntity("user", ContextThreadLocal.get().getUser())
                .setString("username", ContextThreadLocal.get().getUser().getUsername())
                .list();
        return result;
    }

    @Transactional
    public void insert (Weight Weight) {
        Session session = sessionFactory.getCurrentSession();
        session.save(Weight);
    }

    @Transactional
    public void update (Weight Weight) {
        if (Weight.getId() == null) {
            insert(Weight);
        }
        Session session = sessionFactory.getCurrentSession();
        session.update(Weight);
    }

    @Transactional
    public void delete (Weight Weight) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(Weight);
    }
}
