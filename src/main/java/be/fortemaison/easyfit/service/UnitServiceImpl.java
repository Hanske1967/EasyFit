package be.fortemaison.easyfit.service;

import be.fortemaison.easyfit.dao.IUnitDAO;
import be.fortemaison.easyfit.model.Unit;
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
public class UnitServiceImpl implements IUnitService {

    private IUnitDAO unitDAO;

    public IUnitDAO getUnitDAO () {
        return unitDAO;
    }

    public void setUnitDAO (IUnitDAO unitDAO) {
        this.unitDAO = unitDAO;
    }

    @Transactional(readOnly = true)
    public Unit findById (Integer id) {
        return this.unitDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Unit> findByName (String name) {
        return this.unitDAO.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Unit> findAll () {
        return this.unitDAO.findAll();
    }

    @Transactional
    public void insert (Unit unit) {
        this.unitDAO.insert(unit);
    }

    @Transactional
    public void update (Unit unit) {
        if (unit.getId() == null) {
            this.unitDAO.insert(unit);
        } else {
            this.unitDAO.update(unit);
        }
    }

    @Transactional
    public void delete (Unit unit) {
        this.unitDAO.delete(unit);
    }
}
