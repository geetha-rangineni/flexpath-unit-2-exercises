package org.example.Controllers;

import org.example.Models.ToDoList;
import org.example.Models.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lists")
public class ToDoListController {

    @Autowired
    private ToDoListRepository repository;

    @GetMapping
    public List<ToDoList> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ToDoList getById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public ToDoList create(@RequestBody ToDoList list) {
        return repository.save(list);
    }

    @PutMapping("/{id}")
    public ToDoList update(@PathVariable int id, @RequestBody ToDoList updatedList) {
        Optional<ToDoList> existing = repository.findById(id);
        if (existing.isPresent()) {
            ToDoList list = existing.get();
            list.setName(updatedList.getName());
            return repository.save(list);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}
