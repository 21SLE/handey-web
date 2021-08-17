const weeklyForm = document.querySelector(".js-weeklyForm"),
    addWeeklyBtn = document.querySelector(".addWeeklyElement"),
    weeklyList = document.querySelector(".js-weeklyList");

const addToDoElementBtn = document.querySelector(".addToDoElement"),
    toDoList = document.querySelector(".js-toDoList");

const WEEKLY_LS = 'weeklies'; //LS는 local storage를 말함

let weeklies = [];

function createWeeklyElement() {
    const li = document.createElement("li");
    const checkBox = document.createElement("input");
    const checkIcon = document.createElement("i");
    const input = document.createElement("input");
    const newId = weeklies.length+1;
    checkBox.type = "checkbox";
    checkBox.value = newId.toString();
    checkBox.id = "checkbox-id";
    // checkIcon.classList.add("fas", "fa-check");
    checkBox.appendChild(checkIcon);
    li.appendChild(checkBox);

    li.appendChild(input);
    li.id = newId.toString();
    weeklyList.appendChild(li);
    const weeklyElement = {
        id: newId,
        text: input.textContent
    };
    weeklies.push(weeklyElement);
}



function handleAddWeeklyElement() {
    // event.preventDefault();
    createWeeklyElement();

}

function init() {

    addWeeklyBtn.addEventListener("click", handleAddWeeklyElement);
}

init();