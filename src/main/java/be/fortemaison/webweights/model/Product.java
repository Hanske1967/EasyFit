package be.fortemaison.webweights.model;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public class Product extends ProductAncestor {

    /**
     *
     */
    public Product () {
        //
    }

    /**
     * @param name
     * @param unit
     * @param point
     */
    public Product (String name, Unit unit, Double amount, Double point, Boolean favorite, String description) {
        this.name = name;
        this.unit = unit;
        this.amount = amount;
        this.point = point;
        this.favorite = favorite;
        this.description = description;
    }

    /**
     * @param name
     * @param unit
     * @param point
     */
    public Product (Integer id, String name, Unit unit, Double amount, Double point, Boolean favorite, String description) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.amount = amount;
        this.point = point;
        this.favorite = favorite;
        this.description = description;
    }


}
