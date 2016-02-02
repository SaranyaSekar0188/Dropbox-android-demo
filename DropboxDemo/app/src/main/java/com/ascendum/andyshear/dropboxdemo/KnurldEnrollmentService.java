package com.ascendum.andyshear.dropboxdemo;

/**
 * Created by andyshear on 1/24/16.
 */
public class KnurldEnrollmentService {

    private KnurldAsyncTask knurldAsync;

    private KnurldAPIResource knurldAPIResource;
    AsyncKnurldResponse response;

    public KnurldEnrollmentService(AsyncKnurldResponse response) {
        this.response = response;
    }

    public KnurldEnrollmentService(String token) {
        knurldAPIResource = new KnurldAPIResource(token);
    }

    public KnurldEnrollmentsModel indexEnrollment(){
        knurldAsync = new KnurldAsyncTask(response, KnurldUtility.GET, "enrollments", null, null);
        return new KnurldEnrollmentsModel();
    }

    public void showEnrollment(String enrollment){
        knurldAsync = new KnurldAsyncTask(response, KnurldUtility.GET, "enrollments", enrollment, null);
    }

    public void createEnrollment(String body){
        knurldAsync = new KnurldAsyncTask(response, KnurldUtility.POST, "enrollments", null, body);
    }

    public void updateEnrollment(String enrollment, String body){
        knurldAsync = new KnurldAsyncTask(response, KnurldUtility.POST, "enrollments", enrollment, body);
    }

    public String[] show(String enrollment){
        return knurldAPIResource.request(KnurldUtility.GET, "enrollments", enrollment, null);
    }

    public String[] create(String body){
        return knurldAPIResource.request(KnurldUtility.POST, "enrollments", null, body);
    }

    public String[] update(String enrollment, String body){
        return knurldAPIResource.request(KnurldUtility.POST, "enrollments", enrollment, body);
    }
}
