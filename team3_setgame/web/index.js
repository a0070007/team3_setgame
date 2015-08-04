$(document).ready(function () {

    var gamelisttemplate =
            Handlebars.compile($("#gamelisttemplate").html());

    var highscorelisttemplate =
            Handlebars.compile($("#highscorelisttemplate").html());

    var playerlisttemplate =
            Handlebars.compile($("#playerlisttemplate").html());
    
    //Fetches all games upon load of web site
    $.getJSON("")
            .done(function (result) {
                $("#gamelist").empty();
                for (var i in result) {
                    $("#gamelist").append(
                            gamelisttemplate({
                                gameid: result[i].id,
                                gametitle: result[i].title,
                                gamenoofplayer: result[i].noofplayer,
                                gametimestart: result[i].gametimestart
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


