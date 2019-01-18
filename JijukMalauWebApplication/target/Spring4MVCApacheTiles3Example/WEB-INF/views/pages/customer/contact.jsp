<div class="container">
	<div class="row justify-content-center">
		<div class="card">
			<div class="card-header text-info"><a>Contact us</a></div>
			 <div class="card-body">
			 	<a>JijukMalau STOCK</a>
			 	<a>E-mail: jijukmalau.develop@gmail.com</a>
			 	<a>Goiru Kalea, 2, 20500 Arrasate, Gipuzkoa</a>
			 </div>
		<div id="mapid" style="height: 200px;"></div>
		<script>
			var mymap = L.map('mapid').setView([43.063162, -2.506252], 13);
			
			L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
			    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
			    maxZoom: 18,
			    id: 'mapbox.streets',
			    accessToken: 'your.mapbox.access.token'
			}).addTo(mymap);
			
			var marker = L.marker([43.063162, -2.506252]).addTo(mymap);
			marker.bindPopup("<b>Mondragon Unibertsitatea</b><br>Polo Garaia, 11th building").openPopup();
		</script>
	</div></div>
</div>
