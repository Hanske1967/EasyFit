package be.fortemaison.easyfit.controller;

import be.fortemaison.easyfit.dao.IUnitDAO;
import be.fortemaison.easyfit.form.UnitForm;
import be.fortemaison.easyfit.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 26/01/13
 * Time: 23:36
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/units")
public class UnitController {

    @Autowired
    private IUnitDAO unitDAO;

    /**
     * List view with all units
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String prepareList (Model model) {
        List<Unit> units = this.unitDAO.findAll();
        List<UnitForm> unitForms = new ArrayList<UnitForm>(units.size());
        for (Unit unit : units) {
            unitForms.add(new UnitForm(unit));
        }
        model.addAttribute("units", unitForms);
        return "units/list";
    }

    /**
     * Prepare update form for input
     *
     * @param key
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String prepareUpdate (@RequestParam(value = "key", required = false) Integer key, Model model) {
        if (key == null) {
            model.addAttribute(new UnitForm());
            return "units/edit";
        } else {
            Unit unit = this.unitDAO.findById(key);
            if (unit != null) {
                model.addAttribute(new UnitForm(unit));
                return "units/edit";
            }
        }

        return "units/list";
    }

    /**
     * Process update form input, update record in db
     *
     * @param unitForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String processUpdate (@Validated UnitForm unitForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "units/edit";
        }

        Unit unit = unitForm.getUnit();
        if (unit.getId() != null) {
            //  update from existing
            unit = this.unitDAO.findById(unit.getId());

            unit.setName(unitForm.getName());
            unit.setDescription(unitForm.getDescription());
            unit.setShared(unitForm.getShared());
        }

        this.unitDAO.update(unit);
        return "redirect:/units/list";
    }

    /**
     * Delete scale and redirect to list
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String processDelete (@RequestParam(value = "key") Integer key) {
        Unit unit = this.unitDAO.findById(key);
        if (unit != null) {
            this.unitDAO.delete(unit);
            return "redirect:/units/list";
        }

        return "units/list";
    }

}
