/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeclone.entity;

import java.util.ArrayList;
import snakeclone.EntityManagement;

/**
 *
 * @author Manning
 */
public class SnakeTailEntity extends Entity {
	private final SnakeEntity owner;
	private long lastX;
	private long lastY;

	public SnakeTailEntity(String ref, SnakeEntity owner) {
		super(ref, 2 ,2);
		this.owner = owner;
	}
	
	@Override
	public void move(long delta){
		//Chages to the new old
		lastX = x;
		lastY = y;
		
		/*
		 * Gets the owners tail and checks to see if it is the first tail
		 * in the sequence. If it is then it users its owners last coridinates
		 * to generate its position if it isnt then it will use the previous
		 * tail segment to generate its new position
		 */
		ArrayList ownerTail = owner.getSnakeTail();
		if (ownerTail.indexOf(this) == 0) {
		x = owner.getLastX();
		y = owner.getLastY();
		} else {
			int previous = ownerTail.indexOf(this) - 1;
			SnakeTailEntity previousSeg = (SnakeTailEntity) ownerTail.get(previous);
			x = previousSeg.getLastX();
			y = previousSeg.getLastY();
		}
	}
	
	public void collidedWith(Entity other) {
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
}
