/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import DoiTuong.CTHDN;
import DoiTuong.HoaDonNhap;
import HoTro.HienThi;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author LENOVO
 */
public class QLHoaDonNhap {

    static int chucNang = 0;
    static boolean thoat = false;
    static boolean luachon = false;
    static String chonThoat = "";
    static Scanner Scan = new Scanner(System.in);
    public List<HoaDonNhap> ds = new ArrayList<HoaDonNhap>();
    static List<CTHDN> dsct = new ArrayList<CTHDN>();

    private SimpleDateFormat nt = new SimpleDateFormat("dd/MM/yyyy");
    HienThi hienThi = new HienThi();

    public void KhoiTao() {
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            ds = DocFile();
            dsct = DocFileChiTiet();
            HienThiDanhSach(ds);
            System.out.println("╟───────────────────────────────────────────────────────────────────────────╢");
            System.out.println("║1. Thêm, 2. Sửa, 3. Xóa, 4. Tìm theo mã, 5. Tìm theo tên nhà cung cấp, 6. Menu chính, 7. Thoát                            ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");
            System.out.print("Chọn chức năng: ");
            chucNang = Scan.nextInt();
            switch (chucNang) {
                case 1:
                    Them();
                    break;
                case 2:
                    Sua();
                    break;
                case 3:
                    Xoa();
                    break;
                case 4:
                    TimTheoMa();
                    break;
                case 5:
                    TimTheoTenNCC();
                    break;
                case 6:
                    thoat = true;
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        } while (!thoat);
    }

    private void TieuDeChucNang() {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           CHỨC NĂNG QUẢN LÝ HÓA ĐƠN NHẬP                                                                 ║");
    }

