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

import io.github.jorgelr0610.crud_ciclismo.model.Etapa;
import io.github.jorgelr0610.crud_ciclismo.service.EtapaService;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/etapas")
public class EtapaController {

    private final EtapaService etapaService;

    public EtapaController(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    @GetMapping
    public String listEtapas(Model model, @RequestParam(required = false) String campo,
            @RequestParam(required = false) String query) {
        List<Etapa> etapas;

        if (campo != null && query != null && !query.isEmpty()) {
            etapas = etapaService.search(campo, query);
        } else {
            etapas = etapaService.findAll();
        }
        model.addAttribute("listaDeEtapas", etapas);
        return "etapas/list";
    }

    @GetMapping("/nuevo")
    public String createForm(Model model) {
        Etapa etapa = new Etapa();
        model.addAttribute("etapa", etapa);
        return "etapas/form";
    }

    @PostMapping("/guardar")
    public String createEtapa(@Valid @ModelAttribute("etapa") Etapa etapa, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "etapas/form";
        }
        etapaService.create(etapa);
        redirectAttributes.addFlashAttribute("mensaje", "Etapa creada exitosamente");
        redirectAttributes.addFlashAttribute("tipo", "success");
        return "redirect:/etapas";
    }

    @GetMapping("/editar/{id}")
    public String updateEtapaForm(@PathVariable Integer id, Model model) {
        Etapa etapa = etapaService.findById(id);
        model.addAttribute("etapa", etapa);
        return "etapas/form";
    }

    @PutMapping("/{id}")
    public String updateEtapa(@PathVariable Integer id, @Valid @ModelAttribute("etapa") Etapa etapa,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "etapas/form";
        }
        etapaService.update(id, etapa);
        redirectAttributes.addFlashAttribute("mensaje", "Etapa actualizada exitosamente");
        redirectAttributes.addFlashAttribute("tipo", "info");
        return "redirect:/etapas";
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteEtapa(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        etapaService.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Etapa eliminada exitosamente");
        redirectAttributes.addFlashAttribute("tipo", "warning");
        return "redirect:/etapas";
    }

}
