package com.movieapp.pokemonapp.models;

import com.movieapp.pokemonapp.model.PokemonApiResponse;
import com.movieapp.pokemonapp.model.Result;

import junit.framework.TestCase;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class ModelsTest extends TestCase {

    private PokemonApiResponse pokemonApiResponse;
    private Result result;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        pokemonApiResponse = new PokemonApiResponse();
        result = new Result();
    }

    public void testCountItems() {

        Long expected = 1L;
        pokemonApiResponse.mCount = expected;
        Long actual = pokemonApiResponse.mCount;
        Assert.assertEquals(expected, actual);
    }

    public void testResults() {

        List<Result> expectedResultList = new ArrayList<>();
        expectedResultList.add(new Result("pokemon", "xxxx"));
        pokemonApiResponse.mResults = expectedResultList;
        List<Result> actualResultList = pokemonApiResponse.mResults;
        Assert.assertEquals(expectedResultList, actualResultList);
    }

    public void testPokemonName() {

        String expectedName = "pikachu";
        result.mName = expectedName;
        String actualName = result.mName;
        Assert.assertEquals(expectedName, actualName);
    }


}
