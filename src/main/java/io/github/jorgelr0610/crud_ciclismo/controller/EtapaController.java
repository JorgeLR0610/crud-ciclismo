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

import io.github.jorgelr0610.crud_ciclismo.model.Etapa;
import io.github.jorgelr0610.crud_ciclismo.service.EtapaService;

@Controller
@RequestMapping("/etapas")
public class EtapaController {

    private final EtapaService etapaService;

    public EtapaController(EtapaService etapaService){
        this.etapaService = etapaService;
    }

    @GetMapping
    public String listEtapas(Model model){
        List<Etapa> etapas = etapaService.findAll();
        model.addAttribute("listaDeEtapas", etapas);
        return "etapas/list";
    }

    @GetMapping("/nuevo")
    public String createForm(Model model){
        Etapa etapa = new Etapa();
        model.addAttribute("etapa", etapa);
        return "etapas/form";
    }

    @PostMapping("/guardar")
    public String createEtapa(@ModelAttribute("etapa") Etapa etapa){
        etapaService.create(etapa);
        return "redirect:/etapas";
    }

    @GetMapping("/editar/{id}")
    public String updateEtapaForm(@PathVariable Integer id, Model model){
        Etapa etapa = etapaService.findById(id);
        model.addAttribute("etapa", etapa);
        return "etapas/form";
    }

    @PutMapping("/{id}") 
    public String updateEtapa(@PathVariable Integer id, @ModelAttribute("etapa") Etapa etapa){
        etapaService.update(id, etapa); 
        return "redirect:/etapas";
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteEtapa(@PathVariable Integer id){
        etapaService.delete(id);
        return "redirect:/etapas";
    }

}

