package be.fortemaison.easyfit.form;

import be.fortemaison.easyfit.model.Unit;
import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 24/01/13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class UnitForm {

    private String id;

    private String name;

    private String description;

    private Boolean shared = Boolean.TRUE;

    /**
     *
     */
    public UnitForm () {
        //
    }

    /**
     *
     */
    public UnitForm (final Unit unit) {
        this.id = unit.getId().toString();
        this.name = unit.getName();
        this.description = unit.getDescription();
        this.shared = unit.isShared();
    }

    public Boolean getShared () {
        return shared;
    }

    public void setShared (Boolean shared) {
        this.shared = shared;
    }

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
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

    public Unit getUnit () {
        return new Unit(StringUtils.isEmpty(id) ? null : Integer.decode(id), name, description, shared);
    }
}
