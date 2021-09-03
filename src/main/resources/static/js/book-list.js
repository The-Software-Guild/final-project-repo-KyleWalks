function reviewBook(val) {
    let bookId = val.parent().parent().find("#id").text();
    
    window.location = "/book-review?bookId=" + bookId;
}

function deleteBook(val) {
    let bookId = val.parent().parent().find("#id").text();
    bookId = parseInt(bookId);

    window.location = "/delete-book?bookId=" + bookId;
}