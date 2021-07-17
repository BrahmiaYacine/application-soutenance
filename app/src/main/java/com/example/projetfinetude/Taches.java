package com.example.projetfinetude;

import androidx.room.Entity;

import com.pentabin.livingroom.BasicEntity;
import com.pentabin.livingroom.annotations.Crudable;

@Crudable
@Entity
public class Taches extends BasicEntity {
    private String title;
    private String description;
    private String nom_position;
    private String longitude;
    private String latitude;

    public Taches(String title, String description, String nom_position, String longitude, String latitude) {
        this.title = title;
        this.description = description;
        this.nom_position = nom_position;
        this.latitude = latitude;
        this.longitude = longitude;

    }
    /*public String gettachelatitude() {
       // return latitude;
    }
    public String gettachelongitude() {
        return longitude;
    }

    public void settachelatitude(String latitude) {
        this.latitude = latitude;
    }
    public void settachelongitude(String longitude) { this.longitude = longitude;  }
*/





   // public int getPosition_Id() {  return id_position;    }
   // public void setPosition_Id() {  this.id_position=id_position;    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {   this.description = description;    }

    public String getNom_position() {
        return nom_position;
    }

    public void setNom_position(String nom_position) {
        this.nom_position = nom_position;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    /*public void setPosition_Id(int id_position) {
        this.id_position = id_position;
    }*/
/*
    public String getNom_position() {
        return nom_position;
    }

    public void setNom_position(String nom_position) {
        this.nom_position = nom_position;
    }*/
}

