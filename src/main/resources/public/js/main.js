
let active_id = "#calendar-container";

$(document).ready(function () {

    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    $(".sidebar-item").on("click", function(e){
        let foo = e.currentTarget.attributes.target.value;
        if(foo != active_id){
            $(active_id).toggle();
            $(foo).toggle();
            active_id = foo;
        }
    });
});