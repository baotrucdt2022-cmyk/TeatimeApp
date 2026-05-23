package com.example.teatimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvProducts;
    private ProductAdapter adapter;
    private List<Product> productList;
    private List<Product> displayList;
    private ExtendedFloatingActionButton fabCart;
    private TextInputEditText edtSearch;
    private ChipGroup chipGroup;
    
    private int cartItemCount = 0;
    private String selectedCategory = "Tất cả";
    private String searchQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupData();
        setupRecyclerView();
        setupSearch();
        setupFilter();
        setupFab();
    }

    private void initViews() {
        rcvProducts = findViewById(R.id.rcvProducts);
        fabCart = findViewById(R.id.fabCart);
        edtSearch = findViewById(R.id.edtSearch);
        chipGroup = findViewById(R.id.chipGroup);
    }

    private void setupData() {
        productList = new ArrayList<>();
        // Danh mục: Trà Sữa, Trà Trái Cây, Cà Phê, Đồ Ăn Vặt
        productList.add(new Product("Trà Sữa Trân Châu Hoàng Kim", "45.000 đ", R.drawable.img, 
                "Vị trà đậm đà hòa quyện cùng sữa béo và trân châu hoàng kim dai giòn đặc trưng.", "Trà Sữa"));
        productList.add(new Product("Sữa Tươi Trân Châu Đường Đen", "50.000 đ", R.drawable.img_1, 
                "Sữa tươi nguyên chất kết hợp với trân châu đường đen thủ công mềm dẻo.", "Trà Sữa"));
        productList.add(new Product("Trà Xoài Kem Cheese", "48.000 đ", R.drawable.img_2, 
                "Trà trái cây tươi mát kết hợp lớp kem cheese mặn béo ngậy tan chảy.", "Trà Trái Cây"));
        productList.add(new Product("Trà Sữa Khoai Môn Taro", "42.000 đ", R.drawable.img_3, 
                "Hương vị khoai môn thơm nồng, bùi bùi, màu sắc tím bắt mắt.", "Trà Sữa"));
        productList.add(new Product("Matcha Đậu Đỏ Nhật Bản", "48.000 đ", R.drawable.img_4, 
                "Bột matcha cao cấp cùng đậu đỏ ngọt bùi, chuẩn vị Nhật Bản.", "Trà Sữa"));
        productList.add(new Product("Cà Phê Muối", "35.000 đ", R.drawable.img, 
                "Sự kết hợp độc đáo giữa vị đắng của cà phê và vị mặn nhẹ của kem muối.", "Cà Phê"));
        productList.add(new Product("Bánh Tráng Trộn", "25.000 đ", R.drawable.img_2, 
                "Món ăn vặt đặc trưng với vị chua cay mặn ngọt hài hòa.", "Đồ Ăn Vặt"));

        displayList = new ArrayList<>(productList);
    }

    private void setupRecyclerView() {
        adapter = new ProductAdapter(displayList, new ProductAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(Product product) {
                openDetail(product);
            }

            @Override
            public void onAddQuickClick(Product product) {
                cartItemCount++;
                updateCartFab();
                // Thông báo khi thêm món vào giỏ hàng
                Toast.makeText(MainActivity.this, "Đã thêm " + product.getName() + " vào giỏ hàng!", Toast.LENGTH_SHORT).show();
            }
        });

        rcvProducts.setLayoutManager(new LinearLayoutManager(this));
        rcvProducts.setAdapter(adapter);

        // Hiệu ứng UX: Thu gọn FAB khi cuộn xuống, mở rộng khi cuộn lên
        rcvProducts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 10 && fabCart.isExtended()) {
                    fabCart.shrink();
                } else if (dy < -10 && !fabCart.isExtended()) {
                    fabCart.extend();
                }
            }
        });
    }

    private void setupSearch() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchQuery = s.toString().toLowerCase().trim();
                applyFilters();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupFilter() {
        chipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (!checkedIds.isEmpty()) {
                Chip chip = findViewById(checkedIds.get(0));
                selectedCategory = chip.getText().toString();
                applyFilters();
                // Thông báo khi lọc danh mục
                Toast.makeText(this, "Đang lọc: " + selectedCategory, Toast.LENGTH_SHORT).show();
            } else {
                selectedCategory = "Tất cả";
                applyFilters();
            }
        });
    }

    private void applyFilters() {
        displayList.clear();
        for (Product product : productList) {
            boolean matchesSearch = product.getName().toLowerCase().contains(searchQuery);
            boolean matchesCategory = selectedCategory.equals("Tất cả") || product.getCategory().equals(selectedCategory);

            if (matchesSearch && matchesCategory) {
                displayList.add(product);
            }
        }
        adapter.notifyDataSetChanged();
        
        // Thông báo nếu không tìm thấy kết quả sau khi lọc
        if (displayList.isEmpty() && (!searchQuery.isEmpty() || !selectedCategory.equals("Tất cả"))) {
            Toast.makeText(this, "Không tìm thấy đồ uống phù hợp!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateCartFab() {
        fabCart.setText("Giỏ hàng (" + cartItemCount + ")");
    }

    private void setupFab() {
        fabCart.setOnClickListener(v -> {
            if (cartItemCount > 0) {
                Toast.makeText(this, "Bạn có " + cartItemCount + " món. Đang chuyển tới giỏ hàng...", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Giỏ hàng của bạn đang trống!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDetail(Product product) {
        Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("product_price", product.getPrice());
        intent.putExtra("product_image", product.getImageResource());
        intent.putExtra("product_description", product.getDescription());
        startActivity(intent);
    }
}
