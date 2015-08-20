package com.example.webonise.FragmentAssignment;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


public class HomeScreenFragment extends Fragment implements PersonListAdapter.CallBack,View.OnClickListener{

    Button btnSave;
    ListView listView;
    private PersonListAdapter listAdapter;
    DetailsDBAdapter detailsDBAdapter;

    public void HomeScreenFragment() {
        listView =null;
        listAdapter = null;
        detailsDBAdapter = null;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        detailsDBAdapter = new DetailsDBAdapter(this.getActivity());
        listAdapter = new PersonListAdapter(this.getActivity(), R.layout.list_item, detailsDBAdapter.fetchAllDetails(), this);
        listView = (ListView) getView().findViewById(R.id.lvDetails);
        listView.setAdapter(listAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        return view;
    }
    /*We created this interface*/
    @Override
    public void onDelete() {
        detailsDBAdapter = new DetailsDBAdapter(this.getActivity());
        listAdapter = new PersonListAdapter(this.getActivity(), R.layout.list_item, detailsDBAdapter.fetchAllDetails(), this);
        listView.setAdapter(listAdapter);
        }

    public void onUpgrade() {
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SaveScreenFragment saveScreenFragment = new SaveScreenFragment();
        /*Add animation*/

        fragmentTransaction.setCustomAnimations(R.anim.out_to_right, R.anim.out_to_left);
        /*We initially added now we'll replace*/
        fragmentTransaction.replace(R.id.fragment_container, saveScreenFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

