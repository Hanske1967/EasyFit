package be.fortemaison.webweights.controller;

import be.fortemaison.webweights.form.UserForm;
import be.fortemaison.webweights.model.User;
import be.fortemaison.webweights.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Hans
 * Date: 26/01/13
 * Time: 23:36
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private IUserService userService;

    @Autowired
    public void setUserService (IUserService userService) {
        this.userService = userService;
    }

    /**
     * List view with all users
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String prepareList (Model model) {
        List<User> users = this.userService.findAll();
        List<UserForm> userForms = new ArrayList<UserForm>(users.size());
        for (User user : users) {
            userForms.add(new UserForm(user));
        }
        model.addAttribute("users", userForms);
        return "users/list";
    }

    /**
     * Prepare new form for input
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String prepareNew (Model model) {
        model.addAttribute(new UserForm());
        return "users/edit";
    }

    /**
     * Prepare update form for input
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String prepareUpdate (@RequestParam(value = "username") String username, Model model) {
        User user = this.userService.findByUsername(username);
        if (user != null) {
            model.addAttribute(new UserForm(user));
            return "users/edit";
        }

        return "users/list";
    }

    /**
     * Process new form inpout, create new users and return to list
     *
     * @param userForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processNew (@Validated UserForm userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/edit";
        }

        this.userService.update(userForm.getUser());
        return "redirect:/users/list";
    }

    /**
     * Process update form input, update record in db
     *
     * @param userForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdate (@Validated UserForm userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/edit";
        }

        this.userService.update(userForm.getUser());
        return "redirect:/users/list";
    }

    /**
     * Delete scale and redirect to list
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String processDelete (@RequestParam(value = "username") String username, Model model) {
        User user = this.userService.findByUsername(username);
        if (user != null) {
            this.userService.delete(user);
            return "redirect:/users/list";
        }

        return "users/list";
    }

}
