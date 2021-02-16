package com.eamosse.gmail.gestion.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.eamosse.gmail.gestion.myapplication.R;
import com.eamosse.gmail.gestion.myapplication.dto.Article;
import com.eamosse.gmail.gestion.myapplication.viewmodels.ArticleViewModel;

import java.util.List;

// TODO Renommer ce fragment en ListArticleFragment -- attention, il faudra renommer aussi le layout pour respecter la convention de nommage
// TODO Utiliser le data binding
// TODO Modifier le layout pour y ajouter :
//  1° Un textview invitant l'utilisateur à présenter son tag NFC afin de lire le code pays qui sera envoyé au web service
//  2° un recycler view qui sera utilisé pour afficher la liste des articles
//  3° Un textview pour afficher les erreurs (de lecture de tag, d'accès réseau, ...)
//
public class FirstFragment extends Fragment {
    // Instance du View Model
    ArticleViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Instancier le view model
        // L'instance d'un view model est gérée par le système. Pour la créer on utilise un
        // ViewModelProvider.
        // Le cycle de vue d'un view model peut être lié à une activité ou à un fragment
        // Le paramètre du ViewModelProvider détermine le composant qui gère son cycle de vie.
        // Ici, on est dans un fragment, le mot clef this fait référence à l'instance du fragment et
        // par conséquent le cycle de vie est lié au fragment.
        // Il est possible de rattacher le view model à l'instance de l'activité. Pour cela, on remplacerait
        // this par getActivity().
        // Par contre, si on est dans une activité, this traduirait l'instance de l'activité et
        // le view model serait lié à l'activité.

        // Aussi, on rappelle que l'instance du view model est gérée par le système; ainsi quand on
        // fait un get sur le view model le système peut faire 2 choses :
        // a) si une instance existe pour le owner, le système renvoi cette instance
        // b) si aucune instance existe, le système en crée une nouvelle et la renvoi

        // Un view model est automatiquement détruit quand son owner est détruit, c'est encore le
        // système qui gère cela.
        viewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Ici on observe le live data qui renvoi la liste des articles.
        // Observer un live data revient à s'abonner aux changements sur les données gérées par le
        // live data. Cela signifie qu'à chaque fois que les données changent, la méthode de l'observer
        // sera appelé automatiquement.

        // Aussi, on observe un live data sur un gestionnaire de cycle de vie, ici on utilise getViewLifecycleOwner()
        // Cela garantit que l'observation s'arrêtera automatiquement dès que l'instance se détruit.
        // Ceci est très utile pour la gestion de la mémoire.

        // Quand on commence à observer un live data, la méthode ne fait rien tant qu'il n'y a pas de données
        // Si des données existent déjà, la méthode s'exécute toute de suite.
        // Le callback d'un live data s'exécute sur le thread principal

        viewModel.getListArticle().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            /*
             * Cette méthode est automatiquement appelée quand les données changent
             */
            public void onChanged(List<Article> articles) {
                //TODO : Afficher les données dans le recycler view, inspirez-vous du TP Entre Voisins
                //TODO Quand l'utilisateur clique sur un article, afficher le fragment DetailArticleFragment
                // TODO Enlever le toast
                Toast.makeText(getContext(), "Liste d'articles " + articles.size(), Toast.LENGTH_SHORT).show();
            }
        });
        // Ici on appele la méthode qui charge la liste des données et qui mettra à jour la liste
        // TODO Modifier ce fragment pour récupérer le code du pays dans un tag NFC - Il faut utiliser le code iso des pays en, fr, gb etc...

        viewModel.getArticles("fr");
        // TODO A supprimer
        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.removeArticle();
            }
        });
    }
}