/**
 * 
 */
package com.andyman.utils;

import static com.andyman.persist.DdUtils.connectionUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import com.andyman.beans.Adresse;
import com.andyman.beans.Course;
import com.andyman.beans.Position;
import com.andyman.beans.User;
import com.andyman.beans.Voiture;
import com.andyman.data.Longueur;

/**
 * @author AndyMan
 *
 */
public class UserUtils {
	public static User connection(String username,String password) {
		password = passwordHasher(password);
		User u = connectionUser(username,password);
		if(u != null) {
			return u;
		}else return null;
	}
	
	public static Position positionToAdresse(Adresse adresse) {
		return autoPositionFinder();
	} 
	
	public static double ObtenirPrixCourse(User user, Voiture voiture, Adresse adresse) {
		Course c = new Course(user,adresse);
		
		return c.getPrix();
	}
	public static double distanceEntrePosition(Position pos1, Position pos2) {
		double x1 = pos1.getLongitude() - pos2.getLongitude();
		double x2 = pos1.getLatitude() - pos2.getLatitude();
		double x = (x1 > 0 ) ? x1 : -x1;
		double y = (x2 > 0 ) ? x2 : -x2;

		return(x+y);
	}

	public static Position autoPositionFinder() {
		double x1 = Math.random()+1;
		double x2 = Math.random()+1;
		Position pos = new Position(x1,x2);
		return pos;
	}
	
	public static double arrondiLongueur(double A, Longueur p) {
		int B;
		if(p==Longueur.METRE) {
			B=6;
		}else if(p==Longueur.DECAMETRE) {
			B=5;
		}else if(p==Longueur.HECTOMETRE) {
			B=4;
		}
		else {
			B=3;
		}
		double rep = (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
		return rep * p.getUnite();
	}
	public static double arrondi(double A, int B) {

		return  (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);

	}


	/**
	 * @param pos1 lieu de depart
	 * @param pos2 lieu d'arriver
	 * @return le prix pour deux distance, le prix et calculer par interval par 1000fc le kilometre;
	 */
	public static double prixCalculator(Position pos1 , Position pos2) {
		double distance = distanceEntrePosition(pos1,pos2);
		double prix = 10000 * distance;
		return arrondi(prix,2);	
	}

	/**
	 * @param password
	 * @return un mot de passe crypte sous hachage SHA-224
	 */
	public static String passwordHasher(String password) {
		byte[] inputBytes=password.getBytes();

		String hashValue="";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-224");
			messageDigest.update(inputBytes);
			byte[] digestBytes= messageDigest.digest();
			hashValue=DatatypeConverter.printHexBinary(digestBytes).toLowerCase();
		}catch(NoSuchAlgorithmException ex) {

			try {
				throw new Exception("Algorithme de hachage est inconnu "+ex.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				throw new Exception("Erreur lors du hachage "+e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return hashValue;
	}
	public static String passwordSetter(String pass1,String pass2) throws Exception {
		if(pass1.matches(pass2)) {
			return passwordHasher(pass1);
		}else
			throw new Exception("Mots de passes ne sont pas correct");
	}
	

}
