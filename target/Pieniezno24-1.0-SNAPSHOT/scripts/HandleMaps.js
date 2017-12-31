/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const X_COORD = 54.23635;
const Y_COORD = 20.12771;


function getRowTitle(row) {
        switch (row) {
                case 0:
                        return "Miasto początkowe";
                case 1:
                        return "Długość trasy";
                case 2:
                        return "Czas przejazdu";
        }
}

function getDistance() {
        var origin = $("input[name='dist_origin']").val();
        var destination = "pieniężno";
        var transportMode = $("select[name='dist_transport']").val();
        var units = $("select[name='dist_units']").val();

        $.getJSON("resources/facts/distance", {"origins": origin, "destinations": destination, "mode": transportMode, "units": units}, function (data) {

                if (data.rows[0].elements[0].distance === null) {
                        $("#error").text("Miasto nie zostało odnalezione.").fadeIn("slow");
                        dbg(data.rows[0].elements[0].distance);
                } 
                else {
                        $("#error").text("").hide();
                        $("#distance_table").find("tr").remove();
                        var table = $("#distance_table");
                        var row = $('<tr>').appendTo(table);
                        var tdTitle = $('<td>').text("Miasto początkowe").appendTo(row);
                        $('<td>').text(data.originAddresses[0]).appendTo(row);

                        row = $('<tr>').appendTo(table);
                        tdTitle = $('<td>').text("Długość trasy").appendTo(row);
                        $('<td>').text(data.rows[0].elements[0].distance.humanReadable).appendTo(row);

                        row = $('<tr>').appendTo(table);
                        tdTitle = $('<td>').text("Czas przejazdu").appendTo(row);
                        $('<td>').text(data.rows[0].elements[0].duration.humanReadable).appendTo(row);
                }
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


     