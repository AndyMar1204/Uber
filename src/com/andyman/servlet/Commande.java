package com.andyman.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andyman.beans.Adresse;
import com.andyman.beans.Course;
import com.andyman.beans.User;
import com.andyman.beans.Voiture;
import com.andyman.dao.DAOFactory;
import com.andyman.data.Etat;
import com.andyman.data.TypeVoiture;
import com.andyman.persist.AdresseManager;
import com.andyman.persist.CourseManager;
import com.andyman.persist.VoitureManager;

/**
 * Servlet implementation class Commande
 */
@WebServlet("/Commande")
public class Commande extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String VUE ="/commande.jsp";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Commande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory dao = DAOFactory.getInstance();
		String commune = request.getParameter("commune");
		String quartier = request.getParameter("quartier");
		String avenue = request.getParameter("avenue");
		int numero = Integer.valueOf(request.getParameter("numero")).intValue();
		Adresse ad= new Adresse();
		ad.setVille("Kinshasa");
		ad.setCommune(commune);
		ad.setQuartier(quartier);
		
		ad.setAvenue(avenue);
		ad.setNumero(numero);
		new AdresseManager(dao).save(ad);
		System.out.println(ad.toString());
		Voiture v = new Voiture();
		v.setDescription("Jolie voiture climatiée");
		v.setType(TypeVoiture.TAXI);
		v.setAdresse(new Adresse());
		new VoitureManager(dao).save(v);
		System.out.println(v.toString());
		User user = (User) request.getSession().getAttribute(Connexion.SESSION_USER);
		System.out.println(user.toString());
		
		Course course = new Course(user,ad,Etat.EN_COURS,v);
		new CourseManager(dao).save(course);
		System.out.println(course.toString());
		request.setAttribute("course", course);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	

}
