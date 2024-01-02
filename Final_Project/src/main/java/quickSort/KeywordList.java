package quickSort;

import java.util.ArrayList;

public class KeywordList {
	public static ArrayList<Keyword> lst = new ArrayList<Keyword>();
	
	public void add(Keyword keyword){
		System.out.println("keyword add");
		lst.add(keyword);
    }
	
	public static void resetLst() {
		lst = new ArrayList<Keyword>();
	}
	
	//Quick sort
	public static ArrayList<Keyword> sort( ArrayList<Keyword> list){
		if(list.size() == 0)
		{
			System.out.println("InvalidOperation");
			list = null;
		}
		else 
		{
			quickSort(0, list.size()-1);
		}
		return list;		
	}
	public static ArrayList<Keyword> getLst() {
		return lst;
	}

	//inplace quick sort改成由大到小
	private static void quickSort(int leftbound, int rightbound){
		if (leftbound < rightbound) 
		{
			//因為需要一直用i++來移指標 所以一開始要設leftBound-1
			int i = leftbound - 1;
			//一開始以last element為基準點 用for loop將比基準點小的element移到其右邊 比基準點大的element留在原位
			for (int j = leftbound; j <= rightbound - 1; j++) 
			{
				//if j的分數比基準點的分數大 swap(i, j) 即將index = j的element移到左邊
				if (lst.get(j).score > lst.get(rightbound).score) 
				{
					i++;
					swap(i, j);
				}
			}
			swap((i+1), rightbound);  //將基準點從last element移到比基準點大&小的分界
			
			//基準點現在的index是i+1 所以兩邊切成i和i+2
			quickSort(leftbound, i);
			quickSort(i + 2, rightbound);
		}
	}

	private static void swap(int aIndex, int bIndex){
		Keyword temp = lst.get(aIndex);
		lst.set(aIndex, lst.get(bIndex));
		lst.set(bIndex, temp);
	}
	
	public void output(){
		for(int i = 0; i < lst.size(); i++) {
			Keyword k = lst.get(i);
			System.out.println("url = " + k.link + "   score = " + k.score);
		}
		/*StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < lst.size(); i++){
			Keyword k = lst.get(i);
			if(i > 0)sb.append(" ");
			sb.append(k.toString());
		}	
		System.out.println(sb.toString());	*/
	}
}