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
	private int speedOfSnake = -100;
	
	public SnakeEntity(SnakeClone game, String ref, int x, int y) {
		super(ref, x ,y);
		snakeTail = new ArrayList();
		this.game = game;
		this.setHorizontalMovement(0);
		this.setVerticleMovement(speedOfSnake);
	}
	public void collidedWith(Entity other) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
