/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import DoiTuong.*;
import HoTro.HienThi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author LENOVO
 */
public class ThongKe {


    static boolean thoat = true;
    static boolean luachon = false;
    static String chonThoat = "";
    String epkieu;
    Scanner scan = new Scanner(System.in);
    SimpleDateFormat nt = new SimpleDateFormat("dd/MM/yyyy");
    HienThi hienThi = new HienThi();

    public int Menu1(){
        hienThi.XoaManHinh();
        Scanner sc = new Scanner(System.in);
        System.out.println(" ----------------------------------------------");
        System.out.println("|            1. doanh thu theo ngày            |");
        System.out.println("|            2.  doanh thu                     |");
        System.out.println("|            3. Mặt hàng bán chạy              |");
        System.out.println("|            4. lợi nhuận                      |");
        System.out.println("|            5. Quay lại                       |");
        System.out.println(" ----------------------------------------------");
        do {
            System.out.print("Vui lòng chọn (1-5) : ");
            epkieu=sc.nextLine();

        }while(Xulingoaile.Kiemtra(epkieu)==false||Integer.parseInt(epkieu) < 0 || Integer.parseInt(epkieu) >5);
        return Integer.parseInt(epkieu);
    }
    public int Menu2(){
        hienThi.XoaManHinh();
        Scanner sc = new Scanner(System.in);
        System.out.println(" ----------------------------------------------");
        System.out.println("|            1.Tổng tiền bán                   |");
        System.out.println("|            2.Tổng tiền nhập                  |");
        System.out.println("|            3. Quay lại                       |");
        System.out.println(" ----------------------------------------------");
        do {
            System.out.print("Vui lòng chọn (1-3) : ");
            epkieu=sc.nextLine();

        }while(Xulingoaile.Kiemtra(epkieu)==false||Integer.parseInt(epkieu) < 0 || Integer.parseInt(epkieu) >3);
        return Integer.parseInt(epkieu);
    }

    public void KhoiTao() {
        int chon;

        do {
            chon=Menu1();
            switch (chon) {
                case 1:
                    thongkedoanhthu();
                   System.out.println("ấn phím bất kì để quay lại");
                    break;
                case 2:
                    do {
                        chon=Menu2();
                        switch (chon)
                        {
                            case 1:
                            Tongdoanhthu();
                            scan.nextLine();
                            break;
                            case 2:
                             Tongchi();
                             scan.nextLine();
                             break;

                        }
                    }while (chon>=1&&chon<=2);
                case 3:
                    thongkeMHBC();
                    scan.nextLine();
                    break;
                case 4:
                    Tinhtien();
                    scan.nextLine();
                    break;
            }
        }while (chon>=1&&chon<=4);

    }

    private void TieuDeChucNang() {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                             CHỨC NĂNG THỐNG KÊ                                                                        ║");

    }
    public void Tinhtien()
    {
        int tintien= Tongdoanhthu()-Tongchi();
       if (tintien<0){

            System.out.println("\n\n\n\nSỐ tiền trong cửa hàng hiện tại đang âm , cố bán sản phẩm nhé ^^");
        }

        System.out.println("Doanh số tiền lãi/âm của cửa hàng là: "+tintien+"VND");
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
                kt = true;
                if (dateNow.before(x)) {
                    System.out.println("Lỗi: Ngày nhập không được lớn hơn ngày hiện tại");
                    kt = false;
                }
            } catch (Exception e) {
                System.out.println("Lỗi: Sai định dạng ngày tháng");
                kt = false;
            }
        } while (!(kt));
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║     MÃ CT         |    MÃ HÓA ĐƠN   |    MÃ MẶT HÀNG   |   SỐ LƯỢNG   |      GIÁ BÁN    |    SIZE   |    TỔNG TIỀN   |        ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");

        for (HoaDonXuat hdx : listHD) {
            if (hdx.getNgayxuat().compareTo(x) == 0) {
                for (CTHDX ct : listCT) {
                    if (ct.getMaHDX().equals(hdx.getMaHDX())) {

                        s = s + ct.getTt();
                        System.out.println(ct.ToString2());
                    }
                }
            }
        }
        System.out.println("╟─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");

        System.out.print("doanh thu ngày là: " + s + " VNĐ");
        scan.nextLine();
    }
    public int Tongdoanhthu() {
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║     MÃ CT         |    MÃ HÓA ĐƠN   |    MÃ MẶT HÀNG   |   SỐ LƯỢNG   |      GIÁ BÁN    |    SIZE   |    TỔNG TIỀN   |        ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");

        int s = 0;
        QLHoaDonXuat qLHoaDonXuat = new QLHoaDonXuat();
        List<HoaDonXuat> listHD = qLHoaDonXuat.DocFile();
        List<CTHDX> listCT = qLHoaDonXuat.DocFileChiTiet();
        boolean kt = true;
        {
            for (HoaDonXuat hdx : listHD) {

                for (CTHDX ct : listCT) {
                    if (ct.getMaHDX().equals(hdx.getMaHDX())) {

                        s = s + ct.getTt();
                        System.out.println(ct.ToString2());
                    }
                }
            }
            System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");

            System.out.println("doanh thu tổng là: " + s + " VNĐ");
            System.out.println("ấn phím bất kì để trở lại!");
        }
        return s;
    }

    public int Tongchi() {
        System.out.println("║                                          DANH SÁCH MẶT HÀNG                                                                           ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║ MÃ MH |        TÊN MH       |           Loại giầy          | GIÁ NHẬP(VND)|   SỐ LƯỢNG  |  SIZE       |Tổng tiền(VND)                 ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");

        int s = 0;
        QLMatHang qlMatHang = new QLMatHang();
        List<MatHang> listHD = qlMatHang.DocFile();
        List<MatHang> listCT = qlMatHang.DocFileChiTiet();
        boolean kt = true;
        {
            for (MatHang hdx : listHD) {

                for (MatHang ct : listCT) {
                    if (ct.getMaMatHang().equals(hdx.getMaMatHang())) {

                        s = s + ct.getTt();
                        System.out.println(ct.ToString());
                    }
                }
            }
            System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");

            System.out.println("Tổng chi nhập hàng là : " + s + " VNĐ");
            System.out.println("ấn phím bất kì để trở lại!");
        }
        return s;
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
        System.out.print("Có sl mat hang: " + ds.size());
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
        for (int i = 0; i < listSoLuong.length; i++) {
            for (MatHang d : ds) {
                if (listMaMH[i].equals(d.getMaMatHang())) {
                    System.out.println((i + 1) + ". Mặt hàng (" + d.getMaMatHang() + "," + d.getTenMatHang() + ")- Số lượng bán: " + listSoLuong[i]);
                }
            }
        }
    }

    public List<HoaDonNhap> DocFile() {
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
}
