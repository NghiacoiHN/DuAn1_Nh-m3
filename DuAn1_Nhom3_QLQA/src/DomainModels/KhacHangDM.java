package DomainModels;

/**
 *
 * @author HP
 */
public class KhacHangDM {

    private Long IDKH;
    private String MaKH;
    private String HoKH;
    private String TenKH;
    private String SDT;
    private String DiaChi;
    private int TrangThai;

    public KhacHangDM() {
    }

    public KhacHangDM(Long IDTichDiem, String MaKH, String HoKH, String TenKH, String SDT, String DiaChi, int TrangThai) {

        this.MaKH = MaKH;
        this.HoKH = HoKH;
        this.TenKH = TenKH;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.TrangThai = TrangThai;
    }

    public KhacHangDM(Long IDKH, Long IDTichDiem, String MaKH, String HoKH, String TenKH, String SDT, String DiaChi, int TrangThai) {
        this.IDKH = IDKH;
        this.MaKH = MaKH;
        this.HoKH = HoKH;
        this.TenKH = TenKH;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.TrangThai = TrangThai;
    }

    public KhacHangDM(String MaKH, String HoKH, String TenKH, String SDT, String DiaChi, int TrangThai) {
        this.MaKH = MaKH;
        this.HoKH = HoKH;
        this.TenKH = TenKH;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.TrangThai = TrangThai;
    }

    public Long getIDKH() {
        return IDKH;
    }

    public void setIDKH(Long IDKH) {
        this.IDKH = IDKH;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getHoKH() {
        return HoKH;
    }

    public void setHoKH(String HoKH) {
        this.HoKH = HoKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

}
