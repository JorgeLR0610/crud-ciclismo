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

import io.github.jorgelr0610.crud_ciclismo.model.Equipo;
import io.github.jorgelr0610.crud_ciclismo.service.EquipoService;

@Controller
@RequestMapping("/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService){
        this.equipoService = equipoService; //Constructor injection
    }

    //Para listar todos los registros de la tabla una vez que se entre a esta ruta, o según los filtros de búsqueda
    @GetMapping //Este método responde a la ruta raíz dentro de /equipos
    public String listEquipos(Model model, @RequestParam(required = false) String campo, @RequestParam(required = false) String query){ //Los parámetros son opcionales
        List<Equipo> equipos;

        if (campo != null && query != null && !query.isEmpty()){
            equipos = equipoService.search(campo, query);
        } else{
            equipos = equipoService.findAll();
        }
        model.addAttribute("listaDeEquipos", equipos); //Pasa los datos a la vista
        return "equipos/list"; // devuelve el nombre del archivo html sin la extensión (templates/equipos/list.html)
    }

    // Para agregar un nuevo registro; se requiere un método GET para mostrar el formulario y un POST para guardar los datos
    @GetMapping("/nuevo")
    public String createForm(Model model){
        Equipo equipo = new Equipo();
        model.addAttribute("equipo", equipo);
        return "equipos/form"; // templates/equipos/form.html
    }

    @PostMapping("/guardar")
    public String createEquipo(@ModelAttribute("equipo") Equipo equipo, RedirectAttributes redirectAttributes){
        equipoService.create(equipo);
        redirectAttributes.addFlashAttribute("mensaje", "Equipo creado exitosamente");
        redirectAttributes.addFlashAttribute("tipo", "success");
        return "redirect:/equipos"; // "redirect:" es para enviar al usuario de vuelta a la lista y se evita un doble envío si se refresca la página
    }

    //Para editar un registro también se requiere un GET para mostrar el formulario pero ahora con los datos cargados, y un POST
    @GetMapping("/editar/{id}")
    public String udpateEquipoForm(@PathVariable Integer id, Model model){

        Equipo equipo = equipoService.findById(id);
        model.addAttribute("equipo", equipo);
        return "equipos/form";
    }

    @PutMapping("/{id}") 
    public String updateEquipo(@PathVariable Integer id, @ModelAttribute("equipo") Equipo equipo, RedirectAttributes redirectAttributes){
        equipoService.update(id, equipo); 
        redirectAttributes.addFlashAttribute("mensaje", "Equipo actualizado exitosamente");
        redirectAttributes.addFlashAttribute("tipo", "info");
        return "redirect:/equipos";
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteEquipo(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        equipoService.delete(id);
        redirectAttributes.addFlashAttribute("mensaje", "Equipo eliminado exitosamente");
        redirectAttributes.addFlashAttribute("tipo", "warning");

        return "redirect:/equipos";
    }

}
