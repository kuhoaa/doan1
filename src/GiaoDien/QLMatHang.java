/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import DoiTuong.CTHDX;
import DoiTuong.MatHang;
import DoiTuong.Xulingoaile;
import HoTro.HienThi;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.*;
import java.util.regex.Pattern;

/**
 *
 * @author LENOVO
 */
public class QLMatHang {

    static int chucNang = 0;
    static boolean thoat = false;
    static boolean luachon = false;
    static String chonThoat = "";
    static Scanner Scan = new Scanner(System.in);
    static List<MatHang> ds = new ArrayList<MatHang>();
    HienThi hienThi = new HienThi();

    public  static  int Menu1(){
        Scanner sc = new Scanner(System.in);
        System.out.println(" ----------------------------------------------");
        System.out.println("|            1. Tìm theo tên                   |");
        System.out.println("|            2. Tìm theo mã                    |");
        System.out.println("|            3. Tìm theo loại                  |");
        System.out.println("|            4. Tìm theo SIZE                  |");
        System.out.println("|            0. Quay lại                       |");
        System.out.println(" ----------------------------------------------");
        String epkieu;
        do {
            System.out.print("Vui lòng chọn (1-5) : ");
            epkieu=sc.nextLine();

        }while(Xulingoaile.Kiemtra(epkieu)==false||Integer.parseInt(epkieu) < 0 || Integer.parseInt(epkieu) >5);
        return Integer.parseInt(epkieu);
    }

