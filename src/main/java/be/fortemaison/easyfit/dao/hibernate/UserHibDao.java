package be.fortemaison.easyfit.dao.hibernate;

import be.fortemaison.easyfit.dao.IUserDAO;
import be.fortemaison.easyfit.model.User;
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
public class UserHibDao implements IUserDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findById (Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.get(User.class, id);
    }

    @Transactional(readOnly = true)
    public User findByUsername (String name) {
        Session session = sessionFactory.getCurrentSession();
        User result = (User) session.createQuery("from User where username like :name").setString("name", name).uniqueResult();
        return result;
    }

    @Transactional(readOnly = true)
    public List<User> findAll () {
        Session session = sessionFactory.getCurrentSession();
        List<User> result = (List<User>) session.createQuery("from User").list();
        return result;
    }

    @Transactional
    public void insert (User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Transactional
    public void update (User user) {
        if (user.getId() == null) {
            insert(user);
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.update(user);
        }
    }

    @Transactional
    public void delete (User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }
}
