package be.fortemaison.webweights.dao;

import be.fortemaison.webweights.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public interface IUserDAO {

    public User findById (Integer id);

    public User findByUsername (String username);

    public List<User> findByName (String name);

    public List<User> findAll ();

    public void insert (User User);

    public void update (User User);

    public void delete (User User);

}
