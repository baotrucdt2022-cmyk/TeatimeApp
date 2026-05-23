package com.example.teatimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    // Bước 1: Khai báo các thành phần nút bấm và dòng chữ
    private Button btnLogin;
    private TextView txtGoToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Bước 2: Ánh xạ chính xác ID từ file activity_login.xml
        btnLogin = findViewById(R.id.btnLogin);
        txtGoToRegister = findViewById(R.id.txtGoToRegister);

        // Bước 3: Xử lý khi bấm nút ĐĂNG NHẬP -> VÀO THẲNG TRANG CHỦ (MainActivity)
        if (btnLogin != null) {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tạo lệnh chuyển từ trang Login sang Trang Chủ (MainActivity)
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                    // Đóng màn hình đăng nhập để không bị quay lại khi bấm nút Back
                    finish();
                }
            });
        }

        // Xử lý khi bấm dòng chữ "Chưa có tài khoản? Đăng ký ngay" -> Qua RegisterActivity
        if (txtGoToRegister != null) {
            txtGoToRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tạo lệnh chuyển từ trang Login sang trang Đăng Ký (RegisterActivity)
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}