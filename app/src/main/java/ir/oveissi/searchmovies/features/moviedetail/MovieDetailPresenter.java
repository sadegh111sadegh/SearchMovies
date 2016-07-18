/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ir.oveissi.searchmovies.features.moviedetail;

import android.util.Log;

import ir.oveissi.searchmovies.interactors.MovieInteractor;
import ir.oveissi.searchmovies.pojo.Movie;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private final MovieInteractor mMovieInteractor;
    private final MovieDetailContract.View mtView;
    private CompositeSubscription mSubscriptions;
    private static final String TAG="MovieSearchPresenter";

    public MovieDetailPresenter(MovieInteractor mMovieInteractor, MovieDetailContract.View tView) {
        this.mMovieInteractor = mMovieInteractor;
        this.mtView = tView;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void getMovieDetailFromWebservice(String id) {
                Subscription mSubscription=
                mMovieInteractor.getMovieByID(id)
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            Log.d(TAG, "onError StatusCode: "+((HttpException) e).code());
                        }
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onNext(Movie movie) {
                        Log.d(TAG, "onNext");
                        mtView.showMovieDetail(movie);
                    }
                });
        mSubscriptions.add(mSubscription);
    }



}