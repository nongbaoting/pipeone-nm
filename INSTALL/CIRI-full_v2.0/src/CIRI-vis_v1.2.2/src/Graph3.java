import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph3 {
		// 邻接矩阵
		private double[][] matrix;
		// 顶点数组
		private String[] vertex;
		// 顶点的数目
		private int vertexNum;
		// 当前结点是否还有下一个结点，判断递归是否结束的标志
		// 所有路径的结果集
		private List<List<String>> allresult = new ArrayList<List<String>>();
		
		private String t = "break";
		
		private int countdown=0;

		public Graph3(double[][] matrix, String[] vertex) {
			if (matrix.length != matrix[0].length) {
				throw new IllegalArgumentException("该邻接矩阵不是方阵");
			}
			if (matrix.length != vertex.length) {
				throw new IllegalArgumentException("结点数量和邻接矩阵大小不一致");
			}
			this.matrix = matrix;
			this.vertex = vertex;
			vertexNum = matrix.length;
		}

		/**
		 * 深度遍历的递归
		 */
		

		private void DFS(int begin, List<List<String>> pathgroup, int pathnum){
			pathgroup.get(pathnum).add(vertex[begin]);
			String bsj=vertex[vertex.length-1]+","+vertex[0];
			int over=0;
			List_toString test1=new List_toString(pathgroup.get(pathnum));
			String test1out=test1.get_toString();
			if(test1out.indexOf(bsj)==-1){
				over=0;
			}
			else{
				over=1;
			}
			int pos=0;
			while(pos<vertex.length){
				if(vertex[pos].equals(pathgroup.get(pathnum).get(0))){
					break;
				}
				pos++;
			}
			if(over==1&&begin>pos){
				int i=0;
				int link=0;
				while(i<vertex.length){
					if(matrix[i][begin]==1)
						link++;
					i++;
				}
				if(link==0){
					pathgroup.get(pathnum).remove(pathgroup.get(pathnum).size()-1);
					pathgroup.get(pathnum).add(t);
		//			System.out.println("across break "+pathnum);
				}
				else{
					pathgroup.get(pathnum).remove(pathgroup.get(pathnum).size()-1);
					pathgroup.get(pathnum).add("no");
		//			System.out.println("across not "+pathnum);
				}

				countdown++;
			}
			else if(over==1&&begin==pos){
		//		System.out.println("complete_finish "+pathnum);
				pathgroup.get(pathnum).remove(pathgroup.get(pathnum).size()-1);
				countdown++;
			}
			else{
				int i=0;
				int ifexist=0;
				int ifbranch=0;
				int j=0;
				//
				String[] newpath=new String[pathgroup.get(pathnum).size()];
				while(j<pathgroup.get(pathnum).size()){
					newpath[j]=(pathgroup.get(pathnum).get(j));
					j++;
				}
				
				int z=0;

				while(i<vertex.length){
					if(matrix[begin][i]!=0&&ifbranch==0){
						DFS(i,pathgroup,pathnum);
						ifexist++;
						ifbranch++;
					}
					else if(matrix[begin][i]!=0&&ifbranch!=0){
						List<String> newp=new ArrayList<String>();
						pathgroup.add(newp);
						
						j=0;
						while(j<newpath.length){
							pathgroup.get(pathgroup.size()-1).add(newpath[j]);
							j++;
						}
						DFS(i,pathgroup,pathgroup.size()-1);
						ifexist++;
					}
					i++;
				}
				if(ifexist==0){
					pathgroup.get(pathnum).add(t);
		//			System.out.println("forward break "+pathnum);
					countdown++;
				}
			}
			
			if(countdown==pathgroup.size()){
				return;
			}
		}

		/**
		 * 开始深度优先遍历
		 */
		public List<List<String>> startSearch() {
			int i=0;
			while(i<vertex.length){
				List<List<String>> result=new ArrayList<List<String>>();
				List<String> path = new ArrayList<String>();
				result.add(path);
				DFS(i,result,0);
				///correct!
				int a=0;
		//		System.out.println("before");
		//		System.out.println(result);
				List<List<String>> result1=new ArrayList<List<String>>();
				int ifstart=0;
				while(a<result.size()){
					if(!result.get(a).get(result.get(a).size()-1).equals("no"))
						result1.add(result.get(a));
					if(result.get(a).indexOf(vertex[0])==-1)
						ifstart++;
					a++;
				}
				a=0;
		//		System.out.println(result1);
				if(ifstart==0){
					String bsj=vertex[vertex.length-1]+","+vertex[0];

				
				while(a<result1.size()){
					if(!result1.get(a).contains("break")){
						int startpoint=result1.get(a).indexOf(vertex[0]);
						//	int endpoint=result.get(a).indexOf(vertex[vertex.length-1]);
							String[] tmp=new String[startpoint];
							int l=0;
							while(l<startpoint){
								tmp[l]=result1.get(a).get(0);
								result1.get(a).remove(0);
								l++;
							}
							l=0;
							while(l<startpoint){
								result1.get(a).add(tmp[l]);
								l++;
							}
					}

					List_toString a1=new List_toString(result1.get(a));
					String a1s=a1.get_toString();
					
					String tmpa1=a1s+a1s;
					int b=0;
					int ifexist=0;
					while(b<allresult.size()){
						List_toString a2=new List_toString(allresult.get(b));
						String a2s=a2.get_toString();
						String tmpa2=a2s+a2s;
						
					//	System.out.println("tmpa1 "+tmpa1);
					//	System.out.println("tmpa2 "+tmpa2);
						if(a1s.indexOf("break")!=-1){

							if(tmpa1.indexOf(a2s)!=-1){
								allresult.remove(b);
								b--;
							}
							else if(tmpa2.indexOf(a1s)!=-1){
								ifexist++;
								break;
							}
						}
						else{
							if(a1s.equals(a2s)){
								ifexist++;
								break;
							}
						}
						b++;
					}
					if(ifexist==0){
						int startpoint=result1.get(a).indexOf(vertex[0]);
						//	int endpoint=result.get(a).indexOf(vertex[vertex.length-1]);
							String[] tmp=new String[startpoint];
							int l=0;
							while(l<startpoint){
								tmp[l]=result1.get(a).get(0);
								result1.get(a).remove(0);
								l++;
							}
							l=0;
							while(l<startpoint){
								result1.get(a).add(tmp[l]);
								l++;
							}
						allresult.add(result1.get(a));
					}
					a++;	
				}

				
				
				//correct1;
				}
				i++;
			}
				
			return allresult;
		}

	
}
