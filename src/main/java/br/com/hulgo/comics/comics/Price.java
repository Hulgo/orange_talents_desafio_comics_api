package br.com.hulgo.comics.comics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "price"
})
public class Price {

    @JsonProperty("type")
    private String type;

    @JsonProperty("price")
    private double price;

    @JsonProperty("discountDay")
    private String discountDay;

    @JsonProperty("discountActive")
    private boolean discountActive;

    public Price() {}

    public Price(String type, double price, String discountDay, boolean discountActive) {
        super();
        this.type = type;
        this.price = price;
        this.discountDay = discountDay;
        this.discountActive = discountActive;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    public String getDiscountDay() {
        return discountDay;
    }

    public boolean isDiscountActive() {
        return discountActive;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("price")
    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscountDay(String discountDay) {
        this.discountDay = discountDay;
    }

    public void setDiscountActive(boolean discountActive) {
        this.discountActive = discountActive;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Price.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(this.price);
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
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((int)(Double.doubleToLongBits(this.price)^(Double.doubleToLongBits(this.price)>>> 32))));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Price) == false) {
            return false;
        }
        Price rhs = ((Price) other);
        return ((((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&(Double.doubleToLongBits(this.price) == Double.doubleToLongBits(rhs.price)));
    }
}
