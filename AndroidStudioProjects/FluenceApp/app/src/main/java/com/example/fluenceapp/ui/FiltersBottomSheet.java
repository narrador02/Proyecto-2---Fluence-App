package com.example.fluenceapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.fluenceapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.slider.RangeSlider;
import java.util.*;

public class FiltersBottomSheet extends BottomSheetDialogFragment {

    public enum UserType { INFLUENCER, EMPRESA }

    private UserType userType;
    private OnApplyListener onApplyListener;
    private LinearLayout filtersContainer;
    private final Map<String, View> refs = new HashMap<>();

    public interface OnApplyListener {
        void onApply(FilterResult result);
    }

    public FiltersBottomSheet(UserType userType, OnApplyListener listener) {
        this.userType = userType;
        this.onApplyListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_filters, container, false);
        filtersContainer = view.findViewById(R.id.filters_container);
        setupFilters(requireContext());
        view.findViewById(R.id.btn_apply_filters).setOnClickListener(v -> {
            if (onApplyListener != null) {
                onApplyListener.onApply(collectFilters());
            }
            dismiss();
        });
        return view;
    }

    private void setupFilters(Context context) {
        filtersContainer.removeAllViews();
        if (userType == UserType.INFLUENCER) {
            addMultiSelect(context, "Tipo de colaboración", "collab_type", Arrays.asList("Pago fijo", "Comisión por venta", "Intercambio de producto"));
            addSlider(context, "Presupuesto estimado (€)", "budget", 0f, 10000f, 0f, 10000f, "€");
            addDropdown(context, "Duración de campaña", "duration", Arrays.asList("1 semana", "1 mes", "3 meses", "Puntual"));
            addDropdown(context, "Fecha de publicación", "publish_date", Arrays.asList("Hoy", "Última semana", "Último mes"));
            addMultiSelect(context, "Idioma requerido", "languages", Arrays.asList("Español", "Inglés", "Francés", "Alemán"));
        } else {
            addSlider(context, "Seguidores", "followers", 10000f, 500000f, 10000f, 500000f, "k");
            addMultiSelect(context, "Plataforma", "platforms", Arrays.asList("Instagram", "TikTok", "YouTube", "Twitch"));
            addMultiSelect(context, "Sector", "sectors", Arrays.asList("Moda", "Gaming", "Viajes", "Belleza", "Fitness", "Comida", "Educación"));
            addTextInput(context, "Ubicación", "location");
            addSlider(context, "Engagement rate (%)", "engagement", 0f, 100f, 0f, 100f, "%");
            addCheckbox(context, "Solo influencers disponibles", "available");
        }
    }

    private void addMultiSelect(Context context, String label, String key, List<String> options) {
        TextView tv = new TextView(context);
        tv.setText(label);
        ChipGroup chipGroup = new ChipGroup(context);
        chipGroup.setSingleSelection(false);
        for (String opt : options) {
            Chip chip = new Chip(context);
            chip.setText(opt);
            chip.setCheckable(true);
            chipGroup.addView(chip);
        }
        filtersContainer.addView(tv);
        filtersContainer.addView(chipGroup);
        refs.put(key, chipGroup);
    }

    private void addSlider(Context context, String label, String key, float min, float max, float start, float end, String suffix) {
        TextView tv = new TextView(context);
        tv.setText(label);
        RangeSlider slider = new RangeSlider(context);
        slider.setValueFrom(min);
        slider.setValueTo(max);
        slider.setValues(start, end);
        slider.setStepSize(suffix.equals("%") ? 1f : 1000f);
        TextView valuesTv = new TextView(context);
        List<Float> v = slider.getValues();
        valuesTv.setText(Math.round(v.get(0)) + suffix + " - " + Math.round(v.get(1)) + suffix);
        slider.addOnChangeListener((s, value, fromUser) -> {
            List<Float> vals = slider.getValues();
            valuesTv.setText(Math.round(vals.get(0)) + suffix + " - " + Math.round(vals.get(1)) + suffix);
        });
        filtersContainer.addView(tv);
        filtersContainer.addView(slider);
        filtersContainer.addView(valuesTv);
        refs.put(key, slider);
    }

    private void addDropdown(Context context, String label, String key, List<String> options) {
        TextView tv = new TextView(context);
        tv.setText(label);
        Spinner spinner = new Spinner(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, options);
        spinner.setAdapter(adapter);
        filtersContainer.addView(tv);
        filtersContainer.addView(spinner);
        refs.put(key, spinner);
    }

    private void addTextInput(Context context, String label, String key) {
        TextView tv = new TextView(context);
        tv.setText(label);
        EditText et = new EditText(context);
        et.setHint(label);
        filtersContainer.addView(tv);
        filtersContainer.addView(et);
        refs.put(key, et);
    }

    private void addCheckbox(Context context, String label, String key) {
        CheckBox cb = new CheckBox(context);
        cb.setText(label);
        filtersContainer.addView(cb);
        refs.put(key, cb);
    }

    private FilterResult collectFilters() {
        FilterResult result = new FilterResult();
        if (userType == UserType.INFLUENCER) {
            result.collabTypes = getSelectedChips("collab_type");
            result.budget = getSelectedSlider("budget");
            result.duration = getSelectedDropdown("duration");
            result.publishDate = getSelectedDropdown("publish_date");
            result.languages = getSelectedChips("languages");
        } else {
            result.followers = getSelectedSlider("followers");
            result.platforms = getSelectedChips("platforms");
            result.sectors = getSelectedChips("sectors");
            result.location = getSelectedText("location");
            result.engagement = getSelectedSlider("engagement");
            result.available = getSelectedCheckbox("available");
        }
        return result;
    }

    public FilterResult getSelectedFilters() {
        return collectFilters();
    }

    private List<String> getSelectedChips(String key) {
        ChipGroup group = (ChipGroup) refs.get(key);
        if (group == null) return Collections.emptyList();
        List<String> selected = new ArrayList<>();
        for (int i = 0; i < group.getChildCount(); i++) {
            Chip chip = (Chip) group.getChildAt(i);
            if (chip.isChecked()) selected.add(chip.getText().toString());
        }
        return selected;
    }

    private Pair<Float, Float> getSelectedSlider(String key) {
        RangeSlider slider = (RangeSlider) refs.get(key);
        if (slider == null) return new Pair<>(0f, 0f);
        List<Float> v = slider.getValues();
        return new Pair<>(v.get(0), v.get(1));
    }

    private String getSelectedDropdown(String key) {
        Spinner spinner = (Spinner) refs.get(key);
        if (spinner == null) return "";
        Object item = spinner.getSelectedItem();
        return item != null ? item.toString() : "";
    }

    private String getSelectedText(String key) {
        EditText et = (EditText) refs.get(key);
        if (et == null) return "";
        return et.getText().toString();
    }

    private boolean getSelectedCheckbox(String key) {
        CheckBox cb = (CheckBox) refs.get(key);
        return cb != null && cb.isChecked();
    }

    public static class Pair<A, B> {
        public final A first;
        public final B second;
        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }
}