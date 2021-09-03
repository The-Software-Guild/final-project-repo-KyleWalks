$(document).ready(function() {
    // Get book title in URL
    let urlParam = document.URL;
    urlParam = urlParam.substring(urlParam.indexOf('=') + 1);
    
    // Decode key
    var authorKey = decodeURI(urlParam);
    authorKey = authorKey + ".json";
    
    let queryUrl = "https://openlibrary.org/authors/" + authorKey;
    $.getJSON(queryUrl).done(function(jsonObj) {
        populatePageBook(jsonObj);
    });
});

function populatePageBook(authorJson) {
    // Cover image
    let coverImgId = authorJson["photos"][0];

    coverImgId = coverImgId + "-M.jpg";
    let coverUrl = "http://covers.openlibrary.org/a/id/" + coverImgId;

    let img = document.createElement('img');
    img.src = coverUrl;

    document.getElementById("images").appendChild(img);
    
    // Title
    $("#authorName").html(authorJson["name"]);
    $("#nameVal").val(authorJson["name"]);
    // Description
    let bio = authorJson["bio"]["value"];
    
    $("#bio").text(bio);
    $("#bioVal").val(bio);

    let birthdate = authorJson["birth_date"];
    
    $("#birthDate").text(birthdate);
    $("#birthdateVal").val(birthdate);

    let authorLink = authorJson["links"][0]["url"];
    $("#authorLink").text(authorLink);
    $("#authorLink").attr("href", authorLink);
    $("#authorLinkVal").val(authorLink);
    
    let alternateNames = authorJson["alternate_names"].join(", ");
    $("#alternateNames").html(alternateNames);
    $("#altNameVal").val(alternateNames);
}

function addAuthor() {
    $("#authorForm").submit();
}