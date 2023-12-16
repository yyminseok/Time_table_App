package com.example.gmap_minseok;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimetableActivity2 extends Activity{
    myDBHelper myHelper, themeHelper;
    SQLiteDatabase sqlDB, themeDB;
    FloatingActionButton set, listplusbtn;


    Integer[]  forest_theme ={R.color.greentheme1,R.color.greentheme2,R.color.greentheme3,R.color.greentheme4,
            R.color.greentheme5,R.color.greentheme6,R.color.greentheme7,R.color.greentheme8};

    Integer[]  sea_theme ={R.color.bluetheme1,R.color.bluetheme2,R.color.bluetheme3,R.color.bluetheme4,
            R.color.bluetheme5,R.color.bluetheme6,R.color.bluetheme7,R.color.bluetheme8};

    Integer[]  pink_theme ={R.color.pinktheme1,R.color.pinktheme2,R.color.pinktheme3,R.color.pinktheme4,
            R.color.pinktheme5,R.color.pinktheme6,R.color.pinktheme7,R.color.pinktheme8};

    Integer[]  aqua_theme ={R.color.aquatheme1,R.color.aquatheme2,R.color.aquatheme3,R.color.aquatheme4,
            R.color.aquatheme5,R.color.aquatheme6,R.color.aquatheme7,R.color.aquatheme8};
    private final long finishtimeed = 1000;
    private long presstime = 0;
    final String[] themeList={"포레스트", "바다","핑크","딥아쿠아"};
    Integer[]  themeToastList={R.drawable.theme_forest,R.drawable.theme_sea,R.drawable.theme_pink,R.drawable.theme_aqua};
    int startSetNum;
    String theme;


    TextView
            monday1,tuesday1,wednesday1,thursday1,friday1,
            monday2,tuesday2,wednesday2,thursday2,friday2,
            monday3,tuesday3,wednesday3,thursday3,friday3,
            monday4,tuesday4,wednesday4,thursday4,friday4,
            monday5,tuesday5,wednesday5,thursday5,friday5,
            monday6,tuesday6,wednesday6,thursday6,friday6,
            monday7,tuesday7,wednesday7,thursday7,friday7,
            monday8,tuesday8,wednesday8,thursday8,friday8,
            monday9,tuesday9,wednesday9,thursday9,friday9;



    Map<String, Integer[]> htheme = new HashMap<String, Integer[]>();




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable2);

        monday1 = (TextView) findViewById(R.id.monday1);
        tuesday1 = (TextView) findViewById(R.id.tuesday1);
        wednesday1 = (TextView) findViewById(R.id.wednesday1);
        thursday1 = (TextView) findViewById(R.id.thursday1);
        friday1 = (TextView) findViewById(R.id.friday1);

        monday2 = (TextView) findViewById(R.id.monday2);
        tuesday2 = (TextView) findViewById(R.id.tuesday2);
        wednesday2 = (TextView) findViewById(R.id.wednesday2);
        thursday2 = (TextView) findViewById(R.id.thursday2);
        friday2 = (TextView) findViewById(R.id.friday2);

        monday3 = (TextView) findViewById(R.id.monday3);
        tuesday3 = (TextView) findViewById(R.id.tuesday3);
        wednesday3 = (TextView) findViewById(R.id.wednesday3);
        thursday3 = (TextView) findViewById(R.id.thursday3);
        friday3 = (TextView) findViewById(R.id.friday3);

        monday4 = (TextView) findViewById(R.id.monday4);
        tuesday4 = (TextView) findViewById(R.id.tuesday4);
        wednesday4 = (TextView) findViewById(R.id.wednesday4);
        thursday4 = (TextView) findViewById(R.id.thursday4);
        friday4 = (TextView) findViewById(R.id.friday4);

        monday5 = (TextView) findViewById(R.id.monday5);
        tuesday5 = (TextView) findViewById(R.id.tuesday5);
        wednesday5 = (TextView) findViewById(R.id.wednesday5);
        thursday5 = (TextView) findViewById(R.id.thursday5);
        friday5 = (TextView) findViewById(R.id.friday5);

        monday6 = (TextView) findViewById(R.id.monday6);
        tuesday6 = (TextView) findViewById(R.id.tuesday6);
        wednesday6 = (TextView) findViewById(R.id.wednesday6);
        thursday6 = (TextView) findViewById(R.id.thursday6);
        friday6 = (TextView) findViewById(R.id.friday6);

        monday7 = (TextView) findViewById(R.id.monday7);
        tuesday7 = (TextView) findViewById(R.id.tuesday7);
        wednesday7 = (TextView) findViewById(R.id.wednesday7);
        thursday7 = (TextView) findViewById(R.id.thursday7);
        friday7 = (TextView) findViewById(R.id.friday7);

        monday8 = (TextView) findViewById(R.id.monday8);
        tuesday8 = (TextView) findViewById(R.id.tuesday8);
        wednesday8 = (TextView) findViewById(R.id.wednesday8);
        thursday8 = (TextView) findViewById(R.id.thursday8);
        friday8 = (TextView) findViewById(R.id.friday8);

        monday9 = (TextView) findViewById(R.id.monday9);
        tuesday9 = (TextView) findViewById(R.id.tuesday9);
        wednesday9 = (TextView) findViewById(R.id.wednesday9);
        thursday9 = (TextView) findViewById(R.id.thursday9);
        friday9 = (TextView) findViewById(R.id.friday9);

        set=(FloatingActionButton) findViewById(R.id.set);
        listplusbtn=(FloatingActionButton) findViewById(R.id.listplus_btn);

        TextView[] cellNameV = {monday1, tuesday1, wednesday1, thursday1, friday1,
                monday2, tuesday2, wednesday2, thursday2, friday2,
                monday3, tuesday3, wednesday3, thursday3, friday3,
                monday4, tuesday4, wednesday4, thursday4, friday4,
                monday5, tuesday5, wednesday5, thursday5, friday5,
                monday6, tuesday6, wednesday6, thursday6, friday6,
                monday7, tuesday7, wednesday7, thursday7, friday7,
                monday8, tuesday8, wednesday8, thursday8, friday8,
                monday9, tuesday9, wednesday9, thursday9, friday9};

        String[] cellNameK = {
                "Monday1", "Tuesday1", "Wednesday1", "Thursday1", "Friday1",
                "Monday2", "Tuesday2", "Wednesday2", "Thursday2", "Friday2",
                "Monday3", "Tuesday3", "Wednesday3", "Thursday3", "Friday3",
                "Monday4", "Tuesday4", "Wednesday4", "Thursday4", "Friday4",
                "Monday5", "Tuesday5", "Wednesday5", "Thursday5", "Friday5",
                "Monday6", "Tuesday6", "Wednesday6", "Thursday6", "Friday6",
                "Monday7", "Tuesday7", "Wednesday7", "Thursday7", "Friday7",
                "Monday8", "Tuesday8", "Wednesday8", "Thursday8", "Friday8",
                "Monday9", "Tuesday9", "Wednesday9", "Thursday9", "Friday9"};

        htheme.put("포레스트", forest_theme);
        htheme.put("바다", sea_theme);
        htheme.put("핑크", pink_theme);
        htheme.put("딥아쿠아", aqua_theme);


        startSetNum = 0;
        theme_update(); //디비에 저장된 테마 불러오기
        tableShow(cellNameV, cellNameK, htheme.get(theme));




        set.setOnClickListener(new View.OnClickListener() { // 시간표 테마 설정
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(TimetableActivity2.this);
                dlg.setTitle("테마 설정");
                dlg.setIcon(R.drawable.palette5);
                dlg.setPositiveButton("확인", null);
                dlg.setSingleChoiceItems(themeList, startSetNum, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_layout, null);
                        ImageView img = (ImageView) layout.findViewById(R.id.toast_image);

                        img.setImageDrawable(getResources().getDrawable(themeToastList[i]));
                        if (i == 0) {
                            theme_DBupdate("포레스트");
                            theme_update();
                        } else if (i == 1) {
                            theme_DBupdate("바다");
                            theme_update();
                        } else if (i == 2) {
                            theme_DBupdate("핑크");
                            theme_update();
                        } else {
                            theme_DBupdate("딥아쿠아");
                            theme_update();
                        }
                        tableShow(cellNameV, cellNameK, htheme.get(theme));
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(layout);
                        toast.show();
                        startSetNum = i;
                    }
                });
                dlg.show();
            }
        });

        listplusbtn.setOnClickListener(new View.OnClickListener() { //강의 리스트 관리 화면으로 이동
            @Override
            public void onClick(View view) {
                Intent inIntent = new Intent(getApplicationContext(), ListPlus.class);
                inIntent.putExtra("intheme",theme);
                startActivity(inIntent);
                finish();

            }
        });
        Intent themeIntent = getIntent();
        if(themeIntent.getStringExtra("outtheme")!=null) {
            theme = themeIntent.getStringExtra("outtheme");
            tableShow(cellNameV, cellNameK, htheme.get(theme));
        }

    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - presstime;

        if (0 <= intervalTime && finishtimeed >= intervalTime)
        {
            finish();
        }
        else
        {
            presstime = tempTime;
            Toast.makeText(getApplicationContext(), "한번더 누르시면 앱이 종료됩니다", Toast.LENGTH_SHORT).show();
        }
    }



    public void tableShow(TextView[] cellNameV, String[] cellNameK, Integer[] theme){ //시간표에 강의 표시
        myHelper = new myDBHelper(this);
        sqlDB=myHelper.getReadableDatabase();
        Cursor cursor;
        cursor=sqlDB.rawQuery("SElECT * FROM My_Timetable",null);
        ArrayList<String> strDate = new ArrayList<String>(); //수업 시간 저장할 리스트
        ArrayList<String> Subject = new ArrayList<String>(); //강의명 저장할 리스트
        Map<String, TextView> ht = new HashMap<String, TextView>();
        for(int i=0;i<45;i++){
            ht.put(cellNameK[i],cellNameV[i]);
        }
        ////셀 전부 초기화///////
        for(int i=0;i<cellNameK.length;i++){
            ht.get(cellNameK[i]).setText("");
            ht.get(cellNameK[i]).setBackground(getDrawable(R.drawable.cell));
        }

        while(cursor.moveToNext()) {
            strDate.add(cursor.getString(3)); // 리스트에 시간표값 추가
            Subject.add(cursor.getString(1)); // 리스트에 강의명 추가
        }

        for(int i=0;i<strDate.size();i++){
            String day_week=null;
            String[] day= strDate.get(i).split(","); //요일별로 구분
            for(int j=0;j< day.length;j++){
                String[] daytime = day[j].split(" "); //day_week:요일 daytime[]:시간(공백으로 구분)
                day_week=daytime[0].substring(0,1);
                daytime[0]=daytime[0].substring(1,2);

                int hour= daytime.length; //몇시간 수업인지 저장

                if (hour == 1) { //한 교시만 있을 경우
                    String cell_name = dayToEnglish(day_week) + daytime[0];// 선택할 셀의 이름
                    ht.get(cell_name).setText(Subject.get(i));
                    ht.get(cell_name).setBackground(getDrawable(theme[i]));
                    ht.get(cell_name).setTextColor(Color.parseColor("#ffffff"));
                    ht.get(cell_name).setTextSize(13);

                } else {
                    for (int k = 0; k < hour; k++) {
                        String cell_name = dayToEnglish(day_week) + daytime[k]; // 선택할 셀의 이름
                        if (k == 0) { //수업이 연속으로 있을경우 맨 처음에만 수업명을 표시
                            ht.get(cell_name).setText(Subject.get(i));
                            ht.get(cell_name).setTextSize(12);
                        }
                        ht.get(cell_name).setBackground(getDrawable(theme[i]));
                        ht.get(cell_name).setTextColor(Color.parseColor("#ffffff"));
                    }
                }

            }
        }
        cursor.close();
        sqlDB.close();

    }

    public void theme_update(){ //DB에 있는 테마 불러오기
        themeHelper=new myDBHelper(this);
        themeDB=themeHelper.getReadableDatabase();
        Cursor cursor2;
        cursor2=themeDB.rawQuery("SElECT * FROM tema_table",null);
        ArrayList<String> f = new ArrayList<String>();
        while(cursor2.moveToNext()){
            f.add(cursor2.getString(1));
        }
        theme=f.get(0);

        cursor2.close();
        themeDB.close();

        //테마에 따른 라디오버튼기본 위치값 설정
        if(theme.equals("포레스트")){
            startSetNum = 0;
            set.setColorPressedResId(R.color.greentheme2);
            listplusbtn.setColorPressedResId(R.color.greentheme2);
        }
        else if(theme.equals("바다")){
            startSetNum = 1;
            set.setColorPressedResId(R.color.bluetheme2);
            listplusbtn.setColorPressedResId(R.color.bluetheme2);
        }
        else if(theme.equals("핑크")){
            startSetNum = 2;
            set.setColorPressedResId(R.color.pinktheme2);
            listplusbtn.setColorPressedResId(R.color.pinktheme2);
        }
        else if(theme.equals("딥아쿠아")){
            startSetNum = 3;
            set.setColorPressedResId(R.color.aquatheme1);
            listplusbtn.setColorPressedResId(R.color.aquatheme1);
        }
    }

    public void theme_DBupdate(String intheme){ //DB에 테마 update
        themeHelper=new myDBHelper(this);
        themeDB=themeHelper.getWritableDatabase();
        themeDB.execSQL("UPDATE tema_table SET tema = '"+intheme+"' WHERE id=1");
        themeDB.close();
    }

    public String dayToEnglish(String day) //요일 한글을 영어로 바꿔서 반환
    {
        if (day.equals("월"))
            return "Monday";
        else if (day.equals("화"))
            return "Tuesday";
        else if (day.equals("수"))
            return "Wednesday";
        else if (day.equals("목"))
            return "Thursday";
        else if (day.equals("금"))
            return "Friday";
        else
            return "0";
    }


    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "GMapDB.db", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }



}