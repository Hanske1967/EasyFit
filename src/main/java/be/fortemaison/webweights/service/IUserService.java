package be.fortemaison.webweights.service;

import be.fortemaison.webweights.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 26/01/13
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */
public interface IUserService {

    public User findById (Integer id);

    public User findByUsername (String name);

    public List<User> findByName (String name);

    public List<User> findAll ();

    public void update (User user);

    public void delete (User user);

}
