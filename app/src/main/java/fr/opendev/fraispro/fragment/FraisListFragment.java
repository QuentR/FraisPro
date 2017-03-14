package fr.opendev.fraispro.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import fr.opendev.fraispro.R;
import fr.opendev.fraispro.activity.LoginActivity;
import fr.opendev.fraispro.adapter.FirebaseFraisAdapter;
import fr.opendev.fraispro.adapter.FraisHolder;
import fr.opendev.fraispro.model.Frais;
import fr.opendev.fraispro.ui.ClickListener;
import fr.opendev.fraispro.ui.DividerItemDecoration;
import fr.opendev.fraispro.ui.RecyclerTouchListener;

import static fr.opendev.fraispro.utils.Constants.FRAIS_NODE;

/**
 * Created by Quentin on 13/03/2017.
 */

public class FraisListFragment extends Fragment {

    private RecyclerView recyclerView;
    private FirebaseFraisAdapter mAdapter;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseDatabase database;
    private DatabaseReference userData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get current user
        user = FirebaseAuth.getInstance().getCurrentUser();
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        userData = database.getReference(FRAIS_NODE).child(user.getUid());


        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                }
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fraislist, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mAdapter = new FirebaseFraisAdapter(Frais.class, R.layout.frais_item, FraisHolder.class, userData, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        // set the adapter
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, final int position) {
                Snackbar snackbar = Snackbar
                        .make(getView(), "Voulez-vous supprimer l'enregistrement?", Snackbar.LENGTH_LONG)
                        .setAction("Supprimer", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mAdapter.getRef(position).removeValue();
                            }
                        });
                snackbar.show();

            }
        }));

        return view;
    }
}