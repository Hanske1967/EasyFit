package be.fortemaison.easyfit.controller;

import be.fortemaison.easyfit.form.UserForm;
import be.fortemaison.easyfit.model.User;
import be.fortemaison.easyfit.service.IUserService;
import be.fortemaison.easyfit.util.ContextThreadLocal;
import be.fortemaison.easyfit.util.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
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


    @Autowired
    private IUserService userService;

    @Autowired
    private Context context;

    @ModelAttribute("context")
    public Context getContext () {
        return context;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String prepareHome (ModelMap model) {
        Map<Integer, String> userMap = new HashMap<Integer, String>();
        MessageFormat mf = new MessageFormat("{0} - {1} {2}");
        List<User> users = this.userService.findAll();
        userMap.put(null, "-- Choose a user --");
        for (User user : users) {
            userMap.put(user.getId(), mf.format(new String[]{user.getUsername(), user.getFirstName(), user.getLastName()}));
        }
        model.addAttribute("users", userMap);
        model.addAttribute("userForm", new UserForm());

        return "home";
    }

    @RequestMapping(value = "/changepwd", method = RequestMethod.GET)
    public String prepareChangePwd (@ModelAttribute("userForm") UserForm userForm, ModelMap model) {
        if (userForm.getId() == null) {
            return "home";
        }

        model.addAttribute("userForm", userForm);

        return "/users/changepwd";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleUserChoice (@ModelAttribute("userForm") UserForm userForm, Model model) {
        if (userForm.getId() == null) {
            return "redirect:home";
        }
        User user = this.userService.findById(userForm.getId());

        if (user != null) {
            //  TODO make password mandatory
            if (StringUtils.isEmpty(user.getPassword())) {
                model.addAttribute("context", userForm);
                return "/users/changepwd";
            }

            if (!StringUtils.isEmpty(userForm.getPassword())) {
                String hash = PasswordService.getInstance().encrypt(userForm.getPassword());
                if (user.getPassword().equals(hash)) {
                    getContext().setUser(user);
                    return "redirect:consumptions/list";
                }
            }
        }

        return "redirect:/home";
    }

    @RequestMapping(value = "/changepwd", method = RequestMethod.POST)
    public String handleChangePassword (@ModelAttribute("userForm") UserForm userForm, Model model) {
        User user = this.userService.findById(userForm.getId());

        if (user != null) {
            //  TODO compare with old one
            ContextThreadLocal.set(getContext());

            String hash = PasswordService.getInstance().encrypt(userForm.getPassword());
            user.setPassword(hash);
            getContext().setUser(user);

            this.userService.update(user);
            return "redirect:consumptions/list";
        }

        return "home";
    }

    @ExceptionHandler(HttpSessionRequiredException.class)
    public String handleException () {
        return "redirect:/home";
    }

}
