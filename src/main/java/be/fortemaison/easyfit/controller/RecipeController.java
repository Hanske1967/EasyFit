package be.fortemaison.easyfit.controller;

import be.fortemaison.easyfit.dao.IProductAndRecipeDAO;
import be.fortemaison.easyfit.dao.IProductCategoryDAO;
import be.fortemaison.easyfit.dao.IRecipeDAO;
import be.fortemaison.easyfit.dao.IUnitDAO;
import be.fortemaison.easyfit.form.ProductForm;
import be.fortemaison.easyfit.form.RecipeDetailForm;
import be.fortemaison.easyfit.form.RecipeForm;
import be.fortemaison.easyfit.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 26/01/13
 * Time: 23:36
 * To change this template use File | ettings | File Templates.
 */
@Controller
@RequestMapping("/recipes")
@SessionAttributes("recipeDetailForm")
public class RecipeController {

    public static final String RECIPE_DETAIL_FORM = "recipeDetailForm";

    @Autowired
    private IRecipeDAO recipeDAO;

    @Autowired
    private IProductAndRecipeDAO productAndRecipeDAO;

    @Autowired
    private IUnitDAO unitDAO;

    @Autowired
    private IProductCategoryDAO productCategoryDAO;

    @Autowired
    private IProductCategoryDAO categoryDAO;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String prepareList (@RequestParam(value = "page", required = false) Integer pageIndex, Model model) {
        Page<Recipe> recipes = this.recipeDAO.findAll(pageIndex);
        List<RecipeForm> forms = new ArrayList<RecipeForm>(recipes.size());
        for (Recipe recipe : recipes) {
            forms.add(new RecipeForm(recipe));
        }
        model.addAttribute("recipes", forms);

        model.addAttribute("currentPage", recipes.getCurrentPage());
        model.addAttribute("pageCount", recipes.getPageCount());

        return "recipes/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String prepareEdit (@RequestParam(value = "key", required = false) Integer key, ModelMap modelMap) {
        RecipeForm recipeForm = new RecipeForm();
        if (key != null) {
            // update
            Recipe recipe = this.recipeDAO.findByIdWithDetails(key);
            recipeForm = new RecipeForm(recipe);

            for (RecipeDetail link : recipe.getRecipeDetails()) {
                RecipeDetailForm linkForm = new RecipeDetailForm(link);
                recipeForm.addProduct(linkForm);
            }
        }
        modelMap.addAttribute("recipeForm", recipeForm);

        //  store all units for combo
        List<Unit> units = unitDAO.findAll();
        Map<Integer, String> allUnits = new LinkedHashMap<Integer, String>();
        for (Unit unit : units) {
            allUnits.put(unit.getId(), unit.getName());
        }
        modelMap.addAttribute("allUnits", allUnits);

        List<ProductCategory> categories = this.categoryDAO.findAll();
        Map<Integer, String> categoryForms = new LinkedHashMap<Integer, String>();
        for (ProductCategory category : categories) {
            categoryForms.put(category.getId(), category.getName());
        }
        modelMap.addAttribute("allCategories", categoryForms);

        return "recipes/edit";
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.GET, params = "key")
    public String processFavorite (Integer key, Model model) {
        Recipe recipe = this.recipeDAO.findById(key);
        if (recipe != null) {
            recipe.setShared(!recipe.isShared());
            this.recipeDAO.update(recipe);
            return "redirect:/recipes/list";
        }

        return "recipes/list";
    }

