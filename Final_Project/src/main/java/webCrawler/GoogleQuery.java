package webCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

<<<<<<< Updated upstream
=======
				crawlSubpages(citeUrl, retVal);
				
>>>>>>> Stashed changes
			} catch (IndexOutOfBoundsException e) 
			{
//				e.printStackTrace();
			}
		}
		return retVal;
	}
<<<<<<< Updated upstream
=======
	
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

<<<<<<< Updated upstream

			// Create a WebPage instance for the subpage
            WebPage subPage = new WebPage("Subpage Title: " + subPageTitle, "Subpage Content:\n" + subPageContent);

            // Create a WebNode instance for the subpage
            WebNode subNode = new WebNode(subPage);
            
            // Add the subNode to the parentNode's children
            subNode.parent.addChild(subNode);

            // Recursively crawl subpages for the subNode
            //crawlSubpages(realUrl, subNode);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(subPageTitle, realUrl);
            crawlSubpages(realUrl, map);
			
=======
>>>>>>> Stashed changes
	        
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
<<<<<<< Updated upstream
>>>>>>> Stashed changes
}
=======
}
>>>>>>> Stashed changes
