$(document).ready(function () {
    //This is used to size the the card squareness
    $("#selectable li").css("height", $("#selectable li").css("width"));
});



$(function () {
    
    chosencards = new Array();

    $("#selectable").bind("mousedown", function (e) {
        e.metaKey = true;
    }).selectable({
        stop: function () {
            $(".ui-selected", this).each(function () {
                if (!isInArray($(this).attr("id"), chosencards))
                    chosencards.push($(this).attr("id"));
            });
        }
    });
});


//YOUTUBE VIDEO
var youtubeFunc = '';
var outerDiv = document.getElementById("instructionmodal");
var youtubeIframe = outerDiv.getElementsByTagName("iframe")[0].contentWindow;
$('#introVideo').on('hidden.bs.modal', function (e) {
    youtubeFunc = 'pauseVideo';
    youtubeIframe.postMessage('{"event":"command","func":"' + youtubeFunc + '","args":""}', '*');
});
$('#introVideo').on('shown.bs.modal', function (e) {
    youtubeFunc = 'playVideo';
    youtubeIframe.postMessage('{"event":"command","func":"' + youtubeFunc + '","args":""}', '*');
});

 