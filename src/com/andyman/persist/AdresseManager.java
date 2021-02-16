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
import com.andyman.dao.DAOException;
import com.andyman.dao.DAOFactory;
import com.andyman.data.ManagerInterface;

/**
 * @author AndyMan
 *
 */
public class AdresseManager implements ManagerInterface<Adresse> {
	private DAOFactory dao;
	private static final String tableName = "ADRESSE";
	private static final String SQL_SAVE="INSERT INTO " +tableName +" (ville,commune,quartier,avenue,numero,position_id) VALUES (?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE " +tableName +" SET ville =?,commune =?,quartier = ?,avenue=?,numero=?,position_id=? WHERE id=?";
	private static final String SQL_DELETE ="DELETE FROM " +tableName + " WHERE id = ?";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM " +tableName +" WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM " +tableName +"";
	private Connection conn = null;
	private ResultSet rs =null;
	private PreparedStatement ps =null;
	@Override
	public void save(Adresse t) {
		// TODO Auto-generated method stub
		try {
			conn = dao.getConnection();
			new PositionManager(dao).save(t.getPosition());
			ps = initialisationRequetePreparee(conn, SQL_SAVE, true, t.getVille(),t.getCommune(),t.getQuartier(),t.getAvenue(),t.getNumero(),t.getPosition().getId_());
			int statut = ps.executeUpdate();
			if(statut ==0) {
				throw new DAOException("Echec de l'enregistrement");
			}else {
				/* R�cup�ration de l'id
				 *  auto-g�n�r� par la 
				 *  requ�ted'insertion */
				rs =ps.getGeneratedKeys();
				if(rs.next()) {
					/* 
					 * Puis initialisation
					 *  de la propri�t� id du 
					 *  beanUtilisateur avec sa valeur */
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
	public void delete(Adresse t) {
		try {
			conn = this.dao.getConnection();

			ps = initialisationRequetePreparee(conn, SQL_DELETE, false, t.getId());

			ps.execute();
			new PositionManager(dao).delete(t.getPosition());
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(ps, conn);
		}
	}

	@Override
	public void update(Adresse t) {
		// TODO Auto-generated method stub
		try {
			conn = dao.getConnection();
			new PositionManager(dao).update(t.getPosition());
			ps = initialisationRequetePreparee(conn, SQL_UPDATE, false,t.getVille(),t.getCommune(),t.getQuartier(),t.getAvenue(),t.getNumero(),t.getPosition().getId_(),t.getId());
			ps.execute();
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Echec de mise à jour",e);
		}finally{
			fermeturesSilencieuses(ps, conn);
		}
	}

	@Override
	public Adresse findById(int id) {
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
	public List<Adresse> findAll() {
		try {
			List<Adresse> lstUser = new ArrayList<Adresse>();
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
	private Adresse map(ResultSet rs) {
		Adresse ad = new Adresse();

		try {
			ad.setVille(rs.getString("ville"));
			ad.setCommune("commune");
			ad.setQuartier(rs.getString("quartier"));
			ad.setAvenue("avenue");
			ad.setNumero(rs.getInt("numero"));
			ad.setId(rs.getInt("id"));
			ad.setPosition(new PositionManager(dao).findById(rs.getInt("position_id")));
			return ad;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Erreur lors du mapping");
		}
	}



	/**
	 * @param dao
	 */
	
	/**
	 * @param dao
	 */
	public AdresseManager(DAOFactory dao) {
		this.dao = dao;
	}

}
