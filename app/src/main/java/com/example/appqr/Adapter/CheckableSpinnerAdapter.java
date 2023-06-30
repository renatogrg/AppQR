package com.example.appqr.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.appqr.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckableSpinnerAdapter extends ArrayAdapter<String> {
    private List<String> items;
    private List<String> ids;
    private Map<String, Boolean> checkedItems;

    public CheckableSpinnerAdapter(Context context, int resource, List<String> items, List<String> ids) {
        super(context, resource, items);
        this.items = items;
        this.ids = ids;
        checkedItems = new HashMap<>();
        for (String item : items) {
            checkedItems.put(item, false);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(items.get(position));

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.spinner_item, parent, false);
        }

        TextView textViewNombre = view.findViewById(R.id.txtNombre);
        textViewNombre.setText(items.get(position));

        TextView textViewCodigo = view.findViewById(R.id.txtCodigo);
        textViewCodigo.setText(ids.get(position));

        CheckBox checkBox = view.findViewById(R.id.checkbox);
        checkBox.setChecked(checkedItems.get(items.get(position)));
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) view;
                checkedItems.put(items.get(position), cb.isChecked());
            }
        });

        return view;
    }

    public List<String> getCheckedItems() {
        List<String> checkedItemsList = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : checkedItems.entrySet()) {
            if (entry.getValue()) {
                checkedItemsList.add(entry.getKey());
            }
        }
        return checkedItemsList;
    }
}
