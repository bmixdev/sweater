$('#editMessage').on('show.bs.modal', function(event) {
    var button = $(event.relatedTarget);
    var messageId = button.data('message-id');
    var tag = button.data('tag');
    var msgText = button.data('msg-text');
    var msgAuthor = button.data('msg-author');
    //var recipient_two = button.parents('.card').find('h3').text();

    console.log('show.bs.modal');

    //console.log('messageId: ' + messageId);

    var modal = $(this);
    modal.find('.modal-body input[name="messageId"]').val(messageId); //.css("background-color", "red");
    modal.find('.modal-body input[id="tagMessage"]').val(tag); //.css("background-color", "red");
    modal.find('.modal-body input[id="textMessage"]').val(msgText); //.css("background-color", "red");
    modal.find('.modal-body input[id="authorMessage"]').val(msgAuthor); //.css("background-color", "red");

    //inputTmp.css("background-color", "red");
    // inputTmp.inpText = "1111";

  //  modal.find('.modal-title').text('Header: ' + recipient_two + ', data: ' + recipient);
})
/*
$('#modal_purchase_question').on('hide.bs.modal', function(event) {
    //var button = $(event.relatedTarget);
    //var recipient = button.data('whatever');
    //var recipient_two = button.parents('.card').find('h3').text();
    //console.log('whatever: '+recipient);

    console.log('hide.bs.modal');

    var modal = $(this);
    var inpText = modal.find('input[name="testInp"]').val(); //.css("background-color", "red");

    console.log(modal);

     var form = $(this).find('form');
     form[0].reset();

    $("div.card-body").text(inpText);
    //modal.find('.modal-title').text('Header: ' + recipient_two + ', data: ' + recipient);
})

*/

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