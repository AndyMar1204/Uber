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

import com.andyman.beans.Position;
import com.andyman.dao.DAOException;
import com.andyman.dao.DAOFactory;
import com.andyman.data.ManagerInterface;

/**
 * @author AndyMan
 *
 */
public class PositionManager implements ManagerInterface<Position> {
	private DAOFactory dao;
	private static final String tableName = "POSITION";
	private static final String SQL_SAVE="INSERT INTO " +tableName +" (longitude,latitude) VALUES (?,?)";
	private static final String SQL_UPDATE = "UPDATE " +tableName +" SET longitude =?,latitude =? WHERE id=?";
	private static final String SQL_DELETE ="DELETE FROM " +tableName + " WHERE id = ?";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM " +tableName +" WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM " +tableName +"";
	private Connection conn = null;
	private ResultSet rs =null;
	private PreparedStatement ps =null;
	@Override
	public void save(Position t) { 
		try {
			conn = dao.getConnection();
			ps = initialisationRequetePreparee(conn, SQL_SAVE, true, t.getLongitude(),t.getLatitude());
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
					t.setId_(rs.getInt(1));
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
	public void delete(Position t) { 
		try {
			conn = this.dao.getConnection();
			ps = initialisationRequetePreparee(conn, SQL_DELETE, false, t.getId_());
			ps.execute();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(ps, conn);
		}
	}

	@Override
	public void update(Position t) { 
		try {
			conn = dao.getConnection();
			ps = initialisationRequetePreparee(conn, SQL_UPDATE, false,t.getLongitude(),t.getLatitude(),t.getId_());
			ps.execute();
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Echec de mise à jour",e);
		}finally{
			fermeturesSilencieuses(ps, conn);
		}
	}

	@Override
	public Position findById(int id) {
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
	public List<Position> findAll() {
		try {
			List<Position> lstUser = new ArrayList<Position>();
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
	private Position map(ResultSet res) {
		Position p = new Position();
		try {
			p.setLatitude(rs.getDouble("latitude"));
			p.setLongitude(res.getDouble("longitude"));
			p.setId_(rs.getInt("id"));
			return p;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException("Erreur du mapping");
		}
	}
	

	/**
	 * @param dao
	 */
	public PositionManager(DAOFactory dao) {
		this.dao = dao;
	}
	
}
