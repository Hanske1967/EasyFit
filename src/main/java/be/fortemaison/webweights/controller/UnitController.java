package be.fortemaison.webweights.controller;

import be.fortemaison.webweights.form.UnitForm;
import be.fortemaison.webweights.model.Unit;
import be.fortemaison.webweights.service.IUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    private IUnitService unitService;

    @Autowired
    public void setUnitService (IUnitService unitService) {
        this.unitService = unitService;
    }

    /**
     * List view with all units
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String prepareList (Model model) {
        model.addAttribute("units", this.unitService.findAll());
        return "units/list";
    }


    /**
     * Prepare new form for input
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String prepareNew (Model model) {
        model.addAttribute(new UnitForm());
        return "units/edit";
    }

    /**
     * Prepare update form for input
     *
     * @param key
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET, params = "key")
    public String prepareUpdate (Integer key, Model model) {
        Unit unit = this.unitService.findById(key);
        if (unit != null) {
            model.addAttribute(new UnitForm(unit));
            return "units/edit";
        }

        return "units/list";
    }

    /**
     * Process new form inpout, create new units and return to list
     *
     * @param unitForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processNew (@Validated UnitForm unitForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "units/edit";
        }

        this.unitService.update(unitForm.getUnit());
        return "redirect:/units/list";
    }

    /**
     * Process update form input, update record in db
     *
     * @param unitForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdate (@Validated UnitForm unitForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "units/edit";
        }

        this.unitService.update(unitForm.getUnit());
        return "redirect:/units/list";
    }


    /**
     * Delete scale and redirect to list
     *
     * @param key
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET, params = "key")
    public String processDelete (Integer key, Model model) {
        Unit unit = this.unitService.findById(key);
        if (unit != null) {
            this.unitService.delete(unit);
            return "redirect:/units/list";
        }

        return "units/list";
    }

}
