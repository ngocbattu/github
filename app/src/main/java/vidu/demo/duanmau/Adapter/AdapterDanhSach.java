package vidu.demo.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vidu.demo.duanmau.Model.ThanhVien;
import vidu.demo.duanmau.R;

public class AdapterDanhSach extends RecyclerView.Adapter<AdapterDanhSach.DanhSachViewHodel>{

    private Context context;
    private List<ThanhVien> list;

    public AdapterDanhSach(Context context, List<ThanhVien> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DanhSachViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.item_recy_view_danh_sach,null);
        return new DanhSachViewHodel (view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhSachViewHodel holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    public class DanhSachViewHodel extends RecyclerView.ViewHolder{
        TextView tv_user_ng , tv_pass_word_ng;
        public DanhSachViewHodel(@NonNull View itemView) {
            super (itemView);
        }
    }
}
