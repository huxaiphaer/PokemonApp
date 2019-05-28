package com.movieapp.pokemonapp.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.movieapp.pokemonapp.R;
import com.movieapp.pokemonapp.adapter.PokemonAdapter;
import com.movieapp.pokemonapp.data.remote.RetrofitClient;
import com.movieapp.pokemonapp.model.PokemonApiResponse;
import com.movieapp.pokemonapp.model.Result;
import com.movieapp.pokemonapp.viewmodel.PokemonViewModel;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        PokemonViewModel pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        final PokemonAdapter pokemonAdapter = new PokemonAdapter(MainActivity.this);

        pokemonViewModel.resultPagedList.observe(MainActivity.this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(@Nullable PagedList<Result> results) {

                 pokemonAdapter.submitList(results);
            }
        });

        recyclerView.setAdapter(pokemonAdapter);


    }
}
