package com.movieapp.pokemonapp.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.movieapp.pokemonapp.R;
import com.movieapp.pokemonapp.adapter.PokemonAdapter;
import com.movieapp.pokemonapp.utils.NetworkUtility;
import com.movieapp.pokemonapp.viewmodel.PokemonViewModel;

import java.util.Objects;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateList();
    }

    /**
     * This method populates the RecyclerView with
     * the help of the ViewModel
     */
    public void populateList() {

        NetworkUtility networkUtility = new NetworkUtility(this);
        if (networkUtility.isOnline()) {
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            recyclerView.setHasFixedSize(true);

            PokemonViewModel pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
            PokemonAdapter pokemonAdapter = new PokemonAdapter(this);

            pokemonViewModel.resultPagedList.observe(this, pokemonAdapter::submitList);

            SlideInLeftAnimator animator = new SlideInLeftAnimator(new OvershootInterpolator(1f));
            recyclerView.setItemAnimator(animator);
            Objects.requireNonNull(recyclerView.getItemAnimator()).setAddDuration(1200);
            recyclerView.setAdapter(pokemonAdapter);
        } else {
            Toast.makeText(this, "Check your internet connection.", Toast.LENGTH_LONG).show();
        }
    }
}
