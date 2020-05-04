package idv.ycc.service;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"Line", "Rates", "UpdateTime", "Currencytype"})
public class ExchangeRateApiResponseObjectService {
    private D d;

    public D getD() {return d;}
    public void setD(D d) {this.d = d;}
}

class D {
    @JsonProperty("Line")
    private Line line;
    @JsonProperty("Rates")
    private List<Rate> rates;
    @JsonProperty("UpdateTime")
    private String updateTime;
    @JsonProperty("Currencytype")
    private String currencyType;

    public Line getLine() {return line;}
    public void setLine(Line line) {this.line = line;}
    
    public List<Rate> getRates() {return rates;}
    public void setRates(List<Rate> rates) {this.rates = rates;}
    
    public String getUpdateTime() {return updateTime;}
    public void setUpdateTime(String updateTime) {this.updateTime = updateTime;}
    
    public String getCurrencyType() {return currencyType;}
    public void setCurrencytype(String currencyType) {this.currencyType = currencyType;}
}


class Line {
    private Chart chart;
    private Credits credits;
    private Legend legend;
    private Navigation navigation;
    @JsonProperty("plotOptions")
    private PlotOptions plotOptions;
    private List<Object> series;
    private Subtitle subtitle;
    private Title title;
    private Tooltip tooltip;
    @JsonProperty("xAxis")
    private XAxis xAxis;
    @JsonProperty("yAxis")
    private YAxis yAxis;

    public Chart getChart() {return chart;}
    public void setChart(Chart chart) {this.chart = chart;}
    
    public Credits getCredits() {return credits;}
    public void setCredits(Credits credits) {this.credits = credits;}
    
    public Legend getLegend() {return legend;}
    public void setLegend(Legend legend) {this.legend = legend;}
    
    public Navigation getNavigation() {return navigation;}
    public void setNavigation(Navigation navigation) {this.navigation = navigation;}
    
    public PlotOptions getPlotOptions() {return plotOptions;}
    public void setPlotOptions(PlotOptions plotOptions) {this.plotOptions = plotOptions;}
    
    public List<Object> getSeries() {return series;}
    public void setSeries(List<Object> series) {this.series = series;}

    public Subtitle getSubtitle() {return subtitle;}
    public void setSubtitle(Subtitle subtitle) {this.subtitle = subtitle;}
    
    public Title getTitle() {return title;}
    public void setTitle(Title title) {this.title = title;}
    
    public Tooltip getTooltip() {return tooltip;}
    public void setTooltip(Tooltip tooltip) {this.tooltip = tooltip;}
    
    public XAxis getXAxis() {return xAxis;}
    public void setXAxis(XAxis xAxis) {this.xAxis = xAxis;}
    
    public YAxis getYAxis() {return yAxis;}
    public void setYAxis(YAxis yAxis) {this.yAxis = yAxis;}
}

class YAxis {
    private YAxis.Title title;
    private float min;
    @JsonProperty("minorGridLineWidth")
    private float minorGridLineWidth;
    @JsonProperty("plotBands")
    private List<PlotBand> plotBands;
    @JsonProperty("gridLineWidth")
    private float gridLineWidth;

    public YAxis.Title getTitle() {return title;}
    public void setTitle(YAxis.Title title) {this.title = title;}
    
    public float getMin() {return min;}
    public void setMin(float min) {this.min = min;}

    public float getMinorGridLineWidth() {return minorGridLineWidth;}
    public void setMinorGridLineWidth(float minorGridLineWidth) {this.minorGridLineWidth = minorGridLineWidth;}
    
    public List<PlotBand> getPlotBands() {return plotBands;}
    public void setPlotBands(List<PlotBand> plotBands) {this.plotBands = plotBands;}
    
    public float getGridLineWidth() {return gridLineWidth;}
    public void setGridLineWidth(float gridLineWidth) {this.gridLineWidth = gridLineWidth;}

    
    public class Title {
        private String text;

        public String getText() {return text;}
        public void setText(String text) {this.text = text;}
    }
}

class PlotBand {
    private double from;
    private double to;
    private String color;
    private Label label;
    
    public double getFrom() {return from;}
    public void setFrom(double from) {this.from = from;}
    
    public double getTo() {return to;}
    public void setTo(double to) {this.to = to;}
    
    public String getColor() {return color;}
    public void setColor(String color) {this.color = color;}
    
    public Label getLabel() {return label;}
    public void setLabel(Label label) {this.label = label;}
    
    public class Label {
        private Style style;
        
        public Style getStyle() {return style;}
        public void setStyle(Style style) {this.style = style;}
        
