import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import java.util.Date;
import java.util.Calendar;
import java.applet.AudioClip;
import java.awt.Color;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.JComboBox;

import acm.graphics.*;
public class SortANMTNC extends GCompound{
	

	public SortANMTNC(int n) {
		
		rectList=new GRect[n];
		title= new GLabel("");
		cmprlbl= new GLabel("");
		cmprnm= new GLabel("");
		ipsize= new GLabel("");
		iplbl= new GLabel("");
		timelbl =new GLabel("");
		time = new GLabel("");
		backgroundrect=new GRect(APPLICATION_WIDTH,APPLICATION_HEIGHT);
		backgroundrect.setLocation(0,0);
		backgroundrect.setColor(Color.BLACK);
		backgroundrect.setFilled(true);
		

		
		baseLine= new GLine(150,601,150,601);
		baseLine.setColor(Color.PINK);
		
		title.setColor(new Color(240,255,255));
		title.setFont("Serif-BOLD-25");
		
		cmprlbl.setColor(Color.ORANGE);
		cmprlbl.setFont("Serif-BOLD-15");
		
		iplbl.setColor(new Color(124,252,0));
		iplbl.setFont("Serif-BOLD-15");
		
		ipsize.setColor(Color.WHITE);
		ipsize.setFont("Serif-BOLD-15");
		
		timelbl.setColor(Color.MAGENTA);
		timelbl.setFont("Serif-BOLD-15");
		
		time.setColor(Color.PINK);
		time.setFont("Serif-BOLD-15");
		
		cmprnm.setColor(Color.YELLOW);
		cmprnm.setFont("Serif-BOLD-15");
		add(backgroundrect);
		add(title,380,30);
		add(iplbl,270,60);
		add(ipsize,340,60);
		add(cmprlbl,415,60);
		add(cmprnm,580,60);
		add(timelbl,650,60);
		add(time,740,60);
		add(baseLine);
		

		
		
	}
	public GRect[] getRectList() {
		return rectList;
	}
	

	
	public void rswap(int i, int j) {
		double ix,jx,iy,jy;
		ix=rectList[i].getX();
		iy=rectList[i].getY();
		
		jx=rectList[j].getX();
		jy=rectList[j].getY();
		Color clri,clrj;
		clri=rectList[i].getColor();
		clrj=rectList[j].getColor();
		rectList[i].setFilled(true);
		rectList[j].setFilled(true);
		changeColor(rectList[i],rectList[j],Color.GREEN);
		//rectList[i].setColor(Color.GREEN);
		//rectList[j].setColor(Color.GREEN);
		
		GRect tmp=rectList[i];
		rectList[i]=rectList[j];
		rectList[j]=tmp;

		pause(3);
		rectList[i].setLocation(ix, jy);
		rectList[j].setLocation(jx, iy);
		//rectList[i].setColor(Color.YELLOW);
		//rectList[j].setColor(Color.YELLOW);
		pause(3);
		changeColor(rectList[i],rectList[j],Color.CYAN);
		rectList[i].setFilled(false);
		rectList[j].setFilled(false);
		
	}
	public GLine getBaseLine() {
		return baseLine;
	}
	
	public void changeColor(GRect a, GRect b, Color clr) {
		a.setColor(clr);
		b.setColor(clr);
	}
	
	public void setRect(GRect r,int i) {
		double ix=rectList[i].getWidth();
		double ry=r.getY();
		rectList[i]=r;
		rectList[i].setLocation(ix, ry);
		
	}
	


	private GLine baseLine;
	private GRect[] rectList;
	private GRect backgroundrect;
	public GLabel title,cmprlbl,cmprnm,iplbl,ipsize,timelbl,time;
	private RandomGenerator gen=RandomGenerator.getInstance();
	//private AudioClip swapClip;
	private static final int APPLICATION_WIDTH=2000;
	private static final int APPLICATION_HEIGHT=800;

}
