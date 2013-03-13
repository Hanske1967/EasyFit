package be.fortemaison.easyfit.model;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 12/03/13
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */
public class CommonAncestor implements Auditable {

    protected Integer id;

    protected TechnicalSegment technicalSegment = new TechnicalSegment();

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public TechnicalSegment getTechnicalSegment () {
        return technicalSegment;
    }

    public void setTechnicalSegment (TechnicalSegment technicalSegment) {
        this.technicalSegment = technicalSegment;
    }
}
