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

function createTopBar() {
        $("header").append('<div id="top_logo"><img id="logo" src="img/logo2.png" alt="logo"/><div id="quote"></div></div>');
        $("header").append('<div id="top_menu"><div id="top_menu_container"></div></div>');
        $("#top_menu_container").append('<div class="top_menu_element"><a href="town.html">Miasto</a></div>');
        $("#top_menu_container").append('<div class="top_menu_element"><a href="weather.html">Pogoda</a></div>');
        $("#top_menu_container").append('<div class="top_menu_element"><a href="distance.html">Jak daleko jesteś od Pieniężna?</a></div>');
        $("#top_menu_container").append('<div class="top_menu_element"><a href="fun_facts.html">Ciekawostki</a></div>');
        $("#top_menu_container").append('<div class="top_menu_element"><a href="investors.html">Dla Inwestorów</a></div>');

        $(document).ready(function () {
                $.getJSON("resources/facts/random", function (data) {
                        $("#quote").text(data.text);
                }).error(function (jqXHR) {
                        console.log("incoming Text " + jqXHR.responseText);
                });
        });


}

