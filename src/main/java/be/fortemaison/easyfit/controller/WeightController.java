package be.fortemaison.easyfit.controller;

import be.fortemaison.easyfit.dao.IWeightDAO;
import be.fortemaison.easyfit.form.WeightForm;
import be.fortemaison.easyfit.model.Weight;
import be.fortemaison.easyfit.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Weight: Hans
 * Date: 26/01/13
 * Time: 23:36
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/weights")
public class WeightController {

    @Autowired
    private IWeightDAO weightDAO;

    @Autowired
    private Context context;

    private static final DateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @ModelAttribute("context")
    public Context getContext () {
        return context;
    }

    /**
     * List view with all Weights
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String prepareList (Model model) {
        List<Weight> weights = this.weightDAO.findAll();
        List<WeightForm> weightForms = new ArrayList<WeightForm>(weights.size());
        for (Weight weight : weights) {
            weightForms.add(new WeightForm(weight));
        }
        model.addAttribute("weights", weightForms);
        return "weights/list";
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
            model.addAttribute(new WeightForm());
            return "weights/edit";
        } else {
            Weight weight = this.weightDAO.findById(key);
            if (weight != null) {
                model.addAttribute(new WeightForm(weight));
                return "weights/edit";
            }
        }

        return "weights/list";
    }

    /**
     * Process update form input, update record in db
     *
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String processUpdate (@Validated WeightForm weightForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "weights/edit";
        }

        Weight weight = weightForm.toWeight();
        if (weightForm.getId() != null) {
            //  update from existing
            weight = this.weightDAO.findById(weightForm.getId());
            weight.setWeight(weightForm.getWeight());
        }

        Date aDate = null;
        try {
            aDate = weightForm.getDateStr() == null ? weightForm.getDate() : Utils.SHORT_DATE_FORMATTER.parse(weightForm.getDateStr());
        } catch (ParseException e) {
            System.err.println("Date not recognized");
            aDate = weightForm.getDate();
        }

        weight.setDate(aDate);
        weight.setUser(getContext().getUser());
        this.weightDAO.update(weight);

        return "redirect:/weights/list";
    }

    /**
     * Delete scale and redirect to list
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String processDelete (@RequestParam(value = "key") Integer key) {
        Weight weight = this.weightDAO.findById(key);
        if (weight != null) {
            this.weightDAO.delete(weight);
            return "redirect:/weights/list";
        }

        return "weights/list";
    }

}
