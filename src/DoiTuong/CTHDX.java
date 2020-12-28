/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoiTuong;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author LENOVO
 */
public class CTHDX {

    private String _maCTHDX;
    private String _maHDX;
    private String _maMH;
    private int _soluong;
    private int _giaban;
    private String _donvitinh;
    private int _tt;

    public CTHDX() {

    }

    public CTHDX(String _maCTHDX, String _maHDX, String _maMH, int _soluong, int _giaban, String _donvitinh, int _tt) {
        this._maCTHDX = _maCTHDX;
        this._maHDX = _maHDX;
        this._maMH = _maMH;
        this._soluong = _soluong;
        this._giaban = _giaban;
        this._donvitinh = _donvitinh;
        this._tt = _tt;
    }
public int sotiengiam;

    public String getMaCTHDX() {
        return _maCTHDX;
    }



    public void setMaCTHDX(String _maCTHDX) {
        this._maCTHDX = _maCTHDX;
    }

    public String getMaHDX() {
        return _maHDX;
    }

    public void setMaHDX(String _maHDX) {
        this._maHDX = _maHDX;
    }

    public String getMaMH() {
        return _maMH;
    }

    public void setMaMH(String _maMH) {
        this._maMH = _maMH;
    }

    public int getSoluong() {
        return _soluong;
    }

    public void setSoluong(int _soluong) {
        this._soluong = _soluong;
    }

    public int getGiaban() {
        return _giaban;
    }

    public void setGiaban(int _giaban) {
        this._giaban = _giaban;
    }

    public String getDonvitinh() {
        return _donvitinh;
    }

    public void setDonvitinh(String _donvitinh) {
        this._donvitinh = _donvitinh;
    }

    public int getTt() {
        return _tt;
    }

    public void setTt(int _tt) {
        this._tt = _tt;
    }

    public CTHDX Them(List<CTHDX> list) {
        Scanner scan = new Scanner(System.in);
        boolean kt = true;

        do {
            kt = true;
            System.out.print("nhập mã chi tiết hóa đơn xuất:");
            this._maCTHDX = scan.nextLine();
            if (kt && !Pattern.matches("[C]{1}[T]{1}[X]{1}[0-9]{4}", this._maCTHDX)) {
                System.out.println("Lỗi: Mã chi tiết hóa đơn xuất phải có 7 kí tự và bắt đầu bằng CTX");
                kt = false;
            }
            if (kt) {
                for (CTHDX hdx : list) {
                    if (hdx.getMaCTHDX().contains(this._maCTHDX)) {
                        System.out.println("Lỗi:mã chi tiết hóa đơn xuất đã tồn tại");
                        kt = false;
                        break;
                    }
                }
            }
        } while (!(kt));
        do {
            kt = true;
            System.out.print("nhập mã mặt hàng:");
            this._maMH = scan.nextLine();
            if (kt && !Pattern.matches("[M]{1}[H]{1}[0-9]{5}", this._maMH)) {
                System.out.println("Lỗi: Mã mặt hàng nhập phải có 7 kí tự và bắt đầu bằng MH");
                kt = false;
            }
            if (kt) {
//                    List<MatHang> ds = new ArrayList<MatHang>();//lí do
                List<MatHang> ds = DocFile();
                kt = false;
                for (MatHang mh : ds) {
                    if (mh.getMaMatHang().contains(this._maMH)) {
                        kt = true;
                        System.out.println(mh.getTenMatHang());
                        break;
                    }
                }
                if (kt == false) {
                    System.out.println("Lỗi: mã mặt hàng không tồn tại");
                }
            }
        } while (!(kt));
        do {

            kt = true;
            System.out.print("nhập số lượng :");
            this._soluong = Integer.parseInt(scan.nextLine());
            if (this._soluong < 0) {
                System.out.println("Lỗi số lượng phải lớn hơn 0");

                kt = false;
            }

        } while (!(kt));
        do {
            kt = true;
            System.out.print("nhập giá bán :");
            this._giaban = Integer.parseInt(scan.nextLine());
            if (this._giaban < 0) {
                System.out.println("Lỗi: Gía bán phải lớn hơn 0");
                kt = false;
            }
        } while (!(kt));
        do {
            kt = true;
            System.out.print("NHập SIZE :");
            this._donvitinh=scan.nextLine();

//            kt = false;

            if (kt) {
                List<MatHang> ds = DocFile();
                kt = false;
                for (MatHang mh : ds) {
                    if (this._donvitinh.contains(mh.getDonvitinh()) && this._maMH.equals(mh.getMaMatHang())) {
                        kt = true;
                        System.out.println("tên giày :"+mh.getTenMatHang()+" loại : "+mh.getCongdung()+" Size"+mh.getDonvitinh());
                        break;
                    }
                }
                if (kt == false) {
                    System.out.println("Lỗi: mã trên không tồn tại SIZE "+this._donvitinh);
                }

            }
        } while (!(kt));
        System.out.println("thành tiền:");
        _tt = _soluong * _giaban;

        System.out.print("số tiền đã trả là" + _tt);
        return this;
    }

