package quickSort;

public class Keyword {
	public String link;
    public double score;
    
    //這裡的keyword是每個爬到的rootLink跟他們的score
    //score不用print出來 但要用來做quickSort的比較 所以必須留著
    //link到時候要依序傳到前端
    public Keyword(String link, double score){
		this.link = link;
		this.score = score;
    }
    
    /*@Override
    public String toString(){
    	return "["+name+","+count+"]";
    }*/
}