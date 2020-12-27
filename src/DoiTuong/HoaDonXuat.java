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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author LENOVO
 */
public class HoaDonXuat {
    private String _maHDX;
    private String _maNV;
    private String _tenKH;
    private Date _ngayxuat;
    private SimpleDateFormat nt = new SimpleDateFormat("dd/MM/yyyy");

    public HoaDonXuat() {

    }

    public HoaDonXuat(String _maHDX, String _maNV, String _tenKH, Date _ngayxuất) {
        this._maHDX = _maHDX;
        this._maNV = _maNV;
        this._tenKH = _tenKH;
        this._ngayxuat = _ngayxuất;
    }

    public String getMaHDX() {
        return _maHDX;
    }

    public void setMaHDX(String _maHDX) {
        this._maHDX = _maHDX;
    }

    public String getMaNV() {
        return _maNV;
    }

    public void setMaNV(String _maNV) {
        this._maNV = _maNV;
    }

    public String getTenKH() {
        return _tenKH;
    }

    public void setTenKH(String _tenKH) {
        this._tenKH = _tenKH;
    }

    public Date getNgayxuat() {
        return _ngayxuat;
    }

    public void setNgayxuat(Date _ngayxuất) {
        this._ngayxuat = _ngayxuất;
    }

    public SimpleDateFormat getNt() {
        return nt;
    }

    public void setNt(SimpleDateFormat nt) {
        this.nt = nt;
    }

    public HoaDonXuat Them(List<HoaDonXuat> list) {
        Scanner scan = new Scanner(System.in);
        boolean kt = true;
        do {
            kt = true;
            System.out.print("nhập mã hóa đơn xuất:");
            this._maHDX = scan.next();
            if (("").contains(this._maHDX)) {
                System.out.println("Lỗi: mã hóa đơn nhập không được để trống");
                kt = false;
            }
            if (kt && !Pattern.matches("[H]{1}[D]{1}[X]{1}[0-9]{4}", this._maHDX)) {
                System.out.println("Lỗi: Mã hóa đơn phải có 7 kí tự và bắt đầu bằng HDX");
                kt = false;
            }
            if (kt) {
                for (HoaDonXuat hdx : list) {
                    if (hdx.getMaHDX().contains(this._maHDX)) {
                        System.out.println("Lỗi:mã hóa đơn xuất đã tồn tại");
                        kt = false;
                        break;
                    }
                }
            }
        } while (!(kt));
        do {

            kt = true;
            scan.nextLine();
            System.out.print("nhập mã nhân viên xuất:");
            this._maNV = scan.nextLine();
            if (kt && !Pattern.matches("[N]{1}[V]{1}[0-9]{5}", this._maNV)) {
                System.out.println("Lỗi: Mã nhân viên nhập phải có 7 kí tự và bắt đầu bằng NV");
                kt = false;
            }
            if (kt) {
                List<NhanVien> kh = DocFile();
                kt = false;
                for (NhanVien mh : kh) {
                    if (mh.getMaNhanVien().contains(this._maNV)) {
                        kt = true;
                        System.out.println(mh._maNhanVien);
                        break;
                    }
                }
                if (kt == false) {
                    System.out.println("Lỗi: mã nhân viên không tồn tại");
                }
            }
        } while (!(kt));
        do {
            kt = true;
            System.out.print("nhập tên khách hàng:");
            this._tenKH = scan.nextLine();
            if (kt && this._tenKH.length() > 30) {
                System.out.println("Lỗi: tên khách hàng phải nhỏ hơn 30 kí tự");
                kt = false;
            }
        } while (!(kt));
        do {
            try {
                kt = true;
                System.out.print("nhập ngày xuất:");
                String ngayxuat = scan.nextLine();
                this._ngayxuat = new SimpleDateFormat("dd/MM/yyyy").parse(ngayxuat);
                Date dateNow = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
                if (dateNow.before(this._ngayxuat)) {
                    System.out.println("Lỗi: Ngày xuất không được lớn hơn ngày hiện tại");
                    kt = false;
                }
            } catch (Exception e) {
                System.out.println("Lỗi: Sai định dạng ngày tháng");
                kt = false;
            }
        } while (!(kt));
        return this;
    }

    public HoaDonXuat Sua() {
        Scanner scan = new Scanner(System.in);
        boolean kt = true;
        do {

            kt = true;
            scan.nextLine();
            System.out.print("nhập mã nhân viên xuất:");
            this._maNV = scan.nextLine();
            if (kt && !Pattern.matches("[N]{1}[V]{1}[0-9]{5}", this._maNV)) {
                System.out.println("Lỗi: Mã nhân viên nhập phải có 7 kí tự và bắt đầu bằng NV");
                kt = false;
            }
            if (kt) {
                List<NhanVien> kh = DocFile();
                kt = false;
                for (NhanVien mh : kh) {
                    if (mh.getMaNhanVien().contains(this._maNV)) {
                        kt = true;
                        System.out.println(mh._maNhanVien);
                        break;
                    }
                }
                if (kt == false) {
                    System.out.println("Lỗi: mã nhân viên không tồn tại");
                }
            }
        } while (!(kt));

        do {
            kt = true;
            System.out.print("nhập tên khách hàng :");
            this._tenKH = scan.nextLine();
            if (kt && this._tenKH.length() > 30) {
                System.out.println("Lỗi: tên khách hàng phải nhỏ hơn 30 kí tự");
                kt = false;
            }
        } while (!(kt));
        do {
            try {
                kt = true;
                System.out.print("nhập ngày xuất:");
                String ngayxuat = scan.nextLine();
                this._ngayxuat = new SimpleDateFormat("dd/MM/yyyy").parse(ngayxuat);
                Date dateNow = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
                if (dateNow.before(this._ngayxuat)) {
                    System.out.println("Lỗi: Ngày xuất không được lớn hơn ngày hiện tại");
                    kt = false;
                }
            } catch (Exception e) {
                System.out.println("Lỗi: Sai định dạng ngày tháng");
                kt = false;
            }
        } while (!(kt));
        return this;
    }

    public String ToString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(this._ngayxuat);
        return "║" + String.format("%-7s", this._maHDX) + "|" + String.format("%-7s", this._maNV) + "|" + String.format("%-30s", this._tenKH) + "|" + strDate + "║";
    }

    public void hienthi() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(this._ngayxuat);
        System.out.println("║ Mã hóa đơn  : " + String.format("%-112s", this._maHDX) + "║");
        System.out.println("║ Mã nhân viên: " + String.format("%-112s", this._maNV) + "║");
        System.out.println("║ Tên khách hàng: " + String.format("%-110s", this._tenKH) + "║");
        System.out.println("║ Ngày xuất: " + String.format("%-115s", strDate) + "║");
        System.out.println("║ CHI TIẾT HÓA ĐƠN: " + String.format("%-108s", "") + "║");
    }


    private List<NhanVien> DocFile() {
        List<NhanVien> ds = new ArrayList<NhanVien>();
        String fileName = "NhanVien.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(Pattern.quote("|"));
                NhanVien nv = new NhanVien(array[0], array[1], array[2], array[3], array[4]);
                ds.add((NhanVien) nv);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return ds;
    }
}