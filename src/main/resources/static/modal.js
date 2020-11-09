
$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (user, status) {
            $('.myForm #id').val(user.id);
            $('.myForm #username').val(user.username);
            $('.myForm #password').val(user.password);
            $('.myForm #email').val(user.email);
        });

        $('.myForm #editModal').modal();
    });
});

