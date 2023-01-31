// logged in on a 45 question test

function downloadQuestionsAsJson() {
    var questions = [];
    for (var i = 0; i < 45; i++) {
        var question = APP.oppgavesett.get_question(i);
        questions.push(question);
    }

    var data = JSON.stringify(questions);
    var fileName = "questions.json";
    var blob = new Blob([data], {type: "application/json"});
    var url = URL.createObjectURL(blob);

    var a = document.createElement("a");
    a.href = url;
    a.download = fileName;
    a.click();
    URL.revokeObjectURL(url);
}

function simulateClicks() {
    var i = 0;

    function next() {
        if (i < 40) {
            var numList = [1, 2, 4, 8];
            var randomNum = numList[Math.floor(Math.random() * numList.length)];
            var divId = "answer_div_" + randomNum;
            var div = document.querySelector("#" + divId);
            div.click();

            var nextButton = document.querySelector("#next");
            nextButton.click();

            // Add delay before clicking again
            setTimeout(next, 3500);
            i++;
        } else {
            downloadQuestionsAsJson();
        }
    }

    next();
}

simulateClicks();