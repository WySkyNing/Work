package com.wy.work.bean;

import com.ning.fastwork.net.bass.BaseBean;

/**
 * Created by Administrator on 2018/3/14.
 */

public class LoginBean extends BaseBean{

    /**
     * body : {"uid":"20966","sid":"1647314f-cb9b-45fe-8c9f-3bbacabfb1021520995461609","username":"18842606495","noviceActivityStatus":"1","rtnUrl":""}
     * sid : 1647314f-cb9b-45fe-8c9f-3bbacabfb1021520995461609
     * status : 0
     * code : 0
     * msg :
     */

    private BodyBean body;
    private String sid;
    private int status;
    private int code;
    private String msg;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class BodyBean {
        /**
         * uid : 20966
         * sid : 1647314f-cb9b-45fe-8c9f-3bbacabfb1021520995461609
         * username : 18842606495
         * noviceActivityStatus : 1
         * rtnUrl :
         */

        private String uid;
        private String sid;
        private String username;
        private String noviceActivityStatus;
        private String rtnUrl;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNoviceActivityStatus() {
            return noviceActivityStatus;
        }

        public void setNoviceActivityStatus(String noviceActivityStatus) {
            this.noviceActivityStatus = noviceActivityStatus;
        }

        public String getRtnUrl() {
            return rtnUrl;
        }

        public void setRtnUrl(String rtnUrl) {
            this.rtnUrl = rtnUrl;
        }
    }
}
