package objectSectionThings;

public class Connection {
	public byte side;
	public ObjectSection child;
	
	public static final byte SIDE_BASE = 0;
	public static final byte SIDE_END = 1;
	
	public Connection(ObjectSection OS,byte side){
		this.side=side;
		child=OS;
	}
}
