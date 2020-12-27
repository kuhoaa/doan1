/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import DoiTuong.NhanVien;
import HoTro.HienThi;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author LENOVO
 */
public class QLNhanVien {

    static int chucNang = 0;
    static boolean thoat = false;
    static boolean luachon = false;
    static String chonThoat = "";
    static Scanner Scan = new Scanner(System.in);
    static List<NhanVien> ds = new ArrayList<NhanVien>();
    HienThi hienThi = new HienThi();

    public void KhoiTao() {
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            ds = new ArrayList<NhanVien>();
            ds = DocFile();
            HienThiDanhSach(ds);
            System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
            System.out.println("║1. Thêm, 2. Sửa, 3. Xóa, 4. Tìm theo mã, 5. Tìm theo tên, 6. Menu chính, 7. Thoát                                                      ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
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
                    TimTheoTen();
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
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           CHỨC NĂNG QUẢN LÝ NHÂN VIÊN                                                                                 ║");

    }

    private void HienThiDanhSach(List<NhanVien> dsnv) {
        System.out.println("║                                DANH SÁCH NHÂN VIÊN                                                                                    ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║ MÃ NV|            TÊN NV           |    GIỚI TÍNH   |        SỐ ĐIỆN THOẠI      |       ĐỊA CHỈ     |                                 ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");

        for (NhanVien nv : dsnv) {
            System.out.println(nv.ToString());
        }
    }

    private void HienThiChiTiet(NhanVien nv) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                               CHI TIẾT NHÂN VIÊN                                                                                      ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║ MÃ NV |            TÊN NV            |    GIỚI TÍNH   |        SỐ ĐIỆN THOẠI   |      ĐỊA CHỈ     |                                   ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println(nv.ToString());
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    private void Them() {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                                  THÊM NHÂN VIÊN                                                                                    ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();
            NhanVien nv = new NhanVien();
            nv.Them(ds);
            System.out.print("Bạn có muốm lưu nhân viên này không? (y/n):");
            chonThoat = Scan.next();
            if (chonThoat.equalsIgnoreCase("y")) {
                ds.add(nv);
                GhiFile(ds);
                System.out.println("Thêm nhân viên thành công!");
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
            System.out.println("║                                   SỬA NHÂN VIÊN                                                                                   ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();
            System.out.print("Nhập mã nhân viên:");
            String maNhanVien = Scan.next();
            boolean kt = false;
            List<NhanVien> dsMoi = new ArrayList<NhanVien>();
            for (NhanVien nv : ds) {
                if (nv.getMaNhanVien().contains(maNhanVien)) {
                    HienThiChiTiet(nv);
                    System.out.println("Nhập các thông tin thay đổi!");
                    kt = true;
                    nv.Sua();
                }
                dsMoi.add(nv);
            }
            if (!kt) {
                System.out.println("Không tìm thấy nhân viên cần sửa!");
            } else {
                System.out.print("Bạn có muốm thay đổi thông tin nhân viên này không? (y/n):");
                chonThoat = Scan.next();
                if (chonThoat.equalsIgnoreCase("y")) {
                    GhiFile(dsMoi);
                    System.out.println("Sửa nhân viên thành công!");
                }
            }
            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
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
            System.out.println("║                                   XÓA NHÂN VIÊN                                                                                    ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");

            ds = DocFile();

            System.out.print("Nhập mã nhân viên:");
            String maNhanVien = Scan.next();
            boolean kt = false;
            List<NhanVien> dsMoi = new ArrayList<NhanVien>();
            for (NhanVien nv : ds) {
                if (nv.getMaNhanVien().contains(maNhanVien)) {
                    HienThiChiTiet(nv);
                    nv.setMaNhanVien("");
                    kt = true;
                }
                dsMoi.add(nv);
            }
            if (!kt) {
                System.out.println("Không tìm thấy nhân viên cần xóa!");
            } else {
                System.out.print("Bạn có muốm xóa nhân viên này không? (y/n):");
                chonThoat = Scan.next();
                if (chonThoat.equalsIgnoreCase("y")) {
                    GhiFile(dsMoi);
                    System.out.println("Xóa nhân viên thành công!");
                }
            }
            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }
        } while (!luachon);
    }

    public NhanVien TimNV_Ma(String Ma) {
        ds = DocFile();
        boolean kt = false;
        for (NhanVien nv : ds) {
            if (nv.getMaNhanVien().contains(Ma)) {
                return nv;
            }
        }
        return null;
    }

    private void TimTheoMa() {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                              TÌM THEO MÃ NHÂN VIÊN                                                                                 ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");

            System.out.print("Nhập mã nhân viên:");
            String maNhanVien = Scan.next();
            NhanVien nv = TimNV_Ma(maNhanVien);
            if (nv != null) {
                HienThiChiTiet(nv);
            } else {
                System.out.println("Không tìm thấy nhân viên cần tìm!");
            }
            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }

        } while (!luachon);
    }

    private void TimTheoTen() {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                              TÌM THEO TÊN NHÂN VIÊN                                                                                ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");

            ds = DocFile();

            System.out.print("Nhập tên nhân viên:");
            String tenNhanVien = Scan.next();
            boolean kt = false;
            List<NhanVien> dsTimKiem = new ArrayList<NhanVien>();
            for (NhanVien nv : ds) {
                if (nv.getTenNhanVien().contains(tenNhanVien)) {
                    dsTimKiem.add(nv);
                    kt = true;
                }
            }
            if (!kt) {
                System.out.println("Không tìm thấy nhân viên cần tìm!");
            } else {
                System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
                HienThiDanhSach(dsTimKiem);
                System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            }

            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }

        } while (!luachon);
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

    private void GhiFile(List<NhanVien> ds) {
        try {
            Formatter f = new Formatter("NhanVien.txt");
            for (NhanVien nv : ds) {
                if (nv.getMaNhanVien() != "") {
                    String content = nv.getMaNhanVien() + "|" + nv.getTenNhanVien() + "|" + nv.getGioitinh() + "|" + nv.getSoDienThoai() + "|" + nv.getDiaChi();
                    f.format(content + "\r\n", null);
                }
            }
            f.close();
        } catch (FileNotFoundException e) {
        }
    }

}
