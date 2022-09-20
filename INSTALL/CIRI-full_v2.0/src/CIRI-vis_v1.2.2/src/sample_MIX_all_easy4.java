import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class sample_MIX_all_easy4 {
	public String t;
	public int l;
	public int print_cov;
	public int print_splice;
	public int print_bsj;
	public int print_ro;
	public int print_less;
	public List<Integer> library;
	public int readlength;
	public List<String> output=new ArrayList<String>();
	public String given;
	public String giveniso;
	public int maxiso;
	public int ran_seed;  //??+ 

	
	public sample_MIX_all_easy4(String t1,int u1,int u2, int u3,int u4, int u5, int u6, List<Integer> library1,int readlength1,String given1,String giveniso1,int maxiso1,int ran_seed1){ //??+ 
		this.t=t1;
		this.l=u1;
		this.print_cov=u2;
		this.print_splice=u3;
		this.print_bsj=u4;
		this.print_ro=u5;
		this.print_less=u6;
		this.library=library1;
		this.readlength=readlength1;
		this.given=given1;
		this.giveniso=giveniso1;
		this.maxiso=maxiso1;
		this.ran_seed=ran_seed1;     //??+ 

		
    }
	float bottom;
	double width;
	int zone_num;
	int cir_num;
	public String Combinestate;
	public Graphics2D paint(Graphics2D g)throws IOException {
		int len=l;
		float gao=100;
		int x=60;
		int step=40;
		Rectangle2D rect1;
		    Color x1=new Color(0,0,255,40);
			Color x2=new Color(0,0,238,70);
			Color x3=new Color(0,0,205,120);
			Color x4=new Color(0,0,170,180);
			
		    Color z1=new Color(255,0,0,40);
		    Color z2=new Color(238,0,0,70);
		    Color z3=new Color(205,0,0,120);
	        Color z4=new Color(170,0,0,180);
	        
	        Color darkblue=new Color(53,151,143);
	        
	        Color darkblack=new Color(100,100,100);
	        
	     //   List<Color> randomcolor=new ArrayList<Color>();
	        
	        List<Color> cgroup=new ArrayList<Color>();
	        Color g1=new Color(141,211,199,120);
	        cgroup.add(g1);
	        Color g2=new Color(255,255,179,120);
	        cgroup.add(g2);
	        Color g3=new Color(190,186,218,120);
	        cgroup.add(g3);
	        Color g4=new Color(251,128,114,120);
	        cgroup.add(g4);
	        Color g5=new Color(128,177,211,120);
	        cgroup.add(g5);
	        Color g6=new Color(253,180,98,120);
	        cgroup.add(g6);
	        Color g7=new Color(179,222,105,120);
	        cgroup.add(g7);
	        Color g8=new Color(252,205,229,120);
	        cgroup.add(g8);
	        Color g9=new Color(217,217,217,120);
	        cgroup.add(g9);
	        Color g10=new Color(188,128,189,120);
	        cgroup.add(g10);
	        Color g11=new Color(204,235,197,120);
	        cgroup.add(g11);
	        Color g12=new Color(166,206,227,120);
	        cgroup.add(g12);
	        Color g13=new Color(31,120,180,120);
	        cgroup.add(g13);
	        Color g14=new Color(178,223,138,120);
	        cgroup.add(g14);
	        Color g15=new Color(51,160,44,120);
	        cgroup.add(g15);
	        Color g16=new Color(251,154,153,120);
	        cgroup.add(g16);
	        Color g17=new Color(227,26,28,120);
	        cgroup.add(g17);
	        Color g18=new Color(253,191,111,120);
	        cgroup.add(g18);
	        Color g19=new Color(255,127,0,120);
	        cgroup.add(g19);
	        Color g20=new Color(202,178,214,120);
	        cgroup.add(g20);
	        Color g21=new Color(106,61,154,120);
	        cgroup.add(g21);
	        Color g22=new Color(255,255,153,120);
	        cgroup.add(g22);
	        Color g23=new Color(177,89,40,120);
	        cgroup.add(g23);


	        
	        Color red=new Color(255,0,0,140);
	        Color grey=new Color(200,200,200,150);
            Color yellow2=new Color(255,250,0,30);
            Color gray2=new Color(100,100,100,30);
            Color orange2=new Color(255,255,0,140);
            Color blue2=new Color(65,105,225);
     		// Font zt1=new Font ("TimesRoman",Font.BOLD,30);
            int w=8;
    		 Font zt11=new Font ("TimesRoman",Font.BOLD,w);
    		 Font zt2=new Font ("TimesRoman",Font.BOLD,6);
    		//Font zt3=new Font ("TimesRoman",Font.BOLD,25);
		RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHints(renderHints);
		String chr=null;
  //		 FileReader text = new FileReader("/Users/zhengyi/Documents/data5/test11/sample-"+k);
  //		 LineNumberReader reader=new LineNumberReader(text);
 // 		 String t=reader.readLine();
		
		
		
  	   	Arc2D arc;
  	    FontRenderContext frc=g.getFontRenderContext();
  	    TextLayout layout;
 // 	layout=new TextLayout("Sample-"+k,zt1,frc);
 // 	layout.draw(g, 300, 50);
  	    double zonestart;
  	    double zoneend;
  	    

  		 int o=0;
		 int start;
		 int end;
		 String ex_pos;
		 String read_pos;
		 String read_cov;
		 String pre_ex;
		 String longread_pos;
		 List<int[]> cirexon=new ArrayList<int[]>();
		 List<int[]> cirexon2=new ArrayList<int[]>();
		 List<Integer> readnum2=new ArrayList<Integer>();
		 String strain="n/a";
		 String geneid="n/a";

		 int ro_number=0;
		 int k=0;
		 int o1=0;
			 String[] tmp=t.split("	");
			 System.out.println(tmp[0]);
			 start=Integer.parseInt(tmp[2]);
			 end=Integer.parseInt(tmp[3]);
			 ex_pos=tmp[4];
			 read_pos=tmp[7];
			 read_cov=tmp[6];
			 strain=tmp[10];
			 geneid=tmp[9];

			 if(tmp.length>8){
			 longread_pos=tmp[8];
			 if(read_pos.equals("n/a")!=true&&longread_pos.equals("n/a")!=true)
				 Combinestate="as,ro";
			 else if(read_pos.equals("n/a")!=true)
				 Combinestate="only_as";			 
			 else
				 Combinestate="only_ro";
			 }
			 else{
				 longread_pos="n/a";
				 Combinestate="only_as";			 
			 }
			 
			Random r1=new Random(ran_seed+start+end);   //??

			 int[] cov_bsj=new int[end-start+1];
		//	 read_pos=read_pos.replace("|", "&");
		//	 read_pos=read_pos.replace("(", "@");
		//	 read_pos=read_pos.replace(")", "%");
			 
			// if(tmp[8].equals("N/A")!=true){//高远格式
	   		//		String[] b1=tmp[8].split(":");
	   		//		String[] c1=b1[1].split("-");
	   		//		p1=Integer.parseInt(c1[0]);
	   		//	}
	   		//	else{
	   		//		p1=start[o]-15000;
	   		
	   		//	}
	   		//	if(tmp[9].equals("N/A")!=true){//高远格式
	   		//		String[] b1=tmp[9].split(":");
	   		//		String[] c1=b1[1].split("-");
	   		//		p4=Integer.parseInt(c1[1]);
	   		//	}
	   		//	else{
	   		//		p4=end[o]+15000;	   			
	   		//	}
	   		//

	   			chr=tmp[1];
		 int exonmaxl=0;
		 int[] ex_posall=new int[500];
		 int[] ex_lenall=new int[500];
		    while(o<500){
		    	ex_posall[o]=-1;
		    	ex_lenall[o]=-1;
		    	o++;
		    }
		    o=0;
             if(ex_pos.equals("n/a")!=true&&ex_pos.equals("")!=true){
        	 String[] tmp1=ex_pos.split(",");
        	 while(o<tmp1.length){
        		 String[] tmp2=tmp1[o].split(":");
        		 ex_posall[o]=Integer.parseInt(tmp2[0]);
        		 ex_lenall[o]=Integer.parseInt(tmp2[1])-Integer.parseInt(tmp2[0]);
        		 o++;
        	 }
             }

		    o=0;
		    	//获得所有外显子的位置
		
		  
		    while(ex_posall[o]>0){
		    	o++;	    	
		    }
         int m=o;
      //   System.out.println(m);
         //获得高远预测外显子：
         int[] pre_pos=new int[500];
         int[] pre_len=new int[500];
         
    	 tmp[5]=tmp[5].replace("(ICF)", "");
    	 tmp[5]=tmp[5].replace("(intron_retention)", "");
		 pre_ex=tmp[5];
         o=0;
         if(given!=null){
        	 pre_ex=given.replace("-", ":");
         }
         if(pre_ex.equals("n/a")!=true){
    	 String[] tmp_pre=pre_ex.split(",");
    	 while(o<tmp_pre.length){
    		 String[] tmp_pre2=tmp_pre[o].split(":");
    		 pre_pos[o]=Integer.parseInt(tmp_pre2[0]);
    		 pre_len[o]=Integer.parseInt(tmp_pre2[1])-Integer.parseInt(tmp_pre2[0]);
    		 int[] cire={Integer.parseInt(tmp_pre2[0]),Integer.parseInt(tmp_pre2[1])};
    		 cirexon.add(cire);
    		 cirexon2.add(cire);
    		 
    		 
    		// Color tmpc=new Color(cl1,cl2,cl3,50);
    		// randomcolor.add(tmpc);
    		 exonmaxl=Math.max(pre_len[o], exonmaxl);
    		 o++;
    	 }
         }
         int m2=o;
         o=0;
         int[] cov;
         int covh1=0;
         String[] tmp_cov=read_cov.split(",");
         cov=new int[tmp_cov.length];
         if(read_cov.equals("n/a")!=true){
         while(o<tmp_cov.length){
        	 cov[o]=Integer.parseInt(tmp_cov[o]);
        	 covh1=Math.max(cov[o], covh1);
        	 o++;
         	}//coverage
         }
         Map<String, Integer> map2 = new HashMap<String, Integer>();
         List<int[]> easygroup=new ArrayList<int[]>();

         
         o=0;
         String[] tmpstring=read_pos.split("<");
         k=tmpstring.length-1;
         String[] classname=new String[k];
         int[][][][] readstart=new int[k][2][30000][10];//分组， +/- 0/1 , read, read区
         int[][][][] readend=new int[k][2][30000][10];
         int[][][][] readq=new int[k][2][30000][10];
         int[] zone1=new int[100];
         int[] zone2=new int[100];
         int[] tmpstart=new int[200000];
         int[] tmpend=new int[200000];
         int[] readnum=new int[k];
         int[] readstate=new int[k];

         int gg=0;
         
         //long_read
         int[][] readstartlong=new int[5000][20];//group,readnum,read_fragment,
         int[][] readendlong=new int[5000][20];
         if(longread_pos.equals("n/a")!=true){
     	 String[] tmpstring1=longread_pos.split("&&");
     	 ro_number=tmpstring1.length;
     	 o1=0;
     	while(o1<tmpstring1.length){
    		int o12=0;
    //	    System.out.println("o1="+o1); 
    		String[] tmpstring2=tmpstring1[o1].split("##");
    		map2.put(tmpstring2[0], 0);
    		String[] tmpstring3=tmpstring2[1].split(",");
    		while(o12<tmpstring3.length){
    			String[] tmpstring4=tmpstring3[o12].split("-");
    			readstartlong[o1][o12]=Integer.parseInt(tmpstring4[0]);
    			readendlong[o1][o12]=Integer.parseInt(tmpstring4[1]);
    		//	System.out.println(readend[o][o1][o12]+"= "+"["+o+"]["+o1+"]["+o12+"]");
    			tmpstart[gg]=readstartlong[o1][o12];
    			tmpend[gg]=readendlong[o1][o12];
    			gg++;
    			o12++;
    		}
    		o1++;
    	}
         }

         //short_reads
         if(read_pos.equals("n/a")!=true){
         while(o<k){
        	 Map<String,Integer> tmpbsj=new HashMap<String,Integer>();
        	 String[] tmpstring1=tmpstring[o+1].split("::");
        	 classname[o]=tmpstring1[0];
        	 if(tmpstring1[1].equals(">")!=true){
        	 tmpstring1[1]=tmpstring1[1].replace(")","),");
        	 tmpstring1[1]=tmpstring1[1].replace("),(",")(");
        	 tmpstring1[1]=tmpstring1[1].replace(",>","");
        	 o1=0;
        	 int o1z=0;
        	 String[] tmpstring2=tmpstring1[1].split(",");
        		 while(o1<tmpstring2.length){
        		 String[] tmpstring3=tmpstring2[o1].split("\\(");
        		 List<int[]> tmpl=new ArrayList<int[]>();
    			 if(tmpstring3[1].substring(0,1).equals("\\)")!=true&&!tmpbsj.containsKey(tmpstring3[0])){
    				 String[] tmpstring4=tmpstring3[1].split("\\|");
    				 int z=0;
    				 while(z<tmpstring4.length-1){
    					 if(tmpstring4[z].equals("NA")!=true){
    					 String[] tmpstring5=tmpstring4[z].split("!");
    					 readq[o][0][o1z][z]=Integer.parseInt(tmpstring5[1]);
    					 String[] tmpstring6=tmpstring5[0].split("-");
            			 readstart[o][0][o1z][z]=Integer.parseInt(tmpstring6[0]);
            			 readend[o][0][o1z][z]=Integer.parseInt(tmpstring6[1]);
    					 tmpstart[gg]=Integer.parseInt(tmpstring6[0]);
    					 tmpend[gg]=Integer.parseInt(tmpstring6[1]);
    					 int[] tmpse={tmpstart[gg],tmpend[gg]};
    					 tmpl.add(tmpse);
    				//	 System.out.println("+	"+tmpstart[gg]+"-"+tmpend[gg]);
    					 gg++;
    					 }
    					 z++;
    				 }
    				}
    			 if(tmpstring3[2].substring(0,1).equals("\\)")!=true&&!tmpbsj.containsKey(tmpstring3[0])){
    				 String[] tmpstring4=tmpstring3[2].split("\\|");
    				 int z=0;
    				 while(z<tmpstring4.length-1){
    					 if(tmpstring4[z].equals("NA")!=true){
    					 String[] tmpstring5=tmpstring4[z].split("!");
    					 readq[o][1][o1z][z]=Integer.parseInt(tmpstring5[1]);
    					 String[] tmpstring6=tmpstring5[0].split("-");
            			 readstart[o][1][o1z][z]=Integer.parseInt(tmpstring6[0]);
            			 readend[o][1][o1z][z]=Integer.parseInt(tmpstring6[1]);
    					 tmpstart[gg]=Integer.parseInt(tmpstring6[0]);
    					 tmpend[gg]=Integer.parseInt(tmpstring6[1]);
    					 int[] tmpse={tmpstart[gg],tmpend[gg]};
    					 tmpl.add(tmpse);
    					 gg++;
    					 }
    					 z++;
    				 }
    				}
    			 if(!tmpbsj.containsKey(tmpstring3[0]))
    				 o1z++;
        		 tmpbsj.put(tmpstring3[0], 0);

        		 if(!map2.containsKey(tmpstring3[0])){
        			 
            		 int zi=0;
            	//	 System.out.print(tmpstring3[0]+"!!!");
            	//	 while(zi<tmpl.size()){
            	//		 System.out.print(tmpl.get(zi)[0]+"-"+tmpl.get(zi)[1]+",");
            			 zi++;
            //		 }
            	//	 System.out.println("");

        			 Exon_length test=new Exon_length(tmpl,start,end);
        			 test.takemerge();
        			 
        			 int u=0;
        			 while(u<tmpl.size()){
        			//	 System.out.print(tmpl.get(u)[0]+"-"+tmpl.get(u)[1]+",");
        				 int z=tmpl.get(u)[0];
        				 while(z<tmpl.get(u)[1]){
        					 cov_bsj[z-start]++;
        					 z++;
        				 }
        				 u++;
        			 }
    			//	 System.out.println("");
        			 map2.put(tmpstring3[0],0);
        		 }

    			 o1++;

        	 }
         }//获得read连接信息
        	 readnum[o]=tmpbsj.size();
        	 o++;
}
         }
        //尝试获得zone
         o=0;
   //      while(o<gg){
   //     	 System.out.println(tmpstart[o]+"-"+tmpend[o]);
   //++;
   //      }

         
         for(int a1=0;a1<gg;a1++){
       	 for(int a2=0;a2<gg-a1-1;a2++){
       		 if((tmpstart[a2]+tmpend[a2])/2>(tmpstart[a2+1]+tmpend[a2+1])/2){
       			 int temp1=tmpstart[a2];
        	     int temp2=tmpend[a2];
        	     tmpstart[a2]=tmpstart[a2+1];
       			 tmpend[a2]=tmpend[a2+1];
       			 tmpstart[a2+1]=temp1;
       			 tmpend[a2+1]=temp2;
       			 }
        		}
        	}
         
         Exon_length test2=new Exon_length(cirexon2,start,end);
         test2.takemerge();
         
         o=0;
         while(o<cirexon2.size()){
        	 zone1[o]=cirexon2.get(o)[0];
        	 zone2[o]=cirexon2.get(o)[1];
        	 o++;
       // 	 System.out.println("zone add");
         }
         
         o=0;
        
        	 while(o<gg){
        		 int n1=0;
        		 int n2=0;
        	           		 while(zone1[n1]!=0&&n2!=1){
        	           			 if(tmpend[o]>=zone1[n1]&&tmpstart[o]<=zone2[n1]){
        	           				 zone1[n1]=Math.min(tmpstart[o], zone1[n1]);
        	            		     zone2[n1]=Math.max(tmpend[o], zone2[n1]);
        	            				 n2=1;
        	            			 }
        	            			 n1++;
        	            		 }
        	            		 if(n2==0){
        	            			 zone1[n1]=tmpstart[o];
        	            			 zone2[n1]=tmpend[o];
        	       		 }
        	            		 o++;
         
         }
         //看看zone:，排排序
       int  n1=0;
       while(zone1[n1]!=0){
        	 n1++;
         }
      //   System.out.println(n1);
         for(int a1=0;a1<n1-1;a1++){
       	 for(int a2=0;a2<n1-1-a1;a2++){
       		 if(zone1[a2]>zone1[a2+1]){
       			 int temp1=zone1[a2];
        	     int temp2=zone2[a2];
       			 zone1[a2]=zone1[a2+1];
       			 zone2[a2]=zone2[a2+1];
       			 zone1[a2+1]=temp1;
        		 zone2[a2+1]=temp2;
       			 }
        	 }
        	}
         n1=0;
       //  while(zone1[n1]!=0){
      //  	 System.out.println("zone? "+n1+" "+zone1[n1]+"--"+zone2[n1]);
      //  	 n1++;
      //   }

         
         int v;//随意用的
         o=0;
         while(zone1[o+1]!=0){
        	 if(zone2[o]>zone1[o+1]){
        		 zone2[o]=Math.max(zone2[o], zone2[o+1]);
        		 v=o+1;
        		 while(zone1[v]!=0){
        			 zone1[v]=zone1[v+1];
        			 zone2[v]=zone2[v+1];
        			 v++;
        		 }
        	 }
        	 o++;
       }
         n1=0;
         int al=0;
         while(zone1[n1]!=0){
        //	 System.out.println("zone "+n1+" "+zone1[n1]+"--"+zone2[n1]);
        	 al=al+zone2[n1]-zone1[n1];
        	 n1++;
         }
          o=0;
          zone_num=n1;
          if(n1==0){
        	  return g;
          }
       //   System.out.println("high= "+gao);
          // this is bone:
          
        BasicStroke stroke=new BasicStroke(4);
        g.setStroke(stroke);
        Line2D l=new Line2D.Double(x,(gao),len+x,(gao));
        g.draw(l);//
  //      layout=new TextLayout("Exon",zt11,frc);
  //      layout.draw(g, (float)(x-45), (float)(gao+2));
  //      layout=new TextLayout("Cirexon",zt11,frc);
  //      layout.draw(g, (float)(x-45), (float)(gao-6));
        layout=new TextLayout(map2.size()+"",zt2,frc);
        layout.draw(g, (float)(x-55), (float)(gao+step+10));


        stroke=new BasicStroke(2);
        g.setStroke(stroke);
        l=new Line2D.Double(x-2,(gao)+4,x-2,(gao)-4);
        g.draw(l);
        l=new Line2D.Double(x+2+len,(gao)+4,x+2+len,(gao)-4);
        g.draw(l);
        
        //
        double[] pl=new double[n1];
        double[] pp=new double[n1];
        int bsj_cov_max=10;
        
        o=0;
        
        
        while(o<n1){
        	pl[o]=(len*(zone2[o]-zone1[o]))/al;
        	if(o==0)
        		pp[o]=x-30;
        	else{
                pp[o]=pp[o-1]+15+pl[o-1];
        	}
        	
        	int t=Math.max(zone1[o],start);
        	while(t<Math.min(zone2[o],end)){
        		bsj_cov_max=Math.max(cov_bsj[t-start], bsj_cov_max);
        		t++;
        	}
        	o++;
        }
        o=0;
        zonestart=pp[o];
        zoneend=pp[n1-1]+pl[n1-1];
        while(o<n1){
        	g.setColor(Color.black);
        	stroke=new BasicStroke(4);
            g.setStroke(stroke);
        	l=new Line2D.Double(pp[o],(gao)+step,pp[o]+pl[o],(gao)+step);
        //	g.draw(l);
        	l=new Line2D.Double(pp[o],(gao)+(step+8),pp[o]+pl[o],(gao)+(step+8));
        	g.draw(l);
        	stroke=new BasicStroke(1);
            g.setStroke(stroke);
            l=new Line2D.Double(pp[o]-2,(gao)+(step+3),pp[o]-2,(gao)+(step+13));
            g.draw(l);
            l=new Line2D.Double(pp[o]+pl[o]+2,(gao)+(step+3),pp[o]+pl[o]+2,(gao)+(step+13));
            g.draw(l);
            width=pp[o]+pl[o]+2;
            g.setColor(Color.blue);
            l=new Line2D.Double(pp[o]-2,(gao)+(step-7),x+(((zone1[o]-start)*len)/(end-start)),(gao)+3);
     //       g.draw(l);
            l=new Line2D.Double(pp[o]+pl[o]+2,(gao)+(step-7),x+(((zone2[o]-start)*len)/(end-start)),(gao)+3);
            
      //      g.draw(l);
            Polygon po=new Polygon();
            double[] a1={pp[o]-2,x+(((zone1[o]-start)*len)/(end-start)),x+(((zone2[o]-start)*len)/(end-start)),pp[o]+pl[o]+2};
            double[] b1={(gao)+(step-7),(gao)+6,(gao)+6,(gao)+(step-7)};
            int vv=0;
            while(vv<a1.length){
            	po.addPoint((int)a1[vv],(int)b1[vv]);
            	vv++;
            }
            //g.draw(po);
            g.setColor(grey);
            g.fill(po);
            g.setColor(darkblue);
            double grb=(zone2[o]-zone1[o])/pl[o];
            double bsj_cov_h=gao+step+20;
            int nl=0;
            int go=0;
        	double hi=0;
            while(nl<pl[o]){
            	if(go<=grb*nl) 
            		hi=0;
            	while(go<=grb*nl){
            		int vi=go+zone1[o]-start;
            		if(vi>=0&&vi<cov_bsj.length){
            	//	System.out.print(vi+":"+cov_bsj[vi]+", ");
            		hi=Math.max(hi, cov_bsj[vi]);
            		}
            		go++;
            	}
            	//draw;
            	double hj=hi*50/bsj_cov_max;
            	l=new Line2D.Double(pp[o]+nl,bsj_cov_h,pp[o]+nl,bsj_cov_h+hj);
            	g.draw(l);
            	nl++;
            }
            //drwa_cov_bsj;
           //  layout=new TextLayout((zone1[o]-start)+" +"+(zone2[o]-zone1[o]),zt2,frc);
           // float a=(float)(pp[o]);
           // float b=(float)((gao)+step-uu*0);
           // layout.draw(g,a,b);
            int s=0;
            //画区域上外显子
            double push=0;
            
            while(s<m2){
            	int pstart,pend;
            	if(pre_pos[s]<=zone2[o]&&pre_pos[s]+pre_len[s]>=zone1[o]){
            		if(pre_pos[s]<zone1[o])
            			pstart=zone1[o];
            		else
            			pstart=pre_pos[s];
            		if(pre_pos[s]+pre_len[s]>zone2[o])
            			pend=zone2[o];
            		else
            			pend=pre_pos[s]+pre_len[s];
                    g.setColor(Color.gray);
            	    rect1=new Rectangle2D.Double(pp[o]+(((pstart-zone1[o])*pl[o])/(zone2[o]-zone1[o])),(gao)+(step+4),((pend-pstart)*pl[o])/(zone2[o]-zone1[o]),8);
            	    g.draw(rect1);
            	    g.setColor(cgroup.get(s));
            	    g.fill(rect1);
            	    layout=new TextLayout(String.valueOf(pre_len[s]+1),zt2,frc);
                	Rectangle2D rect2 = layout.getBounds();
                	double h1=rect2.getHeight();
                	double w1=rect2.getWidth();
                	rect1=new Rectangle2D.Double(pp[o]+push, gao+step-h1-1, w1+1, h1+2);
                	g.fill(rect1);
                	g.setColor(Color.black);
                    float a=(float)(pp[o]+push);
                    float b=(float)(gao+step);
                    layout.draw(g,a,b);
                    push=push+w1+1+1;
            	    
            	}
            	s++;
            }
           s=0;
           
           
   //         while(s<m){
  //          	int pstart,pend;
  //          	if(ex_posall[s]<=zone2[o]&&ex_posall[s]+ex_lenall[s]>=zone1[o]){
  //          		if(ex_posall[s]<zone1[o])
  //          			pstart=zone1[o];
  //          		else
  //          			pstart=ex_posall[s];
  //          		if(ex_posall[s]+ex_lenall[s]>zone2[o])
  //          			pend=zone2[o];
  //          		else
  //          			pend=ex_posall[s]+ex_lenall[s];
  //                  g.setColor(Color.gray);
  //          	    rect1=new Rectangle2D.Double(pp[o]+(((pstart-zone1[o])*pl[o])/(zone2[o]-zone1[o])),(gao)+(step-4),((pend-pstart)*pl[o])/(zone2[o]-zone1[o]),8);
  //          	    g.draw(rect1);
  //          	    g.setColor(red);
  //          	    g.fill(rect1);
  //          	}
  //          	s++;
  //          }
            o++;
        }
  		int maxarc=0;

        if(print_splice==1){

        stroke=new BasicStroke(1);
        g.setStroke(stroke);
        g.setColor(Color.black);
  		o=0;
  		
  		//set_class=4
  		while(o<k-1){
  			if(readnum[o]*100/map2.size()<5){
  				readstate[o]=0;
  			}
  			else if(readnum[o]*100/map2.size()<15){
  				readstate[o]=1;
  			}
  			else if(readnum[o]*100/map2.size()<30){
  				readstate[o]=2;
  			}
  			else if(readnum[o]*100/map2.size()<50){
  				readstate[o]=3;
  			}
  			else{
  				readstate[o]=4;
  			}  			
  			o++;
  		}
   		o=0;
  		while(o<k-1){
  	        int qpre=0;
  			int n2pre=0;
  			double qspre=0,qepre=0;
  	  		int name1pre=0;
  	  		int name2pre=0;
  	  		

  	  		String[] tmpnamepre=classname[o].split(":");
  	  		name1pre=Integer.parseInt(tmpnamepre[0]);
  	  		name2pre=Integer.parseInt(tmpnamepre[1]);
  	  		int[] c={name1pre,name2pre};
  	  		while(qpre<n1&&n2pre==0){	
  	  			if(name1pre>=zone1[qpre]-5&&name1pre<=zone2[qpre]+5){
  	  				n2pre=1;
  	  				qspre=pp[qpre]+((name1pre-zone1[qpre])*pl[qpre])/(zone2[qpre]-zone1[qpre]);
  	  			}
  	  			qpre++;
  	  		}
  	  		qpre=0;n2pre=0;
  	  		while(qpre<n1&&n2pre==0){
  	  			if(name2pre>=zone1[qpre]-5&&name2pre<=zone2[qpre]+5){
  	  				n2pre=1;
  	  				qepre=pp[qpre]+((name2pre-zone1[qpre])*pl[qpre])/(zone2[qpre]-zone1[qpre]);
  	  			}
  	  			qpre++;
  	  		}
	  			easygroup.add(c);
	  			readnum2.add(readnum[o]);
/////	  			System.out.println("splice:: "+c[0]+"   "+c[1]+" >>> "+readnum[o]);
  	  		if(readnum[o]>0){
  	  			int hy=0;
  	    		g.setColor(darkblack);
  	  			while(hy<=readstate[o]){
  	  			arc=new Arc2D.Double(qspre,(gao)+step+12-(5+hy*2+20*(qepre-qspre)/60)/2,qepre-qspre,5+hy*2+20*(qepre-qspre)/60,180,180,Arc2D.OPEN);
  	  			//l=new Line2D.Double(qspre-4,+(gao)+(qepre-qspre)/30+(step+12-(5+20*(qepre-qspre)/60)/2)+(5+20*(qepre-qspre)/60)/2,qspre,+(gao)+(qepre-qspre)/30+(step+12-(5+20*(qepre-qspre)/60)/2)+(5+20*(qepre-qspre)/60)/2);
  	  			//g.draw(l);
  	  			//l=new Line2D.Double(qepre,+(gao)+(qepre-qspre)/30+(step+12-(5+20*(qepre-qspre)/60)/2)+(5+20*(qepre-qspre)/60)/2,qepre+4,+(gao)+(qepre-qspre)/30+(step+12-(5+20*(qepre-qspre)/60)/2)+(5+20*(qepre-qspre)/60)/2);
  	  			//g.draw(l);
  	  			g.draw(arc);
  	  			maxarc=(int)Math.max(maxarc,5+hy*2+20*(qepre-qspre)/60);
  	  			hy++;
  	  			}
  	  			g.setColor(Color.black);
  	  			layout=new TextLayout(readnum[o]+" ",zt2,frc);
  	  			layout.draw(g,(float)((qepre+qspre)/2)-4,(float)(((gao)+step+12-(5+hy*2+20*(qepre-qspre)/60)/2)+5+hy*2+20*(qepre-qspre)/60+5));
  	  		//	System.out.println(classname[o]+" >>> "+readnum[o]);

  	  		}
  	  		o++;
  			}
 ///// 			System.out.println("Total bsj >>> "+map2.size());
	}
        float high=Math.max((maxarc+36)/3,50);

        
        //draw_splice
       	 
       	 
        //long reads
        
        
        if(print_ro==1&&ro_number!=0){
      	int b=0;
      	int	select_ro=8000/(ro_number);
      	
      	
      	while(readstartlong[b][0]!=0){
      		int ifstart=0;
      		//select:
      		if(print_less==0){
      			ifstart=1;
      		}
      		else{
      			if(r1.nextDouble()*1000<select_ro)   //??
      				ifstart=1;
      		}
      		if(ifstart==1){
          	double[] ls=new double[10];
          	double[] le=new double[10];
          	double[][] rs=new double[10][200];//line.frag
          	double[][] re=new double[10][200];//line.frag
      		int s=0;
      		int rl=0;
      		int tmpu[]=new int[10];
      		while(readstartlong[b][s]!=0){
            int q=0;
      		int n2=0;
      		int before;
      		if(s!=0)
      			before=readstartlong[b][s-1];
      		else{
      			before=0;
      			ls[rl]=999999999;
      			le[rl]=0;
      		}
    //  		System.out.println(before+" "+readend[0][b][s]+" "+rl+" ["+o+"]["+b+"]["+s+"]");
    //   	   System.out.println(before+" readstart["+o+"]["+b+"]["+s+"]="+readstart[o][b][s]+" "+rl);
      		if(before>=readstartlong[b][s]-5){
      			rl++;
      		    ls[rl]=999999999;
      		    le[rl]=0;
      		}
      		while(q<n1&&n2==0){
  //    			System.out.println("qq="+qq+" s="+s+" "+readstart[o][qq][b][s]+" "+zone1[q]+" "+readend[o][qq][b][s]+" "+zone2[q]);
      			if(readstartlong[b][s]>=zone1[q]&&readendlong[b][s]<=zone2[q]){
      				n2=1;
      	            rs[rl][tmpu[rl]]=pp[q]+((readstartlong[b][s]-zone1[q])*pl[q])/(zone2[q]-zone1[q]);
//      	            System.out.println("rs["+qq+"]["+s+"]="+rs[qq][s]);
      	            re[rl][tmpu[rl]]=pp[q]+((readendlong[b][s]-zone1[q])*pl[q])/(zone2[q]-zone1[q]);
//       	            System.out.println("re["+qq+"]["+s+"]="+re[qq][s]);
      	            ls[rl]=Math.min(ls[rl],rs[rl][tmpu[rl]]);
      	            le[rl]=Math.max(le[rl],re[rl][tmpu[rl]]);
      			}
      			q++;
      		}
      		tmpu[rl]++;
      		s++;
      		}
      	int rll=0;
      	while(rll<=rl){
      	//画图咯
      		float[] arr = {4.0f,2.0f}; 
      		stroke = new BasicStroke(1,
      	              BasicStroke.CAP_BUTT,
      	              BasicStroke.JOIN_BEVEL,
      	              1.0f,arr,0);
      	
          g.setStroke(stroke);
          g.setColor(grey);
          l=new Line2D.Double(ls[rll],(gao)+(step+8)+(1.5*high),le[rll],(gao)+(step+8)+(1.5*high));
          g.draw(l);
    	//	System.out.println(ls[rll]+" "+le[rll]+" "+rll);

      	 s=0;
      	while(s<tmpu[rll]){
      		stroke=new BasicStroke(3);
              g.setStroke(stroke);
      			g.setColor(Color.orange);
    //          System.out.println("readq["+o+"]["+qq+"]["+b+"]["+s+"]="+readq[o][qq][b][s]);
      		l=new Line2D.Double(rs[rll][s],(gao)+(step+8)+(1.5*high),re[rll][s],(gao)+(step+8)+(1.5*high));
      		g.draw(l);
      		if(s+1<tmpu[rll]){
          		stroke=new BasicStroke(1);
                g.setStroke(stroke);
        		g.setColor(Color.gray);
        		arc=new Arc2D.Double(re[rll][s],(gao)+(step+8)+(1.5*high)-8,rs[rll][s+1]-re[rll][s],16,0,180,Arc2D.OPEN);
        		g.draw(arc);
      		}
      		s++;
      	}
      	g.setColor(Color.black);
          Polygon po=new Polygon();
  		stroke=new BasicStroke(0);
          g.setStroke(stroke);
          double[] a1={zonestart-5,zonestart-10,zonestart-10};
          double[] b1={(gao)+(step+8)+(1.5*high),(gao)+(step+8)+(1.5*high)+2,(gao)+(step+8)+(1.5*high)-2};
          int vv=0;
          while(vv<a1.length){
          	po.addPoint((int)a1[vv],(int)b1[vv]);
          	vv++;
          }
          if(rll!=0){
          g.draw(po);
          g.fill(po);
          }
           po=new Polygon();
          double[] a2={zoneend+10,zoneend+5,zoneend+5};
          double[] b2={(gao)+(step+8)+(1.5*high),(gao)+(step+8)+(1.5*high)+3,(gao)+(step+8)+(1.5*high)-3};
           vv=0;
          while(vv<a1.length){
          	po.addPoint((int)a2[vv],(int)b2[vv]);
          	vv++;
          }
          if(rll!=rl){
          g.draw(po);
          g.fill(po);
          }
          high=high+2;
          rll++;
      	}
          high=high+3;
      		}
          b++;
      	}
    	  g.setColor(Color.gray);
    	  stroke=new BasicStroke(1);
    	  g.setStroke(stroke);
    	  l=new Line2D.Double(x-25,(gao)+(step+8)+(1.5*high)+1,zoneend+5,(gao)+(step+8)+(1.5*high)+1);
    	  g.draw(l);
      	high=high+5;
	}
      	
      	o=0;
      	int printtotal=0;
      	while(o<k){
      		printtotal=readnum[o]+printtotal;
      		o++;
      	}
      	o=0;
      	int b=0;
      	
        //short reads
      	if(print_bsj==1){
      	
      	if(read_pos.equals("n/a")!=true){
      		int color2=0;
      		int name1=0;
      		int name2=0;
      		while(o<k){
//        	System.out.println(classname[o]);
        	if(classname[o].equals("NULL")==true){
        		layout=new TextLayout("OTHERS",zt2,frc);
        		
        	}
        	else{
        	String[] tmpname=classname[o].split(":");
        	name1=Integer.parseInt(tmpname[0]);
        	name2=Integer.parseInt(tmpname[1]);
        	int q=0;
            int s=0;
            int e=0;
        	while(q<n1){
        		if(zone1[q]<=name1&&zone2[q]>=name1){
        			s=q+1;
        		}
        		if(zone1[q]<=name2&&zone2[q]>=name2){
        			e=q+1;
        		}
        		q++;
        	}
        	}

        	float top=high-1;
        	b=0;
        	double[][] rs=new double[2][200];
        	double[][] re=new double[2][200];
        	int printnum=0;
        	while(b<readnum[o]){
        		int ifstart=0;
        		if(print_less!=0){
        			if(r1.nextDouble()*1000<1000*print_less/printtotal){ //??
        				ifstart=1;
        			}
        			if(printnum>=readnum[o]*print_less/printtotal+1){
        				ifstart=0;
        			}
        			if(printnum==0&&b==readnum[o]-1){
        				ifstart=1;
        			}
        				
        		}
        		if(ifstart==1){
        		printnum++;
        		if(readstart[o][0][b][0]!=0||readstart[o][1][b][0]!=0){
            	double ls=999999999;
            	double le=0;

        		int qq=0;
        		int[] tmpu=new int[2];
        		while(qq<=1){
                int s=0;
                
        		while(readstart[o][qq][b][s]!=0){
                int q=0;
        		int n2=0;
   //     		System.out.println("readend["+o+"]["+qq+"]["+b+"]["+s+"]="+readend[o][qq][b][s]);
        		while(q<n1&&n2==0){
    //    			System.out.println("qq="+qq+" s="+s+" "+readstart[o][qq][b][s]+" "+zone1[q]+" "+readend[o][qq][b][s]+" "+zone2[q]);
        			if(readstart[o][qq][b][s]>=zone1[q]&&readend[o][qq][b][s]<=zone2[q]){
        				n2=1;
        	            rs[qq][s]=pp[q]+((readstart[o][qq][b][s]-zone1[q])*pl[q])/(zone2[q]-zone1[q]);
        //	            System.out.println("rs["+qq+"]["+s+"]="+rs[qq][s]);
        	            re[qq][s]=pp[q]+((readend[o][qq][b][s]-zone1[q])*pl[q])/(zone2[q]-zone1[q]);
        //	            System.out.println("re["+qq+"]["+s+"]="+re[qq][s]);
        	            ls=Math.min(ls,rs[qq][s]);
        	            le=Math.max(le,re[qq][s]);
        			}
        			q++;
        		}
        		s++;
        		}
        		tmpu[qq]=s;
        		qq++;
        		}
        	
        	//画图咯
        		float[] arr = {4.0f,2.0f}; 
        		stroke = new BasicStroke(1,
        	              BasicStroke.CAP_BUTT,
        	              BasicStroke.JOIN_BEVEL,
        	              1.0f,arr,0);
        	
            g.setStroke(stroke);
            g.setColor(grey);
            l=new Line2D.Double(ls,(gao)+(step+8)+(1.5*high),le,(gao)+(step+8)+(1.5*high));
          //  System.out.println(ls+" "+le);
            g.draw(l);
            qq=0;
            while(qq<=1){
        	int s=0;
        	while(s<tmpu[qq]){
        		stroke=new BasicStroke(1);
                g.setStroke(stroke);
                if(qq==0){
        		if(readq[o][qq][b][s]<15)
        			g.setColor(x1);
        		else if(readq[o][qq][b][s]<30)
        			g.setColor(x2);
        		else if(readq[o][qq][b][s]<45)
        			g.setColor(x3);
        		else 
        			g.setColor(x4);
                }
                else{
            		if(readq[o][qq][b][s]<15)
            			g.setColor(z1);
            		else if(readq[o][qq][b][s]<30)
            			g.setColor(z2);
            		else if(readq[o][qq][b][s]<45)
            			g.setColor(z3);
            		else 
            			g.setColor(z4);

                }
      //          System.out.println("readq["+o+"]["+qq+"]["+b+"]["+s+"]="+readq[o][qq][b][s]);
        		l=new Line2D.Double(rs[qq][s],(gao)+(step+8)+(1.5*high),re[qq][s],(gao)+(step+8)+(1.5*high));
        		g.draw(l);
        		s++;
        	}
        	qq++;
        	}
        	g.setColor(Color.black);
            Polygon po=new Polygon();
    		stroke=new BasicStroke(0);
            g.setStroke(stroke);
            double[] a1={zonestart-3,zonestart,zonestart};
            double[] b1={(gao)+(step+8)+(1.5*high),(gao)+(step+8)+(1.5*high)+1,(gao)+(step+8)+(1.5*high)-1};
            int vv=0;
            while(vv<a1.length){
            	po.addPoint((int)a1[vv],(int)b1[vv]);
            	vv++;
            }
            g.draw(po);
            g.fill(po);
             po=new Polygon();
            double[] a2={zoneend+3,zoneend,zoneend};
            double[] b2={(gao)+(step+8)+(1.5*high),(gao)+(step+8)+(1.5*high)+1,(gao)+(step+8)+(1.5*high)-1};
             vv=0;
            while(vv<a1.length){
            	po.addPoint((int)a2[vv],(int)b2[vv]);
            	vv++;
            }
            g.draw(po);
            g.fill(po);
            if(classname[o].equals("NULL")==false){
            stroke=new BasicStroke(1);
            g.setStroke(stroke);
            g.setColor(Color.black);
            int q=0;
    		int n2=0;
    		double qs=0,qe=0;
    		while(q<n1&&n2==0){
    			if(name1>=zone1[q]-5&&name1<=zone2[q]+5){
    				n2=1;
    	            qs=pp[q]+((name1-zone1[q])*pl[q])/(zone2[q]-zone1[q]);
    			}
    			q++;
    		}
    		q=0;n2=0;
    		while(q<n1&&n2==0){
    			if(name2>=zone1[q]-5&&name2<=zone2[q]+5){
    				n2=1;
    	            qe=pp[q]+((name2-zone1[q])*pl[q])/(zone2[q]-zone1[q]);
    			}
    			q++;
            }
    		g.setColor(Color.gray);
    		arc=new Arc2D.Double(qs,(gao)+(step+8)+(1.5*high)-9,qe-qs,18,180,180,Arc2D.OPEN);
    		g.draw(arc);
            }
        	}
            high++;
        		}
            b++;
        	
        	}
        	g.setColor(Color.black);
            stroke=new BasicStroke(1);
            g.setStroke(stroke);
            l=new Line2D.Double(len+x-50,(gao)+(step+8)+(1.5*top)-2,zoneend+5,(gao)+(step+8)+(1.5*top)-2);
            g.draw(l);
            l=new Line2D.Double(len+x-50,(gao)+(step+8)+(1.5*high)+2,zoneend+5,(gao)+(step+8)+(1.5*high)+2);
            g.draw(l);
            l=new Line2D.Double(zoneend+5,(gao)+(step+8)+(1.5*top)-2,zoneend+5,(gao)+(step+8)+(1.5*high)+2);
            g.draw(l);
            //l=new Line2D.Double(zoneend+5,(gao)+(step+8)+(1.5*mid),zoneend+15,(gao)+(step+8)+(1.5*mid));
            //g.draw(l);
            //layout.draw(g,(float)zoneend+20, (float) ((gao)+(step+8)+(1.5*mid-1)));
            if(color2==0){
            g.setColor(gray2);
            color2++;}
            else{
            	g.setColor(yellow2);
            	color2--;
            }
            rect1=new Rectangle2D.Double(x-30,(gao)+(step+8)+(1.5*top),zoneend+5-x+25,1.5*(high-top));
            g.draw(rect1);
            g.fill(rect1);
        	high=high+10;
        	o++;
        }
      	}
	}
      	//bottom2=(int)high;
        bottom=(float) (high*1.5+gao+step+8);
        o=0;
        //画外显子；
        stroke=new BasicStroke(1);
        g.setStroke(stroke);	
        	
        	 
        		int s=0;
        		while(s<m2){
                    g.setColor(Color.gray);
        			rect1=new Rectangle2D.Double(x+(((pre_pos[s]-start)*len)/(end-start)),(gao)-13,(pre_len[s]*len)/(end-start),8);
        			g.draw(rect1);
        			g.setColor(cgroup.get(s));
        			g.fill(rect1);
        			s++;
        	}
        		s=0;
        		while(s<m){
                    g.setColor(Color.gray);
        			rect1=new Rectangle2D.Double(x+(((ex_posall[s]-start)*len)/(end-start)),(gao)-4,(ex_lenall[s]*len)/(end-start),8);
        			g.draw(rect1);
        			g.setColor(red);
        			g.fill(rect1);
        			s++;
        	}
       

        		
        		
      //画coverage
        if(print_cov!=0){
        if(read_cov.equals("n/a")!=true){
           g.setColor(blue2);
           stroke=new BasicStroke(1);
           g.setStroke(stroke);	
           double gr=(double)(end-start)/len;
           int c_pos=0;
           double q1=0;
           int u=0;
           int maxh=50;
           int group=10;
           int max_1=(covh1/group)+1;
 //          System.out.println(end-start);
           if((end-start)>len){
           while(c_pos<=len){
        	    q1=0;
        	   while(u<(double)gr*(c_pos+1)&&u<(end-start)){
        		   q1=Math.max(q1,cov[u]);
        		   u++;   
        	   }
        	   
        	   q1=q1*(maxh/group)/max_1;
        	   l=new Line2D.Double(x+c_pos,gao-15,x+c_pos,gao-15-q1);
        	   g.draw(l);
        	   c_pos++;
           }
           }
           else{
               while(c_pos<=len){
            	   q1=0;
            	   u=(int)Math.floor(gr*c_pos);
            	   q1=cov[u]*(maxh/group)/max_1;
            	   l=new Line2D.Double(x+c_pos,gao-15,x+c_pos,gao-15-q1);
            	   g.draw(l);
            	   c_pos++;
           //	   System.out.println("else");
              }

           }
           g.setColor(Color.black);
          //最大coverage为几百
           l=new Line2D.Double(x-10,gao-15,x-10,gao-15-maxh);
           g.draw(l);
           layout=new TextLayout("0",zt2,frc);
           layout.draw(g, x-23, gao-15);
           layout=new TextLayout((int)(max_1*group)+" ",zt2,frc);
           layout.draw(g, x-23, gao-15-maxh);
           layout=new TextLayout((int)(max_1*group/2)+" ",zt2,frc);
           layout.draw(g, x-23, gao-15-maxh/2);
           

           g.setColor(grey);
           l=new Line2D.Double(x-10,gao-15,x+len,gao-15);
           g.draw(l);
           l=new Line2D.Double(x-10,gao-15-(maxh/4),x+len,gao-15-(maxh/4));
           g.draw(l);
           l=new Line2D.Double(x-10,gao-15-maxh/2,x+len,gao-15-maxh/2);
           g.draw(l);
           l=new Line2D.Double(x-10,gao-15-maxh*3/4,x+len,gao-15-maxh*3/4);
           g.draw(l);
           l=new Line2D.Double(x-10,gao-15-maxh,x+len,gao-15-maxh);
           g.draw(l);
        }
        }
           
        o=0;
      //画link
        
        stroke=new BasicStroke(1);
        g.setStroke(stroke);
     //   System.out.println("end");
        g.setColor(Color.BLACK);
        layout=new TextLayout(chr+":"+start,zt11,frc);
        layout.draw(g,x-40,(gao)+12);
        layout=new TextLayout(chr+":"+end,zt11,frc);
        layout.draw(g,x+len-40,(gao)+12);
        
        
        
        //draw circle:
        if(print_splice!=0&&cirexon.size()>0){

            //find_hard:
    		apart_exon cir=new apart_exon(cirexon);
    		List<int[]> cir1= new ArrayList<int[]>();
    		cir1=cir.get_apart();
    		List<double[]> exp_list=new ArrayList<double[]>();
        	        	
        	//find_hard_end, 
        	//find_soft_assemble
        	
    		
    		if(cir1.get(0)[0]!=start){ 
    			int[] tmpce={start,start+5};
    			cir1.add(0,tmpce);

    		}
    		if(cir1.get(cir1.size()-1)[1]!=end){
    			int[] tmpce={end-5,end};
    			cir1.add(tmpce);
    		}
    		double[][] mar=new double[cir1.size()][cir1.size()];  //n,nz
    		String[] ver = new String[cir1.size()];

  /////  		System.out.println("");
    		int n=0;
    		while(n<cir1.size()){
            	List<Integer> tmpc=new ArrayList<Integer>();
            	int go=0;
            	while(go<cir1.get(n)[1]-cir1.get(n)[0]){
            		int vi=go+cir1.get(n)[0]-start;
            		tmpc.add(cov_bsj[vi]);
            		go++;
            	}
            	
                Collections.sort(tmpc);
                int j1=Math.min((tmpc.size()+1)/4,tmpc.size()-1);
                int j2=Math.min((tmpc.size()+1)/2,tmpc.size()-1);
                int j3=Math.min((3*tmpc.size()+1)/4,tmpc.size()-1);
                
                double[] tmpe={(double)tmpc.get(j1),(double)tmpc.get(j2),(double)tmpc.get(j3)};
                exp_list.add(tmpe);


    /////		    System.out.println(cir1.get(n)[0]+":"+cir1.get(n)[1]+" cov >>> "+tmpc.get(j1)+" "+tmpc.get(j2)+" "+tmpc.get(j3));
    		    
    		    if(n>0&&giveniso==null){
    		    	int nz=0;
    		    	int ifexist=0;
    		    	while(nz<zone_num){
    		    		if(zone1[nz]>cir1.get(n-1)[1]&&zone1[nz]<cir1.get(n)[0]&&zone2[nz]>cir1.get(n-1)[1]&&zone2[nz]<cir1.get(n)[0]){
    		    			ifexist++;
    		    			break;
    		    		}
    		    		nz++;
    		    	}
    		    	if(ifexist==0){
    		    		nz=0;
    		    		int ifexist1=0;
    		    		int ifexist2=0;
        		    	int exz1=0;
        		    	int exz2=0;
        		    	int ng=0;
        		    	while(ng<zone_num){
        		    		if(cir1.get(n-1)[1]>=zone1[ng]&&cir1.get(n-1)[1]<=zone2[ng]){
        		    			exz1=ng;
        		    		}
        		    		if(cir1.get(n)[0]>=zone1[ng]&&cir1.get(n)[0]<=zone2[ng]){
        		    			exz2=ng;
        		    		}
        		    		ng++;
        		    	}

    		    		
    		    		while(nz<easygroup.size()){
    		    			if(easygroup.get(nz)[0]==cir1.get(n-1)[1]){
    		    				ifexist1=1;
    		    			}
    		    			if(easygroup.get(nz)[1]==cir1.get(n)[0]){
    		    				ifexist2=1;
    		    			}
    		    			nz++;
    		    		}
    		    		if((ifexist1==0||ifexist2==0)&&(exz1!=exz2)){
    		    			int[] tmpeasy={cir1.get(n-1)[1],cir1.get(n)[0]};
    		    			easygroup.add(tmpeasy);
    		    			readnum2.add(1);
    		    		}
    		    	}
    		    	
    		    }
    			ver[n]=n+""; 
    			n++;
    		}
    		n=0;
    		while(n<cir1.size()-1){
    			int nz=n+1;
    			while(nz<cir1.size()){
    				if(cir1.get(n)[1]==cir1.get(nz)[0]){
    					mar[n][nz]=1;
    				}
    				int nr=0;
    				while(nr<easygroup.size()){
    				//	System.out.println("easy "+easygroup.get(nr)[0]+"-"+easygroup.get(nr)[1]+" "+nr);
    					if(easygroup.get(nr)[0]==cir1.get(n)[1]&&easygroup.get(nr)[1]==cir1.get(nz)[0]&&readnum2.get(nr)>=1){
    						mar[n][nz]=1;
    					}
    					nr++;
    				}
    				nz++;
    			}
    			n++;
    		}
    		n=0;
    		mar[cir1.size()-1][0]=1;
    		
    		while(n<cir1.size()){
    			int nz=0;
    			while(nz<cir1.size()){
    /////				System.out.print((int)mar[n][nz]+",");
    				nz++;
    			}
  /////  			System.out.println("");
    			n++;
    		}
    		
            Graph3 graph = new Graph3(mar, ver);  
            List<List<String>> soft_result_pre=graph.startSearch();
            List<List<String>> soft_result=new ArrayList<List<String>>();

            
            //maxiso
            if(soft_result_pre.size()>maxiso){
            	List<Double> evalue=new ArrayList<Double>();
            	int iso=0;
            	while(iso<soft_result_pre.size()){
            		int frg=0;
            		double tmppoint=0;
            		while(frg<soft_result_pre.get(iso).size()){
            			if(!soft_result_pre.get(iso).get(frg).equals("break")){
            				int frg1=Integer.parseInt(soft_result_pre.get(iso).get(frg));
            				tmppoint=tmppoint+exp_list.get(frg1)[1];
            			}
            			frg++;
            		}
            		evalue.add(tmppoint/frg);
            		iso++;
            	}
            	int i=0;
            	while(i<evalue.size()-1){
            		int j=i+1;
            		while(j<evalue.size()){
            			if(evalue.get(j)>evalue.get(i)){
            				List<String> tmpl=soft_result_pre.get(i);
            				double tmpj=evalue.get(i);
            				evalue.set(i, evalue.get(j));
            				evalue.set(j, tmpj);
            				soft_result_pre.set(i, soft_result_pre.get(j));
            				soft_result_pre.set(j, tmpl);
            			}
            			j++;
            		}
            		i++;
            	}
            	
            	iso=0;
            	while(iso<maxiso){
            		soft_result.add(soft_result_pre.get(iso));
            		iso++;
            	}
            	
            }
            else{
            	int iso=0;
            	while(iso<soft_result_pre.size()){
            		soft_result.add(soft_result_pre.get(iso));
            		iso++;
            	}
            }
            
            
            // exp

            System.out.println(soft_result);
            
            List<List<int[]>> soft_fina=new ArrayList<List<int[]>>();
            List<List<int[]>> break_fina=new ArrayList<List<int[]>>();
            
            

            int ng=0;
            int ng3=-1;
            int ng4=-1;
            while(ng<soft_result.size()){
            	
            //	System.out.println(soft_result.get(ng).get(0)+" "+soft_result.get(ng).get(soft_result.get(ng).size()-1));
            	if(soft_result.get(ng).contains("break")){
                	//System.out.println("break");
                	int ng2=0;
                	while(ng2<soft_result.get(ng).size()){
                		if(!soft_result.get(ng).get(ng2).equals("break")){
                			int number=Integer.parseInt(soft_result.get(ng).get(ng2));
                			int[] tmps=cir1.get(number);
                			if(ng2==0){
                				List<int[]> softtmp1=new ArrayList<int[]>();
                				softtmp1.add(cir1.get(number));
                				break_fina.add(softtmp1);
                				ng3++;
                			}
                			else{
                				if(break_fina.get(ng3).get(break_fina.get(ng3).size()-1)[1]==tmps[0]){
                					int[] tmps2={break_fina.get(ng3).get(break_fina.get(ng3).size()-1)[0],tmps[1]};
                					break_fina.get(ng3).set(break_fina.get(ng3).size()-1, tmps2);
                				}
                				else{
                					break_fina.get(ng3).add(cir1.get(number));
                			}
                		}
                		}
                		else{
                			int[] blank={0,0};
        					break_fina.get(ng3).add(blank);

                		}
                		ng2++;
                	}
            	}
            	else if(soft_result.get(ng).get(0).equals("0")&&soft_result.get(ng).get(soft_result.get(ng).size()-1).equals(ver[ver.length-1])&&cir1.get(0)[0]==start&&cir1.get(cir1.size()-1)[1]==end){
            		//full
                	int ng2=0;
                //	System.out.println("soft");
                	while(ng2<soft_result.get(ng).size()){
                		int number=Integer.parseInt(soft_result.get(ng).get(ng2));
                		int[] tmps=cir1.get(number);
                		if(ng2==0){
                			List<int[]> softtmp1=new ArrayList<int[]>();
                			softtmp1.add(cir1.get(number));
                			soft_fina.add(softtmp1);
                			ng4++;
                		}
                		else{
                			if(soft_fina.get(ng4).get(soft_fina.get(ng4).size()-1)[1]==tmps[0]){
                				int[] tmps2={soft_fina.get(ng4).get(soft_fina.get(ng4).size()-1)[0],tmps[1]};
                				soft_fina.get(ng4).set(soft_fina.get(ng4).size()-1, tmps2);
                			}
                			else{
                				soft_fina.get(ng4).add(cir1.get(number));
                			}
                		}
                		ng2++;
            	}
            	}
            	ng++;
            }
            
            //zone if not in 
         	//int[] bsj={end,start};
         	// load exon
    //     	int a=0;
    //     	while(a<linkgroup.size()){
    //     		int[] firstexon={start,linkgroup.get(a).get(0)[0]};
    //     		int[] lastexon={linkgroup.get(a).get(linkgroup.get(a).size()-1)[1],end};
    //     		int bz=0;
     //    		while(bz<cirexon.size()){
   //      			if(Arrays.equals(cirexon.get(bz),firstexon))
   //      				linkgroup.get(a).add(0,bsj);
   //      			if(Arrays.equals(cirexon.get(bz),lastexon))
   //      				linkgroup.get(a).add(bsj);
   //      			bz++;
   //      		}
    //     		a++;
    //     	}
         	
         	// assmble link;
         	//from_start:
         	//test:
            
            int zz=0;
            int lengthmax=0;
            //merge soft and hard
            
            List<List<int[]>> all_result=new ArrayList<List<int[]>>();
            

       //     System.out.println("soft_result");
     //       zz=0;
      //      while(zz<soft_fina.size()){
      //      	int zz2=0;
      //      	int lengthtmp=0;
      //      	while(zz2<soft_fina.get(zz).size()){
      //      		System.out.print(soft_fina.get(zz).get(zz2)[0]+"-"+soft_fina.get(zz).get(zz2)[1]+",");
      //      		lengthtmp=lengthtmp+(soft_fina.get(zz).get(zz2)[1]-soft_fina.get(zz).get(zz2)[0]);
       //     		zz2++;
      //      	}
      //      	System.out.println("");
      //      	zz++;
      //      }
     //       System.out.println("break_result");
      //      zz=0;
      //      while(zz<break_fina.size()){
      //      	int zz2=0;
      //      	int lengthtmp=0;
      //      	int br=0;
       //     	while(zz2<break_fina.get(zz).size()){
       //     		System.out.print(break_fina.get(zz).get(zz2)[0]+"-"+break_fina.get(zz).get(zz2)[1]+",");
      //      		lengthtmp=lengthtmp+(break_fina.get(zz).get(zz2)[1]-break_fina.get(zz).get(zz2)[0]);
      //      		if((break_fina.get(zz).get(zz2)[1]-break_fina.get(zz).get(zz2)[0])==0)
      //      			br++;
     //       		zz2++;
     //       	}
     //       	System.out.println("");
    //        	zz++;
     //       }
            

            String ptline=tmp[0]+"	"+tmp[1]+"	"+tmp[2]+"	"+tmp[3]+"	"+map2.size()+"	";
	    	double[] lp=new double[1];
          
            	if(giveniso!=null&&soft_fina.size()==0){
            		String[] isogive=giveniso.split("##");
            		int ts = 0;
            		while(ts<isogive.length){
                        String[] fra=isogive[ts].split(",");
                        int ts2=0;
                        List<int[]> input=new ArrayList<int[]>();
                        
                        while(ts2<fra.length){
                        	String[] tmpw=fra[ts2].split("-");
                        	int[] pos={Integer.parseInt(tmpw[0]),Integer.parseInt(tmpw[1])};
                        	input.add(pos);
                        	ts2++;
                        }
                        all_result.add(input);
            			ts++;
            		}
            		
            	}
            	else{
                    while(zz<soft_fina.size()){
                    	all_result.add(soft_fina.get(zz));
                    	zz++;
                    }
                    zz=0;
                    if(giveniso==null){
                    	while(zz<break_fina.size()){
                    		all_result.add(break_fina.get(zz));
                    		zz++;
                    	}
                    }
            	}
            	lp=new double[all_result.size()];

            	if(all_result.size()>1){
            		
            		  if(library.size()>0){

            	Read_as_cov_for_use_muti expj=new Read_as_cov_for_use_muti(all_result,cir1,exp_list,map2.size(),library,readlength,readnum2,easygroup,ran_seed); //??
            	double[] p=expj.get_answer();
            		  

            	int i=0;
            	while(i<p.length-1){
            		int j=i+1;
            		while(j<p.length){
            			if(p[j]>p[i]){
            				List<int[]> tmpl=all_result.get(i);
            				double tmpj=p[i];
            				p[i]=p[j];
            				p[j]=tmpj;
            				all_result.set(i, all_result.get(j));
            				all_result.set(j, tmpl);
            			}
            			j++;
            		}
            		i++;
            	}
            	
            	i=0;
            	while(i<p.length){
            		if(p[i]<1)
            			break;
            		i++;
            	}
            	while(i<all_result.size()){
            		all_result.remove(i);
            	}
            		  

    	    	
    	    	i=0;
    	    	while(i<all_result.size()){
                	String pline=ptline;
    //	    		System.out.println("test "+ptmp.get(op));
    	    			List<int[]> tmpa = all_result.get(i);
    	    			String iso_cir="";
    	    			int op1=0;
    	    			int length=0;
    	    			String state="Full";
    	    			while(op1<tmpa.size()){
    	    				iso_cir=iso_cir+tmpa.get(op1)[0]+"-"+tmpa.get(op1)[1]+",";
    	    				length=length+tmpa.get(op1)[1]-tmpa.get(op1)[0]+1;
    	    				if(tmpa.get(op1)[1]==0)
    	    					state="Break";
    	    				op1++;
    	    			}
    	    			if(state.equals("Full"))
    	    				lengthmax=Math.max(lengthmax, length);
    	    			else
    	    				lengthmax=Math.max(lengthmax, length*8/7);
    	    			pline=pline+all_result.size()+"	"+(int)p[i]+"	"+length+"	"+state+"	"+strain+"	"+geneid+"	"+iso_cir;
    	    			
    	    			output.add(pline);
    	    		i++;
    	    	}
    	    	lp=p;
            		  }
            		  else{
              	    	int i=0;
            	    	while(i<all_result.size()){
                        	String pline=ptline;
            //	    		System.out.println("test "+ptmp.get(op));
            	    			List<int[]> tmpa = all_result.get(i);
            	    			String iso_cir="";
            	    			int op1=0;
            	    			int length=0;
            	    			String state="Full";
            	    			while(op1<tmpa.size()){
            	    				iso_cir=iso_cir+tmpa.get(op1)[0]+"-"+tmpa.get(op1)[1]+",";
            	    				length=length+tmpa.get(op1)[1]-tmpa.get(op1)[0]+1;
            	    				if(tmpa.get(op1)[1]==0)
            	    					state="Break";
            	    				op1++;
            	    			}
            	    			if(state.equals("Full"))
            	    				lengthmax=Math.max(lengthmax, length);
            	    			else
            	    				lengthmax=Math.max(lengthmax, length*8/7);
            	    			pline=pline+all_result.size()+"	na	"+length+"	"+state+"	"+strain+"	"+geneid+"	"+iso_cir;
            	    			
            	    			output.add(pline);
            	    		i++;
            		  }
            }
            	}
            	else if(all_result.size()==1){
            		int op1=0;
            		String pline=ptline;
            		List<int[]> tmpa=all_result.get(0);
            		String iso_cir="";
            		int length=0;
            		String state="Full";
            		
	    			while(op1<tmpa.size()){
	    				iso_cir=iso_cir+tmpa.get(op1)[0]+"-"+tmpa.get(op1)[1]+",";
	    				length=length+tmpa.get(op1)[1]-tmpa.get(op1)[0]+1;
	    				if(tmpa.get(op1)[1]==0)
	    					state="Break";
	    				op1++;
	    			}
	    			if(state.equals("Full"))
	    				lengthmax=Math.max(lengthmax, length);
	    			else
	    				lengthmax=Math.max(lengthmax, length*8/7);

	    			pline=pline+(1)+"	"+map2.size()+"	"+length+"	"+state+"	"+strain+"	"+geneid+"	"+iso_cir;
	    			output.add(pline);
	    			lp[0]=map2.size();
            	}
            //
            //draw circle/line
            //get max length
           
            high=bottom;
            high=high+10;
            int circle_d_max=70;
            zz=0;
            int x_start=x-20;
            while(zz<all_result.size()){
            	if(x_start+40>width){
                	high=high+circle_d_max+10;
                	x_start=x-20;
            	}
                double curl=0;
            	int zz1=0;
            	int br=0;
            	while(zz1<all_result.get(zz).size()){
            		int[] tmpi=all_result.get(zz).get(zz1);
            		int lengthz=tmpi[1]-tmpi[0]+1;
            		if(tmpi[0]!=0){
            			curl=curl+lengthz;
            		}
            		else{
            			br++;
            		}
            		zz1++;
            	}
            	curl=curl*8/(8-br);
            	zz1=0;
            	double co_start=90;
                double circle_d=circle_d_max*curl/lengthmax;
                
                double scircle_d=circle_d*3/4;
            	
           //     System.out.println(lengthmax+" "+circle_d_max+" "+circle_d+" "+scircle_d+" ");
            	while(zz1<all_result.get(zz).size()){
            		double cl=0;
        			Color cur=Color.black;
            		int[] tmpi=all_result.get(zz).get(zz1);
            		int lengthz=tmpi[1]-tmpi[0]+1;
          //  		System.out.println(tmpi[0]+"-"+tmpi[1]);
            		if(tmpi[0]!=0){
            			cl=360*(double)lengthz/(double)curl;
            			cur=Color.black;
            			o=0;
            			while(o<cirexon.size()){
            				if(cirexon.get(o)[0]==tmpi[0]&&cirexon.get(o)[1]==tmpi[1]){
            					cur=cgroup.get(o);
            					break;
            				}
            			o++;
            			}
                        stroke=new BasicStroke(1);
                        g.setStroke(stroke);
            	    }
            	    else{
            			cl=360/8;
            			cur=Color.white;
                		float[] arr = {4.0f,2.0f}; 
                		stroke = new BasicStroke(1,
              	              BasicStroke.CAP_BUTT,
              	              BasicStroke.JOIN_BEVEL,
              	              1.0f,arr,0);
                        g.setStroke(stroke);
            	    }
            		//downexon;
                    arc = new Arc2D.Double(x_start,high,circle_d,circle_d,co_start,cl,Arc2D.PIE);
                    g.setColor(Color.black);
                    g.draw(arc);
                    g.setColor(cur);
                    g.fill(arc);
                    arc = new Arc2D.Double(x_start+(circle_d-scircle_d)/2,high+(circle_d-scircle_d)/2,scircle_d,scircle_d,co_start,cl,Arc2D.PIE);

                    g.setColor(Color.black);
                    g.draw(arc);
                    co_start=co_start+cl;
            		zz1++;
            	}
                arc = new Arc2D.Double(x_start+(circle_d-scircle_d)/2,high+(circle_d-scircle_d)/2,scircle_d,scircle_d,0,360,Arc2D.PIE);
                g.setColor(Color.white);
                g.fill(arc);
                g.setColor(Color.black);
                if(br!=0){
                	layout=new TextLayout("> "+(int)(curl*7/8)+" nt",zt11,frc);
                }
                else{
                	layout=new TextLayout((int)curl+" nt",zt11,frc);
                }
            	Rectangle2D rect2 = layout.getBounds();
            	double h1=rect2.getHeight();
            	double w1=rect2.getWidth();
                layout.draw(g,(float)(x_start+circle_d/2-w1/2),(float)(high+circle_d/2+h1/2));
                String tmpe=null;
                if(lp[zz]>0){
                	 tmpe=(int)lp[zz]+"";
                }
                else{
                	 tmpe="na";
                }
                layout=new TextLayout(tmpe,zt11,frc);
                layout.draw(g,(float)(x_start),(float)(high));
                
            	zz++;
            	x_start=x_start+10+(int)circle_d;
            }
            high=high+circle_d_max+20;
            bottom=high;

        }
        System.out.println("");
        //length;
        return g;

}
	public int gethigh(){
		int a=(int)(bottom)+10;
		System.out.println("high=" + a);
		return a;
	}
	public int getwidth(){
		int b=(int)(width+100);
		return b;
	}
	public List<String> get_info(){
		return output;
	}


}
