package com.ascendum.andyshear.dropboxdemo;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by andyshear on 1/11/16.
 */
public class KnurldService {
    private static final String CLIENT_ID = "EGVYDlI9Xgwhtd7GBvZsTjIPAmTjVxMR";
    private static final String CLIENT_SECRET = "e7yCrwbBeOzdholu";
    final static private String DEVELOPER_ID = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MDQ4MTY5MDUsInJvbGUiOiJhZG1pbiIsImlkIjoiMDQ5MTg0NDUxMzc4NTgzODg1MmQ1NTBmOTIwNjk5ZjgiLCJ0ZW5hbnQiOiJ0ZW5hbnRfbXJwdGF4M25tejVobzRsMm5ycmc2MnRibTUzdGc1ZHduNXZob3ozam5yMmdpM3J2bzV5cSsrKysiLCJuYW1lIjoiYWRtaW4ifQ.2EGZ3bXCq8yaawru5uzwcqEjEHhVs96MlD9HGnF4JQZmCwc2T3efca1F480dVcQSVKc7wwOk61NlgkiDB_6NXw";
    private static String CLIENT_TOKEN;

    private static final String LINE_FEED = "\r\n";

    AsyncKnurldResponse response;


    public KnurldService(AsyncKnurldResponse response) {
        this.response = response;
    }

    public void getToken(){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("accessToken", null, null);
    }

    public void indexAppModel(){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("indexAppModels", null, null);
    }

    public void showAppModel(String appModel){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("showAppModel", appModel, null);
    }

    public void createAppModel(String body){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("createAppModel", null, body);
    }

    public void indexConsumer(){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("indexConsumers", null, null);
    }

    public void showConsumer(String consumer){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("showConsumer", consumer, null);
    }

    public void createConsumer(String body){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("createConsumer", null, body);
    }

    public void updateConsumer(String consumer, String body){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("updateConsumer", consumer, body);
    }

    public void deleteConsumer(String consumer){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("deleteConsumer", consumer, null);
    }

    public void indexEnrollment(){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("indexEnrollments", null, null);
    }

    public void showEnrollment(String enrollment){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("showEnrollment", enrollment, null);
    }

    public void createEnrollment(String body){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("createEnrollment", null, body);
    }

    public void updateEnrollment(String enrollment, String body){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("updateEnrollment", enrollment, body);
    }

    public void deleteEnrollment(String enrollment){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("deleteEnrollment", enrollment, null);
    }

    public void indexVerification(){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("indexVerifications", null, null);
    }

    public void showVerification(String verification){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("showVerification", verification, null);
    }

    public void createVerification(String body){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("createVerification", null, body);
    }

    public void updateVerification(String verification, String body){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("updateVerification", verification, body);
    }

    public void deleteVerification(String verification){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("deleteVerification", verification, null);
    }


    public void showEndpointAnalysis(String endpointAnalysis){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("showEndpointAnalysis", endpointAnalysis, null);
    }

    public void createEndpointAnalysis(String body){
        HttpAsyncTask httpAsync = new HttpAsyncTask();
        httpAsync.delegate = response;
        httpAsync.execute("createEndpointAnalysis", null, body);
    }

    public static String[] GET(String... params) {
        StringBuilder sb = new StringBuilder();
        InputStream in = null;
        String method = params[0];
        String urlStringParams = (params[1] == null) ? "" : params[1];
        String[] result = {"", ""};
        String path = "";

        if (method.contains("EndpointAnalysis")) {
            path = "endpointAnalysis/";
        }

        String urlString = "https://api.knurld.io/v1/" + path + urlStringParams;

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

                System.out.println(""+sb.toString());
            } else{
                System.out.println(urlConnection.getResponseMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            result[0] = method;
            result[1] = e.getMessage();
            return result;
        }

        result[0] = method;
        result[1] = sb.toString();

        return result;
    }

    public static String[] AccessToken(String... params) {
        StringBuilder sb = new StringBuilder();
        InputStream in = null;
        String urlString = "https://api.knurld.io/oauth/client_credential/accesstoken?grant_type=client_credentials";
        String result = "";
        String credentials = "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET;

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("grant_type", "client_credentials");

            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            String body = "grant_type=client_credentials";
            out.write(credentials);
            out.flush();
            out.close();

            int HttpResult = urlConnection.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {

                    sb.append(line);
                }
                String test = sb.toString();
                JSONObject jsonResponse = new JSONObject(sb.toString());
                CLIENT_TOKEN = jsonResponse.getString("access_token");
                br.close();

                System.out.println("" + sb.toString());
                System.out.println("TOKEN " + CLIENT_TOKEN);
            } else{
                System.out.println(urlConnection.getResponseMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new String[]{"error", e.getMessage()};
        }

        return new String[]{"accessToken", CLIENT_TOKEN};
    }

