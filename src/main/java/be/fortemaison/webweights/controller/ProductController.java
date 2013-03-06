package be.fortemaison.webweights.controller;

import be.fortemaison.webweights.form.ProductForm;
import be.fortemaison.webweights.model.Product;
import be.fortemaison.webweights.model.Unit;
import be.fortemaison.webweights.service.IProductService;
import be.fortemaison.webweights.service.IUnitService;
import be.fortemaison.webweights.util.IConstants;
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

    private IProductService productService;

    private IUnitService unitService;

    @Autowired
    public void setUnitService (IUnitService unitService) {
        this.unitService = unitService;
    }

    @Autowired
    public void setProductService (IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String prepareList (@RequestParam(value = "queryName", required = false) String queryName, Model model) {
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
        model.addAttribute("products", forms);
        model.addAttribute("queryName", queryName);
        return "products/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String prepareNew (Model model) {
        ProductForm productForm = new ProductForm();
        model.addAttribute("productForm", productForm);

        //  store all units for combo
        List<Unit> units = unitService.findAll();
        Map<Integer, String> allUnits = new LinkedHashMap<Integer, String>();
        for (Unit unit : units) {
            allUnits.put(unit.getId(), unit.getName());
        }
        model.addAttribute("allUnits", allUnits);

        return "products/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String prepareUpdate (Integer key, Model model) {
        Product product = this.productService.findById(key);
        ProductForm productForm = new ProductForm(product);
        model.addAttribute("productForm", productForm);

        //  store all units for combo
        List<Unit> units = unitService.findAll();
        Map<String, String> allUnits = new LinkedHashMap<String, String>();
        for (Unit unit : units) {
            allUnits.put("" + unit.getId(), unit.getName());
        }
        model.addAttribute("allUnits", allUnits);

        return "products/edit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processNew (@Validated ProductForm formProduct, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "products/edit";
        }

        //  Unit returned by formProduct contains only the ID in the name property of Unit instance.
        Unit unit = unitService.findById(formProduct.getUnitId());
        Product product = (Product) formProduct.getProduct();
        product.setUnit(unit);

        this.productService.insert(product);
        return "redirect:/products/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdate (@Validated ProductForm formProduct, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "products/edit";
        }

        //  Unit returned by formProduct contains only the ID in the name property of Unit instance.
        Unit unit = unitService.findById(formProduct.getUnitId());
        Product product = (Product) formProduct.getProduct();
        product.setUnit(unit);

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
