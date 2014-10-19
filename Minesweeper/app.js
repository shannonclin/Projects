var easy = 8;
var spots = createEmptyArray(easy);
var numberOfMines = 10;
var nonMinesClicked = 0;

prepareBoard();

// Begins the game
function playNow() {
    $(".play-button").css({"visibility": "hidden"});
    $(".option-buttons").css({"visibility": "visible"});
    $("#play-again").attr("onclick", "showAlert()");
    refreshSettings();
}

function showAlert() {
    alert("You're in game!");
}

function refreshSettings() {
    nonMinesClicked = 0;
    $(".grid").attr("onclick", "showHidden(this.id)");
    $(".result").empty();
    clearBoard();
    spots = createEmptyArray(easy);
    populateArray(spots);
}

// Starts over
function resetNow() {
    refreshSettings(); 
}

function prepareBoard() {
    // Create the UI for minesweeper game
    $(document).ready(function() {
        $(".board").width(50 * easy).height(50 * easy);
        $(".board").css({"outline": "2px solid"});

        for (var i = 0; i < easy; i++) {
            for (var j = 0; j < easy; j++) {
                $(".board").append("<div id='" 
                    + i + j 
                    + "' onclick='null' class='grid'></div>");
            }
        }
    });
}

function clearBoard() {
    for (var i = 0; i < easy; i++) {
        for (var j = 0; j < easy; j++) {
            $("#" + i + j).empty();
        }
    }
}

function createEmptyArray(difficulty) {
    var emptyArray = []

    for (var i = 0; i < difficulty; i++) {
        emptyArray.push([]);
        for (var j = 0; j < difficulty; j++) {
            emptyArray[i].push(0);
        }
    }

    return emptyArray;
}

// Adds appropriate symbols to array
// Numbers for surrounding bombs and M for mine
function populateArray(emptyArray) {
	var counter = 0;
	
	while (counter != numberOfMines) {
		var row = Math.floor(Math.random()*emptyArray.length);
		var column = Math.floor(Math.random()*emptyArray[0].length);

		if (emptyArray[row][column] == 0) {
			emptyArray[row][column] = 'M';
            emptyArray = returnSurrounding(emptyArray, row, column);
			counter++;
		}
	}
}

// HELPER FUNCTION: Populate cells surrounding the mines
function returnSurrounding(minedArray, row, column) {
	var possibleRowIndex = [parseInt(row) - 1, parseInt(row), parseInt(row) + 1];
    var possibleColIndex = [parseInt(column) - 1, parseInt(column), parseInt(column) + 1];

	possibleRowIndex = possibleIndexes(possibleRowIndex);
    possibleColIndex = possibleIndexes(possibleColIndex);

    minedArray = fillSurrounding(minedArray, possibleRowIndex, possibleColIndex);
    return minedArray;
}

// HELPER FUNCTION: Removes any out-of-bounds
function possibleIndexes(possibleIndex) {
    var validPositions = [];
    console.log("yolo");
    console.log(possibleIndex);

    for (var i = 0; i < possibleIndex.length; i++) {
        if (possibleIndex[i] < 0 || possibleIndex[i] >= easy) {
            continue;
        } else {
            validPositions.push(parseInt(possibleIndex[i]));
        }
    }

    return validPositions;
}

// HELPER FUNCTION: Goes through possible indexes and counts the bombs
function fillSurrounding(minedArray, possibleRowIndex, possibleColIndex) {
    for (var i = 0; i < possibleRowIndex.length; i++) {
        for (var j = 0; j < possibleColIndex.length; j++) {
            if (minedArray[possibleRowIndex[i]][possibleColIndex[j]] != "M") {
                minedArray[possibleRowIndex[i]][possibleColIndex[j]] += 1;
            }
        }
    }
    
    return minedArray;
}

// Shows what is located there
function showHidden(id) {
    nonMinesClicked += 1;
    $("#" + id).text("" + spots[id[0]][id[1]]);
    $("#" + id).attr("onclick", "null");

    if (spots[id[0]][id[1]] == 0) {
        extendBlock(id);
    } else if (spots[id[0]][id[1]] == "M") {
        loseGame();
    } else if (nonMinesClicked == (Math.pow(easy, 2) - numberOfMines)) {
        winGame();
    }
}

function extendBlock(id) {
    var row = id[0];
    var column = id[1];

    var possibleRowIndex = [row - 1, row, row + 1];
    var possibleColIndex = [column - 1, column, column + 1];

    possibleRowIndex = possibleIndexes(possibleRowIndex);
    possibleColIndex = possibleIndexes(possibleColIndex);

    console.log(possibleRowIndex);
    console.log(possibleColIndex);

    possibleCells = createCellPos(possibleRowIndex, possibleColIndex);

    for (var i = 0; i < possibleCells.length; i++) {
        var currentRow = possibleCells[i][0];
        var currentCol = possibleCells[i][1];
        var currentID = "" + currentRow + currentCol;

        if (spots[currentRow][currentCol] == "M") {
            return;
        } 
        // else if ($("#" + currentID).text.is(':empty')) {
        //     showHidden(currentID);
        // }
    //     if (spots[currentRow][currentCol] != "M" &&
    //         ($("#" + currentID).text == null)) {
    //         nonMinesClicked += 1;
    //         $("#" + currentID).text("" + spots[currentRow][currentCol]);
    //         $("#" + currentID).attr("onclick", "null");

    //         if (spots[currentRow][currentCol] == 0) {
    //             extendBlock(currentID);
    //         }
    //     }
    }
}

function createCellPos(possibleRowIndex, possibleColIndex) {
    var cellHolder = [];
    for (var i = 0; i < possibleRowIndex.length; i++) {
        for (var j = 0; j < possibleColIndex.length; j++) {
            cellHolder.push([possibleRowIndex[i], possibleColIndex[j]]);
        }
    }

    return cellHolder;
}

function winGame() {
    $(".result").text("You win!");
    $("#play-again").attr("onclick", "playNow()");
}

function loseGame() {
    $(".result").text("You lose!");
    $("#play-again").attr("onclick", "playNow()");

    for (var i = 0; i < easy; i++) {
        for (var j = 0; j < easy; j++) {
            $("#" + i + j).text("" + spots[i][j]);
        }
    }
}

