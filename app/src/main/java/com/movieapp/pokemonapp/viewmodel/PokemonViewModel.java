package com.movieapp.pokemonapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.movieapp.pokemonapp.data.ResultDataSourceFactory;
import com.movieapp.pokemonapp.model.Result;

import static com.movieapp.pokemonapp.data.ResultDataSource.PAGE_LIMIT;

public class PokemonViewModel extends ViewModel {

    public LiveData<PagedList<Result>> resultPagedList;
    public LiveData<PageKeyedDataSource<Integer, Result>> liveDataSource;

    public PokemonViewModel() {

        ResultDataSourceFactory resultDataSourceFactory = new ResultDataSourceFactory();
        liveDataSource = resultDataSourceFactory.getResultLiveDataSource();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_LIMIT)
                .build();


        resultPagedList = (new LivePagedListBuilder(resultDataSourceFactory, config)).build();
    }

}

