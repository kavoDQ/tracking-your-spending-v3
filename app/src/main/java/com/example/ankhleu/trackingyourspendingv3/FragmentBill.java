package com.example.ankhleu.trackingyourspendingv3;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ankhleu.trackingyourspendingv3.tripdata.Adapter_tripshow;
import com.example.ankhleu.trackingyourspendingv3.tripdata.tripDAO;
import com.example.ankhleu.trackingyourspendingv3.tripdata.tripDB;
import com.example.ankhleu.trackingyourspendingv3.tripdata.tripDetail;
import com.example.ankhleu.trackingyourspendingv3.tripdata.tripDetailDAO;
import com.example.ankhleu.trackingyourspendingv3.tripdata.tripadd;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentBill.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentBill#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBill extends Fragment implements View.OnClickListener
{
    tripDB tdb;
    SQLiteDatabase db;
    FloatingActionButton fb1,fb2,fb3;
//    ListView lv;
    tripDB dbhelper;
    String idStr,titleStr, startdateStr ,enddateStr,budgetStr ;
    private Cursor result;
    private ArrayList<String> trip =new ArrayList<String>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ViewGroup rootView;


    public FragmentBill() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBill.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBill newInstance(String param1, String param2) {
        FragmentBill fragment = new FragmentBill();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    fb1=(FloatingActionButton)getActivity().findViewById(R.id.floatingActionButton3) ;
        // 要用getactivity,fragment生命週期在onActivityCreated之後

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }





    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View vi = inflater.inflate(R.layout.fragment_bill, container, false);
        ListView lv = vi.findViewById(R.id.listview);
        ArrayList<String> tripidd = tripnames();

        tdb = new tripDB(getActivity(), "title", null, 1);
        Adapter_tripshow adapterTripidd = new Adapter_tripshow(tripidd, getActivity());
        lv.setAdapter(adapterTripidd);

        return vi;
        }


        boolean connectToDatabase()
        {
            db = tdb.getWritableDatabase();
            if (db != null) {
                return true;
            }
            return false;
        }



        ArrayList<String> tripnames()
        {
            ArrayList<String> tripid = new ArrayList<>();
            if(connectToDatabase())
            {
                Cursor cursor = db.rawQuery("SELECT Title FROM trip",null);
                if(cursor.moveToFirst())
                {
                    do{
                        tripid.add(cursor.getString(0));
                    } while (cursor.moveToNext());
                }
                db.close();
            }
            return tripid;
        }

//        fb1=(FloatingActionButton)view.findViewById(R.id.floatingActionButton);
//        fb1.setOnClickListener(this);
//        return view;

//        View mview = inflater.inflate(R.layout.fragment_bill, container, false);
//        ListView listView = mview.findViewById(R.id.listview); //list的實體化
//        dbhelper = new tripDB(mview.getContext()); //dbHelper的實體化
//        db = dbhelper.getReadableDatabase(); //讀取
//        String sql = "select _id, Title, startdate, enddate,budget from trip where _id='' order by _id";
//        result = db.rawQuery(sql, null);
//        int resultCounts = result.getCount();
//        if (resultCounts == 0 || !result.moveToFirst()) {
//            Toast.makeText(getActivity(), "無資料", Toast.LENGTH_SHORT).show();
//        } else {
//            while (!result.isAfterLast()) {
//                idStr = String.valueOf(result.getInt(result.getColumnIndex("id"))) + "\t";
//                titleStr = result.getString(result.getColumnIndex("Title")) + ":\t";
//                startdateStr = result.getString(result.getColumnIndex("startdate")) + "\t";
//                enddateStr = result.getString(result.getColumnIndex("enddate")) + "\t";
//                budgetStr = result.getString(result.getColumnIndex("budget"));
//                trip.add(idStr + titleStr + startdateStr + enddateStr + budgetStr);
//                result.moveToNext();
//            }
//        }
//        listView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.fragment_bill, trip));
//        db.close();
//        return mview;
//
//        return inflater.inflate(R.layout.fragment_bill, container, false);

        // Inflate the layout for this fragment



/*
    rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fragment_bill, container, false);
        Button fb1 = (Button) rootView.findViewById(R.id.floatingActionButton);
        fb1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        return rootView;        //此段目前先不用
*/


    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       fb1 = (FloatingActionButton) getActivity().findViewById(R.id.floatingActionButton3);
        fb2 = (FloatingActionButton) getActivity().findViewById(R.id.floatingActionButton2);
        fb3 = (FloatingActionButton) getActivity().findViewById(R.id.floatingActionButton);


        fb1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent it = new Intent(getActivity(), Newstart.class);
                        //new Intent(getActivity(),StartactActivity.class);
                        startActivity(it);
                    }
                });
        fb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(getActivity(), Tripadddetail.class);
                startActivity(it2);
            }
        });

 /*       fb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

*/
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

        if (mListener != null) {
            mListener.onFragmentInteraction(uri);

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//           throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

    }


/*
    private void startActivity(FloatingActionButton fb1) {

    }
*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
