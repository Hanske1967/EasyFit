package be.fortemaison.easyfit.controller;

import be.fortemaison.easyfit.dao.IUserDAO;
import be.fortemaison.easyfit.form.UserForm;
import be.fortemaison.easyfit.model.User;
import be.fortemaison.easyfit.util.PasswordService;
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

    @Autowired
    private IUserDAO userDAO;


    /**
     * List view with all users
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String prepareList (Model model) {
        List<User> users = this.userDAO.findAll();
        List<UserForm> userForms = new ArrayList<UserForm>(users.size());
        for (User user : users) {
            userForms.add(new UserForm(user));
        }
        model.addAttribute("users", userForms);
        return "users/list";
    }

    /**
     * Prepare update form for input
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String prepareUpdate (@RequestParam(value = "key", required = false) Integer key, Model model) {
        if (key == null) {
            model.addAttribute(new UserForm());
            return "users/edit";
        } else {
            User user = this.userDAO.findById(key);
            if (user != null) {
                model.addAttribute(new UserForm(user));
                return "users/edit";
            }
        }

        return "users/list";
    }

    /**
     * Process update form input, update record in db
     *
     * @param userForm
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String processUpdate (@Validated UserForm userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/edit";
        }

        User user = userForm.getUser();
        if (user.getId() != null) {
            //  update
            user = this.userDAO.findById(user.getId());

            if (userForm.getPassword() != null) {
                user.setPassword(PasswordService.getInstance().encrypt(userForm.getPassword()));
            }
            user.setDayPoints(userForm.getDayPoints());
            user.setExtraPoints(userForm.getExtraPoints());
            user.setFirstName(userForm.getFirstName());
            user.setLastName(userForm.getLastName());
            user.setTargetWeight(userForm.getTargetWeight());
        }

        this.userDAO.update(user);
        return "redirect:/users/list";
    }

    /**
     * Delete and redirect to list
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String processDelete (@RequestParam(value = "key") Integer key, Model model) {
        User user = this.userDAO.findById(key);
        if (user != null) {
            this.userDAO.delete(user);
            return "redirect:/users/list";
        }

        return "users/list";
    }

}
