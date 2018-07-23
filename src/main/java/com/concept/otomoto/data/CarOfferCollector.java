package com.concept.otomoto.data;

import com.concept.otomoto.model.CarOffer;
import com.concept.otomoto.service.CarOfferService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarOfferCollector extends DataCollectorSettings {

    @Autowired
    CarOfferService carOfferService;

    private String findLastPaginationNumber() throws Exception {
        Document paginationPage = Jsoup.connect("https://www.otomoto.pl/osobowe/nowe/?search%5Bcountry%5D=&l=1")
                .proxy("10.51.55.34", 8080)
                .userAgent(USER_AGENT)
                .referrer(REFERRER)
                .timeout(12000)
                .followRedirects(true)
                .get();

        Elements pagination = paginationPage
                .select("span.page");
        String lastPaginationNumber = pagination.last().text();
        System.out.println(lastPaginationNumber);
        return lastPaginationNumber;
    }

    private void fillPaginationList() throws Exception {
        int lastPaginationNumber = Integer.parseInt(findLastPaginationNumber());
        for (int i = 1; i <= lastPaginationNumber; i++) {
            paginationList.add("https://www.otomoto.pl/osobowe/nowe/?search%5Bcountry%5D=&l=1&page=" + i);
        }
        System.out.println(paginationList.toString());
    }

    public void collectLinks() throws Exception {
        fillPaginationList();
        for (int i = 0; i < paginationList.size(); i++) {
            Document linkCollection = Jsoup.connect(paginationList.get(i))
                    .proxy("10.51.55.34", 8080)
                    .userAgent(USER_AGENT)
                    .referrer(REFERRER)
                    .timeout(12000)
                    .followRedirects(true)
                    .get();
            Elements content = linkCollection.select("div.offers.list");
            Elements url = content.select("div.offer-item__title>h2.offer-title");
            for (Element element : url) {
                String link = element
                        .select("a.offer-title__link")
                        .attr("href");
                carOffersList.add(link);
                System.out.println(link);
            }
        }
//        removeDuplicatesFromList();

    }

    public void collectTestLinks() throws Exception {
        Document linkCollection = Jsoup.connect(
                "https://www.otomoto.pl/osobowe/nowe/?search%5Bcountry%5D=&l=&page=3")
                .proxy("10.51.55.34", 8080)
                .userAgent(USER_AGENT)
                .referrer(REFERRER)
                .timeout(12000)
                .followRedirects(true)
                .get();
        Elements content = linkCollection.select("div.offers.list");
        Elements url = content.select("div.offer-item__title>h2.offer-title");
        for (Element element : url) {
            String link = element
                    .select("a.offer-title__link")
                    .attr("abs:href");

            System.out.println(link);
        }
    }

    public void collectData() throws Exception {
        collectLinks();
        for (int i = 0; i < carOffersList.size(); i++) {
            Document document = Jsoup.connect(carOffersList.get(i))
                    .proxy("10.51.55.34", 8080)
                    .userAgent(USER_AGENT)
                    .referrer(REFERRER)
                    .timeout(12000)
                    .ignoreHttpErrors(true)
                    .followRedirects(true)
                    .get();
            CarOffer carOffer = new CarOffer();
            Elements sellerBox = document.select("div.seller-box");
            for (Element element : sellerBox) {
                carOffer.setDealer(searchForDealer(element));
                carOffer.setAdress(searchForAdress(element));
            }
            Elements metaData = document.select("div.offer-meta");
            for (Element element : metaData) {
                carOffer.setDatePublished(searchForDatePublished(element));
                carOffer.setIdOffer(searchForIdOffer(element));
            }
            Elements offerHeader = document.select("div.offer-header__row");
            for (Element element : offerHeader) {
                carOffer.setPrice(searchForPrice(element));
                carOffer.setPriceDetails(searchForPriceDetails(element));
                carOffer.setCurrency(searchForCurrency(element));
                carOffer.setCarBrand(searchForCarBrand(element));
            }
            Elements offerParams = document.select("div.offer-params");
            for (Element element : offerParams) {
                carOffer.setOdertaOd(searchForOfertaOd(element));
                carOffer.setKategoria(searchForKategoria(element));
                carOffer.setMarkaPojazdu(searchForMarkaPojazdu(element));
                carOffer.setModelPojazdu(searchForModelPojazdu(element));
                carOffer.setWersja(searchForWersja(element));
                carOffer.setRokProdukcji(searchForRokProdukcji(element));
                carOffer.setPrzebieg(searchForPrzebieg(element));
                carOffer.setPojemnoscSkokowa(searchForPojemnoscSkokowa(element));
                carOffer.setRodzajPaliwa(searchForRodzajPaliwa(element));
                carOffer.setMoc(searchForMoc(element));
                carOffer.setSkrzyniaBiegow(searchForSkrzyniaBiegow(element));
                carOffer.setNaped(searchForNaped(element));
                carOffer.setTyp(searchforTyp(element));
                carOffer.setColor(searchForColor(element));
                carOffer.setMetalik(searchForMetalik(element));
                carOffer.setLiczbaDrzwi(searchForLiczbaDrzwi(element));
                carOffer.setLiczbaMiejsc(searchForLiczbaMiejsc(element));
                carOffer.setBezwypadkowy(searchForBezwypadkowy(element));
                carOffer.setStan(searchForStan(element));
            }
            carOfferService.save(carOffer);
        }
        System.out.println("KONIEC!");
    }

    public void collectTestData() throws Exception {
        Document document = Jsoup.connect
                ("https://www.otomoto.pl/oferta/volkswagen-transporter-t6-furgon-3400mm-2-0tdi-150-km-srebrny-metalik-2018-asokrotoskicichy-ID6zvb2j.html#2bcc049572")
                .proxy("10.51.55.34", 8080)
                .userAgent(USER_AGENT)
                .referrer(REFERRER)
                .timeout(12000)
                .ignoreHttpErrors(true)
                .followRedirects(true)
                .get();
        CarOffer carOffer = new CarOffer();
        Elements sellerBox = document.select("div.seller-box");
        for (Element element : sellerBox) {
            carOffer.setDealer(searchForDealer(element));
            carOffer.setAdress(searchForAdress(element));
        }
        Elements metaData = document.select("div.offer-meta");
        for (Element element : metaData) {
            carOffer.setDatePublished(searchForDatePublished(element));
            carOffer.setIdOffer(searchForIdOffer(element));
        }
        Elements offerHeader = document.select("div.offer-header__row");
        for (Element element : offerHeader) {
            carOffer.setPrice(searchForPrice(element));
            carOffer.setPriceDetails(searchForPriceDetails(element));
            carOffer.setCurrency(searchForCurrency(element));
            carOffer.setCarBrand(searchForCarBrand(element));
        }
        Elements offerParams = document.select("div.offer-params");
        for (Element element : offerParams) {
            carOffer.setOdertaOd(searchForOfertaOd(element));
            carOffer.setKategoria(searchForKategoria(element));
            carOffer.setMarkaPojazdu(searchForMarkaPojazdu(element));
            carOffer.setModelPojazdu(searchForModelPojazdu(element));
            carOffer.setWersja(searchForWersja(element));
            carOffer.setRokProdukcji(searchForRokProdukcji(element));
            carOffer.setPrzebieg(searchForPrzebieg(element));
            carOffer.setPojemnoscSkokowa(searchForPojemnoscSkokowa(element));
            carOffer.setRodzajPaliwa(searchForRodzajPaliwa(element));
            carOffer.setMoc(searchForMoc(element));
            carOffer.setSkrzyniaBiegow(searchForSkrzyniaBiegow(element));
            carOffer.setNaped(searchForNaped(element));
            carOffer.setTyp(searchforTyp(element));
            carOffer.setColor(searchForColor(element));
            carOffer.setMetalik(searchForMetalik(element));
            carOffer.setLiczbaDrzwi(searchForLiczbaDrzwi(element));
            carOffer.setLiczbaMiejsc(searchForLiczbaMiejsc(element));
            carOffer.setBezwypadkowy(searchForBezwypadkowy(element));
            carOffer.setStan(searchForStan(element));
        }
        carOfferService.save(carOffer);

    }

    private String searchForCarBrand(Element element) {
        String carBrand = element.select("h1.offer-title.big-text").text();
        return carBrand;
    }

    private String searchForStan(Element element) {
        String stan = element.getElementsContainingOwnText("Bezwypadkowy").next().text();
        return stan;
    }

    private String searchForBezwypadkowy(Element element) {
        String bezwypadkowy = element.getElementsContainingOwnText("Bezwypadkowy").next().text();
        return bezwypadkowy;
    }

    private String searchForLiczbaMiejsc(Element element) {
        String miejsca = element.getElementsContainingOwnText("Liczba miejsc").next().text();
        return miejsca;
    }

    private String searchForLiczbaDrzwi(Element element) {
        String drzwi = element.getElementsContainingOwnText("Liczba drzwi").next().text();
        return drzwi;
    }

    private String searchForMetalik(Element element) {
        String metalik = element.getElementsContainingOwnText("Metalik").next().text();
        return metalik;
    }

    private String searchForColor(Element element) {
        String color = element.getElementsContainingOwnText("Kolor").next().text();
        return color;
    }

    private String searchforTyp(Element element) {
        String typ = element.getElementsContainingOwnText("Typ").next().text();
        return typ;
    }

    private String searchForNaped(Element element) {
        String naped = element.getElementsContainingOwnText("Napęd").next().text();
        return naped;
    }

    private String searchForSkrzyniaBiegow(Element element) {
        String skrzynia = element.getElementsContainingOwnText("Skrzynia biegów").next().text();
        return skrzynia;
    }

    private String searchForMoc(Element element) {
        String moc = element.getElementsContainingOwnText("Moc").next().text();
        return moc;
    }

    private String searchForRodzajPaliwa(Element element) {
        String paliwo = element.getElementsContainingOwnText("Rodzaj paliwa").next().text();
        return paliwo;
    }

    private String searchForPojemnoscSkokowa(Element element) {
        String pojSkokowa = element.getElementsContainingOwnText("Pojemność skokowa").next().text();
        return pojSkokowa;
    }

    private String searchForPrzebieg(Element element) {
        String przebieg = element.getElementsContainingOwnText("Przebieg").next().text();
        return przebieg;
    }

    private String searchForRokProdukcji(Element element) {
        String rokProdukcji = element.getElementsContainingOwnText("Rok produkcji").next().text();
        return rokProdukcji;
    }

    private String searchForWersja(Element element) {
        String wersja = element.getElementsContainingOwnText("Wersja").next().text();
        return wersja;
    }

    private String searchForModelPojazdu(Element element) {
        String model = element.getElementsContainingOwnText("Model pojazdu").next().text();
        return model;
    }

    private String searchForMarkaPojazdu(Element element) {
        String marka = element.getElementsContainingOwnText("Marka pojazdu").next().text();
        return marka;
    }

    private String searchForKategoria(Element element) {
        String kategoria = element.getElementsContainingOwnText("Kategoria").next().text();
        return kategoria;
    }

    private String searchForOfertaOd(Element element) {
        String ofertaOd = element.getElementsContainingOwnText("Oferta od").next().text();
        return ofertaOd;
    }

    private String searchForCurrency(Element element) {
        String currency = element.select("div.offer-price>span.offer-price__number>span.offer-price__currency").text();
        return currency;
    }

    //    TODO - zostaw tylko cene netto/brutto
    private String searchForPriceDetails(Element element) {
        String priceDetails = element.select("div.offer-price>span.offer-price__details").text();
        return priceDetails;
    }

    private String searchForPrice(Element element) {
        String price = element.select("div.offer-price>span.offer-price__number")
                .text();
        return price;
    }

    private String searchForIdOffer(Element element) {
        String idOffer = element.select("span.offer-meta__item>span.offer-meta__value").last().text();
        return idOffer;
    }

    //    TODO - godziny wywal
    private String searchForDatePublished(Element element) {
        String datePublished = element.select("span.offer-meta__item>span.offer-meta__value").first().text();
        return datePublished;
    }

    private String searchForAdress(Element element) {
        String adress = element.select("div.seller-box__seller-address").text();
        return adress;
    }

    private String searchForDealer(Element element) {
        String dealer = element.select("h2.seller-box__seller-name").text();
        return dealer;
    }
}



