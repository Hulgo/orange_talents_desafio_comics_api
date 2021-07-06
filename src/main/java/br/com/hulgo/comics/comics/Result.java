package br.com.hulgo.comics.comics;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "isbn",
        "description",
        "prices",
        "creators"
})
public class Result {

    @Id
    @JsonProperty("id")
    private long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("description")
    private String description;

    @JsonProperty("prices")
    private List<Price> prices = null;

    @JsonProperty("creators")
    private Creators creators;

    public Result() {}

    public Result(long id, String title, String isbn, String description, List<Price> prices, Creators creators) {
        super();
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.description = description;
        this.prices = prices;
        this.creators = creators;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("isbn")
    public String getIsbn() {
        return isbn;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("prices")
    public List<Price> getPrices() {
        return prices;
    }

    @JsonProperty("creators")
    public Creators getCreators() {
        return creators;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Result.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(this.id);
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("isbn");
        sb.append('=');
        sb.append(((this.isbn == null)?"<null>":this.isbn));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("prices");
        sb.append('=');
        sb.append(((this.prices == null)?"<null>":this.prices));
        sb.append(',');
        sb.append("creators");
        sb.append('=');
        sb.append(((this.creators == null)?"<null>":this.creators));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.creators == null)? 0 :this.creators.hashCode()));
        result = ((result* 31)+((this.isbn == null)? 0 :this.isbn.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((int)(this.id^(this.id >>> 32))));
        result = ((result* 31)+((this.title == null)? 0 :this.title.hashCode()));
        result = ((result* 31)+((this.prices == null)? 0 :this.prices.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Result) == false) {
            return false;
        }
        Result rhs = ((Result) other);
        return ((((((((this.creators == rhs.creators)||((this.creators!= null)&&this.creators.equals(rhs.creators)))&&((this.isbn == rhs.isbn)||((this.isbn!= null)&&this.isbn.equals(rhs.isbn))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&(this.id == rhs.id))&&((this.title == rhs.title)||((this.title!= null)&&this.title.equals(rhs.title))))&&((this.prices == rhs.prices)||((this.prices!= null)&&this.prices.equals(rhs.prices)))));
    }
}