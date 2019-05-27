package com.movieapp.pokemonapp.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movieapp.pokemonapp.R;
import com.movieapp.pokemonapp.model.Result;
import com.movieapp.pokemonapp.utils.Utils;

import java.util.ArrayList;

public class PokemonAdapter extends PagedListAdapter<Result, PokemonAdapter.PokemonHolder> {


    private static DiffUtil.ItemCallback<Result> DIFF_CALLBACK = new DiffUtil.ItemCallback<Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull Result oldResult, @NonNull Result newResult) {
            return oldResult.getUrl() == newResult.getUrl();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Result oldResult, @NonNull Result newResult) {
            return oldResult.equals(newResult);
        }
    };
    private ArrayList<Result> resultList;
    private Context context;


    public PokemonAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pokemon, viewGroup, false);
        return new PokemonHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder pokemonHolder, int i) {

        final Result result = resultList.get(i);
        final String pokemonName = result.getName();
        pokemonHolder.pokemonName.setText(pokemonName);

        Glide.with(context)
                .load(Utils.IMAGE_BASE_URL.concat("1.png"))
                .into(pokemonHolder.pokemonImg);

    }

    static final class PokemonHolder extends RecyclerView.ViewHolder {

        private View root;
        private ImageView pokemonImg;
        private TextView pokemonName;

        public PokemonHolder(@NonNull View itemView) {
            super(itemView);

            root = itemView;
            pokemonImg = itemView.findViewById(R.id.pokemonImg);
            pokemonName = itemView.findViewById(R.id.pokemonName);
        }
    }
}
