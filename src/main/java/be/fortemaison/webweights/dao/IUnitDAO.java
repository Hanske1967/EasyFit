package be.fortemaison.webweights.dao;

import be.fortemaison.webweights.model.Unit;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public interface IUnitDAO {

    public Unit findById (Integer id);

    public List<Unit> findByName (String name);

    public List<Unit> findAll ();

    public void insert (Unit unit);

    public void update (Unit unit);

    public void delete (Unit unit);

}
