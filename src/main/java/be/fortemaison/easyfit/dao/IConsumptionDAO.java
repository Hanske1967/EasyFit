package be.fortemaison.easyfit.dao;

import be.fortemaison.easyfit.model.Consumption;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public interface IConsumptionDAO {

    public Consumption findById (Integer id);

    public Consumption findByIdWithDetails (Integer id);

    public Consumption findByDate (Date date);

    public Consumption findByDateWithDetails (Date date);

    public List<Consumption> findBetweenDates (Date startDate, Date endDate);

    public List<Consumption> findBetweenDatesWithDetails (Date startDate, Date endDate);

    public List<Consumption> findAll ();

    public List<Consumption> findAllWithDetails ();

    public void insert (Consumption consumption);

    public void update (Consumption consumption);

    public void delete (Consumption consumption);

}
