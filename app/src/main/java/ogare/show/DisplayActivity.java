package ogare.show;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    ListAdapter adapter ;
    ArrayList<File> arrayList = new ArrayList<>();
    File folder = null ;
    File file[];
    TextView textview ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        recyclerView = (RecyclerView)findViewById(R.id.list_image);
        textview = (TextView)findViewById(R.id.subname);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            folder = (File)bundle.get("File");
            file = folder.listFiles();

        }

        textview.setText(folder.getName());

        if(file.length>0) {

            for(int i=0; i<file.length ; i++)
                arrayList.add(file[i]);

        }

        //Make toast when the folder is empty

        if(arrayList.size() == 0)
        {  Toast.makeText(getApplicationContext() ,"No Image Files",Toast.LENGTH_LONG).show(); }

        else {

            adapter = new ListAdapter(arrayList, 2, DisplayActivity.this);
            recyclerView.setAdapter(adapter);
        }

    }
}
