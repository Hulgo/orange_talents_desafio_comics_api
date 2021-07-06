package br.com.hulgo.comics.comics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comics")
public class ComicController {

    @Autowired
    private ComicService service;

    @GetMapping
    public Comic findAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Comic findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
}
