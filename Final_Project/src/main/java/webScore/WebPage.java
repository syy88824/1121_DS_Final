package webScore;

import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	public String url;
	public String name;
	public WordCounter counter;
	public KeywordList keys = new KeywordList();
	public double score;
	
	public WebPage(String url, String name){
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url);	
		this.score = 0;
	}
	
	public void setScore() throws IOException{
		ArrayList<webScore.Keyword> keywords = keys.getList();
//		1. calculate score
		for(Keyword k : keywords){		
			k.setCount(counter.countKeyword(k.name));
			score += k.weight * k.count;
		}
	}	
}
