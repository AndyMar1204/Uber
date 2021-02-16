package com.andyman.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andyman.beans.User;
import com.andyman.page.UserPage;
import com.andyman.utils.UserUtils;

/**
 * Servlet implementation class Inscription
 */
@WebServlet(description = "Page d'inscription", urlPatterns = { "/Inscription" })
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERNAME = "username";
	private static final String EMAIL = "email";
	private static final String NUMERO = "numero";
	private static final String PASSWORD = "password";
	private static final String CONFIRM ="confirm";
	private static final String CONNEXION ="Connexion";
	private static String VUE ="/inscription.jsp";
	public static String erreur ;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Inscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User u = UserPage.form2user(req);
		System.out.println(u.toString());
		if(u != null) {
			new UserPage().save(u);
			resp.sendRedirect(CONNEXION);
		}
	}

}
