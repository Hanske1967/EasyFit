package be.fortemaison.webweights.controller;

import be.fortemaison.webweights.dao.IProductCategoryDAO;
import be.fortemaison.webweights.form.ProductForm;
import be.fortemaison.webweights.model.Product;
import be.fortemaison.webweights.model.ProductCategory;
import be.fortemaison.webweights.model.Unit;
import be.fortemaison.webweights.service.IProductService;
import be.fortemaison.webweights.service.IUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 26/01/13
 * Time: 23:36
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/products")
public class ProductController {


    private IProductCategoryDAO categoryDAO;

    private IProductService productService;

    private IUnitService unitService;

    @Autowired
    public void setCategoryDAO (IProductCategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Autowired
    public void setUnitService (IUnitService unitService) {
        this.unitService = unitService;
    }

    @Autowired
    public void setProductService (IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String prepareList (@RequestParam(value = "queryName", required = false) String queryName, @RequestParam(value = "category", required = false) Integer categoryId, Model model) {

        ProductCategory category = null;

        List<ProductCategory> categories = this.categoryDAO.findAll();
        Map<Integer, String> categoryForms = new LinkedHashMap<Integer, String>();
        for (ProductCategory cat : categories) {
            categoryForms.put(cat.getId(), cat.getName());
            if (categoryId != null && cat.getId().equals(categoryId)) {
                category = cat;
            }
        }
        model.addAttribute("allCategories", categoryForms);

        List<Product> products = this.productService.findByNameAndCategory(queryName, category);
        List<ProductForm> forms = new ArrayList<ProductForm>(products.size());
        for (Product product : products) {
            forms.add(new ProductForm(product));
        }
        model.addAttribute("products", forms);

        model.addAttribute("queryName", queryName);
        model.addAttribute("category", category);
        return "products/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String prepareEdit (@RequestParam(value = "key", required = false) Integer key, Model model) {
        if (key == null) {
            ProductForm productForm = new ProductForm();
            model.addAttribute("productForm", productForm);
        } else {
            Product product = this.productService.findById(key);
            ProductForm productForm = new ProductForm(product);
            model.addAttribute("productForm", productForm);
        }

        // TODO Move this code in ControllerHelper
        //  store all units for combo
        List<Unit> units = unitService.findAll();
        Map<Integer, String> allUnits = new LinkedHashMap<Integer, String>();
        for (Unit unit : units) {
            allUnits.put(unit.getId(), unit.getName());
        }
        model.addAttribute("allUnits", allUnits);

        List<ProductCategory> categories = this.categoryDAO.findAll();
        Map<Integer, String> categoryForms = new LinkedHashMap<Integer, String>();
        for (ProductCategory category : categories) {
            categoryForms.put(category.getId(), category.getName());
        }
        model.addAttribute("allCategories", categoryForms);

        return "products/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String processUpdate (@Validated ProductForm formProduct, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "products/edit";
        }

        Product product = (Product) formProduct.getProduct();

        //  Unit returned by formProduct contains only the ID in the name property of Unit instance.
        Unit unit = unitService.findById(formProduct.getUnitId());
        product.setUnit(unit);

        //  Category returned by formProduct contains only the ID in the name property of Category instance.
        ProductCategory category = this.categoryDAO.findById(formProduct.getCategoryId());
        product.setCategory(category);

        this.productService.update(product);
        return "redirect:/products/list";
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.GET, params = "key")
    public String processFavorite (Integer key, Model model) {
        Product product = this.productService.findById(key);
        if (product != null) {
            product.setFavorite(!product.isFavorite());
            this.productService.update(product);
            return "redirect:/products/list";
        }

        return "products/list";
    }

    /**
     * Delete product and redirect to list
     *
     * @param key
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET, params = "key")
    public String processDelete (Integer key, Model model) {
        Product product = this.productService.findById(key);
        if (product != null) {
            this.productService.delete(product);
            return "redirect:/products/list";
        }

        return "products/list";
    }
}
