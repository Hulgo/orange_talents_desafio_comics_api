package br.com.hulgo.comics.comics;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comic {

    @JsonProperty("data")
    private Data data;

    public Comic() {}

    public Comic(Data data) {
        super();
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Comic.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
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
        result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Comic) == false) {
            return false;
        }
        Comic rhs = ((Comic) other);
        return (((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data))));
    }

}