    /**
     * Update the recipe signaletic without updating product links
     *
     * @param recipeForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String processUpdate (@Validated RecipeForm recipeForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "recipes/edit";
        }

        if (recipeForm.getId() == null) {
            insertRecipe(recipeForm);
        } else {
            updateRecipe(recipeForm);
        }
        return "redirect:/recipes/list";
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
        Recipe recipe = this.recipeDAO.findById(key);
        if (recipe != null) {
            this.recipeDAO.delete(recipe);
            return "redirect:/recipes/list";
        }

        return "recipes/list";
    }

    /**
     * @param key
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/editdetail", method = RequestMethod.GET)
    public String prepareEditDetail (@RequestParam("key") Integer key, @RequestParam("detailKey") Integer detailKey, ModelMap modelMap, final SessionStatus status) {

        Recipe recipe = this.recipeDAO.findByIdWithDetails(key);
        if (recipe != null) {
            RecipeDetail editedDetail = null;
            for (RecipeDetail detail : recipe.getRecipeDetails()) {
                if (detail.getId().equals(detailKey)) {
                    editedDetail = detail;
                }
            }

            RecipeDetailForm recipeDetailForm = new RecipeDetailForm(editedDetail);
            recipeDetailForm.setRecipeId(key);
            modelMap.addAttribute(RECIPE_DETAIL_FORM, recipeDetailForm);

            return "/recipes/editdetail";
        }

        return "recipes/list";
    }

    /**
     * Initial page of AddDetail wizard : choose product
     *
     * @param key is the recipeKey
     * @return
     */

    @RequestMapping(value = "/adddetail1", method = RequestMethod.POST)
    public String processAddDetail1 (@RequestParam(value = "key", required = false) Integer key,
                                     @RequestParam(value = "queryName", required = false) String queryName,
                                     @RequestParam(value = "category", required = false) Integer categoryId,
                                     @RequestParam(value = "page", required = false) Integer pageIndex,
                                     @Validated RecipeForm recipeForm,
                                     BindingResult result, ModelMap modelMap) {

        RecipeDetailForm recipeDetailForm = (RecipeDetailForm) modelMap.get(RECIPE_DETAIL_FORM);
        if (recipeDetailForm == null) {
            if (key == null) {
                //  recipe yet to be inserted
                if (recipeForm != null) {
                    Recipe recipe = insertRecipe(recipeForm);
                    key = recipe.getId();
                }
            }

            recipeDetailForm = new RecipeDetailForm();
            recipeDetailForm.setRecipeId(key);
            modelMap.addAttribute(RECIPE_DETAIL_FORM, recipeDetailForm);
        }

        // TODO Move this code in ControllerHelper
        //  all product categories for filtering
        ProductCategory cat = null;
        List<ProductCategory> categories = this.productCategoryDAO.findAll();
        Map<Integer, String> categoryForms = new LinkedHashMap<Integer, String>();
        for (ProductCategory category : categories) {
            categoryForms.put(category.getId(), category.getName());
            if (categoryId != null && category.getId().equals(categoryId)) {
                cat = category;
            }
        }
        modelMap.addAttribute("allCategories", categoryForms);

        //  Find product
        Page<ProductAncestor> products = this.productAndRecipeDAO.findByNameAndCategory(queryName, cat, pageIndex);
        List<ProductForm> forms = new ArrayList<ProductForm>(products.size());
        for (ProductAncestor product : products) {
            forms.add(new ProductForm(product));
        }

        modelMap.addAttribute("products", forms);
        modelMap.addAttribute("queryName", queryName);

        modelMap.addAttribute("currentPage", products.getCurrentPage());
        modelMap.addAttribute("pageCount", products.getPageCount());

        return "products/findproduct";
    }

    /**
     * Second page of AddDetail wizard
     *
     * @return
     */
    @RequestMapping(value = "/adddetail2", method = RequestMethod.POST)
    public String processAddDetail2 (@RequestParam("productKey") Integer
                                             productKey, @ModelAttribute(RECIPE_DETAIL_FORM) RecipeDetailForm recipeDetailForm, final Errors errors,
                                     final HttpServletResponse response) {
        ProductAncestor product = this.productAndRecipeDAO.findById(productKey);
        assert (product != null);
        recipeDetailForm.setProduct(new ProductForm(product));

        return "/recipes/editdetail";
    }

