package com.knurld.dropboxdemo.service;

import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by andyshear on 2/16/16.
 */
public class KnurldAnalysisService {

    private static final String CLIENT_ID = "EGVYDlI9Xgwhtd7GBvZsTjIPAmTjVxMR";
    private static final String CLIENT_SECRET = "e7yCrwbBeOzdholu";
    final static private String DEVELOPER_ID = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MTY5MDUsInJvbGUiOiJhZG1pbiIsImlkIjoiMDQ5MTg0NDUxMzc4NTgzODg1MmQ1NTBmOTIwNjk5ZjgiLCJ0ZW5hbnQiOiJ0ZW5hbnRfbXJwdGF4M25tejVobzRsMm5ycmc2MnRibTUzdGc1ZHduNXZob3ozam5yMmdpM3J2bzV5cSsrKysiLCJuYW1lIjoiYWRtaW4ifQ.2EGZ3bXCq8yaawru5uzwcqEjEHhVs96MlD9HGnF4JQZmCwc2T3efca1F480dVcQSVKc7wwOk61NlgkiDB_6NXw";
    private static String CLIENT_TOKEN;

    private static final String LINE_FEED = "\r\n";

    public KnurldAnalysisService(String token) {
        CLIENT_TOKEN = token;
    }

    public JSONArray getAnalysis(String urlParam) {
        StringBuilder sb = new StringBuilder();
        InputStream in = null;
        String result = "";

        String urlString = "https://api.knurld.io/v1/endpointAnalysis" + urlParam;


        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Developer-Id", "Bearer: " + DEVELOPER_ID);
            urlConnection.setRequestProperty("Authorization", "Bearer " + CLIENT_TOKEN);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            int HttpResult = urlConnection.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                System.out.println("" + sb.toString());
            } else{
                System.out.println(urlConnection.getResponseMessage());
            }

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            JSONArray res = null;
            return res;
        }

        result = sb.toString();
        JSONObject jsonParam = null;
        JSONArray intervals = null;
        try {
            jsonParam = new JSONObject(result);
            intervals = jsonParam.has("intervals") ? jsonParam.getJSONArray("intervals") : null;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return intervals;
    }

    public String startAnalysis(String body) {
        StringBuilder sb = new StringBuilder();

        String method = "endpointAnalysis";
        File file;

        String urlString = "https://api.knurld.io/v1/endpointAnalysis/file";
        String result = "";


        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);


            String boundary = "===" + System.currentTimeMillis() + "===";

            urlConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            urlConnection.setRequestProperty("Developer-Id", "Bearer: " + DEVELOPER_ID);
            urlConnection.setRequestProperty("Authorization", "Bearer " + CLIENT_TOKEN);
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            OutputStream outputStream = urlConnection.getOutputStream();

            String filePath = Environment.getExternalStorageDirectory().getPath();
            filePath = filePath + "/AudioRecorder/";
            JSONObject jsonBody = new JSONObject(body);
            String fileName = jsonBody.getString("filedata");
            file = new File(filePath, fileName);

            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            String name = "filedata";
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: audio/wav").append(LINE_FEED);
            writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();

            FileInputStream fin = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = fin.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            fin.close();

            writer.append(LINE_FEED);
            writer.flush();

            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"words\"").append(LINE_FEED);
            writer.append("Content-Type: text/plain; charset=UTF-8").append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.append("3").append(LINE_FEED);
            writer.flush();

            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();



            int HttpResult = urlConnection.getResponseCode();

            if (HttpResult == HttpURLConnection.HTTP_OK || HttpResult == HttpURLConnection.HTTP_CREATED || HttpResult == HttpURLConnection.HTTP_ACCEPTED){
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();

                Log.d("ResponseMessage", sb.toString());
            } else{
                method = "failed";
                Log.d("ResponseMessage", urlConnection.getResponseMessage());
            }

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            result = e.getMessage();
            return result;
        }

        result = sb.toString();

        return result;
    }
}
