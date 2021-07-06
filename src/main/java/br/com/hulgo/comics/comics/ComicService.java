package br.com.hulgo.comics.comics;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class ComicService {

    private static final String PUBLIC_KEY = "fe25ad8f8b503f662d50dc4f1e19e8c4";
    private static final String PRIVATE_KEY = "b4cfbb9456af19f2be5f857551b3aa8862184f30";

    private ComicRepository client;

    public ComicService(ComicRepository client) {
        this.client = client;
    }

    public Comic getAll() {
        Long timestamp = new Date().getTime();

        return client.getAll(timestamp, PUBLIC_KEY, buildHash((timestamp)));
    }

    private String buildHash(Long timeStamp) {
        String string = timeStamp + PRIVATE_KEY + PUBLIC_KEY;
        InputStream inputStream = new ByteArrayInputStream(string.getBytes(Charset.forName("UTF-8")));
        try {
            return DigestUtils.md5DigestAsHex(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Comic findById(Long id) {
        Long timestamp = new Date().getTime();

        Comic comic = client.findById(timestamp, PUBLIC_KEY, buildHash((timestamp)), id);

        String isbn;
        String lastDigit;
        if(comic.getData().getResults().get(0).getIsbn().isEmpty()){
            lastDigit = "";
        } else {
            isbn = comic.getData().getResults().get(0).getIsbn();
            lastDigit = isbn.substring(isbn.length() - 1);
        }
        comic.getData().getResults().forEach(item -> item.getPrices().forEach(pop -> pop.setDiscountDay(discountDay(lastDigit))));
        comic.getData().getResults().forEach(item -> item.getPrices().forEach(pop -> pop.setDiscountActive(discountActive(lastDigit))));

        if(discountActive(lastDigit)){
            comic.getData().getResults().forEach(item -> item.getPrices().forEach(pop -> pop.setPrice(pop.getPrice() * 0.9)));
        } else {
            comic.getData().getResults().forEach(item -> item.getPrices().forEach(pop -> pop.setPrice(pop.getPrice())));
        }

        return comic;
    }

    private String discountDay(String lastDigit) {

        switch (lastDigit) {
            case "0":
            case "1":
                return "Segunda-Feira";
            case "2":
            case "3":
                return "Terça-Feira";
            case "4":
            case "5":
                return "Quarta-Feira";
            case "6":
            case "7":
                return "Quinta-Feira";
            case "8":
            case "9":
                return "Sexta-Feira";
            default:
                return "Sem dia de desconto";

        }
    }

    private boolean discountActive(String lastDigit) {

        Date today = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);

        switch (lastDigit) {
            case "0":
            case "1":
                if(calendar.get(calendar.DAY_OF_WEEK) == Calendar.MONDAY){
                    return true;
                } else {
                    return false;
                }
            case "2":
            case "3":
                if(calendar.get(calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
                    return true;
                } else {
                    return false;
                }
            case "4":
            case "5":
                if(calendar.get(calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
                    return true;
                } else {
                    return false;
                }
            case "6":
            case "7":
                if(calendar.get(calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
                    return true;
                } else {
                    return false;
                }
            case "8":
            case "9":
                if(calendar.get(calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }
}
