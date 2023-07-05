package com.example.deneme;

public class Oyuncular {
    private String ad;
    private String soyad;
    private String pozisyon;

    public Oyuncular(String ad, String soyad, String pozisyon){
        this.ad = ad;
        this.soyad = soyad;
        this.pozisyon = pozisyon;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getPozisyon() {
        return pozisyon;
    }
}
