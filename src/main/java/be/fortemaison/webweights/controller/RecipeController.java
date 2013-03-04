package be.fortemaison.webweights.controller;

import be.fortemaison.webweights.form.ProductForm;
import be.fortemaison.webweights.form.RecipeDetailForm;
import be.fortemaison.webweights.form.RecipeForm;
import be.fortemaison.webweights.model.Product;
import be.fortemaison.webweights.model.Recipe;
import be.fortemaison.webweights.model.RecipeDetail;
import be.fortemaison.webweights.model.Unit;
import be.fortemaison.webweights.service.IProductService;
import be.fortemaison.webweights.service.IRecipeService;
import be.fortemaison.webweights.service.IUnitService;
import be.fortemaison.webweights.util.IConstants;
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
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/recipes")
@SessionAttributes({"recipeForm", "recipeDetailForm"})
public class RecipeController {

    private IRecipeService recipeService;

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

    @Autowired
    public void setRecipeService (IRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String prepareList (Model model) {
        List<Recipe> recipes = this.recipeService.findAll();
        List<RecipeForm> forms = new ArrayList<RecipeForm>(recipes.size());
        for (Recipe recipe : recipes) {
            forms.add(new RecipeForm(recipe));
        }
        model.addAttribute("recipes", forms);
        return "recipes/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String prepareEdit (@RequestParam(value = "key", required = false) Integer key, ModelMap modelMap) {
        RecipeForm recipeForm = new RecipeForm();
        if (key != null) {
            // update
            Recipe recipe = this.recipeService.findByIdWithDetails(key);
            recipeForm = new RecipeForm(recipe);

            for (RecipeDetail link : recipe.getRecipeDetails()) {
                RecipeDetailForm linkForm = new RecipeDetailForm(link.getId(), new ProductForm(link.getProduct()), link.getAmount());
                recipeForm.addProduct(linkForm);
            }
        }
        modelMap.addAttribute("recipeForm", recipeForm);

        //  store all units for combo
        List<Unit> units = unitService.findAll();
        Map<String, String> allUnits = new LinkedHashMap<String, String>();
        for (Unit unit : units) {
            allUnits.put("" + unit.getId(), unit.getName());
        }
        modelMap.addAttribute("allUnits", allUnits);

        return "recipes/edit";
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.GET, params = "key")
    public String processFavorite (Integer key, Model model) {
        Recipe recipe = this.recipeService.findById(key);
        if (recipe != null) {
            recipe.setFavorite(!recipe.isFavorite());
            this.recipeService.update(recipe);
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
        Recipe recipe = this.recipeService.findById(key);
        if (recipe != null) {
            this.recipeService.delete(recipe);
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
    public String prepareEditDetail (@RequestParam("key") Integer key, @RequestParam("detailKey") Integer detailKey, ModelMap modelMap, final HttpServletResponse response) {
        Recipe recipe = this.recipeService.findByIdWithDetails(key);
        if (recipe != null) {
            RecipeDetail editedDetail = null;
            for (RecipeDetail detail : recipe.getRecipeDetails()) {
                if (detail.getId().equals(detailKey)) {
                    editedDetail = detail;
                }
            }

            RecipeDetailForm recipeDetailForm = (RecipeDetailForm) modelMap.get("recipeDetailForm");
            if (recipeDetailForm == null) {
                recipeDetailForm = new RecipeDetailForm(editedDetail);
            }

            recipeDetailForm.setRecipeId(key);
            modelMap.addAttribute("recipeDetailForm", recipeDetailForm);
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
    public String processAddDetail1 (@RequestParam(value = "key", required = false) Integer key, @RequestParam(value = "queryName", required = false) String queryName, @Validated RecipeForm recipeForm, BindingResult result, ModelMap modelMap) {

        if (key == null) {
            //  recipe yet to be inserted
            if (recipeForm != null) {
                Recipe recipe = insertRecipe(recipeForm);
                key = recipe.getId();
            }
        }

        RecipeDetailForm recipeDetailForm = (RecipeDetailForm) modelMap.get("recipeDetailForm");
        if (recipeDetailForm == null) {
            recipeDetailForm = new RecipeDetailForm();
            recipeDetailForm.setRecipeId(key);
            modelMap.addAttribute("recipeDetailForm", recipeDetailForm);
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
    public String processAddDetail2 (@RequestParam("productKey") Integer productKey, @ModelAttribute("recipeDetailForm") RecipeDetailForm recipeDetailForm, final Errors errors, final HttpServletResponse response) {
        Product product = this.productService.findById(productKey);
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
    public String processAddDetail3 (@ModelAttribute("recipeDetailForm") RecipeDetailForm recipeDetailForm, final Errors errors, final SessionStatus status) {
        Recipe recipe = this.recipeService.findByIdWithDetails(recipeDetailForm.getRecipeId());
        Product product = this.productService.findById(recipeDetailForm.getProduct().getId());
        assert (recipe != null);
        assert (product != null);

        recipe.addRecipeDetail(new RecipeDetail(null, recipe, product, recipeDetailForm.getAmount()));

        this.recipeService.update(recipe);
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
    public String processRemoveDetail (@RequestParam("key") Integer key, @RequestParam("detailKey") Integer detailKey, Model model) {
        Recipe recipe = this.recipeService.findByIdWithDetails(key);
        Set<RecipeDetail> details = recipe.getRecipeDetails();
        RecipeDetail removedDetail = null;
        for (RecipeDetail detail : details) {
            if (detail.getId().equals(detailKey)) {
                removedDetail = detail;
            }
        }

        if (recipe != null && removedDetail != null) {
            recipe.removeRecipeDetail(removedDetail);
            this.recipeService.update(recipe);
            return "redirect:/recipes/edit?key=" + key;
        }

        return "recipes/list";
    }

    /**
     * Remove a product from the recipe
     *
     * @param key is the Recipe identifier
     * @param detailKey is the detail identifier
     * @param model
     * @return
     */
    @RequestMapping(value = "/editProduct", method = RequestMethod.POST)
    public String processEditProduct (@RequestParam("key") Integer key, @RequestParam("detailKey") Integer detailKey, @Validated RecipeDetailForm detailForm, BindingResult result, Model model) {
        Recipe recipe = this.recipeService.findByIdWithDetails(key);
        Set<RecipeDetail> details = recipe.getRecipeDetails();
        RecipeDetail editedDetail = null;
        for (RecipeDetail detail : details) {
            if (detail.getId().equals(detailKey)) {
                editedDetail = detail;
            }
        }

        if (recipe != null && editedDetail != null) {
            editedDetail.setAmount(detailForm.getAmount());
            this.recipeService.update(recipe);
            return "redirect:/recipes/update?key=" + key;
        }

        return "recipes/list";
    }

    /**
     * @param recipeForm
     * @return
     */
    private Recipe updateRecipe (RecipeForm recipeForm) {
        Recipe recipe = this.recipeService.findByIdWithDetails(recipeForm.getId());
        assert (recipe != null);

        recipe.setName(recipeForm.getName());
        recipe.setDescription(recipeForm.getDescription());
        recipe.setFavorite(recipeForm.isFavorite());
        Unit unit = unitService.findById(Integer.decode(recipeForm.getUnit()));
        recipe.setUnit(unit);
        recipe.setAmount(recipeForm.getAmount());
        recipe.updatePoints();

        this.recipeService.update(recipe);
        return recipe;
    }

    /**
     * @param recipeForm
     * @return
     */
    private Recipe insertRecipe (RecipeForm recipeForm) {
        Recipe recipe = new Recipe(recipeForm.getName(), recipeForm.isFavorite());
        recipe.setDescription(recipeForm.getDescription());
        Unit unit = unitService.findById(Integer.decode(recipeForm.getUnit()));
        recipe.setUnit(unit);
        recipe.setAmount(recipeForm.getAmount());

        for (RecipeDetailForm linkForm : recipeForm.getRecipeDetailForms()) {
            Product product = this.productService.findById(linkForm.getProduct().getId());
            if (product != null) {
                recipe.addRecipeDetail(new RecipeDetail(null, recipe, product, linkForm.getAmount()));
            }
        }

        this.recipeService.insert(recipe);
        return recipe;
    }

}
