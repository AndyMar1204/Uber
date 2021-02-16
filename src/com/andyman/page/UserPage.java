/**
 * 
 */
package com.andyman.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.andyman.beans.User;
import com.andyman.dao.DAOFactory;
import com.andyman.data.ManagerInterface;
import com.andyman.persist.UserManager;
import com.andyman.utils.UserUtils;

/**
 * @author AndyMan
 *
 */
public class UserPage implements ManagerInterface<User>{
	DAOFactory dao = DAOFactory.getInstance();
	UserManager um = new UserManager(dao);
	
	  private static final String USERNAME = "username";
	    private static final String EMAIL = "email";
	    private static final String NUMERO = "numero";
	    private static final String PASSWORD = "password";
	    private static final String CONFIRM ="confirm";
	   // private static String VUE ="/inscription.jsp";
	    public static String erreur ;
	
	public static User form2user(HttpServletRequest req) {
		String username = req.getParameter(USERNAME);
		String email = req.getParameter(EMAIL);
		String numero = req.getParameter(NUMERO);
		String password = req.getParameter(PASSWORD);
		String confirm = req.getParameter(CONFIRM);
		
		if(password.matches(confirm)) {
			User u = new User();
			password = UserUtils.passwordHasher(password);
			u.setUsername(username.trim());
			u.setEmail(email.trim());
			u.setTelephone(numero.trim());
			u.setPassword(password);
			
			return u;
			}
		else {
			return null;
		}
	}

	@Override
	public void save(User t) {
		// TODO Auto-generated method stub
		um.save(t);
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
