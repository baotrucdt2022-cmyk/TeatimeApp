package com.example.teatimeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    // Bước 1: Khai báo tất cả các ô nhập liệu và nút bấm từ giao diện XML
    private EditText edtFullName, edtPhone, edtEmail, edtPassword, edtConfirmPassword;
    private Button btnRegisterSubmit;
    private TextView txtBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Bước 2: Ánh xạ (bắn kết nối) giữa code Java và ID bên file XML
        edtFullName = findViewById(R.id.edtRegisterFullName);
        edtPhone = findViewById(R.id.edtRegisterPhone);
        edtEmail = findViewById(R.id.edtRegisterEmail);
        edtPassword = findViewById(R.id.edtRegisterPassword);
        edtConfirmPassword = findViewById(R.id.edtRegisterConfirmPassword);
        btnRegisterSubmit = findViewById(R.id.btnRegisterSubmit);
        txtBackToLogin = findViewById(R.id.txtBackToLogin);

        // Bước 3: Xử lý sự kiện khi bấm nút HOÀN TẤT ĐĂNG KÝ
        btnRegisterSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy chuỗi chữ mà người dùng đã nhập vào các ô
                String fullName = edtFullName.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String confirmPassword = edtConfirmPassword.getText().toString().trim();

                // Kiểm tra xem người dùng có bỏ trống ô nào không
                if (fullName.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    // Hiển thị thông báo nhỏ trên màn hình nhắc nhở
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
                // Kiểm tra xem mật khẩu nhập lại có khớp với mật khẩu ban đầu không
                else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu nhập lại không trùng khớp!", Toast.LENGTH_SHORT).show();
                }
                // Nếu mọi thứ đều ổn ổn thì tiến hành chuyển trang
                else {
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

                    // Lệnh Intent dùng để chuyển từ trang Đăng Ký quay lại trang Đăng Nhập
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                    // Đóng màn hình đăng ký này lại để giải phóng bộ nhớ
                    finish();
                }
            }
        });

        // Xử lý sự kiện khi người dùng không muốn đăng ký nữa, bấm dòng chữ "Đã có tài khoản? Đăng nhập"
        txtBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}