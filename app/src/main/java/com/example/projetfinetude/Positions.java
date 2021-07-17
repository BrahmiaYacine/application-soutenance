package com.example.projetfinetude;

import androidx.room.Entity;

import com.pentabin.livingroom.BasicEntity;
import com.pentabin.livingroom.annotations.Crudable;
import com.pentabin.livingroom.annotations.SelectableById;

@Crudable
    @SelectableById
    @Entity
    public class Positions extends BasicEntity {
        private String Name;
        private String latitude;
        private String longitude;


        public Positions(String Name, String latitude, String longitude) {
            this.Name = Name;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }

