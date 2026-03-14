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
        System.out.print("Nhập lương cơ bản: ");
        luongCoBan = Double.parseDouble(sc.nextLine());
    }

    public abstract double tinhLuong();

    public void hienThiThongTin() {
        System.out.printf("| %-10s | %-20s | %15.0f | %15.0f |%n",
                maNV, hoTen, luongCoBan, tinhLuong());
    }
}

class NhanVienFullTime extends NhanVien {
    private double heSoLuong;

    @Override
    public void nhapThongTin(Scanner sc) {
        super.nhapThongTin(sc);
        System.out.print("Nhập hệ số lương: ");
        heSoLuong = Double.parseDouble(sc.nextLine());
    }

    @Override
    public double tinhLuong() {
        return luongCoBan * heSoLuong;
    }

    @Override
    public void hienThiThongTin() {
        System.out.printf("| %-10s | %-20s | %15.0f | HS: %-6.1f | %15.0f | %-10s |%n",
                maNV, hoTen, luongCoBan, heSoLuong, tinhLuong(), "Full-time");
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
        System.out.printf("| %-10s | %-20s | %15.0f | %3d giờ×%-5.0f | %15.0f | %-10s |%n",
                maNV, hoTen, luongCoBan, soGioLam, luongMoiGio, tinhLuong(), "Part-time");
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

            NhanVien nv;
            if (loai == 1) {
                nv = new NhanVienFullTime();
            } else {
                nv = new NhanVienPartTime();
            }
            nv.nhapThongTin(sc);
            dsNV.add(nv);
        }

        System.out.println("\n========== DANH SÁCH NHÂN VIÊN ==========");
        System.out.printf("| %-10s | %-20s | %15s | %-13s | %15s | %-10s |%n",
                "Mã NV", "Họ tên", "Lương CB", "Chi tiết", "Lương thực", "Loại");
        System.out.println("|------------|----------------------|-----------------|---------------|-----------------|------------|");
        for (NhanVien nv : dsNV) {
            nv.hienThiThongTin();
        }

        sc.close();
    }
}
