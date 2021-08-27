const weeklyForm = document.querySelector(".js-weeklyForm"),
    addWeeklyBtn = document.querySelector(".addWeeklyElement"),
    weeklyList = document.querySelector(".js-weeklyList");

const addToDoBoxBtn = document.querySelector(".js-addToDoBoxBtn"),
    toDoFlexWrap = document.querySelector(".js-toDoFlexWrap");

const addToDoElementBtn = document.querySelector(".js");

const WEEKLY_LS = 'weeklies'; //LS는 local storage를 말함

let weeklies = [];
let toDoBoxes =[];

function addWeeklyElement() {
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
    const titleSection = document.createElement("div");
    const title = document.createElement("input");
    const addToDoElementBtn = document.createElement("button");
    const addIcon = document.createElement("i");
    const toDoList = document.createElement("ul");
    const newid = toDoBoxes.length+1;

    toDoList.classList.add("toDoList", "js-toDoList");
    title.classList.add("title");
    addIcon.classList.add("fas", "fa-plus");
    addToDoElementBtn.classList.add("js-addToDoElementBtn", "addToDoElementBtn");
    addToDoElementBtn.addEventListener("click", function(){handleAddToDoElement(newid)});
    addToDoElementBtn.appendChild(addIcon);
    titleSection.classList.add("titleSection");
    titleSection.appendChild(title);
    titleSection.appendChild(addToDoElementBtn);
    toDoBox.classList.add("toDoBox", "js-toDoBox");
    toDoBox.appendChild(titleSection);
    toDoBox.appendChild(toDoList);
    toDoBox.id = "toDoBox_" + newid.toString();
    toDoFlexWrap.appendChild(toDoBox);
    // toDoFlexWrap.insertBefore(toDoBox, toDoFlexWrap.firstChild);

    const toDoBoxData = {
        id: newid,
        title: title.textContent,
        toDoList: []
    };

    toDoBoxes.push(toDoBoxData);
}

function addToDoElement(id) {
    const toDoBox = document.querySelector("#toDoBox_" + id.toString()),
        toDoListComponent = toDoBox.querySelector(".js-toDoList");

    const li = document.createElement("li");
    const checkBox = document.createElement("input");
    const checkIcon = document.createElement("i");
    const input = document.createElement("input");
    const newToDoElementId = toDoBoxes[id-1]["toDoList"].length+1;

    checkBox.type = "checkbox";
    checkBox.value = newToDoElementId.toString();
    checkBox.appendChild(checkIcon);
    li.appendChild(checkBox);
    li.appendChild(input);
    li.id = newToDoElementId.toString();
    toDoListComponent.appendChild(li);

    const toDoElement ={
        id: newToDoElementId,
        toDoText: input.textContent
    };

    toDoBoxes[id-1]["toDoList"].push(toDoElement);
    console.log(toDoBoxes);


}

function handleAddWeeklyElement() {
    // event.preventDefault();
    addWeeklyElement();

}

function handleAddToDoBoxBtn() {
    createToDoBox();
}

function handleAddToDoElement(id) {
    addToDoElement(id);
}

function init() {
    addWeeklyBtn.addEventListener("click", handleAddWeeklyElement);
    addToDoBoxBtn.addEventListener("click", handleAddToDoBoxBtn);
}

init();