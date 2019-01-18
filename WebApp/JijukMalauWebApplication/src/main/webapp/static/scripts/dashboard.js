$(document).ready(function(){
          $.ajax({                        
                url:"/Spring4MVCApacheTiles3Example/Dashboards/First",
                dataType:"json",
                Type:"GET",
                contentType:"application/json;charset=UTF-8",
                success:function(res){
                    console.log(res);
                    makePlot1(res);
                },
                error:function() {
                      alert("error occurred");
                },
            });
		$.ajax({                        
		    url:"/Spring4MVCApacheTiles3Example/Dashboards/Second",
		    dataType:"json",
		    Type:"GET",
		    contentType:"application/json;charset=UTF-8",
		    success:function(res){
		        console.log(res);
		        makePlot2(res);
		    },
		    error:function() {
		          alert("error occurred");
		    },
		});
		$.ajax({                        
		    url:"/Spring4MVCApacheTiles3Example/Dashboards/Third",
		    dataType:"json",
		    Type:"GET",
		    contentType:"application/json;charset=UTF-8",
		    success:function(res){
		        console.log(res);
		        makePlot3(res);
		    },
		    error:function() {
		          alert("error occurred");
		    },
		});
});	
function makePlot1(res){
	var data = [{
		x: ['Screens', 'Desktop Computers', 'Laptops','Peripherals','Printers'],
		y: [res[0],res[1], res[2],res[3], res[4]],
		type: 'bar'
		}];
		Plotly.newPlot('myDiv0', data, {}, {showSendToCloud:true});
}
function makePlot2(res){
	console.log[res];
	var arrayUser = new Array();
	var arrayOrders = new Array();
	var arrayCost = new Array();
	var	arrayAvgOrders = new Array();
	var	arrayAvgCost = new Array();
		
	for(var i = 0;i < res["list1"].length;i++){
		console.log(i)
		arrayUser[i] = res["list1"][i][0];
		arrayOrders[i] = res["list1"][i][1];
		arrayCost[i] = res["list2"][i];
		arrayAvgOrders[i] = res["list3"][i];
		arrayAvgCost[i] = res["list4"][i];
	}
		var values = [
			 arrayUser,
			 arrayOrders,
			 arrayCost,
			 arrayAvgOrders,
			 arrayAvgCost
		]

		 var data = [{
		 type: 'table',
		 header: {
		 values: [["User ID"], ["Total orders"],
		 ["Total cost"], ["Avg products per order"], ["Avg cost"]],
		 align: "center",
		 line: {width: 1, color: 'black'},
		 fill: {color: "grey"},
		 font: {family: "Arial", size: 13, color: "white"}
		 },
		 cells: {
		 values: values,
		 align: "center",
		 line: {color: "black", width: 1},
		 font: {family: "Arial", size: 12, color: ["black"]}
		 }
		 }]

		 Plotly.plot('myDiv2', data);
}
function makePlot3(res){
		var ultimateColors = [['rgb(56, 75, 126)', 'rgb(18, 36, 37)', 'rgb(34, 53, 101)', 'rgb(36, 55, 57)', 'rgb(6, 4, 4)']];
		var data = [{
		  values: [res[0],res[1],res[2],res[3],res[4]],
		  labels: ['Vehicle 1','Vehicle 2','Vehicle 3','Vehicle 4','Vehicle 5'],
		  type: 'pie',
		  marker: {
    		colors: ultimateColors[0]
 		  },
		}];

		var layout = {
		  height: 400,
		  width: 500
		};

		Plotly.newPlot('myDiv1', data, layout);

}
