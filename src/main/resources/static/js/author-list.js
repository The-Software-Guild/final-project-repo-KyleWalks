$(document).ready(function() {
    //$("#website").attr("href", $("#website").text());
});

function viewAuthor(val) {
    let authorId = val.parent().parent().find("#id").text();

    window.location = "/view-author?authorId=" + authorId;
}

function deleteAuthor(val) {
    let authorId = val.parent().parent().find("#id").text();
    authorId = parseInt(authorId);

    window.location = "/delete-author?authorId=" + authorId;
}