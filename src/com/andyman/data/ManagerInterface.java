/**
 * 
 */
package com.andyman.data;

import java.util.List;

/**
 * @author AndyMan
 *
 */
public interface ManagerInterface<T> {
	/**
	 * @param t enregistre une instance
	 */
	void save(T t);
	/**
	 * @param t supprime une instance
	 */
	void delete(T t);
	/**
	 * @param t mets � jour  une instance
	 */
	void update(T t);
	/**
	 * @param id
	 * @return une instance � partir de son id
	 */
	
	T findById(int id);
	/**
	 * @param id
	 * @return toutes les instances sauvegard�es
	 */
	List<T> findAll();
}
