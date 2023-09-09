package ru.vova.springboot.todowebapp.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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

    @RequestMapping("todo-list")
    public String getAllTodos(ModelMap model) {
        List<Todo> todos = todoService.getTodos();
        model.addAttribute("todos", todos);
        return "todo-list";
    }

    @RequestMapping(value ="add-todo", method = RequestMethod.GET)
    public String addTodoPage(ModelMap model) {
        String userName = (String) model.get("name");
        Todo todo = new Todo(0, userName, "", LocalDate.now(), false);
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value ="add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        String userName = (String) model.get("name");
        todoService.addTodo(userName, todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:todo-list";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteTodoById(id);
        return "redirect:todo-list";
    }

    @RequestMapping("update-todo")
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findTodoById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        todo.setUserName((String) model.get("name"));
        todoService.updateTodo(todo);
        return "redirect:todo-list";
    }


}
