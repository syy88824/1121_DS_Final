package quickSort;

public class Keyword {
<<<<<<< Updated upstream
	public String title;
	public String link;
=======
	public String link, title;
>>>>>>> Stashed changes
    public double score;
    
    //這裡的keyword是每個爬到的rootLink跟他們的score
    //score不用print出來 但要用來做quickSort的比較 所以必須留著
    //link到時候要依序傳到前端
<<<<<<< Updated upstream
    public Keyword(String title, String link, double score){
		this.title = title;
    	this.link = link;
=======
    public Keyword(String link, String title, double score){
    	this.title = title;
		this.link = link;
>>>>>>> Stashed changes
		this.score = score;
    }
    
    /*@Override
    public String toString(){
    	return "["+name+","+count+"]";
    }*/
}