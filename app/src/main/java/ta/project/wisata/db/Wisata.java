package ta.project.wisata.db;

public class Wisata {

    private String namaWisata;
    private String gambar;
    private String deskripsi;
    private String alamat;
    private String fasilitas;
    private String koordinat;
    private String jamBuka;
    private String jamTutup;

    public Wisata(){}

    public Wisata(String namaWisata, String gambar, String deskripsi, String alamat, String fasilitas, String koordinat, String jamBuka, String jamTutup) {
        this.namaWisata = namaWisata;
        this.gambar = gambar;
        this.deskripsi = deskripsi;
        this.alamat = alamat;
        this.fasilitas = fasilitas;
        this.koordinat = koordinat;
        this.jamBuka = jamBuka;
        this.jamBuka = jamTutup;

    }

    public String getNamaWisata() {
        return namaWisata;
    }

    public void setNamaWisata(String namaWisata) {
        this.namaWisata = namaWisata;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getKoordinat() {
        return koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.koordinat = koordinat;
    }

    public String getJamBuka() {
        return jamBuka;
    }

    public void setJamBuka(String jamBuka) {
        this.jamBuka = jamBuka;
    }

    public String getJamTutup() {
        return jamTutup;
    }

    public void setJamTutup(String jamTutup) {
        this.jamTutup = jamTutup;
    }
}
