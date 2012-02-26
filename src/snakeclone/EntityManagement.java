/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeclone;

import java.util.ArrayList;
import snakeclone.entity.Entity;

/**
 * This class better organizes the Entities to prevent clutter in the main
 * game loop
 * @author Manning
 */
public class EntityManagement {
	//Object that holds arrays that hold all entities in the current game
	private static EntityManagement entities = new EntityManagement();
	//arrays for those entities
	private ArrayList aliveEntities;
	private ArrayList deadEntities;
	
	public EntityManagement() {
		aliveEntities = new ArrayList();
		deadEntities = new ArrayList();
	}
	
	public EntityManagement get() {
		return entities;
	}
	
	public void addLivingEntity(Entity e){
		//adds a new entity
		aliveEntities.add(e);
	}
	
	public void addDeadEntity(Entity e) {
		//marks an entity for destruction
		deadEntities.add(e);
	}
	
	public void destroyDeadEntities() {
		//removes dead entities and clears the list
		aliveEntities.removeAll(deadEntities);
		deadEntities.clear();
	}
}
