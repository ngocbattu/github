package vidu.demo.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import vidu.demo.duanmau.DAO.DAO_TT;

public class ManHinhLogin extends AppCompatActivity {
    Button btn_dang_nhap;
    TextInputEditText ed_tk , ed_mk;
    CheckBox cb_savemk;
    DAO_TT cn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_man_hinh_login);
        btn_dang_nhap = this.findViewById (R.id.btn_dang_nhap);
        ed_tk = this.findViewById (R.id.ed_tai_khoan_dn);
        ed_mk = this.findViewById (R.id.ed_mat_khau_dn);
        cb_savemk = this.findViewById (R.id.cb_save_mk);
        cn = new DAO_TT (this);
        btn_dang_nhap.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(ed_tk.getText ().toString ().isEmpty ()){
                    Toast.makeText (ManHinhLogin.this, "Vui lòng nhập tên tài khoản", Toast.LENGTH_SHORT).show ();
                }else if(ed_mk.getText ().toString ().isEmpty ()){
                    Toast.makeText (ManHinhLogin.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show ();
                    return;
                }
                if(ed_tk.getText ().toString ().equals ("admin") && ed_mk.getText ().toString ().equals ("admin")){
                    Intent intent1 = new Intent (ManHinhLogin.this,GiaoDienChinh.class);
                    startActivity (intent1);
                    Toast.makeText (ManHinhLogin.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show ();
                }else{
                    String tk = ed_tk.getText ().toString ();
                    String mk = ed_mk.getText ().toString ();
                    boolean kt = cn.kiemTraLogin (tk,mk);
                    if(kt){
                        Intent intent = new Intent (ManHinhLogin.this,GiaoDienChinh.class);
                        startActivity (intent);
                        Toast.makeText (ManHinhLogin.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show ();
                    }else{
                        Toast.makeText (ManHinhLogin.this, "Vui lòng kiểm tra lại tài khoản và mật khẩu ", Toast.LENGTH_SHORT).show ();
                    }
                }
            }
        });
    }
}