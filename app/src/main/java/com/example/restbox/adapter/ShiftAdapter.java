package com.example.restbox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restbox.R;
import com.example.restbox.model.RestboxModel;

public class ShiftAdapter extends BaseAdapter {

    Context context;
    private static LayoutInflater inflater = null;
    private final RestboxModel model = RestboxModel.getInstance();


    public ShiftAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return model.getQueue().size();
    }

    @Override
    public Object getItem(int position) {
        return model.getQueue().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.row_data_shift, null);
        }

        TextView name = view.findViewById(R.id.personnameShift);
        TextView function = view.findViewById(R.id.personFunctionShift);
        ImageView image = view.findViewById(R.id.imageViewShift);

        name.setText(model.getQueue().get(position).getName());
        function.setText(model.getQueue().get(position).getFunction());

        image.setImageResource(R.drawable.user);

        return view;
    }
}
