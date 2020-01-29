function openCreateTournamentBox() {
    $("#openCreateTournamentBox").show();
}

function createTournament() {
    var TournamentName = $('#tournamentName').val();
    var Sets = $('#Sets').val();
    var PlayerCount = $('#playerCount').val();

    var loginObj = { "Tournament": TournamentName, "sets": Sets, "playerscount": PlayerCount, "Id": document.cookie }
    console.log(loginObj)
    $.ajax({
        type: "POST",
        url: "managerLogin",
        data: loginObj
    })
    $("#viewTournament").prop("disabled", false);
}
function viewTournament() {

    var loginObj = { "Id": document.cookie }
    console.log(loginObj)
    $.ajax({
        type: "GET",
        url: "managerLogin",
        data: loginObj,
        success: function (response) {
            var resp = JSON.parse(response);
            console.log(resp);
            showTournament(resp);


        },
        error: function (error) {
            alert("InCorrect Id");
        }
    })

}
function showTournament(resp) {
    for (var i = 1; i <= resp.length; i++) {
        const showTournament = ` <div class="showTournaments">
                                        <div>
                                            <div id="tName${i}">
                                                ${resp[i]}

                                                <button id="viewTournamentDetails${i}" onclick="viewExtraDetails('${resp[i]}',${i})">View Details</button>
                                                <button class = "selectMatch"onclick="selectMatch('${resp[i]}',${i})">schedule matches</button>
                                                <div id="ExtraDetails${i}"> </div> 
                                            </div>

                                        </div>
                                    </div>`
        $("#openViewTournamentBox").append($.parseHTML(showTournament))
    }
    $(".selectMatch").prop("disabled", true);
    $("#viewTournament").prop("disabled", true);
}
roundNo = 1;
function viewTournamentDetails(resp, i) {
    var TournamentNames = resp;
    //var roundNo = $('#roundNo' + String(i)).val();

    var loginObj = { "Tournament": TournamentNames, "Id": document.cookie, "RoundNum": roundNo }
    console.log(loginObj)
    $.ajax({
        type: "GET",
        url: "Schedule",
        data: loginObj,
        success: function (response) {
            var res = JSON.parse(response);
            console.log(res);
            viewExtraDetails(res, i);


        },
        error: function (error) {
            alert("InCorrect Id");
        }
    })
}
function viewExtraDetails(res, i) {
    console.log(res)
    console.log(i)

    const extraDetails = ` <div> <div id="a${i}"></div></div>`
    $("#ExtraDetails" + String(i)).append($.parseHTML(extraDetails))
    $(".selectMatch").prop("disabled", false);

}

function selectMatch(TournamentName, i) {
    console.log(TournamentName)
    console.log("MatchNum" + String(i))
    var a = document.getElementById("MatchNum" + String(i));
    console.log(a)


    var loginObj = { "Tournament": TournamentName, "Id": document.cookie, "RoundNum": roundNo }
    console.log(loginObj)
    $.ajax({
        type: "GET",
        url: "Schedule",
        data: loginObj,
        success: function (response) {
            var res = JSON.parse(response);
            console.log(res)


            for (var j = 0; j < res.length; j++) {
                console.log(j)
                console.log(res[j].player1);
                const scheduleCard = `<div class  = "scheduleCard"  style="border:2px solid black">
                                        <div class="imageVS">
                                        <span id="playerA${j}">PlayerA:${res[j].player1}<img src="images/propic.png"></span>
                                            <span><b>VS</b></span>
                                        <span id="playerB${j}"><img src="images/propic.png">PlayerA:${res[j].player2}</span>
                                        </div>
                                        <div
                                            <div class ="winsets">WinSets :<input id ="winsetsA${j}" type="text">WinSets :<input id ="winsetsB${j}" type="text"></div>
                                        </div>
                                            <button id = submiMatch${j}  onclick="submitSingleMatch(${j},${res[j].player1},${res[j].player2})">Submit match</button>
                                        </div>`
                $("#a" + String(i)).append($.parseHTML(scheduleCard))
            }

        },
        error: function (error) {
            alert("InCorrect Id");
        }
    })
    $(".selectMatch").prop("disabled", true);
}

function submitSingleMatch(j,player1,player2){
    console.log(player1)
    var matchResult;
   var winsets_A = $("#winsetsA"+String(j)).val();
   var winsets_B = $("#winsetsB"+String(j)).val();
   var totalSets = winsets_A+winsets_B;console.log(totalSets)
   if(winsets_A > winsets_B)
   {
       matchResult = player1;
   }
   else{
       matchResult = player2;
   }
   var loginObj = { "winsets_a": winsets_A, "winsets_b": winsets_B, "matchresult": matchResult,"playerA": player1,"playerB": player2,"Id": document.cookie }
   console.log(loginObj)
   $.ajax({
       type: "POST",
       url: "matchResult",
       data: loginObj
   })
}