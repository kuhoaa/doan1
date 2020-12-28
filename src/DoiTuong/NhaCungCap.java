/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoiTuong;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 *
 * @author LENOVO
 */
public class NhaCungCap {
    String _mancc;
    String _tenncc;
    String _soDienThoai;
    String _diaChi;
    public NhaCungCap(){
        
    }

    public String getMancc() {
        return _mancc;
    }

    public void setMancc(String _mancc) {
        this._mancc = _mancc;
    }

    public String getTenncc() {
        return _tenncc;
    }

    public void setTenncc(String _tenncc) {
        this._tenncc = _tenncc;
    }

    public String getSoDienThoai() {
        return _soDienThoai;
    }

    public void setSoDienThoai(String _soDienThoai) {
        this._soDienThoai = _soDienThoai;
    }

    public String getDiaChi() {
        return _diaChi;
    }

    public void setDiaChi(String _diaChi) {
        this._diaChi = _diaChi;
    }

    public NhaCungCap(String _mancc, String _tenncc, String _soDienThoai, String _diaChi) {
        this._mancc = _mancc;
        this._tenncc = _tenncc;
        this._soDienThoai = _soDienThoai;
        this._diaChi = _diaChi;
    }
    public NhaCungCap(String ncc) {
        String[] sc = new String[4];
        sc = ncc.split("|");
        this._mancc = sc[0];
        this._tenncc = sc[1];
        this._soDienThoai  = sc[2];
        this._diaChi= sc[3]; 
    }

    public NhaCungCap Them(List<NhaCungCap> list){
        Scanner Scan=new Scanner(System.in);
        //kiểm tra mã 
        boolean kt;
        do{
            kt=true;
            System.out.print("nhập mã nhà cung cấp:");
            this._mancc=Scan.nextLine();
        //kiểm tra mã nhà cung cấp không được rỗng
            if(("").contains(this._mancc)){
                System.out.println("lỗi: Mã nhà cung cấp không được để trống");
                kt=false;
            }
          //   kt mã nhà cung cấp đúng cú pháp VD: NCC456
            if(kt && !Pattern.matches("[N]{1}[C]{1}[C]{1}[0-9]{4}",this._mancc)){
                System.out.println("Lỗi: Mã khách hàng phải có độ dài 7 ký tự và bắt đầu bởi NCC");
                kt = false;
            }
            //kiểm tra trùng nhau
            if(kt){
                for(NhaCungCap ncc:list){
                   if(ncc.getMancc().contains(this._mancc)){  
                        System.out.println("Lỗi: Mã nhà cung cấp đã tồn tại");
                        kt=false;
                        break;
                    } 
                }
            }
        }while(!(kt));
        //check tên nhà  cung cấp
        do{
            kt=true;
            System.out.print("nhập tên nhà cung cấp:");
            this._tenncc=Scan.nextLine();
            //kiểm tra tên nhà cung cấp không được rỗng
            if(("").contains(this._tenncc)){
                System.out.println("Lỗi: Tên nhà cung cấp không được để trống");
                kt=false;
            }
            //kiểm tra tên nhà cung cấp không được vượt quá 30 kí tự
            if(kt&& this._tenncc.length()>30){
                System.out.println("Lỗi: tên nhà cung cấp phải nhỏ hơn 30 kí tự");
                kt=false;
            }
        }while(!(kt));
        //check số điện thoại
        do{
            kt=true;
            System.out.print("nhập số điện thoại:");
            this._soDienThoai=Scan.nextLine();
            if(!("").contains(this._soDienThoai)){
                if(!Pattern.matches("[0]{1}[0-9]{9}",this._soDienThoai)){
                    System.out.println("Lỗi: Số điện thoại có độ dài 10 số bắt đầu là số 0");
                    kt=false;
                }
            }
        }while(!(kt));
        //check địa chỉ
        do{
            kt=true;
            System.out.print("nhập địa chỉ :");
            this._diaChi=Scan.nextLine();
            //kiểm trahợp lệ địa chỉ khi nhập còn không nhập thì bỏ qua
            if((" ").contains(this._diaChi)){
                if(kt&& this._diaChi.length()>23){
                    System.out.println("Lỗi: địa chỉ phải nhỏ hơn 23 kí tự");
                    kt=false;
                }
            }
        }while(!(kt));
        return this;
    }
    public NhaCungCap Sua(){
          Scanner Scan=new Scanner(System.in);
        //kiểm tra mã 
        boolean kt;
        //check tên nhà  cung cấp
        do{
            kt=true;
            System.out.println("nhập tên nhà cung cấp:");
            this._tenncc=Scan.nextLine();
            //kiểm tra tên nhà cung cấp không được rỗng
            if((" ").contains(this._tenncc)){
                System.out.println("Lỗi: Tên nhà cung cấp không được để trống");
                kt=false;
            }
            //kiểm tra tên nhà cung cấp không được vượt quá 30 kí tự
            if(kt&& this._tenncc.length()>30){
                System.out.println("Lỗi: tên nhà cung cấp phải nhỏ hơn 30 kí tự");
            }
        }while(!(kt));
        //check số điện thoại
        do{
            kt=true;
            System.out.println("nhập số điện thoại:");
            this._soDienThoai=Scan.nextLine();
            //kiểm tra số điện thoại khi nhập không nhập thì bỏ qua
            if((" ").contains(this._mancc)){
                if(!Pattern.matches("[0]{1}[0-9]{9}",this._soDienThoai)){
                    System.out.println("Lỗi: Số điện thoại có độ dài 10 số bắt đầu là số 0");
                    kt=false;
                }
            }
        }while(!(kt));
        //check địa chỉ
        do{
            kt=true;
            System.out.println("nhập địa chỉ :");
            this._diaChi=Scan.nextLine();
            //kiểm trahợp lệ địa chỉ khi nhập còn không nhập thì bỏ qua
            if((" ").contains(this._diaChi)){
                if(kt&& this._diaChi.length()>23){
                    System.out.println("Lỗi: địa chỉ phải nhỏ hơn 23 kí tự");
                    kt=false;
                }
            }
        }while(!(kt));
        return this;
    }
    public String ToString(){
     return "║"  + String.format("%-8s", this._mancc) + "|"+ String.format("%-31s", this._tenncc)+"|"+ String.format("%-20s", this._soDienThoai) + "|"+ String.format("%-19s", this._diaChi) +"|"+String.format("%-53s", "")+"║";
    }

   
    
}
    


            


                
            
                
            
        
        
        
   
           
                
        
        
        
        
    



    
    

