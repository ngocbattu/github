package vidu.demo.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import vidu.demo.duanmau.Fragment.DoanhThuFragment;
import vidu.demo.duanmau.Fragment.DoiMatKhauFragment;
import vidu.demo.duanmau.Fragment.QuanLyLoaiSachFragment;
import vidu.demo.duanmau.Fragment.QuanLyPhieuMuonFragment;
import vidu.demo.duanmau.Fragment.QuanLySachFragment;
import vidu.demo.duanmau.Fragment.QuanLyThanhVienFragment;
import vidu.demo.duanmau.Fragment.ThemNgDungFragment;
import vidu.demo.duanmau.Fragment.Top10SachFragment;

public class GiaoDienChinh extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    QuanLyPhieuMuonFragment quanLyPhieuMuonFragment;
    QuanLyLoaiSachFragment quanLyLoaiSachFragment;
    QuanLySachFragment quanLySachFragment;
    QuanLyThanhVienFragment quanLyThanhVienFragment;
    Top10SachFragment top10SachFragment;
    DoanhThuFragment doanhThuFragment;
    ThemNgDungFragment themNgDungFragment;
    DoiMatKhauFragment doiMatKhauFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_giao_dien_chinh);
        toolbar = this.findViewById (R.id.too_bar);
        drawerLayout = this.findViewById (R.id.draw_layout);
        navigationView = this.findViewById (R.id.navi_gaiton);
        setSupportActionBar (toolbar);
        getSupportActionBar ().setHomeAsUpIndicator (R.drawable.ic_menu);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);

        Toast.makeText (this, "Chào mừng bạn đến với ứng dụng", Toast.LENGTH_SHORT).show ();
        toolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer (navigationView);
            }
        });
        fragmentManager = getSupportFragmentManager ();
        quanLyPhieuMuonFragment = new QuanLyPhieuMuonFragment ();
        quanLyLoaiSachFragment = new QuanLyLoaiSachFragment ();
        quanLySachFragment = new QuanLySachFragment ();
        quanLyThanhVienFragment = new QuanLyThanhVienFragment ();
        top10SachFragment = new Top10SachFragment ();
        doanhThuFragment = new DoanhThuFragment ();
        themNgDungFragment  = new ThemNgDungFragment ();
        doiMatKhauFragment = new DoiMatKhauFragment ();
        navigationView.setNavigationItemSelectedListener (new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId ()){
                    case R.id.item_phieu_muon:
                        fragmentManager.beginTransaction ().replace (R.id.fragmnetconten_view,quanLyPhieuMuonFragment).commit ();
                        break;
                    case R.id.item_loai_sach:
                        fragmentManager.beginTransaction ().replace (R.id.fragmnetconten_view,quanLyLoaiSachFragment).commit ();
                        break;
                    case R.id.item_sach:
                        fragmentManager.beginTransaction ().replace (R.id.fragmnetconten_view,quanLySachFragment).commit ();
                        break;
                    case R.id.item_thanh_vien:
                        fragmentManager.beginTransaction ().replace (R.id.fragmnetconten_view,quanLyThanhVienFragment).commit ();
                        break;
                    case R.id.item_top_sach:
                        fragmentManager.beginTransaction ().replace (R.id.fragmnetconten_view,top10SachFragment).commit ();
                        break;
                    case R.id.item_doanh_thu:
                        fragmentManager.beginTransaction ().replace (R.id.fragmnetconten_view,doanhThuFragment).commit ();
                        break;
                    case R.id.item_them_ng_dung:
                        fragmentManager.beginTransaction ().replace (R.id.fragmnetconten_view,themNgDungFragment).commit ();
                        break;
                    case R.id.item_doi_mk:
                        fragmentManager.beginTransaction ().replace (R.id.fragmnetconten_view,doiMatKhauFragment).commit ();
                        break;
                    case R.id.item_dang_xuat:
                        Intent intent = new Intent (GiaoDienChinh.this,ManHinhLogin.class);
                        startActivity (intent);
                        break;
                }
                 drawerLayout.closeDrawer (navigationView);
                return true;
            }
        });


    }



}