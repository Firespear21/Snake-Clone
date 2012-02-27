/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeclone;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 *
 * @author Manning
 */
public class SpriteStore {
	private static SpriteStore spriteStore = new SpriteStore();
	private HashMap sprites = new HashMap();
	
	public static SpriteStore get() {
		return spriteStore;
	}
	
	public Sprite getSprite(String ref) {
		//Check for already existing sprite
		if (sprites.get(ref) != null) {
			return (Sprite) sprites.get(ref);
		}
		
		//Get new sprite
		BufferedImage sourceImage = null;
		
		try {
			URL url = this.getClass().getClassLoader().getResource(ref);
			if (url == null){
				fail("Can't Find Ref:"+ref);
			}
			sourceImage = ImageIO.read(url);
		} catch (IOException e) {
			fail("Failed to load: "+ref);
		}
		
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		Image image = gc.createCompatibleImage(sourceImage.getWidth(),sourceImage.getHeight(),Transparency.BITMASK);
		
		image.getGraphics().drawImage(sourceImage, 0, 0, null);
		
		Sprite sprite = new Sprite(image);
		sprites.put(ref, sprite);
		return sprite;
	}

	private void fail(String m) {
		System.err.println(m);
		System.exit(0);
	}
	
}
