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

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnSearch)   Button btnSearch;
    @BindView(R.id.rvUserList)      RecyclerView rvUserList;

    private static final String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog progressDoalog;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog();

        UserServices service = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        final Observable<List<UserDetails>> call = service.getAllDetail();


        call
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ResourceObserver<List<UserDetails>>() {

                    @Override
                    public void onComplete() {

                        progressDoalog.dismiss();
                        Log.d(TAG, "In onCompleted()");
                    }

                    @Override
                    public void onNext(List<UserDetails> retroUserDetails) {

                        System.out.println("OnNext...");
                        Log.d(TAG, "In onNext() " +retroUserDetails );
                        generateDataList(retroUserDetails);

                    }

                    @Override
                    public void onError(Throwable e) {

                        progressDoalog.dismiss();
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                    }
                });
    }


    private void progressDialog() {
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
    }


    private void generateDataList(List<UserDetails> userList) {
        adapter = new UserAdapter(this, userList);
        rvUserList.setHasFixedSize(true);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        rvUserList.setAdapter(adapter);
    }

    private void showToast() {
        Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
    }
}
