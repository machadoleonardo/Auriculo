var map;
var elevator;
var latlng;
function initMap() {
    // function to get random element from an array
    (function($) {
	$.rand = function(arg) {
	    if ($.isArray(arg)) {
		return arg[$.rand(arg.length)];
	    } else if (typeof arg === "number") {
		return Math.floor(Math.random() * arg);
	    } else {
		return 4; // chosen by fair dice roll
	    }
	};
    })(jQuery);
    // start code on document ready

    var myOptions = {
	zoom : 6,
	center : new google.maps.LatLng(53.3496, -6.3263),
	mapTypeId : google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map($('#map')[0], myOptions);

    // get place from inputfile.js
    //var placesObject = place;
    errorArray = [];

    // will fire 20 ajax request at a time and other will keep in queue
    var queuCounter = 0, setLimit = 20;

    // keep count of added markers and update at top
    totalAddedMarkers = 0;

    // make an array of geocode keys to avoid the overlimit error
    var geoCodKeys = ["AIzaSyDSPKRPz4w78_lBlRp8YlK52RrLGwMFyF8"
    				 //'AIzaSyCF82XXUtT0vzMTcEPpTXvKQPr1keMNr_4',
                     //'AIzaSyAYPw6oFHktAMhQqp34PptnkDEdmXwC3s0',
                     //'AIzaSyAwd0OLvubYtKkEWwMe4Fe0DQpauX0pzlk',
                     //'AIzaSyDF3F09RkYcibDuTFaINrWFBOG7ilCsVL0',
                     //'AIzaSyC1dyD2kzPmZPmM4-oGYnIH_0x--0hVSY8'                   
                	];

    // funciton to add marker
    var addMarkers = function(address, icon, queKey) {
	var key = jQuery.rand(geoCodKeys);
	var url = 'https://maps.googleapis.com/maps/api/geocode/json?key=' + key + '&address=' + address + '&sensor=false';

	var qyName = '';
	if (queKey) {
	    qyName = queKey;
	} else {
	    qyName = 'MyQueue' + queuCounter;
	}

	$.ajaxq(qyName, {
	    url : url,
	    dataType : 'json'
	}).done(function(data) {
	    var address = getParameterByName('address', this.url);
	    var index = errorArray.indexOf(address);
	    try {
		//var p = data.results[0].geometry.location;
		//var latlng = new google.maps.LatLng(p.lat, p.lng);
		new google.maps.Marker({
		    position:  data.results[0].geometry.location,
		    map: map,
		    icon: icon
		});
		map.setCenter(data.results[0].geometry.location);

		if (index > -1) {
		    errorArray.splice(index, 1);
		}
	    } catch (e) {
		if (data.status = 'ZERO_RESULTS')
		    return false;

		// on error call add marker function for same address
		// and keep in Error ajax queue
		addMarkers(address, icon, 'Errror');
		if (index == -1) {
		    errorArray.push(address);
		}
	    }
	});

	// mentain ajax queue set
	queuCounter++;
	if (queuCounter == setLimit) {
	    queuCounter = 0;
	}
    }

    // function get url parameter from url string
    getParameterByName = function(name, href) {
	name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
	var regexS = "[\\?&]" + name + "=([^&#]*)";
	var regex = new RegExp(regexS);
	var results = regex.exec(href);
	if (results == null)
	    return "";
	else
	    return decodeURIComponent(results[1].replace(/\+/g, " "));
    }
    function atualizaMapa(){
    	for ( var key in regiaoSul){
    		var icon = {
    			path : "M-20,0a20,20 0 1,0 40,0a20,20 0 1,0 -40,0",
    			fillColor : regiaoSul[key].cor,
    			fillOpacity : .6,
    			anchor : new google.maps.Point(0, 0),
    			strokeWeight : 0,
    			scale : .2
    		};
    		for (var indice in  regiaoSul[key].address) {
    			var address = regiaoSul[key].address[indice]['City'] + ', ' + regiaoSul[key].address[indice]['State'];
    			addMarkers(address, icon);
    }
    	}
    }

    atualizaMapa();
 
};

