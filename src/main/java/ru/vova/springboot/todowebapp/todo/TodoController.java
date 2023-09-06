package ru.vova.springboot.todowebapp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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
}
