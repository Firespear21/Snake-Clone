/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeclone.entity;

/**
 *
 * @author Manning
 */
public class SnakeEntity  extends Entity {

	public SnakeEntity(String ref) {
		super(ref, 2 ,2);
	}
	@Override
	public void collidedWith(Entity other) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
