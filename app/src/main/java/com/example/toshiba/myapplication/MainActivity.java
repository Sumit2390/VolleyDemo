package com.example.toshiba.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.support.v4.os.LocaleListCompat.create;

public class MainActivity extends AppCompatActivity {

    public Button btn1, btn2, btn3, btn4;
    String Tag = "string call";
    String jsonobjectTag = "jsonobject call ";
    String jsonArrayTag = "jsonarray call ";
    String jsonimageTag = "jsonimage call ";
    View showDialogView;
    TextView outputTextView;


    String URL = "https://androidtutorialpoint.com/api/volleyString ";
    String jsonURL = "https://androidtutorialpoint.com/api/volleyJsonObject";
    String jsonArrayURL = " https://androidtutorialpoint.com/api/volleyJsonArray";
    String imageURL = "https://androidtutorialpoint.com/api/lg_nexus_5x";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //   setContentView(R.layout.first_screen);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                volleyStringRequest();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                volleyJsonObjectRequest();

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                volleyJsonArrayRequest();

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                volleyJsonObjectRequest();

            }
        });

    }

    private void volleyStringRequest() {

        String REQUEST_TAG = "com.androidtutorialpoint.volleyStringRequest";

       /* progressDialog.setMessage("Loading .....");
        progressDialog.show();*/

        StringRequest strReq = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(Tag, response.toString());

                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                showDialogView = li.inflate(R.layout.show_dialog, null);
                outputTextView = (TextView) showDialogView.findViewById(R.id.text_view_dialog);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setView(showDialogView);
                alertDialogBuilder
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        })
                        .setCancelable(false)
                        .create();
                outputTextView.setText(response.toString());
                alertDialogBuilder.show();
                //       progressDialog.hide();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(Tag, "Error: " + error.getMessage());
                progressDialog.hide();
            }
        });
        // Adding String request to request queue

        VolleyAppSingleton.getmVolleyAppSingletonInstance(getApplicationContext()).addToRequestQueue(strReq, REQUEST_TAG);
    }

    public void volleyJsonObjectRequest() {

        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
       /* progressDialog.setMessage("Loading...");
        progressDialog.show();*/

        /*JsonObjectRequest jsonObjectReq = new JsonObjectRequest(jsonURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(jsonobjectTag, response.toString());

                        LayoutInflater li = LayoutInflater.from(MainActivity.this);
                        showDialogView = li.inflate(R.layout.show_dialog, null);
                        outputTextView = (TextView) showDialogView.findViewById(R.id.text_view_dialog);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setView(showDialogView);
                        alertDialogBuilder
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                })
                                .setCancelable(false)
                                .create();
                        outputTextView.setText(response.toString());
                        alertDialogBuilder.show();
                        //       progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(jsonobjectTag, "Error: " + error.getMessage());
                //   progressDialog.hide();
            }
        });*/

        String url = "http://stage.survey.hff.ukkoteknik.com/save-survey-api";
        final String params =
                "{\n" +
                        "\"30\":\n" +
                        "[\n" +
                        "{\"image\":\"null\",\"SurveyId\":6,\"UserId\":9,\"_id\":2,\"autopopulateOptionId\":0,\"chiefComplaintGroupId\":0,\"childEnabled\":0,\"conditionalDisplay\":0,\"deleted\":0,\"id\":171,\"label\":\"3 months \",\"languageId\":1,\"optionType\":\"Radio\",\"order\":2,\"parentId\":0,\"readonly\":0,\"reminder\":0,\"reminderDays\":0,\"requestPrescription\":0,\"severity\":\"low\",\"status\":1,\"suffixLabel\":\"null\",\"surveyQuestionId\":30,\"validationType\":\"null\",\"value\":\"3 months \"\n" +
                        "}\n" +
                        "]\n" +
                        "}" ;


        JsonObjectRequest jsonObjectReq = null ;
        try {
            jsonObjectReq = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(params) , new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        String status=response.getString("status");
                        String message=response.getString("message");
                        Log.d("response", status);
                        Log.d("msg" , message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }




        // Adding JsonObject request to request queue
        //   AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectReq,REQUEST_TAG);

        VolleyAppSingleton.getmVolleyAppSingletonInstance(getApplicationContext()).addToRequestQueue(jsonObjectReq, "");
    }

    public void volleyJsonArrayRequest() {

        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonArrayRequest";
       /* progressDialog.setMessage("Loading...");
        progressDialog.show();
*/
        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(jsonArrayURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(jsonArrayTag, response.toString());
                        LayoutInflater li = LayoutInflater.from(MainActivity.this);
                        showDialogView = li.inflate(R.layout.show_dialog, null);
                        outputTextView = (TextView) showDialogView.findViewById(R.id.text_view_dialog);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setView(showDialogView);
                        alertDialogBuilder
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                })
                                .setCancelable(false)
                                .create();
                        outputTextView.setText(response.toString());
                        alertDialogBuilder.show();
                        //  progressDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(jsonArrayTag, "Error: " + error.getMessage());
                progressDialog.hide();
            }
        });

        // Adding JsonObject request to request queue
        //   AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayReq, REQUEST_TAG);
        VolleyAppSingleton.getmVolleyAppSingletonInstance(getApplicationContext()).addToRequestQueue(jsonArrayReq, REQUEST_TAG);
    }


}
