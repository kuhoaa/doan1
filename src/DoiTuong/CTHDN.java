/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoiTuong;

import javax.print.Doc;
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
public class CTHDN {

    private String _maCTHD;
    private String _maHDN;
    private String _maMH;
    private int _soluong;
    private String _donvitinh;

    public CTHDN() {

    }

    public CTHDN(String _maCTHD, String _maHDN, String _maMH, int _soluong, String _donvitinh) {
        this._maCTHD = _maCTHD;
        this._maHDN = _maHDN;
        this._maMH = _maMH;
        this._soluong = _soluong;
        this._donvitinh = _donvitinh;
    }

    public String getMaCTHD() {
        return _maCTHD;
    }

    public String getHDN() {
        return _maHDN;
    }

    public String getMaMH() {
        return _maMH;
    }

    public void setMaMH(String _maMH) {
        this._maMH = _maMH;
    }

    public void setMaHDN(String _maHDN) {
        this._maHDN = _maHDN;
    }

    public int getSoluong() {
        return _soluong;
    }

    public void setSoluong(int _soluong) {
        this._soluong = _soluong;
    }

    public String getDonvitinh() {
        return _donvitinh;
    }

    public void setDonvitinh(String _donvitinh) {
        this._donvitinh = _donvitinh;
    }

    public CTHDN Them(List<CTHDN> list) {
        Scanner scan = new Scanner(System.in);
        boolean kt = true;

        do {
            kt = true;
            System.out.print("nhập mã chi tiết hóa đơn:");
            this._maCTHD = scan.nextLine();
            if (kt && !Pattern.matches("[C]{1}[T]{1}[0-9]{5}", this._maCTHD)) {
                System.out.println("Lỗi: Mã chi tiết hóa đơn nhập phải có 7 kí tự và bắt đầu bằng CT");
                kt = false;
                if (kt) {
                    for (CTHDN hdn : list) {
                        if (hdn.getMaCTHD().contains(this._maCTHD)) {
                            System.out.println("Lỗi:mã chi tiết hóa đơn nhập đã tồn tại");
                            kt = false;
                            break;
                        }
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
                        System.out.println(" MÃ :"+this._maMH+"có tên là :"+mh.getTenMatHang()+" có SIZE là"+mh.getCongdung());
                        this._donvitinh=mh.getDonvitinh();
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

//        do {
//            kt = true;
//            System.out.print("NHập SIZE :");
//            this._donvitinh=scan.nextLine();
//
////            kt = false;
//
//            if (kt) {
//                List<MatHang> ds = DocFile();
//                kt = false;
//                for (MatHang mh : ds) {
//                    if (this._donvitinh.contains(mh.getDonvitinh()) && this._maMH.equals(mh.getMaMatHang())) {
//                        kt = true;
//                        System.out.println("Thành công");
//                        break;
//                    }
//                }
//                if (kt == false) {
//                    System.out.println("Lỗi: Size giày không tồn tại hoặc đã hết vui lòng nhập thêm");
//                }
//
//            }
//        } while (!(kt));
        return this;
    }
public  CTHDN Xoa()
{


    return  this;
}
    public CTHDN Sua() {
        Scanner scan = new Scanner(System.in);
        boolean kt = true;
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
        return this;

    }

    public String ToString2() {
        return "║" + String.format("%-19s", this._maCTHD) + "|" + String.format("%-17s", this._maHDN) + "|" + String.format("%-18s", this._maMH) + "|" + String.format("%-14s", this._soluong) + "|" + String.format("%-16s", this._donvitinh) + "|" + String.format("%-33s", "") + "║";
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
                MatHang mh = new MatHang(array[0], array[1], array[2], Integer.parseInt(array[3]), Integer.parseInt(array[4]), array[5],Integer.parseInt(array[6]));
                ds.add((MatHang) mh);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return ds;
    }
}
