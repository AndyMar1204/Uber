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

import com.andyman.beans.Voiture;
import com.andyman.dao.DAOException;
import com.andyman.dao.DAOFactory;
import com.andyman.data.ManagerInterface;
import com.andyman.data.TypeVoiture;

/**
 * @author AndyMan
 *
 */
public class VoitureManager implements ManagerInterface<Voiture> {
	private DAOFactory dao;
	private static final String tableName = "VOITURE";
	private static final String SQL_SAVE="INSERT INTO " +tableName +" (type,description,disponible,adresse_id) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE " +tableName +" SET type =?,description =? ,disponible = ?, adresse_id = ? WHERE id=?";
	private static final String SQL_DELETE ="DELETE FROM " +tableName + " WHERE id = ?";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM " +tableName +" WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM " +tableName +"";
	private Connection conn = null;
	private ResultSet rs =null;
	private PreparedStatement ps =null;
	@Override
	public void save(Voiture t) {
		// TODO Auto-generated method stub
		try {
			conn = dao.getConnection();
			
			new AdresseManager(dao).save(t.getAdresse());
			ps = initialisationRequetePreparee(conn, SQL_SAVE, true, t.getType().name(),t.getDescription(),t.isDisponible(),t.getAdresse().getId());
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
	public void delete(Voiture t) {
		// TODO Auto-generated method stub
		try {
			conn = this.dao.getConnection();
			ps = initialisationRequetePreparee(conn, SQL_DELETE, false, t.getId());
			ps.execute();
			new AdresseManager(dao).delete(t.getAdresse());
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(ps, conn);
		}
	}

	@Override
	public void update(Voiture t) {
		// TODO Auto-generated method stub
		try {
			conn = dao.getConnection();
			new AdresseManager(dao).update(t.getAdresse());
			ps = initialisationRequetePreparee(conn, SQL_UPDATE, false,t.getType().name(),t.getDescription(),t.isDisponible(),t.getAdresse().getId(),t.getId());
			
			ps.execute();
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Echec de mise à jour",e);
		}finally{
			fermeturesSilencieuses(ps, conn);
		}
	}

	@Override
	public Voiture findById(int id) {
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
	public List<Voiture> findAll() {
		try {
			List<Voiture> lstUser = new ArrayList<Voiture>();
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
	private TypeVoiture string2type(String st) {
		if(st.toUpperCase().matches(TypeVoiture.TAXI.name())) return TypeVoiture.TAXI;
		else if(st.toUpperCase().matches(TypeVoiture.BUS.name())) return TypeVoiture.BUS;
		else if(st.toUpperCase().matches(TypeVoiture.AUTRES.name())) return TypeVoiture.AUTRES;
		else return null;
	}
	private Voiture map(ResultSet rs) {
		Voiture v = new Voiture();
		try {
			v.setId(rs.getInt("id"));
			v.setDescription(rs.getString("description"));
			v.setType(string2type(rs.getString("type")));
			v.setDisponible(rs.getBoolean("disponible"));
			v.setAdresse(new AdresseManager(dao).findById(rs.getInt("adresse_id")));
			return v;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException("Erreur du mapping");
		}
		
	}
	

	/**
	 * @param dao
	 */
	public VoitureManager(DAOFactory dao) {
		this.dao = dao;
	}
	
}
