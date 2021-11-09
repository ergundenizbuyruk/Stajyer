package com.ergundenizbuyruk.stajyer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ergundenizbuyruk.stajyer.databinding.FragmentOgretmenGirisBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OgretmenGirisFragment extends Fragment {

    private FragmentOgretmenGirisBinding binding;
    private FirebaseAuth auth;
    private String ePosta;
    private String sifre;

    public OgretmenGirisFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment with view Binding
        binding = FragmentOgretmenGirisBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.girisButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ePosta = binding.epostaEditText.getText().toString();
                sifre = binding.sifreEditText.getText().toString();

                if (ePosta.equals("") || sifre.equals("")) {
                    Toast.makeText(getContext(),
                            R.string.email_veya_sifre_bos_toasti, Toast.LENGTH_SHORT).show();
                } else {
                    girisYap(v, ePosta, sifre);
                }

            }
        });

        binding.kayitOlaGitButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kayitOlaGit(v);
            }
        });

        binding.sifreniMiUnuttunTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), R.string.sifreni_mi_unuttun_toast_mesaji, Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void girisYap(View view, String ePosta, String sifre) {

        auth.signInWithEmailAndPassword(ePosta, sifre)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                OgretmenAnaSayfayaGit();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void kayitOlaGit(View view) {
        NavDirections action = OgretmenGirisFragmentDirections
                .actionOgretmenGirisFragmentToOgretmenKayitFragment();
        Navigation.findNavController(view).navigate(action);
    }

    public void OgretmenAnaSayfayaGit() {
        Intent intent = new Intent(getContext(), OgretmenAnaSayfa.class);
        startActivity(intent);
        try {
            getActivity().finish();
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}