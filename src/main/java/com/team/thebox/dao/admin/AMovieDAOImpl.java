package com.team.thebox.dao.admin;

import com.team.thebox.model.*;
import com.team.thebox.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("amovdao")
public class AMovieDAOImpl implements AMovieDAO {
    private final MovieRepository movieRepository;
    private final MovieStillcutRepository movieStillcutRepository;
    private final MovieAttachRepository movieAttachRepository;
    private final MovieReplyRepository movieReplyRepository;
    private final MovieScheduleRepository movieScheduleRepository;
    private final BookedRepository bookedRepository;
    private final MovieLocationRepository movieLocationRepository;

    @Autowired
    public AMovieDAOImpl(MovieRepository movieRepository, MovieStillcutRepository movieStillcutRepository, MovieAttachRepository movieAttachRepository, MovieReplyRepository movieReplyRepository, MovieScheduleRepository movieScheduleRepository, BookedRepository bookedRepository,
                         MovieLocationRepository movieLocationRepository) {
        this.movieRepository = movieRepository;
        this.movieStillcutRepository = movieStillcutRepository;
        this.movieAttachRepository = movieAttachRepository;
        this.movieReplyRepository = movieReplyRepository;
        this.movieScheduleRepository = movieScheduleRepository;
        this.bookedRepository = bookedRepository;
        this.movieLocationRepository = movieLocationRepository;
    }

    @Override
    public Long insertMovie(Movie movie) {
        return movieRepository.save(movie).getMovno();
    }

    @Override
    public int insertMovieAttach(MovieAttach ma) {

        return Math.toIntExact(movieAttachRepository.save(ma).getMovano());
    }


    @Override
    public Map<String, Object> selectMovie(int cpg) {
        Pageable paging = PageRequest.of(cpg, 25, Sort.Direction.DESC, "movno");
        Map<String, Object> movs = new HashMap<>();
        movs.put("movlist", movieRepository.findAll(paging).getContent());
        movs.put("cntpg", movieRepository.findAll(paging).getTotalPages());
        return movs;
    }

//    @Override
//    public List<String> selectMovieTitle(long movno) {
//        return movieRepository.findMovTitleByMovno(movno);
//    }
    @Override //now
    public Map<String, Object> selectMovie() {
        Map<String, Object> movs = new HashMap<>();
        movs.put("mlist", movieRepository.findAll() );
        return movs;
    }

    @Override
    public int insertMovieReply(MovieReply reply) {
        return  Math.toIntExact(movieReplyRepository.save(reply).getMovno() );
    }

    @Override
    public List<MovieReply> selectOneMovieReply(Long movno) {
        return movieReplyRepository.findByMovnoOrderByRegdateAsc(movno);
    }

    @Override
    public Object selectOneMovie(Long movno) {

        return movieStillcutRepository.findAllByMovno(movno);
    }

    @Override
    public Long updateMovie(Movie updatemovie) {
        System.out.println("저장직전repository"+updatemovie);

        return movieRepository.save(updatemovie).getMovno();

    }

    @Override
    public List<String> selectMovieTitle() {
        //return movieRepository.findMovTitleByMovno(movno);
        return null;
    }

    @Override
    public int insertMovieSchedule(MovieSchedule movsch) {
        return Math.toIntExact(movieScheduleRepository.save(movsch).getSchno());
    }

    @Override
    public List<Movie> selectMovnoAndTitle() {
        return movieRepository.findAll();
    }

    @Override
    public List<MovieSchedule> selectMovieSchdule() {
        return movieScheduleRepository.findAll();
    }

    @Override
    public List<Integer> selectBookedCnt() {
        return bookedRepository.countTotalSeatIds();
    }

    @Override
    public Map<String, Object> selectScheduleList(Long movno, Long schno) {
        Map<String, Object> movschlist = new HashMap<>();
        movschlist.put("movschlist", movieScheduleRepository.findAll());
        movschlist.put("movtitle", movieRepository.findMovTitleByMovno(movno));
        movschlist.put("booked", bookedRepository.countTotalSeatIdsBySchno(schno));
        return movschlist;
    }

    @Override
    public void deleteMovieByMovno(Long movno) {
        movieRepository.deleteById(movno);
    }

    @Override
    public void insertMovieInfo(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public List<Movie> selectAllMovie() {
        return movieRepository.findAll();
    }

    @Override
    public Long insertMovieStillcut(MovieStillcut ms) {
        return movieStillcutRepository.save(ms).getId();
    }
//    @Override
//    public List<Movie> selectMovnoAndTitle() {
//        return movieRepository.findAll();
//    }
    @Override
    public List<Movielocation> selectLocation() {
        return movieLocationRepository.findAll();
    }

    @Override
    public Movielocation selectMovieLocation(Long ciplace) {
        return movieLocationRepository.findDistrictNameByLocationNum(ciplace);
    }

    @Override
    public Movie getMovieByMovno(Long movno) {
        return movieRepository.getMovieByMovno(movno);
    }


}
