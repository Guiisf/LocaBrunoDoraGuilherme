package br.com.senac.locadoracrud.controller;

import br.com.senac.locadoracrud.data.CarroRepository;
import br.com.senac.locadoracrud.model.Carro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    return "index";
}

@RequestMapping("/error")
public String handleError() {
    return "error";
}


}