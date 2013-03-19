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

    private Boolean shared = Boolean.TRUE;

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
    public Unit (Integer id, String name, String description, Boolean shared) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shared = shared;
    }

    /**
     * @param name
     */
    public Unit (String name) {
        this.name = name;
    }

    public Boolean isShared () {
        return shared;
    }

    public void setShared (Boolean shared) {
        this.shared = shared;
    }

    public Boolean getShared () {
        return shared;
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
