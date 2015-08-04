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
                for (var i in result) {
                    $("#gamelist").append(
                            gamelisttemplate({
                                gameid: result[i].gameId,
                                gametitle: result[i].title,
                                gamenoofplayer: result[i].maxPlayers,
                                gametimestart: result[i].startTime
                            })
                            );
                }
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


