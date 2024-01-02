package webCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import webScore.*;

<<<<<<< Updated upstream
public class GoogleQuery 
{
	public String searchKeyword;
	public String url;
	public String content;
	
	public GoogleQuery(String searchKeyword) throws IOException
	{
		this.searchKeyword = searchKeyword;
		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=20";
	}
	
	private String fetchContent() throws IOException
	{
		String retVal = "";

		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		//set HTTP header
		conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line = bufReader.readLine()) != null)
		{
			retVal += line;
		}
		return retVal;
	}
	
	public HashMap<String, String> query() throws IOException
	{
		if(content == null)
		{
			content = fetchContent();
		}

		HashMap<String, String> retVal = new HashMap<String, String>();
		
		
		/* 
		 * some Jsoup source
		 * https://jsoup.org/apidocs/org/jsoup/nodes/package-summary.html
		 * https://www.1ju.org/jsoup/jsoup-quick-start
 		 */
		
		//using Jsoup analyze html string
		Document doc = Jsoup.parse(content);
		
		//select particular element(tag) which you want 
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		
		for(Element li : lis)
		{
			try 
			{
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				
				if(title.equals("")) 
				{
					continue;
				}
				
				System.out.println("Title: "+title + " , url: " + citeUrl);
				
				//put title and pair into HashMap
				retVal.put(title, citeUrl);
				crawlSubpages(citeUrl, retVal);
			} catch (IndexOutOfBoundsException e) 
			{
//				e.printStackTrace();
			}
		}
		return retVal;
	}
