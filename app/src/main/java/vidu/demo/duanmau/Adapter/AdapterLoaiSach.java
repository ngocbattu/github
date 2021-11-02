package vidu.demo.duanmau.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import vidu.demo.duanmau.Fragment.QuanLyLoaiSachFragment;
import vidu.demo.duanmau.Model.LoaiSach;
import vidu.demo.duanmau.R;

public class AdapterLoaiSach extends RecyclerView.Adapter<AdapterLoaiSach.LoaiSachViewHodel>{
    private Context context;
    private List<LoaiSach> list;
    private QuanLyLoaiSachFragment quanLyLoaiSachFragment;

    public AdapterLoaiSach(Context context, List<LoaiSach> list, QuanLyLoaiSachFragment quanLyLoaiSachFragment) {
        this.context = context;
        this.list = list;
        this.quanLyLoaiSachFragment = quanLyLoaiSachFragment;
    }

    @NonNull
    @Override
    public LoaiSachViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_recy_view_loai_sach,null);
        return new LoaiSachViewHodel (view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachViewHodel holder, int position) {
        LoaiSach ls = list.get (position);
        holder.ls_maloai.setText ("Mã loại : " + ls.getMaLoai () + "" );
        holder.ls_ten_loai.setText (ls.getTenLoai ());
        if(ls.getTenLoai ().charAt (0) == 'N'){
            holder.ls_ten_loai.setTextColor (ContextCompat.getColor (context,R.color.xanh));
        }else if(ls.getTenLoai ().charAt (0) == 'A'){
            holder.ls_ten_loai.setTextColor (ContextCompat.getColor (context,R.color.red));
        }else {
            holder.ls_ten_loai.setTextColor (ContextCompat.getColor (context,R.color.black));
        }
        holder.ls_nha_cung_cap.setText (ls.getNhaCungCap ());
////        Toast.makeText (context, "dữ liệu : " + list.get (0), Toast.LENGTH_SHORT).show ();
//        int [] array = new int[list.size ()];
//        for (int i = 0 ; i < list.size () ; i++){
//
//        }

        holder.img_delete.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder (context);
                builder.setTitle ("Delete");
                builder.setMessage ("Bạn có chắc chắn muốn xóa không");
                builder.setPositiveButton ("Yes", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        quanLyLoaiSachFragment.Delete (ls.getMaLoai ());
                        quanLyLoaiSachFragment.onResume ();
                        Toast.makeText (context, "Xóa thành công ", Toast.LENGTH_SHORT).show ();
                    }
                });
                builder.setNegativeButton ("No", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss ();
                    }
                });
                builder.create ().show ();
            }
        });

        holder.update_ls.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder (context);
                View view = LayoutInflater.from (context).inflate (R.layout.dia_log_update_loai_sach,null);
                builder.setView (view);


                TextView tv_mals = view.findViewById (R.id.tv_update_maloai);
                TextInputEditText ed_update_nha_cung_Cap = view.findViewById (R.id.ed_update_nha_cung_cap);
                TextInputEditText ed_updat_ten_loai = view.findViewById (R.id.ed_update_ten_ls);

                tv_mals.setText ("Mã loại : " + ls.getMaLoai ()+"");
                ed_update_nha_cung_Cap.setText (ls.getNhaCungCap ());
                ed_updat_ten_loai.setText (ls.getTenLoai ());

                builder.setPositiveButton ("Đồng ý", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ls.setTenLoai (ed_updat_ten_loai.getText ().toString ());
                        ls.setNhaCungCap (ed_update_nha_cung_Cap.getText ().toString ());
                        list.add (ls);
                        quanLyLoaiSachFragment.Update (ls);
                        quanLyLoaiSachFragment.onResume ();
                        Toast.makeText (context, "Update thành công", Toast.LENGTH_SHORT).show ();
                    }
                });
                builder.setNegativeButton ("Hủy", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss ();
                    }
                });
                builder.create ().show ();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    public class LoaiSachViewHodel extends RecyclerView.ViewHolder {
        TextView ls_maloai , ls_ten_loai , ls_nha_cung_cap;
        ImageView img_delete;
        LinearLayout update_ls;

        public LoaiSachViewHodel(@NonNull View itemView) {
            super (itemView);
            ls_maloai = itemView.findViewById (R.id.tv_ma_loai);
            ls_ten_loai = itemView.findViewById (R.id.tv_ten_loai);
            ls_nha_cung_cap = itemView.findViewById (R.id.tv_nha_cung_cap);
            img_delete = itemView.findViewById (R.id.img_xoa_ls);
            update_ls = itemView.findViewById (R.id.layout_loai_sach);
        }
    }

}
