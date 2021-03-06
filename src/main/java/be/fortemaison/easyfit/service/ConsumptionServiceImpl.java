package be.fortemaison.easyfit.service;

import be.fortemaison.easyfit.dao.IConsumptionDAO;
import be.fortemaison.easyfit.dao.IProductDAO;
import be.fortemaison.easyfit.dao.IUserDAO;
import be.fortemaison.easyfit.model.Consumption;
import be.fortemaison.easyfit.model.ConsumptionWeek;
import be.fortemaison.easyfit.model.User;
import be.fortemaison.easyfit.util.ContextThreadLocal;
import org.joda.time.DateMidnight;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service("consumptionService")
public class ConsumptionServiceImpl implements IConsumptionService {

   @Autowired
    private IConsumptionDAO consumptionDAO;

    @Autowired
    private IProductDAO productDAO;

    @Autowired
    private IUserDAO userDAO;

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
    public ConsumptionWeek findForWeek (Date date, int firstWeekDay) {
        ConsumptionWeek result = new ConsumptionWeek(date);

        DateMidnight dm = new DateMidnight(date.getTime());
        DateMidnight firstDay = dm.withDayOfWeek(firstWeekDay);
        DateMidnight lastDay = dm.withDayOfWeek((firstWeekDay - 1) == 0 ? 7 : firstWeekDay - 1); // plusDay(7) to go to next week

        Consumption consumption = this.findByDateWithDetails(date);
        List<Consumption> consumptions = this.consumptionDAO.findBetweenDates(firstDay.toDate(), lastDay.toDate());
        result.setConsumptions(consumptions);
        result.setCurrentConsumption(consumption);

        User user = ContextThreadLocal.get().getUser();

        Double extraPointsLeft = user.getExtraPoints().doubleValue();
        Double excercisePoints = 0.0;
        for (Consumption c : consumptions) {
            if (c.getPoints() != null && c.getPoints() > user.getDayPoints()) {
                extraPointsLeft -= (c.getPoints() - user.getDayPoints());
            }
            excercisePoints += c.getExcercisePoints();
        }

        Double exercisePointsLeft = excercisePoints;
        if (extraPointsLeft < 0){
            exercisePointsLeft += extraPointsLeft; // extraPointsLeft negatif
            extraPointsLeft = 0.0;
        }

        result.setDayPoints(user.getDayPoints());
        result.setExtraPoints(user.getExtraPoints());
        result.setExcercisePoints(excercisePoints);
        result.setExtraPointsLeft(extraPointsLeft);
        result.setExcercisePointsLeft(exercisePointsLeft);

        Double pointsLeft = user.getDayPoints().doubleValue() - (consumption == null ? 0.0 : consumption.getPoints());
        pointsLeft = pointsLeft < 0 ? 0 : pointsLeft;
        result.setDayPointsLeft(pointsLeft);

        return result;
    }

}
