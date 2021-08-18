const weeklyForm = document.querySelector(".js-weeklyForm"),
    addWeeklyBtn = document.querySelector(".addWeeklyElement"),
    weeklyList = document.querySelector(".js-weeklyList");

const addToDoBoxBtn = document.querySelector(".js-addToDoBoxBtn"),
    toDoListLayout = document.querySelector(".js-toDoListLayout");

const WEEKLY_LS = 'weeklies'; //LS는 local storage를 말함

let weeklies = [];
let toDoBoxes =[];

function createWeeklyElement() {
    const li = document.createElement("li");
    const checkBox = document.createElement("input");
    const checkIcon = document.createElement("i");
    const input = document.createElement("input");
    const newId = weeklies.length+1;
    checkBox.type = "checkbox";
    checkBox.value = newId.toString();
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

function createToDoBox() {
    const toDoBox = document.createElement("div");
    const toDoBoxTitleSection = document.createElement("div");
    const toDoBoxTitle = document.createElement("input");
    const addToDoElementBtn = document.createElement("button");
    const addIcon = document.createElement("i");
    const toDoList = document.createElement("ul");
    const newToDoBoxId = toDoBoxes.length+1;

    toDoList.classList.add("toDoList", "js-toDoList");
    toDoBoxTitle.classList.add("toDoBoxTitle");
    addIcon.classList.add("fas", "fa-plus");
    addToDoElementBtn.classList.add("addToDoElementBtn");
    addToDoElementBtn.appendChild(addIcon);
    toDoBoxTitleSection.classList.add("toDoBoxTitleSection");
    toDoBoxTitleSection.appendChild(toDoBoxTitle);
    toDoBoxTitleSection.appendChild(addToDoElementBtn);
    toDoBox.classList.add("toDoBox", "js-toDoBox");
    toDoBox.appendChild(toDoBoxTitleSection);
    toDoBox.appendChild(toDoList);
    toDoBox.id = newToDoBoxId.toString();
    toDoListLayout.insertBefore(toDoBox, toDoListLayout.firstChild);

    const toDoBoxData = {
        id: newToDoBoxId,
        text: toDoBoxTitle.textContent
    };

    toDoBoxes.push(toDoBoxData);
}


function handleAddWeeklyElement() {
    // event.preventDefault();
    createWeeklyElement();

}

function handleAddToDoBoxBtn() {
    createToDoBox();
}

function init() {
    addWeeklyBtn.addEventListener("click", handleAddWeeklyElement);
    addToDoBoxBtn.addEventListener("click", handleAddToDoBoxBtn);
}

init();