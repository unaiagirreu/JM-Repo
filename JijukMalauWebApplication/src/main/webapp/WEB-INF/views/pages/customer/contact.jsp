<!-- Contact us map -->
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.4.0/dist/leaflet.css"
    integrity="sha512-puBpdR0798OZvTTbP4A8Ix/l+A4dHDD0DGqYW6RQ+9jxkRFclaxxQb/SJAWZfWAkuyeQUytO7+7N4QKrDh+drA==" crossorigin=""/>
    <script src="https://unpkg.com/leaflet@1.4.0/dist/leaflet.js" 
    integrity="sha512-QVftwZFqvtRNi0ZyCtsznlKSWOStnDORoefr1enyq5mVL4tmKB3S/EnC3rRJcxCPavG10IcrVGSmPh6Qw5lwrg==" crossorigin=""></script>

<div class="container">
    <div class="row justify-content-center">
        <div class="card">
            <div class="card-header text-info text-center"><a class="text-center">Contact us</a></div>
             <div class="card-body">
                 <a>JijukMalau STOCK</a><br>
                 <a>E-mail: jijukmalau.develop@gmail.com</a><br>
                 <a>Goiru Kalea, 2, 20500 Arrasate, Gipuzkoa</a><br>
             </div>
        <div id="mapid" style="height: 400px; width: 600px"></div>
        <script>
            var mymap = L.map('mapid').setView([43.063162, -2.506252], 15);

                L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
                    maxZoom: 18,
                    id: 'mapbox.streets'
                }).addTo(mymap);

                L.marker([43.063162, -2.506252]).addTo(mymap)
                    .bindPopup("<b>Mondragon Unibertsitatea</b><br>Polo Garaia, 11th building").openPopup();

        </script>
        </div>
    </div>
</div>
