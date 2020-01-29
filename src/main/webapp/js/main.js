var inatiator = 4;
var r = 0;
var m = inatiator;



function respo(info) {
    if (info !== null) {
        built(info, r, m);
        console.log(r + " " + m)
        r = m;

        if (r >= 100) {
            console.log(r)
            r = 0;
            m = inatiator;
            alert("new call")
            load()
        }
        m = m + inatiator;
        console.log(r + " " + m)
        var windowheight = window.innerHeight;
        var scrollyheight = window.scrollY;
        var bodyoffsetHeight = document.body.offsetHeight;

        console.log(windowheight)
        console.log(scrollyheight)
        console.log(bodyoffsetHeight)
        console.log((windowheight + scrollyheight) >= bodyoffsetHeight)

        if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {

            respo(info)

        }

        window.onscroll = function() {
            if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
                // you're at the bottom of the page

                respo(info)

            }
        };
    }
    else {
        alert("no data")
    }

}


function load() {
    var request = new XMLHttpRequest();
    console.log(request)
    url = callURL();
    console.log(url)
    request.open('GET', url, true)
    request.onload = function () {
        var data = JSON.parse(this.response);
        var info = data;
        console.log(info);

        if (r < info.length) {
            respo(info)
        }
        return(info)
    }
    request.send()


}

function callURL() {
    var url = 'http://localhost:8080/Table2Tennis/Players'
    return url
}
