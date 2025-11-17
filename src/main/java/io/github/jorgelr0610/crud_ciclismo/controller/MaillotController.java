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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.jorgelr0610.crud_ciclismo.model.Maillot;
import io.github.jorgelr0610.crud_ciclismo.service.MaillotService;

@Controller
@RequestMapping("/maillots")
public class MaillotController {

    private final MaillotService maillotService;

    public MaillotController(MaillotService maillotService){
        this.maillotService = maillotService;
    }

    @GetMapping
    public String listMaillots(Model model, @RequestParam(required = false) String campo, @RequestParam(required = false) String query){
        List<Maillot> maillots;

        if (campo != null && query != null && !query.isEmpty()){
            maillots = maillotService.search(campo, query);
        } else{
            maillots = maillotService.findAll();
        }
        model.addAttribute("listaDeMaillots", maillots);
        return "maillots/list";
    }

    @GetMapping("/nuevo")
    public String createForm(Model model){
        Maillot maillot = new Maillot();
        model.addAttribute("maillot", maillot);
        return "maillots/form";
    }

    @PostMapping("/guardar")
    public String createMaillot(@ModelAttribute("maillot") Maillot maillot, RedirectAttributes redirectAttributes){
        maillotService.create(maillot);
        redirectAttributes.addFlashAttribute("mensaje", "Maillot creado exitosamente");
        redirectAttributes.addFlashAttribute("tipo", "success");
        return "redirect:/maillots";
    }

    @GetMapping("/editar/{id}")
    public String updateMaillotForm(@PathVariable Integer id, Model model){
        Maillot maillot = maillotService.findById(id);
        model.addAttribute("maillot", maillot);
        return "maillots/form";
    }

    @PutMapping("/{id}") 
    public String updateMaillot(@PathVariable Integer id, @ModelAttribute("maillot") Maillot maillot, RedirectAttributes redirectAttributes){
        maillotService.update(id, maillot);
        redirectAttributes.addFlashAttribute("mensaje", "Maillot actualizado exitosamente");
        redirectAttributes.addFlashAttribute("tipo", "info");
        return "redirect:/maillots";
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteMaillot(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        maillotService.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Maillot eliminado exitosamente");
        redirectAttributes.addFlashAttribute("tipo", "warning");
        return "redirect:/maillots";
    }

}

