/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoiTuong;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author LENOVO
 */
//mã mặt hàng, tên mặt hàng, công dụng, giá bán, số lượng, đơn vị tính
public class MatHang {

    private String _maMatHang;
    private String _tenMatHang;
    private String _congdung;
    private int _giaban;
    private int _soluong;
    private String _donvitinh;

    public MatHang() {
        _maMatHang = " ";
        _tenMatHang = " ";
        _congdung = " ";
        _giaban = 1;
        _soluong = 0;
        _donvitinh = " ";
    }

    public MatHang(String maMatHang, String tenMatHang, String congdung, int giaban, int soluong, String donvitinh) {
        this._maMatHang = maMatHang;
        this._tenMatHang = tenMatHang;
        this._congdung = congdung;
        this._giaban = giaban;
        this._soluong = soluong;
        this._donvitinh = donvitinh;
    }

    public MatHang(String mh) {
        String[] sc = new String[6];
        sc = mh.split("|");
        this._maMatHang = sc[0];
        this._tenMatHang = sc[1];
        this._congdung = sc[2];
        this._giaban = Integer.parseInt(sc[3]);
        this._soluong = Integer.parseInt(sc[4]);
        this._donvitinh = sc[5];
    }

    public String getMaMatHang() {
        return _maMatHang;
    }

    public void setMaMatHang(String _maMatHang) {
        this._maMatHang = _maMatHang;
    }

    public String getTenMatHang() {
        return _tenMatHang;
    }

    public void setTenMatHang(String _tenMatHang) {
        this._tenMatHang = _tenMatHang;
    }

    public String getCongdung() {
        return _congdung;
    }

    public void setCongdung(String _congdung) {
        this._congdung = _congdung;
    }

    public int getGiaban() {
        return _giaban;
    }

