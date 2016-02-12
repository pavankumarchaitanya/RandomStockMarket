package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	@Value("${debugMode}")
	private  boolean debugFlag;
	
    private static final List<Stock> stockList = new ArrayList<Stock>();
   private static   List<Stock> originalStockList = new ArrayList<Stock>();  
   private static long numberOfChanges = 0; 
    
    static{
    	int numberOfStocks = 1000;
    	for(int i=0; i<numberOfStocks;i++ )
    	{
    		Stock S = new Stock();
    		S.setName(i+"_Stock");
    		S.setValue(Math.random()*10000);
    		stockList.add(S);
    		originalStockList.add(new Stock(S));
    	}
    	
    //	originalStockList = new ArrayList<Stock> (stockList.size()+1);
    //	Collections.copy(originalStockList, stockList); 
    	printStockList();
    	
    }
    
    public static List<Stock> getOriginalStockList() {
		return originalStockList;
	}

	public static void setOriginalStockList(List<Stock> originalStockList) {
		ScheduledTasks.originalStockList = originalStockList;
	}

	public static List<Stock> getStocklist() {
		return stockList;
	}

	@Scheduled(fixedRate = 50)
    public void changeStockPrice() {
    	
    	//System.out.println("Change Stock Price...");
    	
    	numberOfChanges ++;
    	for(Stock S : stockList)
    	{
    		double tempValue= 0;
    		Long multiplier = 1L;
    		if(Math.random() < 0.5){  // TODO : Move this to application.properties. Tweaking this value will make the market go up or down. A value less than 0.5 will make it go up, and greater than 0.5 will make the market go down! 
    		multiplier *= -1;
    		}
    		
    		tempValue = S.getValue()* (1 + ((Math.random()*10)*multiplier)/100);
    		S.setValue(tempValue);
    		
    	}
    	
   // 	printStockList();
    	
    	
    }
    
    @Scheduled(fixedRate = 3000)
    public void printTotalPriceChangeDifference()
    {
    	
    	int sum = 0 ;
    	for(Stock s : stockList)
    	{
    		
    		int originalIndex = originalStockList.indexOf(s);
    		Stock original = originalStockList.get(originalIndex);
    		sum = (int) (sum + (s.getValue() -original.getValue()));
    		
    		
    	}
    	if(debugFlag)
    	{
    		compareAndPrintStockList(originalStockList,stockList);
    	
    	
    	
    	System.out.println("Net Price Change from Original :"  + sum);
	
    	
    	System.out.println("Number of changes : " + numberOfChanges);
    	}
    	resetNumberOfChanges();
    	//System.out.println("This is garbage..."+Thread.currentThread().getId());
    	
    }
    
    private void resetNumberOfChanges() {
		// TODO Auto-generated method stub
		numberOfChanges=0;
	}

	private static void printStockList()
    {
    	for(Stock s : stockList)
    	{
    		
    		System.out.println(s);
    	}
    }
    
    private static void compareAndPrintStockList(List<Stock> originalStockList, List<Stock> changedStockList)
    {
    	for(Stock s : changedStockList)
    	{
    		
    		int originalIndex = originalStockList.indexOf(s);
    		Stock original = originalStockList.get(originalIndex);
    		
    		System.out.println(s + " Original Price : " + original.getValue() + " (" + (s.getValue() -original.getValue()) + ") ");
    		
    	}
    }
    
    
    
    
}
