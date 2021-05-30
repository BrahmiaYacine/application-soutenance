package com.example.projetfinetude;

import androidx.room.Entity;

import com.pentabin.livingroom.BasicEntity;
import com.pentabin.livingroom.annotations.Crudable;
import com.pentabin.livingroom.annotations.SelectableWhere;

    @Crudable
    @SelectableWhere(methodName = "getArchived", where = "isDeleted = 1")
    @SelectableWhere(methodName = "getDateRange",
            where = "created_at > :from AND created_at < :to",
            params = {"java.util.Date from", "java.util.Date to"})
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

