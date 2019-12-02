$('#modal_purchase_question').on('show.bs.modal', function(event) {
    var button = $(event.relatedTarget);
    var recipient = button.data('whatever');
    var recipient_two = button.parents('.card').find('h3').text();

    console.log('show.bs.modal');

    console.log('whatever: ' + recipient);
    console.log('whatever: ' + recipient_two);

    var modal = $(this);
    modal.find('input[name="testInp"]').val(""); //.css("background-color", "red");
    //inputTmp.css("background-color", "red");
    // inputTmp.inpText = "1111";

    modal.find('.modal-title').text('Header: ' + recipient_two + ', data: ' + recipient);
})

$('#modal_purchase_question').on('hide.bs.modal', function(event) {
    //var button = $(event.relatedTarget);
    //var recipient = button.data('whatever');
    //var recipient_two = button.parents('.card').find('h3').text();
    //console.log('whatever: '+recipient);

    console.log('hide.bs.modal');

    var modal = $(this);
    var inpText = modal.find('input[name="testInp"]').val(); //.css("background-color", "red");

    console.log(modal);

    $("div.card-body").text(inpText);
    //modal.find('.modal-title').text('Header: ' + recipient_two + ', data: ' + recipient);
})

/*
$.ajax({
        method: "POST",
        url: "localhost:8080",
        data: { name: "John", location: "Boston" }
    })
    .done(function(msg) {
        alert("Data Saved: " + msg);
	});
	*/