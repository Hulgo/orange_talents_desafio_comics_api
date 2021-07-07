package br.com.hulgo.comics.userComics;

import br.com.hulgo.comics.users.Users;

import javax.validation.constraints.NotNull;

public class UserComicsRequest {

    @NotNull
    private Users user;

    @NotNull
    private Long comicId;

    private String title;

    private Double price;

    private String discountDay;

    private boolean discountActive;

    private String creators;

    private String isbn;

    private String description;

    public UserComicsRequest() {
        super();
    }

    public UserComicsRequest(Users user, Long comicId, String title, Double price, String discountDay, boolean discountActive, String creators, String isbn, String description) {
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
                "user=" + user  +
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

    public UserComics toUserComics() {
        return new UserComics(
                this.user,
                this.comicId,
                this.title,
                this.price,
                this.discountDay,
                this.discountActive,
                this.creators,
                this.isbn,
                this.description
        );
    }
}
