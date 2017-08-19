package be.fortemaison.easyfit.controller;

import be.fortemaison.easyfit.dao.IProductAndRecipeDAO;
import be.fortemaison.easyfit.dao.IProductCategoryDAO;
import be.fortemaison.easyfit.dao.IProductDAO;
import be.fortemaison.easyfit.dao.IUnitDAO;
import be.fortemaison.easyfit.form.ConsumptionDetailForm;
import be.fortemaison.easyfit.form.ConsumptionForm;
import be.fortemaison.easyfit.form.ProductForm;
import be.fortemaison.easyfit.form.UserForm;
import be.fortemaison.easyfit.model.*;
import be.fortemaison.easyfit.service.IConsumptionService;
import be.fortemaison.easyfit.util.Utils;
import org.apache.log4j.Logger;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 26/01/13
 * Time: 23:36
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/consumptions")
@SessionAttributes("consumptionDetailForm")
public class ConsumptionController {

    public static final String CONSUMPTION_DETAIL_FORM = "consumptionDetailForm";

    @Autowired
    private IConsumptionService consumptionService;

    @Autowired
    private IProductAndRecipeDAO productAndRecipeDAO;

    @Autowired
    private IProductDAO productDAO;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private IUnitDAO unitDAO;

    @Autowired
    private IProductCategoryDAO productCategoryDAO;

    @Autowired
    private Context context;

