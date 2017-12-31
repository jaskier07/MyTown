/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 Tak powinna wyglądać struktura strony po załadowaniu:
 <div id="top_logo">
 <img id="logo" src="logo.PNG" alt="logo">
 </div>
 <div id="top_menu">
 <div id="top_menu_container">
 <div class="top_menu_element">Miasto</div>
 </div>
 </div>
 </header>
 <div id="content_container">a </div>
 */

var SRC_IMG_1 = "img/1.jpg";
var SRC_IMG_2 = "img/2.jpg";
var SRC_IMG_3 = "img/3.jpg";
var SRC_IMG_4 = "img/4.jpg";

function createTopBar() {
        $("header").append('<div id="top_logo"><img id="logo" src="img/logo3.png" alt="logo"/><div id="quote_container"><div id="quote"></div></div></div>');
        $("header").append('<div id="top_menu"><div id="top_menu_container"></div></div>');
        $("#top_menu_container").append('<div class="top_menu_element"><a href="town.html">Miasto</a></div>');
        $("#top_menu_container").append('<div class="top_menu_element"><a href="distance.html">Dojazd</a></div>');
        $("#top_menu_container").append('<div class="top_menu_element"><a href="fun_facts.html">Ciekawostki</a></div>');
        $("#top_menu_container").append('<div class="top_menu_element"><a href="weather.html">Pogoda</a></div>');
        $("#top_menu_container").append('<div class="top_menu_element"><a href="http://pieniezno.pl/">Dla zainteresowanych</a></div>');

        $(document).ready(function () {
                $.getJSON("resources/facts/random", function (data) {
                        $("#quote").text(data.text).fadeIn("slow");
                }).error(function (jqXHR) {
                        console.log("incoming Text " + jqXHR.responseText);
                });
        });
}

function changeImage() {
        var img = $("#whole_image");
        
        switch (img.attr("src")) {
                case SRC_IMG_1:
                        img.attr("src", SRC_IMG_2);
                        break;
                case SRC_IMG_2:
                        img.attr("src", SRC_IMG_3);
                        break;
                case SRC_IMG_3:
                        img.attr("src", SRC_IMG_4);
                        break;
                case SRC_IMG_4:
                        img.attr("src", SRC_IMG_1);
                        break;
        }
        img.fadeIn("slow");
}

