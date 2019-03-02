package com.a1magway.bgg.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by enid on 2018/7/21.
 */

public class AddressSelectedDataBean implements Parcelable{

    /**
     * code : 0
     * msg : 获取城市列表成功
     * data : [{"id":1945,"name":"四川省","sonList":[{"id":1946,"name":"成都市","sonList":[{"id":1947,"name":"锦江区","sonList":null},{"id":1948,"name":"青羊区","sonList":null},{"id":1949,"name":"金牛区","sonList":null},{"id":8,"name":"海淀区","sonList":[]}]}]},{"id":3356,"name":"香港","sonList":null}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;
    public AddressSelectedDataBean(){}

    protected AddressSelectedDataBean(Parcel in) {
        code = in.readInt();
        msg = in.readString();
        data = in.createTypedArrayList(DataBean.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(msg);
        dest.writeTypedList(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddressSelectedDataBean> CREATOR = new Creator<AddressSelectedDataBean>() {
        @Override
        public AddressSelectedDataBean createFromParcel(Parcel in) {
            return new AddressSelectedDataBean(in);
        }

        @Override
        public AddressSelectedDataBean[] newArray(int size) {
            return new AddressSelectedDataBean[size];
        }
    };

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable{
        /**
         * id : 1945
         * name : 四川省
         * sonList : [{"id":1946,"name":"成都市","sonList":[{"id":1947,"name":"锦江区","sonList":null},{"id":1948,"name":"青羊区","sonList":null},{"id":1949,"name":"金牛区","sonList":null},{"id":8,"name":"海淀区","sonList":[]}]}]
         */

        private int id;
        private String name;
        private List<SonListBeanX> sonList;

        public DataBean(){}

        protected DataBean(Parcel in) {
            id = in.readInt();
            name = in.readString();
            sonList = in.createTypedArrayList(SonListBeanX.CREATOR);
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SonListBeanX> getSonList() {
            return sonList;
        }

        public void setSonList(List<SonListBeanX> sonList) {
            this.sonList = sonList;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeTypedList(sonList);
        }

        public static class SonListBeanX implements Parcelable{
            /**
             * id : 1946
             * name : 成都市
             * sonList : [{"id":1947,"name":"锦江区","sonList":null},{"id":1948,"name":"青羊区","sonList":null},{"id":1949,"name":"金牛区","sonList":null},{"id":8,"name":"海淀区","sonList":[]}]
             */

            private int id;
            private String name;
            private List<SonListBean> sonList;

            public SonListBeanX(){}

            protected SonListBeanX(Parcel in) {
                id = in.readInt();
                name = in.readString();
                sonList = in.createTypedArrayList(SonListBean.CREATOR);
            }

            public static final Creator<SonListBeanX> CREATOR = new Creator<SonListBeanX>() {
                @Override
                public SonListBeanX createFromParcel(Parcel in) {
                    return new SonListBeanX(in);
                }

                @Override
                public SonListBeanX[] newArray(int size) {
                    return new SonListBeanX[size];
                }
            };

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<SonListBean> getSonList() {
                return sonList;
            }

            public void setSonList(List<SonListBean> sonList) {
                this.sonList = sonList;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(name);
                dest.writeTypedList(sonList);
            }

            public static class SonListBean implements Parcelable{
                /**
                 * id : 1947
                 * name : 锦江区
                 * sonList : null
                 */

                private int id;
                private String name;
                private Object sonList;
                public SonListBean(){}

                protected SonListBean(Parcel in) {
                    id = in.readInt();
                    name = in.readString();
                }

                public static final Creator<SonListBean> CREATOR = new Creator<SonListBean>() {
                    @Override
                    public SonListBean createFromParcel(Parcel in) {
                        return new SonListBean(in);
                    }

                    @Override
                    public SonListBean[] newArray(int size) {
                        return new SonListBean[size];
                    }
                };

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Object getSonList() {
                    return sonList;
                }

                public void setSonList(Object sonList) {
                    this.sonList = sonList;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(id);
                    dest.writeString(name);
                }
            }
        }
    }
}
