package ru.test.sibers.pokeapp.model;

import java.util.List;

public class Pokedex {

    private List<Pokemon> mPokemonList;

    public Pokedex() {

    }

    public Pokedex(List<Pokemon> pokemonList) {
        mPokemonList = pokemonList;
    }

    public List<Pokemon> getPokemonList() {
        return mPokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        mPokemonList = pokemonList;
    }
}
