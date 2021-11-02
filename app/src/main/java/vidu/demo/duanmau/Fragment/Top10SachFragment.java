package vidu.demo.duanmau.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vidu.demo.duanmau.Adapter.AdapterTop10Sach;
import vidu.demo.duanmau.DAO.DAO_PM;
import vidu.demo.duanmau.Model.Top;
import vidu.demo.duanmau.R;


public class Top10SachFragment extends Fragment {

    RecyclerView recyclerView;
    DAO_PM cn;
    AdapterTop10Sach adapterTop10Sach;
    ArrayList<Top>arrayList;

    public Top10SachFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_top10_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        recyclerView = view.findViewById (R.id.recy_view_top10sach);
        cn = new DAO_PM (getContext ());
        arrayList = (ArrayList<Top>) cn.GetThongKe ();
        adapterTop10Sach = new AdapterTop10Sach (getContext (),arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager (getContext (),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager (layoutManager);
        recyclerView.setAdapter (adapterTop10Sach);
        adapterTop10Sach.notifyDataSetChanged ();
    }
    @Override
    public void onResume() {
        super.onResume ();
        arrayList.clear ();
        arrayList.addAll (cn.GetThongKe ());
        if(arrayList != null){
            adapterTop10Sach.notifyDataSetChanged ();
        }
    }
}