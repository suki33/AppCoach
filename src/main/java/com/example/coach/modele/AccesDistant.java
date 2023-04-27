package com.example.coach.modele;

import android.util.Log;

import com.example.coach.controleur.Controle;
import com.example.coach.outils.AccesHTTP;
import com.example.coach.outils.AsyncResponse;
import com.example.coach.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class AccesDistant  implements AsyncResponse {

    //constante
    private static final String SERVERADDR = "http://192.168.1.17/coach/serveurcoach.php";
    private Controle controle;
    /**
     * constructeur
     */
    public AccesDistant(){
        controle = Controle.getInstance(null);
    }

    /**
     * retour du serveur distant
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "*******************"+output);
        //decoupage du message recu avec %
        String[] message = output.split("%");
        //dans message[0] : "enreg", "dernier", "Erreur"
        //dans message [1] : reste du message
        //s'il y a 2 cases
        if(message.length>1){
            if(message[0].equals("enreg")){
                Log.d("enreg", "****************"+message[1]);
            }else{
                if(message[0].equals("dernier")){
                    Log.d("dernier", "****************"+message[1]);
                    try {
                        JSONObject info = new JSONObject(message[1]);
                        Integer poids = info.getInt("poids");
                        Integer taille = info.getInt("taille");
                        Integer age= info.getInt("age");
                        Integer sexe= info.getInt("sexe");
                        String datemesure = info.getString("datemesure");
                        Date date = MesOutils.convertStringToDate(datemesure, "yyyy-MM-dd hh:mm:ss");
                        Log.d("date mysql", "****************retour mysql :  "+date);
                        Profil profil = new Profil(date, poids, taille, age, sexe);
                        controle.setProfil(profil);

                    } catch (JSONException e) {
                        Log.d("erreur", "conversion JSON impossible"+e.toString());
                    }
                }else{
                    if(message[0].equals("Erreur")){
                        Log.d("Erreur", "****************"+message[1]);
                    }
                }
            }
        }

    }
    public void envoi(String operation, JSONArray lesDonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();
        //lien de delegation
        accesDonnees.delegate = this;
        //ajout parametres
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        //appel au serveur
        accesDonnees.execute(SERVERADDR);
    }

}
