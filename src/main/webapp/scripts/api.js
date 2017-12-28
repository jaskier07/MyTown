/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const measurementsPerDay = 8;
const GET_DEGREE_PARAMETER = 0;
const GET_DEGREE_CHAR = 1;
const DEGREE_PARAMETER_CELCIUS = "&units=metric";
const DEGREE_PARAMETER_FARHENHEIT = "&units=imperial";
const PL_LANG_PARAMETER = "&lang=pl";
const LIMIT_RESULTS_PARAMETER = "&cnt=";
const Y_COORD_PARAMETER = "&lon=";

const CELCIUS = "Stopnie Celcjusza";
const KELVIN = "Stopnie Kelvina";

const DEGREE_SYMBOL = "\xB0";

const X_COORD = 54.23635;
const Y_COORD = 20.12771;

const google_key = "AIzaSyAPwma7_2ztcHHAnTdnLBcQDAKPMZqFMSU";
const OPEN_WEATHER_KEY = "31241e788646b2ccb6613b9ca3466e59";
var appId = "&APPID=" + OPEN_WEATHER_KEY;


function getWeather() {
        var xCoord =  $("input[name='x_coord']").val();
        var yCoord = $("input[name='y_coord']").val();
        var temp = $("select[name='temp_degree']").val();
        var days = 1//parseFloat($("select[name='days_nr']").val());
             
        var measurements  = days * measurementsPerDay;
             
        var request = "http://api.openweathermap.org/data/2.5/forecast?lat=" + xCoord + Y_COORD_PARAMETER + yCoord + 
                getStringByDegreeType(temp, GET_DEGREE_PARAMETER) + LIMIT_RESULTS_PARAMETER +  measurements + PL_LANG_PARAMETER + appId;
        //var request = "http://api.openweathermap.org/data/2.5/forecast?id=3099434" + appId;
        $.getJSON(request, function success(data) {
                var table = $("#weather_table"); 
                $("#weather_table").find("tr:gt(0)").remove();

                for (i = 0; i < measurements; i++) {
                        var row = $("<tr>").appendTo(table);
                        $("<td>").text(data.list[i].dt_txt).appendTo(row);
                        $("<td>").text(data.list[i].main.temp + " " + DEGREE_SYMBOL + getStringByDegreeType(temp, GET_DEGREE_CHAR)).appendTo(row);
                        var imgTd = $("<td>").appendTo(row);
                        $("<img>").attr("class", "weather_img").attr("src", "img/" + getImgSrcByWeatherType(data.list[i].weather[0].icon)).appendTo(imgTd);
                        $("<td>").text(data.list[i].weather[0].description).appendTo(row);
                        $("<td>").text(data.list[i].main.pressure + " hPa").appendTo(row);
                }      
        }).error(function (jqXHR) {
                console.log("incoming Text " + jqXHR.responseText);
        });
        
        /*
         $.get('pieniezno24/process/sayHello', {'name': appId}, function (data) {
         console.log("jestem tutaj 1");
         $("#content_container").text(JSON.stringify(data));  
         }).error(function(jqXHR, textStatus, errorThrown) { 
         console.log("error" + textStatus); 
         console.log("incoming Text " + jqXHR.responseText);
         });
         console.log("jestem tutaj 3");*/
}

function getDistance() {
        var origin = "&origins=" + "nowy dwór gdański";
        var destination = "&destinations="  + "pieniężno";
        var transport = "?mode=" +  "driving";
        var units = "?units=" + "metric";
        
        var request = "http://maps.googleapis.com/maps/api/distancematrix/json" + units + transport + origin + destination;
        request = encodeURI(request);
        dbg(request);
        
       // $.getJSON('resources')
}

function getFunFacts() {
        $.getJSON('resources/facts/list', function(data) {
                var table = $("#facts_table");
                    $.each(data, function (i, fact) {
                        var row = $('<tr>').appendTo(table);
                        $('<td>').text(fact.id + ", " + fact.importance + ", " + fact.text).appendTo(row);
                    });
        }).error(function (jqXHR) {
                console.log("incoming Text " + jqXHR.responseText);
        });
}

function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: X_COORD, lng: Y_COORD},
                zoom: 12
        }
        );
}

function dbg(txt) {
        console.log(txt);
}

function initForm() {
        $("input[name='x_coord']").val(X_COORD);
        $("input[name='y_coord']").val(Y_COORD);
}

function getStringByDegreeType(temp, parameter_type) {
        switch(parameter_type) {
                case GET_DEGREE_PARAMETER:
                        switch(temp) {
                                case CELCIUS:
                                        return DEGREE_PARAMETER_CELCIUS;
                                case KELVIN:
                                        return "";
                                default:
                                        return DEGREE_PARAMETER_FARHENHEIT;
                        }
                case GET_DEGREE_CHAR:
                        switch(temp) {
                                case CELCIUS:
                                        return "C";
                                case KELVIN:
                                        return "K";
                                default:
                                        return "F";
                        }
        }
}

function getImgSrcByWeatherType(type) {
        switch(type) {
                case "01d":
                        return "clear sky.png";
                case "01n":
                        return "clear sky.png";
                 case "02d":
                        return "few clouds.png";
                case "02n":
                        return "few clouds.png";   
                case "03d":
                        return "scattered clouds.png";
                case "03n":
                        return "scattered clouds.png";   
                case "04d":
                        return "broken clouds.png";
                case "04n":
                        return "broken clouds.png";
                  case "09d":
                        return "shower rain.png";
                case "09n":
                        return "shower rain.png";   
                case "10d":
                        return "rain.png";
                case "10n":
                        return "rain.png"; 
                 case "11d":
                        return "thunderstorm.png";
                case "11n":
                        return "thunderstorm.png";   
                case "13d":
                        return "snow.png";
                case "13n":
                        return "snow.png"; 
                case "50d":
                        return "mist.png";
                case "50n":
                        return "mist.png"; 
        }
}


     