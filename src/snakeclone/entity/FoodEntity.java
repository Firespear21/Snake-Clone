/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeclone.entity;

import snakeclone.SnakeClone;
import java.util.Random;
/**
 *
 * @author Manning
 */
public class FoodEntity extends Entity{
	private SnakeClone game;
	//put food in a specific spot
	public FoodEntity(SnakeClone game, String ref, int x, int y) {
		super(ref, x ,y);
		this.game = game;
	}
	
	//Randomly generate a food piece on the map
	public FoodEntity(SnakeClone game, String ref){
		super(ref, new Random().nextInt(600), new Random().nextInt(600));
		this.game = game;
	}
	
	//Food doesnt move? So do nothing in the food move method
	public void move(long delta){
		//nothing
	}

	@Override
	public void collidedWith(Entity other) {
		if (other instanceof SnakeEntity){
			
		}
	}
	
}
