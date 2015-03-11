package objects.types;

import java.awt.Graphics2D;
import java.io.Serializable;


public abstract class ObjectBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5314360693643155519L;
	public abstract void paint(Graphics2D g);
	public abstract ObjectBase clone();
	public abstract void checkMouseClick();
}
