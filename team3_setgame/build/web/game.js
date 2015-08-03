$(document).ready(function () {
    //This is used to size the the card squareness
    $("#selectable li").css("height", $("#selectable li").css("width"));
});
$(function () {
    var cardtemplate = Handlebars.compile($("#cardtemplate").html());
    chosencards = new Array();

    $("#btn_submit").on("click", function () {
        alert(chosencards.toString());
    });

    $("#selectable").bind("mousedown", function (e) {
        e.metaKey = true;
    }).selectable({
        stop: function () {
            $(".ui-selected", this).each(function () {
                if (!isInArray($(this).attr("id"),chosencards))
                chosencards.push($(this).attr("id"));
            });
        }
    });
});

 