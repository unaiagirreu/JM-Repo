var image;
var idImage;
var figures;

$(document).ready(function(){
    intervalId = setInterval(ajax, 5000);
	});

		function ajax(){
          $.ajax({                        
                url:"/Spring4MVCApacheTiles3Example/Warehouse/Warehouse",
                dataType:"json",
                Type:"GET",
                contentType:"application/json;charset=UTF-8",
                success:function(res){
                	figures = document.getElementsByClassName("figure-img");
                	for (var i = 0; i < figures.length;i++){
                		figures[i].style.visibility = "hidden";
                	}
                    for(i=0;i<res.length;i++){
                    	console.log(res.length,res[i]["id"]);
                    	draw(res[i]["status"],res[i]["currentSegment"]["id"]);
                    }
                },
                error:function() {
                      alert("error occurred");
                },
            });
      }
 


    function draw(status,currentSegment,id){
            switch(currentSegment){
                case 1:
                    var image = document.getElementById("Line1_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l1");

                break;
                case 2:
                    image = document.getElementById("Line2_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l2");

                break;
                 case 3:
                    image = document.getElementById("Line3_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l3");

                break;
                 case 4:
                    image = document.getElementById("Line4_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l4");

                break;
                 case 5:
                    image = document.getElementById("Line5_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l5");

                break;
                 case 6:
                    image = document.getElementById("Line6_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l6");

                break;
                 case 7:
                    image = document.getElementById("Line7_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l7");

                break;
                 case 8:
                    image = document.getElementById("Line8_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l8");

                break;
                case 9:
                    image = document.getElementById("Line9_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l9");

                break;
                 case 10:
                    image = document.getElementById("Line10_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l10");

                break;
                 case 11:
                    image = document.getElementById("Line11_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l11");

                break;
                 case 12:
                    var image = document.getElementById("Line12_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l12");

                break;
                 case 13:
                    image = document.getElementById("Line13_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l13");

                break;
                 case 14:
                    image = document.getElementById("Line14_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l14");

                break;
                 case 15:
                    image = document.getElementById("Line15_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l15");

                break;
                 case 16:
                    image = document.getElementById("Line16_image");
                    image.style.visibility = "visible";
                    setStatus(status,"l16");

                break;
                 case 17:
                    image = document.getElementById("Workstation1_image");
                    image.style.visibility = "visible";
                    setStatus(status,"w1");

                break;
                 case 18:
                    image = document.getElementById("Workstation2_image");
                    image.style.visibility = "visible";
                    setStatus(status,"w2");

                break;
                 case 19:
                    image = document.getElementById("Workstation3_image");
                    image.style.visibility = "visible";
                    setStatus(status,"w3");

                break;
                 case 20:
                    image = document.getElementById("Workstation4_image");
                    image.style.visibility = "visible";
                    setStatus(status,"w4");

                break;
                 case 21:
                    image = document.getElementById("Workstation5_image");
                    image.style.visibility = "visible";
                    setStatus(status,"w5");

                break;
                 case 22:
                    image = document.getElementById("Workstation6_image");
                    image.style.visibility = "visible";
                    setStatus(status,"w6");

                break;
                 case 23:
                    image = document.getElementById("Parking1_image");
                    image.style.visibility = "visible";
                    setStatus(status,"p1");

                break;
                 case 24:
                    image = document.getElementById("Parking2_image");
                    image.style.visibility = "visible";
                    setStatus(status,"p2");

                break;
                 case 25:
                    image = document.getElementById("Parking3_image");
                    image.style.visibility = "visible";
                    setStatus(status,"p3");

                break;
                 case 26:
                     image = document.getElementById("Parking4_image");
                     image.style.visibility = "visible";
                     setStatus(status,"p4");
                 break;

                default:

            }
    }
      
      
    function setStatus(status,idImage){
        switch(status){
            case "movingToParking":
            imageSrc = document.getElementById(idImage);
            imageSrc.src = "/Spring4MVCApacheTiles3Example/static/img/coxe_verde.png";
            break;
            case "stoped":
            imageSrc = document.getElementById(idImage);
            imageSrc.src = "/Spring4MVCApacheTiles3Example/static/img/coxe_rojo.png";
            break;
            case "moving":
            imageSrc = document.getElementById(idImage);
            imageSrc.src = "/Spring4MVCApacheTiles3Example/static/img/coxe_amarillo.png";
            break;
            default:
            imageSrc = document.getElementById(idImage);
            imageSrc.src = "/Spring4MVCApacheTiles3Example/static/img/coxe.png";
            break;
        }
      }
 