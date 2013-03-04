package be.fortemaison.webweights.service;

import be.fortemaison.webweights.dao.IUserDAO;
import be.fortemaison.webweights.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 26/01/13
 * Time: 23:23
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserServiceImpl implements IUserService {

    private IUserDAO userDAO;

    public IUserDAO getUserDAO () {
        return userDAO;
    }

    public void setUserDAO (IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById (Integer id) {
        return this.userDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public User findByUsername (String username) {
        return this.userDAO.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public List<User> findByName (String name) {
        return this.userDAO.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<User> findAll () {
        return this.userDAO.findAll();
    }

    @Transactional
    public void insert (User user) {
        this.userDAO.insert(user);
    }

    @Transactional
    public void update (User user) {
        this.userDAO.update(user);
    }

    @Transactional
    public void delete (User user) {
        this.userDAO.delete(user);
    }
}
