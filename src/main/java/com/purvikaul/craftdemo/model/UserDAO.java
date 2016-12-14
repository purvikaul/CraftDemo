package com.purvikaul.craftdemo.model;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;


/**
 * Created by purvi on 12/13/16.
 */
public class UserDAO extends AbstractDAO<User>{

    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    public List<User> findAll(){
        return list(namedQuery("com.purvikaul.craftdemo.model.User.findAll"));
    }
    public User findByUserName(String name){
        StringBuilder builder = new StringBuilder("%");
        builder.append(name).append("%");
        List<User> users = list(namedQuery("com.purvikaul.craftdemo.model.User.findByUserName").setParameter("name",builder.toString()));
        if(users.isEmpty()){
            return null;
        }
        return users.get(0);
    }
    public User findByUserId(Long userid){
        List<User> users = list(namedQuery("com.purvikaul.craftdemo.model.User.findByUserId").setParameter("userid",userid));
        if(users.isEmpty()){
            return null;
        }
        return users.get(0);
    }


    public void insert(User user){
        currentSession().persist(user);
    }
}
