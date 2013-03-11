package be.fortemaison.webweights.service;

import be.fortemaison.webweights.dao.IConsumptionDAO;
import be.fortemaison.webweights.dao.IProductDAO;
import be.fortemaison.webweights.dao.IUserDAO;
import be.fortemaison.webweights.model.Consumption;
import org.joda.time.DateMidnight;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 3/02/13
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ConsumptionServiceImpl implements IConsumptionService {

    private IConsumptionDAO consumptionDAO;

    private IProductDAO productDAO;

    private IUserDAO userDAO;

    public void setUserDAO (IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setProductDAO (IProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void setConsumptionDAO (IConsumptionDAO consumptionDAO) {
        this.consumptionDAO = consumptionDAO;
    }

    @Transactional(readOnly = true)
    public Consumption findById (Integer id) {
        return this.consumptionDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public Consumption findByIdWithDetails (Integer id) {
        return this.consumptionDAO.findByIdWithDetails(id);
    }

    @Transactional(readOnly = true)
    public Consumption findByDate (Date date) {
        return this.consumptionDAO.findByDate(date);
    }

    @Transactional(readOnly = true)
    public Consumption findByDateWithDetails (Date date) {
        return this.consumptionDAO.findByDateWithDetails(date);
    }

    @Transactional(readOnly = true)
    public List<Consumption> findAll () {
        return this.consumptionDAO.findAll();
    }

    @Transactional(readOnly = true)
    public List<Consumption> findAllWithdetails () {
        return this.consumptionDAO.findAllWithDetails();
    }

    @Transactional
    public void insert (Consumption consumption) {
        this.consumptionDAO.insert(consumption);
    }

    @Transactional
    public void update (Consumption consumption) {
        if (consumption.getId() == null) {
            this.consumptionDAO.insert(consumption);
        } else {
            this.consumptionDAO.update(consumption);
        }
    }

    @Transactional
    public void delete (Consumption consumption) {
        this.consumptionDAO.delete(consumption);
    }

    /**
     * if dayOfWeek(date) = FRIDAY(5) and firstWeekDay = WEDNESDAY => firstDay: WEDNESDAY(3) of this week, lastDay: TUESDAY(firstDay-1) of next week
     * if dayOfWeek(date) = FRIDAY(5) and firstWeekDay = SUNDAY => firstDay: SUNDAY(7) of this week, lastDay: SATURDAY(6 = (firstDay-1 = 0 ? 7 : firstDay-1)) of next week
     * if dayOfWeek(date) = FRIDAY(5) and firstWeekDay = MONDAY => firstDay: MONDAY(1) of this week, lastDay: SUNDAY(7 = (firstDay-1 = 0 ? 7 : firstDay-1)) of next week
     * if dayOfWeek(date) = FRIDAY(5) and firstWeekDay = FRIDAY => firstDay: FRIDAY(1) of this week, lastDay: THURSDAY(7 = (firstDay-1 = 0 ? 7 : firstDay-1)) of next week
     *
     * @param date the date in the week to search for
     * @param firstWeekDay is the index of the day beginning the week, as defined in <code>org.joda.time.DateTimeConstants</code>
     * @return
     */
    @Override
    public List<Consumption> findForWeek (Date date, int firstWeekDay) {
        DateMidnight dm = new DateMidnight(date.getTime());
        DateMidnight firstDay = dm.withDayOfWeek(firstWeekDay);
        DateMidnight lastDay = dm.withDayOfWeek((firstWeekDay - 1) == 0 ? 7 : firstWeekDay - 1); // plusDay(7) to go to next week

        List<Consumption> result = this.consumptionDAO.findBetweenDates(firstDay.toDate(), lastDay.toDate());
        return result;
    }

}
