package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockService {

	
	@RequestMapping("/stocks/{pathName}" )
	@ResponseBody
	Stock getStockDetail( @PathVariable String pathName ){
	
		Stock s = new Stock(); 
	ArrayList<Stock> list = (ArrayList<Stock>) ScheduledTasks.getStocklist();
	Stock stockUnit = new Stock();
	stockUnit.setName(pathName);
	
	
	if(list!=null){
		
		stockUnit =	list.get(list.indexOf(stockUnit));
	}
	
//	System.out.println(name);
		return stockUnit;
	
	
	}
	
	@RequestMapping("/stocks" )
	@ResponseBody
	List<Stock> getStockList(@RequestParam String name){
	
		Stock s = new Stock(); 
	ArrayList<Stock> list = (ArrayList<Stock>) ScheduledTasks.getStocklist();
	
	return list;
	

	
	
	}
	
	
}
