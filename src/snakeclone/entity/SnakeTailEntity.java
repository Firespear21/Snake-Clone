/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeclone.entity;

/**
 *
 * @author Manning
 */
public class SnakeTailEntity extends Entity {

	public SnakeTailEntity(String ref) {
		super(ref, 2 ,2);
	}
	
	public void collidedWith(Entity other) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
