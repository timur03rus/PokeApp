package ru.test.sibers.pokeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robertlevonyan.views.chip.Chip;

import java.lang.reflect.Type;
import java.util.List;

import ru.test.sibers.pokeapp.R;
import ru.test.sibers.pokeapp.common.Common;
import ru.test.sibers.pokeapp.interfaces.IItemClickListener;

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.PokemonTypeViewHolder> {

    Context mContext;
    List<String> mTypeList;

    public PokemonTypeAdapter(Context context, List<String> typeList) {
        mContext = context;
        mTypeList = typeList;
    }

    @NonNull
    @Override
    public PokemonTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chip_item, parent, false);

        return new PokemonTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonTypeViewHolder holder, int position) {
        holder.chip.setText(mTypeList.get(position));
        holder.chip.setChipBackgroundColor(Common.getColorByType(mTypeList.get(position)));
        holder.setIItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mTypeList.size();
    }

    public class PokemonTypeViewHolder extends RecyclerView.ViewHolder {

        Chip chip;
        IItemClickListener mIItemClickListener;

        public void setIItemClickListener(IItemClickListener IItemClickListener) {
            mIItemClickListener = IItemClickListener;
        }

        public PokemonTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chip);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIItemClickListener.onClick(v, getAdapterPosition());
                }
            });
        }
    }
}