    @ModelAttribute("context")
    public Context getContext () {
        return context;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String prepareList (@RequestParam(value = "date", required = false) String date, Model model, HttpServletRequest request) {

        Date aDate = null;
        try {
            aDate = date == null ? new Date() : Utils.DATE_FORMATTER.parse(date);
        } catch (ParseException e) {
            Logger.getLogger(this.getClass()).debug("Date not recognized");
            aDate = new Date();
        }

        ConsumptionWeek week = this.consumptionService.findForWeek(aDate, DateTimeConstants.MONDAY);
        List<ConsumptionForm> weekForms = new ArrayList<ConsumptionForm>(7);
        for (Consumption day: week.getConsumptions()){
            ConsumptionForm consumptionForm = new ConsumptionForm(day);
            consumptionForm.setDayPoints(week.getDayPoints());
            consumptionForm.setExtraPoints(week.getExtraPoints());
            weekForms.add(consumptionForm);
        }
        model.addAttribute("consumptionWeek", weekForms);

        Consumption consumption = week.getCurrentConsumption();
        ConsumptionForm consumptionForm = null;
        if (consumption == null) {
            consumptionForm = new ConsumptionForm(aDate);
        } else {
            consumptionForm = new ConsumptionForm(consumption);

            for (ConsumptionDetail link : consumption.getConsumptionDetails()) {
                ConsumptionDetailForm linkForm = new ConsumptionDetailForm(link);
                consumptionForm.addConsumptionDetailForm(linkForm);
            }
        }

        consumptionForm.setExtraPointsLeft(week.getExtraPointsLeft());
        consumptionForm.setDayPoints(week.getDayPoints());
        consumptionForm.setExtraPoints(week.getExtraPoints());
        consumptionForm.setDayPointsLeft(week.getDayPointsLeft());
        consumptionForm.setExcercisePoints(week.getExcercisePoints());
        consumptionForm.setExcercisePointsLeft(week.getExcercisePointsLeft());

        model.addAttribute("consumptionForm", consumptionForm);

        //  labels of consumption type (lunch, diner...)
        int maxTitles = ConsumptionDetailType.ALL.length;
        String[] sectionTitles = new String[maxTitles];
        String prefix = "ConsumptionDetailType.";
        for (int i = 0; i < maxTitles; i++) {
            sectionTitles[i] = this.messageSource.getMessage(prefix + (i + 1), null, new RequestContext(request).getLocale());
        }
        consumptionForm.setConsumptionDetailHeaders(sectionTitles);

        model.addAttribute("userForm", new UserForm(this.getContext().getUser()));

        return "consumptions/list";
    }

    /**
     * @param key
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/editdetail", method = RequestMethod.GET)
    public String prepareEditDetail (@RequestParam("key") Integer key, @RequestParam("detailKey") Integer detailKey, ModelMap modelMap, final SessionStatus status) {

        Consumption consumption = this.consumptionService.findByIdWithDetails(key);
        if (consumption != null) {
            ConsumptionDetail editedDetail = null;
            for (ConsumptionDetail detail : consumption.getConsumptionDetails()) {
                if (detail.getId().equals(detailKey)) {
                    editedDetail = detail;
                    break;
                }
            }

            ConsumptionDetailForm consumptionDetailForm = new ConsumptionDetailForm(editedDetail);
            consumptionDetailForm.setConsumptionId(key);
            modelMap.addAttribute(CONSUMPTION_DETAIL_FORM, consumptionDetailForm);


            //  store all units for combo
            List<Unit> units = unitDAO.findAll();
            Map<String, String> allUnits = new LinkedHashMap<String, String>(units.size());
            for (Unit unit : units) {
                allUnits.put(unit.getId().toString(), unit.getName());
            }
            modelMap.addAttribute("allUnits", allUnits);
            return "/consumptions/editdetail";
        }

        return "consumptions/list";
    }

    /**
     * Second page of AddDetail wizard
     *
     * @return
     */
    @RequestMapping(value = "/adddexcercise", method = RequestMethod.POST)
    public String processAddExercise (
            @RequestParam(value = "key", required = false) Integer key,
            @RequestParam(value = "date", required = false) String date,
            ModelMap modelMap) {

        ConsumptionDetailForm consumptionDetailForm = new ConsumptionDetailForm();
        consumptionDetailForm.setConsumptionId(key);

        Date aDate = null;
        try {
            aDate = date == null ? new Date() : Utils.DATE_FORMATTER.parse(date);
        } catch (ParseException e) {
            Logger.getLogger(this.getClass()).debug("Date not recognized");
            aDate = new Date();
        }

        consumptionDetailForm.setDate(aDate);
        consumptionDetailForm.setType(ConsumptionDetailType.EXCERCISE);
        modelMap.addAttribute(CONSUMPTION_DETAIL_FORM, consumptionDetailForm);

        List<Excercise> products = this.productAndRecipeDAO.findExcercises();
        List<ProductForm> forms = new ArrayList<ProductForm>(products.size());
        for (ProductAncestor product : products) {
            forms.add(new ProductForm(product));
        }
        modelMap.addAttribute("products", forms);

        modelMap.addAttribute("hideNavigation", true);
        modelMap.addAttribute("finderMode", true);

        return "/products/list";
    }

    /**
     * Initial page of AddDetail wizard : choose product
     *
     * @param key is the consumptionKey
     * @return
     */

    @RequestMapping(value = "/adddetail1", method = RequestMethod.POST)
    public String processAddDetail1 (
            @RequestParam(value = "queryName", required = false) String queryName,
            @RequestParam(value = "category", required = false) Integer categoryId,
            @RequestParam(value = "key", required = false) Integer key,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "consumptionDetailType", required = false) Integer consumptionDetailType,
            @RequestParam(value = "page", required = false) Integer pageIndex,
            ModelMap modelMap,
            final SessionStatus status) {

        ConsumptionDetailForm consumptionDetailForm = (ConsumptionDetailForm) modelMap.get(CONSUMPTION_DETAIL_FORM);
        if (consumptionDetailForm == null) {
            consumptionDetailForm = new ConsumptionDetailForm();
            consumptionDetailForm.setConsumptionId(key);

            Date aDate = null;
            try {
                aDate = date == null ? new Date() : Utils.URL_DATE_FORMATTER.parse(date);
            } catch (ParseException e) {
                Logger.getLogger(this.getClass()).debug("Date not recognized");
                aDate = new Date();
            }

            consumptionDetailForm.setDate(aDate);
            consumptionDetailForm.setType(ConsumptionDetailType.get(consumptionDetailType));
            modelMap.addAttribute(CONSUMPTION_DETAIL_FORM, consumptionDetailForm);
        }

        // TODO Move this code in ControllerHelper
        //  all product categories for filtering
        ProductCategory cat = null;
        List<ProductCategory> categories = this.productCategoryDAO.findAll();
        Map<Integer, String> categoryForms = new LinkedHashMap<Integer, String>(categories.size());
        for (ProductCategory category : categories) {
            categoryForms.put(category.getId(), category.getName());
            if (categoryId != null && category.getId().equals(categoryId)) {
                cat = category;
            }
        }
        modelMap.addAttribute("allCategories", categoryForms);

        //  Find product
        if (!StringUtils.isEmpty(queryName) && queryName.endsWith(",")) {
            //  browser returns the param followed by a comma ??
            queryName = queryName.substring(0, queryName.indexOf(','));

            if (queryName.startsWith("%")) {
                modelMap.addAttribute("queryName", queryName);
            }
        }
        modelMap.addAttribute("category", categoryId);

        Page<ProductAncestor> products = this.productAndRecipeDAO.findByNameAndCategory(queryName, cat, pageIndex);
        List<ProductForm> forms = new ArrayList<ProductForm>(products.size());
        for (ProductAncestor product : products) {
            forms.add(new ProductForm(product));
        }
        modelMap.addAttribute("products", forms);

        modelMap.addAttribute("currentPage", products.getCurrentPage());
        modelMap.addAttribute("pageCount", products.getPageCount());
        modelMap.addAttribute("finderMode", true);

        return "products/list";
    }

