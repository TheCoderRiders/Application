package com.self.constants;

/**
 * Created by akash.p on 10/6/16.
 */
public class LoginConstants {
    public static final String SELECT_USER_AUTHENTICATION = "select username,password, active_flag from user_authentication where username=?";
    public static final String SELECT_USER_ROLE = "select username, role_name, role_id from user_authentication where username=?";
}
