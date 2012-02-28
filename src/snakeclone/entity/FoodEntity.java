/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeclone.entity;

import snakeclone.SnakeClone;
import java.util.Random;
import snakeclone.EntityManagement;
/**
 * This is the food entity it is eaten(destroyed) upon contact with the snake
 * and spawns at a specific or random point
 * @author Manning
 */
public class FoodEntity extends Entity{
	private SnakeClone game;
	private EntityManagement entities = new EntityManagement().get();
	//put food in a specific spot
	public FoodEntity(SnakeClone game, String ref, int x, int y) {
		super(ref, x ,y);
		this.game = game;
	}
	
	//Randomly generate a food piece on the map
	public FoodEntity(SnakeClone game, String ref){
		super(ref, new Random().nextInt(40) * 20, new Random().nextInt(30) * 20);
		this.game = game;
	}

	@Override
	public void collidedWith(Entity other) {
		if (other instanceof SnakeEntity){
			entities.addDeadEntity(this);
			game.foodLeft--;
		}
	}
	
	//Food doesnt move? So do nothing in the food move method
	public void move(long delta){
		//nothing
	}
	public void turnLeft(){
	}
	public void turnRight(){
	}
	
}
