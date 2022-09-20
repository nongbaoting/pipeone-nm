import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seq {
	public String newlist;
	public String ref;
	public Seq(String newlist1, String ref1) {
		 this.newlist=newlist1;
		 this.ref=ref1;
	 }
	public int getseq() throws IOException{

	//	String label=newlist.split("\\/")[newlist.split("\\/").length-1].split("\\.")[0];
        BufferedWriter writeStream=new BufferedWriter(
	    		new FileWriter(newlist+"_circle.fa"));
        FileReader in =new FileReader(newlist);
		LineNumberReader reader=new LineNumberReader(in);
		String line=reader.readLine();
		line=reader.readLine();
        Map<String,List<String>> mapchr=new HashMap<String,List<String>>();
		while(line!=null){
			String[] tmp=line.split("	");
			//System.out.println(line);
			if(mapchr.containsKey(tmp[2])){
				mapchr.get(tmp[2]).add(line);
			}
			else{
				List<String> a=new ArrayList<String>();
				a.add(line);
				mapchr.put(tmp[2], a);
			}
			line=reader.readLine();
		}
		reader.close();
			
        FileReader in_r =new FileReader(ref);
		LineNumberReader reader_r=new LineNumberReader(in_r);
		String line_r=reader_r.readLine();
		
		while(line_r!=null){
			String seqn=null;
			if(line_r.indexOf(" ")!=-1)
			  seqn=line_r.substring(1,line_r.indexOf(" "));
			else
		      seqn=line_r.substring(1,line_r.length());
			line_r=reader_r.readLine();
			StringBuffer tmpseq=new StringBuffer();
			while(line_r.substring(0,1).equals(">")!=true){
				tmpseq.append(line_r);
				//System.out.println(rseq.length());
				line_r=reader_r.readLine();
				if(line_r==null)
					break;
			}
			
			String rseq=tmpseq.toString();
			System.out.println(seqn+" read "+rseq.length());
			if(mapchr.containsKey(seqn)){
			List<String> b=mapchr.get(seqn);
			int nS=0;
			while(nS<b.size()){
				String[] tmp=b.get(nS).split("	");
        		if(tmp[9].equals("Full")==true){
        			//System.out.println(tmp[8]);
        			String[] cirexon=tmp[12].split(",");
        			int s=0; 
        			int[] startS=new int[cirexon.length];
        			int[] endS=new int[cirexon.length];
        			while(s<cirexon.length){
        				String[] tmp2=cirexon[s].split("-");
        				startS[s]=Integer.parseInt(tmp2[0]);
        				endS[s]=Integer.parseInt(tmp2[1]);
        				s++;
        			}
        	         int iS=0;
        	         String name=">"+tmp[0]+"#"+tmp[1]+" length="+tmp[8]+" "+tmp[7]+"/"+tmp[5]+" "+tmp[10];
        	         String seq="";
        	         while(iS<cirexon.length){
        	        	 seq=seq+rseq.substring(startS[iS]-1,endS[iS]).toUpperCase();
        	        	 iS++;
        	         }
        	         if(tmp[10].equals("+")){
        	        	 reversc a =new reversc(seq);
        	        	 seq=a.getrc();
        	         }
        	         
        	         System.out.println(name);
        	         writeStream.write(name);
        	         writeStream.newLine();
        	         writeStream.write(seq);
        	         writeStream.newLine();
        		}
				nS++;
			}
			}
	    System.out.println(seqn+" Completed");

	}
		reader_r.close();
		writeStream.close();
	
		return 0;
	}
	
	



}
