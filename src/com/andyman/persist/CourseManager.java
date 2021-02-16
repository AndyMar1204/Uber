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

import com.andyman.beans.Adresse;
import com.andyman.beans.Course;
import com.andyman.beans.User;
import com.andyman.beans.Voiture;
import com.andyman.dao.DAOException;
import com.andyman.dao.DAOFactory;
import com.andyman.data.Etat;
import com.andyman.data.ManagerInterface;

/**
 * @author AndyMan
 *
 */
public class CourseManager implements ManagerInterface<Course> {
	private DAOFactory dao;
	private static final String tableName = "COURSE";
	private static final String SQL_SAVE="INSERT INTO " +tableName +" (prix,users_id,adresse_id,voiture_id,etat) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE " +tableName +" SET prix =?,users_id =?,adresse_id =?,voiture_id =?,etat = ? WHERE id=?";
	private static final String SQL_DELETE ="DELETE FROM " +tableName + " WHERE id = ?";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM " +tableName +" WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM " +tableName +"";
	private Connection conn = null;
	private ResultSet rs =null;
	private PreparedStatement ps =null;

	@Override
	public void save(Course t) {
		// TODO Auto-generated method stub
		try {
			conn = dao.getConnection();
			ps = initialisationRequetePreparee(conn, SQL_SAVE, true, t.getPrix(),t.getUser().getId(),t.getAdresse_destination().getId(),t.getVoiture().getId(),t.getEtat().name());
			int statut = ps.executeUpdate();
			if(statut ==0) {
				throw new DAOException("Echec de l'enregistrement");
			}else {
				/* Récupération de l'id
				 *  auto-génération par la 
				 *  requête d'insertion */
				rs =ps.getGeneratedKeys();
				if(rs.next()) {
					/* 
					 * Puis initialisation
					 *  de la propriété id du 
					 *  bean  avec sa valeur */
					t.setId(rs.getInt(1));
				}else {
					throw new DAOException("Echec de l'enregistrement, aucun ID géneré");
				}
			}
		}
		catch(NullPointerException | SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			fermeturesSilencieuses(rs, ps, conn);
		}
	}

	@Override
	public void delete(Course t) {
		try {
			conn = this.dao.getConnection();
			ps = initialisationRequetePreparee(conn, SQL_DELETE, false, t.getId());
			ps.execute();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(ps, conn);
		}
	}

	@Override
	public void update(Course t) {
		try {
			conn = dao.getConnection();
			ps = initialisationRequetePreparee(conn, SQL_UPDATE, false, t.getPrix(),t.getUser().getId(),t.getAdresse_destination().getId(),t.getVoiture().getId(),t.getEtat().name(),t.getId());
			ps.execute();
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Echec de mise à jour",e);
		}finally{
			fermeturesSilencieuses(ps, conn);
		}

	}

	@Override
	public Course findById(int id) {
		try {
			conn =dao.getConnection();
			ps = initialisationRequetePreparee(conn, SQL_FIND_BY_ID, false, id);
			rs =ps.executeQuery();
			if(rs.next())
				return map(rs);
			else
				throw new DAOException("Aucun resultat");
		} catch (SQLException e) {
			throw new DAOException(e);
		}finally {
			fermeturesSilencieuses(rs, ps, conn);
		}
	}

	@Override
	public List<Course> findAll() {
		try {
			List<Course> lstUser = new ArrayList<Course>();
			conn = dao.getConnection();
			ps = conn.prepareStatement(SQL_FIND_ALL);
			rs = ps.executeQuery();
			while(rs.next()) {
				lstUser.add(map(rs));
			}
			return lstUser;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}finally {
			fermeturesSilencieuses(rs, ps, conn);
		}
	}
	private static Etat stringToEtat(String etat){
		etat = etat.toUpperCase();
		switch(etat) {
		case "ANNULEE":
			return Etat.ANNULEE;
		case "DISPONIBLE":
			return Etat.DISPONIBLE;
		case "EN_COURS":
			return Etat.EN_COURS;
		case "RESERVEE" :
			return Etat.RESERVEE;
		case "TERMINE" :
			return Etat.TERMINE;
		default:
			return Etat.DISPONIBLE;
		}
	}
	private Course map(ResultSet rs) {
		try {
			int id_user = rs.getInt("users_id");
			int id_adresse = rs.getInt("adresse_id");
			int id_voiture = rs.getInt("voiture_id");
			int id = rs.getInt("id");
			Etat etat = stringToEtat(rs.getString("etat"));
			double prix = rs.getDouble("prix");
			User u = new UserManager(dao).findById(id_user);
			Adresse ad = new AdresseManager(dao).findById(id_adresse);
			Voiture voit = new VoitureManager(dao).findById(id_voiture);
			Course course = new Course(u,ad,prix,etat,voit);
			course.setId(id);
			return course;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException("Erreur du mapping",e);
		}
	}

	/**
	 * @param dao
	 */
	public CourseManager(DAOFactory dao) {
		this.dao = dao;
	}

}
