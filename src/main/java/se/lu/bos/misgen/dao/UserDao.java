package se.lu.bos.misgen.dao;

import se.lu.bos.misgen.sec.User;

/**
 * Created with IntelliJ IDEA.
 * User: eriklupander
 * Date: 15-02-13
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao {

    User findUser(String username);

    User createUser(String username, String password);
}
