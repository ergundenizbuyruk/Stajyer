package com.ergundenizbuyruk.stajyer;

import android.os.Bundle;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GirisFragment extends Fragment {
    public GirisFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_giris, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button ogrenciButton = view.findViewById(R.id.ogrenciButonu);
        Button ogretmenButonu = view.findViewById(R.id.ogretmenButonu);

        ogrenciButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentOgrenciKayitaGit(v);
            }
        });

        ogretmenButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentOgretmenKayitaGit(v);
            }
        });
    }

    public void fragmentOgrenciKayitaGit(View view) {
        NavDirections action = GirisFragmentDirections.actionGirisFragmentToOgrenciGirisFragment();
        Navigation.findNavController(view).navigate(action);
    }

    public void fragmentOgretmenKayitaGit(View view) {
        NavDirections action = GirisFragmentDirections.actionGirisFragmentToOgretmenGirisFragment();
        Navigation.findNavController(view).navigate(action);

    }
}