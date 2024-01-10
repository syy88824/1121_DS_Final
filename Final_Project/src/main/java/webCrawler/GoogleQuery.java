package webCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
<<<<<<< Updated upstream
import java.util.HashMap;
=======
import java.util.LinkedHashMap;
import java.util.Map;
>>>>>>> Stashed changes

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import webScore.WebNode;
import webScore.WebPage;

public class GoogleQuery {
    public String searchKeyword;
    public String url;
    public String content;
    public ArrayList<WebPage> subWebPages; // Add a list to store sub-webpages

<<<<<<< Updated upstream
    public GoogleQuery(String searchKeyword) {
        this.searchKeyword = searchKeyword;
        try {
            String encodeKeyword = java.net.URLEncoder.encode(searchKeyword, "utf-8");
            this.url = "https://www.google.com/search?q=" + encodeKeyword + "&oe=utf8&num=20";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.subWebPages = new ArrayList<>();
    }

    private String fetchContent() throws IOException {
        String retVal = "";
=======
	//用urls篩選可能會爬出的搜索結果
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
	}
	
	//將urls搜索結果頁面的html取出來
	private String fetchContent() throws Exception {
		String retVal = "";
		for(String url : urls) {
			URL u = new URL(url);
			SslUtils.ignoreSsl();
			URLConnection conn = u.openConnection();
			conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
			InputStream in = conn.getInputStream();
>>>>>>> Stashed changes

        URL u = new URL(url);
        URLConnection conn = u.openConnection();
        conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
        InputStream in = conn.getInputStream();

        InputStreamReader inReader = new InputStreamReader(in, "utf-8");
        BufferedReader bufReader = new BufferedReader(inReader);
        String line = null;

<<<<<<< Updated upstream
        while ((line = bufReader.readLine()) != null) {
            retVal += line;
        }
        return retVal;
    }

    // New method to fetch sub-webpage titles and URLs
    private void fetchSubWebPages() throws IOException {
        Document doc = Jsoup.parse(content);
        Elements links = doc.select("a[href]");

        for (Element link : links) {
            String subPageUrl = link.absUrl("href");

            if (!subPageUrl.isEmpty()) {
                String subPageTitle = link.text();
                WebPage subWebPage = new WebPage(subPageUrl, subPageTitle);
                subWebPages.add(subWebPage);
            }
        }
    }

    public HashMap<String, String> query() throws IOException {
        if (content == null) {
            content = fetchContent();
            fetchSubWebPages();
        }

        HashMap<String, String> retVal = new HashMap<>();

        Document doc = Jsoup.parse(content);
        Elements lis = doc.select("div");
        lis = lis.select(".kCrYT");

        for (Element li : lis) {
            try {
                String citeUrl = li.select("a").get(0).attr("href").replace("/url?q=", "");
                String title = li.select("a").get(0).select(".vvjwJb").text();

                if (title.equals("")) {
                    continue;
                }

                System.out.println("Title: " + title + " , url: " + citeUrl);
                retVal.put(title, citeUrl);

            } catch (IndexOutOfBoundsException e) {
                // e.printStackTrace();
            }
        }

        for (WebPage subWebPage : subWebPages) {
            System.out.println("Sub-Page Title: " + subWebPage.name + " , Sub-Page URL: " + subWebPage.url);
        }

        return retVal;
    }
=======
	//將每個網頁的html取出來
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
			
		//catch此error時(通常是因為網頁受安全阻擋而無法爬出html) 回傳此網頁的html為空字串 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;
	}

	//將每個網頁的子網頁url及title抓出來
	private ArrayList<WebNode> fetchSublink(String link) throws IOException {
		ArrayList<WebNode> sub = new ArrayList<WebNode>();
		int counter = 0;
		char slash;
		int where = 0;
		String concatlink = "1";
		String content = this.fetchContent(link);
		
		//如果抓不到該網頁(被安全阻擋) 以致html為空字串 則直接跳過該網頁 不爬子網頁
		if(!content.equals("")) {
			//每個網頁最多抓取三個子網頁
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
			//只存取到前三個子網頁的html
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
				if (!suburl.contains("http://") && !suburl.contains("https://")) {
					if (concatlink != "1") {
						suburl = concatlink + suburl;
					} else {
						suburl = link + suburl;
					}
				}
				//在此處將子網頁轉成webnode object，並存入ArrayList<WebNode> sub
				if (sub.size() <= 2) {
					sub.add(new WebNode(new WebPage(suburl, subtitle)));
				} else {
					break;
				}
			}
			for(WebNode s : sub) {
				WebPage p = s.webPage;
				System.out.println("url = " + p.url + "   name = " + p.name);
			}
		}
		return sub;
	}

	//將關鍵字爬到的結果整理並存入LinkedHashMap
	public LinkedHashMap<String, String> query() throws Exception {
		if (content == null) {
			content = fetchContent();			
		}		
		LinkedHashMap<String, String> retVal = new LinkedHashMap<>();
		Document doc = Jsoup.parse(content);
		//限制爬到的網頁(不要pdf檔 不要douban或dramasq的網頁)
		String range = "*:not([href*=.pdf]):not(:containsOwn(dramasq.cc)):not(:containsOwn(douban.com)), div";
		Elements lis = doc.select(range);
		lis = lis.select(".kCrYT");

		//針對每一個搜尋結果的動作
				for (Element li : lis) {
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
					} catch (IndexOutOfBoundsException e) {}
				}
				return retVal;
	}
	
	//幫每個爬到的結果(rootPage)加上Score
	public void score() throws Exception {
		LinkedHashMap<String, String> webMap = this.query();
		System.out.println("webMap size = " + webMap.size());
		for (Map.Entry<String, String> webMapEntry : webMap.entrySet()) {			
			String url = webMapEntry.getValue();
			String title = webMapEntry.getKey();	
			//在這裡替每一個rootPage new webNode object，並將子網頁放到rootPage的ArrayList中
			WebPage rootPage = new WebPage(url, title);
			WebNode rootNode = new WebNode(rootPage);				
			System.out.println("GoogleQuery186  url = " + url + "   title = " + title);
			ArrayList<WebNode> subPages = fetchSublink(url);
			if(subPages.size() != 0) {
				for(WebNode subPage : subPages) {
					rootNode.addChild(subPage);
				}
				WebTree tree = new WebTree(rootPage);
				//set完score之後把這個網頁用quickSort.keyweb加到quickSort.keywebList裡面
				tree.setPostOrderScore();	
			}
		}
	}
	
	//把這裡改成return Entry<String, String> entry  分數最大的要最先進去
	//爬出7個結果(rsNum>6) 時不再繼續處理結果
	public LinkedHashMap<String, String> sortResult(){
		int rsNum = 0;
		ArrayList<Keyweb> results = KeywebList.sort(KeywebList.getLst());
		LinkedHashMap<String, String> rsMap = new LinkedHashMap<String, String>();
		for(Keyweb result : results) {
			rsNum++;
			rsMap.put(result.link, result.title);
			System.out.println("num = " + rsNum + "   title = " + result.title + "   score = " + result.score);
			if(rsNum >6) {
				break;
			}			
		}
		return rsMap;
	}
	
>>>>>>> Stashed changes
}

