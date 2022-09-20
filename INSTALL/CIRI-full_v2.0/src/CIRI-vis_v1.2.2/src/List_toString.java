import java.util.List;

public class List_toString {
	public List input;
	public String output="";
	public List_toString(List a1){
		this.input=a1;
	}
	String get_toString(){
		int i=0;
		while(i<input.size()){
			output=output+(String)input.get(i)+",";
			i++;
		}
		return output;
	}

}
