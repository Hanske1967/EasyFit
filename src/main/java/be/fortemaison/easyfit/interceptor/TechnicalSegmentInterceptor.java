package be.fortemaison.easyfit.interceptor;

import be.fortemaison.easyfit.model.Auditable;
import be.fortemaison.easyfit.model.TechnicalSegment;
import be.fortemaison.easyfit.util.ContextThreadLocal;
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
            Date now = new Date();
            String username = ContextThreadLocal.get().getUser().getUsername();
            TechnicalSegment tc = ((Auditable) entity).getTechnicalSegment();

            tc.setUpdateUser(username);
            tc.setUpdateDate(now);

            if (tc.getCreationUser() == null) {
                tc.setCreationDate(now);
                tc.setCreationUser(username);
            }
        }

        return true;
    }

    public boolean onFlushDirty (
            Object entity,
            Serializable id,
            Object[] currentState,
            Object[] previousState,
            String[] propertyNames,
            Type[] types) {

        if (entity instanceof Auditable) {
            Date now = new Date();
            String username = ContextThreadLocal.get().getUser().getUsername();
            TechnicalSegment tc = ((Auditable) entity).getTechnicalSegment();

            tc.setUpdateUser(username);
            tc.setUpdateDate(now);
        }

        return true;
    }
}
