import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Read_as_cov_for_use_muti {
	public List<Integer> library;
	public List<List<int[]>> iso;
	public List<int[]> apart_exon;
	public List<double[]> exp_real;
	public int total_exp;
	public int readlength;
	public List<Integer> readnum;
	public List<int[]> easygroup;
	public int ran_seed;
 	
	public Read_as_cov_for_use_muti(List<List<int[]>> iso1 , List<int[]> apart_exon1, List<double[]> exp_real1,int total_exp1, List<Integer> library1,int readlength1, List<Integer> readnum1, List<int[]> easygroup1, int ran_seed1){
		this.iso=iso1;
		this.apart_exon=apart_exon1;
		this.exp_real=exp_real1;
		this.total_exp=total_exp1;
		this.library=library1;
		this.readlength=readlength1;
		this.readnum=readnum1;
		this.easygroup=easygroup1;
		this.ran_seed=ran_seed1; //??
	}
	
	public double[] get_answer() throws IOException{
		double[] answer_min = new double[iso.size()];
		double[] fina_answer=new double[iso.size()];

		List<double[]> exp_result=new ArrayList<double[]>();
		List<double[]> exp_result_low=new ArrayList<double[]>();
		List<double[]> exp_result_high=new ArrayList<double[]>();
		List<double[]> exp_point_result=new ArrayList<double[]>();
		
		Random r1=new Random(ran_seed*total_exp); //??
		
		int o=0;
		Map<String,Integer> easylink=new HashMap<String,Integer>();
		while(o<easygroup.size()){
			String tmp=easygroup.get(o)[0]+":"+easygroup.get(o)[1];
			if(o<readnum.size()){
				easylink.put(tmp, readnum.get(o));
			}
			else{
				easylink.put(tmp, 0);
			}
		//	System.out.println("check1: "+tmp+" "+easylink.get(tmp));

			o++;
		}

		int a=0;
		while(a<iso.size()){
			Read_length_cov2 test1=new Read_length_cov2(library,readlength,iso.get(a),apart_exon, ran_seed);
			List<double[]> p = test1.getp();
			exp_result.add(p.get(1));
			exp_result_low.add(p.get(0));
			exp_result_high.add(p.get(2));
			exp_point_result.add(p.get(3));
			a++;
		}
		int exonc=0;
		//get answer min;
		a=0;
		o=0;
		double totalmin=0;

		while(a<iso.size()){
			int tn=0;
			List<String> unique=new ArrayList<String>();
			while(tn+1<iso.get(a).size()){
				unique.add(iso.get(a).get(tn)[1]+":"+iso.get(a).get(tn+1)[0]);
				tn++;
			}
			tn=0;
			//remove
			while(tn<iso.size()){
				if(tn!=a){
					int tn1=0;
					while(tn1+1<iso.get(tn).size()){
						String tmp=iso.get(tn).get(tn1)[1]+":"+iso.get(tn).get(tn1+1)[0];
						if(unique.contains(tmp)){
							unique.remove(tmp);
						}
						tn1++;
					}
				}
				tn++;
			}
			tn=0;
			double test_result=0;
		//	System.out.println(unique.size());
			while(tn<unique.size()){
				int z=0;
				while(z+1<iso.get(a).size()){
					String tmp = iso.get(a).get(z)[1]+":"+iso.get(a).get(z+1)[0];
				//	System.out.println(tmp);
					if(unique.contains(tmp)&&easylink.containsKey(tmp)){
						int min_s=easylink.get(tmp);
						test_result=Math.max(test_result,(min_s*100/exp_point_result.get(a)[z]));
			//			System.out.println("check:  "+a+" "+z+" "+min_s+" "+exp_point_result.get(a)[z]+" "+test_result);
					}
					z++;
				}
				tn++;
			}
			if(unique.size()!=0){
				answer_min[a]=test_result; 
			}
			else{
				answer_min[a]=0;
			}
			totalmin=totalmin+answer_min[a];
	//		System.out.println("min_exp "+(int)answer_min[a]+" for unique_splice: "+unique);
			a++;
		}
		
		a=0;
		if(totalmin>total_exp){
		while(a<answer_min.length){
			answer_min[a]=answer_min[a]*total_exp/totalmin;
		//	System.out.println("min_exp "+answer_min[a]+" "+a);
			a++;
			}
		}
		
		List<List<Integer>> uplist=new ArrayList<List<Integer>>();
		

		while(exonc<apart_exon.size()){
			double p_mid=exp_real.get(exonc)[1]*100/total_exp;
		//	System.out.println(p_mid);
			int isonum=0;
			double[] max={0,0};
			double[] min={100,0};
			List<Integer> upclass=new ArrayList<Integer>();
			while(isonum<exp_result.size()){
				if(max[0]<=exp_result.get(isonum)[exonc]){
					max[0]=exp_result.get(isonum)[exonc];
					max[1]=isonum;
				}
				if(min[0]>=exp_result.get(isonum)[exonc]){
					min[0]=exp_result.get(isonum)[exonc];
					min[1]=isonum;
				}

				if(exp_result.get(isonum)[exonc]>p_mid){
					upclass.add(isonum);
				}
				isonum++;
			}
			if(upclass.size()==0){
				upclass.add((int)max[1]);
			}
			if(upclass.size()==exp_result.size()){
				upclass.remove((int)min[1]);
			}
			uplist.add(upclass);
			exonc++;
		}
		int q=exp_result.size();
		int r=0;
		int round=0;
		int round_max=5+Math.max(iso.size()*2,20);
		double muti_low=100000000;
		while(round<round_max){//round_max){///1
			

		double[] tmp=new double[exp_result.size()];
		double[] answer = new double[iso.size()];
		double[] ran=new double[iso.size()];
		double rantotal=0;
		
		r=0;
		while(r<iso.size()){
			ran[r]=r1.nextDouble()*100; //??
			rantotal=rantotal+ran[r];
			r++;
		}
		
//		System.out.print("before : ");
		if(round<answer.length){
			r=0;
			while(r<answer.length){
				if(r==round)
					answer[r] = total_exp;
				else
					answer[r]=0;
					tmp[r] = answer[r];
					r++;
			}
		}
		else{
			r=0;
			
			while(r<answer.length){
				answer[r]=total_exp*ran[r]/rantotal;
			//System.out.print(answer[r]+",");
				tmp[r]=answer[r];
				r++;
			}
		}
		
		double lowest=100000000;
		int ifgood=0;
		int k=0;
		while(k<3000&&ifgood<30){
			Iterator<Map.Entry<String, Integer>> limsp = easylink.entrySet().iterator();
			exonc=0;
			double controlpoint=0;
			while(exonc<apart_exon.size()){
				int isonum=0;
				double div=0;
				double div_low=0;
				double div_high=0;
				
				while(isonum<exp_result.size()){

					double tmppoint=exp_result.get(isonum)[exonc]*answer[isonum]/100;
					double tmppoint_low=exp_result_low.get(isonum)[exonc]*answer[isonum]/100;
					double tmppoint_high=exp_result_high.get(isonum)[exonc]*answer[isonum]/100;
					div=div+tmppoint;
					div_low=div_low+tmppoint_low;
					div_high=div_high+tmppoint_high;
					isonum++;
				}
		//		System.out.print((int)div+"|");
				double tmppoint=0;//Math.abs(div-exp_real.get(exonc)[1])*Math.abs(div_low-exp_real.get(exonc)[0])*Math.abs(div_high-exp_real.get(exonc)[2])*0.00001;
				
				if(div>=exp_real.get(exonc)[1]){
				//	tmppoint=(div-exp_real.get(exonc)[1]+10*(Math.max(0, div-exp_real.get(exonc)[2])))+Math.abs(div_high-exp_real.get(exonc)[2])+Math.abs(div_low-exp_real.get(exonc)[0]);
					tmppoint=div-exp_real.get(exonc)[1]+(3+Math.log((div+1)/(exp_real.get(exonc)[2]+1)))*(Math.max(0, div-exp_real.get(exonc)[2]))+Math.abs(div_high-exp_real.get(exonc)[2])+Math.abs(div_low-exp_real.get(exonc)[0]);
				}
				else{
				//	tmppoint=(exp_real.get(exonc)[1]-div+10*(Math.max(0,exp_real.get(exonc)[0]-div)))+Math.abs(div_high-exp_real.get(exonc)[2])+Math.abs(div_low-exp_real.get(exonc)[0]);
					tmppoint=exp_real.get(exonc)[1]-div+(3+Math.log((exp_real.get(exonc)[0]+1)/(div+1)))*(Math.max(0,exp_real.get(exonc)[0]-div))+Math.abs(div_high-exp_real.get(exonc)[2])+Math.abs(div_low-exp_real.get(exonc)[0]);

				}
			//	Math.abs(div-exp_real.get(exonc)[1])*(tmpx1.get(exonc)[1]-tmpx1.get(exonc)[0])/exp_real.get(exonc)[1];
		//		System.out.print((int)tmppoint+"*"+(int)div_low+"|"+(int)div+"|"+(int)div_high+" , ");
		//		System.out.println("tmp "+tmppoint+" "+controlpoint);
					controlpoint=controlpoint+tmppoint;
				exonc++;
			}
			
			Map<String,Double> test_sp=new HashMap<String,Double>();
			int isonum1=0;
			while(isonum1<iso.size()){
				int z=0;
				while(z<iso.get(isonum1).size()-1){
					String tmpsp = iso.get(isonum1).get(z)[1]+":"+iso.get(isonum1).get(z+1)[0];
			//		System.out.println(isonum1+" "+tmpsp);
					if(test_sp.containsKey(tmpsp)){
						double fsj=test_sp.get(tmpsp)+answer[isonum1]*exp_point_result.get(isonum1)[z]/100;
						test_sp.put(tmpsp, fsj);
					}
					else{
						double fsj=answer[isonum1]*exp_point_result.get(isonum1)[z]/100;
						test_sp.put(tmpsp, fsj);
					}
					z++;
				}
				isonum1++;
			}
			
		//	System.out.print(" ## ");
			
			Iterator<Map.Entry<String, Double>> entries = test_sp.entrySet().iterator();
			
			while (entries.hasNext()) {  
			    Map.Entry<String, Double> entry = entries.next();  
			    String key=entry.getKey();
			    double value=entry.getValue();
			    if(easylink.containsKey(key)){
			    	int lim=easylink.get(key);
			    	double divs=Math.max(0, lim-value);
		//	    	System.out.print(divs+",");
			    	divs=divs*50;
			    	controlpoint=controlpoint+divs;
			//	    System.out.println(key+" "+value+"|"+lim);
			    }
			}  
//					System.out.println("");
	//				System.out.println(controlpoint+" "+lowest);
	//				int l=0;
	//				while(l<answer.length){
	//					System.out.print((int)answer[l]+"|");
	//					l++;
	//				}
	//				System.out.print("  ");
	//				l=0;
	//				while(l<tmp.length){
	//					System.out.print((int)tmp[l]+"|");
	//					l++;
	//				}
	//				System.out.println("");
	//		System.out.println(" $$ "+controlpoint+", ………  ");
			r=0;
			while(r<answer.length){
	//			System.out.print((int)answer[r]+",");
				r++;
			}
			

	//		System.out.println();

			if(Math.min(controlpoint, lowest)==controlpoint){
				lowest=controlpoint;
				ifgood=0;
				int y=0;
				while(y<answer.length){
					tmp[y]=answer[y];
					y++;
				}
			}
			else{
		//		System.out.println("good "+ifgood);
				int y=0;
				while(y<tmp.length){
					answer[y]=tmp[y];
					y++;
				}
				ifgood++;
			}
			
			
			exonc=0;
			while(exonc<apart_exon.size()){
				int isonum=0;
				double div=0;
				while(isonum<exp_result.size()){
					double tmppoint=exp_result.get(isonum)[exonc]*answer[isonum]/100;
		//			System.out.println("test "+tmppoint+" "+exp_result.get(isonum)[exonc]+" "+answer[isonum]+" "+isonum+" "+exonc);
					div=div+tmppoint;
					isonum++;
				}
		//		System.out.print("for exon : "+exonc);
				if(div>exp_real.get(exonc)[2]){
		//			System.out.println("  too high");
					double inout=0;
		//			System.out.println("chooseb "+tmpbig);
					double fold=total_exp*(1+2*r1.nextDouble())/(15+ifgood); //??

					int down=q-uplist.get(exonc).size();
					int m=0;
					while(m<uplist.get(exonc).size()){
						double tj=Math.max(answer[uplist.get(exonc).get(m)]-fold, answer_min[uplist.get(exonc).get(m)]);
						inout=inout+(answer[uplist.get(exonc).get(m)]-tj);
						answer[uplist.get(exonc).get(m)]=tj;
			//			System.out.println("after take "+tj+" "+isonum+" "+exonc+" "+m+" "+fold+" "+big);
						m++;
					}
					double give=inout/down;
			//		System.out.print(" "+inout+" "+ give+" ");

					m=0;
					while(m<q){
						if(!uplist.get(exonc).contains(m)){
							answer[m]=answer[m]+give;
				//			System.out.println("after give "+answer[m]+" "+isonum+" "+exonc+" "+m+" "+fold+" "+big);
						}
						m++;
					}
				}
				else if(div<exp_real.get(exonc)[0]){
					double inout=0;
					int m=0;
					while(m<q){
						if(!uplist.get(exonc).contains(m)){
							double fold=total_exp*(1+2*r1.nextDouble())/(15+ifgood); //??
							double tj=Math.max(answer[m]-fold, answer_min[m]);
							inout=inout+(answer[m]-tj);
							answer[m]=tj;
				//			System.out.println("after take "+tj+" "+isonum+" "+exonc+" "+m+" "+fold+" "+big);
						}
						m++;
					}
					double give=inout/uplist.get(exonc).size();
    	     	//	System.out.print(" "+inout+" "+ give+" ");

					m=0;
					while(m<uplist.get(exonc).size()){
						answer[uplist.get(exonc).get(m)]=answer[uplist.get(exonc).get(m)]+give;
				//		System.out.println("after give "+answer[uplist.get(exonc).get(m)]+" "+isonum+" "+exonc+" "+m+" "+fold+" "+big);
						m++;
					}
				}
		//		System.out.println("");
		//		System.out.print("tmpresult  ");
		//		int l=0;
		//		while(l<answer.length){
		//			System.out.print((int)answer[l]+"|");
		//			l++;
		//		}
			
				exonc++;
			}
	//		System.out.println("");

			while (limsp.hasNext()) {  
			    Map.Entry<String, Integer> limsp_1 = limsp.next();  
				int isonum=0;
				double current_sp=0;
				String key=limsp_1.getKey();
				int value=limsp_1.getValue();
				List<Integer> have=new ArrayList<Integer>();
				while(isonum<iso.size()){
					int z=0;
					while(z<iso.get(isonum).size()-1){
						String tmpsp = iso.get(isonum).get(z)[1]+":"+iso.get(isonum).get(z+1)[0];
						if(tmpsp.equals(key)){
							current_sp=current_sp+answer[isonum]*exp_point_result.get(isonum)[z]/100;
							have.add(isonum);
							break;
						}
						z++;
					}
					isonum++;
				}
				
				if(current_sp<=value&&have.size()>0){ //splice must ++;
				//	System.out.println("warning: "+key+" "+value+"	"+current_sp+" "+have.size()+" "+k);
					int m=0;
					double inout=0;
					while(m<q){
						if(!have.contains(m)){
							double fold=total_exp*(1+r1.nextDouble())/(10); //??
							double tj=Math.max(answer[m]-fold, answer_min[m]);
							inout=inout+(answer[m]-tj);
						//	System.out.println("after take "+answer[m]+" -> "+tj+" "+m+" "+fold);

							answer[m]=tj;
						}
						m++;
					}
					double give=inout/have.size();
    	     	//	System.out.print(" "+inout+" "+ give+" ");
					m=0;
					while(m<have.size()){
				//		System.out.println("after give "+answer[have.get(m)]+" -> "+(answer[have.get(m)]+give)+" "+m+" "+fold);
						answer[have.get(m)]=answer[have.get(m)]+give;
						m++;
					}
				}
			}
			
			k++;
		}
	//	System.out.println("   "+lowest+"	"+k);
		
		
//		exonc=0;
//		while(exonc<apart_exon.size()){
//			double div=0;
//			int isonum=0;
//			while(isonum<exp_result.size()){
//				double tmppoint=exp_result.get(isonum)[exonc]*tmp[isonum]/100;
//				div=div+tmppoint;
//				isonum++;
//			}
//				System.out.print((int)div+"|");
//			exonc++;
//		}
//		System.out.println("");
		if(lowest<muti_low){
			int y=0;
			while(y<tmp.length){
				fina_answer[y]=tmp[y];
				y++;
			}
			muti_low = lowest;
		}
		 round++;
		//	System.out.println("");
		}
		//System.out.println(muti_low);
		int n=0;
		while(n<fina_answer.length){
			//System.out.print((int)fina_answer[n]+",");
			n++;
			}
		
		return fina_answer;

	}

}
