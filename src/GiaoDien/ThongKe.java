/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import DoiTuong.CTHDX;
import DoiTuong.HoaDonXuat;
import DoiTuong.MatHang;
import HoTro.HienThi;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author LENOVO
 */
public class ThongKe {

    static String chucNang = "";
    static boolean thoat = false;
    static boolean luachon = false;
    static String chonThoat = "";
    Scanner scan = new Scanner(System.in);
    SimpleDateFormat nt = new SimpleDateFormat("dd/MM/yyyy");
    HienThi hienThi = new HienThi();

    public void KhoiTao() {
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("╟───────────────────────────────────────────────────────────────────────────────────╢");
            System.out.println("║1. thống kê doanh thu theo ngày, 2. thống kê mặt hàng bán chạy, 3. Menu chính, 4. Thoát                                                ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("Chọn chức năng: ");
            chucNang = scan.nextLine();
            switch (chucNang) {
                case "1":
                    thongkedoanhthu();
                    break;
                case "2":
                    thongkeMHBC();
                    scan.nextLine();
                    break;
                case "3":
                    thoat = true;
                    break;
                case "4":
                    System.exit(0);
                    break;
            }
        } while (!thoat);
    }

    private void TieuDeChucNang() {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                             CHỨC NĂNG THỐNG KÊ                                                                        ║");

    }

    public void thongkedoanhthu() {
        int s = 0;
        Date x = null;
        QLHoaDonXuat qLHoaDonXuat = new QLHoaDonXuat();
        List<HoaDonXuat> listHD = qLHoaDonXuat.DocFile();
        List<CTHDX> listCT = qLHoaDonXuat.DocFileChiTiet();
        boolean kt = true;
        do {
            try {

                System.out.print("nhập ngày cần thống kê:");
                String ngayTK = scan.next();
                scan.nextLine();
                x = new SimpleDateFormat("dd/MM/yyyy").parse(ngayTK);
                Date dateNow = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
                if (dateNow.before(x)) {
                    System.out.println("Lỗi: Ngày nhập không được lớn hơn ngày hiện tại");
                    kt = false;
                }
            } catch (Exception e) {
                System.out.println("Lỗi: Sai định dạng ngày tháng");
                kt = false;
            }
        } while (!(kt));
        for (HoaDonXuat hdx : listHD) {
            if (hdx.getNgayxuat().compareTo(x) == 0) {
                for (CTHDX ct : listCT) {
                    if (ct.getMaHDX().equals(hdx.getMaHDX())) {
                        s = s + ct.getTt();
                    }
                }
            }
        }
        System.out.print("doanh thu ngày là: " + s+" VNĐ");
        scan.nextLine();
    }

    public void thongkeMHBC() {
        QLHoaDonXuat qLHoaDonXuat = new QLHoaDonXuat();
        QLMatHang qLMatHang = new QLMatHang();

        List<CTHDX> listCT = qLHoaDonXuat.DocFileChiTiet();
        List<MatHang> ds = qLMatHang.DocFile();

        String[] listMaMH = new String[ds.size()];
        int[] listSoLuong = new int[ds.size()];
        for (int i = 0; i < ds.size(); i++) {
            listMaMH[i] = ds.get(i).getMaMatHang();
            int soLuong = 0;
            for (CTHDX cthdx : listCT) {
                if (cthdx.getMaMH().equals(ds.get(i).getMaMatHang())) {
                    soLuong = soLuong + cthdx.getSoluong();
                }
            }
            listSoLuong[i] = (soLuong);
        }

        bubbleSort(listSoLuong, listMaMH, ds);
        System.out.print("Có sl mat hang: "+ds.size());
    }
    public void bubbleSort(int[] listSoLuong, String[] listMaMH, List<MatHang> ds) {
        int temp = 0;
        String maMH = "";
        for (int i = 0; i < listSoLuong.length - 1; i++) {
            for (int j = listSoLuong.length - 2; j >= i; j--) {
                if (listSoLuong[j] < listSoLuong[j + 1]) {
                    temp = listSoLuong[j];
                    maMH = listMaMH[j];
                    listSoLuong[j] = listSoLuong[j + 1];
                    listMaMH[j] = listMaMH[j + 1];
                    listSoLuong[j + 1] = temp;
                    listMaMH[j + 1] = maMH;
                }
            }
        }
        for (int i = 0; i < listSoLuong.length ; i++) {
            for (MatHang d : ds) {
                if (listMaMH[i].equals(d.getMaMatHang())) {
                    System.out.println((i + 1) + ". Mặt hàng (" + d.getMaMatHang() + "," + d.getTenMatHang() + ")- Số lượng bán: " + listSoLuong[i]);
                }
            }
        }
    }
}
