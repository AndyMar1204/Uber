package com.andyman.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andyman.beans.Adresse;
import com.andyman.beans.Course;
import com.andyman.beans.User;
import com.andyman.dao.DAOFactory;
import com.andyman.persist.AdresseManager;
import com.andyman.persist.CourseManager;
import com.andyman.persist.UserManager;

/**
 * Servlet implementation class Admin
 */
@WebServlet(description = "Page d'admin", urlPatterns = { "/Admin" })
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private static final String VUE ="/admin/admin.jsp"; 
     private static final String LIST_ADRESSE = "list_adresse";
     private static final String LIST_COURSE = "list_course";
     private static final String LIST_USER = "list_user";
     private static final String USER_MANAGER = "u_manager";
     private static final DAOFactory dao = DAOFactory.getInstance();
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		List<User> list_user = new UserManager(dao).findAll();
		List<Course> list_course = new CourseManager(dao).findAll();
		List<Adresse> list_adresse = new AdresseManager(dao).findAll();
		UserManager userManager = new UserManager(dao);
		req.setAttribute(LIST_ADRESSE, list_adresse);
		req.setAttribute(LIST_COURSE, list_course);
		req.setAttribute(LIST_USER, list_user);
		req.setAttribute(USER_MANAGER, userManager);
		this.getServletContext().getRequestDispatcher(VUE).forward(req, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
