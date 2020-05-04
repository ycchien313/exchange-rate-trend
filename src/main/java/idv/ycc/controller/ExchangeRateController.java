package idv.ycc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import idv.ycc.service.BankService;
import idv.ycc.service.RestfulService;

@RestController
public class ExchangeRateController {
    String exchangeRateApiResponse = null;  //JSON 資料，From: Exchange Rate API
    
    /*
     * 方法： 
     * showExchangeRate() : 將解析之JSON顯示於 exchangerate.jsp 
     * getTimes()：取得Rates Times 回傳給Ajax (exchangerate.jsp)
     * getBuyRates()：取得Rates BuyRates 回傳給Ajax (exchangerate.jsp)
     * getSellRates()：取得Rates SellRates 回傳給Ajax(exchangerate.jsp)
     * 
     * 測試用資料： 
     * postParentObject ="{data:{\"Currency\":\"USD\",\"Currencytype\":\"1\",\"Rangetype\":\"0\",\"Startdate\":\"2020-04-30\",\"Enddate\":\"2020-04-30\",\"CurrencyTitle\":\"美元(USD)\"}}";
     * exchangeRateApiResponse = "{\"d\":{\"Rates\":[{\"Time\":\"2020-04-30 09:01:40\",\"BuyRate\":29.79,\"SellRate\":29.89},{\"Time\":\"2020-04-30 09:02:00\",\"BuyRate\":29.79,\"SellRate\":29.89}]}}";
     * exchangeRateApiResponse = "{\"d\":{\"Line\":{\"chart\":{\"className\":null,\"backgroundColor\":null,\"plotBackgroundColor\":null,\"plotBackgroundImage\":null,\"plotBorderWidth\":0,\"plotBorderColor\":null,\"spacingBottom\":15,\"spacingTop\":10,\"spacingRight\":10,\"spacingLeft\":10,\"reflow\":true,\"type\":\"spline\"},\"credits\":{\"enabled\":false,\"href\":null,\"text\":null},\"legend\":{\"align\":\"center\",\"verticalAlign\":\"bottom\",\"x\":0,\"y\":0,\"layout\":\"horizontal\",\"rtl\":false},\"navigation\":{\"menuItemStyle\":{\"fontSize\":\"10px\"}},\"plotOptions\":{\"line\":null,\"spline\":{\"states\":{\"hover\":{\"lineWidth\":3}},\"marker\":{\"enabled\":false,\"format\":null,\"color\":\"black\"},\"pointInterval\":60000,\"pointStart\":1588237300000,\"lineWidth\":2}},\"series\":[{\"data\":[[1588237300000,29.89],[1588237320000,29.89]],\"type\":\"spline\",\"name\":\"賣匯\",\"visible\":true,\"yAxis\":0},{\"data\":[[1588237300000,29.79],[1588237320000,29.79]],\"type\":\"spline\",\"name\":\"買匯\",\"visible\":true,\"yAxis\":0}],\"subtitle\":{\"text\":null,\"align\":\"center\",\"floating\":false,\"x\":0},\"title\":{\"text\":\"\",\"align\":\"center\",\"x\":0,\"y\":15,\"floating\":false,\"margin\":15},\"tooltip\":{\"xDateFormat\":\" %H : %M : %S\",\"valueSuffix\":\" NTD\",\"valuePrefix\":null},\"xAxis\":{\"type\":\"datetime\",\"labels\":{\"overflow\":\"justify\"},\"dateTimeLabelFormats\":{\"day\":\"%b. %e\",\"week\":\"%b. %e\",\"month\":\"%Y. %b\"}},\"yAxis\":{\"title\":{\"text\":\"美元即期\"},\"min\":29.655165,\"minorGridLineWidth\":0,\"plotBands\":[{\"from\":29.655165,\"to\":29.685165,\"color\":\"rgba(68, 170, 213, 0.1)\",\"label\":{\"style\":{\"color\":\"#606060\"}}},{\"from\":29.685165,\"to\":29.715165000000002,\"color\":\"rgba(0, 0, 0, 0)\",\"label\":{\"style\":{\"color\":\"#606060\"}}}],\"gridLineWidth\":0}},\"Rates\":[{\"Time\":\"2020-04-30 09:01:40\",\"BuyRate\":29.79,\"SellRate\":29.89},{\"Time\":\"2020-04-30 09:02:00\",\"BuyRate\":29.79,\"SellRate\":29.89}],\"UpdateTime\":\"2020年04月30日 09:01:40\",\"Currencytype\":\"Spot\"}}";
     */
    
    
    //將解析之JSON顯示於 exchangerate.jsp
    @RequestMapping(value = "/exchangerate")
    public ModelAndView showExchangeRate(@RequestParam(value = "unit") String unit) {
        
        final BankService bankService = new BankService();
        final String exchangeRateAPI = bankService.getExchangeRateAPI();
        String currencyTitle = null;
        String postParentObject = null;
        String[] rates = null;
        
        if (exchangeRateAPI != null) {
            //創建要 POST 給 API 的參數並回傳JSON
            postParentObject = bankService.createParentObject(unit);
            
            RestfulService restfulService = new RestfulService();
            //將參數 POST 至匯率API，並取得API的Response(JSON資料)
            exchangeRateApiResponse = restfulService.doPost(exchangeRateAPI, postParentObject); 
            System.out.println("Exchange Rate Api Response: " + exchangeRateApiResponse);
            
            //取得API response的 Rates Object Value (Time, buyRate, sellRate)
            rates = bankService.getLatestRate(exchangeRateApiResponse);
            
            //設定 Currency Title
            currencyTitle = bankService.setCurrencyTitle(unit);
            
            ModelAndView mv = new ModelAndView("exchangerate");
            mv.addObject("showCurrencyTitle", currencyTitle + "最新現金匯率");
            mv.addObject("showBuyRate", "買入：" + rates[1]);
            mv.addObject("showSellRate", "賣出：" + rates[2]);
            mv.addObject("showLineChartTitle", currencyTitle + "匯率");
            return mv;
        }
        else {
            ModelAndView mv = new ModelAndView("exchangerate");
            mv.addObject("showCurrencyTitle", "讀取失敗");
            return mv;
        }
    }
    
    
    //取得Rates Times 回傳給Ajax (exchangerate.jsp) 
    @RequestMapping(value="/getTimes")
    public String[] getTimes() {
        //String[] time = {"aaa", "bbb"};
        String[] times = null;
        
        if (exchangeRateApiResponse != null) {
            BankService bankService = new BankService();
            times = bankService.getRatesObjectTime(exchangeRateApiResponse);
        }
        return times;
    }
    
    
    //取得Rates BuyRates 回傳給Ajax (exchangerate.jsp)
    @RequestMapping(value = "/getBuyRates")
    public String[] getBuyRates() {
        String[] buyRates = null;
        if (exchangeRateApiResponse != null) {
            BankService bankService = new BankService();
            buyRates = bankService.getRatesObjectBuyRate(exchangeRateApiResponse);
        }
        
        return buyRates;
    }

    
    //取得Rates SellRates 回傳給Ajax(exchangerate.jsp) 
    @RequestMapping(value = "/getSellRates")
    public String[] getSellRates() {
        String[] sellRates = null;
        if (exchangeRateApiResponse != null) {
            BankService bankService = new BankService();
            sellRates = bankService.getRatesObjectSellRate(exchangeRateApiResponse);
        }
        
        return sellRates;
    }
}