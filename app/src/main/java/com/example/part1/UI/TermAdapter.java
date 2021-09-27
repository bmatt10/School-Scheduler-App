package com.example.part1.UI;

import android.content.Context;
import android.content.Intent;
import com.example.part1.Entity.TermEntity;
import com.example.part1.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ProductViewHolder> {

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView productItemView;


        private ProductViewHolder(View itemView) {
            super(itemView);
            productItemView = itemView.findViewById(R.id.productTextView);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                final TermEntity current = mProducts.get(position);
                Intent intent = new Intent(context, TermDetails.class);
                intent.putExtra("productName", current.getTermName());
                intent.putExtra("productPrice", current.getTermNotes());
                intent.putExtra("productID", current.getTermID());
                intent.putExtra("position", position);
                context.startActivity(intent);
            });
        }
    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<TermEntity> mProducts;

    public TermAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.term_list_item, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        if (mProducts != null) {
            final TermEntity current = mProducts.get(position);
            holder.productItemView.setText(current.getTermName());

        } else {

            holder.productItemView.setText("No Word");
        }
    }

    @Override
    public int getItemCount() {
        if (mProducts != null)
            return mProducts.size();
        else return 0;
    }


    public void setWords(List<TermEntity> words) {
        mProducts = words;
        notifyDataSetChanged();
    }
}

