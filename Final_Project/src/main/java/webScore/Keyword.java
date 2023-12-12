package webScore;

public class Keyword {
	public String name;
    public int count;
    public int weight;
    
    //count預設為0 等到進入wordCounter算出次數之後再使用setCount key新的值
    public Keyword(String name, int weight){
		this.name = name;
		this.count = 0;
		this.weight = weight;
    }
    
    @Override
    public String toString(){
    	return "["+name+","+count+","+weight+"]";
    }

	public void setCount(int count) {
		this.count = count;
	}
    
}
