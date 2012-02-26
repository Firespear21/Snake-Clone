/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeclone.entity;

import java.util.ArrayList;
import snakeclone.SnakeClone;

/**
 *
 * @author Manning
 */
public class SnakeEntity  extends Entity {
	protected ArrayList snakeTail;
	private SnakeClone game;
	
	public SnakeEntity(String ref, SnakeClone game) {
		super(ref, 30 ,30);
		snakeTail = new ArrayList();
		this.game = game;
	}
	@Override
	public void collidedWith(Entity other) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