    private void HienThiDanhSach(List<HoaDonNhap> dshdn) {
        System.out.println("║                                DANH SÁCH HÓA ĐƠN NHẬP                                                                    ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────╢");
        for (HoaDonNhap hdn : dshdn) {
            hdn.HienThi();
            System.out.println("╟───────────────────────────────────────────────────────────────────────────╢");
            System.out.println("║     MÃ CT         |    MÃ HÓA ĐƠN   |    MÃ MẶT HÀNG   |   SỐ LƯỢNG   |       SIZE     |                          ║");

            for (CTHDN cthdn : dsct) {
                if (cthdn.getHDN().contains(hdn.getMaHDN())) {
                    System.out.println(cthdn.ToString2());
                }
            }
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");
    }
    private void Them() {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                                  THÊM HÓA ĐƠN NHẬP                                                                  ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();
            HoaDonNhap hdn = new HoaDonNhap();
            hdn.Them(ds);
            dsct = DocFileChiTiet();
            List<CTHDN> dsctThem = new ArrayList<CTHDN>();

            boolean nhd = false;
            do {
                System.out.println("Thêm chi tiết hóa đơn " + (dsctThem.size() + 1));
                CTHDN cthdn = new CTHDN();
                cthdn.Them(dsct);
                dsctThem.add(cthdn);
                System.out.print("Bạn có muốm thêm chi tiết hóa đơn nữa không? (y/n):");
                chonThoat = Scan.next();
                if (chonThoat.equalsIgnoreCase("n")) {
                    nhd = true;
                }
            } while (!nhd);

            System.out.print("Bạn có muốm lưu hóa đơn nhập này không? (y/n):");
            chonThoat = Scan.next();
            if (chonThoat.equalsIgnoreCase("y")) {
                ds.add(hdn);
                GhiFile(ds);
                for (int i = 0; i < dsctThem.size(); i++) {
                    CTHDN cth = dsctThem.get(i);
                    cth.setMaHDN(hdn.getMaHDN());
                    dsct.add(cth);
                    QLMatHang qLMatHang = new QLMatHang();
                    qLMatHang.TangSoLuong(cth.getMaMH(), cth.getSoluong());
                }
                GhiFileChiTiet(dsct);
                System.out.println("Thêm hóa đơn nhập thành công thành công!");
            }
            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }
        } while (!luachon);
    }

    private void Sua() {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                                       SỬA HÓA ĐƠN NHẬP                                                                 ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();
            System.out.print("Nhập mã hóa đơn nhập:");
            String maHDN = Scan.next();
            boolean kt = false;
            List<HoaDonNhap> dsMoi = new ArrayList<HoaDonNhap>();
            dsct = DocFileChiTiet();
            for (HoaDonNhap hdn : ds) {
                if (hdn.getMaHDN().contains(maHDN)) {
                    HienThiDanhSach(ds);
                    System.out.println("Nhập các thông tin thay đổi!");
                    kt = true;
                    hdn.Sua();
                }
                dsMoi.add(hdn);
            }

            if (!kt) {
                System.out.println("Không tìm thấy mã hóa đơn nhập cần sửa!");
            } else {
                dsct = DocFileChiTiet();
                List<CTHDN> dsctSua = new ArrayList<CTHDN>();
                boolean nhd = false;
                boolean ktct = false;
                do {
                    System.out.print("Bạn có muốm sửa chi tiết hóa đơn không? (y/n):");
                    chonThoat = Scan.next();
                    if (chonThoat.equalsIgnoreCase("n")) {
                        nhd = true;
                    } else {
                        System.out.print("Nhập mã chi tiết hóa đơn nhập:");
                        String maCTHDN = Scan.next();

                        for (CTHDN cthd : dsct) {
                            if (cthd.getMaCTHD().contains(maCTHDN)) {
                                cthd.Sua();
                                ktct = true;
                                dsctSua.add(cthd);
                            }
                            dsctSua.add(cthd);
                        }

                        if (!ktct) {
                            System.out.println("Không tìm thấy mã chi tiết hóa đơn nhập cần sửa!");
                        }
                    }
                } while (!nhd);

                System.out.print("Bạn có muốn thay đổi thông tin hóa đơn  này không? (y/n):");
                chonThoat = Scan.next();
                if (chonThoat.equalsIgnoreCase("y")) {
                    GhiFile(dsMoi);
                    System.out.println("Sửa hóa đơn thành công!");
                }
            }
            System.out.print("Bạn có muốn tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }

        } while (!luachon);

    }

    private void Xoa() {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                                   XÓA HÓA ĐƠN NHẬP                                                                  ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");

            ds = DocFile();

            System.out.print("Nhập mã hóa đơn nhập:");
            String maHDN = Scan.next();
            boolean kt = false;
            //khai báo 1 list danh sách mới
            List<HoaDonNhap> dsMoi = new ArrayList<HoaDonNhap>();
            List<CTHDN> dsctXoa = new ArrayList<CTHDN>();
            dsct = DocFileChiTiet();
            for (HoaDonNhap hdn : ds) {
                // mã của 1 phần tự trong mảng trùng với mã cần xóa
                if (hdn.getMaHDN().contains(maHDN)) {
                    HienThiDanhSach(ds);
                    hdn.setMaHDN("");
                    kt = true;

                }
                dsMoi.add(hdn);

                for (CTHDN cthd : dsct) {
                    if (!cthd.getHDN().contains(maHDN)) {
                        dsctXoa.add(cthd);
                    }
                }
            }
            if (!kt) {
                System.out.println("Không tìm thấy mã hóa đơn nhập cần xóa!");
            } else {
                System.out.print("Bạn có muốm xóa hóa đơn nhập này không? (y/n):");
                chonThoat = Scan.next();
                if (chonThoat.equalsIgnoreCase("y")) {
                    //tiến hành ghi file
                    GhiFile(dsMoi);
                    GhiFileChiTiet(dsctXoa);
                    System.out.println("Xóa hóa đơn nhập thành công!");
                }
            }
            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }
        } while (!luachon);
    }
    private void TimTheoMa() {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                                    TÌM THEO MÃ HÓA ĐƠN NHẬP                                                              ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");

            ds = DocFile();
            dsct = DocFileChiTiet();

            System.out.print("Nhập mã hóa đơn nhập:");
            String maHDN = Scan.next();
            boolean kt = false;
            for (HoaDonNhap hd : ds) {
                if (hd.getMaHDN().contains(maHDN)) {
                    HienThiDanhSach(ds);
                    kt = true;
                }
            }
            if (!kt) {
                System.out.println("Không tìm thấy hóa đơn nhập cần tìm!");
            }
            System.out.print("Bạn có muốn tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }

        } while (!luachon);
    }

    private void TimTheoTenNCC() {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                              TÌM THEO TÊN NHÀ CUNG CẤP                                                                   ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();

            System.out.print("Nhập tên nhà cung cấp:");
            String tenNCC = Scan.next();
            boolean kt = false;
            List<HoaDonNhap> dsTimKiem = new ArrayList<HoaDonNhap>();

            for (HoaDonNhap hd : ds) {

                if (hd.getTenNCC().contains(tenNCC)) {
                    dsTimKiem.add(hd);

                    kt = true;
                }

            }
            if (!kt) {
                System.out.println("Không tìm thấy tên nhà cung cấp cần tìm!");
            } else {
                System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
                HienThiDanhSach(dsTimKiem);
                System.out.println("╚═════════════════════════════════════════════════════════════════════════╝");
            }
            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }
        } while (!luachon);
    }

    private List<HoaDonNhap> DocFile() {
        List<HoaDonNhap> ds = new ArrayList<HoaDonNhap>();
        String fileName = "HoaDonNhap.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (!"".equals(line)) {
                    String[] array = line.split(Pattern.quote("|"));
                    HoaDonNhap mh;
                    try {
                        mh = new HoaDonNhap(array[0], array[1], array[2], nt.parse(array[3] + ""));
                        ds.add((HoaDonNhap) mh);
                    } catch (ParseException ex) {
                        Logger.getLogger(QLHoaDonNhap.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException ex) {
        }
        return ds;
    }

    private void GhiFile(List<HoaDonNhap> ds) {
        try {
            Formatter f = new Formatter("HoaDonNhap.txt");
            for (HoaDonNhap mh : ds) {
                //kiểm tra nếu mã khác rỗng thì ghi dữ liệu vào tệp còn = rỗng thì bỏ qua ko lưu => xóa hóa đơn đó khỏi cơ sở dữ liệu
                if (mh.getMaHDN() != "") {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String strDate = dateFormat.format(mh.getNgaynhap());
                    String content = mh.getMaHDN() + "|" + mh.getMaNV() + "|" + mh.getTenNCC() + "|" + strDate;
                    f.format(content + "\r\n", null);
                }
            }
            f.close();
        } catch (FileNotFoundException e) {
        }
    }

   private List<CTHDN> DocFileChiTiet() {
        List<CTHDN> ds = new ArrayList<CTHDN>();
        String fileName = "CTHDN.txt";
        
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (!"".equals(line)) {
                    String[] array = line.split(Pattern.quote("|"));
                    CTHDN mh = new CTHDN(array[0], array[1], array[2], Integer.parseInt(array[3]), array[4]);
                    ds.add((CTHDN) mh);
                }
            }
            bufferedReader.close();
        } catch (IOException ex) {
        }
        return ds;
    }

    private void GhiFileChiTiet(List<CTHDN> ds) {
        try {
            Formatter f = new Formatter("CTHDN.txt");
            for (CTHDN mh : ds) {
                if (mh.getMaCTHD() != "") {
                    String content = mh.getMaCTHD() + "|" + mh.getHDN() + "|" + mh.getMaMH() + "|" + mh.getSoluong() + "|" + mh.getDonvitinh();
                    //dòng này đang gây lỗi buil cẩn thận
                    f.format(content + "\r\n", null);
                }
            }
            f.close();
        } catch (FileNotFoundException e) {
        }
    }
}
