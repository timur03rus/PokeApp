package ru.test.sibers.pokeapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.robertlevonyan.views.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import ru.test.sibers.pokeapp.R;
import ru.test.sibers.pokeapp.common.Common;
import ru.test.sibers.pokeapp.interfaces.IItemClickListener;
import ru.test.sibers.pokeapp.model.Evolution;

public class PokemonEvolutionAdapter extends RecyclerView.Adapter<PokemonEvolutionAdapter.PokemonEvolutionViewHolder> {

    Context mContext;
    List<Evolution> mEvolutions;

    public PokemonEvolutionAdapter(Context context, List<Evolution> evolutions) {
        mContext = context;
        if (evolutions != null)
            mEvolutions = evolutions;
        else
            this.mEvolutions = new ArrayList<>();
    }

    @NonNull
    @Override
    public PokemonEvolutionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chip_item, parent, false);

        return new PokemonEvolutionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonEvolutionViewHolder holder, int position) {
        holder.chip.setText(mEvolutions.get(position).getName());
        holder.chip.setBackgroundColor(
                Common.getColorByType(
                        Common.findPokemonByNum(
                                mEvolutions.get(position).getNum()
                        ).getType()
                        .get(0)
                )
        );
    }

    @Override
    public int getItemCount() {
        return mEvolutions.size();
    }

    public class PokemonEvolutionViewHolder extends RecyclerView.ViewHolder {

        Chip chip;

        public PokemonEvolutionViewHolder(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chip);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LocalBroadcastManager.getInstance(mContext)
                            .sendBroadcast(new Intent(Common.KEY_NUM_EVOLUTION)
                                    .putExtra("num", mEvolutions.get(getAdapterPosition()).getNum()));
                }
            });
        }
    }
}
