const API_URL = 'http://localhost:8080/todo'; // Altere conforme necessário

document.addEventListener('DOMContentLoaded', function() {
    loadTodos();

    const todoForm = document.getElementById('todo-form');
    todoForm.addEventListener('submit', function(e) {
        e.preventDefault();
        addTodo();
    });

    document.getElementById('show-all').addEventListener('click', function() {
        loadTodos();
    });
    document.getElementById('show-completed').addEventListener('click', function() {
        loadTodos(true);
    });
    document.getElementById('show-pending').addEventListener('click', function() {
        loadTodos(false);
    });
});

function loadTodos(completed = null) {
    let url = API_URL;
    if (completed !== null) {
        url += `?completed=${completed}`;
    }

    fetch(url)
        .then(response => response.json())
        .then(todos => {
            const todoList = document.getElementById('todo-list');
            todoList.innerHTML = '';
            todos.forEach(todo => {
                const li = document.createElement('li');
                li.innerHTML = `
                    <div class="todo-info">
                        <strong>${todo.title}</strong>
                        <p>${todo.description}</p>
                        <span class="created-at">Atualizado em: ${todo.createdAt}</span>
                    </div>
                    <div class="todo-actions">
                        <button class="complete" onclick="toggleComplete(${todo.id}, ${!todo.completed}, '${todo.title}', '${todo.description}')">${todo.completed ? 'Concluída' : 'Marcar como Concluída'}</button>
                        <button class="delete" onclick="deleteTodo(${todo.id})">Excluir</button>
                    </div>
                `;
                todoList.appendChild(li);
            });
        })
        .catch(error => console.error('Erro ao carregar tarefas:', error));
}

function addTodo() {
    const title = document.getElementById('title').value;
    const description = document.getElementById('description').value;

    const newTodo = {
        title: title,
        description: description,
        completed: false
    };

    fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newTodo)
    })
        .then(response => response.json())
        .then(() => {
            loadTodos(); // Recarregar todos os todos após a criação
            document.getElementById('todo-form').reset();
        })
        .catch(error => console.error('Erro ao adicionar tarefa:', error));
}

function toggleComplete(id, completed, title, description) {
    // Ao alterar o status da tarefa, mantemos o título e a descrição
    const todoData = {
        title: title,  // Mantemos o título
        description: description,  // Mantemos a descrição
        completed: completed  // Alteramos apenas o status de conclusão
    };

    fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(todoData)
    })
        .then(() => loadTodos()) // Recarregar lista após alteração
        .catch(error => console.error('Erro ao atualizar tarefa:', error));
}

function deleteTodo(id) {
    fetch(`${API_URL}/${id}`, {
        method: 'DELETE'
    })
        .then(() => loadTodos()) // Recarregar lista após exclusão
        .catch(error => console.error('Erro ao excluir tarefa:', error));
}
