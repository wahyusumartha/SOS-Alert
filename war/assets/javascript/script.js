$(document).ready(function(){
    var map = new GMap2($("#map").get(0));
    map.addControl(new GLargeMapControl());
    map.addControl(new GMapTypeControl());

    var burnsvilleMN = new GLatLng(3.15785,101.71165);
    map.setCenter(burnsvilleMN, 10);

    var markers = [];

    $.getJSON('http://sos-alert.appspot.com/rest/activitieslist/reports', function(data) {
    	  size = eval(data.report);
    	  
    	  console.log(size.length == undefined);
    	  console.log(typeof data);
    	  
    	  if(size.length == undefined){
    		  var point = new GLatLng(data.report.latitude, data.report.longitude);
    		  marker = new GMarker(point);
    		  map.addOverlay(marker);
    		  markers[0] = marker;
    		  

    		  $('<li class='+data.report.priority+' />')
  	        .html(data.report.description + " Submitted By : " + data.report.name + 
  	        		" -- Location : " + data.report.latitude + ", " + data.report.longitude) 
  	        .click(function(){
  	            displayPoint(marker, 0);
  	        })
  	        .appendTo("#list");

  	        GEvent.addListener(marker, "click", function(){
  	           displayPoint(marker, 0);
  	        });
    	  }else{
    	  
       	  for(var i = 0; i < size.length; i++){
    		  var point = new GLatLng(data.report[i].latitude, data.report[i].longitude);
    		  marker = new GMarker(point);
    		  map.addOverlay(marker);
    		  markers[i] = marker;
    	  }
    	  
    	  $(markers).each(function(i,marker){
    	        $('<li class='+data.report[i].priority+' />')
    	        .html(data.report[i].description + " Submitted By : " + data.report[i].name + 
    	        		" -- Location : " + data.report[i].latitude + ", " + data.report[i].longitude) 
    	        .click(function(){
    	            displayPoint(marker, i);
    	        })
    	        .appendTo("#list");

    	        GEvent.addListener(marker, "click", function(){
    	        	displayPoint(marker, i);
    	        });
    	    });
    	  }
    	});
  

    $("#message").appendTo(map.getPane(G_MAP_FLOAT_SHADOW_PANE));

    function displayPoint(marker, index){
        $("#message").hide();

        var moveEnd = GEvent.addListener(map, "moveend", function(){
            var markerOffset = map.fromLatLngToDivPixel(marker.getLatLng());
            $("#message")
            .fadeIn()
            .css({
                top:markerOffset.y,
                left:markerOffset.x,
                visibility: 'visible'
            });

            GEvent.removeListener(moveEnd);
        });
        map.panTo(marker.getLatLng());
    }


    //footer slider
    jQuery(function($) {
        var open = false;
        $('#footerSlideButton').click(function () {
            if(open === false) {
                $('#footerSlideContent').animate({
                    height: '300px'
                });
                $(this).css('backgroundPosition', 'bottom left');
                open = true;
            } else {
                $('#footerSlideContent').animate({
                    height: '0px'
                });
                $(this).css('backgroundPosition', 'top left');
                open = false;
            }
        });
    });
});