/*<<<<<<< Updated upstream
=======*/
	
	//加入爬子網頁的method
	private void crawlSubpages(String url, HashMap<String, String> retVal) {
	    try {
	        
	        String realUrl = extractRealUrl(url);

	        Document subPageDoc = Jsoup.connect(realUrl).get();

	        
	        String subPageTitle = subPageDoc.title();
	        System.out.println("子網頁標題: " + subPageTitle);

	        
	        Elements paragraphs = subPageDoc.select("p");
	        StringBuilder subPageContent = new StringBuilder();
	        for (Element paragraph : paragraphs) {
	            subPageContent.append(paragraph.text()).append("\n");
	        }
	        System.out.println("子網頁內容:\n" + subPageContent);

//<<<<<<< Updated upstream

			// Create a WebPage instance for the subpage
           // WebPage subPage = new WebPage("Subpage Title: " + subPageTitle, "Subpage Content:\n" + subPageContent);
            WebPage subPage = new WebPage(, subPageTitle);

            // Create a WebNode instance for the subpage
            WebNode subNode = new WebNode(subPage);
            
            // Add the subNode to the parentNode's children
            subNode.parent.addChild(subNode);

            // Recursively crawl subpages for the subNode
            //crawlSubpages(realUrl, subNode);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(subPageTitle, realUrl);
            crawlSubpages(realUrl, map);
			
/*=======
>>>>>>> Stashed changes*/
	        
	        retVal.put("Subpage Title: " + subPageTitle, "Subpage Content:\n" + subPageContent);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	//解析 Google 搜尋结果的連結，獲取其中真實的 URL
	private String extractRealUrl(String googleUrl) {
	    
	    int startIndex = googleUrl.indexOf("/url?q=") + 7;
	    int endIndex = googleUrl.indexOf("&sa=");
	    if (startIndex != -1 && endIndex != -1) {
	        return googleUrl.substring(startIndex, endIndex);
	    } else {
	        
	        return googleUrl;
	    }
	}
/*<<<<<<< Updated upstream
>>>>>>> Stashed changes*/

}
//>>>>>>> Stashed changes
=======
public class GoogleQuery {
	public String searchKeyword;
	public String url;
	public String content;
	//public ArrayList<WebPage> subWebPages; // Add a list to store sub-webpages

	public GoogleQuery(String searchKeyword) {
		this.searchKeyword = searchKeyword;
		try {
			String encodeKeyword = java.net.URLEncoder.encode(searchKeyword, "utf-8");
			this.url = "https://www.google.com/search?q=" + encodeKeyword + "&oe=utf8&num=20";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//this.subWebPages = new ArrayList<>();
	}

	private String fetchContent() throws Exception {
		String retVal = "";

		URL u = new URL(url);
		SslUtils.ignoreSsl();
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while ((line = bufReader.readLine()) != null) {
			retVal += line;
		}
		return retVal;
	}

	private String fetchContent(String link){
		URL url;
		String retVal = "";
		try {
			url = new URL(link);
			URLConnection conn = url.openConnection();
			
			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line = null;
			while ((line = br.readLine()) != null){
			    retVal = retVal + line + "\n";
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;
	}

	// New method to fetch sub-webpage titles and URLs
	/*
	 * private void fetchSubWebPages() throws IOException { Document doc =
	 * Jsoup.parse(content); Elements links = doc.select("a[href]");
	 * 
	 * for (Element link : links) { String subPageUrl = link.absUrl("href");
	 * 
	 * if (!subPageUrl.isEmpty())
	 * 		 { String subPageTitle = link.text(); 
			 * WebPage subWebPage = new WebPage(subPageUrl, subPageTitle);
			 * subWebPages.add(subWebPage); } 
		} }
	 */
	private ArrayList<WebNode> fetchSublink(String link) throws IOException {
		ArrayList<WebNode> sub = new ArrayList<WebNode>();

		int counter = 0;
		char slash;
		int where = 0;
		String concatlink = "1";
		String content = this.fetchContent(link);

//		�P�_�����O���O�l����
		for (int i = 0; i < link.length(); i++) {
			slash = link.charAt(i);
			if (slash == '/')
				counter++;
			if (counter >= 3) {
				where = i;
				break;
			}
		}
		if(counter == 0) {
			System.out.println("115  no subPage");
		}
//		���X�D���������}
		if (where != 0) {
			concatlink = link.substring(0, where);
		}

		Document doc = Jsoup.parse(content);
		Elements aLinks = doc.select("a[href]");
		for (Element element : aLinks) {
			String suburl = element.attr("href");
			String subtitle = element.text();
//			�D�X�}�Y���Ohttp��https���S�s�bhref���F��
			if (!suburl.contains("http://") && !suburl.contains("https://")) {
				if (concatlink != "1") {
					suburl = concatlink + suburl;
				} else {
					suburl = link + suburl;
				}
			}
			if (sub.size() <= 2) {
				sub.add(new WebNode(new WebPage(suburl, subtitle)));
			} else {
				break;
			}

		}
		System.out.println(sub);

		return sub;
	}

	//透過關鍵字爬到的所有結果
	public HashMap<String, String> query() throws Exception {
		if (content == null) {
			content = fetchContent();

		}
		
		HashMap<String, String> retVal = new HashMap<>();

		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");

		//針對每一個搜尋結果的動作
		for (Element li : lis) {
			//System.out.println("GoogleQuery151  an element");
			try {
				String citeUrl = li.select("a").get(0).attr("href").replace("/url?q=", "");
				citeUrl = java.net.URLDecoder.decode(citeUrl, "UTF-8");
				String[] clean = citeUrl.split("&sa");
				citeUrl = clean[0];
				String title = li.select("a").get(0).select(".vvjwJb").text();

				if (title.equals("")) {
					continue;
				}
				//citeUrl = citeUrl.substring(7);
				//System.out.println("Title: " + title + " , url: " + citeUrl);
				retVal.put(title, citeUrl);
				
			} catch (IndexOutOfBoundsException e) {
				// e.printStackTrace();
			}
		}

		return retVal;
	}
	
	public HashMap<String, String> score() throws Exception {
		HashMap<String, String> webMap = this.query();
		System.out.println("webMap size = " + webMap.size());
		for (Map.Entry<String, String> webMapEntry : webMap.entrySet()) {
			System.out.println("GoogleQuery 183 countScore");
			String url = webMapEntry.getValue();
			String title = webMapEntry.getKey();
			System.out.println("GoogleQuery186  url = " + url + "   title = " + title);
			WebPage rootPage = new WebPage(url, title);
			WebNode rootNode = new WebNode(rootPage);				
			ArrayList<WebNode> subPages = fetchSublink(url);
			for(WebNode subPage : subPages) {
				System.out.println("GoogleQuery190  addChild ");
				rootNode.addChild(subPage);
			}
			WebTree tree = new WebTree(rootPage);
			tree.setPostOrderScore();
		}
		return webMap;
	}
}
>>>>>>> Stashed changes
