package com.movieapp.pokemonapp.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.movieapp.pokemonapp.R;
import com.movieapp.pokemonapp.model.Result;
import com.movieapp.pokemonapp.utils.Utils;
import com.movieapp.pokemonapp.views.DetailsActivity;

public class PokemonAdapter extends PagedListAdapter<Result, PokemonAdapter.PokemonHolder> {

    public static final String POKEMON_ID = "POKEMON_ID";
    public static final String IMAGE_PATH = "IMAGE_PATH";
    public static final String POKEMON_NAME = "POKEMON_NAME";
    private static DiffUtil.ItemCallback<Result> diffCallback = new DiffUtil.ItemCallback<Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull Result oldResult, @NonNull Result newResult) {

            String[] oldUrlPattern = oldResult.mUrl.split("/");
            String[] newUrlPatter = newResult.mUrl.split("/");
            return Integer.parseInt(oldUrlPattern[oldUrlPattern.length - 1]) == Integer.parseInt(newUrlPatter[newUrlPatter.length - 1]);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Result oldResult, @NonNull Result newResult) {
            return oldResult.equals(newResult);
        }
    };
    private Context context;


    public PokemonAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    /***
     * This methods creates the viewholder with respect to the item layout.
     * @param viewGroup View Group.
     * @param i Item position.
     * @return Returns the Holder Object.
     */
    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pokemon, viewGroup, false);
        return new PokemonHolder(layoutView);
    }

    /**
     * This is where the binding of the views occurs.
     *
     * @param pokemonHolder This allows to access views in the holder class.
     * @param i             This is the item position
     */
    @Override
    public void onBindViewHolder(@NonNull PokemonHolder pokemonHolder, int i) {

        final Result result = getItem(i);

        if (result != null) {
            final String pokemonName = result.mName;
            pokemonHolder.pokemonName.setText(pokemonName);
            final String RANDOM_IMAGE = Utils.IMAGE_BASE_URL.concat(result.getId() + ".png");

            Glide.with(PokemonAdapter.this.context)
                    .load(RANDOM_IMAGE)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                            pokemonHolder.imageLoaderPb.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            pokemonHolder.imageLoaderPb.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(pokemonHolder.pokemonImg);

            pokemonHolder.itemView.setOnClickListener(v -> {

                Intent intent = new Intent(PokemonAdapter.this.context, DetailsActivity.class);
                intent.putExtra(POKEMON_ID, String.valueOf(result.getId()));
                intent.putExtra(IMAGE_PATH, RANDOM_IMAGE);
                intent.putExtra(POKEMON_NAME, pokemonName);
                context.startActivity(intent);
            });

        } else {
            Toast.makeText(PokemonAdapter.this.context, "Check your internet connection", Toast.LENGTH_LONG).show();
        }

    }

    class PokemonHolder extends RecyclerView.ViewHolder {

        View root;
        ImageView pokemonImg;
        TextView pokemonName;
        ProgressBar imageLoaderPb;

        /**
         * Holder Constructor to initialize the views.
         *
         * @param itemView This is the itemView.
         */
        PokemonHolder(@NonNull View itemView) {
            super(itemView);

            root = itemView;
            pokemonImg = itemView.findViewById(R.id.pokemonImg);
            pokemonName = itemView.findViewById(R.id.pokemonName);
            imageLoaderPb = itemView.findViewById(R.id.imageLoader_pb);
        }
    }
}
