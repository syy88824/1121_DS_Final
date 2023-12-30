package webCrawler;

import java.io.IOException;

public class Main 
{
	public static void main(String[] args) 
	{
		try 
		{
			/*
			 * Using different keyword depends on the last number of your student ID
			 * 0,1:Tomato
			 * 2,3:Liver
			 * 4,5:Pokemon
			 * 6,7:Tissue
			 * 8,9:Process
<<<<<<< Updated upstream
			 */
			System.out.println(new GoogleQuery("韓劇").query());
=======
<<<<<<< Updated upstream
			 
			System.out.println(new GoogleQuery("Tomato").query());
=======
			 *
			
			//System.out.println(new GoogleQuery(search).query());
>>>>>>> Stashed changes
>>>>>>> Stashed changes
//			GoogleQuery g = new GoogleQuery("NCCU");
//			g.query();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
