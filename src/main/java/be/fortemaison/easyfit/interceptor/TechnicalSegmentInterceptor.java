package be.fortemaison.easyfit.interceptor;

import be.fortemaison.easyfit.model.Auditable;
import be.fortemaison.easyfit.model.TechnicalSegment;
import be.fortemaison.easyfit.util.ContextThreadLocal;
import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 12/03/13
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */
public class TechnicalSegmentInterceptor extends EmptyInterceptor {

    static final String TECHNICAL_SEGMENT = "technicalSegment";

    public boolean onSave (
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {

        if (entity instanceof Auditable) {

            Logger.getLogger(this.getClass()).debug("## onSave : " + entity.getClass());
            Logger.getLogger(this.getClass()).debug(" tc.creationUser: " + ((Auditable) entity).getTechnicalSegment().getCreationUser());
            Logger.getLogger(this.getClass()).debug(" tc.creationDate: " + ((Auditable) entity).getTechnicalSegment().getCreationDate());
            Logger.getLogger(this.getClass()).debug(" tc.updateUser: " + ((Auditable) entity).getTechnicalSegment().getUpdateUser());
            Logger.getLogger(this.getClass()).debug(" tc.updateDate: " + ((Auditable) entity).getTechnicalSegment().getUpdateDate());

            Date now = new Date();
            String username = ContextThreadLocal.get().getUser().getUsername();
            TechnicalSegment tc = ((Auditable) entity).getTechnicalSegment();
            Logger.getLogger(this.getClass()).debug(" ContextThreadLocal.user: " + username);

            tc.setUpdateUser(username);
            tc.setUpdateDate(now);

            if (tc.getCreationUser() == null) {
                tc.setCreationDate(now);
                tc.setCreationUser(username);
            }

            return true;
        }

        return false;
    }

    public boolean onFlushDirty (
            Object entity,
            Serializable id,
            Object[] currentState,
            Object[] previousState,
            String[] propertyNames,
            Type[] types) {

        if (entity instanceof Auditable) {

            Logger.getLogger(this.getClass()).debug("### - onFlushDirty : " + entity.getClass());
            Logger.getLogger(this.getClass()).debug(" tc.creationUser: " + ((Auditable) entity).getTechnicalSegment().getCreationUser());
            Logger.getLogger(this.getClass()).debug(" tc.creationDate: " + ((Auditable) entity).getTechnicalSegment().getCreationDate());
            Logger.getLogger(this.getClass()).debug(" tc.updateUser: " + ((Auditable) entity).getTechnicalSegment().getUpdateUser());
            Logger.getLogger(this.getClass()).debug(" tc.updateDate: " + ((Auditable) entity).getTechnicalSegment().getUpdateDate());

            Date now = new Date();
            String username = ContextThreadLocal.get().getUser().getUsername();
            TechnicalSegment tc = ((Auditable) entity).getTechnicalSegment();

            Logger.getLogger(this.getClass()).debug(" ContextThreadLocal.user: " + username);

            tc.setUpdateUser(username);
            tc.setUpdateDate(now);

            return true;
        }

        return false;
    }
}
