/**
 * 
 */
package com.andyman.data;

/**
 * @author AndyMan
 *
 */
public enum Longueur {
	METRE(10000),DECAMETRE(1000),HECTOMETRE(100),KILOMETRE(10);
	private int unite;

	/**
	 * @return the unite
	 */
	public int getUnite() {
		return unite;
	}

	/**
	 * @param unite
	 */
	private Longueur(int unite) {
		this.unite = unite;
	}
	
}
