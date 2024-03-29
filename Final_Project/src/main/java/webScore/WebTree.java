package webScore;

import java.io.IOException;
import quickSort.Keyweb;
import quickSort.KeywebList;

public class WebTree {
	public WebNode root;
	public KeywebList rootList = new KeywebList();
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
			Keyweb rootNode = new Keyweb(rootPage.url, rootPage.name, root.nodeScore);
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