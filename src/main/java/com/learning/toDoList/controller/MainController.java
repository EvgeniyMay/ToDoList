package com.learning.toDoList.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learning.toDoList.model.ToDo;

@Controller
public class MainController {
	
	private static List<ToDo> toDoList = new ArrayList<ToDo>();
	
	private boolean inputError = false;
	
	static {
		toDoList.add(new ToDo("Normal to do", "Create a normal to do list"));
		toDoList.add(new ToDo("Forget", "Forget about this version"));
	}
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index(Model model) {
		
		return "index";
	}
	
	@RequestMapping(value = "/toDoList", method = RequestMethod.GET)
	public String toDoList(Model model) {
		model.addAttribute("toDoList", toDoList);
		model.addAttribute("inputError", inputError);
		
		return "toDoList";
	}
	
	@RequestMapping(value = "/addToDo", method = RequestMethod.POST)
	public String saveToDo(Model model,
			@ModelAttribute("inputToDo")
			ToDo inputToDo) {
		String title = inputToDo.getTitle();
		String description = inputToDo.getDescription();
		
		if(title != null && title.length() > 0
			&& description != null && description.length() > 0) {
			ToDo toDo = new ToDo(title, description);
			toDoList.add(toDo);
			
			inputError = false;
		} else {
			inputError = true;
		}

		return "redirect:toDoList";
	}
}
