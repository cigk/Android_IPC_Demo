package com.jinxiaolu.ipc.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/27
 * Desc:
 */
public class Msg implements Serializable, Parcelable {
    public String title;
    public String content;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
    }

    public Msg() {
    }

    protected Msg(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<Msg> CREATOR = new Parcelable.Creator<Msg>() {
        @Override
        public Msg createFromParcel(Parcel source) {
            return new Msg(source);
        }

        @Override
        public Msg[] newArray(int size) {
            return new Msg[size];
        }
    };
}
