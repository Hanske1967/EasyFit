package be.fortemaison.easyfit.dao.hibernate;

import be.fortemaison.easyfit.dao.IConsumptionDAO;
import be.fortemaison.easyfit.model.Consumption;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 3/02/13
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
public class ConsumptionHibDao implements IConsumptionDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public Consumption findById (Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Consumption result = (Consumption) session.get(Consumption.class, id);
        return result;
    }

    @Transactional(readOnly = true)
    public Consumption findByIdWithDetails (Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Consumption result = (Consumption) session
                .createQuery("from Consumption r left join fetch r.consumptionDetails link left join fetch link.product where r.id = :id")
                .setInteger("id", id)
                .uniqueResult();

        return result;
    }

    @Transactional(readOnly = true)
    public Consumption findByDate (Date date) {
        Session session = sessionFactory.getCurrentSession();
        Consumption result = (Consumption) session.createQuery("from Consumption r where r.date = :date").setDate("date", date).uniqueResult();
        return result;
    }

    @Transactional(readOnly = true)
    public Consumption findByDateWithDetails (Date date) {
        Session session = sessionFactory.getCurrentSession();
        Consumption result = (Consumption) session
                .createQuery("from Consumption r left join fetch r.consumptionDetails link left join fetch link.product where r.date = ?")
                .setDate(0, date)
                .uniqueResult();

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Consumption> findBetweenDates (Date startDate, Date endDate) {
        Session session = sessionFactory.getCurrentSession();
        List<Consumption> result = (List<Consumption>) session
                .createQuery("from Consumption r where r.date >= :startDate and r.date < :endDate")
                .setDate("startDate", startDate)
                .setDate("endDate", endDate)
                .list();

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Consumption> findBetweenDatesWithDetails (Date startDate, Date endDate) {
        Session session = sessionFactory.getCurrentSession();
        List<Consumption> result = (List<Consumption>) session
                .createQuery("from Consumption r left join fetch r.consumptionDetails link left join fetch link.product where r.date >= :startDate and r.date < :endDate")
                .setDate("startDate", startDate)
                .setDate("endDate", endDate)
                .list();

        return result;
    }

    @Transactional(readOnly = true)
    public List<Consumption> findAll () {
        Session session = sessionFactory.getCurrentSession();
        List<Consumption> result = (List<Consumption>) session.createQuery("from Consumption").list();
        return result;
    }

    @Transactional(readOnly = true)
    public List<Consumption> findAllWithDetails () {
        Session session = sessionFactory.getCurrentSession();
        List<Consumption> result = session
                .createQuery("from Consumption r left join fetch r.consumptionDetails link left join fetch link.product")
                .list();

        return result;
    }

    @Transactional
    public void insert (Consumption consumption) {
        //  TODO find a way to remove Hibernate explicit dependancy
        if (Hibernate.isInitialized(consumption.getConsumptionDetails())) {
            consumption.updatePoints();
        }
        Session session = sessionFactory.getCurrentSession();
        session.save(consumption);
    }

    @Transactional
    public void update (Consumption consumption) {
        //  TODO find a way to remove Hibernate explicit dependancy
        if (Hibernate.isInitialized(consumption.getConsumptionDetails())) {
            consumption.updatePoints();
        }
        Session session = sessionFactory.getCurrentSession();
        session.update(consumption);
    }

    @Transactional
    public void delete (Consumption consumption) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(consumption);
    }

}