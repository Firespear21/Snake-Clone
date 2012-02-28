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
public class SnakeEntity extends Entity {

	protected ArrayList snakeTail;
	private SnakeClone game;
	private long lastX;
	private long lastY;

	public SnakeEntity(SnakeClone game, String ref, int x, int y) {
		super(ref, x, y);
		snakeTail = new ArrayList();
		this.game = game;
		this.setHorizontalMovement(0);
		this.setVerticleMovement(speedOfSnake);
	}

	@Override
	public void move(long delta){
		lastX = x;
		lastY = y;
		super.move(delta);
	}
	@Override
	public void collidedWith(Entity other) {
		if (other instanceof FoodEntity) {
			SnakeTailEntity tailSeg = new SnakeTailEntity("snakeclone/Res/BlueSnakeBody.gif", this);
			entities.addLivingEntity(tailSeg);
			snakeTail.add(tailSeg);
		}
		if (other instanceof SnakeTailEntity) {
			entities.addDeadEntity(this);
			game.loose();
		}
	}

	public long getLastX() {
		return lastX;
	}

	public void setLastX(long lastX) {
		this.lastX = lastX;
	}

	public long getLastY() {
		return lastY;
	}

	public void setLastY(long lastY) {
		this.lastY = lastY;
	}

	public ArrayList getSnakeTail() {
		return snakeTail;
	}
	
}
