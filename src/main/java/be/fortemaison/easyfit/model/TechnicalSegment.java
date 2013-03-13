package be.fortemaison.easyfit.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 12/03/13
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
public class TechnicalSegment implements Serializable {

    private Date creationDate;

    private String creationUser;

    private Date updateDate;

    private String updateUser;

    public Date getCreationDate () {
        return creationDate;
    }

    public void setCreationDate (Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationUser () {
        return creationUser;
    }

    public void setCreationUser (String creationUser) {
        this.creationUser = creationUser;
    }

    public Date getUpdateDate () {
        return updateDate;
    }

    public void setUpdateDate (Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser () {
        return updateUser;
    }

    public void setUpdateUser (String updateUser) {
        this.updateUser = updateUser;
    }
}
