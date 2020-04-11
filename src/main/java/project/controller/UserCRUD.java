package project.controller;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.dao.UserDAO;
import project.dao.UserDAOImpl;
import project.model.UserModel;

@Controller
public class UserCRUD {

    private final UserModel USER_MODEL;
    SessionFactory sessionFactory = null;
    String registrationStatus = "Complete the registration form please";

    @Autowired
    public UserCRUD(UserModel USER_MODEL) {
        this.USER_MODEL = USER_MODEL;
    }

    @GetMapping("/registration")
    public String createUserAccountForm(Model model) {

        model.addAttribute("userRegistration", USER_MODEL);
        model.addAttribute("registrationStatus", registrationStatus);
        return "/WEB-INF/views/user_registration.jsp";

    }

    @PostMapping("/registration")
    public String createUserAccountProcess(@ModelAttribute("userRegistration") UserModel userModel) {

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            UserDAO<UserModel, String> userDAO = new UserDAOImpl(sessionFactory);

            Query query = sessionFactory.openSession().createSQLQuery("SELECT login FROM user");
            String loginListTmp = query.list().toString();
            boolean checkLoginExistTmp = loginListTmp.contains(userModel.getUserLogin());

            if (checkLoginExistTmp) {
                registrationStatus = "Login is empty or already exist, choose another login please";
                return "redirect:/registration";
            }

            userDAO.create(userModel);

        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }

        String redirectRegistrationUrl = userModel.getUserLogin();
        return "redirect:/read/"+redirectRegistrationUrl;
    }

    @GetMapping("read/{userLogin}")
    public String readUserAccountData(Model model, @PathVariable String userLogin){

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            UserDAO<UserModel, String> userDAO = new UserDAOImpl(sessionFactory);

            model.addAttribute("userName", userDAO.read(userLogin).getUserName());
            model.addAttribute("userSurname", userDAO.read(userLogin).getUserSurname());
            model.addAttribute("userCountry", userDAO.read(userLogin).getUserCountry());
            model.addAttribute("userCity", userDAO.read(userLogin).getUserCity());

        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }

        return "/WEB-INF/views/user_account.jsp";
    }

    @GetMapping("/read/delete/{userLogin}")
    public String deleteUserAccountButton(@PathVariable String userLogin){

        registrationStatus = "Your account has been successfully deleted, " +
                "you can complete the registration form again";

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            UserDAO<UserModel, String> userDAO = new UserDAOImpl(sessionFactory);

            userDAO.delete(userDAO.read(userLogin));

        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }

        return "redirect:/registration";
    }

    @GetMapping("/read/update/{userLogin}")
    public String updateUserDataForm(@PathVariable String userLogin, Model model) {

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            UserDAO<UserModel, String> userDAO = new UserDAOImpl(sessionFactory);

            model.addAttribute("userUpdate", userDAO.read(userLogin));

        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }

        return "/WEB-INF/views/user_update.jsp";
    }

    @PostMapping("/read/update")
    public String updateUserDataProcess(@ModelAttribute("userUpdate") UserModel userModel){

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            UserDAO<UserModel, String> userDAO = new UserDAOImpl(sessionFactory);

            userDAO.update(userModel);

        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }

        String redirectUpdateUrl = userModel.getUserLogin();
        return "redirect:/read/"+redirectUpdateUrl;
    }
}
