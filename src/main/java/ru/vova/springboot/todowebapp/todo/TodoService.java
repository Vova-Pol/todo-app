package ru.vova.springboot.todowebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo(1, "Vova", "Learn Spring",
                LocalDate.now().plusMonths(2), false));
        todos.add(new Todo(2, "Vova", "Learn Php",
                LocalDate.now().plusMonths(4), false));
        todos.add(new Todo(3, "Vova", "Create FullStack App",
                LocalDate.now().plusMonths(1), false));
    }

    public List<Todo> getTodos() {
        return todos;
    }
}
