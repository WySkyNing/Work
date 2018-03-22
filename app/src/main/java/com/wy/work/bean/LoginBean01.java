package com.wy.work.bean;

/**
 * Created by Administrator on 2018/3/16.
 */

public class LoginBean01 {


    /**
     * info : {"code":"0","code_info":"成功"}
     * uid : 260
     * token : a648d0a021
     * phoneNum : 18842606495
     * headPic :
     * name :
     */

    private InfoBean info;
    private String uid;
    private String token;
    private String phoneNum;
    private String headPic;
    private String name;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class InfoBean {
        /**
         * code : 0
         * code_info : 成功
         */

        private String code;
        private String code_info;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCode_info() {
            return code_info;
        }

        public void setCode_info(String code_info) {
            this.code_info = code_info;
        }
    }
}
