package be.fortemaison.webweights.controller;

import be.fortemaison.webweights.dao.IProductCategoryDAO;
import be.fortemaison.webweights.form.ProductCategoryForm;
import be.fortemaison.webweights.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 26/01/13
 * Time: 23:36
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/categories")
public class ProductCategoryController {

    private IProductCategoryDAO categoryDAO;

    @Autowired
    public void setProductCategoryDAO (IProductCategoryDAO productCategoryDAO) {
        this.categoryDAO = productCategoryDAO;
    }

    /**
     * List view with all categorys
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String prepareList (Model model) {
        model.addAttribute("categories", this.categoryDAO.findAll());
        return "categories/list";
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
            model.addAttribute(new ProductCategoryForm());
            return "categories/edit";
        } else {
            ProductCategory category = this.categoryDAO.findById(key);
            if (category != null) {
                model.addAttribute(new ProductCategoryForm(category));
                return "categories/edit";
            }
        }

        return "categories/list";
    }

    /**
     * Process update form input, update record in db
     *
     * @param
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String processUpdate (@Validated ProductCategoryForm categoryForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "categories/edit";
        }

        this.categoryDAO.update(categoryForm.toProductCategory());
        return "redirect:/categories/list";
    }

    /**
     * Delete scale and redirect to list
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String processDelete (@RequestParam(value = "key") Integer key) {
        ProductCategory category = this.categoryDAO.findById(key);
        if (category != null) {
            this.categoryDAO.delete(category);
            return "redirect:/categories/list";
        }

        return "categories/list";
    }

}
