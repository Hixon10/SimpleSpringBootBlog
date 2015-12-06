
var posts = [];
var categories = [];

var login = "";
var password = "";


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

function insertPosts(posts) {
    var postsHtml = "";

    for (var i = 0; i < posts.length; i++) {
        postsHtml += '<div class="blog-post">';
        postsHtml += '<h2 class="blog-post-title">' + posts[i].title + '</h2>';
        postsHtml += '<p class="blog-post-meta">' + posts[i].createdDate.month + ' ' + posts[i].createdDate.dayOfMonth + ', ' + posts[i].createdDate.year + ' by <a href="#">Chris</a></p>';
        postsHtml += '<div>' + posts[i].content + '</div>';
        postsHtml += '</div>';
    }

    $("#contentContainer").html(postsHtml);
}

function insertAllCategories() {
    var categoriesHtml = "";

    for (var i = 0; i < categories.length; i++) {
        categoriesHtml += '<li><a data-category_title="' + categories[i].title + '" class="categoryLink" href="#">' + categories[i].title + '</a></li>';
    }

    $('#categoryList').html(categoriesHtml);
}

function searchPosts() {
    var query = $('#s').val();

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': '/post/search?query=' + query,
        'dataType': 'json',
        success: function(result) {
            insertPosts(result);
        }
    });
}


function loginF() {
    var log  = $('#loginLogin').val();
    var pas = $('#passwordLogin').val();

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': '/account/login',
        'async': false,
        'data': JSON.stringify({'login': log, 'password': pas}),
        'dataType': 'json',
        success: function(result) {
        },
        error: function(jqXHR, textStatus, errorThrown) {
            var response = jqXHR.responseText;

            if (response) {
                response = JSON.parse(response);
                alert(response.message);
                location.reload(true);
            }
        }
    });

    login = log;
    password = pas;

    $('#registerLink').addClass('hidden');
    $('#loginLink').addClass('hidden');

    $('#logoutLink').removeClass('hidden');
    $('#logoutLink').text('Привет, ' + login + '. Выйти.');

    $('#loginModal').modal('toggle');
}

function register() {
    var login = $('#login').val();
    var password = $('#password').val();

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': '/account/register',
        'async': false,
        'data': JSON.stringify({'login': login, 'password': password}),
        'dataType': 'json',
        success: function(result) {
        },
        error: function(jqXHR, textStatus, errorThrown) {
            var response = jqXHR.responseText;

            if (response) {
                response = JSON.parse(response);
                alert(response.message);
            }
        }
    });

    location.reload(true);
}

$(document).ready(function() {
    init();
    insertPosts(posts);
    insertAllCategories();

    $('#logoutLink').click(function(){
        $('#registerLink').removeClass('hidden');
        $('#loginLink').removeClass('hidden');

        $('#logoutLink').addClass('hidden');
        login = "";
        password = "";
    });

    $('#loginButton').on('click', function () {
        loginF();
    });

    $("#loginLink").click(function(){
        $('#loginModal').modal('toggle');
    });

    $('#registerButton').on('click', function () {
        register();
    });

    $("#registerLink").click(function(){
        $('#registerModal').modal('toggle');
    });

    $("#homePageLink").click(function(){
        insertPosts(posts);
    });

    $("#searchButton").click(function(){
        searchPosts();
        return false;
    });

    $('body').on('click', 'a.categoryLink', function() {
        var $el = $(this);
        var categoryTitle = $el.data('category_title');

        for (var i = 0; i < categories.length; i++) {
            if (categories[i].title == categoryTitle) {
                insertPosts(categories[i].posts);
                break;
            }
        }
    });
});