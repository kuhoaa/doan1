package GiaoDien;
import HoTro.*;

 public class QLChung {
     
    HienThi hienThi = new HienThi();

    public void KhoiTao() {
        hienThi.XoaManHinh();
        hienThi.TieuDe();
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                              CHỨC NĂNG                                                                ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║                                                 1: QUẢN LÝ KHÁCH HÀNG                                                                 ║");
        System.out.println("║                                                 2: QUẢN LÝ NHÀ CUNG CẤP                                                               ║");
        System.out.println("║                                                 3: QUẢN LÝ NHÂN VIÊN                                                                  ║");
        System.out.println("║                                                 4: QUẢN LÝ MẶT HÀNG                                                                   ║");
        System.out.println("║                                                 5: QUẢN LÝ HÓA ĐƠN NHẬP                                                               ║");
        System.out.println("║                                                 6: QUẢN LÝ HÓA ĐƠN XUẤT                                                               ║");
        System.out.println("║                                                 7: THỐNG KÊ                                                                           ║");
        System.out.println("║                                                 8: THOÁT CHƯƠNG TRÌNH                                                                 ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════╝");
        System.out.print("Chọn chức năng: ");
    }

    public void Thoat(){
        hienThi.XoaManHinh();
        hienThi.TieuDe();
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                               BẠN CÓ MUỐN THOÁT KHỎI CHƯƠNG TRÌNH?                                                    ║");
        System.out.println("║                                                                                                                                       ║");
        System.out.println("║                                                                                                                                       ║");
        System.out.println("║                                                               y/n                                                                     ║");
        System.out.println("║                                                                                                                                       ║");
        System.out.println("║                                                                                                                                       ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════╝");
    }
 }

    