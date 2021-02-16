package com.andyman.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.andyman.beans.User;
import com.andyman.utils.UserUtils;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String VUE ="/connexion.jsp"; 
    private static final String SUCCESS ="Accueil";
    public static final String SESSION_USER="user";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("username");
		String password = request.getParameter("password");
		User user = UserUtils.connection(nom, password);
		
		
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute(SESSION_USER, user);
			response.sendRedirect(SUCCESS);
		}else {
			request.setAttribute("erreur", "Echec de connection");
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);;
		}
	}

}
