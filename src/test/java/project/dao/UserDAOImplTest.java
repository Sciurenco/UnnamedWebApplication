package project.dao;

import project.model.UserModel;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserDAOImplTest {

    private SessionFactory sessionFactory;

    private UserDAO<UserModel, String>  userDAOImpl;

    private final UserModel userModel = new UserModel();

    @Before
    public void before(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        userDAOImpl = new UserDAOImpl(sessionFactory);

        userModel.setUserLogin("TestLogin");
        userModel.setUserPassword("TestPassword");
        userModel.setUserName("John");
        userModel.setUserSurname("Doe");
        userModel.setUserCountry("USA");
        userModel.setUserCity("New York");
    }

    @After
    public void after() {
        if (userDAOImpl.read(userModel.getUserLogin()) != null) {
            userDAOImpl.delete(userModel);
        }

        sessionFactory.close();
    }

    @Test
    public void whenReadUserModelNotExistThenUserModelIsNull() {

        UserModel read = userDAOImpl.read(userModel.getUserLogin());
        assertNull(read.getUserLogin());
    }

    @Test
    public void whenCreateUserModelThenUserModelIsExist() {

        userDAOImpl.create(userModel);
        UserModel result = userDAOImpl.read(userModel.getUserLogin());

        String entityToDB = userModel.toString();
        String entityFromDB = result.toString();

        assertThat(entityToDB, is(entityFromDB));

    }

    @Test
    public void whenUserModelUpdateThenUserCityHasNewValue() {

        userDAOImpl.create(userModel);
        userModel.setUserCity("Los-Angeles");
        userDAOImpl.update(userModel);
        UserModel result = userDAOImpl.read(userModel.getUserLogin());

        assertThat(result.getUserCity(), is ("Los-Angeles"));

    }

    @Test
    public void whenUserModelDeleteThenUserModelNotExist() {

        userDAOImpl.create(userModel);
        UserModel before = userDAOImpl.read(userModel.getUserLogin());
        userDAOImpl.delete(userModel);
        UserModel after = userDAOImpl.read(userModel.getUserLogin());
        assertNotNull(before);
        assertNull(after.getUserLogin());

    }
}