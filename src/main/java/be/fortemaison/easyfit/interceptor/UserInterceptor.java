package be.fortemaison.easyfit.interceptor;

import be.fortemaison.easyfit.controller.Context;
import be.fortemaison.easyfit.util.ContextThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 13/03/13
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */
@Component
public class UserInterceptor implements WebRequestInterceptor {

    @Autowired
    private Context context;

    @ModelAttribute("context")
    public Context getContext () {
        return context;
    }

    /**
     * Intercept the execution of a request handler <i>before</i> its invocation.
     * <p>Allows for preparing context resources (such as a Hibernate Session)
     * and expose them as request attributes or as thread-local objects.
     *
     * @param request the current web request
     * @throws Exception in case of errors
     */
    @Override
    public void preHandle (WebRequest request) throws Exception {
        Context ctx = getContext();

        if (ctx.getUser() == null) {
            String path = request.getDescription(false);
            if ((path.indexOf("/login") == -1) && (path.indexOf("/changepwd") == -1)) {
                throw new SecurityException("Must log in first !");
            }
        }

        ContextThreadLocal.set(ctx);
    }

    /**
     * Intercept the execution of a request handler <i>after</i> its successful
     * invocation, right before view rendering (if any).
     * <p>Allows for modifying context resources after successful handler
     * execution (for example, flushing a Hibernate Session).
     *
     * @param request the current web request
     * @param model the map of model objects that will be exposed to the view
     * (may be {@code null}). Can be used to analyze the exposed model
     * and/or to add further model attributes, if desired.
     * @throws Exception in case of errors
     */
    @Override
    public void postHandle (WebRequest request, ModelMap model) throws Exception {
        ContextThreadLocal.unset();
    }

    /**
     * Callback after completion of request processing, that is, after rendering
     * the view. Will be called on any outcome of handler execution, thus allows
     * for proper resource cleanup.
     * <p>Note: Will only be called if this interceptor's {@code preHandle}
     * method has successfully completed!
     *
     * @param request the current web request
     * @param ex exception thrown on handler execution, if any
     * @throws Exception in case of errors
     */
    @Override
    public void afterCompletion (WebRequest request, Exception ex) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
