$(document).ready(function() {
    // Get book title in URL
    let urlParam = document.URL;
    urlParam = urlParam.substring(urlParam.indexOf('=') + 1);
    
    // Decode the title string
    var bookTitle = decodeURI(urlParam);
    
    var bookJson;
    let queryUrl = "";
    $.getJSON({
        
    });
});