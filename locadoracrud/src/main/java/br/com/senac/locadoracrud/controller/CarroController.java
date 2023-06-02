package br.com.senac.locadoracrud.controller;

import br.com.senac.locadoracrud.data.CarroRepository;
import br.com.senac.locadoracrud.model.Carro;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/carros")
public class CarroController {

    private final CarroRepository carroRepository;

    @Autowired
    public CarroController(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    @GetMapping("/form")
    public String exibirFormulario() {
        return "formulario-carro";
    }

    @PostMapping("/salvar")
    public String salvarCarro(Carro carro) {
        carroRepository.save(carro);
        return "redirect:/carros/listar";
    }

    @GetMapping("/listar")
    public String listarCarros(Model model) {
        Iterable<Carro> carros = carroRepository.findAll();
        model.addAttribute("carros", carros);
        return "lista-carros";
    }

    @GetMapping("/")
    public String exibirPaginaInicial() {
        return "home";
    }

    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }

    @GetMapping("/alterarCarro")
public String exibirFormularioAlterarCarro(Model model, @RequestParam("id") Long id) {
    Optional<Carro> optionalCarro = carroRepository.findById(id);
    if (optionalCarro.isPresent()) {
        Carro carro = optionalCarro.get();
        model.addAttribute("carro", carro);
        return "formulario-alterar-carro";
    } else {
        return "error"; // Página de erro caso o carro não seja encontrado
    }
}

@PostMapping("/alterarCarro")
public String alterarCarro(@ModelAttribute("carro") Carro carro) {
    carroRepository.save(carro);
    return "redirect:/carros/listar";
}

@GetMapping("/excluirCarro")
public String excluirCarro(@RequestParam("id") Long id) {
    carroRepository.deleteById(id);
    return "redirect:/carros/listar";
}


}
