package com.example.hedgenet_backend.Controllers;

import com.example.hedgenet_backend.Entity.StockEntity;
import com.example.hedgenet_backend.Models.StockData;
import com.example.hedgenet_backend.Service.StockService;
import com.example.hedgenet_backend.Service.YahooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Stocks")
@CrossOrigin(origins={"http://20.82.224.21:3000/","http://localhost:3000"})
public class StockController
{
    @Autowired
    StockService sf;

    @Autowired
    YahooService yf;

    @GetMapping("/createAssetTable")
    public Map<String, List<StockEntity>> getAllStocks()
    {
        List<StockEntity> stocks=sf.getStocks();
        return Collections.singletonMap("Stocks",stocks);
    }

    @GetMapping("/getGraphData")
    public Map<String, StockEntity> getData(@RequestParam String tickerid) throws IOException {
        StockEntity data=sf.getStock(tickerid);
        return Collections.singletonMap("Data",data);
    }

}
