package com.jinfulin.quick_master.beans;

import java.util.ArrayList;

/**
 * Created by king on 2015/12/2.
 */

public class UserInfo {
    public String alias;

    public int club_id;

    public String club_name;

    public String common_module;

    public String created_date;

    public int created_user;

    public boolean del;

    public int id;

    public String name;

    public int outlet_id;

    public String password;

    public ArrayList<Resources> resources;

    public String updated_date;

    public int updated_user;

    public class Resources {
        public int depth;

        public String icon;

        public String id;

        public boolean is_active;

        public boolean is_item;

        public String menu_type;

        public String name;

        public String owner;

        public int seq;

    }

}

