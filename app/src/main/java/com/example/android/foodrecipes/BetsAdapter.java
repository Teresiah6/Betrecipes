package com.example.android.foodrecipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;




public class BetsAdapter extends RecyclerView.Adapter<BetsAdapter.BetViewHolder> {

    ArrayList<Bet> bets;
    public BetsAdapter(ArrayList<Bet> bets){

        this.bets = bets;
    }

    @NonNull
    @Override
    //called when a recylerview needs a new viewholder
    public BetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.bet_list_item,parent, false);

        return new BetViewHolder(itemView) ;
    }

    @Override
    //called to display the data
    public void onBindViewHolder(@NonNull BetViewHolder holder, int position) {

        Bet bet = bets.get(position);
        holder.bind(bet);
    }

    @Override
    public int getItemCount() {
        return bets.size();
    }

    public class BetViewHolder extends RecyclerView.ViewHolder{


        TextView titleTv;
        TextView key;
        TextView group;

        public BetViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.title_text_view);
            key = (TextView) itemView.findViewById(R.id.key_text_view);
            group = (TextView) itemView.findViewById(R.id.group_text_view);

        }

        public void bind (Bet bet){
            titleTv.setText(bet.title);
            key.setText(bet.key);
            group.setText(bet.group);

        }
    }
}
