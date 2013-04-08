package be.fortemaison.easyfit.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import javax.servlet.ServletContext;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 13/03/13
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */
@Component
public class VersionInterceptor implements WebRequestInterceptor, ServletContextAware {

    private ServletContext servletContext;

    private String version;

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
        //
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
        model.addAttribute("version", this.version);
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

    /**
     * Set the {@link javax.servlet.ServletContext} that this object runs in.
     * <p>Invoked after population of normal bean properties but before an init
     * callback like InitializingBean's {@code afterPropertiesSet} or a
     * custom init-method. Invoked after ApplicationContextAware's
     * {@code setApplicationContext}.
     *
     * @param servletContext ServletContext object to be used by this object
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext
     */
    @Override
    public void setServletContext (ServletContext servletContext) {
        this.servletContext = servletContext;
        this.version = servletContext.getServletContextName();
    }
}
