package idv.ycc.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BankService {

    //取得 URL API
    public String getExchangeRateAPI() {
        try {
            int urlIndex, beginIndex, endIndex;
            String urlBank, urlApi;
            //urlBank = "https://www.esunbank.com.tw/bank/personal/deposit/rate/forex/exchange-rate-chart?Currency=USD/TWD";
            urlBank = "https://www.esunbank.com.tw/bank/personal/deposit/rate/forex/exchange-rate-chart?Currency=CNY/TWD";
            //urlBank = "https://www.esunbank.com.tw/bank/personal/deposit/rate/forex/exchange-rate-chart?Currency=HKD/TWD";
            //解析 URL
            Document document = Jsoup.connect(urlBank).get();
            //查詢 script 標籤元素
            Elements elements = document.getElementsByTag("script");
            
            //擷取urlApi
            for (Element element : elements) {
                for (DataNode dataNode : element.dataNodes()) {
                    
                    urlIndex = dataNode.getWholeData().indexOf("url:");
                    if (urlIndex > -1) {
                        beginIndex = dataNode.getWholeData().indexOf("'", urlIndex) + 1;
                        endIndex = dataNode.getWholeData().indexOf("',", urlIndex);
                        urlApi = dataNode.getWholeData().substring(beginIndex, endIndex);
                        urlApi = "https://www.esunbank.com.tw" + urlApi;
                        
                        System.out.println(beginIndex);
                        System.out.println(endIndex);
                        System.out.println("Exchange Rate API: " + urlApi);
                        
                        return urlApi;
                    }
                    
                }
                //System.out.println("-------------------"); 
            }
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        return null;
    }

    //創建 POST parent object 
    public String createParentObject(String unit) {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //日期格式化為("yyyy-MM-dd")
        final String currency = unit;
        final String currencytype = "0";    //0:現金匯率、1:即期匯率
        final String rangetype = "0";   //0:當天
        final String startdate = simpleDateFormat.format(date); //開始日期
        final String enddate = simpleDateFormat.format(date);   //結束日期
        final String currencyTitle = "美元(USD)";
        String postParentObject = null;
        
        Data data = new Data();
        data.setCurrency(currency);
        data.setCurrencytype(currencytype);
        data.setRangetype(rangetype);
        data.setStartdate(startdate);
        data.setEnddate(enddate);
        data.setCurrencyTitle(currencyTitle);
        
        PostParentObjectService postParentObjectService = new PostParentObjectService();
        postParentObjectService.setData(data);
        
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            postParentObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(postParentObjectService);
            System.out.println("POST to API's Parent: " + postParentObject);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return postParentObject;
    }

    /*
    //取得最新 ExchangeRateAPI 的 Rate Object Value
    public String[] getLatestRate(String exchangeRateApiResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.setSerializationInclusion(Include.NON_NULL); //不顯示Value為Null之Key
        //objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        //objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true); //不受Jackson強制轉換大小寫
        
        try {
            //JSON to Java
            ExchangeRateApiResponseObjectService exchangeRateApiResponseObjectService = 
                    objectMapper.readValue(exchangeRateApiResponse, ExchangeRateApiResponseObjectService.class);
            
            final int rateSize = exchangeRateApiResponseObjectService.getD().getRates().size();
            System.out.println("Rates Object Size: " + rateSize);
            
            String[] ratesObjectValues = new String[3];

            return ratesObjectValues;
            
                   
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/
    
    
    //取得 ExchangeRateAPI 的 Rate Object Value
    public String[][] getRatesObjectValue(String exchangeRateApiResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.setSerializationInclusion(Include.NON_NULL); //不顯示Value為Null之Key
        //objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        //objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true); //不受Jackson強制轉換大小寫
        
        try {
            //JSON to Java
            ExchangeRateApiResponseObjectService exchangeRateApiResponseObjectService = 
                    objectMapper.readValue(exchangeRateApiResponse, ExchangeRateApiResponseObjectService.class);
            
            final int rateSize = exchangeRateApiResponseObjectService.getD().getRates().size();
            System.out.println("Rates Object Size: " + rateSize);
            
            String[][] ratesObjectValues = new String[rateSize][3];

            for (int i = 0; i < rateSize; i++) {
                ratesObjectValues[i][0] = exchangeRateApiResponseObjectService.getD().getRates().get(rateSize - 1).getTime();
                ratesObjectValues[i][1] = exchangeRateApiResponseObjectService.getD().getRates().get(rateSize - 1).getBuyRate();
                ratesObjectValues[i][2] = exchangeRateApiResponseObjectService.getD().getRates().get(rateSize - 1).getSellRate();
            }
            return ratesObjectValues;
            
            /*
            String[] times = new String[rateSize];
            String[] buyRates = new String[rateSize];
            String[] sellRates = new String[rateSize];
            for (int i = 0; i < rateSize; i++) {
                times[i] = exchangeRateApiResponseObjectService.getD().getRates().get(i).getTime();
                buyRates[i] = exchangeRateApiResponseObjectService.getD().getRates().get(i).getBuyRate();
                sellRates[i] = exchangeRateApiResponseObjectService.getD().getRates().get(i).getSellRate();
                
            }*/
            //return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(exchangeRateApiResponseObjectService);
                    
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //取得 Rates Object's Time
    public String[] getRatesObjectTime(String exchangeRateApiResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        try {
            //JSON to Java
            ExchangeRateApiResponseObjectService exchangeRateApiResponseObjectService = 
                    objectMapper.readValue(exchangeRateApiResponse, ExchangeRateApiResponseObjectService.class);
            final int rateSize = exchangeRateApiResponseObjectService.getD().getRates().size();
            String[] ratesObjectTimes = new String[rateSize];

            //取得 Rates Object 的所有 time
            for (int i = 0; i < rateSize; i++)
                ratesObjectTimes[i] = exchangeRateApiResponseObjectService.getD().getRates().get(i).getTime();
            
            return ratesObjectTimes;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //取得 Rates Object's BuyRate
    public String[] getRatesObjectBuyRate(String exchangeRateApiResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        try {
            //JSON to Java
            ExchangeRateApiResponseObjectService exchangeRateApiResponseObjectService = 
                    objectMapper.readValue(exchangeRateApiResponse, ExchangeRateApiResponseObjectService.class);
            final int rateSize = exchangeRateApiResponseObjectService.getD().getRates().size();
            String[] rateObjectBuyRates = new String[rateSize];
            
            //取得 Rates Object 的所有 BuyRate
            for (int i = 0; i < rateSize; i++)
                rateObjectBuyRates[i] = exchangeRateApiResponseObjectService.getD().getRates().get(i).getBuyRate();
            
            return rateObjectBuyRates;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //取得 Rates Object's BuyRate
    public String[] getRatesObjectSellRate(String exchangeRateApiResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        try {
            //JSON to Java
            ExchangeRateApiResponseObjectService exchangeRateApiResponseObjectService = 
                    objectMapper.readValue(exchangeRateApiResponse, ExchangeRateApiResponseObjectService.class);
            final int rateSize = exchangeRateApiResponseObjectService.getD().getRates().size();
            String[] rateObjectSellRates = new String[rateSize];
            
            //取得 Rates Object 的所有 BuyRate
            for (int i = 0; i < rateSize; i++)
                rateObjectSellRates[i] = exchangeRateApiResponseObjectService.getD().getRates().get(i).getSellRate();
            
            return rateObjectSellRates;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    //設定 Currency Title
    public String setCurrencyTitle(String unit) {
        String currencyTitle = null;
        switch (unit) {
        case "CNY": currencyTitle = "人民幣"; break;
        case "USD": currencyTitle = "美元"; break;
        case "AUD": currencyTitle = "澳幣"; break;
        case "EUR": currencyTitle = "歐元"; break;
        case "HKD": currencyTitle = "港幣"; break;
        } 
        
        return currencyTitle;
    }
}
