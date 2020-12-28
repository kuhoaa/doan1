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
public class NhanVien {
    String _maNhanVien;
    String _tenNhanVien;
    String _gioitinh;
    String _soDienThoai;
    String _diaChi;
    public NhanVien(){
        
    }

    public String getMaNhanVien() {
        return _maNhanVien;
    }

    public void setMaNhanVien(String _maNhanVien) {
        this._maNhanVien = _maNhanVien;
    }

    public String getTenNhanVien() {
        return _tenNhanVien;
    }

    public void setTenNhanVien(String _tenNhanVien) {
        this._tenNhanVien = _tenNhanVien;
    }

    public String getGioitinh() {
        return _gioitinh;
    }

    public void setGioitinh(String _gioitinh) {
        this._gioitinh = _gioitinh;
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

    public NhanVien(String _maNhanVien, String _tenNhanVien, String _gioitinh, String _soDienThoai, String _diaChi) {
        this._maNhanVien = _maNhanVien;
        this._tenNhanVien = _tenNhanVien;
        this._gioitinh = _gioitinh;
        this._soDienThoai = _soDienThoai;
        this._diaChi = _diaChi;
    }
    public NhanVien(String nv) {
        String[] sc = new String[5];
        sc = nv.split("#");
        this._maNhanVien = sc[0];
        this._tenNhanVien = sc[1];
        this._gioitinh=sc[2];
        this._soDienThoai  = sc[3];
        this._diaChi= sc[4]; 
    }
    
    public NhanVien Them(List <NhanVien>list){
        Scanner scan=new Scanner(System.in);
        boolean kt=true;
        do{
            kt=true;
            System.out.print("nhập mã nhân viên:");
            this._maNhanVien=scan.next();
            if((" ").contains(this._maNhanVien)){
                System.out.println("Lỗi: Mã nhân viên không được để trống");
                kt=false;
            }
            if(kt&& ! Pattern.matches("[N]{1}[V]{1}[0-9]{5}",this._maNhanVien)){
                System.out.println("Lỗi: Mã nhân viên phải có 7 kí tự bắt đầu bằng NV");
                kt=false;
            }
            if(kt){
                for (NhanVien nv:list){
                    if(nv.getMaNhanVien().contains(this._maNhanVien)){
                        System.out.println("Lôi: Mã nhân viên đã tồn tại");
                        kt=false;
                    }
                }
            }          
        }while(!(kt));
        do{
            kt=true;
            scan.nextLine();
            System.out.print("nhập tên nhân viên:");
            this._tenNhanVien=scan.nextLine();
            if(("").contains(this._tenNhanVien)){
                System.out.println("Lỗi: Tên nhân viên không được để trống");
                kt=false;
                
            }
            if(kt&& this._tenNhanVien.length()>30){
                System.out.println("Lỗi: Tên nhân viên phải có độ dài nhỏ hơn 30 kí tự");
                kt=false;
            }
        }while(!(kt));
        do{
            kt=true;
            System.out.print("nhập giới tính nhân viên:");
            this._gioitinh=scan.nextLine();

            if(("").contains(this._gioitinh)){
                System.out.println("Lỗi: giới tính nhân viên không được để trống");
                kt=false;
            }
            if(kt && (!"nam".equals(this._gioitinh.toLowerCase()) && !"nữ".equals(this._gioitinh.toLowerCase()))){
                System.out.println("Lỗi: Giới tính nhân viên phải là nam hoặc nữ");
                kt=false;
                
            }
        }while(!(kt));
        do{
            kt=true;
            System.out.print("Nhập số điện thoại:");
            this._soDienThoai = scan.nextLine();

            //kt cú pháp số điện thoại khi nhập còn không nhập thì bỏ qua
            if(!("").contains(this._soDienThoai)){
                if(!Pattern.matches("[0]{1}[0-9]{9}",this._soDienThoai)){
                    System.out.println("Lỗi: Số điện thoại 10 số và bắt đầu là số 0");
                    kt = false;
                }
            }
        }while(!kt);
        do{
             kt = true;
            System.out.print("Nhập địa chỉ:");
            this._diaChi = scan.nextLine();

            //kt hợp lệ địa chỉ khi nhập còn không nhập thì bỏ qua
            if(!("").contains(this._diaChi)){
                if(this._soDienThoai.length() > 23){
                    System.out.println("Lỗi: Địa chỉ phải có độ dài nhỏ hơn 23 ký tự");
                    kt = false;
                }
            }
        }while(!(kt));
        return this;
        
    }
    public NhanVien Sua(){
        Scanner scan=new Scanner(System.in);
        boolean kt=true;
         do{
            kt=true;
            System.out.print("nhập tên nhân viên:");
            this._tenNhanVien=scan.nextLine();
            if(("").contains(this._tenNhanVien)){
                System.out.println("Lỗi: Tên nhân viên không được để trống");
                kt=false;
                
            }
            if(kt&& this._tenNhanVien.length()>30){
                System.out.println("Lỗi: Tên nhân viên phải có độ dài nhỏ hơn 30 kí tự");
                kt=false;
            }
        }while(!(kt));
        do{
            kt=true;
            System.out.print("nhập giới tính nhân viên:");
            this._gioitinh=scan.nextLine();
            if(("").contains(this._gioitinh)){
                System.out.println("Lỗi: giới tính nhân viên không được để trống");
                kt=false;
            }
            if(kt&&  (!"nam".equals(this._gioitinh) && !"nu".equals(this._gioitinh))){
                System.out.println("Lỗi: Giới tính nhân viên phải là nam hoặc nữ");
                kt=false;
                
            }
        }while(!(kt));
        do{
            kt=true;
            System.out.print("Nhập số điện thoại:");
            this._soDienThoai = scan.nextLine();

            //kt cú pháp số điện thoại khi nhập còn không nhập thì bỏ qua
            if(!("").contains(this._soDienThoai)){
                if(!Pattern.matches("[0]{1}[0-9]{9}",this._soDienThoai)){
                    System.out.println("Lỗi: Số điện thoại 10 số và bắt đầu là số 0");
                    kt = false;
                }
            }
        }while(!kt);
        do{
             kt = true;
            System.out.print("Nhập địa chỉ:");
            this._diaChi = scan.nextLine();

            //kt hợp lệ địa chỉ khi nhập còn không nhập thì bỏ qua
            if(!("").contains(this._diaChi)){
                if(this._soDienThoai.length() > 23){
                    System.out.println("Lỗi: Địa chỉ phải có độ dài nhỏ hơn 23 ký tự");
                    kt = false;
                }
            }
        }while(!(kt));
        return this;  
    }
    public String ToString(){
         return "║"  + String.format("%-7s", this._maNhanVien) + "|"+ String.format("%-28s", this._tenNhanVien)+"|"+ String.format("%-16s", this._gioitinh) + "|"+String.format("%-27s", this._soDienThoai) + "|"+ String.format("%-19s", this._diaChi) +"|"+String.format("%-33s", "")+ "║";
    }
}

