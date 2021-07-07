package br.com.hulgo.comics.userComics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserComicsRepository extends JpaRepository<UserComics, Long> {}
