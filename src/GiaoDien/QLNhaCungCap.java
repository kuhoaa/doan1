/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import DoiTuong.NhaCungCap;
import DoiTuong.Xulingoaile;
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
public class QLNhaCungCap {

    static int chucNang = 0;
    static boolean thoat = false;
    static boolean luachon = false;
    static String chonThoat = "";
    static Scanner Scan = new Scanner(System.in);
    static List<NhaCungCap> ds = new ArrayList<NhaCungCap>();
    HienThi hienThi = new HienThi();

    public void KhoiTao() {
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            ds = new ArrayList<NhaCungCap>();
            ds = DocFile();

            HienThiDanhSach(ds);
            System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
            System.out.println("║1. Thêm, 2. Sửa, 3. Xóa, 4. Tìm 5. Menu chính, 6. Thoát                                                                                ║");
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
                    do {
                        chucNang=Menu6();
                        switch (chucNang)
                        {
                            case 1:TimTheoMa();
                            break;
                            case 2:TimTheoTen();
                            break;
                        }
                    }while (chucNang>=1&&chucNang<=2);
                    break;

                case 5:
                    thoat = true;
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        } while (!thoat);
    }

    private void TieuDeChucNang() {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           CHỨC NĂNG QUẢN LÝ NHÀ CUNG CẤP                                                                              ║");
    }

    private void HienThiDanhSach(List<NhaCungCap> dsncc) {
        System.out.println("║                                DANH SÁCH NHÀ CUNG CẤP                                                                                 ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║ MÃ NCC |            TÊN NCC            |    SỐ ĐIỆN THOẠI   |        ĐỊA CHỈ    |                                                     ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");

        for (NhaCungCap ncc : dsncc) {
            System.out.println(ncc.ToString());
        }
    }

    private void HienThiChiTiet(NhaCungCap ncc) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                               CHI TIẾT NHÀ CUNG CẤP                                                                                   ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║ MÃ NCC |          TÊN NCC          |    SỐ ĐIỆN THOẠI   |        ĐỊA CHỈ     |                                                        ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println(ncc.ToString());
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    private void Them() {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                                  THÊM NHÀ CUNG CẤP                                                                                 ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();
            NhaCungCap ncc = new NhaCungCap();
            ncc.Them(ds);
            System.out.print("Bạn có muốm lưu nhà cung cấp này không? (y/n):");
            chonThoat = Scan.next();
            if (chonThoat.equalsIgnoreCase("y")) {
                ds.add(ncc);
                GhiFile(ds);
                System.out.println("Thêm nhà cung cấp thành công!");
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
            System.out.println("║                                   SỬA NHÀ CUNG CẤP                                                                                    ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();
            System.out.print("Nhập mã nhà cung cấp:");
            String mancc = Scan.next();
            boolean kt = false;
            List<NhaCungCap> dsMoi = new ArrayList<NhaCungCap>();
            for (NhaCungCap ncc : ds) {
                if (ncc.getMancc().contains(mancc)) {
                    HienThiChiTiet(ncc);
                    System.out.println("Nhập các thông tin thay đổi!");
                    kt = true;
                    ncc.Sua();
                }
                dsMoi.add(ncc);
            }
            if (!kt) {
                System.out.println("Không tìm thấy nhà cung cấp cần sửa!");
            } else {
                System.out.print("Bạn có muốm thay đổi thông tin nhà cung cấp này không? (y/n):");
                chonThoat = Scan.next();
                if (chonThoat.equalsIgnoreCase("y")) {
                    GhiFile(dsMoi);
                    System.out.println("Sửa nhà cung cấp thành công!");
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
            System.out.println("║                                   XÓA NHÀ CUNG CẤP                                                                                    ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");

            ds = DocFile();

            System.out.print("Nhập mã nhà cung cấp:");
            String mancc = Scan.next();
            boolean kt = false;
            List<NhaCungCap> dsMoi = new ArrayList<NhaCungCap>();
            for (NhaCungCap ncc : ds) {
                if (ncc.getMancc().contains(mancc)) {
                    HienThiChiTiet(ncc);
                    ncc.setMancc("");
                    kt = true;
                }
                dsMoi.add(ncc);
            }
            if (!kt) {
                System.out.println("Không tìm thấy nhà cung cấp cần xóa!");
            } else {
                System.out.print("Bạn có muốm xóa nhà cung cấp này không? (y/n):");
                chonThoat = Scan.next();
                if (chonThoat.equalsIgnoreCase("y")) {
                    GhiFile(dsMoi);
                    System.out.println("Xóa nhà cung cấp thành công!");
                }
            }
            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }
        } while (!luachon);
    }

    public NhaCungCap TimNV_Ma(String Ma) {
        ds = DocFile();
        for (NhaCungCap ncc : ds) {
            if (ncc.getMancc().contains(Ma)) {
                return ncc;
            }
        }
        return null;
    }

    public NhaCungCap TimNV_Ten(String ten) {
        ds = DocFile();
        for (NhaCungCap ncc : ds) {
            if (ncc.getTenncc().toLowerCase().contains(ten.toLowerCase())) {
                return ncc;

            }
        }
        return null;
    }
    private   static  int Menu6(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ----------------------------------------------");
        System.out.println("|            1. Tìm theo tên                   |");
        System.out.println("|            2. Tìm theo mã                    |");
        System.out.println("|            3. Quay lại                       |");
        System.out.println(" ----------------------------------------------");
        String epkieu;
        do {
            System.out.print("Vui lòng chọn (1-3) : ");
            epkieu=scanner.nextLine();

        }while(Xulingoaile.Kiemtra(epkieu)==false||Integer.parseInt(epkieu) < 0 || Integer.parseInt(epkieu) >3);
        return Integer.parseInt(epkieu);
    }
    private void TimTheoMa() {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                              TÌM THEO MÃ NHÀ CUNG CẤP                                                                                 ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            System.out.print("Nhập mã nhà cung cấp:");
            String mancc = Scan.next();
            NhaCungCap ncc = TimNV_Ma(mancc);
            if (ncc != null) {
                HienThiChiTiet(ncc);
            } 
            else {
                System.out.println("Không tìm thấy nhà cung cấp cần tìm!");
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
            System.out.println("║                              TÌM THEO TÊN NHÀ CUNG CẤP                                                                                ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            System.out.print("Nhập tên nhà cung cấp:");
            String tenncc = Scan.next();
            boolean kt = false;
            List<NhaCungCap> dsTimKiem = new ArrayList<NhaCungCap>();
            NhaCungCap ncc = TimNV_Ten(tenncc);
            if (ncc != null) {
                dsTimKiem.add(ncc);
                kt = true;
            }
            if (ncc==null) {
                System.out.println("Không tìm thấy nhà cung cấp cần tìm!");
            } else {
                System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
                HienThiDanhSach(dsTimKiem);
                System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            }

            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }

        } while (!luachon);
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

    private void GhiFile(List<NhaCungCap> ds) {
        try {
            Formatter f = new Formatter("NhaCungCap.txt");
            for (NhaCungCap ncc : ds) {
                if (ncc.getMancc() != "") {
                    String content = ncc.getMancc() + "|" + ncc.getTenncc() + "|" + ncc.getSoDienThoai() + "|" + ncc.getDiaChi();
                    f.format(content + "\r\n", null);
                }
            }
            f.close();
        } catch (FileNotFoundException e) {
        }
    }
}
