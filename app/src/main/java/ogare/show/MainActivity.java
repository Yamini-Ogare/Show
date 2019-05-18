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
import android.view.View;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListAdapter adapter;
    ArrayList<File> arrayList = new ArrayList<>();
    final int READ_STORAGE_PERMISSION = 1;
    //   final int WRITE_STORAGE_PERMISSION = 3;

    final int SUB_REQUEST = 2;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.sub);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ListAdapter(arrayList, 1, MainActivity.this);
        recyclerView.setAdapter(adapter);

        openFolder(SUB_REQUEST);


    }


    // Ask for permission to access external storage
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == READ_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                openFolder(SUB_REQUEST);

            }
        }

    }


    private void openFolder(int sub_request) {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_STORAGE_PERMISSION);

        } else {

            boolean success = true;

            // get access to the folder named Show

            File storageDir = new File(Environment.getExternalStorageDirectory(), "Show");
            // if Folder does not exist then create folder
            if (!storageDir.exists()) {
                    success = storageDir.mkdir();
                    Toast.makeText(getApplicationContext(), "Folder created", Toast.LENGTH_LONG).show();

            }

                if (success) {
                    // Access the storage path and get all its file

                    File f = new File(storageDir.getPath());
                    File file[] = f.listFiles();

                    if (file == null) {
                        Toast.makeText(getApplicationContext(), "Folder is empty", Toast.LENGTH_LONG).show();
                    } else {

                        if (file.length > 0) {

                            for (i = 0; i < file.length; i++)
                                arrayList.add(file[i]);

                        }

                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error encountered", Toast.LENGTH_LONG).show();
                }
            }


        }
    }







