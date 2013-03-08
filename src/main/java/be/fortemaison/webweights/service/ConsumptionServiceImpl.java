package be.fortemaison.webweights.service;

import be.fortemaison.webweights.dao.IConsumptionDAO;
import be.fortemaison.webweights.dao.IProductDAO;
import be.fortemaison.webweights.dao.IUserDAO;
import be.fortemaison.webweights.model.Consumption;
import be.fortemaison.webweights.model.User;
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
@Service
public class ConsumptionServiceImpl implements IConsumptionService {

    private IConsumptionDAO consumptionDAO;

    private IProductDAO productDAO;

    private IUserDAO userDAO;

    public void setUserDAO (IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setProductDAO (IProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void setConsumptionDAO (IConsumptionDAO consumptionDAO) {
        this.consumptionDAO = consumptionDAO;
    }

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
        List<User> users = this.userDAO.findByName("INGE");
        if (!users.isEmpty()) {
            User inge = users.get(0);
            consumption.setUser(inge);
        }

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

}
