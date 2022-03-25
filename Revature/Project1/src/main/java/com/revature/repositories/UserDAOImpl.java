package com.revature.repositories;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO{

    @Override
    public void createEmployee(User user)
    {
        try(Connection conn = ConnectionFactory.getInstance())  {
            String sql = "insert into ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email) values (?, ?, ?, ?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstname());
            ps.setString(4, user.getLastname());
            ps.setString(5, user.getEmail());

            ps.executeUpdate();

        }catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

    }

    @Override
    public void createManager(User user) {

        try(Connection conn = ConnectionFactory.getInstance())  {
            String sql = "insert into ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id_fk) values (?, ?, ?, ?,?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getFirstname());
            ps.setString(4,user.getLastname());
            ps.setString(5,user.getEmail());
            ps.setInt(6,user.getUser_role_id_fk());

            ps.executeUpdate();

        }catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }

    @Override
    public User getUserUsername(String username) {
        User user = null;

        try(Connection conn = ConnectionFactory.getInstance()){
            String sql = "select * from ers_users eu left join ers_user_roles eur  on  eu.user_role_id_fk = eur.ers_user_role_id where ers_username = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(9));
            }

        }catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserEmail(String email) {
        User user = null;

        try(Connection conn = ConnectionFactory.getInstance()){
            String sql = "select * from ers_users eu left join ers_user_roles eur  on  eu.user_role_id_fk = eur.ers_user_role_id where eu.user_email = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,email);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(9));
            }

        }catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return user;
    }

    @Override
    public User validateCredentials(String username, String password) {

        User user = null;

        try(Connection conn = ConnectionFactory.getInstance())  {
            String sql = "select * from ers_users eu left join ers_user_roles eur  on  eu.user_role_id_fk = eur.ers_user_role_id where ers_username = ? and ers_password = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(9));
            }

        }catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return user;
    }

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    /*public Optional<User> getByUsername(String username) {
        return Optional.empty();
    }*/

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     *
     * Note: The userToBeRegistered will have an id=0, and username and password will not be null.
     * Additional fields may be null.
     */
    public User create(User userToBeRegistered) {
        return userToBeRegistered;
    }











}
