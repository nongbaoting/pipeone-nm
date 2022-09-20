import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Read_length_cov2 {
	// one point 
	// to get p;
	
	public List<Integer> insert = new ArrayList<Integer>();
	public int read_length;
	public List<int[]> iso = new ArrayList<int[]>();
	public List<double[]> p = new ArrayList<double[]>();
	public List<int[]> exon_apart = new ArrayList<int[]>();
	public int ran_seed;

	public Read_length_cov2(List insert1,int readl,List iso1, List exon_apart1, int ran_seed1){ //??
		this.insert=insert1;
		this.read_length=readl;
		this.iso=iso1;
		this.exon_apart=exon_apart1;
		this.ran_seed=ran_seed1; //??
	}
	public List<double[]> getp(){
		int breakzone=200;
		int total_length=0;
		int total_length2=0;
		Random r1=new Random(ran_seed); //??
		int i=0;
		List<Integer> point = new ArrayList<Integer>(); //
		List<Integer> point_s = new ArrayList<Integer>(); //

		double[] p_zone1=new double[exon_apart.size()];
		double[] p_zone2=new double[exon_apart.size()];
		double[] p_zone3=new double[exon_apart.size()];

		List<int[]> exist_exon=new ArrayList<int[]>();
		List<Integer> start_exon_p=new ArrayList<Integer>();

		while(i<iso.size()){
		//	System.out.println(iso.get(i)[0]+" "+ iso.get(i)[1]);
			if(iso.get(i)[0]==0&&iso.get(i)[1]==0){
				total_length=total_length+breakzone;
//				System.out.println("cov2_print_lenght: "+total_length+" break ");
				point.add(total_length);
				point_s.add(total_length2);
			}
			else{
				int k=0;
				while(k<exon_apart.size()){
					if(exon_apart.get(k)[0]>=iso.get(i)[0]&&exon_apart.get(k)[1]<=iso.get(i)[1]){
			//			System.out.println(exon_apart.get(k)[0]+" "+exon_apart.get(k)[1]+" "+iso.get(i)[0]+" "+iso.get(i)[1]);
						total_length=total_length+exon_apart.get(k)[1]-exon_apart.get(k)[0];
						exist_exon.add(exon_apart.get(k));
//						System.out.println("cov2_print_lenght: "+total_length);
						point.add(total_length);
						start_exon_p.add(total_length);
					}
					k++;
				}
				total_length2=total_length2+iso.get(i)[1]-iso.get(i)[0];
				point_s.add(total_length2);
			}
			i++;
		}
		double[] p_point=new double[point.size()-1];
		int testtime=10000;
		int[] expg = new int[total_length];
	    i=0;
	    while(i<testtime){
	    	List<int[]> tmpl=new ArrayList<int[]>();
	    	int rltest=insert.get((int)(r1.nextDouble()*insert.size())); //??
	    	int mid=Math.max(0, rltest-2*read_length);
	    	int start_site=(int)(r1.nextDouble()*total_length)+1;//??
	    	if(mid==0){
	    		int[] pos={start_site,start_site+rltest};
	    		tmpl.add(pos);
	    	}
	    	else{
	    		int[] pos1={start_site,start_site+read_length};
	    		int[] pos2={start_site+read_length+mid,start_site+read_length*2+mid};
	    		tmpl.add(pos1);
	    		tmpl.add(pos2);
	    	}
	    	int z=0;
	    	while(z<tmpl.size()){
	    		if(tmpl.get(z)[0]<=total_length&&tmpl.get(z)[1]>total_length){
	    			int start1=tmpl.get(z)[0];
	    			int end1=tmpl.get(z)[1];
	    			int[] tmp1={start1,total_length};
	    			int[] tmp2={1,end1-total_length};
	    			tmpl.add(z,tmp1);
	    			tmpl.set(z+1, tmp2);
	    		}
	    		else if(tmpl.get(z)[0]>total_length){
	    			int start1=tmpl.get(z)[0];
	    			int end1=tmpl.get(z)[1];
	    			int[] tmp1={start1-total_length,end1-total_length};
	    			tmpl.set(z,tmp1);
	    			z--;
	    		}
		    	z++;
	    	}
	    	//report
	    //	int j=0;
	   // 	while(j<tmpl.size()){
	   // 		System.out.print(tmpl.get(j)[0]+"-"+tmpl.get(j)[1]+",");
	   // 		j++;
	   // 	}
	  //  	System.out.println("");
	    	//report
	    	z=0;
	    	int ifbsj=0;
	    	while(z<tmpl.size()-1){
	    		if(tmpl.get(z)[0]<total_length-20&&tmpl.get(z+1)[1]>=20&&tmpl.get(z)[1]==total_length&&tmpl.get(z+1)[0]==1){
	    			ifbsj=1;
	    			break;
	    		}
	    		z++;
	    	}
	 //   	System.out.println("start "+start_site+" & length "+rltest+" | "+ifbsj);
	    	
	    	if(ifbsj==1){
	    		Exon_length test=new Exon_length(tmpl,1,total_length);
	    		test.takemerge();
	    		int a1=0;
	    		while(a1<tmpl.size()){
	    			int a2=tmpl.get(a1)[0];
	    			while(a2<tmpl.get(a1)[1]){
	    				expg[a2-1]++;
	    				a2++;
	    			}
	    			a1++;
	    		}
		    	//report
		  //  	j=0;
		  //  	while(j<tmpl.size()){
		  //  		System.out.print(tmpl.get(j)[0]+"-"+tmpl.get(j)[1]+",");
		  //  		j++;
		  //  	}
		  //  	System.out.println("");
		    	//report
	    	}
	    	else{
	    		i--;
	    	}
	    	i++;
	    }
	    
	    i=0;
	    int j=0;
	    while(i<exon_apart.size()){
	    	if(exist_exon.contains(exon_apart.get(i))){
	    		int start=0;
	    		int end=0;
	    		end=start_exon_p.get(j);
	    		start=start_exon_p.get(j)-(exon_apart.get(i)[1]-exon_apart.get(i)[0]);
	    		int z=start;
	    		List<Integer> tmpcov=new ArrayList<Integer>();
	    		int sum=0;
	    		while(z<end){
	    			tmpcov.add(expg[z]);
	    			sum=sum+expg[z];
	    			z++;
	    		}
	    		if(end-start<5){
	    			double tcov=sum/(end-start);
	    			p_zone1[i]=tcov;
	    			p_zone2[i]=tcov;
	    			p_zone3[i]=tcov;
	    			
	    		}
	    		else{
	    		Collections.sort(tmpcov);
	    		int n1=(tmpcov.size()+1)/4;
	    		int n2=(tmpcov.size()+1)/2;	
	    		int n3=3*(tmpcov.size()+1)/4;
	    	//	System.out.println("cov2_coverage|"+start+"-"+end+" : "+tmpcov.get(n1)+" "+tmpcov.get(n2)+" "+tmpcov.get(n3));
	    		p_zone1[i]=(double)tmpcov.get(n1)*100/(double)testtime;
	    		p_zone2[i]=(double)tmpcov.get(n2)*100/(double)testtime;
	    		p_zone3[i]=(double)tmpcov.get(Math.min(n3,tmpcov.size()-1))*100/(double)testtime;
	    		}
	    		j++;
	    		
	    		}
	    	else{
	    		p_zone1[i]=0;
	    		p_zone2[i]=0;
	    		p_zone3[i]=0;

	    	}
	    //	System.out.print((int)p_zone1[i]+"|"+(int)p_zone2[i]+"|"+(int)p_zone3[i]+" , ");
	    	i++;
	    }
	 //   System.out.println();
	    i=0;
	    while(i<point_s.size()-1){
	    	p_point[i]=(double)expg[point_s.get(i)]*100/(double)testtime;
	//    	System.out.println(p_point[i]);
	    	i++;
	    }
//   	System.out.println();
//	    i=0;
//	    System.out.print("p: ");
//	    while(i<p.length){
//	    	System.out.print(p[i]+",");
//	    	i++;
//	    }
//	    System.out.println("");
	    p.add(p_zone1);
	    p.add(p_zone2);
	    p.add(p_zone3);

	    p.add(p_point);
		return p;
	}
	

}
