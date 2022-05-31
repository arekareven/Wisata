package ta.project.wisata.db;

public class Berita {
    private String penulis;
    private String gambar;
    private String judul;
    private String isi;

    public Berita(){}

    public Berita(String penulis, String gambar, String judul, String isi) {
        this.penulis = penulis;
        this.gambar = gambar;
        this.judul = judul;
        this.isi = isi;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