    public CTHDX Sua() {
        Scanner scan = new Scanner(System.in);
        boolean kt = true;
        List<CTHDX> ds = new ArrayList<CTHDX>();

        do {
            kt = true;
            System.out.print("nhập mã mặt hàng:");
            this._maMH = scan.nextLine();
            if (kt && !Pattern.matches("[M]{1}[H]{1}[0-9]{5}", this._maMH)) {
                System.out.println("Lỗi: Mã mặt hàng nhập phải có 7 kí tự và bắt đầu bằng MH");
                kt = false;
            }
            if (kt) {
                List<MatHang> dsn = DocFile();
                kt = false;
                for (MatHang mh : dsn) {
                    if (mh.getMaMatHang().contains(this._maMH)) {
                        kt = true;
                        System.out.println(mh.getTenMatHang());
                        break;
                    }
                }
                if (kt == false) {
                    System.out.println("Lỗi: mã mặt hàng không tồn tại");
                }
            }
        } while (!(kt));
        do {

            kt = true;
            System.out.print("nhập số lượng :");
            this._soluong = Integer.parseInt(scan.nextLine());
            if (this._soluong < 0) {
                System.out.println("Lỗi số lượng phải lớn hơn 0");
                kt = false;




            }
        } while (!(kt));
        do {
            kt = true;
            System.out.print("nhập giá bán :");
            this._giaban = Integer.parseInt(scan.nextLine());
            if (this._giaban < 0) {
                System.out.println("Lỗi: Gía bán phải lớn hơn 0");
                kt = false;
            }
        } while (!(kt));
        do {
            kt = true;
            System.out.print("NHập SIZE :");
            this._donvitinh=scan.nextLine();

//            kt = false;

            if (kt) {
                List<MatHang> dsn = DocFile();
                kt = false;
                for (MatHang mh : dsn) {
                    if (this._donvitinh.contains(mh.getDonvitinh()) && this._maMH.equals(mh.getMaMatHang())) {
                        kt = true;
                        System.out.println(mh.getTenMatHang());
                        break;
                    }
                }
                if (kt == false) {
                    System.out.println("Lỗi: Size giày không tồn tại hoặc đã hết vui lòng nhập thêm");
                }

            }
        } while (!(kt));
        System.out.println("thành tiền:");
        _tt = _soluong * _giaban-((sotiengiam*_tt)/100);
        System.out.print("số tiền đã trả là" + _tt);
        return this;
    }

    public String ToString2() {
        return "║" + String.format("%-19s", this._maCTHDX) + "|" + String.format("%-17s", this._maHDX) + "|" + String.format("%-18s", this._maMH) + "|" + String.format("%-14s", this._soluong) + "|" + String.format("%-17s", this._giaban) + "|" + String.format("%-18s", this._donvitinh) + "|" + String.format("%-18s", this._tt)+"║";
    }

    public List<MatHang> DocFile() {
        List<MatHang> ds = new ArrayList<MatHang>();
        String fileName = "MatHang.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(Pattern.quote("|"));
                MatHang mh = new MatHang(array[0], array[1], array[2], Integer.parseInt(array[3]), Integer.parseInt(array[4]), array[5]);
                ds.add((MatHang) mh);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return ds;
    }
}