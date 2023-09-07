package ru.vova.springboot.todowebapp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping("todos-list")
    public String getAllTodos(ModelMap model) {
        List<Todo> todos = todoService.getTodos();
        model.addAttribute("todos", todos);
        return "listTodos";
    }

    @RequestMapping(value ="add-todo", method = RequestMethod.GET)
    public String addTodoPage() {
        return "todo";
    }

    @RequestMapping(value ="add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @RequestParam String description) {

        String userName = (String) model.get("name");
        todoService.addTodo(userName, description, LocalDate.now(), false);
        return "redirect:todos-list";
    }
}
