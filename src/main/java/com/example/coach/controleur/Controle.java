package com.example.coach.controleur;

import android.content.Context;
import android.util.Log;

import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;
import com.example.coach.vue.MainActivity;

import org.json.JSONArray;

import java.util.Date;

public final class Controle {

    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofil" ;
    //private static AccesLocal  accesLocal;
    private  static AccesDistant accesDistant;
    private static Context contexte;
    /**
     * constructeur private
     */
    private Controle(){
        super();
    }

    /**
     * Creation de l'instance
     * @return instance
     */
    public static final Controle getInstance(Context contexte){
        if(contexte != null){
            Controle.contexte = contexte;
        }
        if(Controle.instance == null) {
            Controle.instance = new Controle();
            //accesLocal = new AccesLocal(contexte);
            accesDistant = new AccesDistant();
           // profil = accesLocal.recupDernier();
            accesDistant.envoi("dernier", new JSONArray());
           // recupSerialize(contexte);
        }
        return Controle.instance;
    }

    /**
     *Creation du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme et 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context contexte){
        profil= new Profil(new Date(), poids, taille, age, sexe);
        Log.d("date", "*******************"+(new Date()));
       // accesLocal.ajout(profil);
        accesDistant.envoi("enreg", profil.convertToJSONArray());
        //Serializer.serialize(nomFic, profil, contexte);
    }



    public void setProfil(Profil profil){
        Controle.profil = profil;
        ((MainActivity) contexte).recupProfil();
    }


    /**
     * recuperation de l'img de profil
     * @return
     */
    public float getImg(){
        return profil.getImg();
    }

    /**
     *recuperation message de profil
     * @return message
     */
    public String getMessage(){
        return profil.getMessage();
    }


    /**
     * receuperation de l'objet serialise (le profil)
     * @param contexte
     */
    private static void recupSerialize(Context contexte){
        profil = (Profil) Serializer.deSerialize(nomFic, contexte);
    }
    public Integer getPoids(){
        if (profil == null){
            return null;
        }else{
            return profil.getPoids();
        }
    }

    public Integer getTaille(){
        if (profil == null){
            return null;
        }else{
            return profil.getTaille();
        }
    }

    public Integer getAge(){
        if (profil == null){
            return null;
        }else{
            return profil.getAge();
        }
    }

    public Integer getSexe(){
        if (profil == null){
            return null;
        }else{
            return profil.getSexe();
        }
    }
}
