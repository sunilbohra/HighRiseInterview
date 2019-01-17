package com.example.sbohr002.myapplication.com.example.sbohr002.myapplication.data;

public class DataCache {
    private static DataCache mDataCache = null;

    /**
     * Handle to DataCache class. If an instance of DataCache is created then it
     * is returned else a new instance is created. This guarantees that in
     * application's life cycle, there is only one instance of DataCache
     * created.
     *
     * @return mDataCache
     */
    public static DataCache getDataCache() {
        if (mDataCache == null) {
            mDataCache = new DataCache();
        }
        return mDataCache;
    }
    private MoviesController.MovieDetails movieDetails ;

    public MoviesController.MovieDetails getMovieDetails(){
        return movieDetails;
    }

    public void setMovieDetails(MoviesController.MovieDetails  pMovieDetails){
        this.movieDetails = pMovieDetails;
    }
}
