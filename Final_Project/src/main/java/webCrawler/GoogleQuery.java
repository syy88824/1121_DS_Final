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

import webScore.*;

public class GoogleQuery 
{
	public String searchKeyword;
	public String url;
	public String content;

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
