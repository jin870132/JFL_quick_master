package com.jinfulin.quick_master.beans;

import java.util.ArrayList;

/**
 * Created by king on 2015/12/15.
 */
public class ClubMemberInfo {
    public int page;

    public int records;

    public ArrayList<Rows> rows ;

    public int total;

    public class Rows {
        public boolean activate;

        public String birthday;

        public int buy_price;

        public String card;

        public int card_type;

        public int card_type_id;

        public String card_type_name;

        public String card_uuid;

        public int club_id;

        public String club_name;

        public String created_date;

        public int created_user;

        public boolean debt;

        public double debt_max;

        public double debt_money;

        public boolean del;

        public String email;

        public String end_date;

        public boolean fee;

        public String fee_end_date;

        public String gender;

        public int gender_id;

        public String headUrl;

        public int id;

        public int identity_id;

        public boolean identity_mark;

        public String identity_name;

        public boolean main;

        public String main_card;

        public double monetary;

        public String name;

        public String phone;

        public String py;

        public String remark;

        public String saler;

        public int saler_id;

        public boolean sign;

        public int sign_count;

        public String start_date;

        public double store;

        public ArrayList<String> store_money ;

        public int type;

        public String updated_date;

        public int updated_user;

        public int year_fee;


    }

}
