package com.example.listexample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.isfaaghyth.rak.Rak;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText search_list;
    EditText Search;
    Button go_search;
    Button check_gps;
    ListView listView;
    String names[]={"تخت جمشید","میدان نقش جهان","تخت سلیمان","ارگ بم","گنبد سلطانیه","بیستون"};
    String ImageUrl[]={"http://www.entekhab.ir/files/fa/news/1394/5/3/102264_885.jpg","http://www.entekhab.ir/files/fa/news/1394/5/3/102263_872.jpg","http://www.entekhab.ir/files/fa/news/1394/5/3/102265_716.jpg","http://images.hamshahrionline.ir/images/2011/6/arge-bam0.jpg","http://www.entekhab.ir/files/fa/news/1394/5/3/102267_708.jpg","http://www.entekhab.ir/files/fa/news/1394/5/3/102268_178.jpg"};
    String City[]={"فارس","اصفحان","تکاب","بم","زنجان","کرمانشاه"};
    List<ListObject> list;
    ListAdapter adapter;
    RadioGroup radioGroup;
    RadioButton radioCity;
    RadioButton radioName;
    int RadioButtonI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Rak.initialize(MainActivity.this);
        Bind_list();
        bind_value();
        setChanged();

        adapter=new ListAdapter(this,list);
        listView.setAdapter(adapter);
    }

    private void Bind_list() {

        list=new ArrayList<>();
            for (int i =0 ;i<names.length;i++){
                ListObject listObject = new ListObject(names[i],ImageUrl[i],City[i]);
                list.add(listObject);
            }

    }

    private void Check_gps() {
        Intent intent = new Intent(this,SubActivity.class);
        startActivityForResult(intent,150);
    }

    private void bind_value() {
        Search= (EditText) findViewById(R.id.search);
        go_search = (Button)findViewById(R.id.search_go_btn);
        check_gps = (Button)findViewById(R.id.Check_Gps);
        listView = (ListView)findViewById(R.id.list_view);
        search_list = (EditText)findViewById(R.id.search_list);
        radioGroup =(RadioGroup)findViewById(R.id.radioGender);
        radioCity = (RadioButton)findViewById(R.id.CityButton);
        radioName = (RadioButton)findViewById(R.id.nameButton);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String state;
        if (requestCode==150)
            if(resultCode== Activity.RESULT_OK){
                Boolean get = data.getBooleanExtra("r",false);
                if (get)state="On";
                else state="off";

                Toast.makeText(this, "Gps is "+state+"", Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Check_Gps:
                Check_gps();
                break;

            case R.id.search_go_btn:
                Uri uri = Uri.parse("http://"+Search.getText().toString()+"");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }
    public void setChanged(){
        check_gps.setOnClickListener(this);

        search_list.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                RadioButtonI=radioGroup.getCheckedRadioButtonId();
                radioName = (RadioButton)findViewById(RadioButtonI);
                String text = search_list.getText().toString();
                adapter.filter(text,radioName.getText().toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
