package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quickSort.KeywebList;
import webCrawler.GoogleQuery;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("inputSearch")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		String keyword = request.getParameter("inputSearch");
		System.out.println("keyword = " + keyword);
		GoogleQuery google = new GoogleQuery(keyword);
		
		try {
			google.score();
			LinkedHashMap<String, String> query = google.sortResult();
			System.out.println("servlet 54");
			String[][] s = new String[query.size()][2];
			request.setAttribute("query", s);
			int num = 0;
			for(Entry<String, String> entry : query.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    s[num][0] = key;
			    s[num][1] = value;
			    num++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(google.query());
		
		System.out.println("servlet 65");
		request.getRequestDispatcher("index2.jsp")
		 .forward(request, response);
		KeywebList.resetLst();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
