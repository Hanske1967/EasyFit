package be.fortemaison.easyfit.controller;

import be.fortemaison.easyfit.dao.IUserDAO;
import be.fortemaison.easyfit.form.UserForm;
import be.fortemaison.easyfit.model.User;
import be.fortemaison.easyfit.util.ContextThreadLocal;
import be.fortemaison.easyfit.util.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 3/02/13
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {


    public static final String USER_FORM = "userForm";

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private Context context;

    @Autowired
    private MessageSource messageSource;


    @ModelAttribute("context")
    public Context getContext () {
        return context;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String prepareHome (@RequestHeader("Accept-Language") String lang, ModelMap model) {
        Map<Integer, String> userMap = new HashMap<Integer, String>();
        MessageFormat mf = new MessageFormat("{0} - {1} {2}");
        List<User> users = this.userDAO.findAll();
        userMap.put(null, messageSource.getMessage("home.chooseUser", null, Locale.forLanguageTag(lang)));
        for (User user : users) {
            userMap.put(user.getId(), mf.format(new String[]{user.getUsername(), user.getFirstName(), user.getLastName()}));
        }
        model.addAttribute("users", userMap);
        model.addAttribute(USER_FORM, new UserForm());

        return "home";
    }

    @RequestMapping(value = "/changepwd", method = RequestMethod.GET)
    public String prepareChangePwd (@RequestParam("userid") Integer userid, ModelMap model) {
        User user = this.userDAO.findById(userid);
        UserForm userForm = new UserForm(user);
        model.addAttribute(USER_FORM, userForm);

        return "/users/changepwd";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleUserChoice (@RequestHeader("Accept-Language") String lang, @ModelAttribute(USER_FORM) UserForm userForm, Model model) {
        if (userForm == null || userForm.getId() == null){
            return "redirect:home";
        }

        User user = this.userDAO.findById(userForm.getId());
        if (isLoginValid(userForm, user)){

            //  TODO make password mandatory
            if (StringUtils.isEmpty(user.getPassword())) {
                userForm.setOldPassword(null);
                userForm.setPassword(null);
                model.addAttribute("context", userForm);
                return "/users/changepwd";
            }

            getContext().setUser(user);

            if (user.getLanguage() == null){
                user.setLocale(Locale.forLanguageTag(lang));
            }
            return "redirect:consumptions/list";
        }

        return "redirect:/home";
    }

    private boolean isLoginValid(UserForm userForm, User user){
        boolean result = false;
        if (result = (user != null)) {
            //  TODO make password mandatory
            result = StringUtils.isEmpty(user.getPassword()) ||
                user.getPassword().equals(PasswordService.getInstance().encrypt(userForm.getPassword()));
        }

        return result;
    }

    @RequestMapping(value = "/changepwd", method = RequestMethod.POST)
    public String handleChangePassword (@ModelAttribute(USER_FORM) UserForm userForm, Model model) {
        User user = this.userDAO.findById(userForm.getId());

        if (user != null) {
            ContextThreadLocal.set(getContext());

            String oldHash = StringUtils.isEmpty(userForm.getOldPassword()) ? "" : PasswordService.getInstance().encrypt(userForm.getOldPassword());
            String newHash = StringUtils.isEmpty(user.getPassword()) ? "" : user.getPassword();
            if (oldHash.equals(newHash)) {
                user.setPassword(PasswordService.getInstance().encrypt(userForm.getPassword()));
                getContext().setUser(user);
                this.userDAO.update(user);

                return "redirect:consumptions/list";
            }
        }

        return "redirect:home";
    }

}
