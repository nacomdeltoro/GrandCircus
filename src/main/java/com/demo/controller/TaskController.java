package com.demo.controller;

import com.demo.model.Task;
import com.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks/")
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("signup")
    public String showSignUpForm(Task task) {
        return "add-task";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addtask(@Valid Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-task";
        }

        taskRepository.save(task);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        model.addAttribute("task", task);
        return "update-task";
    }

    @PostMapping("update/{id}")
    public String updatetask(@PathVariable("id") long id, @Valid Task task, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            task.setId(id);
            return "update-task";
        }

        taskRepository.save(task);
        model.addAttribute("tasks", taskRepository.findAll());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deletetask(@PathVariable("id") long id, Model model) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        taskRepository.delete(task);
        model.addAttribute("tasks", taskRepository.findAll());
        return "index";
    }




}