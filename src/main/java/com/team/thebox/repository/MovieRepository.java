package com.team.thebox.repository;

import com.team.thebox.model.Movie;
import com.team.thebox.model.MovieView;
import com.team.thebox.model.Movielocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    


//
//    @Query("SELECT m.movtitle FROM Movie m WHERE m.movno = :movno")
//    List<String> findMovTitleByMovno(@Param("movno") long movno);

    Movie findMovTitleByMovno(Long movno);



//    @Query("SELECT m.movno, m.movtitle, m.movactor, m.movgrade, m.movreleasedate, mp.url, AVG(r.star) AS avg_star " +
//        "FROM Movie m " +
//        "JOIN MovieReply r ON m.movno = r.movno " +
//        "LEFT JOIN MoviePoster mp ON m.movno = mp.movie.movno " +
//        "GROUP BY m.movno, m.movtitle, m.movactor, m.movgrade, m.movreleasedate, mp.url " +
//        "ORDER BY avg_star DESC")
    @Query("SELECT m.movno, m.movtitle, m.movactor, m.movgrade, m.movreleasedate,m.movmainposter, m.tsales, AVG(r.star) AS avg_star " +
            "FROM Movie m JOIN MovieReply r ON m.movno = r.movno " +
            "GROUP BY m.movno, m.movtitle, m.movactor, m.movgrade, m.movreleasedate, m.movmainposter, m.tsales " +
            "ORDER BY avg_star DESC")
    List<Object[]> findMoviesOrderByAvgStarDesc();


    @Query("from Movie order by tsales desc")
    List<Movie> findAllByOrderByTsalesDesc();

    @Query("from Movie order by movreleasedate desc")
    List<Movie> findAllOrderBYMovreleasedateDesc();

    Optional<Movie> findByMovno(Long movno);

    Movie getMovieByMovno(Long movno);
}
