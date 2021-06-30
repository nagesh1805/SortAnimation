import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JComboBox;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class SortANMTN extends GraphicsProgram{
	public void init() {
		setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
		
		sort_algo = new JComboBox();
		String[] algo_list = {"Bubble sort", "Insertion sort","Selection sort","Merge sort","Quick sort","Heap sort"};
 		for(String algo : algo_list) {
			sort_algo.addItem(algo);
		}
		sort_algo.setSelectedItem(algo_list[0]);
		add(sort_algo,NORTH);
		go_button = new JButton("Go");
		add(go_button,NORTH);
		addActionListeners();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Go")) {
			sort_start=true;
		}
	}

	private double maxVal(double[] dlist) {
		double max = dlist[0];
		for (double d : dlist) {
			if(d>max) {
				max=d;
			}
		}
		return max;
	}
	
	private SortANMTNC setUp(double[] d) {
		
		double max=maxVal(d);
		colrs=new Color[d.length];
		for (int i=0;i<colrs.length;i++) {
			do {
				colrs[i]=gen.nextColor();
			}while(colrs[i].equals(Color.GREEN));
		}
		
		clipURL= getClass().getResource("Mc2.wav");
		swapClip=Applet.newAudioClip(clipURL);
		
		 //swapClip = getAudioClip(getDocumentBase(),"Mc2.wav");
		
		SortANMTNC scomp= new SortANMTNC(d.length);

		GRect[] rlist=scomp.getRectList();
		for(int i=0;i<d.length;i++) {
			rlist[i]=new GRect(R_WIDTH,R_HEIGHT*(d[i]/max));
			rlist[i].setLocation(X_OFFSET+i*R_WIDTH,Y_OFFSET-rlist[i].getHeight());
			//rlist[i].setFilled(true);
			rlist[i].setColor(Color.CYAN);
			scomp.add(rlist[i]);
			
		}
		scomp.getBaseLine().setEndPoint(X_OFFSET+d.length*R_WIDTH,Y_OFFSET+1);
		startTime = System.currentTimeMillis();
		c=0;
		
		

		add(scomp);
		pause(200);
		scomp.cmprlbl.setLabel("Number of comparisons:");
		scomp.timelbl.setLabel("Time passed: ");
		scomp.cmprnm.setLabel(" "+c);
		scomp.iplbl.setLabel("Input size: ");
		scomp.ipsize.setLabel(""+d.length);
		
		return scomp;
		
	}
	


	public void run() {
		
		double[] d= {835, 899, 689, 956, 809, 311, 612, 617, 193, 366, 544, 881, 187, 783, 422, 360, 835, 771, 152, 910, 807, 343, 675, 540, 105, 593, 192, 657, 200, 897, 294, 512, 748, 129, 739, 863, 239, 820, 918, 250, 439, 638, 484, 973, 179, 882, 872, 890, 647, 422, 265, 457, 756, 579, 710, 978, 686, 925, 569, 213, 594, 167, 933, 260, 839, 655, 715, 415, 224, 614, 553, 432, 585, 348, 127, 131, 677, 221, 722, 533, 809, 649, 134, 896, 689, 194, 192, 720, 509, 688, 682, 174, 641, 864, 605, 510, 648, 814, 148, 682, 126, 607, 270, 596, 974, 501, 149, 558, 924, 355, 831, 352, 984, 208, 372, 616, 723, 578, 353, 540, 248, 741, 275, 592, 359, 103, 395, 450, 151, 702, 108, 680, 503, 895, 987, 665, 583, 458, 692, 900, 869, 670, 381, 885, 106, 944, 546, 647, 928, 748, 873, 847, 329, 629, 966, 110, 273, 755, 472, 696, 806, 111, 531, 151, 259, 210, 570, 824, 674, 714, 996, 669, 354, 365, 256, 411, 415, 423, 654, 345, 614, 739, 181, 445, 706, 529, 733, 194, 926, 843, 456, 915, 740, 267, 704, 947, 143, 502, 800, 708};

		SortANMTNC scomp =setUp(d.clone());
		while (true) {
			
			if(sort_start) {
				System.out.println("Hi");
				remove(scomp);
				scomp =setUp(d.clone());
				pause(50);
				switch(sort_algo.getSelectedIndex()) {
				
				case 0:
					
					BubbleSortAnimation(d.clone(),scomp);
					climax(scomp.getRectList());
					pause(1000);
					sort_start=false;
					break;
				case 1:
					InsertionSortAnimation(d.clone(),scomp);
					climax(scomp.getRectList());
					pause(1000);
					sort_start=false;
					break;
				case 2:
					SelectionSortAnimation(d.clone(),scomp);
					climax(scomp.getRectList());
					pause(1000);
					sort_start=false;
					break;
					
				case 3:
					
					MergeSortAnimation(d.clone(),0,d.length-1,scomp);
					climax(scomp.getRectList());
					pause(1000);
					sort_start=false;
					break;
				case 4:
					 QuickSortAnimation(d.clone(),0,d.length-1,scomp);
					 climax(scomp.getRectList());
					pause(1000);
					sort_start=false;
					break;
				case 5:
					HeapSortAnimation(d.clone(),scomp);
					climax(scomp.getRectList());
					pause(1000);
					sort_start=false;
					break;
					
				
				}
			}
			
			pause(10);
		}
		
	
		
		


		
	}
	
	private void dswap(double [] dl, int i,int j) {
		double tmp;
		tmp=dl[i];
		dl[i]=dl[j];
		dl[j]=tmp;
	}
	
	private void assign(GRect a, GRect b) {
		double ax=a.getX();
		double by=b.getY();
		a=b;
		a.setLocation(ax,by);
	}
	

	
   private void climax(GRect[] rlist) {
		 for(int i=0;i<rlist.length;i++) {
			  rlist[i].setColor(Color.GREEN);
			  rlist[i].setFilled(true);
			  swapClip.play();
			  pause(3);
		 }
		 pause(20);
		 for(int i=0;i<rlist.length;i++) {
			 rlist[i].setColor(Color.CYAN);
			 rlist[i].setFilled(false);
		 }
		 swapClip.play();
		 pause(5);
		   
}	
	
	private void BubbleSortAnimation(double[] dlist,SortANMTNC scmp) {
		
		scmp.title.setLabel("***Bubble Sort Visualization***");
		
		int n=dlist.length,i,j;
		for (i=0;i<(n-1);i++) {
			boolean sorted = true;
			for(j=0;j<(n-i-1);j++){
				if(dlist[j]>dlist[j+1]) {
					scmp.time.setLabel(System.currentTimeMillis()-startTime+" milliseconds");
					scmp.rswap(j, j+1);
					dswap(dlist,j,j+1);
					swapClip.play();
					c=c+1;
					scmp.cmprnm.setLabel(" "+c);
				    sorted=false;
				}
			}
			if(sorted) {
				return;
			}
			
		}
	

	}
	
	
