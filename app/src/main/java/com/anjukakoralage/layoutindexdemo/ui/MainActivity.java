package com.anjukakoralage.layoutindexdemo.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.anjukakoralage.layoutindexdemo.R;
import com.anjukakoralage.layoutindexdemo.adapter.UserAdapter;
import com.anjukakoralage.layoutindexdemo.controller.RetrofitClientInstance;
import com.anjukakoralage.layoutindexdemo.data.UserServices;
import com.anjukakoralage.layoutindexdemo.model.UserDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnSearch)   Button btnSearch;
    private RecyclerView rvCountryData;

    private static final String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog progressDoalog;
    private RecyclerView.Adapter  adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog();

        UserServices service = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);

        Call<String> call = service.getAllDetails();


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        writeTv(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                        //Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


    private void progressDialog() {
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
    }

    private void writeTv(String response){

        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(response);
            if(obj.optString("page").equals("1")){
                progressDoalog.hide();
                ArrayList<UserDetails> retroModelArrayList = new ArrayList<>();
                JSONArray dataArray  = obj.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {

                    UserDetails retroModel = new UserDetails();
                    JSONObject dataobj = dataArray.getJSONObject(i);

                    retroModel.setId(dataobj.getString("id"));
                    retroModel.setFirst_name(dataobj.getString("first_name"));
                    retroModel.setLast_name(dataobj.getString("last_name"));
                    retroModel.setEmail(dataobj.getString("email"));
                    retroModel.setAvatar(dataobj.getString("avatar"));

                    retroModelArrayList.add(retroModel);

                }
                generateDataList(retroModelArrayList);


                for (int j = 0; j < retroModelArrayList.size(); j++){
                }

            }else {
                progressDoalog.hide();
                Toast.makeText(MainActivity.this, obj.optString("message")+"", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void generateDataList(ArrayList<UserDetails> retroModelArrayList) {

        rvCountryData = findViewById(R.id.rvUserList);
        adapter = new UserAdapter(this, retroModelArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rvCountryData.setLayoutManager(layoutManager);
        rvCountryData.setAdapter(adapter);
    }

    private void showToast() {
        Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
    }
}
