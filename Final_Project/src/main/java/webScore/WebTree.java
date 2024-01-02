package webScore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import quickSort.Keyword;

public class WebTree {
	public WebNode root;
	public quickSort.KeywordList rootList = new quickSort.KeywordList();
	public WebPage rootPage;
	
	public WebTree(WebPage rootPage){
		this.rootPage = rootPage;
		this.root = new WebNode(rootPage);
	}
	
	//在使用postOrder traversal算出rootPage的score後 將root的url跟score new出一個quickSort那邊的rootNode
	//並加到quickSort那邊的rootList
	public void setPostOrderScore() throws IOException{
		setPostOrderScore(root);
		if(rootPage != null) {
<<<<<<< Updated upstream
			quickSort.Keyword rootNode = new quickSort.Keyword(rootPage.name, rootPage.url, root.nodeScore);
=======
			quickSort.Keyword rootNode = new quickSort.Keyword(rootPage.url, rootPage.name, root.nodeScore);
>>>>>>> Stashed changes
			rootList.add(rootNode);
		}		
	}
	
	private void setPostOrderScore(WebNode startNode) throws IOException{
		//2. compute the score of children nodes via post-order
		for(WebNode child : startNode.children){
			setPostOrderScore(child);		
		}
		//setNode score of startNode
		startNode.setNodeScore();
	}
	
	//把這裡改成return Entry<String, String> entry  分數最大的要最先進去
	//rsNum>6時不再繼續處理結果
	public HashMap<String, String> sortResult(){
		//eularPrintTree(root);
		int rsNum = 0;
		ArrayList<Keyword> results = rootList.sort();
		HashMap<String, String> rsMap = new HashMap<String, String>();
		for(quickSort.Keyword result : results) {
			rsNum++;
			rsMap.put(result.link, result.title);
			if(rsNum >6) {
				break;
			}
			
		}
		//rootList.output();
		return rsMap;
	}
	
	private void eularPrintTree(WebNode startNode){
		int nodeDepth = startNode.getDepth();
		
		if(nodeDepth > 1) System.out.print("\n" + repeat("\t", nodeDepth-1));

		System.out.print("(");
		System.out.print(startNode.webPage.name + "," + startNode.nodeScore);
		
		//3.print child via pre-order
		for(WebNode child : startNode.children){
			eularPrintTree(child);		
		}
		
		System.out.print(")");
		
		if(startNode.isTheLastChild()) System.out.print("\n" + repeat("\t", nodeDepth-2));
		
	}
	
	private String repeat(String str, int repeat){
		String retVal = "";
		for(int i = 0; i < repeat; i++){
			retVal += str;
		}
		return retVal;
	}
}