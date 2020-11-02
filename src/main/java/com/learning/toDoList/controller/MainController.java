package com.learning.toDoList.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index() {
		
		return "index";
	}
	
	@RequestMapping(value = "/toDoList", method = RequestMethod.GET)
	public String toDoList(Model model) {
		model.addAttribute("toDoList", toDoList);
		model.addAttribute("inputError", inputError);
		
		return "toDoList";
	}
	
	@RequestMapping(value="/deleteToDo", method = RequestMethod.POST)
	public String deleteDoTo(Model model,
			@ModelAttribute("code")
			Integer code) {
		for(int i=0; i<toDoList.size(); ++i) {
			if(toDoList.get(i).getCode() == code)
				toDoList.remove(i);
		}
		
		return "redirect:toDoList";
	}
	
	@RequestMapping(value = "/addToDo", method = RequestMethod.POST)
	public String saveToDo(Model model,
			@ModelAttribute("inputToDo")
			ToDo inputToDo) {
		String text = inputToDo.getText();
		
		if(text != null && text.length() > 0) {
			ToDo toDo = new ToDo(text);
			toDoList.add(toDo);
			
			inputError = false;
		} else {
			inputError = true;
		}

		return "redirect:toDoList";
	}
}
