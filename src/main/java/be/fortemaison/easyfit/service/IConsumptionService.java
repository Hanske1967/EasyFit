package be.fortemaison.easyfit.service;

import be.fortemaison.easyfit.model.Consumption;
import be.fortemaison.easyfit.model.ConsumptionWeek;

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

    /**
     * find onsumption by id
     *
     * @param id identifier of the consumtion
     * @throws <oode>SecurityException</oode> in cas of attempt to see another user consumtion
     */
    public Consumption findById (Integer id);

    /**
     * find onsumption and details by id
     *
     * @param id identifier of the consumtion
     * @throws <oode>SecurityException</oode> in cas of attempt to see another user consumtion
     */
    public Consumption findByIdWithDetails (Integer id);

    /**
     * find onsumption by current user (via Contect) and date
     *
     * @param date of the consumtion
     */
    public Consumption findByDate (Date date);

    /**
     * find onsumption with details by current user (via Contect) and date
     *
     * @param date of the consumtion
     */
    public Consumption findByDateWithDetails (Date date);

    /**
     * find all consumptions for the current user (via Contect)
     */
    public List<Consumption> findAll ();

    /**
     * find all consumptions and details for the current user (via Contect)
     */
    public List<Consumption> findAllWithdetails ();

    /**
     * insert a consumption and all details for the current user (via Contect)
     */
    public void insert (Consumption consumption);

    /**
     * update a consumption and all details for the current user (via Contect)
     *
     * @throws <code>SecurityException</code> in case of attemp to delete consumption from another user
     */
    public void update (Consumption consumption);

    /**
     * delete a consumption and all details.
     *
     * @throws <code>SecurityException</code> in case of attemp to delete consumption from another user
     */
    public void delete (Consumption consumption);

    // week related API

    /**
     * Find all consumptions and details for the current user (via Contect) for a given week
     *
     * @param date the date in the week to search for
     * @param firstWeekDay is the index of the day beginning the week, as defined in <code>org.joda.time.DateTimeConstants</code>
     * @return 7 consumptions, one for each day of the week containing the given date.
     */
    public ConsumptionWeek findForWeek (Date date, int firstWeekDay);

}
