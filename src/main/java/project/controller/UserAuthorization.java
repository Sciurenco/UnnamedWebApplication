package project.controller;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.dao.UserDAO;
import project.dao.UserDAOImpl;
import project.model.UserModel;

@Controller
@RequestMapping("/authorization")
public class UserAuthorization {

    SessionFactory sessionFactory = null;
    private final  UserModel USER_MODEL_AUTHORIZATION;
    String authorizationStatus = "Log into your account";

    @Autowired
    public UserAuthorization(UserModel USER_MODEL_AUTHORIZATION) {
        this.USER_MODEL_AUTHORIZATION = USER_MODEL_AUTHORIZATION;
    }

    @GetMapping
    public String authorizationUserForm(Model model){

        model.addAttribute("userAuthorization", USER_MODEL_AUTHORIZATION);
        model.addAttribute("authorizationStatus", authorizationStatus);
        return "/WEB-INF/views/user_authorization.jsp";
    }

    @PostMapping
    public String authorizationUserProcess(@ModelAttribute("userAuthorization") UserModel userModel) {

        if (userModel.getUserLogin().isEmpty() || userModel.getUserPassword().isEmpty())  {

            authorizationStatus = "Your login or password is empty";
            return "redirect:/authorization";

        } else {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
                UserDAO<UserModel, String> userDAO = new UserDAOImpl(sessionFactory);

                Query query = sessionFactory.openSession().createSQLQuery("SELECT login FROM user");
                String loginListTmp = query.list().toString();
                boolean checkLoginExistTmp = loginListTmp.contains(userModel.getUserLogin());
                String checkPasswordCorrectTmp = userDAO.read(userModel.getUserLogin()).toString();

                if (!checkLoginExistTmp ||
                        !checkPasswordCorrectTmp.contains(userModel.getUserPassword())) {

                    authorizationStatus = "Login or password are wrong, try again";
                    return "redirect:/authorization";
                }

            } finally {
                if (sessionFactory != null) {
                    sessionFactory.close();
                }
            }

            String redirectAuthorizationUrl = userModel.getUserLogin();
            return "redirect:/read/" + redirectAuthorizationUrl;
        }
    }
}