    public static String[] POST(String... params) {
        StringBuilder sb = new StringBuilder();
        StringBuilder dataBuilder = new StringBuilder();
        InputStream in = null;

        String method = params[0];
        String urlStringParams = (params[1] == null) ? "" : params[1];
        String body = params[2];
        File file;
        String path = "";
        if (method.contains("AppModel")) {
            path = "app-models";
        } else if (method.contains("Consumer")) {
            path = "consumers";
        } else if (method.contains("Enrollment")) {
            path = "enrollments";
        } else if (method.contains("Verification")) {
            path = "verifications";
        } else if (method.contains("EndpointAnalysis")) {
            path = "endpointAnalysis/file";


//            String filePath = Environment.getExternalStorageDirectory().getPath();
//            try {
//                JSONObject jsonBody = new JSONObject(body);
//                String fileName = jsonBody.getString("filedata");
//                file = new File(filePath, fileName);
//                DataInputStream dis = new DataInputStream(new FileInputStream(file));
//                while (dis.available()>0) {
//                    dataBuilder.append(dis.read());
//                }
//                String fileData = dataBuilder.toString();
//                jsonBody.put("filedata", fileData);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


        }
        String urlString = "https://api.knurld.io/v1/" + path + urlStringParams;
        String[] result = {"", ""};

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            if (method.contains("EndpointAnalysis")) {
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

//                PrintWriter writer = new PrintWriter(out, true);
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
                String name = "filedata";
                String filename = "enrollments.wav";
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + filename + "\"").append(LINE_FEED);
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

            } else {
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Developer-Id", "Bearer: " + DEVELOPER_ID);
                urlConnection.setRequestProperty("Authorization", "Bearer " + CLIENT_TOKEN);
                urlConnection.setRequestMethod("POST");
                urlConnection.connect();

                OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
                out.write(body);
                out.flush();
                out.close();
            }



            int HttpResult = urlConnection.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK || HttpResult == HttpURLConnection.HTTP_CREATED){
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();

                System.out.println(""+sb.toString());
            } else{
                System.out.println(urlConnection.getResponseMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            result[0] = method;
            result[1] = e.getMessage();
            return result;
        }

        result[0] = method;
        result[1] = sb.toString();

        return result;
    }


    private class HttpAsyncTask extends AsyncTask<String, String, String[]> {
        public AsyncKnurldResponse delegate = null;
        @Override
        protected String[] doInBackground(String... params) {
            String method = params[0];
            String param = (params[1] == null) ? null : params[1];
            String body = (params[2] == null) ? null : params[2];
            switch (method) {
                case "accessToken":
                    return AccessToken();
                case "createAppModel":
                    return POST(method, param, body);
                case "indexAppModels":
                    return GET(method);
                case "showAppModel":
                    return GET(method, param);
                case "updateAppModel":
                    return POST(method, param, body);
                case "deleteAppModel":
                    return POST(method, param, body);
                case "createConsumer":
                    return POST(method, param, body);
                case "indexConsumers":
                    return GET(method);
                case "showConsumer":
                    return GET(method, param);
                case "updateConsumer":
                    return POST(method, param, body);
                case "deleteConsumer":
                    return POST(method, param, body);
                case "createEnrollment":
                    return POST(method, param, body);
                case "indexEnrollments":
                    return GET(method);
                case "showEnrollment":
                    return GET(method, param);
                case "updateEnrollment":
                    return POST(method, param, body);
                case "deleteEnrollment":
                    return POST(method, param, body);
                case "createVerification":
                    return POST(method, param, body);
                case "indexVerifications":
                    return GET(method);
                case "showVerification":
                    return GET(method, param);
                case "updateVerification":
                    return POST(method, param, body);
                case "deleteVerification":
                    return POST(method, param, body);
                case "createEndpointAnalysis":
                    return POST(method, param, body);
                case "showEndpointAnalysis":
                    return GET(method, param);
                default:
                    return null;
            }

        }

        protected void onPostExecute(String... result) {
            delegate.processFinish("knurld", result[0], result[1]);
        }
    }
}
