package com.carlrocks.httputils.entity;

import com.carlrocks.httputils.http.utils.Utils;

import java.util.List;

public class AnchorData {

    private List<Anchor> list;

    public List<Anchor> getList() {
        return list;
    }

    public void setList(List<Anchor> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return Utils.toString(this);
    }
}
