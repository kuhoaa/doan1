package DoiTuong;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class KhachHang {
    String _maKhachHang;
    String _tenKhacHhang;
    String _soDienThoai;
    String _diaChi;

    public String getMaKhachHang()
    {
        return this._maKhachHang;
    }

    public String getTenKhachHang()
    {
        return this._tenKhacHhang;
    }

    public String getSoDienThoai()
    {
        return this._soDienThoai;
    }

    public String getDiaChi()
    {
        return this._diaChi;
    }

    public void setMaKhachHang(String maKh)
    {
        this._maKhachHang = maKh;
    }

    public void setTenKhachHang( String tenKH)
    {
        this._tenKhacHhang = tenKH;
    }

    public void setSoDienThoai(String sdt)
    {
        this._soDienThoai = sdt;
    }

    public void setDiaChi( String dc)
    {
        this._diaChi = dc;
    }

    public KhachHang(){

    }
    public KhachHang(String maKh, String tenKH, String sdt, String dc){
        this._maKhachHang = maKh;
        this._tenKhacHhang = tenKH;
        this._soDienThoai = sdt;
        this._diaChi = dc;
    }
    public KhachHang(String kh) {
        String[] sc = new String[4];
        sc = kh.split("|");
        this._maKhachHang = sc[0];
        this._tenKhacHhang = sc[1];
        this._soDienThoai  = sc[2];
        this._diaChi= sc[3];
    }
    public KhachHang Them(List<KhachHang> list){
        Scanner Scan = new Scanner(System.in);
        //Check mã khách hàng
        boolean kt = true;
        do{
            kt = true;
            System.out.print("Nhập mã khách hàng:");
            this._maKhachHang = Scan.nextLine();


            if(("").contains(this._maKhachHang)){
                System.out.println("Lỗi: Mã khách hàng không được để trống");
                kt = false;
            }


            if(kt && !Pattern.matches("[K]{1}[H]{1}[0-9]{5}",this._maKhachHang)){
                System.out.println("Lỗi: Mã khách hàng phải có độ dài 7 ký tự và bắt đầu bởi KH");
                kt = false;
            }
            //kt trùng mã
            if(kt)
            {
                for (KhachHang kh: list) {
                    if(kh.getMaKhachHang().contains(this._maKhachHang)){
                        System.out.println("Lỗi: Mã khách hàng đã tồn tại");
                        kt = false;
                        break;
                    }
                }
            }
        }while(!kt);

        //Check tên khách hàng
        do{
            kt = true;
            System.out.print("Nhập tên khách hàng:");
            this._tenKhacHhang = Scan.nextLine();

            //kt tên khách hàng không được rỗng
            if(("").contains(this._tenKhacHhang)){
                System.out.println("Lỗi: Tên khách hàng không được để trống");
                kt = false;
            }

            //kt tên khách hàng không được vượt quá 30 kí tự
            if(kt && this._tenKhacHhang.length()>30){
                System.out.println("Lỗi: Tên khách hàng phải có độ dài nhỏ hơn 30 ký tự");
                kt = false;
            }
        }while(!kt);

        //Check số điện thoại
        do{
            kt = true;
            System.out.print("Nhập số điện thoại:");
            this._soDienThoai = Scan.nextLine();

            //kt cú pháp số điện thoại khi nhập còn không nhập thì bỏ qua
            if(!("").contains(this._soDienThoai)){
                if(!Pattern.matches("[0]{1}[0-9]{9}",this._soDienThoai)){
                    System.out.println("Lỗi: Số điện thoại 10 số và bắt đầu là số 0");
                    kt = false;
                }
            }
        }while(!kt);

        //Check địa chỉ
        do{
            kt = true;
            System.out.print("Nhập địa chỉ:");
            this._diaChi = Scan.nextLine();

            //kt hợp lệ địa chỉ khi nhập còn không nhập thì bỏ qua
            if(!("").contains(this._diaChi)){
                if(this._soDienThoai.length() > 23){
                    System.out.println("Lỗi: Địa chỉ phải có độ dài nhỏ hơn 23 ký tự");
                    kt = false;
                }
            }
        }while(!kt);
        return this;
    }

    public KhachHang Sua(){
        Scanner Scan = new Scanner(System.in);
        boolean kt = true;
        //Check tên khách hàng
        do{
            kt = true;
            System.out.print("Nhập tên khách hàng:");
            this._tenKhacHhang = Scan.nextLine();

            //kt tên khách hàng không được rỗng
            if(("").contains(this._tenKhacHhang)){
                System.out.println("Lỗi: Tên khách hàng không được để trống");
                kt = false;
            }

            //kt tên khách hàng không được vượt quá 30 kí tự
            if(kt && this._tenKhacHhang.length() > 30){
                System.out.println("Lỗi: Tên khách hàng phải có độ dài nhỏ hơn 30 ký tự");
                kt = false;
            }
        }while(!kt);

        //Check số điện thoại
        do{
            kt = true;
            System.out.print("Nhập số điện thoại:");
            this._soDienThoai = Scan.nextLine();

            //kt cú pháp số điện thoại khi nhập còn không nhập thì bỏ qua
            if(!("").contains(this._soDienThoai)){
                if(!Pattern.matches("[0]{1}[0-9]{9}",this._soDienThoai)){
                    System.out.println("Lỗi: Số điện thoại 10 số và bắt đầu là số 0");
                    kt = false;
                }
            }
        }while(!kt);

        //Check địa chỉ
        do{
            kt = true;
            System.out.print("Nhập địa chỉ:");
            this._diaChi = Scan.nextLine();

            //kt hợp lệ địa chỉ khi nhập còn không nhập thì bỏ qua
            if(!("").contains(this._diaChi)){
                if(this._diaChi.length() > 23){
                    System.out.println("Lỗi: Địa chỉ phải có độ dài nhỏ hơn 23 ký tự");
                    kt = false;
                }
            }
        }while(!kt);
        return this;
    }

    public String ToString(){
        return "║"  + String.format("%-7s", this._maKhachHang) + "|"+ String.format("%-30s", this._tenKhacHhang)+"|"+ String.format("%-20s", this._soDienThoai) + "|"+ String.format("%-23s", this._diaChi) +"|"+String.format("%-51s", "")+"║";
    }
}


