var ipaddr = "";

var gamelisttemplate =
        Handlebars.compile($("#gamelisttemplate").html());

var highscorelisttemplate =
        Handlebars.compile($("#highscorelisttemplate").html());

var playerlisttemplate =
        Handlebars.compile($("#playerlisttemplate").html());

$(document).ready(function () {

    //Fetches all games upon load of web site
    $.getJSON(ipaddr + "api/main/getallgames")
            .done(function (result) {
                $("#gamelist").empty();
                var game = result.gamesArray;
                for (var i in game) {
                    $("#gamelist").append(
                            gamelisttemplate({
                                gameid: game[i].gameId,
                                gametitle: game[i].title,
                                gamenoofplayer: game[i].maxPlayers,
                                gametimestart: game[i].startTime
                            })
                            );
                }
            });
            
    //Fetches highscore board upon load of web site
    $.getJSON(ipaddr + "api/main/gettopplayers")
            .done(function (result) {
                $("#highscorelist").empty();
                var topPlayer = result.topPlayersArray;
                for (var i in topPlayer) {
                    $("#highscorelist").append(
                            highscorelisttemplate({
                                gravatarurl: "",
                                playername: topPlayer[i].name,
                                playerscore: topPlayer[i].highscore
                            })
                            );
                }
            });    
            
    //create new player        
    $("#btn_submitplayer").on("click", function() {
        $.getJSON("api/player/" + $("#inputEmail").val() + "/" + $("#inputName").val() + "/" +$("#inputPassword").val())
            .done(function(result){
                if(result === Response.Status.BAD_REQUEST){
                    //return unsucessful
                }
                else{
                    //return successful
                }
            });
    });
    
    //create new game
    $("#btn_submitgame").on("click", function() {
        $.getJSON("api/game/" + $("#inputTitle").val + "/" + $("#inputDuration").val() + "/" +$("#inputPlayers").val())
            .done(function(result){
                if(result === Response.Status.BAD_REQUEST){
                    //return unsucessful
                }
                else{
                    Response.redirect("index.html");
                }
            });
    });

    $('#gameform')
            .formValidation({
                framework: 'bootstrap',
                icon: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                excluded: ':disabled',
                fields: {
                    title: {
                        validators: {
                            notEmpty: {
                                message: 'The name is required'
                            },
                            stringLength: {
                                min: 6,
                                max: 30,
                                message: 'The name must be more than 6 and less than 30 characters long'
                            }
                        }
                    },
                    players: {
                        validators: {
                            notEmpty: {
                                message: 'The size is required'
                            }
                        }
                    },
                    duration: {
                        validators: {
                            notEmpty: {
                                message: 'The color is required'
                            }
                        }
                    }
                }
            })
            // Using Bootbox for color and size select elements
            .find('[name="color"], [name="size"]')
            .combobox()
            .end();
});


