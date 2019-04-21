package example.yancey.ceg4981app;

import org.json.JSONObject;
import java.util.concurrent.CountDownLatch;

import android.media.Image;
import android.util.Log;
import org.json.JSONException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Endpoints {

    private static String url = "http://10.16.34.11:5000/";
    private static JSONObject jsonResponse;
    private static CountDownLatch countDownLatch;

    private Endpoints() {
    }

    private static void clearPreviousEndpointContext() {
        jsonResponse = null;
        countDownLatch = new CountDownLatch(1);
    }

    public static String getServerURL() {
        return url;
    }

    //********************SCHEDULE**************************

    /**
     * This will GET a schedule from the server.
     * On success the response will be a JSON object with the schedule
     * On failure the response will be a null jSON object
     * @param scheduleId start of id ranges to get from server
     */

    public static JSONObject getSchedule(String scheduleId) {

        OkHttpClient client = new OkHttpClient();
        String localURL = url + "getSchedule?scheduleId=" + scheduleId;

        // Form request
        Request request = new Request.Builder()
                .url(localURL)
                .build();

        clearPreviousEndpointContext();
        // this is here to await the end of the call so that way we don't do anything until request comes back
        // async call because sync call causes exception
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Failed request");
                e.printStackTrace();
                countDownLatch.countDown();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        jsonResponse = new JSONObject(response.body().string());
                    } catch (JSONException e) {
                        System.out.println("Failed to get JSON");
                        e.printStackTrace();
                    }
                }
                System.out.println(jsonResponse);
                countDownLatch.countDown();
            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonResponse;

    }

    /**
     * This will GET all schedules from the server.
     * On success the response will be a JSON object with the schedules
     * On failure the response will be a null jSON object
     */

    public static JSONObject listSchedule() {

        OkHttpClient client = new OkHttpClient();
        String localURL = url;

        // Form request
        Request request = new Request.Builder()
                .url(localURL)
                .build();

        clearPreviousEndpointContext();
        // this is here to await the end of the call so that way we don't do anything until request comes back
        // async call because sync call causes exception
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Failed request");
                e.printStackTrace();
                countDownLatch.countDown();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        jsonResponse = new JSONObject(response.body().string());
                    } catch (JSONException e) {
                        System.out.println("Failed to get JSON");
                        e.printStackTrace();
                    }
                }
                System.out.println(jsonResponse);
                countDownLatch.countDown();
            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonResponse;

    }


    //TO DO: create schedule

    /**
     * Sends a DELETE request to the server to delete an Schedule .
     * The request holds the schedule which will be deleted on the server.
     * Success will leave the JSON response with a server confirmation of successful deletion.
     * Failure will leave the JSON response as null.
     * @param scheduleId of recipe to delete on server
     */
    public static JSONObject deleteSchedule(int scheduleId){
        OkHttpClient client = new OkHttpClient();
        String localURL = url + "scheduleId?scheduleId=" + scheduleId;

        // Form request
        Request request = new Request.Builder()
                .url(localURL)
                .delete()
                .build();

        clearPreviousEndpointContext();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Failed request");
                e.printStackTrace();
                countDownLatch.countDown();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        jsonResponse = new JSONObject(response.body().string());
                    } catch (JSONException e) {
                        System.out.println("Failed to get JSON");
                        e.printStackTrace();
                    }
                }
                System.out.println(jsonResponse);
                countDownLatch.countDown();
            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    //********************RECIPE**************************

    /**
     * This will GET a recipe from the server.
     * On success the response will be a JSON object with the schedule
     * On failure the response will be a null jSON object
     * @param recipeId start of id ranges to get from server
     */

    public static JSONObject getRecipe(String recipeId) {

        OkHttpClient client = new OkHttpClient();
        String localURL = url + "getRecipe?recipeId=" + recipeId;

        // Form request
        Request request = new Request.Builder()
                .url(localURL)
                .build();

        clearPreviousEndpointContext();
        // this is here to await the end of the call so that way we don't do anything until request comes back
        // async call because sync call causes exception
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Failed request");
                e.printStackTrace();
                countDownLatch.countDown();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        jsonResponse = new JSONObject(response.body().string());
                    } catch (JSONException e) {
                        System.out.println("Failed to get JSON");
                        e.printStackTrace();
                    }
                }
                System.out.println(jsonResponse);
                countDownLatch.countDown();
            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonResponse;

    }

    /** This will GET all recipes from the server.
     * On success the response will be a JSON object with the recipes
     * On failure the response will be a null jSON object */

    public static JSONObject listRecipe() {

        OkHttpClient client = new OkHttpClient();
        String localURL = url;

        // Form request
        Request request = new Request.Builder()
                .url(localURL)
                .build();

        clearPreviousEndpointContext();
        // this is here to await the end of the call so that way we don't do anything until request comes back
        // async call because sync call causes exception
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Failed request");
                e.printStackTrace();
                countDownLatch.countDown();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        jsonResponse = new JSONObject(response.body().string());
                    } catch (JSONException e) {
                        System.out.println("Failed to get JSON");
                        e.printStackTrace();
                    }
                }
                System.out.println(jsonResponse);
                countDownLatch.countDown();
            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonResponse;

    }

    //TO DO: create recipe

     /**
     * Sends a DELETE request to the server to delete an Recipe .
     * The request holds the recipe which will be deleted on the server.
     * Success will leave the JSON response with a server confirmation of successful deletion.
     * Failure will leave the JSON response as null.
     * @param recipeId of recipe to delete on server
     */

    public static JSONObject deleteRecipe(int recipeId){
        OkHttpClient client = new OkHttpClient();
        String localURL = url + "recipeId?recipeId=" + recipeId;

        // Form request
        Request request = new Request.Builder()
                .url(localURL)
                .delete()
                .build();

        clearPreviousEndpointContext();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Failed request");
                e.printStackTrace();
                countDownLatch.countDown();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        jsonResponse = new JSONObject(response.body().string());
                    } catch (JSONException e) {
                        System.out.println("Failed to get JSON");
                        e.printStackTrace();
                    }
                }
                System.out.println(jsonResponse);
                countDownLatch.countDown();
            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jsonResponse;

    }

}
