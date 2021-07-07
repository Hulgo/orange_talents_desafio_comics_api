package br.com.hulgo.comics.userComics;

import br.com.hulgo.comics.comics.Comic;
import br.com.hulgo.comics.comics.ComicService;
import br.com.hulgo.comics.users.Users;
import br.com.hulgo.comics.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

@RestController
@RequestMapping("/usercomics")
public class UserComicsController {

    @Autowired
    private UserComicsRepository userComicsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ComicService comicService;

    @PostMapping
    public ResponseEntity<?> userComicSave(@RequestBody @Valid UserComicsRequest userComicsRequest) {
        Optional<Comic> comic = Optional.ofNullable(comicService.findById(userComicsRequest.getComicId()));
        Optional<Users> users = usersRepository.findById(userComicsRequest.getUser().getUserId());

        if (comic.isPresent() && users.isPresent()) {
            UserComics userComics = userComicsRequest.toUserComics();
            userComics.setUser(users.get());
            userComics.setComicId(userComicsRequest.getComicId().longValue());
            userComics.setTitle(comic.get().getData().getResults().get(0).getTitle());

            // Verifica o ISBN e aplica o desconto de acordo com a regra de negócio.
            String isbn;
            String lastDigit;
            if(comic.get().getData().getResults().get(0).getIsbn().isEmpty()){
                lastDigit = "";
            } else {
                isbn = comic.get().getData().getResults().get(0).getIsbn();
                lastDigit = isbn.substring(isbn.length() - 1);
            }
            userComics.setDiscountDay(discountDay(lastDigit));
            userComics.setDiscountActive(discountActive(lastDigit));

            // Verifica se o dia de desconto é hoje, caso sim, aplica 10% de desconto no preço informado pela API da Marvel.
            if(discountActive(lastDigit)){
                userComics.setPrice(comic.get().getData().getResults().get(0).getPrices().get(0).getPrice() * 0.9);
            } else {
                userComics.setPrice(comic.get().getData().getResults().get(0).getPrices().get(0).getPrice());
            }

            // Cria uma String com todos os autores (Creators) registrados para a Comic.
            StringBuilder creatorsNames = new StringBuilder();
            comic.get().getData().getResults().get(0).getCreators().getItems().forEach(item -> creatorsNames.append(item.getName() + ", ") );
            userComics.setCreators(creatorsNames.toString());

            userComics.setIsbn(comic.get().getData().getResults().get(0).getIsbn());
            userComics.setDescription(comic.get().getData().getResults().get(0).getDescription());
            userComicsRepository.save(userComics);
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.badRequest().build();
    }

    private String discountDay(String lastDigit) {
        // Regra de negócio informando o dia da semana dedicado a descontos de acordo com o ISBN.
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
        // Regra de negócio informando se o desconto está ativo no dia atual.
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
