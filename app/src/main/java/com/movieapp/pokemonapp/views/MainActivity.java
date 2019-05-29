package com.movieapp.pokemonapp.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.movieapp.pokemonapp.R;
import com.movieapp.pokemonapp.adapter.PokemonAdapter;
import com.movieapp.pokemonapp.viewmodel.PokemonViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

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

        recyclerView = findViewById(R.id.recyclerView);
        Context context=recyclerView.getContext();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        PokemonViewModel pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        PokemonAdapter pokemonAdapter = new PokemonAdapter(this);

        pokemonViewModel.resultPagedList.observe(this, results -> {

            pokemonAdapter.submitList(results);

        });

        LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_slide_from_bottom);

        recyclerView.setAdapter(pokemonAdapter);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();


    }
}
