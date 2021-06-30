import acm.graphics.GRect;

public class SRect extends GRect {
	
	public SRect(double w, double h) {
		super(w,h);
	}
	
	
	private void setLocation(double x) {
		this.setLocation(x,Y);
	}
	
	private static final double Y=500;
}