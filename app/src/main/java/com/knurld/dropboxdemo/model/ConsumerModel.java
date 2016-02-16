package com.knurld.dropboxdemo.model;

import com.knurld.dropboxdemo.service.KnurldModelService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by andyshear on 2/16/16.
 */
public class ConsumerModel extends KnurldModelService {
    private String developerId;
    private String authorization;
    private String gender;
    private String username;
    private String password;
    private String href;

    public String consumerModelId;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.consumerModelId = href.substring(href.lastIndexOf("/") + 1);
        this.href = href;
    }

    @Override
    public void buildFromResponse(String response) {
        JSONObject jsonParam = null;

        try {
            jsonParam = new JSONObject(response);
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

    @Override
    public void buildFromId(String id) {
        this.consumerModelId = id;
    }

    @Override
    public String index() {
        return getRequest("consumers");
    }

    @Override
    public String show(String urlParam) {
        return getRequest("consumers", urlParam);
    }

    @Override
    public String create(String body) {
        return postRequest("consumers", null, body);
    }

    @Override
    public String update(String... params) {
        return postRequest("consumers", params[0], params[1]);
    }
}
