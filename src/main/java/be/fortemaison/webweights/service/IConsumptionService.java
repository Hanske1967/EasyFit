package be.fortemaison.webweights.service;

import be.fortemaison.webweights.model.Consumption;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 3/02/13
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */
public interface IConsumptionService {

    public Consumption findById (Integer id);

    public Consumption findByIdWithDetails (Integer id);

    public Consumption findByDate (Date date);

    public Consumption findByDateWithDetails (Date date);

    public List<Consumption> findAll ();

    public List<Consumption> findAllWithdetails ();

    public void insert (Consumption consumption);

    public void update (Consumption consumption);

    public void delete (Consumption consumption);

    // week related API

    /**
     * @param date the date in the week to search for
     * @param firstWeekDay is the index of the day beginning the week, as defined in <code>org.joda.time.DateTimeConstants</code>
     * @return 7 consumptions, one for each day of the week containing the given date.
     */
    public List<Consumption> findForWeek (Date date, int firstWeekDay);

}
