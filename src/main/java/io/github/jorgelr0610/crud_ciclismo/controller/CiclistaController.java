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

import io.github.jorgelr0610.crud_ciclismo.model.Ciclista;
import io.github.jorgelr0610.crud_ciclismo.model.Equipo;
import io.github.jorgelr0610.crud_ciclismo.service.CiclistaService;
import io.github.jorgelr0610.crud_ciclismo.service.EquipoService;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/ciclistas")
public class CiclistaController {

    private final CiclistaService ciclistaService;
    private final EquipoService equipoService;

    public CiclistaController(CiclistaService ciclistaService, EquipoService equipoService) {
        this.ciclistaService = ciclistaService;
        this.equipoService = equipoService;
    }

    @GetMapping
    public String listCiclistas(Model model, @RequestParam(required = false) String campo,
            @RequestParam(required = false) String query) {
        List<Ciclista> ciclistas;

        if (campo != null && query != null && !query.isEmpty()) {
            ciclistaService.search(campo, query);
            ciclistas = ciclistaService.search(campo, query);
        } else {
            ciclistas = ciclistaService.findAll();
        }
        model.addAttribute("listaDeCiclistas", ciclistas);
        return "ciclistas/list";
    }

    @GetMapping("/nuevo")
    public String createForm(Model model) {
        Ciclista ciclista = new Ciclista();
        List<Equipo> equipos = equipoService.findAll();
        model.addAttribute("ciclista", ciclista);
        model.addAttribute("equipos", equipos);
        return "ciclistas/form";
    }

    @PostMapping("/guardar")
    public String createCiclista(@Valid @ModelAttribute("ciclista") Ciclista ciclista, BindingResult bindingResult,
            @RequestParam("noEquipo") Integer equipoId, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            List<Equipo> equipos = equipoService.findAll();
            model.addAttribute("equipos", equipos);
            return "ciclistas/form";
        }

        Equipo equipo = equipoService.findById(equipoId);
        ciclista.setNoEquipo(equipo);
        ciclistaService.create(ciclista);
        redirectAttributes.addFlashAttribute("mensaje", "Ciclista creado exitosamente");
        redirectAttributes.addFlashAttribute("tipo", "success");
        return "redirect:/ciclistas";
    }

    @GetMapping("/editar/{id}")
    public String updateCiclistaForm(@PathVariable Integer id, Model model) {
        Ciclista ciclista = ciclistaService.findById(id);
        List<Equipo> equipos = equipoService.findAll();
        model.addAttribute("ciclista", ciclista);
        model.addAttribute("equipos", equipos);
        return "ciclistas/form";
    }

    @PutMapping("/{id}")
    public String updateCiclista(@PathVariable Integer id, @Valid @ModelAttribute("ciclista") Ciclista ciclista,
            BindingResult bindingResult, @RequestParam("noEquipo") Integer equipoId,
            RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            List<Equipo> equipos = equipoService.findAll();
            model.addAttribute("equipos", equipos);
            return "ciclistas/form";
        }

        Equipo equipo = equipoService.findById(equipoId);
        ciclista.setNoEquipo(equipo);
        ciclistaService.update(id, ciclista);
        redirectAttributes.addFlashAttribute("mensaje", "Ciclista actualizado exitosamente");
        redirectAttributes.addFlashAttribute("tipo", "info");
        return "redirect:/ciclistas";
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteCiclista(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        ciclistaService.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Ciclista eliminado exitosamente");
        redirectAttributes.addFlashAttribute("tipo", "warning");
        return "redirect:/ciclistas";
    }

}