    public void KhoiTao() {
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
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
                        chucNang = Menu1();
                        switch (chucNang) {
                            case 1:
                                TimTheoTen();
                                break;
                            case 2:
                                TimTheoMa();
                                break;
                            case 3:

                                System.out.print("Nhập loại mặt hàng(nữ/nam):");
                              String  loaimathang = Scan.next();
                                TimTheoLoai(loaimathang);
                                break;
                            case 4:
                                TimTheoSize();
                                break;

                        }
                    } while (chucNang >= 1 && chucNang <= 5);
                    break;




//                case 5:
//                    TimTheoTen();
//                    break;
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
        System.out.println("║                                       CHỨC NĂNG QUẢN LÝ MẶT HÀNG                                                                      ║");
    }

    private void HienThiDanhSach(List<MatHang> dsmh) {
        System.out.println("║                                          DANH SÁCH MẶT HÀNG                                                                           ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║ MÃ MH |        TÊN MH       |           Loại giầy          | GIÁ NHẬP(VND)|   SỐ LƯỢNG  |  SIZE       |Tổng tiền(VND)                 ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        for (MatHang mh : dsmh) {
            System.out.println(mh.ToString());
        }
    }

    private void HienThiChiTiet(MatHang mh) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                               CHI TIẾT MẶT HÀNG                                                                                       ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║ MÃ MH |        TÊN MH       |   Loại giày   |  GIÁ Nhập(VND) |  SỐ LƯỢNG  |         Size  |            |Tổng tiền(VND)                ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println(mh.ToString());
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    private void Them() {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                                             THÊM MẶT HÀNG                                                                        ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();
            MatHang mh = new MatHang();
            mh.Them(ds);
            System.out.print("Bạn có muốm lưu mặt hàng này không? (y/n):");
            chonThoat = Scan.next();
            if (chonThoat.equalsIgnoreCase("y")) {
                ds.add(mh);
                GhiFile(ds);
                System.out.println("Thêm mặt hàng thành công!");
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
            System.out.println("║                                   SỬA MẶT HÀNG                                                                                        ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();
            System.out.print("Nhập mã mặt hàng:");
            String maMatHang = Scan.next();
            boolean kt = false;
            List<MatHang> dsMoi = new ArrayList<MatHang>();
            for (MatHang mh : ds) {
                if (mh.getMaMatHang().contains(maMatHang)) {
                    HienThiChiTiet(mh);
                    System.out.println("Nhập các thông tin thay đổi!");
                    kt = true;
                    mh.Sua();
                }
                dsMoi.add(mh);
            }
            if (!kt) {
                System.out.println("Không tìm thấy mặt hàng cần sửa!");
            } else {
                System.out.print("Bạn có muốm thay đổi thông tin mặt hàng này không? (y/n):");
                chonThoat = Scan.next();
                if (chonThoat.equalsIgnoreCase("y")) {
                    GhiFile(dsMoi);
                    System.out.println("Sửa mặt hàng thành công!");
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
            System.out.println("║                                   XÓA MẶT HÀNG                                                                                        ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();
            System.out.print("Nhập mã mặt hàng:");
            String maMatHang = Scan.next();
            boolean kt = false;
            List<MatHang> dsMoi = new ArrayList<MatHang>();
            for (MatHang mh : ds) {
                if (mh.getMaMatHang().contains(maMatHang)) {
                    HienThiChiTiet(mh);
                    mh.setMaMatHang("");
                    //ds.remove(mh);
                    kt = true;
                }
                dsMoi.add(mh);
            }
            if (!kt) {
                System.out.println("Không tìm thấy mặt hàng cần xóa!");
            } else {
                System.out.print("Bạn có muốm xóa mặt hàng này không? (y/n):");
                chonThoat = Scan.next();
                if (chonThoat.equalsIgnoreCase("y")) {
                    GhiFile(dsMoi);
                    System.out.println("Xóa mặt hàng thành công!");
                }
            }
            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }
        } while (!luachon);
    }

    public MatHang TimMH_Ma(String ma) {
        ds = DocFile();
        for (MatHang mh : ds) {
            if (mh.getMaMatHang().contains(ma)) {
                return mh;
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
            System.out.println("║                                          TÌM THEO MÃ MẶT HÀNG                                                                    ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            System.out.print("Nhập mã mặt hàng:");
            String maMatHang = Scan.next();
            boolean kt = false;
            MatHang mh = TimMH_Ma(maMatHang);
            if (mh != null) {
                HienThiChiTiet(mh);
            } else {
                System.out.println("không tìm thấy mặt hàng cần tìm");
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
            System.out.println("║                              TÌM THEO TÊN MẶT HÀNG                                                                               ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");

            ds = DocFile();

            System.out.print("Nhập tên mặt hàng:");
            String tenMatHang = Scan.next();
            boolean kt = false;
            List<MatHang> dsTimKiem = new ArrayList<MatHang>();
            for (MatHang mh : ds) {
                if (mh.getTenMatHang().toLowerCase().contains(tenMatHang.toLowerCase())) {
                    dsTimKiem.add(mh);
                    kt = true;
                }
            }
            if (!kt) {
                System.out.println("Không tìm thấy mặt hàng cần tìm!");
            } else {
                System.out.println("╔═════════════════════════════════════════════════════════════════════════════╗");
                HienThiDanhSach(dsTimKiem);
                System.out.println("╚═════════════════════════════════════════════════════════════════════════════╝");
            }

            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }

        } while (!luachon);
    }
    private void TimTheoLoai( String loaimathang) {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                              TÌM THEO Loại MẶT HÀNG(NỮ/NAM)                                                                               ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");

            ds = DocFile();


            boolean kt = false;
            List<MatHang> dsTimKiem = new ArrayList<MatHang>();
            for (MatHang mh : ds) {
                if (mh.getCongdung().contains(loaimathang)) {
                    dsTimKiem.add(mh);
                    kt = true;
                }
            }
            if (!kt) {
                System.out.println("Không tìm thấy mặt hàng cần tìm!");
            } else {
                System.out.println("╔═════════════════════════════════════════════════════════════════════════════╗");
                HienThiDanhSach(dsTimKiem);
                System.out.println("╚═════════════════════════════════════════════════════════════════════════════╝");
            }

            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }

        } while (!luachon);
    }
    private void TimTheoSize() {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                              TÌM THEO SIZE Giầy                                                                                       ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");

            ds = DocFile();

            System.out.print("Nhập Size cần tìm:");
            String size = Scan.next();
            boolean kt = false;
            List<MatHang> dsTimKiem = new ArrayList<MatHang>();
            for (MatHang mh : ds) {
                if(size.equals(mh.getDonvitinh())) {
                    dsTimKiem.add(mh);
                    kt = true;
                }
            }
            if (!kt) {
                System.out.println("Không tìm thấy mặt hàng cần tìm!");
            } else {
                System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
                HienThiDanhSach(dsTimKiem);
                System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            }

            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }

        } while (!luachon);
    }
    public void TangSoLuong(String maMatHang, int soLuong) {
        ds = DocFile();
        List<MatHang> dsMoi = new ArrayList<MatHang>();
        for (MatHang mh : ds) {
            if (mh.getMaMatHang().contains(maMatHang)) {
                mh.setSoluong(mh.getSoluong() + soLuong);
            }
            dsMoi.add(mh);
        }
        GhiFile(dsMoi);
    }

    public void SuaSoLuong(String maMatHang, int soLuongCu, int soLuongMoi) {
        ds = DocFile();
        List<MatHang> dsMoi = new ArrayList<MatHang>();
        for (MatHang mh : ds) {
            if (mh.getMaMatHang().contains(maMatHang)) {
                mh.setSoluong(mh.getSoluong() - soLuongCu + soLuongMoi);
            }
            dsMoi.add(mh);
        }
        GhiFile(dsMoi);
    }

    public void GiamSoLuong(String maMatHang, int soLuongGiam) {
        ds = DocFile();
        List<MatHang> dsMoi = new ArrayList<MatHang>();
        for (MatHang mh : ds) {
            if (mh.getMaMatHang().contains(maMatHang)) {
                mh.setSoluong(mh.getSoluong() - soLuongGiam);
            }
            dsMoi.add(mh);
        }
        GhiFile(dsMoi);
    }

    public int SoLuongCon(String maMatHang) {
        int soLuong = 0;
        ds = DocFile();
        for (MatHang mh : ds) {
            if (mh.getMaMatHang().contains(maMatHang)) {
                soLuong = mh.getSoluong();
            }
        }
        return soLuong;
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

    public void GhiFile(List<MatHang> ds) {
        try {
            Formatter f = new Formatter("MatHang.txt");
            for (MatHang mh : ds) {
                if (mh.getMaMatHang() != "") {
                    String content = mh.getMaMatHang() + "|" + mh.getTenMatHang() + "|" + mh.getCongdung() + "|" + mh.getGiaban() + "|" + mh.getSoluong() + "|" + mh.getDonvitinh()+ "|" + mh.getTt();
                    f.format(content + "\r\n", null);
                }
            }
            f.close();
        } catch (FileNotFoundException e) {
        }
    }
    public List<MatHang> DocFileChiTiet() {
        List<MatHang> ds = new ArrayList<MatHang>();
        String fileName = "MatHang.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(Pattern.quote("|"));
                MatHang mh = new MatHang(array[0], array[1], array[2], Integer.parseInt(array[3]), Integer.parseInt(array[4]), array[5], Integer.parseInt(array[6]));
                ds.add((MatHang) mh);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return ds;
    }
}
