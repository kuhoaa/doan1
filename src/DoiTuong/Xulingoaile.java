package DoiTuong;

public class Xulingoaile {
    public static boolean  Kiemtra(String chain)
    {
        char[] a=chain.toCharArray();
        for (char i : a) {
            if(Character.isDigit(i)==false){
                return false;
            }
        }
        return true;
    }
}
