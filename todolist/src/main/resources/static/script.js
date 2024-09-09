const todoInput = document.querySelector(".todo-input");
const todoButton = document.querySelector(".todo-button");
const todoList = document.querySelector(".todo-list");
const filterOption = document.querySelector(".filter-todo");

document.addEventListener("DOMContentLoaded", () => {
    getLocalTodos();
    document.getElementById("todo-form").addEventListener("submit", addTodo);
    todoList.addEventListener("click", deleteCheck);
    filterOption.addEventListener("change", filterTodo);
});

function addTodo(event) {
    event.preventDefault();
    const todoText = todoInput.value.trim();

    if (todoText === "") return; //for returning empty todo

    fetch('/api/todo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ title: todoText, done: false })
    })

    .then(response => response.json())
    .then(todo => {
        const todoDiv = document.createElement("div");
        todoDiv.classList.add("todo");
        todoDiv.id = todo.id; //set id to match the backend ID
        
        const newTodo = document.createElement("li");
        newTodo.innerText = todo.title;
        newTodo.classList.add("todo-item");
        todoDiv.appendChild(newTodo);

        const completedButton = document.createElement("button");
        completedButton.innerHTML = '<i class="fas fa-check-circle"></i>';
        completedButton.classList.add("complete-btn");
        todoDiv.appendChild(completedButton);

        const trashButton = document.createElement("button");
        trashButton.innerHTML = '<i class="fas fa-trash"></i>';
        trashButton.classList.add("trash-btn");
        todoDiv.appendChild(trashButton);


        if (todo.done) {
            todoDiv.classList.add("completed");
        }

        todoList.appendChild(todoDiv);
        todoInput.value = "";
    });
}

function deleteCheck(e) {
    const item = e.target;

    if (item.classList[0] === "trash-btn") {
        const todo = item.parentElement;
        const todoId = todo.id; // Get the ID for delete

        fetch(`/api/todo/${todoId}`, {
            method: 'DELETE'
        })
        .then(() => {
            todo.classList.add("slide");
            todo.addEventListener("transitionend", () => {
                todo.remove();
            });
        })
    }

    if (item.classList[0] === "complete-btn") {
        const todo = item.parentElement;
        const todoId = todo.id;
        const todoTitle = todo.querySelector(".todo-item").innerText;
        const isCompleted = todo.classList.toggle("completed");

        fetch(`/api/todo/${todoId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ title: todoTitle, done: isCompleted })
        })
    }
}
//Fetch from backend
function getLocalTodos() {
    fetch('/api/todo')
        .then(response => response.json())
        .then(todos => {
            // Check if no todos
            if (todos.length === 0) {
                const message = document.createElement("p");
                message.innerText = "No todos available";
                todoList.appendChild(message);
                return;
            }

            // Display todos
            todos.forEach(todo => {
                const todoDiv = document.createElement("div");
                todoDiv.classList.add("todo");
                todoDiv.id = todo.id; // Set ID to match backend ID

                const newTodo = document.createElement("li");
                newTodo.innerText = todo.title;
                newTodo.classList.add("todo-item");
                todoDiv.appendChild(newTodo);

                if(todo.done){
                    todoDiv.classList.add("completed");
                }

                const completedButton = document.createElement("button");
                completedButton.innerHTML = '<i class="fas fa-check-circle"></i>';
                completedButton.classList.add("complete-btn");
                todoDiv.appendChild(completedButton);

                const trashButton = document.createElement("button");
                trashButton.innerHTML = '<i class="fas fa-trash"></i>';
                trashButton.classList.add("trash-btn");
                todoDiv.appendChild(trashButton);

                todoList.appendChild(todoDiv);
            });
        })
        .catch(error => console.error('Error fetching todos:', error));
}


function filterTodo(e) {
    const todos = todoList.childNodes;
    todos.forEach(todo => {
        switch(e.target.value) {
            case "all": 
                todo.style.display = "flex";
                break;
            case "completed": 
                if(todo.classList.contains("completed")) {
                    todo.style.display = "flex";
                } else {
                    todo.style.display = "none";
                }
                break;
            case "incomplete":
                if(!todo.classList.contains("completed")) {
                    todo.style.display = "flex";
                } else {
                    todo.style.display = "none";
                }
                break;
        }
    });
}

function removeLocalTodos(todo) {
    let todos;
    if (localStorage.getItem("todos") === null) {
        todos = [];
    } else {
        todos = JSON.parse(localStorage.getItem("todos"));
    }

    const todoIndex = todo.children[0].innerText;
    todos.splice(todos.indexOf(todoIndex), 1);
    localStorage.setItem("todos", JSON.stringify(todos));
}