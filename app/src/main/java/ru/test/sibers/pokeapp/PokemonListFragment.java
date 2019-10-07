package ru.test.sibers.pokeapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import ru.test.sibers.pokeapp.adapter.PokemonListAdapter;
import ru.test.sibers.pokeapp.client.IPokemonDex;
import ru.test.sibers.pokeapp.client.RetrofitClient;
import ru.test.sibers.pokeapp.common.Common;
import ru.test.sibers.pokeapp.common.ItemOffsetDecoration;
import ru.test.sibers.pokeapp.model.Pokedex;

public class PokemonListFragment extends Fragment {

    IPokemonDex mIPokemonDex;
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    RecyclerView mRecyclerViewPokemonList;

    static PokemonListFragment instance;

    public static PokemonListFragment getInstance() {
        if (instance == null)
            instance = new PokemonListFragment();
        return instance;
    }

    public PokemonListFragment() {
        Retrofit retrofit = RetrofitClient.getInstance();
        mIPokemonDex = retrofit.create(IPokemonDex.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        mRecyclerViewPokemonList = view.findViewById(R.id.pokemon_list_recyclerview);
        mRecyclerViewPokemonList.setHasFixedSize(true);
        mRecyclerViewPokemonList.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.spacing);
        mRecyclerViewPokemonList.addItemDecoration(itemOffsetDecoration);

        fetchData();

        return view;
    }

    private void fetchData() {
        mCompositeDisposable.add(mIPokemonDex.getListPokemon()
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pokedex>() {
                    @Override
                    public void accept(Pokedex pokedex) throws Exception {
                        Common.commonPokemonList = pokedex.getPokemonList();
                        PokemonListAdapter adapter = new PokemonListAdapter(getActivity(), Common.commonPokemonList);

                        mRecyclerViewPokemonList.setAdapter(adapter);
                    }
                })
        );
    }
}
