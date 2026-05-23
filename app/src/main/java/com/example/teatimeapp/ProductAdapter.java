package com.example.teatimeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private OnProductClickListener listener;

    public interface OnProductClickListener {
        void onProductClick(Product product);
        void onAddQuickClick(Product product);
    }

    public ProductAdapter(List<Product> productList, OnProductClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product, listener);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtNameProduct, txtPriceProduct, txtDescription;
        ImageButton btnAddQuick;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtNameProduct = itemView.findViewById(R.id.txtNameProduct);
            txtPriceProduct = itemView.findViewById(R.id.txtPriceProduct);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            btnAddQuick = itemView.findViewById(R.id.btnAddQuick);
        }

        public void bind(final Product product, final OnProductClickListener listener) {
            imgProduct.setImageResource(product.getImageResource());
            txtNameProduct.setText(product.getName());
            txtPriceProduct.setText(product.getPrice());
            txtDescription.setText(product.getDescription());

            itemView.setOnClickListener(v -> listener.onProductClick(product));
            btnAddQuick.setOnClickListener(v -> listener.onAddQuickClick(product));
        }
    }
}
