package com.jinfulin.quick_master.beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by king on 2015/12/8.
 */
public class AppointInfo implements Serializable {
    public boolean courseOff;

    public int course_id;

    public String created_date;

    public int created_user;

    public String date;

    public boolean del;

    public int id;

    public int lockId;

    public String nextTime;

    public ArrayList<Person>  persons  ;

    public boolean pos0;

    public boolean pos1;

    public boolean pos2;

    public boolean pos3;

    public int posCount;

    public int slots;

    public String time;

    public String updated_date;

    public int updated_user;


    public class Person implements Serializable {
        public int caddie;

        public String card;

        public String contact_phone;

        public String courses;

        public String created_date;

        public int created_user;

        public String date;

        public boolean del;

        public int hole;

        public int id;

        public int identity;

        public String name;

        public String identityName;

        public int pos;

        public int status;

        public String teetimes;

        public String time;

        public String updated_date;

        public int updated_user;



    }

}
