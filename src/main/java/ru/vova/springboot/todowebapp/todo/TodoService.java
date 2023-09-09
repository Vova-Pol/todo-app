package ru.vova.springboot.todowebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "Vladimir", "Learn Spring Boot",
                LocalDate.now().plusMonths(2), false));
        todos.add(new Todo(++todosCount, "Vladimir", "Learn Php and Wordpress",
                LocalDate.now().plusMonths(4), false));
        todos.add(new Todo(++todosCount, "Vladimir", "Create FullStack App",
                LocalDate.now().plusMonths(1), false));
        todos.add(new Todo(++todosCount, "Bob", "Rent a motobike",
                LocalDate.now().plusMonths(1), false));
        todos.add(new Todo(++todosCount, "Bob", "Go surfing in the sunset ",
                LocalDate.now().plusMonths(1), false));
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void addTodo(String userName, String description, LocalDate targetDate, boolean isDone) {
        todos.add(new Todo(++todosCount, userName, description, targetDate, isDone));
    }

    public void deleteTodoById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findTodoById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        return todos.stream().filter(predicate).findFirst().get();
    }

    public void updateTodo(Todo todo) {
        deleteTodoById(todo.getId());
        addTodo(todo.getUserName(), todo.getDescription(), todo.getTargetDate(), todo.getIsDone());
    }
}
