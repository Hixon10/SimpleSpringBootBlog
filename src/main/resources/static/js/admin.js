
var posts = [];
var categories = [];


function createPost() {
	
}

function updateCategory() {
    var title = $('#categoryNewTitle').val();
    var id = $("#updateCategory").data('category_id');

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'PUT',
        'url': '/admin/category?categoryId=' + id,
        'async': false,
        'data': JSON.stringify({'title': title}),
        'dataType': 'json',
        success: function(result) {
        },
        error: function(jqXHR, textStatus, errorThrown) {
            var response = jqXHR.responseText;

            if (response) {
                response = JSON.parse(response);
                response = response["errors"][0];
                var message = response["defaultMessage"];
                alert(message);
            }
        }
    });

    location.reload(true);
}

function createCategory() {
	var title = $('#categoryTitle').val();

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': '/admin/category',
        'async': false,
        'data': JSON.stringify({'title': title}),
        'dataType': 'json',
        success: function(result) {
        },
        error: function(jqXHR, textStatus, errorThrown) {
            var response = jqXHR.responseText;

            if (response) {
                response = JSON.parse(response);
                response = response["errors"][0];
                var message = response["defaultMessage"];
                alert(message);
            }
        }
    });

    location.reload(true);
}

function deleteCategory(id) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'DELETE',
        'url': '/admin/category?categoryId=' + id,
        'async': false,
        'dataType': 'json',
        success: function(result) {

        }
    });

    location.reload(true);
}

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

    var htmlCategories = "";
    var selectCategories = "";

    for (var i = 0; i < categories.length; i++) {

        if (i == 0) {
            selectCategories += '<option selected="selected" value="' + categories[i].id + '">' + categories[i].title + '</option>';
        } else {
            selectCategories += '<option value="' + categories[i].id + '">' + categories[i].title + '</option>';
        }

        htmlCategories += '<tr>';
        htmlCategories += '<td>' + categories[i].title + '</td>';
        htmlCategories += '<td>';
        htmlCategories += '<button data-category_id="' + categories[i].id + '" type="button" class="updateCategoryButton btn btn-default btn-xs">Редактировать</button>';
        htmlCategories += '</td>';
        htmlCategories += '<td>';
        htmlCategories += '<button data-category_id="' + categories[i].id + '" type="button" class="deleteCategoryButton btn btn-danger btn-xs">Удалить</button>';
        htmlCategories += '</td>';
        htmlCategories += '</tr>';
    }

    $('#categoriesTable > tbody:last-child').append(htmlCategories);
    $("#postCategory").html(selectCategories);
}

$(document).ready(function() {
    init();

    $("#saveCategory").click(function(){
        createCategory();
		$('#editCategoryModal').modal('toggle');
    });

    $("#updateCategory").click(function(){
        updateCategory();
    });

    $(".updateCategoryButton").click(function(){
        var $el = $(this);
        var id = $el.data('category_id');
        var title = "";

        for (var i = 0; i < categories.length; i++) {
            if (categories[i].id == id) {
                title = categories[i].title;
                break;
            }
        }

        $("#updateCategory").data('category_id', id);
        $("#categoryNewTitle").val(title);

        $('#updateCategoryModal').modal('toggle');
    });

    $('.deleteCategoryButton').on('click', function () {
		var $el = $(this);
		var id = $el.data('category_id');
		deleteCategory(id);
	});
});