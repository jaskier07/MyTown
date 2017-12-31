/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const X_COORD = 54.23635;
const Y_COORD = 20.12771;

const MEASUREMENTS_PER_DAY = 8;
const DEGREE_PARAMETER_CELCIUS = 0;
const DEGREE_PARAMETER_FARHENHEIT = 2;

const CELCIUS = "0";
const KELVIN = "1";
const FARENHEIT = "2";

const DEGREE_SYMBOL = "\xB0";
const OPEN_WEATHER_KEY = "31241e788646b2ccb6613b9ca3466e59";
var REQUEST_URI = "http://api.openweathermap.org/data/2.5/forecast?";

function initForm() {
        $("input[name='x_coord']").val(X_COORD);
        $("input[name='y_coord']").val(Y_COORD);
}

function getWeather() {
        var xCoord = $("input[name='x_coord']").val();
        var yCoord = $("input[name='y_coord']").val();
        var temp_degree = $("select[name='temp_degree']").val();

        if (isNaN(xCoord) || isNaN(yCoord) || xCoord > 90 || xCoord < -90 || yCoord > 180 || yCoord < -180) {
                $("#error").text("Wprowadzone dane są niepoprawne.").fadeIn("slow");
        } else {
                $("#error").text("").hide();
                $.getJSON(REQUEST_URI, {"lat": xCoord, "lon": yCoord, "units": getStringParameterByDegreeType(temp_degree), "cnt": MEASUREMENTS_PER_DAY, "lang": "pl", "APPID": OPEN_WEATHER_KEY}, function success(data) {
                        var table = $("#weather_table");
                        $("#weather_table").find("tr:gt(0)").remove();

                        for (i = 0; i < MEASUREMENTS_PER_DAY; i++) {
                                var row = $("<tr>").appendTo(table);
                                $("<td>").text(data.list[i].dt_txt).appendTo(row);
                                $("<td>").text(data.list[i].main.temp + " " + DEGREE_SYMBOL + getDegreeRepresentation(temp_degree)).appendTo(row);
                                var imgTd = $("<td>").appendTo(row);
                                $("<img>").attr("class", "weather_img").attr("src", "img/" + getImgSrcByWeatherType(data.list[i].weather[0].icon)).appendTo(imgTd);
                                $("<td>").text(data.list[i].weather[0].description).appendTo(row);
                                $("<td>").text(data.list[i].main.pressure + " hPa").appendTo(row);
                        }
                }).error(function (jqXHR) {
                        console.log("incoming Text " + jqXHR.responseText);
                });
        }
}

function dbg(txt) {
        console.log(txt);
}

function getDegreeRepresentation(temp) {
        switch (temp) {
                case CELCIUS:
                        return "C";
                case KELVIN:
                        return "K";
                case FARENHEIT:
                        return "F";
        }
}

function getStringParameterByDegreeType(temp) {
        switch (temp) {
                case CELCIUS:
                        return "metric";
                case KELVIN:
                        return "";
                case FARENHEIT:
                        return "imperial";
        }
}

function getImgSrcByWeatherType(type) {
        switch (type) {
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