package webScore;

import java.io.IOException;
<<<<<<< Updated upstream
import java.util.ArrayList;
=======
import quickSort.Keyweb;
import quickSort.KeywebList;
>>>>>>> Stashed changes

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
			quickSort.Keyword rootNode = new quickSort.Keyword(rootPage.url, root.nodeScore);
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
	
	//print出root的webTree(晚一點可以刪掉)之後，將找到的所有rootPage進行quickSort排序再print出來
	//之後結合前端的話只需要看rootList.sort()要塞在哪裡就好 另外兩個不需要
	public void eularPrintTree(){
		eularPrintTree(root);
		rootList.sort();
		rootList.output();
	}
	
	/*private void eularPrintTree(WebNode startNode){
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
	}*/
}