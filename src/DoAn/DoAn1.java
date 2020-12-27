/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoAn;

import GiaoDien.*;
import GiaoDien.QLKhachHang;
import java.util.Scanner;
/**
 *
 * @author LENOVO
 */
public class DoAn1 {

    static boolean thoat = false;
    static String chonthoat = "";
    static int chucnang = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QLChung qlchung = new QLChung();
        QLKhachHang qlKhachHang = new QLKhachHang();
        QLNhaCungCap qlNhaCungCap = new QLNhaCungCap();
        QLNhanVien qlNhanVien=new QLNhanVien();
        QLMatHang qlMatHang=new QLMatHang();
        QLHoaDonNhap qLHoaDonNhap=new QLHoaDonNhap();
        QLHoaDonXuat qLHoaDonXuat=new QLHoaDonXuat();
        ThongKe thongKe = new ThongKe();
        Scanner Scan = new Scanner(System.in);
        do{

            switch (chucnang)
            {
                case 0:
                    qlchung.KhoiTao();
                    chucnang = Scan.nextInt();
                    break;
                case 1:
                    qlKhachHang.KhoiTao();
                    chucnang = 0;
                    break;
                case 2:
                    qlNhaCungCap.KhoiTao();
                    chucnang = 0;
                    break;
                case 3:
                    qlNhanVien.KhoiTao();
                    chucnang=0;
                    break;
                case 4:
                    qlMatHang.KhoiTao();
                    chucnang=0;  
                    break;
                case 5:
                    qLHoaDonNhap.KhoiTao();
                    chucnang=0;  
                    break;
                case 6:
                    qLHoaDonXuat.KhoiTao();
                    chucnang=0; 
                    break;
                case 7:
                    thongKe.KhoiTao();
                    //Scan.hasNext();
                    chucnang=0;  
                    break;
                case 8:
                    qlchung.Thoat();
                    chonthoat = Scan.next();
                    if (chonthoat.equalsIgnoreCase("y"))
                        thoat = true;
                    else
                        chucnang = 0;
                    break;
            }
        }while(!thoat);
        System.exit(0);
    }
    
}
