package com.example.teatimeapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Kết nối trực tiếp với giao diện menu trà sữa activity_main.xml
        setContentView(R.layout.activity_main);

        // Hiện tại trang chủ chỉ hiển thị danh sách bằng XML nên bạn chưa cần viết logic gì thêm ở đây.
        // Sau này nếu muốn bắt sự kiện bấm vào từng món trà sữa để đặt hàng, bạn sẽ viết tiếp ở dưới này nhé.
    }
}