import java.util.ArrayList;
import java.util.Scanner;

abstract class NhanVien {
    protected String maNV;
    protected String hoTen;
    protected double luongCoBan;

    public void nhapThongTin(Scanner sc) {
        System.out.print("Nhập mã NV: ");
        maNV = sc.nextLine();
        System.out.print("Nhập họ tên: ");
        hoTen = sc.nextLine();
    }

    public abstract double tinhLuong();
    public abstract void hienThiThongTin();
}

class NhanVienFullTime extends NhanVien {
    private double heSoLuong;

    @Override
    public void nhapThongTin(Scanner sc) {
        super.nhapThongTin(sc);
        System.out.print("Nhập lương cơ bản: ");
        luongCoBan = Double.parseDouble(sc.nextLine());
        System.out.print("Nhập hệ số lương: ");
        heSoLuong = Double.parseDouble(sc.nextLine());
    }

    @Override
    public double tinhLuong() {
        return luongCoBan * heSoLuong;
    }

    @Override
    public void hienThiThongTin() {
        String chiTiet = "HS: " + heSoLuong;
        System.out.printf("| %-7s | %-20s | %13s | %-12s | %13s | %-10s |%n", 
            maNV, hoTen, String.format("%,.0f", luongCoBan), chiTiet, String.format("%,.0f", tinhLuong()), "Full-time");
    }
}

class NhanVienPartTime extends NhanVien {
    private int soGioLam;
    private double luongMoiGio;

    @Override
    public void nhapThongTin(Scanner sc) {
        super.nhapThongTin(sc);
        System.out.print("Nhập số giờ làm: ");
        soGioLam = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập lương mỗi giờ: ");
        luongMoiGio = Double.parseDouble(sc.nextLine());
    }

    @Override
    public double tinhLuong() {
        return soGioLam * luongMoiGio;
    }

    @Override
    public void hienThiThongTin() {
        String chiTiet = soGioLam + "h x " + String.format("%,.0f", luongMoiGio);
        System.out.printf("| %-7s | %-20s | %13s | %-12s | %13s | %-10s |%n", 
            maNV, hoTen, "-", chiTiet, String.format("%,.0f", tinhLuong()), "Part-time");
    }
}

public class Bai4_QuanLyNhanVien {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<NhanVien> dsNV = new ArrayList<>();

        System.out.print("Nhập số lượng nhân viên: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Nhập thông tin nhân viên thứ " + (i + 1) + " ---");
            System.out.print("Loại nhân viên (1: Full-time, 2: Part-time): ");
            int loai = Integer.parseInt(sc.nextLine());
            
            NhanVien nv = (loai == 1) ? new NhanVienFullTime() : new NhanVienPartTime();
            nv.nhapThongTin(sc);
            dsNV.add(nv);
        }

        // Định nghĩa các đường kẻ bảng
        String divider = "+---------+----------------------+---------------+--------------+---------------+------------+";
        String header  = "| %-7s | %-20s | %13s | %-12s | %13s | %-10s |%n";

        System.out.println("\n" + " ".repeat(30) + "DANH SÁCH NHÂN VIÊN");
        System.out.println(divider);
        System.out.printf(header, "Mã NV", "Họ tên", "Lương CB", "Chi tiết", "Lương thực", "Loại");
        System.out.println(divider);

        double tongLuong = 0;
        for (NhanVien nv : dsNV) {
            nv.hienThiThongTin();
            tongLuong += nv.tinhLuong();
        }

        System.out.println(divider);
        System.out.printf("| %-61s | %13s |            |%n", "TỔNG LƯƠNG CHI TRẢ", String.format("%,.0f", tongLuong));
        System.out.println(divider);

        sc.close();
    }
}
