package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import entity.User;

@Repository  // This makes Spring manage this UserDaoImplementation as a bean
public class UserDaoImplementation implements UserDao {

    @Autowired  //  Spring will inject the SessionFactory from the Hibernate config
    private SessionFactory factory;

    @Override
    public boolean validateUser(User user) {
        Session session = factory.openSession();
        String hql = "FROM User WHERE username = :uname AND password = :pwd";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("uname", user.getUsername());
        query.setParameter("pwd", user.getPassword());
        boolean result = !query.getResultList().isEmpty();
        session.close();
        return result;
    }
}









