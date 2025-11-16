package io.github.jorgelr0610.crud_ciclismo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jorgelr0610.crud_ciclismo.model.Puerto;
import io.github.jorgelr0610.crud_ciclismo.service.PuertoService;

@Controller
@RequestMapping("/puertos")
public class PuertoController {

    private final PuertoService puertoService;

    public PuertoController(PuertoService puertoService){
        this.puertoService = puertoService;
    }

    @GetMapping
    public String listPuertos(Model model){
        List<Puerto> puertos = puertoService.findAll();
        model.addAttribute("listaDePuertos", puertos);
        return "puertos/list";
    }

    @GetMapping("/nuevo")
    public String createForm(Model model){
        Puerto puerto = new Puerto();
        model.addAttribute("puerto", puerto);
        return "puertos/form";
    }

    @PostMapping("/guardar")
    public String createPuerto(@ModelAttribute("puerto") Puerto puerto){
        puertoService.create(puerto);
        return "redirect:/puertos";
    }

    @GetMapping("/editar/{id}")
    public String updatePuertoForm(@PathVariable Integer id, Model model){
        Puerto puerto = puertoService.findById(id);
        model.addAttribute("puerto", puerto);
        return "puertos/form";
    }

    @PutMapping("/{id}") 
    public String updatePuerto(@PathVariable Integer id, @ModelAttribute("puerto") Puerto puerto){
        puertoService.update(id, puerto); 
        return "redirect:/puertos";
    }

    @DeleteMapping("/eliminar/{id}")
    public String deletePuerto(@PathVariable Integer id){
        puertoService.delete(id);
        return "redirect:/puertos";
    }

}

