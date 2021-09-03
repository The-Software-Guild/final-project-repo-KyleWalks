function getBook() {
    var url = "http://openlibrary.org/search.json?title=";
    var search = $("#searchBook").val();
    search = search.replaceAll(" ", "+");
    
    // Format URL
    url = url + search;
    
    // Get JSON
    $.getJSON(url, function(response) {
        var matches = response["numFound"];
        if (matches === 0) {
            
        } else {
            $("#searchTable").css("display", "");
            var bookTitle = response["docs"][0]["text"][1];
            var bookKey = response["docs"][0]["key"];
            
            if ($("#contentRows") !== null)
                $("#contentRows").empty();

            const table = document.getElementById("contentRows");
                
            let row = table.insertRow();
            let name = row.insertCell(0);
            name.innerHTML = bookTitle;
            name.className = "bookTitle";
            
            let key = row.insertCell(1);
            key.innerHTML = bookKey;
            key.className = "bookKey";
            key.style.display = "none";
            
            row = row.insertCell();
            var btn = document.createElement('input');
            btn.type = "button";
            btn.className = "btn btn-primary";
            btn.onclick = function() { bookAdd(this); };
            btn.value = "Add";
            //let add = row.insertCell(1);
            row.appendChild(btn);

            row = table.rows[table.rows.length - 1];
            row = row.insertCell();
            btn = document.createElement('input');
            btn.type = "button";
            btn.className = "btn btn-primary";
            btn.onclick = function () { bookInfo(this); };
            btn.value = "Edit";
            //let add = row.insertCell(1);
            row.appendChild(btn);
        }
    });
}

function bookAdd(val) {
    var $title = $(val).closest("tr").find(".bookTitle").text();
    var $key = $(val).closest("tr").find(".bookKey").text();
    window.location = "/book-view?bookTitle=" + $title + "?bookKey=" + $key;
}

function getAuthor() {
    var url = "http://openlibrary.org/search/authors.json?q=";
}