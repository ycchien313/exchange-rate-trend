package idv.ycc.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class PostParentObjectService {
    
    private Data data;
    
    public Data getData() {return data;}
    public void setData(Data data) {this.data = data;}
}

@JsonPropertyOrder({"Currency", "Currencytype", "Rangetype", "Startdate", "Enddate", "CurrencyTitle"})
class Data{
    private String Currency;
    private String Currencytype;
    private String Rangetype;
    private String Startdate;
    private String Enddate;
    private String CurrencyTitle;
    
    @JsonProperty("Currency")
    public String getCurrency() {return Currency;}
    public void setCurrency(String Currency) {this.Currency = Currency;}
    
    @JsonProperty("Currencytype")
    public String getCurrencytype() {return Currencytype;}
    public void setCurrencytype(String Currencytype) {this.Currencytype = Currencytype;}
    
    @JsonProperty("Rangetype")
    public String getRangetype() {return Rangetype;}
    public void setRangetype(String Rangetype) {this.Rangetype = Rangetype;}
    
    @JsonProperty("Startdate")
    public String getStartdate() {return Startdate;}
    public void setStartdate(String Startdate) {this.Startdate = Startdate;}
    
    @JsonProperty("Enddate")
    public String getEnddate() {return Enddate;}
    public void setEnddate(String Enddate) {this.Enddate = Enddate;}

    @JsonProperty("CurrencyTitle")
    public String getCurrencyTitle() {return CurrencyTitle;}
    public void setCurrencyTitle(String CurrencyTitle) {this.CurrencyTitle = CurrencyTitle;}
    
}