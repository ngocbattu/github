package vidu.demo.duanmau.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import vidu.demo.duanmau.Adapter.AdapterLoaiSach;
import vidu.demo.duanmau.DAO.DAO_LS;
import vidu.demo.duanmau.Model.LoaiSach;
import vidu.demo.duanmau.R;

public class QuanLyLoaiSachFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton fl_btn_them_ls;
    ArrayList<LoaiSach> arrayList;
    AdapterLoaiSach adapterLoaiSach;
    DAO_LS cn;

    public QuanLyLoaiSachFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_quan_ly_loai_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        recyclerView = view.findViewById (R.id.recy_loai_sach);
        fl_btn_them_ls = view.findViewById (R.id.fl_btn_them_ls);
        arrayList = new ArrayList<> ();
        cn = new DAO_LS (getActivity ());
        adapterLoaiSach = new AdapterLoaiSach (getActivity (),arrayList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager (getActivity (),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager (layoutManager);
        recyclerView.setAdapter (adapterLoaiSach);



        fl_btn_them_ls.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                DialogThemLS ();
            }
        });
    }

    public void DialogThemLS(){
        Dialog dialog = new Dialog (getActivity ());
        dialog.setTitle ("Thêm loại sách");
        dialog.setContentView (R.layout.dia_log_them_loai_sach);
        dialog.show ();

        EditText ed_ma_loai = dialog.findViewById (R.id.ed_ma_loai);
        EditText ed_ten_loai = dialog.findViewById (R.id.ed_ten_loai);
        EditText ed_nha_cung_cap = dialog.findViewById (R.id.ed_nha_cung_cap);
        ImageView img_them_ls = dialog.findViewById (R.id.img_them_ls);
        ImageView img_huy_ls = dialog.findViewById (R.id.img_huy_ls);


        img_huy_ls.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                dialog.dismiss ();
            }
        });
        img_them_ls.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(ed_ma_loai.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòng nhập mã loại", Toast.LENGTH_SHORT).show ();
                }else if(ed_ten_loai.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòng nhập tên loại", Toast.LENGTH_SHORT).show ();
                    return;
                }else if(ed_nha_cung_cap.getText ().toString ().isEmpty ()){
                    Toast.makeText (getActivity (), "Vui lòng nhập nhà cung cấp", Toast.LENGTH_SHORT).show ();
                    return;
                }
//
//                if(ed_ten_loai.getText ().toString ().charAt (0) == 'N'){
//                    ed_ten_loai.setTextColor (ContextCompat.getColor (getActivity (),R.color.xanh));
//                }else if(ed_ten_loai.getText ().toString ().charAt (0) == 'A'){
//                    ed_ten_loai.setTextColor (ContextCompat.getColor (getActivity (),R.color.red));
//                }

                int maloai = Integer.parseInt (ed_ma_loai.getText ().toString ());
                String ten_loai = ed_ten_loai.getText ().toString ();
                String nha_cung_Cap = ed_nha_cung_cap.getText ().toString ();
                LoaiSach ls = new LoaiSach (maloai,ten_loai,nha_cung_Cap);
                arrayList.add (ls);
                adapterLoaiSach.notifyDataSetChanged ();
                cn.ThemLS (ls);
                Toast.makeText (getActivity (), "Thêm thành công ", Toast.LENGTH_SHORT).show ();
            }
        });

    }
    public void Delete(int id){
        cn.DeleteLS (id);
    }
    public void Update(LoaiSach ls){
        cn.UpdateLS (ls);
    }

    @Override
    public void onResume() {
        super.onResume ();
        arrayList.clear ();
        arrayList.addAll (cn.GetLS ());
        if(arrayList != null){
            adapterLoaiSach.notifyDataSetChanged ();
        }
    }

}