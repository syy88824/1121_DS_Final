package webScore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter {
	private String urlStr;
    private String content;
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() {
        try {
            URL url = new URL(this.urlStr);
            URLConnection conn = url.openConnection();
            InputStream in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            StringBuilder retVal = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                retVal.append(line).append("\n");
            }

            return retVal.toString();
        } catch (IOException e) {
            // Handle both "Server returned HTTP response code: 403" and "Unexpected end of file from server" here
            if (e.getMessage() != null && (e.getMessage().contains("Server returned HTTP response code: 403") ||
                    e.getMessage().contains("Unexpected end of file from server"))) {
                System.out.println("Skipped: " + e.getMessage() + " for URL: " + this.urlStr);
                return ""; // Return an empty string to indicate the content should be skipped
            } else {
                // Re-throw other IOExceptions
                throw new RuntimeException(e);
            }
        }
    }
    
    public int countKeyword(String keyword) throws IOException{
		if (content == null){
		    content = fetchContent();
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		int retVal = 0;
		int fromIdx = 0;
		int found = -1;
	
		while ((found = content.indexOf(keyword, fromIdx)) != -1){
		    retVal++;
		    fromIdx = found + keyword.length();
		}
	
		return retVal;
    }
}
