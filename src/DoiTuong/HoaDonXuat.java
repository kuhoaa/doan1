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
    private String _maKH;
    private Date _ngayxuat;
    private  String _tenKH;
    private SimpleDateFormat nt = new SimpleDateFormat("dd/MM/yyyy");

    public HoaDonXuat() {

       _maKH =" ";
       _tenKH=" ";
       _ngayxuat =null;
       _tenKH =" ";
       _maHDX=" ";
    }

    public HoaDonXuat(String _maHDX, String _maNV, String _maKH , String _tenKH, Date _ngayxuất) {
        this._maHDX = _maHDX;
        this._maNV = _maNV;
        this._maKH = _maKH;
        this._tenKH=_tenKH;
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

    public String getmaKH() {
        return _maKH;
    }
    public void set_tenKH(String tenKH) {
        this._tenKH=tenKH;
    }
    public String get_tenKH() {
        return _tenKH;
    }

    public void setmaKH(String _tenKH) {
        this._maKH = _tenKH;
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
                    if (mh.getMaNhanVien().contains(this._maNV)&&this._maNV.equals(mh._maNhanVien)) {
                        kt = true;
                        System.out.println("Tên nhân viên ứng với mã là: "+mh._tenNhanVien);
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
            System.out.print("nhập mã khách hàng:");
            this._maKH = scan.nextLine();
            if (("").contains(this._maKH)) {
                System.out.println("Lỗi: Mã khách hàng không được để trống");
                kt = false;
            }


            if (kt && !Pattern.matches("[K]{1}[H]{1}[0-9]{5}", this._maKH)) {
                System.out.println("Lỗi: Mã khách hàng phải có độ dài 7 ký tự và bắt đầu bởi KH");
                kt = false;
            }
            if (kt) {
                List<KhachHang> kh = DocFilekh();
                kt = false;
                for (KhachHang mh : kh) {
                    if (this._maKH.contains(getmaKH())&&this._maKH.equals(mh.getMaKhachHang())){
                        kt = true;
                        System.out.println("mã vừa nhập tên khách hàng là :"+mh._tenKhacHhang);
                        break;
                    }
                }
                if (kt == false) {
                    System.out.println("Lỗi: mã khách hàng không tồn tại, vui lòng nhập lại");
                }
            }
        } while (!(kt));
        do {
            kt = true;
            System.out.print("Nhập tên khách hàng:");
            this._tenKH = scan.nextLine();
            if (("").contains(this._tenKH)) {
                System.out.println("Lỗi: Tên khách hàng không được để trống");
                kt = false;
            }
            if (kt) {
                List<KhachHang> kh = DocFilekh();
                kt = false;
                for (KhachHang mh : kh) {
                    if (this._tenKH.contains(get_tenKH())&&this._tenKH.equals(mh.getTenKhachHang())){
                        kt = true;

                        break;
                    }
                }
                if (kt == false) {
                    System.out.println("Lỗi: Mã khách hàng không khớp tên, vui lòng nhập lại");
                }
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
            System.out.print("nhập mã khách hàng:");
            this._maKH = scan.nextLine();
            if (("").contains(this._maKH)) {
                System.out.println("Lỗi: Mã khách hàng không được để trống");
                kt = false;
            }


            if (kt && !Pattern.matches("[K]{1}[H]{1}[0-9]{5}", this._maKH)) {
                System.out.println("Lỗi: Mã khách hàng phải có độ dài 7 ký tự và bắt đầu bởi KH");
                kt = false;
            }
            if (kt) {
                List<KhachHang> kh = DocFilekh();
                kt = false;
                for (KhachHang mh : kh) {
                    if (this._maKH.contains(getmaKH())&&this._maKH.equals(mh.getMaKhachHang())){
                        kt = true;
                        System.out.println("mã vừa nhập tên khách hàng là :"+mh._tenKhacHhang);
                        break;
                    }
                }
                if (kt == false) {
                    System.out.println("Lỗi: mã khách hàng không tồn tại, vui lòng nhập lại");
                }
                if(kt==true)
                {
                    this._maKH=this._maKH+"|"+this._tenKH;
                }
            }
        } while (!(kt));
        do {
            kt = true;
            System.out.print("Nhập tên khách hàng:");
            this._tenKH = scan.nextLine();
            if (("").contains(this._tenKH)) {
                System.out.println("Lỗi: Tên khách hàng không được để trống");
                kt = false;
            }
            if (kt) {
                List<KhachHang> kh = DocFilekh();
                kt = false;
                for (KhachHang mh : kh) {
                    if (this._tenKH.contains(getmaKH())&&this._tenKH.equals(mh.getMaKhachHang())){
                        kt = true;

                        break;
                    }
                }
                if (kt == false) {
                    System.out.println("Lỗi: Mã khách hàng không khớp tên, vui lòng nhập lại");
                }
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
        return "║" + String.format("%-7s", this._maHDX) + "|" + String.format("%-7s", this._maNV) + "|" + String.format("%-31s", this._maKH) + "|"+ String.format("%-30s", this._tenKH) + "|" + strDate + "║";
    }

    public void hienthi() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(this._ngayxuat);
        System.out.println("║ Mã hóa đơn  : " + String.format("%-112s", this._maHDX) + "║");
        System.out.println("║ Mã nhân viên: " + String.format("%-112s", this._maNV) + "║");
        System.out.println("║ Mã khách hàng: " + String.format("%-111s", this.getmaKH())  + "║");
        System.out.println("║ Tên khách hàng: " + String.format("%-110s",this._tenKH) + "║");
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


    private List<KhachHang> DocFilekh() {
        List<KhachHang> ds = new ArrayList<KhachHang>();
        String fileName = "khachhang.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(Pattern.quote("|"));
                //địa chỉ không bắt nhập
                if (array.length == 4) {
                    KhachHang kh = new KhachHang(array[0], array[1], array[2], array[3]);
                    ds.add((KhachHang) kh);
                } else {
                    KhachHang kh = new KhachHang(array[0], array[1], array[2], "");
                    ds.add((KhachHang) kh);
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return ds;
    }
}