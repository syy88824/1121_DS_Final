package webCrawler;

import java.io.IOException;
import java.util.Scanner;

public class Main 
{
<<<<<<< Updated upstream
	//static String search = "";
	
	public static void setSearch(String search) {
		//Main.search = search;
		try {
			System.out.println(new GoogleQuery(search).query());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		Main.setSearch(sc.next());
		/*try 
		{
=======
	public static void main(String[] args) throws IOException 
	{
>>>>>>> Stashed changes
			/*
			 * Using different keyword depends on the last number of your student ID
			 * 0,1:Tomato
			 * 2,3:Liver
			 * 4,5:Pokemon
			 * 6,7:Tissue
			 * 8,9:Process
<<<<<<< Updated upstream
			 */
<<<<<<< Updated upstream
			System.out.println(new GoogleQuery("Tomato").query());
=======
			 *
=======
			//System.out.println(new GoogleQuery("台北").query());
			 
			//System.out.println(new GoogleQuery("Tomato").query());
>>>>>>> Stashed changes
			
			//System.out.println(new GoogleQuery(search).query());
>>>>>>> Stashed changes
//			GoogleQuery g = new GoogleQuery("NCCU");
//			g.query();
<<<<<<< Updated upstream
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}*/
=======
>>>>>>> Stashed changes
	}
}
