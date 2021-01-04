package GiaoDien;
import DoiTuong.KhachHang;
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
public class QLKhachHang {
    static int chucNang = 0;
    static boolean thoat = false;
    static boolean luachon = false;
    static String chonThoat = "";
    static Scanner Scan = new Scanner(System.in);
    static List<KhachHang> ds = new ArrayList<KhachHang>();
    HienThi hienThi =new HienThi();


    public void KhoiTao() {
        do{
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            ds = new ArrayList<KhachHang>();
            ds = DocFile();

            HienThiDanhSach(ds);
            System.out.println("╟──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
            System.out.println("║1. Thêm, 2. Sửa, 3. Xóa, 4. Tìm  5. Menu chính, 6. Thoát                                                                                  ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("Chọn chức năng: ");
            chucNang = Scan.nextInt();
            switch (chucNang)
            {
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


                        chucNang = Menu6();
                        switch (chucNang) {
                            case 1:
                                TimTheoMa();
                                break;
                            case 2:
                                TimTheoTen();
                                break;


                        }
                    }while (chucNang >= 1 && chucNang <= 2);
                    break;

                case 5:
                    thoat = true;
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }while(!thoat);
    }

        public  static  int Menu6(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ----------------------------------------------");
        System.out.println("|            1. Tìm theo Mã                    |");
        System.out.println("|            2. Tìm theo Tên                   |");
        System.out.println("|            3. Quay lại                       |");
        System.out.println(" ----------------------------------------------");
        String epkieu;
        do {
            System.out.print("Vui lòng chọn (1-3)  : ");
            epkieu=scanner.nextLine();

        }while(Xulingoaile.Kiemtra(epkieu)==false||Integer.parseInt(epkieu) < 0 || Integer.parseInt(epkieu) >3);
        return Integer.parseInt(epkieu);
    }

    private void TieuDeChucNang(){
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           CHỨC NĂNG QUẢN LÝ KHÁCH HÀNG                                                                                   ║");
    }

    private void HienThiDanhSach(List<KhachHang> dsKh){
        System.out.println("║                                DANH SÁCH KHÁCH HÀNG                                                                                      ║");
        System.out.println("╟──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║ MÃ KH |            TÊN KH            |    SỐ ĐIỆN THOẠI   |        ĐỊA CHỈ        ╢ ");
        System.out.println("╟──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");

        for (KhachHang kh : dsKh)
        {
            System.out.println(kh.ToString());
        }
    }

    private void HienThiChiTiet(KhachHang kh){
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                               CHI TIẾT KHÁCH HÀNG                                                                                         ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║ MÃ KH |            TÊN KH            |    SỐ ĐIỆN THOẠI   |        ĐỊA CHỈ                                                                ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢");
        System.out.println(kh.ToString());
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    private void Them(){
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                                  THÊM KHÁCH HÀNG                                                                                         ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();
            KhachHang kh = new KhachHang();
            kh.Them(ds);
            System.out.print("Bạn có muốm lưu khách hàng này không? (y/n):");
            chonThoat = Scan.next();
            if (chonThoat.equalsIgnoreCase("y"))
            {
                ds.add(kh);
                GhiFile(ds);
                System.out.println("Thêm khách hàng thành công!");
            }

            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }
        }while (!luachon);
    }

    private void Sua(){
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                                   SỬA KHÁCH HÀNG                                                                                      ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");
            ds = DocFile();

            System.out.print("Nhập mã khách hàng:");
            String maKh = Scan.next();
            boolean kt = false;
            List<KhachHang> dsMoi = new ArrayList<KhachHang>();
            for (KhachHang kh : ds)
            {
                if(kh.getMaKhachHang().contains(maKh))
                {
                    HienThiChiTiet(kh);
                    System.out.println("Nhập các thông tin thay đổi!");
                    kt = true;
                    kh.Sua();
                }
                dsMoi.add(kh);
            }
            if(!kt)
            {
                System.out.println("Không tìm thấy khách hàng cần sửa!");
            }
            else {
                System.out.print("Bạn có muốm thay đổi thông tin khách hàng này không? (y/n):");
                chonThoat = Scan.next();
                if (chonThoat.equalsIgnoreCase("y")) {
                    GhiFile(dsMoi);
                    System.out.println("Sửa khách hàng thành công!");
                }
            }
            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }

        }while (!luachon);
    }

    private void Xoa(){
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                                   XÓA KHÁCH HÀNG                                                                                      ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");

            ds = DocFile();

            System.out.print("Nhập mã khách hàng:");
            String maKh = Scan.next();
            boolean kt = false;
            List<KhachHang> dsMoi = new ArrayList<KhachHang>();
            for (KhachHang kh : ds)
            {
                if(kh.getMaKhachHang().contains(maKh)){
                    HienThiChiTiet(kh);
                    kh.setMaKhachHang("");
                    kt = true;
                }
                dsMoi.add(kh);
            }
            if(!kt)
            {
                System.out.println("Không tìm thấy khách hàng cần xóa!");
            }
            else {
                System.out.print("Bạn có muốm xóa khách hàng này không? (y/n):");
                chonThoat = Scan.next();
                if (chonThoat.equalsIgnoreCase("y")) {
                    GhiFile(dsMoi);
                    System.out.println("Xóa khách hàng thành công!");
                }
            }
            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }
        }while (!luachon);
    }

    private void TimTheoMa()
    {
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                              TÌM THEO MÃ KHÁCH HÀNG                                                                                   ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");

            ds = DocFile();

            System.out.print("Nhập mã khách hàng:");
            String maKh = Scan.next();
            boolean kt = false;
            for (KhachHang kh : ds)
            {
                if(kh.getMaKhachHang().contains(maKh)){
                    HienThiChiTiet(kh);
                    kt = true;
                }
            }
            if(!kt)
            {
                System.out.println("Không tìm thấy khách hàng cần tìm!");
            }
            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }

        }while (!luachon);
    }

    private void TimTheoTen(){
        boolean luachon = false;
        do {
            hienThi.XoaManHinh();
            hienThi.TieuDe();
            TieuDeChucNang();
            System.out.println("║                              TÌM THEO TÊN KHÁCH HÀNG                                                                                  ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.println("");

            ds = DocFile();

            System.out.print("Nhập tên khách hàng:");
            String ten = Scan.next();
            boolean kt = false;
            List<KhachHang> dsTimKiem = new ArrayList<KhachHang>();
            for (KhachHang kh : ds)
            {
                if(kh.getTenKhachHang().toLowerCase().contains(ten.toLowerCase())){
                    dsTimKiem.add(kh);
                    kt = true;
                }
            }
            if(!kt)
            {
                System.out.println("Không tìm thấy khách hàng cần tìm!");
            }
            else {
                System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
                HienThiDanhSach(dsTimKiem);
                System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            }

            System.out.print("Bạn có muốm tiếp tục chức năng này không? (y/n):");
            chonThoat = Scan.next();
            if (!chonThoat.equalsIgnoreCase("y")) {
                luachon = true;
            }

        }while (!luachon);
    }

    private List<KhachHang> DocFile(){
        List<KhachHang> ds = new ArrayList<KhachHang>();
        String fileName = "khachhang.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(Pattern.quote("|"));
                //địa chỉ không bắt nhập
                if(array.length == 4)
                {
                    KhachHang kh = new KhachHang(array[0],array[1],array[2],array[3]);
                    ds.add((KhachHang) kh);
                }
                else
                {
                    KhachHang kh = new KhachHang(array[0],array[1],array[2],"");
                    ds.add((KhachHang) kh);
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
        }
        catch(IOException ex) {
        }
        return  ds;
    }

    private void GhiFile(List<KhachHang> ds){
        try {
            Formatter f = new Formatter("khachhang.txt");
            for (KhachHang kh : ds)
            {
                if(kh.getMaKhachHang() != "") {
                    String content = kh.getMaKhachHang() + "|" + kh.getTenKhachHang() + "|" + kh.getSoDienThoai() + "|" + kh.getDiaChi();
                    f.format(content + "\r\n", null);
                }
            }
            f.close();
        } catch (FileNotFoundException e) {

        }
    }
}
