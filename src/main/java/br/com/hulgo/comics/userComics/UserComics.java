package br.com.hulgo.comics.userComics;

import br.com.hulgo.comics.users.Users;
import com.fasterxml.jackson.annotation.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usercomics")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="ucid")
public class UserComics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ucid;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonBackReference
    private Users user;

    private Long comicId;

    private String title;

    private Double price;

    private String discountDay;

    private boolean discountActive;

    public String creators;

    private String isbn;

    @Column(columnDefinition = "TEXT")
    private String description;

    public UserComics() {
        super();
    }

    public UserComics(Users user, Long comicId, String title, Double price, String discountDay, boolean discountActive, String creators, String isbn, String description) {
        this.user = user;
        this.comicId = comicId;
        this.title = title;
        this.price = price;
        this.discountDay = discountDay;
        this.discountActive = discountActive;
        this.creators = creators;
        this.isbn = isbn;
        this.description = description;
    }

    public Long getUcid() {
        return this.ucid;
    }

    public Users getUser() {
        return user;
    }

    public Long getComicId() {
        return comicId;
    }

    public String getTitle() { return title; }

    public Double getPrice() { return price; }

    public String getDiscountDay() { return discountDay; }

    public boolean getDiscountActive() { return discountActive; }

    public String getCreators() { return creators; }

    public String getIsbn() { return isbn; }

    public String getDescription() { return description; }

    public void setUcid(Long ucid) {
        this.ucid = ucid;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }

    public void setTitle(String title) { this.title = title; }

    public void setPrice(Double price) { this.price = price; }

    public void setDiscountDay(String discountDay) { this.discountDay = discountDay; }

    public void setDiscountActive(boolean discountActive) { this.discountActive = discountActive; }

    public void setCreators(String creators) { this.creators = creators; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "UserComics{" +
                "ucid="+ ucid +
                ", user=" + user +
                ", comicId='" + comicId +
                ", title='" + title +
                ", price='" + price +
                ", discountDay='" + discountDay +
                ", discountActive='" + discountActive +
                ", creators='" + creators +
                ", isbn='" + isbn +
                ", description='" + description +
                '}';
    }
}
