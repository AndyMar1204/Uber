/**
 * 
 */
package com.andyman.beans;

/**
 * @author AndyMan
 *
 */
public class User {
	private Integer id;
	private String username,email,telephone,password;
	private Adresse adresse = new Adresse ();
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param username
	 * @param email
	 * @param telephone
	 * @param password
	 */
	public User(String username, String email, String telephone, String password) {
		this.username = username;
		this.email = email;
		this.telephone = telephone;
		this.password = password;
	}
	public User() {
		this.adresse = new Adresse();
	}
	
	
	/**
	 * @return the adresse
	 */
	public Adresse getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", username=").append(username).append(", email=").append(email)
				.append(", telephone=").append(telephone).append(", password=").append(password).append(", adresse=")
				.append(adresse.toString()).append("]");
		return builder.toString();
	}
	
	
}
