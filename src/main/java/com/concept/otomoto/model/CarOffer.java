package com.concept.otomoto.model;


import javax.persistence.*;

@Entity
public class CarOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private String dealer;
    private String carBrand;
    private String price;
    private String currency;
    private String priceDetails;
    @Lob
    private String adress;
    private String datePublished;
    private String idOffer;

    private String odertaOd;
    private String kategoria;
    private String markaPojazdu;
    private String modelPojazdu;
    private String wersja;
    private String rokProdukcji;
    private String przebieg;
    private String pojemnoscSkokowa;
    private String rodzajPaliwa;
    private String moc;
    private String skrzyniaBiegow;
    private String naped;
    private String typ;
    private String liczbaDrzwi;
    private String liczbaMiejsc;
    private String color;
    private String metalik;
    private String bezwypadkowy;
    private String stan;

    public CarOffer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(String idOffer) {
        this.idOffer = idOffer;
    }

    public String getOdertaOd() {
        return odertaOd;
    }

    public void setOdertaOd(String odertaOd) {
        this.odertaOd = odertaOd;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getMarkaPojazdu() {
        return markaPojazdu;
    }

    public void setMarkaPojazdu(String markaPojazdu) {
        this.markaPojazdu = markaPojazdu;
    }

    public String getModelPojazdu() {
        return modelPojazdu;
    }

    public void setModelPojazdu(String modelPojazdu) {
        this.modelPojazdu = modelPojazdu;
    }

    public String getWersja() {
        return wersja;
    }

    public void setWersja(String wersja) {
        this.wersja = wersja;
    }

    public String getRokProdukcji() {
        return rokProdukcji;
    }

    public void setRokProdukcji(String rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    public String getPrzebieg() {
        return przebieg;
    }

    public void setPrzebieg(String przebieg) {
        this.przebieg = przebieg;
    }

    public String getPojemnoscSkokowa() {
        return pojemnoscSkokowa;
    }

    public void setPojemnoscSkokowa(String pojemnoscSkokowa) {
        this.pojemnoscSkokowa = pojemnoscSkokowa;
    }

    public String getRodzajPaliwa() {
        return rodzajPaliwa;
    }

    public void setRodzajPaliwa(String rodzajPaliwa) {
        this.rodzajPaliwa = rodzajPaliwa;
    }

    public String getMoc() {
        return moc;
    }

    public void setMoc(String moc) {
        this.moc = moc;
    }

    public String getSkrzyniaBiegow() {
        return skrzyniaBiegow;
    }

    public void setSkrzyniaBiegow(String skrzyniaBiegow) {
        this.skrzyniaBiegow = skrzyniaBiegow;
    }

    public String getNaped() {
        return naped;
    }

    public void setNaped(String naped) {
        this.naped = naped;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getLiczbaDrzwi() {
        return liczbaDrzwi;
    }

    public void setLiczbaDrzwi(String liczbaDrzwi) {
        this.liczbaDrzwi = liczbaDrzwi;
    }

    public String getLiczbaMiejsc() {
        return liczbaMiejsc;
    }

    public void setLiczbaMiejsc(String liczbaMiejsc) {
        this.liczbaMiejsc = liczbaMiejsc;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMetalik() {
        return metalik;
    }

    public void setMetalik(String metalik) {
        this.metalik = metalik;
    }

    public String getBezwypadkowy() {
        return bezwypadkowy;
    }

    public void setBezwypadkowy(String bezwypadkowy) {
        this.bezwypadkowy = bezwypadkowy;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPriceDetails() {
        return priceDetails;
    }

    public void setPriceDetails(String priceDetails) {
        this.priceDetails = priceDetails;
    }
}
