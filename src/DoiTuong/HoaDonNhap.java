/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoiTuong;

import GiaoDien.QLNhaCungCap;
import GiaoDien.QLNhanVien;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author LENOVO
 */
public class HoaDonNhap {

    private String _maHDN;
    private String _maNV;
    private String _mancc;
    ;
    private Date _ngaynhap;
    private SimpleDateFormat nt = new SimpleDateFormat("dd/MM/yyyy");

    public HoaDonNhap() {

    }

    public HoaDonNhap(String maHDN, String maNV, String _mancc, Date ngaynhap) {
        this._maHDN = maHDN;
        this._maNV = maNV;
        this._mancc = _mancc;
        this._ngaynhap = ngaynhap;
    }

    public HoaDonNhap(String hdn) {
        String[] sc = new String[4];
        sc = hdn.split("|");
        this._maHDN = sc[0];
        this._maNV = sc[1];
        this._mancc = sc[2];
        try {
            this._ngaynhap = nt.parse(sc[3]);
        } catch (ParseException ex) {
            Logger.getLogger(HoaDonNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMaHDN() {
        return _maHDN;
    }

    public void setMaHDN(String maHDN) {
        this._maHDN = maHDN;
    }

    public String getMaNV() {
        return _maNV;
    }

    public void setMaNV(String maNV) {
        this._maNV = maNV;
    }

    public String get_mancc() {
        return _mancc;
    }

    public void set_mancc(String _mancc) {
        this._mancc = _mancc;
    }

    public Date getNgaynhap() {
        return _ngaynhap;
    }

    public void setNgaynhap(Date ngaynhap) {
        this._ngaynhap = ngaynhap;
    }

    public SimpleDateFormat getNt() {
        return nt;
    }

    public void setNt(SimpleDateFormat nt) {
        this.nt = nt;
    }

    public HoaDonNhap Them(List<HoaDonNhap> list) {
        Scanner scan = new Scanner(System.in);
        boolean kt = true;
        do {
            kt = true;
            System.out.print("nhập mã hóa đơn nhập:");
            this._maHDN = scan.next();
            if (("").contains(this._maHDN)) {
                System.out.println("Lỗi: mã hóa đơn nhập không được để trống");
                kt = false;
            }
            if (kt && !Pattern.matches("[H]{1}[D]{1}[N]{1}[0-9]{4}", this._maHDN)) {
                System.out.println("Lỗi: Mã hóa đơn phải có 7 kí tự và bắt đầu bằng HDN");
                kt = false;
            }
            if (kt) {
                for (HoaDonNhap hdn : list) {
                    if (hdn.getMaHDN().contains(this._maHDN)) {
                        System.out.println("Lỗi:mã hóa đơn nhập đã tồn tại");
                        kt = false;
                        break;
                    }
                }
            }
        } while (!(kt));
        QLNhanVien qlnv = new QLNhanVien();
        do {

            kt = true;
            scan.nextLine();
            System.out.print("nhập mã nhân viên nhập:");
            this._maNV = scan.nextLine();
            if (kt && !Pattern.matches("[N]{1}[V]{1}[0-9]{5}", this._maNV)) {
                System.out.println("Lỗi: Mã nhân viên nhập phải có 7 kí tự và bắt đầu bằng NV");

                kt = false;
            } else {
                if (qlnv.TimNV_Ma(_maNV) == null) {
                    System.out.println("Lỗi: Mã nhân viên chưa tồn tại");
                    kt = false;
                }

            }
        } while (!(kt));

        do {
            kt = true;
            System.out.print("nhập mã Nhà cung cấp :");
            this._mancc = scan.nextLine();
            if(kt && !Pattern.matches("[N]{1}[C]{1}[C]{1}[0-9]{4}",this._mancc)){
                System.out.println("Lỗi: Mã khách hàng phải có độ dài 7 ký tự và bắt đầu bởi CC");
                kt = false;
            }
            if (kt) {
//                    List<MatHang> ds = new ArrayList<MatHang>();//lí do
                List<NhaCungCap> ds = DocFile();
                kt = false;
                for (NhaCungCap mh : ds) {
                    if (mh.getMancc().contains(this._mancc)) {
                        kt = true;
                        System.out.println("tên ứng với mã nhà cung cấp là :"+mh._tenncc);
                        break;
                    }
                }
                if (kt == false) {
                    System.out.println("Lỗi: mã mặt hàng không tồn tại");
                }
            }
        } while (!(kt));





        do {
            try {
                kt = true;
                System.out.print("nhập ngày nhập:");
                String ngayNhap = scan.nextLine();
                this._ngaynhap = new SimpleDateFormat("dd/MM/yyyy").parse(ngayNhap);
                Date dateNow = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
                if (dateNow.before(this._ngaynhap)) {
                    System.out.println("Lỗi: Ngày nhập không được lớn hơn ngày hiện tại");
                    kt = false;
                }
            } catch (Exception e) {
                System.out.println("Lỗi: Sai định dạng ngày tháng");
                kt = false;
            }
        } while (!(kt));

        return this;
    }

    public HoaDonNhap Sua() {
        Scanner scan = new Scanner(System.in);
        boolean kt = true;
        QLNhanVien qlnv = new QLNhanVien();
        do {

            kt = true;
            scan.nextLine();
            System.out.print("nhập mã nhân viên nhập:");
            this._maNV = scan.nextLine();
            if (kt && !Pattern.matches("[N]{1}[V]{1}[0-9]{5}", this._maNV)) {
                System.out.println("Lỗi: Mã nhân viên nhập phải có 7 kí tự và bắt đầu bằng NV");

                kt = false;
            } else {
                if (qlnv.TimNV_Ma(_maNV) == null) {
                    System.out.println("Lỗi: Mã nhân viên chưa tồn tại");
                    kt = false;
                }

            }
        } while (!(kt));

        do {
            kt = true;
            System.out.print("nhập mã Nhà cung cấp :");
            this._mancc = scan.nextLine();
            if(kt && !Pattern.matches("[N]{1}[C]{1}[C]{1}[0-9]{4}",this._mancc)){
                System.out.println("Lỗi: Mã khách hàng phải có độ dài 7 ký tự và bắt đầu bởi NCC");
                kt = false;
            }
            if (kt) {
//                    List<MatHang> ds = new ArrayList<MatHang>();//lí do
                List<NhaCungCap> ds = DocFile();
                kt = false;
                for (NhaCungCap mh : ds) {
                    if (mh.getMancc().contains(this._mancc)) {
                        kt = true;
                        System.out.println("tên ứng với mã nhà cung cấp là :"+mh._tenncc);
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

    public String ToString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(this._ngaynhap);
        return "║" + String.format("%-7s", this._maHDN) + "|" + String.format("%-7s", this._maNV) + "|" + String.format("%-30s", this._mancc) + "|" + strDate + "║";
    }

    public void HienThi() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(this._ngaynhap);
        System.out.println("║ Mã hóa đơn  : " + String.format("%-107s", this._maHDN) + "║");
        System.out.println("║ Mã nhân viên: " + String.format("%-107s", this._maNV) + "║");
        System.out.println("║ Nhà cung cấp: " + String.format("%-107s", this._mancc) + "║");
        System.out.println("║ Ngày nhập   : " + String.format("%-107s", strDate) + "║");
        System.out.println("║ CHI TIẾT HÓA ĐƠN: " + String.format("%-103s", "") + "║");
    }


    private List<NhaCungCap> DocFile() {
        List<NhaCungCap> ds = new ArrayList<NhaCungCap>();
        String fileName = "NhaCungCap.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(Pattern.quote("|"));
                NhaCungCap ncc = new NhaCungCap(array[0], array[1], array[2], array[3]);
                ds.add((NhaCungCap) ncc);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return ds;
    }
}