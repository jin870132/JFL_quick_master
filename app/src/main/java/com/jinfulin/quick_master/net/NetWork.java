package com.jinfulin.quick_master.net;

import android.content.Context;
import android.text.TextUtils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class NetWork {
    String API = com.jinfulin.quick_master.util.Configuration.API_HOST + "/Api/Front/";
    String API2 = com.jinfulin.quick_master.util.Configuration.API_HOST + "/Api/";
    public final String LOGIN = API + "Login";
    public final String CONFIG = API + "Config";
    public final String LISTCARDS = API + "Consume/ListCards";
    public final String GETPERSONORDER = API + "Consume/GetPersonOrder";
    public final String EDITPRODUCTORDER = API + "Consume/EditProductOrder";
    public final String DELETEPRODUCTORDER = API + "Consume/DeleteProductOrder";
    public final String QUERYTEETIME = API + "Teetime";
    public final String CHANGETEETIME = API + "Reserve/ChangeTeetime";
    public final String CHANGERESERVE = API + "Reserve/ChangeReserve";
    public final String RESERVEDEL = API + "Reserve/Del";
    public final String QUERYCLUBMEMBERLIST = API2 + "ClubMember/List";
    public final String QUERYPRODUCTTREE = API + "ProductTree/ProductTree";
    public final String ADDPRODUCTORDER = API + "Consume/AddProductOrder";

    private int clubId = 1;
    private int leagueId = 30;
//    private int leagueId = 54;
    private static NetWork instance = null;
    private BaseApi mApiClient = null;

    private NetWork(Context context) {
        mApiClient = BaseApi.getInstance(context);
    }

    public static NetWork getInstance() {
        if (instance == null) {
            synchronized (NetWork.class) {
                if (instance == null) {
                    instance = new NetWork(com.jinfulin.quick_master.util.UIUtils.getContext());
                }
            }
        }
        return instance;
    }


    /**
     * 登录接口实现
     *
     * @param password
     * @param callback
     */
    public void login(String userName, String password, com.jinfulin.quick_master.net.Callback callback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clubId", clubId);
            jsonObject.put("leagueId", leagueId);
            jsonObject.put("userName", userName);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mApiClient.postLogin(LOGIN, jsonObject, callback);
    }

    /**
     * 获取config配置信息
     *
     * @param callback
     */
    public void getConfigFromNet(com.jinfulin.quick_master.net.Callback callback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clubId", clubId);
            jsonObject.put("leagueId", leagueId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mApiClient.post(CONFIG, jsonObject, callback);
    }

    /**
     * 获取可用消费卡号
     *
     * @param callback
     */
    public void getlistcardsFromNet(String keyword, com.jinfulin.quick_master.net.Callback callback) {
        if (TextUtils.isEmpty(keyword)) {
            keyword = " ";
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clubId", clubId);
            jsonObject.put("leagueId", leagueId);
            jsonObject.put("keyword", keyword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mApiClient.post(LISTCARDS, jsonObject, callback);
    }

    /**
     * 获取用户已消费项目
     *
     * @param person_id 球友id ListCards返回的id
     * @param callback
     */
    public void getPersonOrderFromNet(int person_id, com.jinfulin.quick_master.net.Callback callback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clubId", clubId);
            jsonObject.put("leagueId", leagueId);
            jsonObject.put("person_id", person_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mApiClient.post(GETPERSONORDER, jsonObject, callback);
    }

    /**
     * 编辑已销户产品
     *
     * @param id       产品订单id
     * @param count    产品订单数量
     * @param discount 产品订单折扣
     * @param callback
     */
    public void editProductOrderFromNet(int id, int count, double discount, com.jinfulin.quick_master.net.Callback callback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clubId", clubId);
            jsonObject.put("leagueId", leagueId);
            jsonObject.put("id", id);
            jsonObject.put("count", count);
            jsonObject.put("discount", discount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mApiClient.post(EDITPRODUCTORDER, jsonObject, callback);
    }

    /**
     * 编辑已销户产品
     *
     * @param id       产品订单id
     * @param callback
     */
    public void deleteProductOrderFromNet(int id, com.jinfulin.quick_master.net.Callback callback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clubId", clubId);
            jsonObject.put("leagueId", leagueId);
            jsonObject.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mApiClient.post(DELETEPRODUCTORDER, jsonObject, callback);
    }

    /**
     * 获取teetime列表
     *
     * @param date     出发时间
     * @param callback
     */
    public void queryTeetimeFromNet(String date, com.jinfulin.quick_master.net.Callback callback) {
        int courseId = com.jinfulin.quick_master.util.ConfigManager.getInstance().getCourseList().get(0).id;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clubId", clubId);
            jsonObject.put("leagueId", leagueId);
            jsonObject.put("courseId", courseId);
            jsonObject.put("date", date);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mApiClient.post(QUERYTEETIME, jsonObject, callback);
    }

    /**
     * 新建/修改预约
     *
     * @param callback
     */
    public void changeTeetimeFromNet(ArrayList<com.jinfulin.quick_master.beans.AppointInfo.Person> person, com.jinfulin.quick_master.net.Callback callback) {
        int courseId = com.jinfulin.quick_master.util.ConfigManager.getInstance().getCourseList().get(0).id;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clubId", clubId);
            jsonObject.put("leagueId", leagueId);
            jsonObject.put("courseId", courseId);
            JSONArray jsonarrray = new JSONArray();
            for (int i = 0; i < person.size(); i++) {
                JSONObject jsonObj2 = new JSONObject();
                jsonObj2.put("date", person.get(i).date);
                jsonObj2.put("time", person.get(i).time);
                jsonObj2.put("name", person.get(i).name);
                jsonObj2.put("card", person.get(i).card);
                jsonObj2.put("pos", person.get(i).pos);
                jsonObj2.put("identity", person.get(i).identity);
                jsonObj2.put("hole", 18);
                jsonObj2.put("courses", "1,2");
                if (person.get(i) != null && person.get(i).id != 0) {
                    jsonObj2.put("id", person.get(i).id);
                }
                jsonarrray.put(jsonObj2);
            }
            jsonObject.put("persons", jsonarrray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mApiClient.post(CHANGETEETIME, jsonObject, callback);
    }

    /**
     * 调整预约
     *
     * @param callback
     */
    public void ChangeReserveFromNet(String date, String time, ArrayList<com.jinfulin.quick_master.beans.AppointInfo.Person> person, com.jinfulin.quick_master.net.Callback callback) {
        int courseId = com.jinfulin.quick_master.util.ConfigManager.getInstance().getCourseList().get(0).id;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clubId", clubId);
            jsonObject.put("leagueId", leagueId);
            jsonObject.put("courseId", courseId);
            jsonObject.put("hole", 9);
            jsonObject.put("date", date);
            jsonObject.put("time", time);
            JSONArray jsonarrray = new JSONArray();
            for (int i = 0; i < person.size(); i++) {
                JSONObject jsonObj2 = new JSONObject();
                jsonObj2.put("date", person.get(i).date);
                jsonObj2.put("time", person.get(i).time);
                jsonObj2.put("name", person.get(i).name);
                jsonObj2.put("pos", person.get(i).pos);
                jsonObj2.put("identity", person.get(i).identity);
                jsonObj2.put("hole", 9);
                jsonObj2.put("courses", "1,2");
                if (person.get(i) != null) {
                    jsonObj2.put("id", person.get(i).id);
                }
                jsonarrray.put(jsonObj2);
            }
            jsonObject.put("persons", jsonarrray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mApiClient.post(CHANGERESERVE, jsonObject, callback);
    }

    /**
     * 删除预约
     *
     * @param callback
     * @param personIds 取消预约的用户id集合	逗号分隔多个用户id
     */
    public void delReserveFromNet(String personIds, com.jinfulin.quick_master.net.Callback callback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clubId", clubId);
            jsonObject.put("leagueId", leagueId);
            jsonObject.put("personIds", personIds);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mApiClient.post(RESERVEDEL, jsonObject, callback);
    }

    /**
     * 查询会员列表
     *
     * @param callback
     */
    public void queryClubMemberList(String text, String type, com.jinfulin.quick_master.net.Callback callback) {
        if (TextUtils.isEmpty(text)) {
            text = " ";
        }

        HashMap<String, String> params = new HashMap<String,String>();
        params.put("clubId", clubId + "");
        params.put("leagueId", leagueId + "");
        params.put("activate", "1");//是否激活
        params.put("page", "1");//页数
        params.put("count", "10");//每页数量
        params.put(type, text);//搜索内容和方式,type为card,phone,name中的一个
        mApiClient.get(QUERYCLUBMEMBERLIST, params, callback);

    }

    /**
     * 获取产品列表
     *
     * @param callback
     */
    public void queryProductTree(int outlet_id, com.jinfulin.quick_master.net.Callback callback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clubId", clubId);
            jsonObject.put("leagueId", leagueId);
            jsonObject.put("outlet_id", outlet_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mApiClient.post(QUERYPRODUCTTREE, jsonObject, callback);

    }

    /**
     * 获取产品列表
     *
     * @param callback
     */
    public void AddProductOrder(int outlet_id, int category_id, int identity, int person_id,
                                int product_id, com.jinfulin.quick_master.net.Callback callback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clubId", clubId);
            jsonObject.put("leagueId", leagueId);
            jsonObject.put("outlet_id", outlet_id);
            jsonObject.put("category_id", category_id);
            jsonObject.put("identity", identity);
            jsonObject.put("person_id", person_id);
            jsonObject.put("product_id", product_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mApiClient.post(ADDPRODUCTORDER, jsonObject, callback);

    }


}