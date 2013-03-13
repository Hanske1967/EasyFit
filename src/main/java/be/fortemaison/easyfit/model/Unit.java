package be.fortemaison.easyfit.model;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class Unit extends CommonAncestor {

    private String name;

    private String description;

    /**
     *
     */
    public Unit () {
        //
    }

    /**
     * @param name
     * @param description
     */
    public Unit (String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @param id
     * @param name
     * @param description
     */
    public Unit (Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


    /**
     * @param name
     */
    public Unit (String name) {
        this.name = name;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    @Override
    public String toString () {
        return name;
    }
}
