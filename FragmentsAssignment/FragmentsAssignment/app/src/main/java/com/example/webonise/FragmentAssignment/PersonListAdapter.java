package com.example.webonise.FragmentAssignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;


/**
 * Created by webonise on 14/8/15.
 */
public class PersonListAdapter extends BaseAdapter {
    List<Details> personDetailsList;
    Context context;
    /*Introduce new CallBack variable - then add it in constructor*/
    CallBack callBack;

    public PersonListAdapter(Context context, int layout, List<Details> personDetailsList, CallBack callBack) {
        this.callBack=callBack;
        this.context = context;
        this.personDetailsList = personDetailsList;
    }

    @Override
    public int getCount() {
        return personDetailsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {


        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        }
        final TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvAge = (TextView) view.findViewById(R.id.tvAge);

        TextView tvWeight = (TextView) view.findViewById(R.id.tvWeight);
        TextView tvHeight = (TextView) view.findViewById(R.id.tvHieght);
        ImageButton imgButton = (ImageButton) view.findViewById(R.id.deletor);

        tvName.setText(personDetailsList.get(position).getName());
        tvAge.setText(String.format(context.getString(R.string.formatedString), personDetailsList.get(position).getAge()));
        tvWeight.setText(personDetailsList.get(position).getWeight().toString());
        tvHeight.setText(personDetailsList.get(position).getHeight().toString());

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsDBAdapter personDatabaseHelper = new DetailsDBAdapter(context);
                int id = personDetailsList.get(position).getId();
                personDatabaseHelper.deleteCertainDetail(id);
                /*After - use the callback - Interface creation in  */
                callBack.onDelete();
                personDatabaseHelper.close();
                /*Before*/
                /*((ListViewActivity) context).onDeleted();*/
            }
        });
        return view;
    }


    public interface CallBack {
        void onDelete();
    }
}
