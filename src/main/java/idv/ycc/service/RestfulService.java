package idv.ycc.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestfulService {
    
    public String doPost(String urlApi, String postParentObject) {
        HttpURLConnection http = null;
        
        try {
            final URL url = new URL(urlApi);
            byte[] postParent = postParentObject.getBytes("utf-8");
            
            //建立連線
            http = (HttpURLConnection) url.openConnection();
            //設定連線方式
            http.setRequestMethod("POST");
            //設定連接超時
            http.setConnectTimeout(10000);
            //設定讀取超時
            http.setReadTimeout(10000);
            //允許輸入流，即允許下載
            http.setDoInput(true);
            //允許輸出流，即允許上傳
            http.setDoOutput(true);
            //設定是否使用快取
            http.setUseCaches(false);
            //允許使用者交互（例如彈出一個驗證對話框）的上下文中對此URL進行檢查
            http.setAllowUserInteraction(false);
            //設置通用請求屬性
            //http.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
            //http.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            //http.setRequestProperty("Content-Length", Integer.toString(input.length));
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //http.setRequestProperty("Cookie", "widgetCheck=true; _gid=GA1.3.1824919925.1588142070; _fbp=fb.2.1588142070078.500458417; ESB-new-W_Cookie=2902593708.47873.0000; ESB-new_Cookie=1226117804.47873.0000; SC_ANALYTICS_GLOBAL_COOKIE=196d92f9ec4d4f9a822a0757e2d3a897|True; _gat_UA-1422809-1=1; ASP.NET_SessionId=2ftq4cp45ehmdsvshxbl3mqu; TS0147d1ae=01033cabac9cdebddf6cf5dacd3b4aa35a3dc4f9f778a43914f119aff8594da955f278e56b5d2d113318f82f3c36c7966c306e9b98c93b89396019843d8418b9f39c3e1a07ac757c72ebc9b255ffce6e990d11acfd944d41a6a49b80ecae53f2d0a8f5fcf140651168f1e03400a55475796ef63482; bookmarkHistory=%5B%7B%22ID%22%3A%22%7B4B2D8F7D-1C0B-4A2E-88DD-DD6C9936EEBB%7D%22%2C%22QueryStringUrl%22%3A%22%22%2C%22DateTime%22%3A1588144103797%2C%22OpBookmark%22%3A0%7D%2C%7B%22ID%22%3A%22%7B7EF02120-79A0-4903-9F34-2C7E5D5E8055%7D%22%2C%22QueryStringUrl%22%3A%22%22%2C%22DateTime%22%3A1588144136212%2C%22OpBookmark%22%3A0%7D%2C%7B%22ID%22%3A%22%7BE13C161C-73C6-41FC-B47A-E49EC756DC36%7D%22%2C%22QueryStringUrl%22%3A%22%22%2C%22DateTime%22%3A1588190285394%2C%22OpBookmark%22%3A0%7D%2C%7B%22ID%22%3A%22%7BBE27117C-4236-47D6-9788-22D1CCF18298%7D%22%2C%22QueryStringUrl%22%3A%22%22%2C%22DateTime%22%3A1588226817387%2C%22OpBookmark%22%3A0%7D%2C%7B%22ID%22%3A%22%7B4757D072-2741-45FF-AD12-AAE5062D2CD2%7D%22%2C%22QueryStringUrl%22%3A%22%22%2C%22DateTime%22%3A1588226830249%2C%22OpBookmark%22%3A0%7D%5D; _ga_56KQZGV7P0=GS1.1.1588226729.7.1.1588226830.35; _ga=GA1.3.1267314106.1588142070; _uetsid=_uetcbf9ef56-8154-7d9b-a413-8954480a07d9");
            //http.setRequestProperty("Host", "www.esunbank.com.tw");
            //http.setRequestProperty("Origin", "https://www.esunbank.com.tw");
            http.setRequestProperty("Referer", "https://www.esunbank.com.tw/bank/personal/deposit/rate/forex/exchange-rate-chart?Currency=USD/TWD");
            //http.setRequestProperty("User-Agent", "Mozilla/5.0");
            //http.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            //連線
            http.connect();
            
            //創建httpURLConnection文字輸出對象
            OutputStream os = http.getOutputStream();
            //向postParent輸出
            os.write(postParent, 0, postParent.length);
            os.flush();
            os.close();
            
            InputStreamReader is = new InputStreamReader(http.getInputStream(), "utf-8");
            BufferedReader buf = new BufferedReader(is);
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = buf.readLine()) != null) {
                responseLine = jsonFormat(responseLine);    //得到的有斜線，要進行格式化
                response.append(responseLine.trim());   //將responseLine 加至response
            }
            is.close();
            buf.close();
            
            return response.toString();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            if (http != null) http.disconnect();
        }
        
        return null;
    }
    
    //API Response JSON 格式化
    private String jsonFormat(String exchangeRateApiResponse) {
        exchangeRateApiResponse = exchangeRateApiResponse.replace("\\\"", "\"");    //取代\"為"
        exchangeRateApiResponse = exchangeRateApiResponse.replace("\"{\"", "{\"");  //取代"{"為{"
        exchangeRateApiResponse = exchangeRateApiResponse.replace("\"}\"", "\"}");  //取代"}"為"}
        return exchangeRateApiResponse;
    }
}
