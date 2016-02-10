// Copyright 2016 Intellisis Inc.  All rights reserved.
//
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file
package com.knurld.dropboxdemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KnurldConsumerModel {

    private String developerId;
    private String authorization;
    private String gender;
    private String username;
    private String password;
    private String href;

    public String consumerModelId;

    public KnurldConsumerModel() {

    }

    public KnurldConsumerModel(String consumerId) {
        this.consumerModelId = consumerId;
    }

    public KnurldConsumerModel(String developerId, String authorization, String gender, String username, String password, String href) {
        this.developerId = developerId;
        this.authorization = authorization;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.consumerModelId = href.substring(href.lastIndexOf("/") + 1);
        this.href = href;
    }

    public void buildFromResponse(String result) {
        JSONObject jsonParam = null;

        try {
            jsonParam = new JSONObject(result);
            JSONArray items = jsonParam.has("items") ? jsonParam.getJSONArray("items") : null;
            if (items != null && items.length() > 0) {
                JSONObject item = (JSONObject) items.get(1);
                String h = item.has("href") ? item.getString("href") : null;
                if (h != null) {
                    setHref(h);
                }
            } else {
                String h = jsonParam.has("href") ? jsonParam.getString("href") : null;
                if (h != null) {
                    setHref(h);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}