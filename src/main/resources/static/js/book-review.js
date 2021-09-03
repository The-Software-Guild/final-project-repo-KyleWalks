$(document).ready(function() {
    console.log($("#reviewVal").val());
    $("#review").val($("#reviewVal").val());
});

function submitReview() {
    $("#reviewVal").val(document.getElementById('review').value);
    $("#bookForm").submit();
}