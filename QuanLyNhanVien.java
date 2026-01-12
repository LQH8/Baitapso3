/**
 * Chuong trinh quan ly nhan vien.
 * Thuc hien cac chuc nang: Quan ly nhan vien van phong, san xuat, tinh luong, sap xep.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 1. Lop Abstract NhanVien
abstract class NhanVien {
    protected String maNV;
    protected String hoTen;
    protected double luongCoBan;

    public NhanVien(String maNV, String hoTen, double luongCoBan) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.luongCoBan = luongCoBan;
    }

    // Phuong thuc abstract tinhLuong
    public abstract double tinhLuong();

    // Phuong thuc hien thi thong tin co ban
    public void hienThi() {
        System.out.print("Ma NV: " + maNV + " | Ho ten: " + hoTen + " | Luong CB: " + String.format("%,.0f", luongCoBan));
    }
}

// 2. Lop Nhan vien van phong
class NhanVienVanPhong extends NhanVien {
    private int soNgayLamViec;

    public NhanVienVanPhong(String maNV, String hoTen, double luongCoBan, int soNgayLamViec) {
        super(maNV, hoTen, luongCoBan);
        this.soNgayLamViec = soNgayLamViec;
    }

    @Override
    public double tinhLuong() {
        return luongCoBan + soNgayLamViec * 200000;
    }

    @Override
    public void hienThi() {
        super.hienThi();
        System.out.println(" | So ngay lam: " + soNgayLamViec + " | --> Tong luong: " + String.format("%,.0f", tinhLuong()) + " VND");
    }
}

// 3. Lop Nhan vien san xuat
class NhanVienSanXuat extends NhanVien {
    private int soSanPham;

    public NhanVienSanXuat(String maNV, String hoTen, double luongCoBan, int soSanPham) {
        super(maNV, hoTen, luongCoBan);
        this.soSanPham = soSanPham;
    }

    @Override
    public double tinhLuong() {
        return luongCoBan + soSanPham * 250000;
    }

    @Override
    public void hienThi() {
        super.hienThi();
        System.out.println(" | So san pham: " + soSanPham + " | --> Tong luong: " + String.format("%,.0f", tinhLuong()) + " VND");
    }
}

// 4. Lop Main de chay chuong trinh
public class QuanLyNhanVien {
    public static void main(String[] args) {
        // Nhap danh sach nhan vien dung ArrayList
        List<NhanVien> danhSachNV = new ArrayList<>();

        // Tao 2 nhan vien van phong
        NhanVien nvVP1 = new NhanVienVanPhong("NV001", "Nguyen Van A", 5000000, 22);
        NhanVien nvVP2 = new NhanVienVanPhong("NV002", "Tran Thi B", 6000000, 20);

        // Tao 2 nhan vien san xuat
        NhanVien nvSX1 = new NhanVienSanXuat("NV003", "Le Van C", 7000000, 100);
        NhanVien nvSX2 = new NhanVienSanXuat("NV004", "Pham Thi D", 7500000, 120);

        // Add vao ArrayList
        danhSachNV.add(nvVP1);
        danhSachNV.add(nvVP2);
        danhSachNV.add(nvSX1);
        danhSachNV.add(nvSX2);

        System.out.println("=== DANH SACH NHAN VIEN BAN DAU ===");
        for (NhanVien nv : danhSachNV) {
            nv.hienThi();
        }

        // In danh sach nhan vien ra man hinh theo giam dan cua luong
        // Su dung Comparator de sap xep
        Collections.sort(danhSachNV, new Comparator<NhanVien>() {
            @Override
            public int compare(NhanVien nv1, NhanVien nv2) {
                // So sanh luong nv2 voi nv1 de sap xep giam dan
                return Double.compare(nv2.tinhLuong(), nv1.tinhLuong());
            }
        });

        System.out.println("\n=== DANH SACH NHAN VIEN THEO LUONG GIAM DAN ===");
        for (NhanVien nv : danhSachNV) {
            nv.hienThi();
        }

        // Tim nhan vien co luong cao nhat
        timNhanVienLuongCaoNhat(danhSachNV);
    }

    public static void timNhanVienLuongCaoNhat(List<NhanVien> list) {
        if (list.isEmpty()) {
            System.out.println("Danh sach trong.");
            return;
        }

        NhanVien maxNV = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).tinhLuong() > maxNV.tinhLuong()) {
                maxNV = list.get(i);
            }
        }

        System.out.println("\n=== NHAN VIEN CO LUONG CAO NHAT ===");
        maxNV.hienThi();
    }
}
