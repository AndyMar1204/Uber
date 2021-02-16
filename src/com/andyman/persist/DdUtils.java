/**
 * 
 */
package com.andyman.persist;

import static com.andyman.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.andyman.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.andyman.beans.Course;
import com.andyman.beans.User;
import com.andyman.beans.Voiture;
import com.andyman.dao.DAOException;
import com.andyman.dao.DAOFactory;
import com.andyman.data.Etat;

/**
 * @author AndyMan
 *
 */
public class DdUtils {
	private static final DAOFactory dao = DAOFactory.getInstance();
	private static final String SQL_USER_CONNECT = "SELECT id FROM USERS WHERE username=? AND password =?";
	private static Connection conn = null;
	private static ResultSet rs =null;
	private static PreparedStatement ps =null;
	public static User connectionUser(String username,String motdepasse) {
		try {
			conn =dao.getConnection();
			ps = initialisationRequetePreparee(conn, SQL_USER_CONNECT, false, username,motdepasse);
			rs =ps.executeQuery();
			if(rs.next())
				return new UserManager(dao).findById(rs.getInt("id"));
			else
				return null;
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally {
			fermeturesSilencieuses(rs, ps, conn);
		}
	}
	public static List<Voiture> voitureDisponible(){
		List<Voiture> lVoiture = new VoitureManager(dao).findAll();
		List<Voiture> lVoitureDispo = new ArrayList<Voiture>();
		lVoiture.forEach(f ->{
			if(f.isDisponible()) {
				lVoitureDispo.add(f);
			}
		});
		return lVoitureDispo;
	}
	static final String SQL_UPDATE = "UPDATE COURSE SET etat = ? WHERE id=?";
	static final String VOITURE_ACTION = "UPDATE VOITURE SET disponible = ? WHERE id =?";

	public static void updateCourse(Course course,Etat etat) {
		try {
			conn = dao.getConnection();
			
			ps = initialisationRequetePreparee(conn,SQL_UPDATE, false, etat,course.getId());
			ps.execute();
			if((etat ==Etat.TERMINE)||(etat == Etat.DISPONIBLE)||(etat == Etat.ANNULEE)) {
				updateVoitureDispo(course.getVoiture(),true);
			}else {
				updateVoitureDispo(course.getVoiture(),false);
			}
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Echec de mise à jour",e);
		}finally{
			fermeturesSilencieuses(ps, conn);
		}
	}
	public static void updateVoitureDispo(Voiture voiture,boolean disponible) {
		try {
			conn = dao.getConnection();
			ps = initialisationRequetePreparee(conn,VOITURE_ACTION, false, disponible,voiture.getId());
			ps.execute();
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Echec de mise à jour",e);
		}finally{
			fermeturesSilencieuses(ps, conn);
		}
	}
}
