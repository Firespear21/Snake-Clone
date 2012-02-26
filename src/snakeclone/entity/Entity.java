/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeclone.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import snakeclone.Sprite;
import snakeclone.SpriteStore;
/**
 *
 * @author Manning
 */
public abstract class Entity {
	protected double x;
	protected double y;
	protected int speedX;
	protected int speedY;
	protected Sprite sprite;
	private int speedOfSnake = 10;
	private Rectangle me = new Rectangle();
	private Rectangle him = new Rectangle();
	
	public Entity(String ref, int x, int y) {
		this.sprite = SpriteStore.get().getSprite(ref);
		this.x = x;
		this.y = y;
	}
	
	public void move(long delta) {
		//Moves the entity
		this.x += (delta * speedX)/1000;
		this.y += (delta * speedY)/1000;
	}
	
	public void setHorizontalMovement(int x){
		this.speedX = x;
	}
	
	public void setVerticleMovement(int y) {
		this.speedY = y;
	}
	
	public int getHorizontalMovement() {
		return speedX;
	}
	
	public int getVerticleMovement() {
		return speedY;
	}
	
	public void draw(Graphics g) {
		sprite.draw(g, (int) x, (int) y);
	}
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public void turnLeft() {
		if(speedX > 0) {
			speedX = 0;
			speedY = -speedOfSnake;
		} else if(speedX < 0){
			speedX = 0;
			speedY = speedOfSnake;
		} else if(speedY > 0) {
			speedY = 0;
			speedX = -speedOfSnake;
		} else if(speedY < 0) {
			speedY = 0;
			speedX = speedOfSnake;
		}
	}
	public void turnRight() {
		if(speedX > 0) {
			speedX = 0;
			speedY = speedOfSnake;
		} else if(speedX < 0){
			speedX = 0;
			speedY = -speedOfSnake;
		} else if(speedY > 0) {
			speedY = 0;
			speedX = speedOfSnake;
		} else if(speedY < 0) {
			speedY = 0;
			speedX = -speedOfSnake;
		}
	}
	
	public boolean collidesWith(Entity other) {
		me.setBounds((int) x, (int) y, sprite.getWidth(), sprite.getHieght());
		him.setBounds((int) other.x, (int) other.y, other.sprite.getWidth(), other.sprite.getHieght());
		return me.intersects(him);
	}
	
	public abstract void collidedWith(Entity other);
}
