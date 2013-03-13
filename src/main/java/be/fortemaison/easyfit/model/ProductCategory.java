package be.fortemaison.easyfit.model;

/**
 * Created with IntelliJ IDEA.
 * User: m999hfo
 * Date: 7/03/13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class ProductCategory extends CommonAncestor {

    private String name;


    /**
     *
     */
    public ProductCategory () {
        //
    }

    public ProductCategory (String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }
}
