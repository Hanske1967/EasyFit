package be.fortemaison.webweights.service;

import be.fortemaison.webweights.model.Unit;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 26/01/13
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */
public interface IUnitService {

    public Unit findById (Integer id);

    public List<Unit> findByName (String name);

    public List<Unit> findAll ();

    public void update (Unit unit);

    public void delete (Unit unit);

}
