package be.fortemaison.easyfit.service;

import be.fortemaison.easyfit.dao.IProductDAO;
import be.fortemaison.easyfit.dao.IRecipeDAO;
import be.fortemaison.easyfit.model.Recipe;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 3/02/13
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
@Service
public class RecipeServiceImpl implements IRecipeService {

    private IRecipeDAO recipeDAO;

    private IProductDAO productDAO;

    public IProductDAO getProductDAO () {
        return productDAO;
    }

    public void setProductDAO (IProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public IRecipeDAO getRecipeDAO () {
        return recipeDAO;
    }

    public void setRecipeDAO (IRecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    @Transactional(readOnly = true)
    public Recipe findById (Integer id) {
        return this.recipeDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public Recipe findByIdWithDetails (Integer id) {
        return this.recipeDAO.findByIdWithDetails(id);
    }

    @Transactional(readOnly = true)
    public List<Recipe> findByName (String name) {
        return this.recipeDAO.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Recipe> findByNameWithDetails (String name) {
        return this.recipeDAO.findByNameWithDetails(name);
    }

    @Transactional(readOnly = true)
    public List<Recipe> findFavorites () {
        return this.recipeDAO.findFavorites();
    }

    @Transactional(readOnly = true)
    public List<Recipe> findFavoritesWithDetails () {
        return this.recipeDAO.findFavoritesWithDetails();
    }

    @Transactional(readOnly = true)
    public List<Recipe> findAll () {
        return this.recipeDAO.findAll();
    }

    @Transactional(readOnly = true)
    public List<Recipe> findAllWithdetails () {
        return this.recipeDAO.findAllWithDetails();
    }

    @Transactional
    public void insert (Recipe recipe) {
        this.recipeDAO.insert(recipe);
    }

    @Transactional
    public void update (Recipe recipe) {
        if (recipe.getId() == null) {
            this.recipeDAO.insert(recipe);
        } else {
            this.recipeDAO.update(recipe);
        }
    }

    @Transactional
    public void delete (Recipe recipe) {
        this.recipeDAO.delete(recipe);
    }

    @Transactional
    public void updatePavorite (Integer id, boolean favorite) {
        Recipe recipe = findById(id);
        recipe.setFavorite(favorite);
        update(recipe);
    }

}