    /**
     * Second page of AddDetail wizard
     *
     * @return
     */
    @RequestMapping(value = "/adddetail2", method = RequestMethod.POST)
    public String processAddDetail2 (
            @RequestParam("productKey") Integer productKey,
            @ModelAttribute(CONSUMPTION_DETAIL_FORM) ConsumptionDetailForm consumptionDetailForm,
            final Errors errors) {

        ProductAncestor product = this.productAndRecipeDAO.findById(productKey);
        assert (product != null);
        consumptionDetailForm.setProduct(new ProductForm(product));

        return "/consumptions/editdetail";
    }

     /**
     * Second page of AddDetail wizard
     *
     * @return
     */
    @RequestMapping(value = "/adddetail3", method = RequestMethod.POST)
    public String processAddDetail3 (@ModelAttribute(CONSUMPTION_DETAIL_FORM) ConsumptionDetailForm consumptionDetailForm, final Errors errors, final SessionStatus status) {

        Integer consumptionId = consumptionDetailForm.getConsumptionId();
        Consumption consumption = null;
        if (consumptionId == null) {
            consumption = new Consumption(consumptionDetailForm.getDate());
        } else {
            consumption = this.consumptionService.findByIdWithDetails(consumptionId);
        }

        if (consumptionDetailForm.getId() != null) {
            for (ConsumptionDetail detail : consumption.getConsumptionDetails()) {
                if (detail.getId().equals(consumptionDetailForm.getId())) {
                    detail.setAmount(consumptionDetailForm.getAmount());
                    break;
                }
            }
        } else {
            ProductAncestor product = this.productAndRecipeDAO.findById(consumptionDetailForm.getProduct().getId());
            assert (product != null);

            ConsumptionDetail consumptionDetail = new ConsumptionDetail(consumptionDetailForm.getType().getType());
            consumptionDetail.setProduct(product);
            consumptionDetail.setAmount(consumptionDetailForm.getAmount());
            consumption.addConsumptionDetail(consumptionDetail);
        }

        this.consumptionService.update(consumption);
        status.setComplete();

        return "redirect:list?date=" + Utils.URL_DATE_FORMATTER.format(consumption.getDate());
    }

    /**
     * Remove a product from the consumption
     *
     * @param key is the Consumption identifier
     * @param detailKey is the detail identifier
     * @param model
     * @return
     */
    @RequestMapping(value = "/removedetail", method = RequestMethod.GET)
    public String processRemoveDetail (@RequestParam("key") Integer key, @RequestParam("detailKey") Integer detailKey, Model model) {
        Consumption consumption = this.consumptionService.findByIdWithDetails(key);
        Set<ConsumptionDetail> details = consumption.getConsumptionDetails();
        ConsumptionDetail removedDetail = null;
        for (ConsumptionDetail detail : details) {
            if (detail.getId().equals(detailKey)) {
                removedDetail = detail;
            }
        }

        if (removedDetail != null) {
            consumption.removeConsumptionDetail(removedDetail);
            this.consumptionService.update(consumption);
        }

        return "redirect:/consumptions/list?date=" + Utils.URL_DATE_FORMATTER.format(consumption.getDate());
    }

    /**
     * Remove a product from the consumption
     *
     * @param key is the Consumption identifier
     * @param detailKey is the detail identifier
     * @param model
     * @return
     */
    @RequestMapping(value = "/editProduct", method = RequestMethod.POST)
    public String processEditProduct (@RequestParam("key") Integer key, @RequestParam("detailKey") Integer detailKey, @Validated ConsumptionDetailForm detailForm, BindingResult result, Model model, final SessionStatus status) {
        Consumption consumption = this.consumptionService.findByIdWithDetails(key);
        Set<ConsumptionDetail> details = consumption.getConsumptionDetails();
        ConsumptionDetail editedDetail = null;
        for (ConsumptionDetail detail : details) {
            if (detail.getId().equals(detailKey)) {
                editedDetail = detail;
                break;
            }
        }

        if (editedDetail != null) {
            editedDetail.setAmount(detailForm.getAmount());
            this.consumptionService.update(consumption);
        }

        status.setComplete();
        return "redirect:/consumptions/list?date=" + Utils.URL_DATE_FORMATTER.format(consumption.getDate());
    }

}