private void InsertionSortAnimation(double[] dlist,SortANMTNC scmp) {
		
		
		//startTime = System.currentTimeMillis();
	   scmp.title.setLabel("***Insertion Sort Visualization***");
	     
	   int ln=dlist.length;
		
		GRect[] rlist=scmp.getRectList();
		
		int i,j;
		for (j=1;j<ln;j++) {
			double key=dlist[j];
			GRect krc=rlist[j];
			//GRect r=krc;
			
			//double krcx=krc.getX();
			double krcy=krc.getY();
			i=j-1;
			while((i>=0)&&(dlist[i]>key)){
				scmp.time.setLabel(System.currentTimeMillis()-startTime+" milliseconds");
			//	r=rlist[i+1];
				scmp.rswap(i, i+1);
				dswap(dlist,i,i+1);
				swapClip.play();
				c=c+1;
				scmp.cmprnm.setLabel(" "+c);
				i=i-1;
				pause(1);
				
			}
			pause(1);
			
			krc.setColor(Color.GREEN);
			krc.setFilled(true);
			rlist[i+1].setColor(Color.GREEN);
			rlist[i+1].setFilled(true);
			
			pause(2);
			dlist[i+1]=key;
			rlist[i+1]=krc;
			rlist[i+1].setLocation(rlist[i+1].getX(),krcy);
		    
			swapClip.play();
			c=c+1;
			scmp.cmprnm.setLabel(" "+c);
			krc.setColor(Color.CYAN);
			krc.setFilled(false);
			rlist[i+1].setColor(Color.CYAN);
			rlist[i+1].setFilled(false);
			pause(3);
			
			
		}
	

	}



