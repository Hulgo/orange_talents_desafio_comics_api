package br.com.hulgo.comics.comics;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "comicRepository", url = "${url.marvel}/v1/public")
public interface ComicRepository {

    @RequestMapping(method = RequestMethod.GET, value = "/comics")
    Comic getAll(@RequestParam(value = "ts") Long timestamp,
                        @RequestParam(value = "apikey") String publicKey,
                        @RequestParam(value = "hash") String hashMD5);

    @RequestMapping(method = RequestMethod.GET, value = "/comics/{id}")
    Comic findById(@RequestParam(value = "ts") Long timestamp,
                   @RequestParam(value = "apikey") String publicKey,
                   @RequestParam(value = "hash") String hashMD5,
                   @RequestParam(value = "id") Long id);
}
