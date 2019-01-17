package com.example.sbohr002.myapplication.com.example.sbohr002.myapplication.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sbohr002.myapplication.R;
import com.example.sbohr002.myapplication.com.example.sbohr002.myapplication.data.DataCache;
import com.example.sbohr002.myapplication.com.example.sbohr002.myapplication.data.MoviesController;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * This fragment will show the details of the movie including the name, description , score and the actors list.
 */
public class MovieDetailsFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_movie_details, container, false);
        TextView movieName = layout.findViewById(R.id.movieNameTxt);
        TextView description = layout.findViewById(R.id.description);
        TextView score = layout.findViewById(R.id.score);
        RecyclerView recyclerView = layout.findViewById(R.id.actorsList);
        ImageView backBtn = layout.findViewById(R.id.back_button);
        MoviesController.MovieDetails movieDetails = DataCache.getDataCache().getMovieDetails();
        if (movieDetails != null) {
            movieName.setText(movieDetails.getName());
            description.setText(movieDetails.getDescription());
            score.setText(String.valueOf(movieDetails.getScore()));
            GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView.setLayoutManager(linearLayoutManager);
            ActorsAdapter moviesAdapter = new ActorsAdapter(movieDetails.getActors());
            recyclerView.setAdapter(moviesAdapter);
            //Added a back button for devices which dont have a physical back button and for ease of access.
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getActivity() != null)
                        getActivity().onBackPressed();
                }
            });
        }
        return layout;
    }

    public static MovieDetailsFragment newInstance() {
        return new MovieDetailsFragment();
    }

    private class ActorsAdapter extends RecyclerView.Adapter<ActorsViewHolder> {
        ArrayList<MoviesController.Actors> actorsList;

        ActorsAdapter(ArrayList<MoviesController.Actors> actors) {
            this.actorsList = actors;

        }

        @NonNull
        @Override
        public ActorsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.actors_row, viewGroup, false);
            return new ActorsViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ActorsViewHolder actorsViewHolder, int i) {
            actorsViewHolder.txtActorName.setText(actorsList.get(i).getName());
            actorsViewHolder.txtAge.setText(String.valueOf(actorsList.get(i).getAge()));
            actorsViewHolder.txtImageView.setVisibility(View.VISIBLE);
            Picasso.with(getActivity())
                    .load(actorsList.get(i).getImageUrl())
                    .placeholder(R.drawable.place_holder)
                    .config(Bitmap.Config.RGB_565)
                    .into(actorsViewHolder.txtImageView);

        }


        @Override
        public int getItemCount() {
            return actorsList.size();
        }
    }


    private class ActorsViewHolder extends RecyclerView.ViewHolder {
        private TextView txtActorName;
        private TextView txtAge;
        private ImageView txtImageView;


        ActorsViewHolder(View itemView) {
            super(itemView);
            txtActorName = itemView.findViewById(R.id.actor_name);
            txtAge = itemView.findViewById(R.id.age);
            txtImageView = itemView.findViewById(R.id.image);
        }
    }
}
