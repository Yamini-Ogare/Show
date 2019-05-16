package ogare.show;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    ListAdapter adapter ;
    ArrayList<File> arrayList = new ArrayList<>();
    final int STORAGE_PERMISSION=1;
    final int SUB_REQUEST=2;
 //   final int IMG_REQUEST=3;
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.sub);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ListAdapter(arrayList, MainActivity.this);
        recyclerView.setAdapter(adapter);

        openFolder(SUB_REQUEST);


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==STORAGE_PERMISSION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                openFolder(SUB_REQUEST);

            }
        }
    }


    private void openFolder(int sub_request) {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION);

        }else {

            // get access to the folder named Show

            File storageDir = new File(Environment.getExternalStorageDirectory(),"Show");

            if(!storageDir.exists()){

                storageDir.mkdirs();

            }

            File f = new File(storageDir.getPath());
            File file[] = f.listFiles();


            if(file.length>0) {

                for(i=0; i<file.length ; i++)
                arrayList.add(file[i]);

            }

            adapter.notifyDataSetChanged();

        }


    }


}
