package com.jinfulin.quick_master.cache;


public interface Cache {

    /**
     * 保存布尔型数据
     * 
     * @param key
     * @param value
     */
    public void putBoolean(String key, boolean value);
    
    /**
     * 保存整型数据
     * 
     * @param key
     * @param value
     */
    public void putInt(String key, int value);
    
    /**
     * 保存long型数据
     * 
     * @param key
     * @param value
     */
    public void putLong(String key, long value);
    
    /**
     * 保存字符串
     * 
     * @param key
     * @param value
     */
    public void putString(String key, String value);
    
    /**
     * 获取boolean型数据
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public boolean getBoolean(String key, boolean defaultValue);
    
    /**
     * 获取整型数据
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInt(String key, int defaultValue);
    
    /**
     * 获取long型数据
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public long getLong(String key, long defaultValue);
    
    /**
     * 获取String型数据
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key, String defaultValue);

    /**
     * 清除某一个值
     * 
     * @param key
     */
    public void removeKey(String key);
    
    /**
     * 批量清除key
     * 
     * @param keys
     */
    public void removeKeys(String[] keys);

    /**
     * 清除所有数据
     */
    public void clearAll();
    
}