private int partition(double[] dlist, int sIx,int eIx,SortANMTNC scmp ) {
    double pvt=dlist[eIx];
    int i;
    i=sIx;
    GRect[] rcl=scmp.getRectList();
    for(int j=sIx;j<eIx;j++) {
    	c=c+1;
    	if (dlist[j]< pvt) {
    		dswap(dlist,i,j);
    		scmp.rswap(i, j);
    		i=i+1;
    		scmp.time.setLabel(System.currentTimeMillis()-startTime+" milliseconds");
    		swapClip.play();
    		scmp.cmprnm.setLabel(" "+c);
    		pause(1);
    	}

    }
    dswap(dlist,i,eIx);
    scmp.rswap(i, eIx);
    c=c+1;
	scmp.time.setLabel(System.currentTimeMillis()-startTime+" milliseconds");
	swapClip.play();
	scmp.cmprnm.setLabel(" "+c);
	pause(1);
    return i;
}

private void QuickSortAnimation(double[] dlist, int sIx,int eIx,SortANMTNC scmp) {
	
	scmp.title.setLabel("***Quick Sort Visualization***");
	int pvt;
	if (sIx < eIx) {
		pvt=partition(dlist,sIx,eIx,scmp);
		QuickSortAnimation(dlist,sIx,pvt-1,scmp);
		pause(2);
		QuickSortAnimation(dlist,pvt+1,eIx,scmp);
		
	}
	scmp.time.setLabel(System.currentTimeMillis()-startTime+" milliseconds");
	swapClip.play();
	c=c+1;
	scmp.cmprnm.setLabel(" "+c);
	swapClip.play();
	pause(1);
	
	
}



private void Merge(double[] dlist, int sIx, int eIx,int mid,SortANMTNC scmp) {
	
	double[] mrgd= new double[eIx-sIx+1];
	GRect[] mrgr = new GRect[eIx-sIx+1];
	GRect[] rlist=scmp.getRectList();

	int lptr=sIx;
	int rptr=mid+1;
	int i=0;
	while(lptr<=mid && rptr <= eIx) {
		c=c+1;
		if(dlist[lptr]<dlist[rptr]) {
			mrgd[i]=dlist[lptr];
		    mrgr[i]=rlist[lptr];
		   // Color clr=mrgr[i].getColor();
		    mrgr[i].setColor(Color.GREEN);
		    mrgr[i].setFilled(true);
		    pause(3);
		    mrgr[i].setLocation(150+4*(i+sIx),mrgr[i].getY());
		    mrgr[i].setColor(Color.BLUE);
		    pause(3);
		    mrgr[i].setFilled(false);
		    mrgr[i].setColor(Color.CYAN);
			scmp.time.setLabel(System.currentTimeMillis()-startTime+" milliseconds");
			scmp.cmprnm.setLabel(" "+c);
		    swapClip.play();
			i=i+1;
			lptr=lptr+1;
		}
		else {
			mrgd[i]=dlist[rptr];
			mrgr[i]=rlist[rptr];
			//Color clr=mrgr[i].getColor();
		    mrgr[i].setColor(Color.BLUE);
		    pause(3);
			mrgr[i].setLocation(150+4*(i+sIx),mrgr[i].getY());
			mrgr[i].setColor(Color.GREEN);
		    pause(3);
		    mrgr[i].setFilled(false);
		    mrgr[i].setColor(Color.CYAN);
			scmp.time.setLabel(System.currentTimeMillis()-startTime+" milliseconds");
			scmp.cmprnm.setLabel(" "+c);
		    swapClip.play();
			i=i+1;
			rptr=rptr+1;
		}
	}
	
	while(lptr <= mid) {
		mrgd[i]=dlist[lptr];

	    mrgr[i]=rlist[lptr];
	  //  Color clr=mrgr[i].getColor();
	    mrgr[i].setColor(Color.GREEN);
	    pause(3);
		mrgr[i].setLocation(150+4*(i+sIx),mrgr[i].getY());
		
		mrgr[i].setColor(Color.BLUE);
	    pause(3);
	    
	    mrgr[i].setFilled(false);
	    mrgr[i].setColor(Color.CYAN);

		scmp.time.setLabel(System.currentTimeMillis()-startTime+" milliseconds");
		c=c+1;
		scmp.cmprnm.setLabel(" "+c);
	    swapClip.play();

		i=i+1;
		lptr=lptr+1;
	}
	while(rptr <= eIx) {
		mrgd[i]=dlist[rptr];
		mrgr[i]=rlist[rptr];
		//Color clr=mrgr[i].getColor();
	    mrgr[i].setColor(Color.BLUE);
	    pause(3);
		
		mrgr[i].setLocation(150+4*(i+sIx),mrgr[i].getY());
		
	    mrgr[i].setColor(Color.GREEN);
	    pause(3);
	    
	    mrgr[i].setFilled(false);
	    mrgr[i].setColor(Color.CYAN);

		scmp.time.setLabel(System.currentTimeMillis()-startTime+" milliseconds");
		c=c+1;
		scmp.cmprnm.setLabel(" "+c);
	    swapClip.play();
		i=i+1;
		rptr=rptr+1;
	}
	
	for(int k = sIx;k<=eIx;k++) {
		dlist[k]=mrgd[k-sIx];
		rlist[k]=mrgr[k-sIx];
		scmp.time.setLabel(System.currentTimeMillis()-startTime+" milliseconds");
		c=c+1;
		scmp.cmprnm.setLabel(" "+c);
	    swapClip.play();
		
		pause(3);
	}

	
}



