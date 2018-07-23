package com.concept.otomoto.data;

import java.util.ArrayList;
import java.util.List;

public abstract class DataCollectorSettings {

    protected static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/45.0.2454.101 Safari/537.36";
    protected static final String REFERRER = "http://www.google.com";

    protected List<String> paginationList = new ArrayList<>();
    protected List<String> carOffersList = new ArrayList<>();
}
