package ru.test.sibers.pokeapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

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
import ru.test.sibers.pokeapp.model.Pokemon;

public class PokemonListFragment extends Fragment {

    IPokemonDex mIPokemonDex;
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    RecyclerView mRecyclerViewPokemonList;

    PokemonListAdapter adapter, searchAdapter;
    List<String> lastSuggest = new ArrayList<>();

    MaterialSearchBar mSearchBar;

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

        //Setup search bar
        mSearchBar = view.findViewById(R.id.search_bar);
        mSearchBar.setHint("Enter Pokemon name");
        mSearchBar.setCardViewElevation(10);
        mSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();
                for (String search :
                        lastSuggest) {
                    if (search.toLowerCase().contains(mSearchBar.getText().toLowerCase()))
                        suggest.add(search);

                    mSearchBar.setLastSuggestions(suggest);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled)
                    mRecyclerViewPokemonList.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        fetchData();

        return view;
    }

    private void startSearch(CharSequence text) {
        if (Common.commonPokemonList.size() > 0) {
            List<Pokemon> result = new ArrayList<>();
            for (Pokemon pokemon :
                    Common.commonPokemonList) {
                if (pokemon.getName().toLowerCase().contains(text.toString().toLowerCase()))
                    result.add(pokemon);
                searchAdapter = new PokemonListAdapter(getActivity(), result);
                mRecyclerViewPokemonList.setAdapter(searchAdapter);
            }
        }
    }

    private void fetchData() {
        mCompositeDisposable.add(mIPokemonDex.getListPokemon()
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pokedex>() {
                    @Override
                    public void accept(Pokedex pokedex) throws Exception {
                        Common.commonPokemonList = pokedex.getPokemonList();
                        adapter = new PokemonListAdapter(getActivity(), Common.commonPokemonList);

                        mRecyclerViewPokemonList.setAdapter(adapter);

                        lastSuggest.clear();
                        for (Pokemon pokemon :
                                Common.commonPokemonList) {
                            lastSuggest.add(pokemon.getName());
                        }
                        mSearchBar.setVisibility(View.VISIBLE);
                        mSearchBar.setLastSuggestions(lastSuggest);
                    }
                })
        );
    }
}