private void MergeSortAnimation(double[] dlist,int sIx, int eIx,SortANMTNC scmp) {

  scmp.title.setLabel("***Merge Sort Visualization***");
	
  if (sIx<eIx){
		int mid=(eIx+sIx)/2;
		MergeSortAnimation(dlist,sIx,mid,scmp);
		pause(2);
		MergeSortAnimation(dlist,mid+1,eIx,scmp);
		pause(2);
		Merge(dlist,sIx,eIx,mid,scmp);
	}
	
}


private void SelectionSortAnimation(double[] dlist,SortANMTNC scmp) {
	
	scmp.title.setLabel("***Selection Sort Visualization***");
	
	//int ln=dlist.length;
	
	int n=dlist.length;
	GRect[] rlist= scmp.getRectList();
	int i,j,min_ix;
	for (i=0;i<n-1;i++) {
		min_ix=i;
		for(j=i+1;j<n;j++){
			if(dlist[j]<dlist[min_ix]) {
				min_ix=j;
			}
			scmp.time.setLabel(System.currentTimeMillis()-startTime+" milliseconds");
			c=c+1;
			scmp.cmprnm.setLabel(" "+c);
			pause(1);
		}
		scmp.time.setLabel(System.currentTimeMillis()-startTime+" milliseconds");
	    scmp.rswap(i, min_ix);
	    dswap(dlist,i,min_ix);
		c=c+1;
		scmp.cmprnm.setLabel(" "+c);
	    swapClip.play();
	    pause(1);
		
	}
	

}

private void heapify(double[] d, int n, int i, SortANMTNC scmp) { 
    int largest = i; 
    int l = 2*i + 1; 
    int r = 2*i + 2; 

    if (l < n && d[l] > d[largest]) {
    	largest = l; 
    }
        

    if (r < n && d[r] > d[largest]) {
    	largest = r; 
    }
        

    if (largest != i) 
    { 
    	dswap(d,i,largest);
    	scmp.rswap(i, largest);
    	
		scmp.time.setLabel(System.currentTimeMillis()-startTime+" milliseconds");
		swapClip.play();
		c=c+1;
		scmp.cmprnm.setLabel(" "+c);

        heapify(d, n, largest,scmp); 
    } 
} 

  private void HeapSortAnimation(double[] d, SortANMTNC scmp) { 
	  
	scmp.title.setLabel("***Heap Sort Visualization***");
    int n = d.length; 

    for (int i = n / 2 - 1; i >= 0; i--) 
        heapify(d, n, i,scmp); 

    for (int i=n-1; i>=0; i--) { 
    	dswap(d,i,0);
    	scmp.rswap(i,0);
    	
		scmp.time.setLabel(System.currentTimeMillis()-startTime+" milliseconds");
		swapClip.play();
		c=c+1;
		scmp.cmprnm.setLabel(" "+c);
   
        heapify(d, i, 0,scmp); 
    } 
} 
	
	
	
	
	private static boolean sort_start=false;
	private JComboBox sort_algo;
	private JButton go_button;
	private static final int APPLICATION_WIDTH=1200;
	private static final int APPLICATION_HEIGHT=700;
	private static final int R_WIDTH=4;
	private static final int R_HEIGHT=500;
	private static final int X_OFFSET=150;
	private static final int Y_OFFSET=600;
	private AudioClip swapClip;
	private URL clipURL;
	
	private static long c=0;
	public Color[] colrs;
	private static long startTime = System.currentTimeMillis();
	private RandomGenerator gen=RandomGenerator.getInstance();

}
