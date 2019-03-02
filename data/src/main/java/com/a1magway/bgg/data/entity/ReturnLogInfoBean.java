package com.a1magway.bgg.data.entity;

import java.util.List;

public class ReturnLogInfoBean {

    /**
     * logisticsSkuList : [{"skuId":5219,"path":"https://smjdev.1magway.com/pictures/platform/commodity/picture/122010901412217795/1.png"},{"skuId":5195,"path":"https://smjdev.1magway.com/pictures/platform/commodity/picture/190010902100225504/1.png"}]
     * logistics : {"message":"ok","nu":"3368570495278","ischeck":"1","condition":"F00","com":"申通","status":"200","state":"3","data":[{"time":"2018-07-22 14:38:06","ftime":"2018-07-22 14:38:06","context":"已签收-已签收"},{"time":"2018-07-22 12:19:50","ftime":"2018-07-22 12:19:50","context":"四川成都蜀汉路营业部-蜀汉路魏韶辰(18084826687,)-派件中"},{"time":"2018-07-22 08:57:17","ftime":"2018-07-22 08:57:17","context":"已到达-四川成都蜀汉路营业部"},{"time":"2018-07-21 16:34:11","ftime":"2018-07-21 16:34:11","context":"四川成都转运中心-已发往-四川成都蜀汉路营业部"},{"time":"2018-07-19 19:55:07","ftime":"2018-07-19 19:55:07","context":"浙江杭州航空部-已装袋发往-四川成都转运中心"},{"time":"2018-07-19 16:46:28","ftime":"2018-07-19 16:46:28","context":"申通沃德萧山仓-已进行装袋扫描"},{"time":"2018-07-19 16:46:28","ftime":"2018-07-19 16:46:28","context":"申通沃德萧山仓-已发往-浙江杭州航空部"},{"time":"2018-07-19 16:41:09","ftime":"2018-07-19 16:41:09","context":"申通沃德萧山仓-园区5(13429656000)-已收件"}]}
     */

    private LogisticsBean logistics;
    private List<LogisticsSkuListBean> logisticsSkuList;

    public LogisticsBean getLogistics() {
        return logistics;
    }

    public void setLogistics(LogisticsBean logistics) {
        this.logistics = logistics;
    }

    public List<LogisticsSkuListBean> getLogisticsSkuList() {
        return logisticsSkuList;
    }

    public void setLogisticsSkuList(List<LogisticsSkuListBean> logisticsSkuList) {
        this.logisticsSkuList = logisticsSkuList;
    }

    public static class LogisticsBean {
        /**
         * message : ok
         * nu : 3368570495278
         * ischeck : 1
         * condition : F00
         * com : 申通
         * status : 200
         * state : 3
         * data : [{"time":"2018-07-22 14:38:06","ftime":"2018-07-22 14:38:06","context":"已签收-已签收"},{"time":"2018-07-22 12:19:50","ftime":"2018-07-22 12:19:50","context":"四川成都蜀汉路营业部-蜀汉路魏韶辰(18084826687,)-派件中"},{"time":"2018-07-22 08:57:17","ftime":"2018-07-22 08:57:17","context":"已到达-四川成都蜀汉路营业部"},{"time":"2018-07-21 16:34:11","ftime":"2018-07-21 16:34:11","context":"四川成都转运中心-已发往-四川成都蜀汉路营业部"},{"time":"2018-07-19 19:55:07","ftime":"2018-07-19 19:55:07","context":"浙江杭州航空部-已装袋发往-四川成都转运中心"},{"time":"2018-07-19 16:46:28","ftime":"2018-07-19 16:46:28","context":"申通沃德萧山仓-已进行装袋扫描"},{"time":"2018-07-19 16:46:28","ftime":"2018-07-19 16:46:28","context":"申通沃德萧山仓-已发往-浙江杭州航空部"},{"time":"2018-07-19 16:41:09","ftime":"2018-07-19 16:41:09","context":"申通沃德萧山仓-园区5(13429656000)-已收件"}]
         */

        private String message;
        private String nu;
        private String ischeck;
        private String condition;
        private String com;
        private String status;
        private String state;
        private List<DataBean> data;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getNu() {
            return nu;
        }

        public void setNu(String nu) {
            this.nu = nu;
        }

        public String getIscheck() {
            return ischeck;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * time : 2018-07-22 14:38:06
             * ftime : 2018-07-22 14:38:06
             * context : 已签收-已签收
             */

            private String time;
            private String ftime;
            private String context;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }
        }
    }

    public static class LogisticsSkuListBean {
        /**
         * skuId : 5219
         * path : https://smjdev.1magway.com/pictures/platform/commodity/picture/122010901412217795/1.png
         */

        private int skuId;
        private String path;

        public int getSkuId() {
            return skuId;
        }

        public void setSkuId(int skuId) {
            this.skuId = skuId;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
