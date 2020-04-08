package project.dao;

import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import project.model.UserModel;

    /**
     *
     * @author serghei.sciurenco
     */
public class UserDAOImpl implements UserDAO<UserModel, String> {

    /**
     * Connection factory to database
     */
    private final SessionFactory sessionFactory;

    public UserDAOImpl(@NotNull final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get user by model
     *
     * @param
     */
    @Override
    public UserModel read(@NotNull final String model) {
        try(@NotNull final Session session = sessionFactory.openSession()) {

            final UserModel result = session.get(UserModel.class, model);
            return result != null ? result : new UserModel();
        }
    }

    /**
     * Create a user in user table
     *
     * @param
     */
    @Override
    public void create(@NotNull final UserModel userEntity) {
        try(final Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            session.save(userEntity);
            session.getTransaction().commit();
        }
    }

    /**
     * Update a user into user table
     *
     * @param
     */
    @Override
    public void update(@NotNull final UserModel userEntity) {
        try(final Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            session.update(userEntity);
            session.getTransaction().commit();

        }
    }

    /**
     * Delete a user from user table
     *
     * @param
     */
    @Override
    public void delete(UserModel userLogin) {
        try(final Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            session.delete(userLogin);
            session.getTransaction().commit();
        }
    }
}