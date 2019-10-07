package ru.test.sibers.pokeapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import ru.test.sibers.pokeapp.R;
import ru.test.sibers.pokeapp.common.Common;
import ru.test.sibers.pokeapp.interfaces.IItemClickListener;
import ru.test.sibers.pokeapp.model.Pokemon;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder> {

    Context mContext;
    List<Pokemon> mPokemonList;

    public PokemonListAdapter(Context context, List<Pokemon> pokemonList) {
        mContext = context;
        mPokemonList = pokemonList;
    }

    public class PokemonListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView pokemonImage;
        TextView pokemonName;

        IItemClickListener mIItemClickListener;

        public void setIItemClickListener(IItemClickListener IItemClickListener) {
            mIItemClickListener = IItemClickListener;
        }

        public PokemonListViewHolder(@NonNull View itemView) {
            super(itemView);

            pokemonImage = itemView.findViewById(R.id.pokemon_image);
            pokemonName = itemView.findViewById(R.id.pokemon_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mIItemClickListener.onClick(v, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public PokemonListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pokemon_list_item, parent, false);

        return new PokemonListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonListViewHolder holder, int position) {
        Glide.with(mContext).load(mPokemonList.get(position).getImg()).into(holder.pokemonImage);
        holder.pokemonName.setText(mPokemonList.get(position).getName());

        holder.setIItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                LocalBroadcastManager.getInstance(mContext)
                        .sendBroadcast(new Intent(Common.KEY_ENABLE_HOME).putExtra("num", mPokemonList.get(position).getNum()));
            }
        });
    }

    @Override
    public int getItemCount() {
          return mPokemonList.size();
    }
}
