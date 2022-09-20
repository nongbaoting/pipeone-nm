import java.util.ArrayList;
import java.util.List;

public class apart_exon {
	public List<int[]> exonlist = new ArrayList<int[]>();
	public List<int[]> apart_result = new ArrayList<int[]>();
	public apart_exon(List<int[]> exonlist1){
		this.exonlist=exonlist1;
	}
	
	List<int[]> get_apart(){
		
		int a=0;
		List<int[]> tmp_result=new ArrayList<int[]>();
		while(a<exonlist.size()){
			int[] positon=exonlist.get(a);
			int b=0;
			int ifexist1=0;
			int ifexist2=0;
			while(b<tmp_result.size()){
				if(tmp_result.get(b)[0]==positon[0]){
					tmp_result.get(b)[1]=1;
					ifexist1=1;
				}
				if(tmp_result.get(b)[0]==positon[1]){
					tmp_result.get(b)[2]=1;
					ifexist2=1;
				}
				
				if(tmp_result.get(b)[0]>positon[0]&&tmp_result.get(b)[0]<positon[1]){
					tmp_result.get(b)[1]=1;	
					tmp_result.get(b)[2]=1;	
				}
				b++;
			}
			
			if(ifexist1==0){
				int[] tmp={positon[0],1,0};
				int c=0;
				int ife=0;
				
				while(c<tmp_result.size()){
					if(tmp[0]<tmp_result.get(c)[0]){
						tmp_result.add(c,tmp);
						ife=1;
						break;
					}
					c++;
				}
				if(ife==0){
					tmp_result.add(tmp);
				}
			}
			
			if(ifexist2==0){
				int[] tmp={positon[1],0,1};
				int c=0;
				int ife=0;
				
				while(c<tmp_result.size()){
					if(tmp[0]<tmp_result.get(c)[0]){
						tmp_result.add(c,tmp);
						ife=1;
						break;
					}
					c++;
				}
				if(ife==0){
					tmp_result.add(tmp);
				}
			}
			a++;
		}
		a=0;
		
		while(a<exonlist.size()){
			int[] positon=exonlist.get(a);
			int b=0;
			while(b<tmp_result.size()){
				if(tmp_result.get(b)[0]>positon[0]&&tmp_result.get(b)[0]<positon[1]){
					tmp_result.get(b)[1]=1;	
					tmp_result.get(b)[2]=1;	
				}
				b++;
			}
			a++;
		}

		a=0;
		
		System.out.println(tmp_result.size());
		while(a<tmp_result.size()-1){
			int[] left=tmp_result.get(a);
			int[] right=tmp_result.get(a+1);
			if(left[1]==0&&right[2]==0){
			}
			else{
				int[] tmp={left[0],right[0]};
				apart_result.add(tmp);
			}
			a++;
		}
		return apart_result;
	}

}
