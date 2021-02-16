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

import com.andyman.beans.User;
import com.andyman.dao.DAOException;
import com.andyman.dao.DAOFactory;
import com.andyman.data.ManagerInterface;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * @author AndyMan
 *
 */
public class UserManager implements ManagerInterface<User> {
	private DAOFactory dao;
	private static final String SQL_SAVE="INSERT INTO USERS(username,email,numero,password,adresse_id) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE USERS SET username =?,email =?,numero = ?,password=? ,adresse_id =? WHERE id=?";
	private static final String SQL_DELETE ="DELETE FROM USERS WHERE id = ?";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM USERS WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM USERS";
	private Connection conn = null;
	private ResultSet rs =null;
	private PreparedStatement ps =null;
	@Override
	public void save(User t) {
		
		try {
			conn = dao.getConnection();
			new AdresseManager(dao).save(t.getAdresse());
			ps = initialisationRequetePreparee(conn, SQL_SAVE, true, t.getUsername(),t.getEmail(),t.getTelephone(),t.getPassword(), t.getAdresse().getId());
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
		}catch(MySQLIntegrityConstraintViolationException ms) {
			throw new DAOException("Nom ou email  est deja ututilisé, reessayez plus un autre",ms);
		}
		catch(NullPointerException | SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			fermeturesSilencieuses(rs, ps, conn);
		}
	}

	@Override
	public void delete(User t) throws DAOException {
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
	public void update(User t)  throws DAOException{
		// TODO Auto-generated method stub
		try {
			new AdresseManager(dao).update(t.getAdresse());
			conn = dao.getConnection();
			ps = initialisationRequetePreparee(conn, SQL_UPDATE, false,t.getUsername(),t.getEmail(),t.getTelephone(),t.getPassword(),t.getAdresse().getId(),t.getId());
			ps.execute();
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Echec de mise à jour",e);
		}finally{
			fermeturesSilencieuses(ps, conn);
		}
	}

	@Override
	public User findById(int id)  throws DAOException{
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
	public List<User> findAll()  throws DAOException{
		try {
			List<User> lstUser = new ArrayList<User>();
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
	/**
	 * @param rs resultSet � transformer dans une instance
	 */
	private User map(ResultSet rs) {
		User user = new User();
		try {
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setTelephone(rs.getString("numero"));
			user.setPassword(rs.getString("password"));
			user.setAdresse(new AdresseManager(dao).findById(rs.getInt("adresse_id")));
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException("Erreur lors du mapping du resultat");
		}


	}

	/**
	 * @param dao
	 */
	public UserManager(DAOFactory dao) {
		this.dao = dao;
	}
	
}
