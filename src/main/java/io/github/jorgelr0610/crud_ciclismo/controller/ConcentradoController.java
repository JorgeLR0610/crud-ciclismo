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
import io.github.jorgelr0610.crud_ciclismo.model.Concentrado;
import io.github.jorgelr0610.crud_ciclismo.model.Etapa;
import io.github.jorgelr0610.crud_ciclismo.model.Maillot;
import io.github.jorgelr0610.crud_ciclismo.model.Puerto;
import io.github.jorgelr0610.crud_ciclismo.service.CiclistaService;
import io.github.jorgelr0610.crud_ciclismo.service.ConcentradoService;
import io.github.jorgelr0610.crud_ciclismo.service.EtapaService;
import io.github.jorgelr0610.crud_ciclismo.service.MaillotService;
import io.github.jorgelr0610.crud_ciclismo.service.PuertoService;

@Controller
@RequestMapping("/concentrados")
public class ConcentradoController {

    private final ConcentradoService concentradoService;
    private final CiclistaService ciclistaService;
    private final EtapaService etapaService;
    private final MaillotService maillotService;
    private final PuertoService puertoService;

    public ConcentradoController(ConcentradoService concentradoService, CiclistaService ciclistaService,
                                 EtapaService etapaService, MaillotService maillotService, PuertoService puertoService){
        this.concentradoService = concentradoService;
        this.ciclistaService = ciclistaService;
        this.etapaService = etapaService;
        this.maillotService = maillotService;
        this.puertoService = puertoService;
    }

    @GetMapping
    public String listConcentrados(Model model){
        List<Concentrado> concentrados = concentradoService.findAll();
        model.addAttribute("listaDeConcentrados", concentrados);
        return "concentrados/list";
    }

    @GetMapping("/nuevo")
    public String createForm(Model model){
        Concentrado concentrado = new Concentrado();
        List<Ciclista> ciclistas = ciclistaService.findAll();
        List<Etapa> etapas = etapaService.findAll();
        List<Maillot> maillots = maillotService.findAll();
        List<Puerto> puertos = puertoService.findAll();
        model.addAttribute("concentrado", concentrado);
        model.addAttribute("ciclistas", ciclistas);
        model.addAttribute("etapas", etapas);
        model.addAttribute("maillots", maillots);
        model.addAttribute("puertos", puertos);
        return "concentrados/form";
    }

    @PostMapping("/guardar")
    public String createConcentrado(@ModelAttribute("concentrado") Concentrado concentrado,
                                    @RequestParam("dorsal") Integer dorsalId,
                                    @RequestParam("noEtapa") Integer etapaId,
                                    @RequestParam("codigo") Integer maillotId,
                                    @RequestParam("idPuerto") Integer puertoId){
        Ciclista ciclista = ciclistaService.findById(dorsalId);
        Etapa etapa = etapaService.findById(etapaId);
        Maillot maillot = maillotService.findById(maillotId);
        Puerto puerto = puertoService.findById(puertoId);
        concentrado.setDorsal(ciclista);
        concentrado.setNoEtapa(etapa);
        concentrado.setCodigo(maillot);
        concentrado.setIdPuerto(puerto);
        concentradoService.create(concentrado);
        return "redirect:/concentrados";
    }

    @GetMapping("/editar/{id}")
    public String updateConcentradoForm(@PathVariable Integer id, Model model){
        Concentrado concentrado = concentradoService.findById(id);
        List<Ciclista> ciclistas = ciclistaService.findAll();
        List<Etapa> etapas = etapaService.findAll();
        List<Maillot> maillots = maillotService.findAll();
        List<Puerto> puertos = puertoService.findAll();
        model.addAttribute("concentrado", concentrado);
        model.addAttribute("ciclistas", ciclistas);
        model.addAttribute("etapas", etapas);
        model.addAttribute("maillots", maillots);
        model.addAttribute("puertos", puertos);
        return "concentrados/form";
    }

    @PutMapping("/{id}") 
    public String updateConcentrado(@PathVariable Integer id, @ModelAttribute("concentrado") Concentrado concentrado,
                                    @RequestParam("dorsal") Integer dorsalId,
                                    @RequestParam("noEtapa") Integer etapaId,
                                    @RequestParam("codigo") Integer maillotId,
                                    @RequestParam("idPuerto") Integer puertoId){
        Ciclista ciclista = ciclistaService.findById(dorsalId);
        Etapa etapa = etapaService.findById(etapaId);
        Maillot maillot = maillotService.findById(maillotId);
        Puerto puerto = puertoService.findById(puertoId);
        concentrado.setDorsal(ciclista);
        concentrado.setNoEtapa(etapa);
        concentrado.setCodigo(maillot);
        concentrado.setIdPuerto(puerto);
        concentradoService.update(id, concentrado); 
        return "redirect:/concentrados";
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteConcentrado(@PathVariable Integer id){
        concentradoService.delete(id);
        return "redirect:/concentrados";
    }

}

