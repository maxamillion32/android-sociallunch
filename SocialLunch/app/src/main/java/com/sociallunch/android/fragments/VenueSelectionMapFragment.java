package com.sociallunch.android.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sociallunch.android.R;
import com.sociallunch.android.models.Venue;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VenueSelectionMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VenueSelectionMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VenueSelectionMapFragment extends MapFragment {

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment VenueSelectionMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VenueSelectionMapFragment newInstance() {
        return new VenueSelectionMapFragment();
    }

    public VenueSelectionMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement VenueSelectionMapFragment.OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
    }

    public void updateItems(ArrayList<Venue> venues) {
        if (map != null) {
            map.clear();
            if (!venues.isEmpty()) {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                int length = venues.size();
                for (int i = 0 ; i < length ; i++) {
                    Venue venue = venues.get(i);
                    MarkerOptions markerOpts = new MarkerOptions()
                            .title(venue.name)
                            .position(venue.coordinate)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_venue));
                    markerOpts.snippet(venue.displayAddress);
                    map.addMarker(markerOpts);
                    builder.include(venue.coordinate);
                }
                LatLngBounds bounds = builder.build();
                int padding = getResources().getInteger(R.integer.lm_map_bounds_padding);
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                map.moveCamera(cu);
            }
        }
    }
}
