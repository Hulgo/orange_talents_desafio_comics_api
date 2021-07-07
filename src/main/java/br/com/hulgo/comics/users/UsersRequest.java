package br.com.hulgo.comics.users;

import br.com.hulgo.comics.validators.CPFValidator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UsersRequest {

    @NotBlank
    private String name;

    @NotBlank @Email
    private String email;

    @NotBlank
    @CPFValidator
    private String cpf;

    @NotNull
    private Date dataNasc;


    public UsersRequest() {
        super();
    }

    public UsersRequest(String name, String email, String cpf, Date dataNasc) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public Users toUsers(){
        return new Users(this.name, this.email, this.cpf, this.dataNasc);
    }

}
