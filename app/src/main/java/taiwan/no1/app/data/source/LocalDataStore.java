package taiwan.no1.app.data.source;

import android.support.annotation.Nullable;

import rx.Observable;
import taiwan.no1.app.data.entities.CastDetailEntity;
import taiwan.no1.app.data.entities.MovieCastsResEntity;
import taiwan.no1.app.data.entities.MovieDetailEntity;
import taiwan.no1.app.data.entities.PopularResEntity;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

public class LocalDataStore implements IDataStore {
    @Nullable
    @Override
    public Observable<PopularResEntity> popularMovieEntities(final int page) {
        throw new Error("No-op");
    }

    @Nullable
    @Override
    public Observable<MovieDetailEntity> movieDetailEntities(final int id) {
        throw new Error("No-op");
    }

    @Nullable
    @Override
    public Observable<MovieCastsResEntity> movieCastsEntities(final int id) {
        throw new Error("No-op");
    }

    @Nullable
    @Override
    public Observable<CastDetailEntity> castDetailEntities(final int id) {
        throw new Error("No-op");
    }
}