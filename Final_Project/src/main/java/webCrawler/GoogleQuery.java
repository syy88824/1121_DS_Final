package webCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

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

        URL u = new URL(url);
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
}

