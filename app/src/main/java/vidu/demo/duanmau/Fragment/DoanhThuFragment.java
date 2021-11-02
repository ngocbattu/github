package vidu.demo.duanmau.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import vidu.demo.duanmau.DAO.DAO_PM;
import vidu.demo.duanmau.R;


public class DoanhThuFragment extends Fragment {

    Button btn_tu_ngay , btn_den_ngay, btn_doanh_thu;

    EditText ed_tu_ngay;
    EditText ed_den_ngay;
    TextView tv_doanh_thu;

    public DoanhThuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_doanh_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        btn_tu_ngay = view.findViewById (R.id.btn_tu_ngay);
        btn_den_ngay = view.findViewById (R.id.btn_den_ngay);
        ed_tu_ngay = view.findViewById (R.id.ed_tu_ngay);
        ed_den_ngay = view.findViewById (R.id.ed_den_ngay);
        btn_doanh_thu = view.findViewById (R.id.btn_doanh_thu);
        tv_doanh_thu = view.findViewById (R.id.tv_doanh_thu);



        btn_tu_ngay.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog (getActivity (), new DatePickerDialog.OnDateSetListener () {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        ed_tu_ngay.setText (year + " / " + month + " / " + dayOfMonth);
                    }
                }, Calendar.YEAR,Calendar.MONTH,Calendar.DATE);
                dialog.show ();
            }
        });
        btn_den_ngay.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog (getActivity (), new DatePickerDialog.OnDateSetListener () {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        ed_den_ngay.setText (year + " / " + month + " / " + dayOfMonth);
                    }
                },Calendar.YEAR, Calendar.MONTH , Calendar.DATE);
                dialog.show ();
            }
        });
        btn_doanh_thu.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String tungay  = ed_tu_ngay.getText ().toString ();
                String denNgay = ed_den_ngay.getText ().toString ();
                DAO_PM cn = new DAO_PM (getActivity ());
                tv_doanh_thu.setText ("Doanh thu : " + cn.GetDoanhThu (tungay,denNgay) + "VND");
            }
        });
    }
}