    public void setGiaban(int _giaban) {
        this._giaban = _giaban;
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

    public MatHang Them(List<MatHang> list) {
        Scanner scan = new Scanner(System.in);
        boolean kt = true;
        do {
            kt = true;
            System.out.print("nhập mã mặt hàng:");
            this._maMatHang = scan.next();
            if (("").contains(this._maMatHang)) {
                System.out.println("Lỗi: mã mặt hàng không được để trống");
                kt = false;
            }
            if (kt && !Pattern.matches("[M]{1}[H]{1}[0-9]{5}", this._maMatHang)) {
                System.out.println("Lỗi: Mã mặt hàng phải có 7 kí tự và bắt đầu bằng MH");
                kt = false;
            }
            if (kt) {
                for (MatHang mh : list) {
                    if (mh.getMaMatHang().contains(this._maMatHang)) {
                        System.out.println("Lỗi:mã mặt hàng đã tồn tại");
                        kt = false;
                        break;
                    }
                }
            }
        } while (!(kt));
        do {
            kt = true;
            scan.nextLine();
            System.out.print("nhập tên mặt hàng:");
            this._tenMatHang = scan.nextLine();
            if ((" ").contains(this._tenMatHang)) {
                System.out.println("Lỗi: tên mặt hàng không được để trống");
                kt = false;
            }
//            if (kt && !Pattern.matches("[N]{1}[P]{1}[K]{1}[0-9]{5}", this._tenMatHang)) {
//                System.out.print("Lỗi:tên mặt hàng phải có 8 kí tự bắt đầu bằng NPK");
//            }

        } while (!(kt));
        do {
            kt = true;

            System.out.print("nhập loại Giày(nam/nữ):");
            this._congdung = scan.nextLine();

            if (kt && (!"nam".equals(this._congdung) && !"nữ".equals(this._congdung)&&!"Nữ".equals(this._congdung)&&!"Nam".equals(this._congdung))) {
                System.out.println("Lỗi: loại giày nữ hoặc nam!");
                kt = false;
            }

            if (("").contains(this._congdung)) {
                System.out.println("Lỗi: loại giày không được để trống");
                kt = false;
//            }
//            if (kt && this._congdung.length() > 50) {
//                System.out.println("Lỗi: công dụng phân bón phải nhỏ hơn 20 kí tự");
            }
            }
            while (!(kt)) ;
            do {
                kt = true;
                System.out.print("nhập giá bán:");
                this._giaban = Integer.parseInt(scan.nextLine());
                if (kt && this._giaban < 0) {
                    System.out.println("Lỗi: Gía bán phải lớn hơn 0");
                    kt = false;
                }

            } while (!(kt));
            do {
                kt = true;
                System.out.print("nhập số lượng:");
                this._soluong = Integer.parseInt(scan.nextLine());
                if (kt && this._soluong < 0) {
                    System.out.println("Lỗi: số lượng phải lớn hơn 0");
                    kt = false;
                }

            } while (!(kt));
            do {
                kt = true;
                do {
                    System.out.print("nhập SIZE giày(từ 15-50):");
                    this._donvitinh=scan.nextLine();
                }while (Xulingoaile.Kiemtra(_donvitinh)==false);
                if (("").contains(this._donvitinh)) {
                    System.out.println("Lỗi: loại giày không được để trống");
                    kt = false;
                }
                else  if (Integer.parseInt(this._donvitinh)<15||Integer.parseInt(this._donvitinh)>50) {
                    System.out.print("Size giầy chỉ từ 15-50 vui long nhập lại ");

                }
            } while (!(kt));
            return this;
        }


    public MatHang Sua() {
        Scanner scan = new Scanner(System.in);
        boolean kt = true;
        do {
            kt = true;
            System.out.print("nhập tên mặt hàng:");
            this._tenMatHang = scan.nextLine();
            if ((" ").contains(this._tenMatHang)) {
                System.out.println("Lỗi: tên mặt hàng không được để trống");
                kt = false;
            }

        } while (!(kt));
            do {
                kt = true;
                System.out.print("nhập loại Giày(nam/nữ):");
                this._congdung = scan.nextLine();

                if (kt && (!"nam".equals(this._congdung.toLowerCase()) && !"nữ".equals(this._congdung.toLowerCase()))) {
                    System.out.println("Lỗi: loại giày nữ hoặc nam!");
                    kt = false;
                }

                if (("").contains(this._congdung)) {
                    System.out.println("Lỗi: loại giày không được để trống");
                    kt = false;
                }
            }while (!(kt));

                do {
                    kt = true;
                    System.out.print("nhập giá bán:");
                    this._giaban = Integer.parseInt(scan.nextLine());
                    if (kt && this._giaban < 0) {
                        System.out.println("Lỗi: Gía bán phải lớn hơn 0");
                        kt = false;
                    }
                    while (!(kt)) ;

                } while (!(kt));
                do {
                    kt = true;
                    System.out.print("nhập số lượng:");
                    this._soluong = Integer.parseInt(scan.nextLine());
                    if (kt && this._soluong < 0) {
                        System.out.println("Lỗi: số lượng phải lớn hơn 0");
                        kt = false;
                    }

                } while (!(kt));
                do {

                    kt = true;
                    do {
                        System.out.print("nhập SIZE giày(từ 15-50:");
                        this._donvitinh=scan.nextLine();
                    }while (Xulingoaile.Kiemtra(_donvitinh)==false);

                    if (("").contains(this._donvitinh)) {
                        System.out.println("Lỗi: loại giày không được để trống");
                        kt = false;
                    }
                    else  if (Integer.parseInt(this._donvitinh)<15||Integer.parseInt(this._donvitinh)>50) {
                        System.out.print("Size giầy chỉ từ 15-50 vui long nhập lại ");

                    }
                }while (!(kt));
                return this;
            }

    public String ToString() {
        return "║" + String.format("%-7s", this._maMatHang) + "|" + String.format("%-21s", this._tenMatHang) + "|" + String.format("%-30s", this._congdung) + "|" + String.format("%-16s", Integer.toString(this._giaban)) +"|"+ String.format("%-12s", Integer.toString(this._soluong)) + "|" + String.format("%-18s", this._donvitinh) +"|"+String.format("%-25s", "")+ "║";
    }
}
