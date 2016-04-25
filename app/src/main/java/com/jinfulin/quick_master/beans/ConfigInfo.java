package com.jinfulin.quick_master.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by king on 2015/12/9.
 */
public class ConfigInfo {
    public ArrayList<Cardtype> cardtype;

    public ArrayList<Outlet> outlet;

    public ArrayList<Payment> payment;

//    public ConsumerCard consumerCard;

//    public ArrayList<Package> package;

    public ArrayList<Transition> transition;

    public ArrayList<Holenum> holenum;

    public ArrayList<Course> course;

    public ArrayList<Unit> unit;

    public ArrayList<Saler> saler;

    public ArrayList<Identity> identity;

    public Club club;//球场信息

    public ArrayList<Gender> gender;

    public ArrayList<CaddieLevel> caddieLevel;

    public class Club {
        public int id;

        public boolean del;

        public String created_date;

        public int created_user;

        public String updated_date;

        public int updated_user;

        public String domain;

        public String name;

        public String address;

        public String telephone;

        public String profile;

        public String profile_image;

        public int ttime_person_hight_count;

        public int holiday_person_low_count;

        public int weekday_person_low_count;

        public boolean oneCard;

        public boolean unifiedNumber;
    }

    public class Cardtype {
        public int id;

        public boolean del;

        public String created_date;

        public int created_user;

        public String updated_date;

        public int updated_user;

        public String club_name;

        public int club_id;

        public String name;

        public int type;

        public boolean all_life;

        public int period;

        public boolean sign;

        public int sign_count;

        public int store;

        public int identity;

        public String identity_name;

        public boolean fee;

        public int year_fee;

        public int buy_price;

        public boolean staging;

        public boolean secondary;

        public int secondary_count;

        public int secondary_identity;

        public boolean secondary_sign;

    }
    public class Outlet {
        public int id;

        public boolean del;

        public String created_date;

        public int created_user;

        public String updated_date;

        public int updated_user;

        public String club_name;

        public int club_id;

        public String name;

        public String address;

        public String telephone;


    }
    public class Payment {
        public int id;

        public boolean del;

        public String created_date;

        public int created_user;

        public String updated_date;

        public int updated_user;

        public int club_id;

        public int config_id;

        public String name;

        public int value;

        public int seq;

    }




    public class Package {
        public int id;

        public boolean del;

        public String created_date;

        public int created_user;

        public String updated_date;

        public int updated_user;

        public int club_id;

        public String club_name;

        public String name;

        public int type;

        public int config_id;

        public String config_name;

        public boolean complex;

        public String identity;

        public String week;

        public boolean is_time;

        public String time_begin;

        public String time_end;

        public List<PackagItems> packagItems ;
        public class PackagItems {
            public int id;

            public boolean del;

            public String created_date;

            public int created_user;

            public String updated_date;

            public int updated_user;

            public int club_id;

            public String club_name;

            public int packageid;

            public String packagename;

            public int productid;

            public String productname;

            public int num;

            public int price;

            public int category_id;

            public int outlet_id;

            public boolean hasrule;

            public String rule;


        }

    }
    public class Transition {
        public int id;

        public boolean del;

        public String created_date;

        public int created_user;

        public String updated_date;

        public int updated_user;

        public String club_name;

        public int club_id;

        public String club_course_name_from;

        public int club_course_from;

        public String club_course_name_to;

        public int club_course_to;

        public int time;


    }
    public class Holenum {
        public int id;

        public boolean del;

        public String created_date;

        public int created_user;

        public String updated_date;

        public int updated_user;

        public int club_id;

        public int config_id;

        public String name;

        public int value;

        public int seq;


    }
    public class Course {
        public int id;

        public boolean del;

        public String created_date;

        public int created_user;

        public String updated_date;

        public int updated_user;

        public int club_id;

        public String club_name;

        public String name;

        public int hole;

        public int time;

        public String time_begin;

        public String time_end;


    }
    public class Unit {
        public int id;

        public boolean del;

        public String created_date;

        public int created_user;

        public String updated_date;

        public int updated_user;

        public int club_id;

        public int config_id;

        public String name;

        public int value;

        public int seq;


    }
    public class Saler {
        public int id;

        public boolean del;

        public String created_date;

        public int created_user;

        public String updated_date;

        public int updated_user;

        public int club_id;

        public int config_id;

        public String name;

        public int value;

        public int seq;


    }
    public class Identity {
        public int id;

        public boolean del;

        public String created_date;

        public int created_user;

        public String updated_date;

        public int updated_user;

        public int club_id;

        public String club_name;

        public int type;

        public String name;

        public String short_name;

        public int category_id;

        public String category_name;

        public int rely_id;

        public String rely_name;

        public int need_check;

        public int sort;

        public int person_max;

        public int cancal_time;


    }
    public class Gender {
        public int id;

        public boolean del;

        public String created_date;

        public int created_user;

        public String updated_date;

        public int updated_user;

        public int club_id;

        public int config_id;

        public String name;

        public int value;

        public int seq;


    }
    public class CaddieLevel {
        public int id;

        public boolean del;

        public String created_date;

        public int created_user;

        public String updated_date;

        public int updated_user;

        public int club_id;

        public int config_id;

        public String name;

        public int value;

        public int seq;


    }
}
