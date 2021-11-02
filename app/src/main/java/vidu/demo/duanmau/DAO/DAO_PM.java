package vidu.demo.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vidu.demo.duanmau.Model.PhieuMuon;
import vidu.demo.duanmau.Model.Top;
import vidu.demo.duanmau.SQL.Dbhelper;

public class DAO_PM {
    Dbhelper dbhelper;
    private Context context;
    public DAO_PM(Context context) {
        dbhelper = new Dbhelper (context);
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
    }
    public void ThemPM (PhieuMuon phieuMuon){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        ContentValues values = new ContentValues ();
        values.put ("MAPM",phieuMuon.getMaPM ());
        values.put ("MATT",phieuMuon.getMaTT ());
        values.put ("MATV",phieuMuon.getMaTV ());
        values.put ("MASACH",phieuMuon.getMaSach ());
        values.put ("NGAY", phieuMuon.getNgay ());
        values.put ("TRASACH",phieuMuon.getTraSach ());
        values.put ("TIENTHUE",phieuMuon.getTienThue ());
        db.insert ("PhieuMuon",null,values);
    }
    public void UpdatePM(PhieuMuon pm){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        ContentValues values = new ContentValues ();
        values.put ("NGAY", pm.getNgay ());
        values.put ("TRASACH",pm.getTraSach ());
        values.put ("TIENTHUE",pm.getTienThue ());
        db.update ("PhieuMuon", values, "MAPM = " + pm.getMaPM (),null);
    }
    public void Delete(int id){
        SQLiteDatabase db = dbhelper.getWritableDatabase ();
        db.delete ("PhieuMuon","MAPM = " + id, null);
    }

    public List<PhieuMuon> GetPM (){
        ArrayList<PhieuMuon> arrayList = new ArrayList<> ();
        SQLiteDatabase db = dbhelper.getReadableDatabase ();
        Cursor cursor = db.rawQuery ("select * from PhieuMuon",null);
        cursor.moveToFirst ();
        while (!cursor.isAfterLast ()){
            PhieuMuon pm = new PhieuMuon ();
            pm.setMaPM (Integer.parseInt (cursor.getString (cursor.getColumnIndex ("MAPM"))));
            pm.setMaTT (cursor.getColumnName (cursor.getColumnIndex ("MATT")));
            pm.setMaTV (Integer.parseInt (cursor.getString (cursor.getColumnIndex ("MATV"))));
            pm.setMaSach (Integer.parseInt (cursor.getString (cursor.getColumnIndex ("MASACH"))));
            pm.setNgay (cursor.getString (cursor.getColumnIndex ("NGAY")));
            pm.setTraSach (cursor.getString (cursor.getColumnIndex ("TRASACH")));
            pm.setTienThue (Integer.parseInt (cursor.getString (cursor.getColumnIndex ("TIENTHUE"))));
            arrayList.add (pm);
            cursor.moveToNext ();
        }
        cursor.close ();
        return arrayList;

    }
    public List<Top> GetThongKe(){
        ArrayList<Top> arrayList = new ArrayList<> ();
        SQLiteDatabase db = dbhelper.getReadableDatabase ();
        String sql = "select  TENSACH , count(PhieuMuon.MASACH) as soluong from PhieuMuon INNER JOIN Sach on PhieuMuon.MASACH = Sach.MASACH GROUP BY TENSACH ORDER BY soluong DESC LIMIT 10";
        Cursor cursor = db.rawQuery (sql,null);
        while (cursor.moveToNext ()){
            Top t = new Top ();
            t.setTenSach (cursor.getString (cursor.getColumnIndex ("TENSACH")));
            t.setSoLuong (Integer.parseInt (cursor.getString (cursor.getColumnIndex ("soluong"))));
            arrayList.add (t);
        }
        cursor.close ();
        return arrayList;
    }
    public int GetDoanhThu(String tuNgay , String denNgay){
        String sql = "select SUM(TIENTHUE) as doanhthu from PhieuMuon where NGAY between ? and ?";
        SQLiteDatabase db = dbhelper.getReadableDatabase ();
        List<Integer> list = new ArrayList<> ();
        Cursor cursor = db.rawQuery (sql,new String[]{tuNgay,denNgay});

        while (cursor.moveToNext ()){
            try {
                list.add (Integer.parseInt (cursor.getString (cursor.getColumnIndex ("doanhthu"))));
            }
            catch (Exception e){
                list.add (0);
            }

        }
        return list.get (0);
    }
}
