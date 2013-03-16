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

    private Boolean shared = Boolean.TRUE;

    /**
     *
     */
    public ProductCategory () {
        //
    }

    public ProductCategory (String name) {
        this.name = name;
    }

    public Boolean isShared () {
        return shared;
    }

    public Boolean getShared () {
        return shared;
    }

    public void setShared (Boolean shared) {
        this.shared = shared;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }
}
