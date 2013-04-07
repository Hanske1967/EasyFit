package be.fortemaison.easyfit.dao;

import be.fortemaison.easyfit.model.Weight;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public interface IWeightDAO {

    public Weight findById (Integer id);

    /**
     * Find all weigths for current user
     *
     * @return
     */
    public List<Weight> findAll ();

    public void insert (Weight Weight);

    public void update (Weight Weight);

    public void delete (Weight Weight);

}
