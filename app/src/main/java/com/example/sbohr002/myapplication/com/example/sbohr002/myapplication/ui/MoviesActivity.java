package com.example.sbohr002.myapplication.com.example.sbohr002.myapplication.ui;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sbohr002.myapplication.R;
import com.example.sbohr002.myapplication.com.example.sbohr002.myapplication.data.DataCache;
import com.example.sbohr002.myapplication.com.example.sbohr002.myapplication.data.MoviesController;

import java.util.ArrayList;


/**
 * This class shows the list of movies given by the movieController.
 */

public class MoviesActivity extends AppCompatActivity {

//    public native ArrayList<Movies> getMovies();

    static {
        System.loadLibrary("MyApplication3");
    }

    private MoviesController moviesController;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRelativeLayout =  findViewById(R.id.movieListContainer);
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.moviesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        moviesController = new MoviesController();
        MoviesAdapter moviesAdapter = new MoviesAdapter(moviesController.getMovies());
        recyclerView.setAdapter(moviesAdapter);
       
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mRelativeLayout.setVisibility(View.VISIBLE);
    }

    private class MoviesAdapter extends RecyclerView.Adapter<MoviesViewHolder> {
        ArrayList<MoviesController.Movie> moviesList;

        MoviesAdapter(ArrayList<MoviesController.Movie> movies) {
            moviesList = movies;
        }

        @NonNull
        @Override
        public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movies_row, viewGroup, false);
            return new MoviesViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MoviesViewHolder moviesViewHolder, int i) {
            moviesViewHolder.txtMoviewName.setText(moviesList.get(i).getName());
            moviesViewHolder.txtMovieLastUpdated.setText(String.valueOf(moviesList.get(i).getLastUpdated()));

        }

        @Override
        public int getItemCount() {
            return moviesList.size();
        }
    }

    private class MoviesViewHolder extends RecyclerView.ViewHolder {
        public TextView txtMoviewName;
        public TextView txtMovieLastUpdated;

        MoviesViewHolder(View itemView) {
            super(itemView);
            txtMoviewName = itemView.findViewById(R.id.movieName);
            txtMovieLastUpdated = itemView.findViewById(R.id.movie_last_updated);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataCache dataCache = DataCache.getDataCache();
                    dataCache.setMovieDetails(moviesController.getMovieDetail(txtMoviewName.getText().toString()));
                    //Since the fragment will be in the same container, we need to hide the recycler view and the header and make it back to visible on back press.
                        mRelativeLayout.setVisibility(View.GONE);

                    //Starting the fragment to show movie details
                    MovieDetailsFragment movieDetailsFragment = MovieDetailsFragment.newInstance();
                    FragmentTransaction beginTransaction = getSupportFragmentManager()
                            .beginTransaction();
                        beginTransaction.addToBackStack(movieDetailsFragment.getClass().getName());

                    beginTransaction.replace(R.id.container, movieDetailsFragment, movieDetailsFragment.getClass().getName());
                    try {
                        beginTransaction.commit();
                    } catch (Exception e) {
                        beginTransaction.commitAllowingStateLoss();
                    }
                }
            });
        }
    }
}
