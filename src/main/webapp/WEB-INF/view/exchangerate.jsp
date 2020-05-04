<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta name="exchangerate" content="width=device-width, initial-scale=1, shrink-to-fit=no; charset=UTF-8">
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    
    <!--  chart.js scripts -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
    
    <!-- Ajax Scripts-->
    <!-- BootStrap 加載的jQquery slim版 不支援AJAX，需將此及BootStrap的link 修改為MIN版 -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    
    <title>外幣匯率</title>
  </head>
  
  <body>
    <!-- 顯示匯率 -->
    <div class ="col-12">
        <center>
            <div><h2><b>${showCurrencyTitle}</b></h2></div>
		    <div><h3><span>${showBuyRate}</span></h3></div>
            <div><h3><span>${showSellRate}</span></h3></div>
        </center>
    </div>

	
	<!-- 顯示圖表(Line Chart) -->
	<div class ="col-md-6 offset-md-3">
	   <div class="card">
	       <div class="card-body">
	           <h1>${showLineChartTitle} <button class="btn btn-success" onclick="updateChart()">更新</button></h1><hr>
	           <canvas id="lineChart"></canvas>
	       </div>
	   </div>
	</div>
	
	
    <script>
    var times, buyRates, sellRates;
    
    //GET Times of Rates
    function getTimes(){
    	$.ajax({
    		type: "GET",
    		url: "/IntumitAssignment/getTimes",
    		dataType: "json",
    		async: false,
    		success: function(data){
    			//var json = JSON.stringify(data);   //轉為字串
    			//var json2 = JSON.parse(data);
    			times = data;
    		},
    		error: function(){
    			alert("error");
    		}
    	});  
    }


    //GET BuyRates of Rates
    function getBuyRates(){
    	$.ajax({
            type: "GET",
            url: "/IntumitAssignment/getBuyRates",
            dataType: "json",
            async: false,
            success: function(data){
                buyRates = data;
            },
            error: function(){
                alert("error");
            }
        });
    }

    
    //GET SellRates of Rates
    function getSellRates(){
    	$.ajax({
            type: "GET",
            url: "/IntumitAssignment/getSellRates",
            dataType: "json",
            async: false,
            success: function(data){
                sellRates = data;
            },
            error: function(){
                alert("error");
            }
        });
    }

    //更新 圖表
    function updateChart() {
        getTimes();
        getBuyRates();
        getSellRates();
        var ctx = document.getElementById('lineChart').getContext('2d');
        var labels = times;     //ex. labels = ["loading", "complete"]
        var datas_BuyRates = buyRates;   //ex. buyRates = [10, 200]
        var datas_SellRates = sellRates;
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'line',
    
            // The data for our dataset
            data: {
                labels: labels,
                datasets: [{
                    label: '買匯',
                    fill: false,
                    borderColor: ["rgba(247,163,92,0.25"],
                    pointRadius: 0.5,
                    data: datas_BuyRates
                },{
                    label: '賣匯',
                    fill: false,
                    borderColor: ["#00FFFF"],
                    pointRadius: 0.5,
                    data: datas_SellRates
                }]
            },
    
            // Configuration options go here
            options: {}
        });
    }
    
    updateChart();
    </script>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>