        public class Style {
            private String color;
            
            public String getColor() {return color;}
            public void setColor(String color) {this.color = color;}
        }
    }
}

class XAxis {
    private String type;
    private Labels labels;
    private DateTimeLabelFormats dateTimeLabelFormats;

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    
    public Labels getLabels() {return labels;}
    public void setLabels(Labels labels) {this.labels = labels;}

    public DateTimeLabelFormats getDateTimeLabelFormats() {return dateTimeLabelFormats;}
    public void setDateTimeLabelFormats(DateTimeLabelFormats dateTimeLabelFormats) {this.dateTimeLabelFormats = dateTimeLabelFormats;}
}

class DateTimeLabelFormats {
    private String day;
    private String week;
    private String month;

    public String getDay() {return day;}
    public void setDay(String day) {this.day = day;}

    public String getWeek() {return week;}
    public void setWeek(String week) {this.week = week;}
    
    public String getMonth() {return month;}
    public void setMonth(String month) {this.month = month;}
}

class Labels {
    private String overflow;

    public String getOverflow() {return overflow;}
    public void setOverflow(String overflow) {this.overflow = overflow;}
}

class Tooltip {
    private String xDateFormat;
    private String valueSuffix;
    private String valuePrefix = null;

    public String getXDateFormat() {return xDateFormat;}
    public void setXDateFormat(String xDateFormat) {this.xDateFormat = xDateFormat;}
    
    public String getValueSuffix() {return valueSuffix;}
    public void setValueSuffix(String valueSuffix) {this.valueSuffix = valueSuffix;}
    
    public String getValuePrefix() {return valuePrefix;}
    public void setValuePrefix(String valuePrefix) {this.valuePrefix = valuePrefix;}
}

class Title {
    private String text;
    private String align;
    private float x;
    private float y;
    private boolean floating;
    private float margin;

    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    
    public String getAlign() {return align;}
    public void setAlign(String align) {this.align = align;}
    
    public float getX() {return x;}
    public void setX(float x) {this.x = x;}
    
    public float getY() {return y;}
    public void setY(float y) {this.y = y;}
    
    public boolean getFloating() {return floating;}
    public void setFloating(boolean floating) {this.floating = floating;}
    
    public float getMargin() {return margin;}
    public void setMargin(float margin) {this.margin = margin;}
}

class Subtitle {
    private String text = null;
    private String align;
    private boolean floating;
    private float x;

    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    
    public String getAlign() {return align;}
    public void setAlign(String align) {this.align = align;}
    
    public boolean getFloating() {return floating;}
    public void setFloating(boolean floating) {this.floating = floating;}
    
    public float getX() {return x;}
    public void setX(float x) {this.x = x;}
}

class Series {
    private List<Float> data;
    private String type;
    private String name;
    private String visible;
    @JsonProperty("yAxis")
    private String yAxis;
    
    public List<Float> getData() {return data;}
    public void setData(List<Float> data) {this.data = data;}
    
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    
    public String getVisible() {return visible;}
    public void setVisible(String visible) {this.visible = visible;}
    
    public String getYAxis() {return yAxis;}
    public void setYAxis(String yAxis) {this.yAxis = yAxis;}
}

class PlotOptions {
    private String line = null;
    private Spline spline;

    public String getLine() {return line;}
    public void setLine(String line) {this.line = line;}    

    public Spline getSpline() {return spline;}
    public void setSpline(Spline spline) {this.spline = spline;}
}

class Spline {
    @JsonProperty("States")
    private States states;
    @JsonProperty("Marker")
    private Marker marker;
    private float pointInterval;
    private float pointStart;
    private float lineWidth;

    public States getStates() {return states;}
    public void setStates(States states) {this.states = states;}
    
    public Marker getMarker() {return marker;}
    public void setMarker(Marker marker) {this.marker = marker;}
    
    public float getPointInterval() {return pointInterval;}
    public void setPointInterval(float pointInterval) {this.pointInterval = pointInterval;}
    
    public float getPointStart() {return pointStart;}
    public void setPointStart(float pointStart) {this.pointStart = pointStart;}
    
    public float getLineWidth() {return lineWidth;}
    public void setLineWidth(float lineWidth) {this.lineWidth = lineWidth;}
}

class Marker {
    private boolean enabled;
    private String format = null;
    private String color;

    public boolean getEnabled() {return enabled;}
    public void setEnabled(boolean enabled) {this.enabled = enabled;}    

    public String getFormat() {return format;}
    public void setFormat(String format) {this.format = format;}   

