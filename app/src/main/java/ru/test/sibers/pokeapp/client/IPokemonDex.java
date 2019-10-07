package ru.test.sibers.pokeapp.client;

import io.reactivex.Observable;
import retrofit2.http.GET;
import ru.test.sibers.pokeapp.model.Pokedex;

public interface IPokemonDex {
    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();
}
