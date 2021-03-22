package com.example.restbox.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restbox.R;
import com.example.restbox.activities.EmployeeListActivity;
import com.example.restbox.model.RestboxModel;
import com.example.restbox.objects.Person;

import java.util.ArrayList;

public class PersonAdapter extends BaseAdapter {

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    Context context;
    private static LayoutInflater inflater = null;
    private final RestboxModel model = RestboxModel.getInstance();

    public PersonAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return model.getPeople().size();
    }

    @Override
    public Object getItem(int position) {
        return model.getPeople().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null)
            view = inflater.inflate(R.layout.row_data, null);

        TextView name = view.findViewById(R.id.personname);
        TextView function = view.findViewById(R.id.personFunction);
        ImageView image = view.findViewById(R.id.imageView);
        CheckBox cb = view.findViewById(R.id.checkBox);

        name.setText(model.getPeople().get(position).getName());
        function.setText(model.getPeople().get(position).getFunction());
        image.setImageResource(R.drawable.user);

        cb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Person p = (Person) getItem(position);
            if (isChecked) {
                model.addToExport(p);
            }
            if (!isChecked) {
                model.removeFromExport(p);
            }
        });


        return view;
    }


}
