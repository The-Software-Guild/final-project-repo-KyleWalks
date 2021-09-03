$(document).ready(function() {
    // Get book title in URL
    let urlParam = document.URL;
    urlParam = urlParam.substring(urlParam.indexOf('=') + 1);
    
    // Decode key
    var authorKey = decodeURI(urlParam);
    authorKey = authorKey + ".json";
    
    let queryUrl = "https://openlibrary.org/authors/" + authorKey;
    $.getJSON(queryUrl).done(function(jsonObj) {
        populatePageAuthor(jsonObj);
    });
});

function populatePageAuthor(authorJson) {
    console.log(authorJson);
    if (!Object.keys(authorJson).includes("birth_date")) {
        alert("No details found.");
        window.location = "/home";
    }
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
    if (bio === undefined)
        bio = authorJson["bio"];
    if (bio.includes("[1]"))
        bio = bio.substring(0, bio.indexOf("[1]"));
    if (bio.includes("([Sour"))
        bio = bio.substring(0, bio.indexOf("([Sour"));
    
    $("#bio").text(bio);
    $("#bioVal").val(bio);

    let birthdate = authorJson["birth_date"];
    
    $("#birthDate").text(birthdate);
    $("#birthdateVal").val(birthdate);

    let authorLink = "";
    if(Object.keys(authorJson).includes("links"))
        authorLink = authorJson["links"][0]["url"];
    else
        authorLink = authorJson["wikipedia"];
    
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