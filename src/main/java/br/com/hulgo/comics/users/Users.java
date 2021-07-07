package br.com.hulgo.comics.users;

import br.com.hulgo.comics.userComics.UserComics;
import br.com.hulgo.comics.validators.CPFValidator;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false, unique = true)
    @CPFValidator
    private String cpf;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataNasc;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH)
    private List<UserComics> userComicsList;

    public Users() {
        super();
    }

    @JsonCreator
    public Users(@JsonProperty("userId") Long userId) {

        super();
        this.userId = userId;
    }

    public Users(String name, String email, String cpf, Date dataNasc) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Users other = (Users) obj;
        if (userId == null) {
            return other.userId == null;
        } else return userId.equals(other.userId);
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public Date getDataNasc() {
        return this.dataNasc;
    }

    public void setUserId(Long userId) { this.userId = userId; }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + this.userId +
                ", name='" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                ", cpf='" + this.cpf + '\'' +
                ", dataNasc=" + this.dataNasc +
                '}';
    }

}
