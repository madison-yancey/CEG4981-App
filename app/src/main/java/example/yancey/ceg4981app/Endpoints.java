package example.yancey.ceg4981app;

import org.json.JSONArray;
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
import android.util.Log;

public class Endpoints {

    private static String url = "http://10.16.34.11:5000/";
    private static JSONObject jsonResponse;
    private static String stringResponse;
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

    //********************COOkING**************************
    /**
     * Start schedule POST
     *
     */
    public static JSONObject startSchedule(int scheduleId){
        OkHttpClient client = new OkHttpClient();
        String localURL = url;

        clearPreviousEndpointContext();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("scheduleId", Integer.toString(scheduleId))
                .build();

        Request request = new Request.Builder()
                .url(localURL + "startSchedule")
                .post(requestBody)
                .build();

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
     * Stop schedule POST
     *
     */
    public static JSONObject stopSchedule(){
        OkHttpClient client = new OkHttpClient();
        String localURL = url;

        clearPreviousEndpointContext();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                //.addFormDataPart("scheduleId", Integer.toString(scheduleId))
                .build();

        Request request = new Request.Builder()
                .url(localURL + "stopSchedule")
                .post(requestBody)
                .build();

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
     * Set cooking setting POST
     *
     */
    public static JSONObject setCookSetting(String cooking_setting){
        OkHttpClient client = new OkHttpClient();
        String localURL = url;

        clearPreviousEndpointContext();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("cooking_setting", cooking_setting)
                .build();

        Request request = new Request.Builder()
                .url(localURL + "setCookSetting")
                .post(requestBody)
                .build();

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
     * This will GET the temperature from the server.
     * On success the response will be a JSON object with the schedule
     * On failure the response will be a null jSON object
     */

    public static String getTemp() {

        OkHttpClient client = new OkHttpClient();
        String localURL = url + "getTemp";

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
                        //jsonResponse = new JSONObject(response.body().string());
                        stringResponse = response.body().string();
                    } catch (Exception e) {
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
        return stringResponse;
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
        String localURL = url + "listSchedule";

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
     * Create schedule POST
     *
     */
    public static JSONObject createSchedule(String body, String name, int time, String setting){
        OkHttpClient client = new OkHttpClient();
        String localURL = url;

        clearPreviousEndpointContext();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("body", body)
                .addFormDataPart("name", name)
                .addFormDataPart("time", Integer.toString(time))
                .addFormDataPart("setting", setting)
                .build();

        Request request = new Request.Builder()
                .url(localURL + "createSchedule")
                .post(requestBody)
                .build();

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
     * Update schedule POST
     *
     */
    public static JSONObject updateSchedule(int scheduleId, String body, String name, int time, String setting){
        OkHttpClient client = new OkHttpClient();
        String localURL = url;

        clearPreviousEndpointContext();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("scheduleId", Integer.toString(scheduleId))
                .addFormDataPart("body", body)
                .addFormDataPart("name", name)
                .addFormDataPart("time", Integer.toString(time))
                .addFormDataPart("setting", setting)
                .build();

        Request request = new Request.Builder()
                .url(localURL + "updateSchedule")
                .put(requestBody)
                .build();

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
     * Sends a DELETE request to the server to delete an Schedule .
     * The request holds the schedule which will be deleted on the server.
     * Success will leave the JSON response with a server confirmation of successful deletion.
     * Failure will leave the JSON response as null.
     * @param scheduleId of recipe to delete on server
     */
    public static JSONObject deleteSchedule(int scheduleId){
        OkHttpClient client = new OkHttpClient();
        String localURL = url + "deleteSchedule" + "?scheduleId=" + scheduleId;

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
        //System.out.println("RESPONSE: " + jsonResponse);
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
        Log.d("Recipe URL:", localURL);
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
                System.out.println("Recipe: Failed request");
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
        String localURL = url + "listRecipe";

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
     * Create recipe POST
     *
     */
    public static JSONObject createRecipe(String body, String name){
        OkHttpClient client = new OkHttpClient();
        String localURL = url;

        clearPreviousEndpointContext();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("body", body)
                .addFormDataPart("name", name)
                .build();

        Request request = new Request.Builder()
                .url(localURL + "createRecipe")
                .post(requestBody)
                .build();

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
     * Update recipe POST
     *
     */
    public static JSONObject updateRecipe(int recipeId, String body, String name){
        OkHttpClient client = new OkHttpClient();
        String localURL = url;

        clearPreviousEndpointContext();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("recipeId", Integer.toString(recipeId))
                .addFormDataPart("body", body)
                .addFormDataPart("name", name)
                .build();

        Request request = new Request.Builder()
                .url(localURL + "updateRecipe")
                .put(requestBody)
                .build();

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
     * Sends a DELETE request to the server to delete an Recipe .
     * The request holds the recipe which will be deleted on the server.
     * Success will leave the JSON response with a server confirmation of successful deletion.
     * Failure will leave the JSON response as null.
     * @param recipeId of recipe to delete on server
     */

    public static JSONObject deleteRecipe(int recipeId){
        OkHttpClient client = new OkHttpClient();
        String localURL = url + "deleteRecipe" + "?recipeId=" + recipeId;

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
