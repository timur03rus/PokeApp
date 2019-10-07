package ru.test.sibers.pokeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;

import ru.test.sibers.pokeapp.common.Common;
import ru.test.sibers.pokeapp.model.Pokemon;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;

    BroadcastReceiver showDetail = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Common.KEY_ENABLE_HOME)) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);

                Fragment detailFragment = PokemonDetailFragment.getInstance();
                String num = intent.getStringExtra("num");
                Bundle bundle = new Bundle();
                bundle.putString("num", num);
                detailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.pokemon_list_recyclerview, detailFragment);
                fragmentTransaction.addToBackStack("detail");
                fragmentTransaction.commit();

                Pokemon pokemon = Common.findPokemonByNum(num);
                mToolbar.setTitle(pokemon.getName());
            }
        }
    };

    BroadcastReceiver showEvolution = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Common.KEY_NUM_EVOLUTION)) {

                Fragment detailFragment = PokemonDetailFragment.getInstance();
                Bundle bundle = new Bundle();
                String num = intent.getStringExtra("num");
                bundle.putString("num", num);
                detailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.remove(detailFragment);
                fragmentTransaction.replace(R.id.pokemon_list_recyclerview, detailFragment);
                fragmentTransaction.addToBackStack("detail");
                fragmentTransaction.commit();

                Pokemon pokemon = Common.findPokemonByNum(num);
                mToolbar.setTitle(pokemon.getName());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Pikachu");
        setSupportActionBar(mToolbar);

        //Register broadcast
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showDetail, new IntentFilter(Common.KEY_ENABLE_HOME));

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showEvolution, new IntentFilter(Common.KEY_NUM_EVOLUTION));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mToolbar.setTitle("Pokemon List");
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
                getSupportFragmentManager().popBackStack("detail", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
                default:
                    break;
        }
        return true;
    }
}
