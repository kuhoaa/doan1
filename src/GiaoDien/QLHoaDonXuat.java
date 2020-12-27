/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import DoiTuong.CTHDX;
import DoiTuong.HoaDonXuat;
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
public class QLHoaDonXuat {

    static int chucNang = 0;
    static boolean thoat = false;
    static boolean luachon = false;
    static String chonThoat = "";
    static Scanner Scan = new Scanner(System.in);
    static List<HoaDonXuat> ds = new ArrayList<HoaDonXuat>();
    static List<CTHDX> dsct = new ArrayList<CTHDX>();
    private SimpleDateFormat nt = new SimpleDateFormat("dd/MM/yyyy");
    HienThi hienThi = new HienThi();

    public void KhoiTao() {
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            ds = DocFile();
            dsct=DocFileChiTiet();
            HienThiDanhSach(ds);
            System.out.println("╟──────────────────────────────────────────────────────────────────────────────╢");
            System.out.println("║1. Thêm, 2. Sửa, 3. Xóa, 4. Tìm theo mã, 5. Tìm theo tên khách hàng, 6. Menu chính, 7. Thoát                                   ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
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
                    TimTheoTenKH();
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
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                            CHỨC NĂNG QUẢN LÝ HÓA ĐƠN XUẤT                                                     ║");
    }

    private void HienThiDanhSach(List<HoaDonXuat> hdx) {
        System.out.println("║                                                 DANH SÁCH HÓA ĐƠN XUẤT                                                        ║");
        System.out.println("╟──────────────────────────────────────────────────────────────────────────────╢");
        for (HoaDonXuat hd : hdx) {
            hd.hienthi();
            System.out.println("╟──────────────────────────────────────────────────────────────────────────────╢");
            System.out.println("║     MÃ CT         |    MÃ HÓA ĐƠN   |    MÃ MẶT HÀNG   |   SỐ LƯỢNG   |      GIÁ BÁN    |    SIZE   |    TỔNG TIỀN   | ║");
            for (CTHDX cthdx : dsct) {
                if(cthdx.getMaHDX().contains(hd.getMaHDX())){
                    System.out.println(cthdx.ToString2());
                    
                }   
            }
        }
    }
    private void Them() {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                                  THÊM HÓA ĐƠN XUẤT                                                                        ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();
            HoaDonXuat hdx = new HoaDonXuat();
            hdx.Them(ds);

            dsct = DocFileChiTiet();
            List<CTHDX> dsctThem = new ArrayList<CTHDX>();

            boolean nhd = false;
            do {
                System.out.println("Thêm chi tiết hóa đơn " + (dsctThem.size() + 1));
                CTHDX cthdx = new CTHDX();
                cthdx.Them(dsct);
                //gắn mã hóa đơn xuất khi đã nhập ở trên vào chi tiết hóa đơn xuất
                cthdx.setMaHDX(hdx.getMaHDX());
                dsctThem.add(cthdx);
                System.out.print("Bạn có muốm thêm chi tiết hóa đơn nữa không? (y/n):");
                chonThoat = Scan.next();
                if (chonThoat.equalsIgnoreCase("n")) {
                    nhd = true;
                }
            } while (!nhd);

            System.out.print("Bạn có muốm lưu hóa đơn xuất này không? (y/n):");
            chonThoat = Scan.next();
            if (chonThoat.equalsIgnoreCase("y")) {
                ds.add(hdx);
                GhiFile(ds);
                for (int i = 0; i < dsctThem.size(); i++) {
                    CTHDX cth = dsctThem.get(i);
                    cth.setMaHDX(hdx.getMaHDX());
                    dsct.add(cth);
                    QLMatHang qLMatHang = new QLMatHang();
                    qLMatHang.GiamSoLuong(cth.getMaMH(), cth.getSoluong());
                }
                GhiFileChiTiet(dsct);

                System.out.println("Thêm hóa đơn xuất thành công thành công!");
            }

            System.out.print("Bạn có muốn tiếp tục chức năng này không? (y/n):");
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
            System.out.println("║                                                    SỬA HÓA ĐƠN XUẤT                                                         ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();
            System.out.print("Nhập mã hóa đơn xuất:");
            String maHDX = Scan.next();
            boolean kt = false;
            List<HoaDonXuat> dsMoi = new ArrayList<HoaDonXuat>();
            dsct = DocFileChiTiet();
            for (HoaDonXuat hdx : ds) {
                if (hdx.getMaHDX().contains(maHDX)) {
                    System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
                    HienThiDanhSach(ds);
                    System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
                    System.out.println("Nhập các thông tin thay đổi!");
                    kt = true;
                    hdx.Sua();
                }
                dsMoi.add(hdx);
            }

            if (!kt) {
                System.out.println("Không tìm thấy mã hóa đơn nhập cần sửa!");
            } else {
                dsct = DocFileChiTiet();
                List<CTHDX> dsctSua = new ArrayList<CTHDX>();
                boolean nhd = false;
                boolean ktct = false;
                do {
                    System.out.print("Bạn có muốn sửa chi tiết hóa đơn không? (y/n):");
                    chonThoat = Scan.next();
                    if (chonThoat.equalsIgnoreCase("n")) {
                        nhd = true;
                    } else {
                        System.out.print("Nhập mã chi tiết hóa đơn nhập:");
                        String maCTHDX = Scan.next();

                        for (CTHDX cthd : dsct) {
                            if (cthd.getMaCTHDX().contains(maCTHDX)) {
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
            System.out.println("║                                               XÓA HÓA ĐƠN XUẤT                                                              ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");

            ds = DocFile();

            System.out.print("Nhập mã hóa đơn xuất:");
            String maHDX = Scan.next();
            boolean kt = false;
            List<HoaDonXuat> dsMoi = new ArrayList<HoaDonXuat>();
            List<CTHDX> dsctXoa = new ArrayList<CTHDX>();
            dsct = DocFileChiTiet();
            for (HoaDonXuat hdx : ds) {
                if (hdx.getMaHDX().contains(maHDX)) {
                    HienThiDanhSach(ds);
                    hdx.setMaHDX("");
                    kt = true;

                }
                dsMoi.add(hdx);

                for (CTHDX cthd : dsct) {
                    if (!cthd.getMaHDX().contains(maHDX)) {
                        dsctXoa.add(cthd);
                    }
                }
            }
            if (!kt) {
                System.out.println("Không tìm thấy mã hóa đơn nhập cần xóa!");
            } else {
                System.out.print("Bạn có muốn xóa hóa đơn nhập này không? (y/n):");
                chonThoat = Scan.next();
                if (chonThoat.equalsIgnoreCase("y")) {
                    GhiFile(dsMoi);
                    GhiFileChiTiet(dsctXoa);
                    System.out.println("Xóa hóa đơn nhập thành công!");
                }
            }
            System.out.print("Bạn có muốn tiếp tục chức năng này không? (y/n):");
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
            System.out.println("║                                    TÌM THEO MÃ HÓA ĐƠN XUẤT                                                                 ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");

            ds = DocFile();

            System.out.print("Nhập mã hóa đơn xuất:");
            String maHDX = Scan.next();
            boolean kt = false;
            for (HoaDonXuat hd : ds) {
                if (hd.getMaHDX().contains(maHDX)) {
                    HienThiDanhSach(ds);
                    kt = true;
                }
            }
            if (!kt) {
                System.out.println("Không tìm thấy hóa đơn xuất cần tìm!");
            }
            System.out.print("Bạn có muốn tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }

        } while (!luachon);
    }

    private void TimTheoTenKH() {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                                                TÌM THEO TÊN KHÁCH HÀNG                                                        ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();
            System.out.print("Nhập tên khách hàng:");
            String tenKH = Scan.next();
            boolean kt = false;
            List<HoaDonXuat> dsTimKiem = new ArrayList<HoaDonXuat>();
            for (HoaDonXuat hd : ds) {
                if (hd.getTenKH().contains(tenKH)) {
                    dsTimKiem.add(hd);
                    kt = true;
                }
            }
            if (!kt) {
                System.out.println("Không tìm thấy tên khách hàng cần tìm!");
            } else {
                System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
                HienThiDanhSach(dsTimKiem);
                System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
            }
            System.out.print("Bạn có muốn tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }
        } while (!luachon);
    }

    public List<HoaDonXuat> DocFile() {
        List<HoaDonXuat> ds = new ArrayList<HoaDonXuat>();
        String fileName = "HoaDonXuat.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(Pattern.quote("|"));
                HoaDonXuat mh;
                try {
                    mh = new HoaDonXuat(array[0], array[1], array[2], nt.parse(array[3] + " "));
                    ds.add((HoaDonXuat) mh);
                } catch (ParseException ex) {
                    Logger.getLogger(QLHoaDonXuat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            bufferedReader.close();
        } catch (IOException ex) {
        }
        return ds;
    }

    private void GhiFile(List<HoaDonXuat> ds) {
        try {
            Formatter f = new Formatter("HoaDonXuat.txt");
            for (HoaDonXuat mh : ds) {
                if (mh.getMaHDX() != "") {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String strDate = dateFormat.format(mh.getNgayxuat());
                    String content = mh.getMaHDX() + "|" + mh.getMaNV() + "|" + mh.getTenKH() + "|" + strDate;
                    f.format(content + "\r\n", null);
                }
            }
            f.close();
        } catch (FileNotFoundException e) {
        }
    }

    public List<CTHDX> DocFileChiTiet() {
        List<CTHDX> ds = new ArrayList<CTHDX>();
        String fileName = "CTHDX.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(Pattern.quote("|"));
                CTHDX mh = new CTHDX(array[0], array[1], array[2], Integer.parseInt(array[3]), Integer.parseInt(array[4]), array[5], Integer.parseInt(array[6]));
                ds.add((CTHDX) mh);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return ds;
    }

    private void GhiFileChiTiet(List<CTHDX> ds) {
        try {
            Formatter f = new Formatter("CTHDX.txt");
            for (CTHDX mh : ds) {
                if (mh.getMaCTHDX() != "") {
                    String content = mh.getMaCTHDX() + "|" + mh.getMaHDX() + "|" + mh.getMaMH() + "|" + mh.getSoluong() + "|" + mh.getGiaban() + "|" + mh.getDonvitinh() + "|" + mh.getTt();
                    f.format(content + "\r\n", null);
                }
            }
            f.close();
        } catch (FileNotFoundException e) {
        }
    }

}
