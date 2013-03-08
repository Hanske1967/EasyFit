package be.fortemaison.webweights.form;

import be.fortemaison.webweights.model.ProductCategory;

/**
 * Created with IntelliJ IDEA.
 * User: m999hfo
 * Date: 7/03/13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class ProductCategoryForm {

    private Integer id;

    private String name;


    /**
     *
     */
    public ProductCategoryForm () {
        //
    }

    /**
     * @param name
     */
    public ProductCategoryForm (String name) {
        this.name = name;
    }

    /**
     * @param category
     */
    public ProductCategoryForm (ProductCategory category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public ProductCategory toProductCategory () {
        ProductCategory result = new ProductCategory(name);
        result.setId(id);
        return result;
    }
}
