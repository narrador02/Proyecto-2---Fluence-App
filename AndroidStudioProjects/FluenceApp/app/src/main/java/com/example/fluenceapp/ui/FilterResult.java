package com.example.fluenceapp.ui;

import java.util.List;

public class FilterResult {
    // Influencer
    public List<String> collabTypes;
    public FiltersBottomSheet.Pair<Float, Float> budget;
    public String duration;
    public String publishDate;
    public List<String> languages;
    // Empresa
    public FiltersBottomSheet.Pair<Float, Float> followers;
    public List<String> platforms;
    public List<String> sectors;
    public String location;
    public FiltersBottomSheet.Pair<Float, Float> engagement;
    public boolean available;
}