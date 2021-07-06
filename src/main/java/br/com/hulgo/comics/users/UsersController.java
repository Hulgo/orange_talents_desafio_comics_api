package br.com.hulgo.comics.users;

import br.com.hulgo.comics.exceptions.UsersNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersRepository repository;

    public UsersController(UsersRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Users> all(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    Users one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new UsersNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<?> UsersSave(@RequestBody @Valid UsersRequest usersRequest) {

        try {
            repository.save(usersRequest.toUsers());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<?> UsersUpdate(@PathVariable Long id, @RequestBody @Valid UsersRequest usersRequest) {

        try {
            Users users = repository.findById(id)
                    .orElseThrow(() -> new UsersNotFoundException(id));

            users.setName(usersRequest.getName());
            users.setEmail(usersRequest.getEmail());
            users.setCpf(usersRequest.getCpf());
            users.setDataNasc(usersRequest.getDataNasc());

            final Users updatedUsers = repository.save(users);
            return ResponseEntity.ok(updatedUsers);

         } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> UsersDelete(@PathVariable Long id){

        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
