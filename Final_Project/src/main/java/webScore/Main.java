package webScore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLSession;

public class Main {
	
	//public static ArrayList<Keyword> keywords = new ArrayList<>();
	//public static quickSort.KeywordList keyList = new quickSort.KeywordList();
	
	public static void main(String[] args) throws IOException {
		//這裡要return webCrawler的結果
		WebPage rootPage = new WebPage("https://www.litv.tv/vod/drama", "litv");		
		WebTree tree = new WebTree(rootPage);
		
		//build childnode
		/*tree.root.addChild(new WebNode(new WebPage("https://www.litv.tv/vod/drama/list.do?category_id=53&page=1","Taiwan")));
		tree.root.addChild(new WebNode(new WebPage("https://www.litv.tv/vod/drama/list.do?category_id=530&page=1","Korea")));
		tree.root.addChild(new WebNode(new WebPage("https://www.litv.tv/vod/drama/list.do?category_id=54&page=1", "Japan")));
		
<<<<<<< HEAD
		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Publications.html","Publication")));
=======
		// Iterate through each child node and crawl its subpages*/
       /* for (WebNode childNode : tree.root.children) {
            crawlSubpages(childNode);
        }

		
		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Publications.html","Publication")));
>>>>>>> df45820cd4de2bf10b4372877cbd63fa2a328e10
		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Projects.html","Projects")));
		tree.root.children.get(1).addChild(new WebNode(new WebPage("https://vlab.cs.ucsb.edu/stranger/", "Stranger")));
		tree.root.addChild(new WebNode(new WebPage("http://soslab.nccu.edu.tw/Members.html", "Member")));
		tree.root.addChild(new WebNode(new WebPage("http://www3.nccu.edu.tw/~yuf/course.htm","Course")));*/
		
		/*
		 這裡要改成將Keywords class加進array keywords裡面
		 看Keyword k = new Keyword(name, weight)的parameter要不要直接加count 然後在這裡分別對每個keyword call wordCounter
		 */
		/*System.out.println("Please input (1)num of keywords (2)name and weight:");
		Scanner scanner = new Scanner(System.in);*/
		
		
		
		/*while(scanner.hasNextLine()){
			int numOfKeywords = scanner.nextInt();
			ArrayList<Keyword> keywords = new ArrayList<Keyword>();
			
			for(int i = 0; i < numOfKeywords; i++)
			{
				String name = scanner.next();
				double weight = scanner.nextDouble();
				Keyword k = new Keyword(name, weight);
				keywords.add(k);
			}*/
			
			tree.setPostOrderScore();
			//tree.eularPrintTree();
			
			
			
		}
		

	/*private static void crawlSubpages(WebNode parentNode) {
        try {
            String url = parentNode.webPage.url;
            HashMap<String, String> subpages = new webCrawler.GoogleQuery(url).query();

            // Create WebNode for each subpage and add to the children list of parentNode
            for (Map.Entry<String, String> entry : subpages.entrySet()) {
                String subpageTitle = entry.getKey();
                String subpageUrl = entry.getValue();

                WebPage subpage = new WebPage(subpageUrl, subpageTitle);
                WebNode subpageNode = new WebNode(subpage);
                parentNode.addChild(subpageNode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
//>>>>>>> df45820cd4de2bf10b4372877cbd63fa2a328e10
	
	
//	static {
//		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() 
//		{
//			public boolean verify(String hostname,SSLSession session) 
//			{
//				return true;
//			}
//		});
//	}	
