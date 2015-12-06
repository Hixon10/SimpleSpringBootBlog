
var posts = [];
var categories = [];


function init() {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': '/admin/category',
        'async': false,
        'dataType': 'json',
        success: function(result) {
            categories = result;
        }
    });

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': '/admin/post',
        'async': false,
        'dataType': 'json',
        success: function(result) {
            posts = result;
        }
    });
}

$(document).ready(function() {
    init();

});