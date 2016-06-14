package com.self.login.config;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;

/**
 * Created by akash.p on 10/6/16.
 */

public class MySqlPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        byte[] utf8 = new byte[0];
        try {
            utf8 = charSequence.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "*" + DigestUtils.shaHex(DigestUtils.sha(utf8)).toUpperCase();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }
}
