function setPicker(){
    $( "#datepicker" ).datepicker(
        { dateFormat: 'yy-mm-dd',
            onSelect: function(d,i) {
                if (d !== i.lastVal) {
                    reloadPageForDateSelection();
                }
            }
        });
};

function getRequestParam(p){
    return (window.location.search.match(new RegExp('[?&]' + p + '=([^&]+)')) || [, null])[1];
};

function setInitialDate(){
    var requestDate = getRequestParam('date');
    if(requestDate == null){
        requestDate = new Date();
    }else{
        requestDate = formatDate(requestDate);
    }
    $('#datepicker').datepicker('setDate', requestDate);

};

function reloadPageForDateSelection(){
    var selectedDate = document.getElementById('datepicker').value;
    var redirectLink = window.location.protocol + "//" + window.location.host + window.location.pathname + '?date=' + selectedDate;
    console.log('Redirecting to: ' + redirectLink);
    window.location.href = redirectLink;
};

function formatDate(input) {
    var dateFormat = 'yyyy-mm-dd';
    var parts = input.match(/(\d+)/g),
        i = 0, fmt = {};
    dateFormat.replace(/(yyyy|dd|mm)/g, function(part) { fmt[part] = i++; });

    return new Date(parts[fmt['yyyy']], parts[fmt['mm']]-1, parts[fmt['dd']]);
};

function startReservation(roomId){
    $("#reservationRoomId").val(roomId);
    $("#reservationDatepicker").val($("#datepicker").val());
    $('#reservationModal').modal('show');
}
function reserveRoom(){
    var request ={
        "roomId": $("#reservationRoomId").val(),
        "guestId": $("#reservationGuestId").val(),
        "dateString": $("#reservationDatepicker").val()
    };
    console.log("Start reservation request");
    console.log(request);
    $.ajax({
        type: "POST",
        url: "reservations",
        data: JSON.stringify(request), // serializes the form's elements.
        dataType: 'json',
        contentType:"application/json; charset=utf-8",
        success: function(data)
        {
            if(data.status){
                $('#reservationModal').modal('hide');
                reloadPageForDateSelection();
            }
        }
    });

}function createGuest(){
    var request={
        "id": $("#id").val(),
        "firstName":$("#firstName").val(),
        "lastName":$("#lastName").val(),
        "emailAddress":$("#email").val(),
        "address":$("#address").val(),
        "country":$("#country").val(),
        "state":$("#state").val(),
        "phoneNumber":$("#phone").val()
    };
    console.log("Start reservation request");
    console.log(request);
    $.ajax({
        type: "POST",
        url: "guests",
        data: JSON.stringify(request), // serializes the form's elements.
        dataType: 'json',
        contentType:"application/json; charset=utf-8",
        success: function(data)
        {
            if(data!=null){
                $('#registerGuestModal').modal('hide');
                reloadPageForDateSelection();
            }
        }
    });

}
$(document).ready(function(){
    setPicker();
    setInitialDate();
});