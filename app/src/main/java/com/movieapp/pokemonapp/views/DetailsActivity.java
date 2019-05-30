package com.movieapp.pokemonapp.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movieapp.pokemonapp.R;
import com.movieapp.pokemonapp.model.DetailsModal;
import com.movieapp.pokemonapp.viewmodel.DetailViewModal;

import static com.movieapp.pokemonapp.adapter.PokemonAdapter.IMAGE_PATH;
import static com.movieapp.pokemonapp.adapter.PokemonAdapter.POKEMON_ID;
import static com.movieapp.pokemonapp.adapter.PokemonAdapter.POKEMON_NAME;

public class DetailsActivity extends AppCompatActivity {

    DetailViewModal detailViewModal;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pokemon Details");

        intent = getIntent();

        String getId = intent.getStringExtra(POKEMON_ID);
        String imageUrl = intent.getStringExtra(IMAGE_PATH);
        String pokemonName = intent.getStringExtra(POKEMON_NAME);

        if (intent != null) {
            int id = Integer.parseInt(getId);
            detailViewModal = ViewModelProviders.of(this).get(DetailViewModal.class);
            // Initialize the view modal.
            detailViewModal.getDetailsLiveData(id).observe(this, detailsModal -> populateViews(detailsModal, imageUrl, pokemonName));
        }


    }


    /**
     * This is helps to enable the back button on the toolbar.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    /***
     * This method is capable for populating data to the views.
     * @param detailsModal This is the Modal class.
     * @param imageUrl This is the parsed image Url.
     * @param pokemonName This is the pokemon Name.
     */
    private void populateViews(DetailsModal detailsModal, String imageUrl, String pokemonName) {

        String effect = detailsModal.mEffectEntries.get(0).mEffect.toString();

        ImageView details_img = findViewById(R.id.details_img);
        TextView description_details_txt = findViewById(R.id.description_details_txt);
        TextView name_details_txt = findViewById(R.id.name_details_txt);


        Glide.with(DetailsActivity.this)
                .load(imageUrl)
                .into(details_img);

        name_details_txt.setText(pokemonName);
        description_details_txt.setText(effect);

    }
}