    public String getColor() {return color;}
    public void setColor(String color) {this.color = color;}
}

class States {
    @JsonProperty("Hover")
    private Hover hover;
    
    public Hover getHover() {return hover;}
    public void setHover(Hover hover) {this.hover = hover;}
}

class Hover {
    private float lineWidth;
    
    public float getLineWidth() {return lineWidth;}
    public void setLineWidth(float lineWidth) {this.lineWidth = lineWidth;}
}

class Navigation {
    private MenuItemStyle menuItemStyle;

    public MenuItemStyle getMenuItemStyle() {return menuItemStyle;}
    public void setMenuItemStyle(MenuItemStyle menuItemStyle) {this.menuItemStyle= menuItemStyle;}
}

class MenuItemStyle {
    private String fontSize;

    public String getFontSize() {return fontSize;}
    public void setFontSize(String fontSize) {this.fontSize = fontSize;}
}

class Legend {
    private String align;
    private String verticalAlign;
    private float x;
    private float y;
    private String layout;
    private boolean rtl;

    public String getAlign() {return align;}
    public void setAlign(String align) {this.align = align;}    

    public String getVerticalAlign() {return verticalAlign;}
    public void setVerticalAlign(String verticalAlign) {this.verticalAlign = verticalAlign;}

    public float getX() {return x;}
    public void setX(float x) {this.x = x;}

    public float getY() {return y;}
    public void setY(float y) {this.y = y;}

    public String getLayout() {return layout;}
    public void setLayout(String layout) {this.layout = layout;}

    public boolean getRtl() {return rtl;}
    public void setRtl(boolean rtl) {this.rtl = rtl;}
}

class Credits {
    private boolean enabled;
    private String href = null;
    private String text = null;

    public boolean getEnabled() {return enabled;}
    public void setEnabled(boolean enabled) {this.enabled = enabled;}
    
    public String getHref() {return href;}
    public void setHref(String href) {this.href = href;}

    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
}

class Chart {
    private String className = null;
    private String backgroundColor = null;
    private String plotBackgroundColor = null;
    private String plotBackgroundImage = null;
    private float plotBorderWidth;
    private String plotBorderColor = null;
    private float spacingBottom;
    private float spacingTop;
    private float spacingRight;
    private float spacingLeft;
    private boolean reflow;
    private String type;

    public String getClassName() {return className;}
    public void setClassName(String className) {this.className = className;}

    public String getBackgroundColor() {return backgroundColor;}
    public void setBackgroundColor(String backgroundColor) {this.backgroundColor = backgroundColor;}

    public String getPlotBackgroundColor() {return plotBackgroundColor;}
    public void setPlotBackgroundColor(String plotBackgroundColor) {this.plotBackgroundColor = plotBackgroundColor;}

    public String getPlotBackgroundImage() {return plotBackgroundImage;}
    public void setPlotBackgroundImage(String plotBackgroundImage) {this.plotBackgroundImage = plotBackgroundImage;}

    public float getPlotBorderWidth() {return plotBorderWidth;}
    public void setPlotBorderWidth(float plotBorderWidth) {this.plotBorderWidth = plotBorderWidth;}

    public String getPlotBorderColor() {return plotBorderColor;}
    public void setPlotBorderColor(String plotBorderColor) {this.plotBorderColor = plotBorderColor;}

    public float getSpacingBottom() {return spacingBottom;}
    public void setSpacingBottom(float spacingBottom) {this.spacingBottom = spacingBottom;}

    public float getSpacingTop() {return spacingTop;}
    public void setSpacingTop(float spacingTop) {this.spacingTop = spacingTop;}

    public float getSpacingRight() {return spacingRight;}
    public void setSpacingRight(float spacingRight) {this.spacingRight = spacingRight;}

    public float getSpacingLeft() {return spacingLeft;}
    public void setSpacingLeft(float spacingLeft) {this.spacingLeft = spacingLeft;}

    public boolean getReflow() {return reflow;}
    public void setReflow(boolean reflow) {this.reflow = reflow;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
}

class Rate{
    @JsonProperty("Time")
    private String time;
    @JsonProperty("BuyRate")
    private String buyRate;
    @JsonProperty("SellRate")
    private String sellRate;
    
    public String getTime() {return time;}
    public void setTime(String time) {this.time = time;}
    
    public String getBuyRate() {return buyRate;}
    public void setBuyRate(String buyRate) {this.buyRate = buyRate;}
    
    public String getSellRate() {return sellRate;}
    public void setSellRate(String sellRate) {this.sellRate = sellRate;}
}