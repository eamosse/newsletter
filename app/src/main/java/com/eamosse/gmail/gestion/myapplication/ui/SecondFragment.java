package com.eamosse.gmail.gestion.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.eamosse.gmail.gestion.myapplication.R;

/*
Classe qui affiche le détail d'un article passé en paramètre
TODO Le paramètre doit être passé ans le bundle du fragment
TODO Renommer cette classe en DetailArticleFragment et modifier son contenu pour afficher le détail
d'un article  (Titre, Description, Auteur, ...) Et un lien permettant de lire l'intégralité de l'article
dans une page web
TODO Modifier aussi le layout associé
TODO Créer un view model qui permettra de récupérer l'article sélectionné dans le repository
 */
public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}