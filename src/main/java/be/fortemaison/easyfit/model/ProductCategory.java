package be.fortemaison.easyfit.model;

/**
 * Created with IntelliJ IDEA.
 * User: m999hfo
 * Date: 7/03/13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class ProductCategory {

    private Integer id;

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
}
