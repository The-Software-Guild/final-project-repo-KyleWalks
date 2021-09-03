$(document).ready(function() {
    // Get book title in URL
    let urlParam = document.URL;
    urlParam = urlParam.substring(urlParam.indexOf('=') + 1);
    
    // Decode the title string
    var bookTitle = urlParam.substring(0, urlParam.indexOf('?'));
    bookTitle = decodeURI(bookTitle);
    
    // Get book key in URL
    urlParam = urlParam.substring(urlParam.indexOf('?') + 1);
    urlParam = urlParam.substring(urlParam.indexOf('=') + 1);
    
    // Decode key
    var bookKey = decodeURI(urlParam);
    bookKey = bookKey + ".json";
    
    let queryUrl = "https://openlibrary.org" + bookKey;
    $.getJSON(queryUrl).done(function(jsonObj) {
        populatePageBook(jsonObj);
    });
});

function populatePageBook(bookJson) {
    console.log(bookJson);
    // Cover image
    let covers = bookJson["covers"].length;
    let count = 5;
    for (let i = 0; (i < covers) && (i < count); i++) {
        let coverImgId = bookJson["covers"][i];
        
        if (coverImgId === -1) {
            count++;
            continue;
        }
        
        coverImgId = coverImgId + "-M.jpg";
        let coverUrl = "http://covers.openlibrary.org/b/id/" + coverImgId;
        
        let img = document.createElement('img');
        img.src = coverUrl;
        
        document.getElementById("images").appendChild(img);
    }
    $("#cover").val(bookJson["covers"][0]);
    
    // Title
    $("#bookTitle").html(bookJson["title"]);
    $("#titleVal").val(bookJson["title"]);
    // Description
    let desc = bookJson["description"]["value"];

    if (desc === undefined)
        desc = bookJson["description"];
    if (desc.includes("---"))
        desc = desc.substring(0, desc.indexOf("---"));
    else if (desc.includes("([source"))
        desc = desc.substring(0, desc.indexOf("([source"));
    
    console.log(desc.indexOf("([source"));
    console.log(desc.substring(0, desc.indexOf("([source")));
    console.log(desc);
    
    $("#bookDesc").text(desc);
    $("#descVal").val(desc);

    let subjects = "None";
    if (Object.keys(bookJson).includes("subjects"))
        subjects = bookJson["subjects"].join(", \n");
    
    $("#subjects").html(subjects);
    $("#subjVal").val(subjects);
    
    let subjectPlaces = "None";
    if (Object.keys(bookJson).includes("subject_places"))
        subjectPlaces = bookJson["subject_places"].join(", \n");

    $("#subjectPlaces").html(subjectPlaces);
    $("#subjPlacesVal").val(subjectPlaces);
    
    let subjectPeople = "None";
    if (Object.keys(bookJson).includes("subject_people"))
        subjectPeople = bookJson["subject_people"].join(", \n");

    $("#subjectPeople").html(subjectPeople);
    $("#subjPeopleVal").val(subjectPeople);
    
    let subjectTimes = "None";
    if (Object.keys(bookJson).includes("subject_times"))
        subjectTimes = bookJson["subject_times"].join(", \n");

    $("#subjectTimes").html(subjectTimes);
    $("#subjTimesVal").val(subjectTimes);
    // Get author JSON
    var authorKey = bookJson["authors"][0]["author"]["key"];
    authorKey = authorKey + ".json";
    let queryUrl = "https://openlibrary.org" + authorKey;
    $.getJSON(queryUrl).done(function(jsonObj) {
        populatePageAuthor(jsonObj);
    });
}

function populatePageAuthor(authorJson) {
    $("#authorName").text(authorJson["name"]);
    $("#authorVal").val(authorJson["name"]);
    
    $("#birthDate").text(authorJson["birth_date"]);
    
    let authorLink = authorJson["links"][0]["url"];
    $("#authorLink").text(authorLink);
    $("#authorLink").attr("href", authorLink);
}

function addBook() {
    $("#bookForm").submit();
}