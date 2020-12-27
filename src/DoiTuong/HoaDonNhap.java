/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoiTuong;

import GiaoDien.QLNhaCungCap;
import GiaoDien.QLNhanVien;
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
    private String _tenNCC;
    private Date _ngaynhap;
    private SimpleDateFormat nt = new SimpleDateFormat("dd/MM/yyyy");

    public HoaDonNhap() {

    }

    public HoaDonNhap(String maHDN, String maNV, String tenNCC, Date ngaynhap) {
        this._maHDN = maHDN;
        this._maNV = maNV;
        this._tenNCC = tenNCC;
        this._ngaynhap = ngaynhap;
    }

    public HoaDonNhap(String hdn) {
        String[] sc = new String[4];
        sc = hdn.split("|");
        this._maHDN = sc[0];
        this._maNV = sc[1];
        this._tenNCC = sc[2];
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

    public String getTenNCC() {
        return _tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this._tenNCC = tenNCC;
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
        QLNhaCungCap qlncc = new QLNhaCungCap();
        do {
            kt = true;
            System.out.print("nhập tên nhà cung cấp:");
            this._tenNCC = scan.nextLine();
            if (kt && this._tenNCC.length() > 50) {
                System.out.println("Lỗi: tên nhà cung cấp phải nhỏ hơn 50 kí tự");
                kt = false;
            } else {
                if (qlncc.TimNV_Ten(_tenNCC) == null) {
                    System.out.println("Lỗi: Tên nhà cung cấp chưa tồn tại");
                    kt = false;

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
            //hiển thị dữ liệu cũ ra
            System.out.print("nhập mã nhân viên nhập '("+ this._maNV +")':");
            //khai báo 1 biến mới chứa dữ liệu mới
            String maNVMoi = scan.nextLine();
            //kiểm tra nếu ko nhập gì thì bỏ qua
            if(!"".contains(maNVMoi))
            {
                //nếu nhập thì thay dữ liệu cũ bằng dữ liệu mới
                this._maNV = maNVMoi;
                if (kt && !Pattern.matches("[N]{1}[V]{1}[0-9]{5}", this._maNV)) {
                    System.out.println("Lỗi: Mã nhân viên nhập phải có 7 kí tự và bắt đầu bằng NV");
                    kt = false;
                } else {
                    if (qlnv.TimNV_Ma(_maNV) == null) {
                        System.out.println("Lỗi: Mã nhân viên chưa tồn tại");
                        kt = false;
                    }
                }
            }

        } while (!(kt));
         QLNhaCungCap qlncc = new QLNhaCungCap();
        do {
            kt = true;
            System.out.print("nhập tên nhà cung cấp:");
            this._tenNCC = scan.nextLine();
            if (kt && this._tenNCC.length() > 50) {
                System.out.println("Lỗi: tên nhà cung cấp phải nhỏ hơn 50 kí tự");
                kt = false;
            }
            else {
                if (qlncc.TimNV_Ten(_tenNCC) == null) {
                    System.out.println("Lỗi: Tên nhà cung cấp chưa tồn tại");
                    kt = false;

                }
            }
        } while (!(kt));
        return this;
    }

    public String ToString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(this._ngaynhap);
        return "║" + String.format("%-7s", this._maHDN) + "|" + String.format("%-7s", this._maNV) + "|" + String.format("%-30s", this._tenNCC) + "|" + strDate + "║";
    }

    public void HienThi() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(this._ngaynhap);
        System.out.println("║ Mã hóa đơn  : " + String.format("%-107s", this._maHDN) + "║");
        System.out.println("║ Mã nhân viên: " + String.format("%-107s", this._maNV) + "║");
        System.out.println("║ Nhà cung cấp: " + String.format("%-107s", this._tenNCC) + "║");
        System.out.println("║ Ngày nhập   : " + String.format("%-107s", strDate) + "║");
        System.out.println("║ CHI TIẾT HÓA ĐƠN: " + String.format("%-103s", "") + "║");
    }

}
