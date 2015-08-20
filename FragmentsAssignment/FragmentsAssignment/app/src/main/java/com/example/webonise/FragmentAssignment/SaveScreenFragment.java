package com.example.webonise.FragmentAssignment;





import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SaveScreenFragment extends Fragment implements PersonListAdapter.CallBack, View.OnClickListener {

    EditText etName;
    EditText etAge;
    EditText etWeight;
    EditText etHeight;
    Button btnSave, btnClear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save_screen, container, false);
        etName = (EditText) view.findViewById(R.id.etName);
        etAge = (EditText) view.findViewById(R.id.etAge);
        etHeight = (EditText) view.findViewById(R.id.etHeight);
        etWeight = (EditText) view.findViewById(R.id.etWeight);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnClear = (Button) view.findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSave:
                String name = etName.getText().toString();
                String age = etAge.getText().toString();
                String height = etHeight.getText().toString();
                String weight = etWeight.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    etName.setError(getString(R.string.nameError));
                } else if (TextUtils.isEmpty(etAge.getText().toString())) {
                    etAge.setError(getString(R.string.ageError));
                } else if (TextUtils.isEmpty(height)) {
                    etHeight.setError(getString(R.string.heightError));
                } else if (TextUtils.isEmpty(weight)) {
                    etWeight.setError(getString(R.string.weightError));
                } else {
                    DetailsDBAdapter personDatabaseHelper = new DetailsDBAdapter(getActivity());
                    Details personDetails = new Details();
                    personDetails.setName(name);
                    personDetails.setHeight(height);
                    personDetails.setWeight(weight);
                    personDetails.setAge(age);
                    personDatabaseHelper.createDetails(personDetails);
                    Toast.makeText(getActivity(), Constants.TOAST_DB_UPDATE, Toast.LENGTH_LONG).show();
                    personDatabaseHelper.close();
                    HomeScreenFragment fragment = new HomeScreenFragment();
                    fragment.onUpgrade();
                }
                break;
            case R.id.btnClear:
                  /*First Approach*/
                etName.getText().clear();
                etAge.getText().clear();
                etHeight.getText().clear();
                etWeight.getText().clear();
                break;
        }
    }

    @Override
    public void onDelete() {

    }


}
