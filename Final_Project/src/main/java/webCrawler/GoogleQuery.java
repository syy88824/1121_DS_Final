package webCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import quickSort.Keyword;
import webScore.*;

public class GoogleQuery 
{
	public String searchKeyword;
	public ArrayList<String> urls = new ArrayList<String>();
	public String content;

	public GoogleQuery(String searchKeyword) {
		this.searchKeyword = searchKeyword;
		try {
			String encodeKeyword = java.net.URLEncoder.encode(searchKeyword, "utf-8");
			this.urls.add("https://www.google.com/search?q=" + encodeKeyword + "series&oe=utf8&num=3");
			this.urls.add("https://www.google.com/search?q=" + encodeKeyword + "movie&oe=utf8&num=5");
			this.urls.add("https://www.google.com/search?q=" + encodeKeyword + "drama&oe=utf8&num=5");
			this.urls.add("https://www.google.com/search?q=" + encodeKeyword + "documentary&oe=utf8&num=2");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//this.subWebPages = new ArrayList<>();
	}

	private String fetchContent() throws Exception {
		String retVal = "";
		for(String url : urls) {
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
		//規範要抓的子網頁種類範圍
		String range = "*:not(:contains(privacy)):not(img):not(:contains(login)):not(:contains(register)), a[href]";
		Elements aLinks = doc.select(range);
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
		System.out.println("GoogleQuery 130 subURLs");
		for(WebNode s : sub) {
			WebPage p = s.webPage;
			System.out.println("url = " + p.url + "   name = " + p.name);
		}

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
				retVal.put(title, citeUrl);
				
			} catch (IndexOutOfBoundsException e) {
				// e.printStackTrace();
			}
		}

		return retVal;
	}
	
	//幫每個爬到的結果加上Score
	public void score() throws Exception {
		HashMap<String, String> webMap = this.query();
		System.out.println("webMap size = " + webMap.size());
		//System.out.println("GoogleQuery 183 countScore");
		
		for (Map.Entry<String, String> webMapEntry : webMap.entrySet()) {			
			String url = webMapEntry.getValue();
			String title = webMapEntry.getKey();			
			WebPage rootPage = new WebPage(url, title);
			WebNode rootNode = new WebNode(rootPage);				
			System.out.println("GoogleQuery186  url = " + url + "   title = " + title);
			ArrayList<WebNode> subPages = fetchSublink(url);
			for(WebNode subPage : subPages) {
				rootNode.addChild(subPage);
			}
			WebTree tree = new WebTree(rootPage);
			//set完score之後把這個網頁用quickSort.keyword加到quickSort.keywordList裡面
			tree.setPostOrderScore();			
		}
		/*System.out.println("Query196  rank rs = ");
		sortResult();*/
	}
	
	//把這裡改成return Entry<String, String> entry  分數最大的要最先進去
	//rsNum>6時不再繼續處理結果
	public HashMap<String, String> sortResult(){
		//eularPrintTree(root);
		int rsNum = 0;
		ArrayList<Keyword> results = quickSort.KeywordList.sort(quickSort.KeywordList.getLst());
		HashMap<String, String> rsMap = new HashMap<String, String>();
		for(quickSort.Keyword result : results) {
			rsNum++;
			rsMap.put(result.link, result.title);
			System.out.println("num = " + rsNum + "   title = " + result.title + "   score = " + result.score);
			if(rsNum >6) {
				break;
			}
			
		}
		//rootList.output();
		return rsMap;
	}
	
}
