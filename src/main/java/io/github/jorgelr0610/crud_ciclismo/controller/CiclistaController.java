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

import io.github.jorgelr0610.crud_ciclismo.model.Ciclista;
import io.github.jorgelr0610.crud_ciclismo.model.Equipo;
import io.github.jorgelr0610.crud_ciclismo.service.CiclistaService;
import io.github.jorgelr0610.crud_ciclismo.service.EquipoService;

@Controller
@RequestMapping("/ciclistas")
public class CiclistaController {

    private final CiclistaService ciclistaService;
    private final EquipoService equipoService;

    public CiclistaController(CiclistaService ciclistaService, EquipoService equipoService){
        this.ciclistaService = ciclistaService;
        this.equipoService = equipoService;
    }

    @GetMapping
    public String listCiclistas(Model model){
        List<Ciclista> ciclistas = ciclistaService.findAll();
        model.addAttribute("listaDeCiclistas", ciclistas);
        return "ciclistas/list";
    }

    @GetMapping("/nuevo")
    public String createForm(Model model){
        Ciclista ciclista = new Ciclista();
        List<Equipo> equipos = equipoService.findAll();
        model.addAttribute("ciclista", ciclista);
        model.addAttribute("equipos", equipos);
        return "ciclistas/form";
    }

    @PostMapping("/guardar")
    public String createCiclista(@ModelAttribute("ciclista") Ciclista ciclista, @RequestParam("noEquipo") Integer equipoId){
        Equipo equipo = equipoService.findById(equipoId);
        ciclista.setNoEquipo(equipo);
        ciclistaService.create(ciclista);
        return "redirect:/ciclistas";
    }

    @GetMapping("/editar/{id}")
    public String updateCiclistaForm(@PathVariable Integer id, Model model){
        Ciclista ciclista = ciclistaService.findById(id);
        List<Equipo> equipos = equipoService.findAll();
        model.addAttribute("ciclista", ciclista);
        model.addAttribute("equipos", equipos);
        return "ciclistas/form";
    }

    @PutMapping("/{id}") 
    public String updateCiclista(@PathVariable Integer id, @ModelAttribute("ciclista") Ciclista ciclista, @RequestParam("noEquipo") Integer equipoId){
        Equipo equipo = equipoService.findById(equipoId);
        ciclista.setNoEquipo(equipo);
        ciclistaService.update(id, ciclista); 
        return "redirect:/ciclistas";
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteCiclista(@PathVariable Integer id){
        ciclistaService.delete(id);
        return "redirect:/ciclistas";
    }

}