    /**
     * Second page of AddDetail wizard
     *
     * @return
     */
    @RequestMapping(value = "/adddetail3", method = RequestMethod.POST)
    public String processAddDetail3 (@ModelAttribute(RECIPE_DETAIL_FORM) RecipeDetailForm recipeDetailForm,
                                     final Errors errors, final SessionStatus status) {

        Recipe recipe = this.recipeDAO.findByIdWithDetails(recipeDetailForm.getRecipeId());
        ProductAncestor product = this.productAndRecipeDAO.findById(recipeDetailForm.getProduct().getId());
        assert (recipe != null);
        assert (product != null);

        if (recipeDetailForm.getId() == null) {
            //  new product added
            recipe.addRecipeDetail(new RecipeDetail(null, recipe, product, recipeDetailForm.getAmount()));
        } else {
            //  amount updated
            for (RecipeDetail detail : recipe.getRecipeDetails()) {
                if (detail.getId() == recipeDetailForm.getId()) {
                    detail.setAmount(recipeDetailForm.getAmount());
                }
            }
        }
        this.recipeDAO.update(recipe);


        status.setComplete();

        return "redirect:/recipes/edit?key=" + recipe.getId();
    }

    /**
     * Remove a product from the recipe
     *
     * @param key is the Recipe identifier
     * @param detailKey is the detail identifier
     * @param model
     * @return
     */
    @RequestMapping(value = "/removedetail", method = RequestMethod.GET)
    public String processRemoveDetail (@RequestParam("key") Integer key, @RequestParam("detailKey") Integer
            detailKey, Model model) {
        Recipe recipe = this.recipeDAO.findByIdWithDetails(key);
        Set<RecipeDetail> details = recipe.getRecipeDetails();
        RecipeDetail removedDetail = null;
        for (RecipeDetail detail : details) {
            if (detail.getId().equals(detailKey)) {
                removedDetail = detail;
            }
        }

        if (recipe != null && removedDetail != null) {
            recipe.removeRecipeDetail(removedDetail);
            this.recipeDAO.update(recipe);
            return "redirect:/recipes/edit?key=" + key;
        }

        return "recipes/list";
    }

    /**
     * @param recipeForm
     * @return
     */

    private Recipe updateRecipe (RecipeForm recipeForm) {
        Recipe recipe = this.recipeDAO.findByIdWithDetails(recipeForm.getId());
        assert (recipe != null);

        recipe.setName(recipeForm.getName());
        recipe.setDescription(recipeForm.getDescription());
        recipe.setShared(recipeForm.getShared());
        recipe.setAmount(recipeForm.getAmount());

        Unit unit = unitDAO.findById(recipeForm.getUnitId());
        recipe.setUnit(unit);

        ProductCategory category = this.productCategoryDAO.findById(recipeForm.getCategoryId());
        recipe.setCategory(category);

        this.recipeDAO.update(recipe);
        return recipe;
    }

    /**
     * @param recipeForm
     * @return
     */
    private Recipe insertRecipe (RecipeForm recipeForm) {
        Recipe recipe = new Recipe(recipeForm.getName(), recipeForm.getShared());
        recipe.setDescription(recipeForm.getDescription());
        recipe.setAmount(recipeForm.getAmount());

        Unit unit = unitDAO.findById(recipeForm.getUnitId());
        recipe.setUnit(unit);

        ProductCategory category = this.productCategoryDAO.findById(recipeForm.getCategoryId());
        recipe.setCategory(category);

        for (RecipeDetailForm linkForm : recipeForm.getRecipeDetailForms()) {
            ProductAncestor product = this.productAndRecipeDAO.findById(linkForm.getProduct().getId());
            if (product != null) {
                recipe.addRecipeDetail(new RecipeDetail(null, recipe, product, linkForm.getAmount()));
            }
        }

        this.recipeDAO.insert(recipe);
        return recipe;
    }

}
