package ir.oveissi.searchmovies.features.moviedetail;


import ir.oveissi.searchmovies.features.moviesearch.MovieSearchContract;
import ir.oveissi.searchmovies.pojo.Movie;
import ir.oveissi.searchmovies.utils.bases.IPresenter;
import ir.oveissi.searchmovies.utils.bases.IView;

/**
 * Created by Abbas on 24/05/2016.
 */
public interface MovieDetailContract {

    interface View extends IView<MovieSearchContract.Presenter> {

        void showMovieDetail(Movie movie);


    }

    interface Presenter extends IPresenter<MovieDetailContract.View> {
        void getMovieDetailFromWebservice(String id);
    }
}
