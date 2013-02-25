package be.fortemaison.webweights.controller;

import be.fortemaison.webweights.form.ConsumptionDetailForm;
import be.fortemaison.webweights.form.ConsumptionForm;
import be.fortemaison.webweights.form.ProductForm;
import be.fortemaison.webweights.model.Consumption;
import be.fortemaison.webweights.model.ConsumptionDetail;
import be.fortemaison.webweights.model.ConsumptionDetailType;
import be.fortemaison.webweights.model.Product;
import be.fortemaison.webweights.service.IConsumptionService;
import be.fortemaison.webweights.service.IProductService;
import be.fortemaison.webweights.util.IConstants;
import be.fortemaison.webweights.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 26/01/13
 * Time: 23:36
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/consumptions")
@SessionAttributes(value = "consumptionDetailForm")
public class ConsumptionController {

    private IConsumptionService consumptionService;

    private IProductService productService;

    private MessageSource messageSource;

    @Autowired
    public void setMessageSource (MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    public void setProductService (IProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setConsumptionService (IConsumptionService consumptionService) {
        this.consumptionService = consumptionService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String prepareList (@RequestParam(value = "date", required = false) String date, Model model, HttpServletRequest request) {
        Date aDate = null;
        try {
            aDate = date == null ? new Date() : Utils.DATE_FORMATTER.parse(date);
        } catch (ParseException e) {
            System.err.println("Date not recognized");
            aDate = new Date();
        }

        Consumption consumption = this.consumptionService.findByDateWithDetails(aDate);
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

        model.addAttribute("consumptionForm", consumptionForm);

        int maxTitles = ConsumptionDetailType.ALL.length;
        String[] sectionTitles = new String[maxTitles];
        String prefix = "ConsumptionDetailType.";
        for (int i = 0; i < maxTitles; i++) {
            sectionTitles[i] = this.messageSource.getMessage(prefix + (i + 1), null, new RequestContext(request).getLocale());
        }
        consumptionForm.setConsumptionDetailHeaders(sectionTitles);

        return "consumptions/list";
    }

    /**
     * Update the consumption signaletic without updating product links
     *
     * @param consumptionForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdate (@Validated ConsumptionForm consumptionForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "consumptions/edit";
        }
//  TODO complete here
        return "redirect:/consumptions/list";
    }

    /**
     * Delete product and all its product links, and redirect to list
     *
     * @param key
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET, params = "key")
    public String processDelete (Integer key, Model model) {
        Consumption consumption = this.consumptionService.findById(key);
        if (consumption != null) {
            this.consumptionService.delete(consumption);
            return "redirect:/consumptions/list";
        }

        return "consumptions/list";
    }

    /**
     * @param key
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/editdetail", method = RequestMethod.GET)
    public String prepareEditDetail (@RequestParam("key") Integer key, @RequestParam("detailKey") Integer detailKey, ModelMap modelMap, final HttpServletResponse response) {
        Consumption consumption = this.consumptionService.findByIdWithDetails(key);
        if (consumption != null) {
            ConsumptionDetail editedDetail = null;
            for (ConsumptionDetail detail : consumption.getConsumptionDetails()) {
                if (detail.getId().equals(detailKey)) {
                    editedDetail = detail;
                }
            }

            ConsumptionDetailForm consumptionDetailForm = (ConsumptionDetailForm) modelMap.get("consumptionDetailForm");
            if (consumptionDetailForm == null) {
                consumptionDetailForm = new ConsumptionDetailForm(editedDetail);
            }

            consumptionDetailForm.setConsumptionId(key);
            modelMap.addAttribute("consumptionDetailForm", consumptionDetailForm);
            return "/consumptions/editdetail";
        }

        return "consumptions/list";
    }

    /**
     * Initial page of AddDetail wizard : choose product
     *
     * @param key is the consumptionKey
     * @return
     */

    @RequestMapping(value = "/adddetail1", method = RequestMethod.POST)
    public String processAddDetail1 (@RequestParam(value = "key", required = false) Integer key, @RequestParam(value = "date", required = false) String date, @RequestParam(value = "consumptionDetailType", required = true) int consumptionDetailType, @RequestParam(value = "queryName", required = false) String queryName, ModelMap modelMap) {
        ConsumptionDetailForm consumptionDetailForm = (ConsumptionDetailForm) modelMap.get("consumptionDetailForm");
        if (consumptionDetailForm == null) {
            consumptionDetailForm = new ConsumptionDetailForm();
            consumptionDetailForm.setConsumptionId(key);

            Date aDate = null;
            try {
                aDate = date == null ? new Date() : Utils.DATE_FORMATTER.parse(date);
            } catch (ParseException e) {
                System.err.println("Date not recognized");
                aDate = new Date();
            }

            consumptionDetailForm.setDate(aDate);
            consumptionDetailForm.setType(ConsumptionDetailType.get(consumptionDetailType));
            modelMap.addAttribute("consumptionDetailForm", consumptionDetailForm);
        }

        //  Find product
        List<Product> products = null;
        if (queryName == null || queryName.isEmpty()) {
            products = this.productService.findAll();
        } else {
            StringBuilder sb = new StringBuilder(queryName.length() + 2);
            sb.append(IConstants.PROCENT);
            sb.append(queryName);
            sb.append(IConstants.PROCENT);
            products = this.productService.findByName(sb.toString());
        }
        List<ProductForm> forms = new ArrayList<ProductForm>(products.size());
        for (Product product : products) {
            forms.add(new ProductForm(product));
        }
        modelMap.addAttribute("products", forms);
        modelMap.addAttribute("queryName", queryName);

        return "products/findproduct";
    }

    /**
     * Second page of AddDetail wizard
     *
     * @return
     */
    @RequestMapping(value = "/adddetail2", method = RequestMethod.POST)
    public String processAddDetail2 (@RequestParam("productKey") Integer productKey, @ModelAttribute("consumptionDetailForm") ConsumptionDetailForm consumptionDetailForm, final Errors errors, final HttpServletResponse response) {
        Product product = this.productService.findById(productKey);
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
    public String processAddDetail3 (@ModelAttribute("consumptionDetailForm") ConsumptionDetailForm consumptionDetailForm, final Errors errors, final SessionStatus status) {
        Integer conumptionId = consumptionDetailForm.getConsumptionId();
        Consumption consumption = null;
        if (conumptionId == null) {
            consumption = new Consumption(consumptionDetailForm.getDate());
            this.consumptionService.update(consumption);
        } else {
            consumption = this.consumptionService.findByIdWithDetails(conumptionId);
        }
        Product product = this.productService.findById(consumptionDetailForm.getProduct().getId());
        assert (product != null);

        ConsumptionDetail consumptionDetail = new ConsumptionDetail(consumptionDetailForm.getType().getType());
        consumptionDetail.setProduct(product);
        consumptionDetail.setAmount(consumptionDetailForm.getAmount());
        consumption.addConsumptionDetail(consumptionDetail);

        this.consumptionService.update(consumption);
        status.setComplete();

        return "redirect:/consumptions/list?date=" + Utils.DATE_FORMATTER.format(consumption.getDate());
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

        if (consumption != null && removedDetail != null) {
            consumption.removeConsumptionDetail(removedDetail);
            this.consumptionService.update(consumption);
        }

        return "redirect:/consumptions/list?date=" + Utils.DATE_FORMATTER.format(consumption.getDate());
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
    public String processEditProduct (@RequestParam("key") Integer key, @RequestParam("detailKey") Integer detailKey, @Validated ConsumptionDetailForm detailForm, BindingResult result, Model model) {
        Consumption consumption = this.consumptionService.findByIdWithDetails(key);
        Set<ConsumptionDetail> details = consumption.getConsumptionDetails();
        ConsumptionDetail editedDetail = null;
        for (ConsumptionDetail detail : details) {
            if (detail.getId().equals(detailKey)) {
                editedDetail = detail;
            }
        }

        if (consumption != null && editedDetail != null) {
            editedDetail.setAmount(detailForm.getAmount());
            this.consumptionService.update(consumption);
        }

        return "redirect:/consumptions/list?date=" + Utils.DATE_FORMATTER.format(consumption.getDate());
    }

}
