package Google;
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
			 */
			System.out.println(new GoogleQuery("旅遊").query());
//			GoogleQuery g = new GoogleQuery("NCCU");
//			g.query();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
