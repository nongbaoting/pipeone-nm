import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.batik.apps.rasterizer.SVGConverterException;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class CIRI_vis_test {

	public static void main(String[] args) throws IOException, SVGConverterException {
		// TODO Auto-generated method stub
		// -h help
		
		// -i input **
		// -l library length **
		// -r ref 
		
		// -d dir
		// -o prefix
		
		// -list (output list)
		
		// -min  (min expression)
		// -rank (top expression %)
		// -exp (top total expression %)
		// -max (max expression ) 
		// -iso (max isoform )

		// -vis (display read number)
		// -width (display width)
		// -ran (random seed) 
		
		long tstart = System.currentTimeMillis();
		String v="Version: CIRI-full 2.0, CIRI-vis 1.2.2";
		System.out.println(v);
		int cmd=args.length;
		List<String> z=new ArrayList<String>();
		String dir=System.getProperty("user.dir");
		int n=0;
		int breakflag=0; //show help doc. 1= show help. 2= input not enough 3= file not found. 4= output set wrong.
		int ran_seed = 0;
		
        String listinput=null;
        String input=null;
        String libray_file=null;
        String ref=null;
        int iflist=0;
        int out_circle=1; //not control for now 
        int out_cov=1;//not control for now 
        int out_bsj=1;//not control for now 
        int out_ro=1;//not control for now 

        int target_min=5;
        int target_max=999999999;
        int out_less=100;
        int length=200;
        int F_exp=0;    //0-100 %
        int F_rank=0;   //0-100 %
		int readl = 0;
		int iso=10;

        String prefix="stout";
        String odir="stdir";

		while(n<cmd){
			z.add(args[n]);
			n++;
		}
		if(z.size()==0){
			System.out.println("no parameter found");
			breakflag=1;
		}
		else if(z.contains("-h")){
			System.out.println("turn to help");
			breakflag=1;
		}
		else{
			if(!z.contains("-i")){
				System.out.println("	-i not set");
				breakflag=2;
			}
			else{ //test
				if(z.indexOf("-list")!=-1){//inputing read1
					
					listinput=z.get(z.indexOf("-list")+1);
					if(!listinput.substring(0,1).equals("/")){
						listinput=dir+"/"+listinput;
					}
					File test2=new File(listinput);
					if(!test2.exists()){
						System.out.println("	list file not found. please check");
						breakflag=3;
					}
					else{
				        iflist=1;
				        System.out.println("	list is set, ignore other filter parameters(-min, -max, -rank, -exp were disabled).");
					}
				}
				if(z.indexOf("-i")!=-1){//inputing read1
					input=z.get(z.indexOf("-i")+1);
					if(!input.substring(0,1).equals("/")){
						input=dir+"/"+input;
					}
					File test1=new File(input);
					if(!test1.exists()){
						System.out.println("	input file not found. please check");
						breakflag=3;
					}
				}
				if(z.indexOf("-l")!=-1){//
					libray_file=z.get(z.indexOf("-l")+1);
					if(!libray_file.substring(0,1).equals("/")){
						libray_file=dir+"/"+libray_file;
					}
					File test1=new File(libray_file);
					if(!test1.exists()){
						System.out.println("	libray length file not found. please check");
						breakflag=3;
					}
				}
				else{
					System.out.println("	libray lenght file not found not set, quantification step skip.");
				}

				if(z.indexOf("-r")!=-1){//inputing read1
					ref=z.get(z.indexOf("-r")+1);
					if(!ref.substring(0,1).equals("/")){
						ref=dir+"/"+ref;
					}
					File test1=new File(ref);
					if(!test1.exists()){
						System.out.println("	reference genome file not found. please check");
						breakflag=3;
					}
				}
				else{
					System.out.println("reference genome file not set, circRNA sequence will not be outputed.");
				}
				
				//output
				if(z.indexOf("-o")!=-1){//
					prefix=z.get(z.indexOf("-o")+1);
					}
				
				if(z.indexOf("-d")!=-1){//
					odir=z.get(z.indexOf("-d")+1);
					}
				
				File test1=new File(odir);
				if(test1.exists()){
					System.out.println("	outputdir existed, please check");
					breakflag=4;
				}
				
				// vis / control
				
				if(z.indexOf("-min")!=-1){//
					target_min=(int)Double.parseDouble(z.get(z.indexOf("-min")+1));
					}
				if(z.indexOf("-max")!=-1){//
					target_max=(int)Double.parseDouble(z.get(z.indexOf("-max")+1));
					}
				if(z.indexOf("-rank")!=-1){//
					F_rank=(int)Double.parseDouble(z.get(z.indexOf("-rank")+1));
					}
				if(z.indexOf("-exp")!=-1){//
					F_exp=(int)Double.parseDouble(z.get(z.indexOf("-exp")+1));
					}
				if(z.indexOf("-vis")!=-1){//
					out_less=(int)Double.parseDouble(z.get(z.indexOf("-vis")+1));
					}
				if(z.indexOf("-width")!=-1){//
					length=(int)Double.parseDouble(z.get(z.indexOf("-width")+1));
					}
				if(z.indexOf("-iso")!=-1){//
					iso=(int)Double.parseDouble(z.get(z.indexOf("-iso")+1));
					}
				if(z.indexOf("-ran")!=-1){//
					ran_seed=(int)Double.parseDouble(z.get(z.indexOf("-ran")+1));
					}


			}
			if(breakflag==0){
			

        File outputdir=new File(odir);
        outputdir.mkdir();
        String output_z=outputdir+"/"+prefix+".list";
        String output_g=outputdir+"/"+prefix+".gtf";

        
        List<Integer> library = new ArrayList<Integer>();
		
		List<Integer> map_length = new ArrayList<Integer>();
		List<Integer> num1=new ArrayList<Integer>();
		if(libray_file!=null){

			FileReader inl=new FileReader(libray_file);
			LineNumberReader readerl=new LineNumberReader(inl);
			String line=readerl.readLine();
			while(line!=null){
				if(line.indexOf("library_length")!=-1){
					String[] tpa=line.split("	");
					int lp=Integer.parseInt(tpa[1]);
					if(lp>=readl){
					library.add(lp);
					}
				}
				else{
					String[] tmp=line.split("	");
					if(tmp.length==8){
						int rltest=Integer.parseInt(tmp[4]);
						if(map_length.contains(rltest)){
							int k = map_length.indexOf(rltest);
							int tmpnum= num1.get(k)+1;
							num1.set(k, tmpnum);
						}
						else{
							map_length.add(rltest);
							num1.add(1);
						}
					}
				}
				line=readerl.readLine();
			}
		
			
			int m=0;
			int nummax=0;
			
			while(m<map_length.size()){
				if(nummax<num1.get(m)){
					readl=map_length.get(m);
					nummax=num1.get(m);
				}
				m++;
			
		}
		System.out.println("	read_length=" + readl);
		}
	//     String input="/Users/yizheng/Documents/data19_liverT/hela/test_1";

       
       
        BufferedWriter writeStream=new BufferedWriter(
	    		new FileWriter(output_z));
        writeStream.write("Image_ID	Circle_ID	Chr	start	end	total_exp	isoform_number	isoform_exp	isoform_length	isoform_state	strain	isoform_cirexon");
        writeStream.newLine();
        
        BufferedWriter writeStream2=new BufferedWriter(
	    		new FileWriter(output_g));
        writeStream2.write("# ");
        int z1=0;
        while(z1<args.length){
        	writeStream2.write(args[z1]+" ");
        	z1++;
        }
        writeStream2.newLine();
        writeStream2.write("# CIRI-vis version 1.2.2");
        writeStream2.newLine();
        
        Map<String,Integer> givelist=new HashMap<String,Integer>();
        
        if(iflist!=0){
    		FileReader in=new FileReader(listinput);
    		LineNumberReader reader=new LineNumberReader(in);
            String linet =reader.readLine();
            while(linet!=null){
            	givelist.put(linet, 0);
            	linet=reader.readLine();
            }
            reader.close();
        }
       
        	
        Map<String,String[]> list=new HashMap<String,String[]>();
        List<Integer> exp_list=new ArrayList<Integer>();
        int total_exp=0;
		FileReader in=new FileReader(input);
		LineNumberReader reader=new LineNumberReader(in);
        String linet =reader.readLine();
        linet=reader.readLine();
        if(iflist==0){
		while(linet!=null){
			String[] A=linet.split("	");
			if(A.length==8){
				linet=linet+"	n/a	n/a	n/a";
			}
			A=linet.split("	");
		    //exp cal;
	        Map<String, Boolean> readid_bsj = new HashMap<String, Boolean>();
	        Map<String, Boolean> readid_ro = new HashMap<String, Boolean>();
	        Map<String, Boolean> readid = new HashMap<String, Boolean>();
	         if(!A[7].equals("n/a")){
	        	String[] tmpstring=A[7].split("<");
	         	int k=tmpstring.length-1;
	         	String[] classname=new String[k];
	            int o=0;
	            while(o<k){
	           	 String[] tmpstring1=tmpstring[o+1].split("::");
	           	 classname[o]=tmpstring1[0];            	 
	           	 tmpstring1[1]=tmpstring1[1].replace(")","),");
	           	 tmpstring1[1]=tmpstring1[1].replace("),(",")(");
	           	 tmpstring1[1]=tmpstring1[1].replace(",>","");
	           	 int o1=0;
	           	 String[] tmpstring2=tmpstring1[1].split(",");
	           	 if(tmpstring1[1].equals(">")!=true){
	           	 while(o1<tmpstring2.length){
	           		 String[] tmpstring3=tmpstring2[o1].split("\\(");
	           		 readid_bsj.put(tmpstring3[0],Boolean.TRUE);
	           		 readid.put(tmpstring3[0],Boolean.TRUE);
	       			 o1++;
	       			}
	            }//获得read连接信息
	           	 o++;
	   }
	         }
	         //long_read
	         if(A[8].equals("n/a")!=true){
	        	 String[] tmpstring1=A[8].split("&&");
	        	 int o1=0;
	        	 while(o1<tmpstring1.length){
	        		 String[] tmpstring2=tmpstring1[o1].split("##");
	        		 readid_ro.put(tmpstring2[0], Boolean.TRUE);
	        		 readid.put(tmpstring2[0], Boolean.TRUE);
	        		 o1++;
	        	 }
	         }
	         
	         int total_num=readid.size();
	         exp_list.add(total_num);
	         total_exp=total_exp+total_num;
	         String[] tmpline={linet,String.valueOf(total_num)};
			 list.put(A[0],tmpline);

			 linet=reader.readLine();
		}
        }
        else{
    		while(linet!=null){
			String[] A=linet.split("	");
			if(A.length==8){
				linet=linet+"	n/a	n/a";
			}
			A=linet.split("	");
			if(givelist.containsKey(A[0])){
		         String[] tmpline={linet,String.valueOf(0)};
				 list.put(A[0],tmpline);
			}
			linet=reader.readLine();
    		}
        }
		reader.close();
		//caluclat
      
        	if(F_rank!=0){
                Collections.sort(exp_list);
                int totalsize=exp_list.size();
       //         System.out.println(totalsize+" "+(int)(totalsize*(100-F_rank))/100);
                target_min=Math.max(exp_list.get((int)(totalsize*(100-F_rank))/100),target_min);
        	}
        	if(F_exp!=0){
                Collections.sort(exp_list);
                int a=exp_list.size()-1;
                int exptar=0;
                
                while(a>0){
                	exptar=exptar+exp_list.get(a);
                	if(exptar*100>total_exp*F_exp){
                		target_min=Math.max(exp_list.get(a),target_min);
                		break;
                	}
                	a--;
                }
        	}
        	
        	 if(iflist==1){
        		 target_min=0;
        		 target_max=999999999;
        	 }
             System.out.println("	target_min was set to "+target_min);
        int y=1;
    	Iterator<String> it=list.keySet().iterator();   
		while(it.hasNext()){
   	     String key;   
   	     String[] value;   
   	     key=(String) it.next();
   	     value=list.get(key);
		 int total_num=Integer.parseInt(value[1]);
		 int zs=Integer.parseInt(key.split("\\:")[1].split("\\|")[0]);
		 int ze=Integer.parseInt(key.split("\\:")[1].split("\\|")[1]);
	//	 System.out.println(zs+" "+ze);

		         if(total_num>=target_min&&total_num<=target_max&&ze>zs+50){//start painting!
		        	System.out.println("start sample_"+y);
		 			DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();           // Create an instance of org.w3c.dom.Document.      
					String svgNS = "zy";       
					Document document = domImpl.createDocument(svgNS, "svg", null);           // Create an instance of the SVG Generator.    
					SVGGraphics2D svgGenerator = new SVGGraphics2D(document); 
					sample_MIX_all_easy4 test=new sample_MIX_all_easy4(value[0],length,out_cov,out_circle,out_bsj,out_ro,out_less,library,readl,null,null,iso,ran_seed);
					test.paint(svgGenerator);           // Finally, stream out SVG to the standard output using        
					boolean useCSS = true; // we want to use CSS style attributes    
					File f = new File(outputdir+"/"+prefix+"_"+y+".svg"); 
					f.deleteOnExit();
					Writer out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");    
					svgGenerator.stream(out,useCSS);    
					int width=test.getwidth();
					int high=test.gethigh();
			     	makepdf2_6_next mypdf =new makepdf2_6_next();
					String a=mypdf.conver(f.toString(),outputdir.toString(),"application/pdf",width,high);
					List<String> output_info = test.get_info();
					int p=0;
					while(p<output_info.size()){
						writeStream.write(prefix+"_"+y+"	"+output_info.get(p));
						writeStream.newLine();
						p++;
					}
					y++;
		         }
			}
        writeStream.close();
        
		FileReader inz=new FileReader(output_z);
		LineNumberReader readerz=new LineNumberReader(inz);
        String line =readerz.readLine();
        line=readerz.readLine();
        int isonum=1;
        String before="null";
        while(line!=null){
        	String[] tmp=line.split("	");
        	if(tmp[9].equals("Full")){
        		if(before.equals(tmp[1])){
        			isonum++;
        		}
        		else{
        			isonum=1;
        		}
        		before=tmp[1];
        		
        		if(tmp[10].equals("-")){
        			writeStream2.write(tmp[2]+"	CIRI-Full	circRNA_transcript	"+tmp[3]+"	"+tmp[4]+"	.	-	.	gene_id: \""+tmp[11]+"\"; transcript_id: \""+tmp[1]+"."+isonum+"\"; BSJ_count: \""+tmp[5]+"\"; Isoform_BSJ_count: \""+tmp[7]+"\";");
        			writeStream2.newLine();
        			String[] cirexon = tmp[12].split(",");
        			int num=1;
        			int g1=0;
        			while(g1<cirexon.length){
        				String[] pos = cirexon[cirexon.length-1-g1].split("-");
        				writeStream2.write(tmp[2]+"	CIRI-Full	cirexon	"+pos[0]+"	"+pos[1]+"	.	-	.	gene_id: \""+tmp[11]+"\"; transcript_id: \""+tmp[1]+"."+isonum+"\"; exon_number: \""+num+"\"; BSJ_count: \""+tmp[5]+"\"; Isoform_BSJ_count: \""+tmp[7]+"\";");
            			writeStream2.newLine();
            			g1++;
            			num++;
        			}
        		}else{
        			writeStream2.write(tmp[2]+"	CIRI-Full	circRNA_transcript	"+tmp[3]+"	"+tmp[4]+"	.	"+tmp[10]+"	.	gene_id: \""+tmp[11]+"\"; transcript_id: \""+tmp[1]+"."+isonum+"\"; BSJ_count: \""+tmp[5]+"\"; Isoform_BSJ_count: \""+tmp[7]+"\";");
        			writeStream2.newLine();
        			String[] cirexon = tmp[12].split(",");
        			int num=1;
        			int g1=0;
        			while(g1<cirexon.length){
        				String[] pos = cirexon[g1].split("-");
        				writeStream2.write(tmp[2]+"	CIRI-Full	cirexon	"+pos[0]+"	"+pos[1]+"	.	"+tmp[10]+"	.	gene_id: \""+tmp[11]+"\"; transcript_id: \""+tmp[1]+"."+isonum+"\"; exon_number: \""+num+"\"; BSJ_count: \""+tmp[5]+"\"; Isoform_BSJ_count: \""+tmp[7]+"\";");
            			writeStream2.newLine();
            			g1++;
            			num++;
        			}

        		}
        	}
        	line=readerz.readLine();
        }
        writeStream2.close();

        if(ref!=null){
        	Seq out=new Seq(output_z,ref);
        	int j=out.getseq();
        }
		long tend = System.currentTimeMillis();
		System.out.println("Take time: "+ (tend-tstart)/1000 +"s");
		

       }
			else{
				System.out.println("encounter problems, please check, type '-h' for usages.");
			}
			}
		if(breakflag==1){
			System.out.println("");
			System.out.println("");
			System.out.println("Program:");
			System.out.println(v);
			System.out.println("");
			System.out.println("This Program can generate the circRNA reads alignment figure and isoform relative abundance in PDF format");
			System.out.println("");
			System.out.println("Input file requirements:");
			System.out.println("");
			System.out.println("	IF you runned CIRI-full Pipeline in previous step, the input file will be named XXX_merge_circRNA_detail.anno under CIRI-full_output folder ");
			System.out.println("	IF you only run CIRI-AS with '-d yes' parameter in previous step, the input file will be named XXX_jav.list under your CIRI-AS output folder ");
			System.out.println("	Library length file is nessaracy for isoform expression estimation. library length file will be XXX_library_length.list under your CIRI-AS output folder");
			System.out.println("");
			System.out.println("Usage:");
			System.out.println("");
			System.out.println("-i	The path of input file of CIRI-vis.(required)");
			System.out.println("-l	The path of library length file.(required for isoform quantification)");
			System.out.println("-r	The path of reference genome sequence in FASTA format. (required for output circRNA sequence)");
			System.out.println("-list	The list of choosen circRNA BSJ.(optional)");
			System.out.println("");
			System.out.println("-d	The dir of output. Default currentdir/stdir (optional)");
			System.out.println("-o	The prefix of output. Default stout (optional)");
			System.out.println("");
			System.out.println("-max	The maximum expression(BSJ reads number) of circRNA that displayed by vis. Default 999999999");
			System.out.println("-min	The minimum expression(BSJ reads number) of circRNA that displayed by vis. Default 10. Note: please only use one of -min, -exp, -rank" );
			System.out.println("-rank	Only display the expression top X% of circRNA");
			System.out.println("-exp	Only display the top expression circRNA that contain X% of BSJ reads.");
			System.out.println("-iso	The maxium number of considering isoform, default 10. High value will make the quantification slower.");
			System.out.println("-ran	Random seed.");
		}


		}
}

