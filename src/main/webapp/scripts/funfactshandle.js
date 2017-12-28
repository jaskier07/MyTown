/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getFunFacts() {
        var fact_limit = $("select[name='fact_limit']").val();
        var fact_sort = $("select[name='fact_sort']").val();
        var fact_importance = $("select[name='fact_importance']").val();
        var request = "resources/facts/list";

        $.getJSON(request, {"limit": fact_limit, "importance": fact_importance, "sort": fact_sort}, function (data) {
                $("#facts_table").find("tr:gt(0)").remove();
                var table = $("#facts_table");

                $.each(data, function (i, fact) {
                        var row = $('<tr>').appendTo(table);
                        $('<td>').text(fact.id + ", " + fact.importance + ", " + fact.text).appendTo(row);
                });
        }).error(function (jqXHR) {
                console.log("incoming Text " + jqXHR.responseText);
        });
}

