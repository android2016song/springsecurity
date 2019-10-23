package web;

import org.springframework.security.crypto.password.PasswordEncoder;

import util.MD5;

public class DefaultPasswordEncoder implements PasswordEncoder  {
	public DefaultPasswordEncoder() {
        this(-1);
    }

    /**
     * @param strength
     *            the log rounds to use, between 4 and 31
     */
    public DefaultPasswordEncoder(int strength) {

    }

    public String encode(CharSequence rawPassword) {
        return MD5.MD5Encode(rawPassword.toString());
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
    	String s=MD5.MD5Encode(rawPassword.toString());
    	 return  s.equals(encodedPassword);
    }
}
