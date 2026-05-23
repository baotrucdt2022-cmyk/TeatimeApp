package com.example.teatimeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView imgProductDetail;
    private TextView txtProductNameDetail, txtProductPriceDetail, txtQuantity;
    private ImageButton btnBack;
    private Button btnMinus, btnPlus, btnAddToCart;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        initViews();
        handleIntent();
        setupListeners();
    }

    private void initViews() {
        imgProductDetail = findViewById(R.id.imgProductDetail);
        txtProductNameDetail = findViewById(R.id.txtProductNameDetail);
        txtProductPriceDetail = findViewById(R.id.txtProductPriceDetail);
        txtQuantity = findViewById(R.id.txtQuantity);
        btnBack = findViewById(R.id.btnBack);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnAddToCart = findViewById(R.id.btnAddToCart);
    }

    private void handleIntent() {
        if (getIntent() != null) {
            String name = getIntent().getStringExtra("product_name");
            String price = getIntent().getStringExtra("product_price");
            int imageRes = getIntent().getIntExtra("product_image", R.drawable.img);

            txtProductNameDetail.setText(name);
            txtProductPriceDetail.setText(price);
            imgProductDetail.setImageResource(imageRes);
        }
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                txtQuantity.setText(String.valueOf(quantity));
            }
        });

        btnPlus.setOnClickListener(v -> {
            quantity++;
            txtQuantity.setText(String.valueOf(quantity));
        });

        btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(this, "Đã thêm " + quantity + " sản phẩm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
        });
    }
}
