package ru.test.sibers.pokeapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ru.test.sibers.pokeapp.adapter.PokemonEvolutionAdapter;
import ru.test.sibers.pokeapp.adapter.PokemonTypeAdapter;
import ru.test.sibers.pokeapp.common.Common;
import ru.test.sibers.pokeapp.model.Pokemon;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonDetailFragment extends Fragment {

    ImageView pokemonImage;
    TextView pokemonName, pokemonHeight, pokemonWeight;
    RecyclerView recyclerType, recyclerWeakness, recyclerNextEvo, recyclerPrevEvo;

    static PokemonDetailFragment instance;

    public static PokemonDetailFragment getInstance() {
        if (instance == null)
            instance = new PokemonDetailFragment();
        return instance;
    }

    public PokemonDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);

        Pokemon pokemon;

        if (getArguments().get("num") == null)
            pokemon = Common.commonPokemonList.get(getArguments().getInt("position"));
        else
            pokemon = null;

        pokemonImage = view.findViewById(R.id.pokemon_image);
        pokemonName = view.findViewById(R.id.pokemon_name);
        pokemonWeight = view.findViewById(R.id.weight);
        pokemonHeight = view.findViewById(R.id.height);

        recyclerType = view.findViewById(R.id.recycler_type);
        recyclerType.setHasFixedSize(true);
        recyclerType.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        recyclerWeakness = view.findViewById(R.id.recycler_weakness);
        recyclerWeakness.setHasFixedSize(true);
        recyclerWeakness.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        recyclerNextEvo = view.findViewById(R.id.recycler_next);
        recyclerNextEvo.setHasFixedSize(true);
        recyclerNextEvo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        recyclerPrevEvo = view.findViewById(R.id.recycler_prev);
        recyclerPrevEvo.setHasFixedSize(true);
        recyclerPrevEvo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        setDetailPokemon(pokemon);

        return view;
    }

    private void setDetailPokemon(Pokemon pokemon) {
        Glide.with(getActivity()).load(pokemon.getImg()).into(pokemonImage);

        pokemonName.setText(pokemon.getName());

        pokemonHeight.setText("Height: " + pokemon.getHeight());
        pokemonWeight.setText("Weight: " + pokemon.getWeight());

        //Set type
        PokemonTypeAdapter typeAdapter = new PokemonTypeAdapter(getActivity(), pokemon.getType());
        recyclerType.setAdapter(typeAdapter);

        //Set Weakness
        PokemonTypeAdapter weaknessAdapter = new PokemonTypeAdapter(getActivity(), pokemon.getWeaknesses());
        recyclerWeakness.setAdapter(weaknessAdapter);

        //Set Evolution
        PokemonEvolutionAdapter nextEvoAdapter = new PokemonEvolutionAdapter(getActivity(), pokemon.getEvolution());
        recyclerNextEvo.setAdapter(weaknessAdapter);

        //Set Evolution
        PokemonEvolutionAdapter prevEvoAdapter = new PokemonEvolutionAdapter(getActivity(), pokemon.getPrevEvolution());
        recyclerPrevEvo.setAdapter(weaknessAdapter);
    }
}
