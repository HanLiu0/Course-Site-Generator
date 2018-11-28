// DATA TO LOAD
var startHour;
var endHour;
var daysOfWeek;
var officeHours;
var undergradTAs;

function buildSyllabus() {
    var dataFile = "./js/SyllabusData.json";
    loadSyllabusData(dataFile);
}

function loadSyllabusData(jsonFile) {
    $.getJSON(jsonFile, function (json) {
	initDays(json);
        addCourseInfo(json);
        addLectureInfo(json);
        addInstructorInfo(json);
        addTAs(json.grad_tas, $("#grad_tas"));
        addTAs(json.undergrad_tas, $("#undergrad_tas"));
        addOfficeHours(json);
        addLabs(json);
        addRecitations(json);
        addTextbookInfo(json);
        addCourseComponentsInfo(json);
        addGradingBreakdownInfo(json);
        addGradingNoteInfo(json);
        addAcademicDishonestyInfo(json);
        addSpecialAssistanceInfo(json);
    });
}

function initDays(data) {
    // GET THE START AND END HOURS
    startHour = parseInt(data.startHour);
    endHour = parseInt(data.endHour);

    // THEN MAKE THE TIMES
    daysOfWeek = new Array();
    daysOfWeek[0] = "MONDAY";
    daysOfWeek[1] = "TUESDAY";
    daysOfWeek[2] = "WEDNESDAY";
    daysOfWeek[3] = "THURSDAY";
    daysOfWeek[4] = "FRIDAY";    
}
function addCourseInfo(data) {
    var courseDescription = $("#syllabus_course_description");
    courseDescription.html(data.courseDescription);
    
    var courseTopicsList = $("#syllabus_course_topics");
    for (var i = 0; i < data.courseTopics.length; i++) {
        var text = "<li>";
        text += data.courseTopics[i];
        text += "</li>";
        courseTopicsList.append(text);
    }
    
    var prerequisites = $("#syllabus_prerequisites");
    prerequisites.html(data.prerequisites);
    
    var courseOutcomesList = $("#syllabus_course_outcomes");
    for (var i = 0; i < data.courseOutcomes.length; i++) {
        var text = "<li>";
        text += data.courseOutcomes[i];
        text += "</li>";
        courseOutcomesList.append(text);
    }
}
function addLectureInfo(data) {
    var lectureRow = $("#lecture_row");
    for (var i = 0; i < data.lectures.length; i++) {
        var lec = data.lectures[i];
        var text = "<td class='rec_" + (i%2) + " rec_cell'>"
                 + "<strong>" + lec.section + "</strong><br />"
                 + lec.days + "<br />"
                 + lec.time + "<br />"
                 + lec.room + "<br />"
                 + "<br /></td>";
         lectureRow.append(text);
    }
}
function addInstructorInfo(data) {
    var instructorData = $("#instructor_data");
    var instructor = data.instructor;
    var text = "<a href='" + instructor.link + "'>" + instructor.name + "</a><br />"
                + "<strong>" + instructor.email + "</strong><br />"
                + instructor.room + "<br />"
                + "<strong>Office Hours:</strong><br />"
                + "<table>";
    for (var i = 0; i < instructor.hours.length; i++) {
        var officeHours = instructor.hours[i];
        text += "<tr><td><strong>--" + officeHours.day + "</td><td class='instructor_time'>" + officeHours.time + "</td></tr>";
    }
    text += "</table>";
    instructorData.append(text);
    
    var instructorPhoto = $("#instructor_photo");
    text = "<img src='" + instructor.photo + "' alt='" + instructor.name + "' + class='instructor_photo' />";
    instructorPhoto.append(text);
}
function addTextbookInfo(data) {
    var textbookData = $("#textbook_data");
    for (var i = 0; i < data.textbooks.length; i++) {
        var textbook = data.textbooks[i];
        var text = "<a href='" + textbook.link + "'><img class='textbook_image' width='100' height='125' src='" 
                + textbook.photo + "' /></a><a href='" + textbook.link + "'><em>" + textbook.title
                + "</em></a><br />"
                + "by ";
        for (var j = 0; j < textbook.authors.length; j++) {
            var author = textbook.authors[j];
            if (j > 0)
                text += ", ";
            text += "<strong>" + author + "</strong>";
        }
        text += "<br />";
        text += "Published by " + textbook.publisher + ", " + textbook.year;
        text += "<br clear='both' /><br /><br />";
        textbookData.append(text);
    }
}
function addCourseComponentsInfo(data) {
    var courseComponents = $("#course_components");
    for (var i = 0; i < data.courseComponents.length; i++) {
        var component = data.courseComponents[i];
        var text = "<li><strong>" + component.name + "</strong> - " + component.description + "<br /><br /></li>";
        courseComponents.append(text);
    }
}
function addGradingBreakdownInfo(data) {
    var gradingBreakdown = $("#grading_breakdown");
    for (var i = 0; i < data.courseComponents.length; i++) {
        var component = data.courseComponents[i];
        var text = "<tr><td>" + component.name + "</td><td align='right'>" + component.weight + "%</td><td></td></tr>";
        gradingBreakdown.append(text);
    }    
    text = "<tr class='grading_total'><td></td><td align='right'><strong>100 %</strong></td></tr>";
    gradingBreakdown.append(text);
}
function addGradingNoteInfo(data) {
    var gradingNote = $("#grading_note");
    gradingNote.append(data.gradingNote);
}
function addAcademicDishonestyInfo(data) {
    var academicDishonesty = $("#academic_dishonesty");
    academicDishonesty.append(data.academicDishonestyNote);
}
function addSpecialAssistanceInfo(data) {
    var specialAssistance = $("#special_assistance");
    specialAssistance.append(data.specialAssistanceNote);
}
function addTAs(taArray, node) {
    var tasPerRow = 4;
    var numTAs = taArray.length;
    for (var i = 0; i < taArray.length; ) {
        var text = "";
        text = "<tr>";
        for (var j = 0; j < tasPerRow; j++) {
            text += buildTACell(i, numTAs, taArray[i]);
            i++;
        }
        text += "</tr>";
        node.append(text);
    }
}
function buildTACell(counter, numTAs, ta) {
    if (counter >= numTAs)
        return "<td></td>";

    var name = ta.name;
    var abbrName = name.replace(/\s/g, '');
    var email = ta.email;
    var text = "<td class='tas'><img width='100' height='100'"
                + " src='./images/tas/" + abbrName + ".jpg' "
                + " alt='" + name + "' /><br />"
                + "<strong>" + name + "</strong><br />"
                + "<span class='email'>" + email + "</span><br />"
                + "<br /><br /></td>";
    return text;
}
function addOfficeHours(data) {
    for (var i = startHour; i < endHour; i++) {
        // ON THE HOUR
        var textToAppend = "<tr>";
        var amPm = getAMorPM(i);
        var displayNum = i;
        if (i > 12)
            displayNum = displayNum-12;
        textToAppend += "<td>" + displayNum + ":00" + amPm + "</td>"
                    + "<td>" + displayNum + ":30" + amPm + "</td>";
        for (var j = 0; j < 5; j++) {
            textToAppend += "<td id=\"" + daysOfWeek[j]
                                + "_" + displayNum
                                + "_00" + amPm
                                + "\" class=\"open\"></td>";
        }
        textToAppend += "</tr>"; 
        
        // ON THE HALF HOUR
        var altAmPm = amPm;
        if (displayNum === 11)
            altAmPm = "pm";
        var altDisplayNum = displayNum + 1;
        if (altDisplayNum > 12)
            altDisplayNum = 1;
                    
        textToAppend += "<tr>";
        textToAppend += "<td>" + displayNum + ":30" + amPm + "</td>"
                    + "<td>" + altDisplayNum + ":00" + altAmPm + "</td>";
            
        for (var j = 0; j < 5; j++) {
            textToAppend += "<td id=\"" + daysOfWeek[j]
                                + "_" + displayNum
                                + "_30" + amPm
                                + "\" class=\"open\"></td>";
        }
        
        textToAppend += "</tr>";
        var cell = $("#office_hours_table");
        cell.append(textToAppend);
    }
    
    // NOW SET THE OFFICE HOURS
    for (var i = 0; i < data.officeHours.length; i++) {
	var id = data.officeHours[i].day + "_" + data.officeHours[i].time;
	var name = data.officeHours[i].name;
	var cell = $("#" + id);
	if (name === "Lecture") {
	    cell.removeClass("open");
	    cell.addClass("lecture");
	    cell.html("Lecture");
	}
        else if (name === "Recitation") {
            cell.removeClass("open");
            cell.addClass("recitation");
            cell.html("Recitation");
        }
	else {
	    cell.removeClass("open");
	    cell.addClass("time");
	    cell.append(name + "<br />");
	}
    }
}
function getAMorPM(testTime) {
    if (testTime >= 12)
        return "pm";
    else
        return "am";
}
function addLabs(data) {
    if (data.labs.length > 0) {
        $("#labs_heading").append("LABS");
        addLabsOrRecs(data.labs, "#labs_table", "lab");
    }
}
function addRecitations(data) {
    if (data.recitations.length > 0) {
        $("#recitations_heading").append("RECITATIONS");
        addLabsOrRecs(data.recitations, "#recitations_table", "rec");
    }
}
function addLabsOrRecs(tableData, tableId, cellClass) {
    var table = $(tableId);
    var rowParity = 0;
    for (var i = 0; i < tableData.length; i+=2) {
        var text = "<tr>";
        var section = tableData[i];
        var cellParity = rowParity;
        text += buildLabOrRecCell(cellParity, section, cellClass);
        cellParity++;
        cellParity %= 2;
        if ((i+1) < tableData.length) {
            section = tableData[i+1];
            text += buildLabOrRecCell(cellParity, section, cellClass);
        }
        else
            text += "<td></td>";
        text += "</tr>";
        table.append(text);
        rowParity++;
        rowParity %= 2;
    }
}
function buildLabOrRecCell(classNum, cellData, cellClass) {
    var text = "<td class='" + cellClass + "_" + classNum + "'>"
                + "<table><tr><td valign='top' class='" + cellClass + "_cell'>" 
                + cellData.section + "<br />"
                + cellData.day_time + "<br />"
                + cellData.location + "<br /></td></tr>"
                + "<tr>";
    
    // LAB/REC TA #1
    text += "<td class='ta_cell'><strong>Supervising TA</strong><br />";
    if (cellData.ta_1 != "none")
        text += "<img src='./images/tas/" 
            + cellData.ta_1.replace(/\s/g, '')
            + ".jpg' width='100' height='100' />"
            + "<br clear='both' />"
            + "(" + cellData.ta_1 + ")<br />";
    else
        text += "TBA";
    text += "</td>";
    
    // LAB/REC TA #2
    text += "<td class='ta_cell'><strong>Supervising TA</strong><br />";
    if (cellData.ta_2 != "none")
        text += "<img src='./images/tas/" 
            + cellData.ta_2.replace(/\s/g, '')
            + ".jpg' width='100' height='100' />"
            + "<br clear='both' />"
            + "(" + cellData.ta_2 + ")<br />";            
    else
        text += "TBA";
    text += "</table></td>";
    return text;
}