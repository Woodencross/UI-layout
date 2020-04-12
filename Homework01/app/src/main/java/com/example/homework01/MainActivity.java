package com.example.homework01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int color=1;//控制颜色赋值
    int[] tvcolors =new int[3];//储存目前颜色//在这不能赋值？
    boolean enabled=false;//是否可用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checkbox
        CheckBox cb=(CheckBox)findViewById(R.id.checkBox);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    enabled=true;
                }
                else{
                    enabled=false;
                }
            }
        });

        //radiogroup
        RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton:
                        color=1;
                        break;
                    case R.id.radioButton2:
                        color=2;
                        break;
                    case R.id.radioButton3:
                        color=3;
                        break;
                    case R.id.radioButton4:
                        color=4;
                        break;
                    case R.id.radioButton5:
                        color=5;
                        break;
                }
            }
        });

        Button bt1=(Button)findViewById(R.id.button);
        Button bt2=(Button)findViewById(R.id.button2);
        Button bt3=(Button)findViewById(R.id.button3);

        bt1.setOnClickListener(bListener);
        bt2.setOnClickListener(bListener);
        bt3.setOnClickListener(bListener);

        //这三个好像不能声明为全局变量，否则程序不能运行
        TextView tv1=(TextView)findViewById(R.id.textView);
        TextView tv2=(TextView)findViewById(R.id.textView2);
        TextView tv3=(TextView)findViewById(R.id.textView3);

        tv1.setOnClickListener(tListener);
        tv2.setOnClickListener(tListener);
        tv3.setOnClickListener(tListener);
    }

    //颜色赋值函数
    private void setColor(TextView tv){
        switch (color){
            case 1:
                tv.setBackgroundColor(Color.RED);
                break;
            case 2:
                tv.setBackgroundColor(Color.BLUE);
                break;
            case 3:
                tv.setBackgroundColor(Color.YELLOW);
                break;
            case 4:
                tv.setBackgroundColor(Color.GREEN);
                break;
            case 5:
                tv.setBackgroundColor(Color.WHITE);
        }
    }

    //重载一个，因为color的值与checkbox绑定，所以绕过color赋值
    private void setColor(TextView tv,int tmpcolor){
        switch (tmpcolor){
            case 1:
                tv.setBackgroundColor(Color.RED);
                break;
            case 2:
                tv.setBackgroundColor(Color.BLUE);
                break;
            case 3:
                tv.setBackgroundColor(Color.YELLOW);
                break;
            case 4:
                tv.setBackgroundColor(Color.GREEN);
                break;
            case 5:
                tv.setBackgroundColor(Color.WHITE);
        }
    }

    //textview事件处理
    private View.OnClickListener tListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),"This is a cute little textview.",Toast.LENGTH_SHORT).show();
        }
    };

    //对话框事件处理
    private DialogInterface.OnClickListener dListener=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            TextView tv1=(TextView)findViewById(R.id.textView);
            TextView tv2=(TextView)findViewById(R.id.textView2);
            TextView tv3=(TextView)findViewById(R.id.textView3);

            int[] seq =new int[3];
            Random r=new Random();
            do{
                for(int i=0;i<3;i++){
                    seq[i]=(int)(r.nextInt(100)%5)+1;
                }
            }while(seq[0]==2&&seq[1]==5&&seq[2]==1);//不能是蓝白红

            setColor(tv1,seq[0]);
            setColor(tv2,seq[1]);
            setColor(tv3,seq[2]);

            System.arraycopy(seq, 0, tvcolors, 0, 3);
        }
    };

    //button事件处理
    private View.OnClickListener bListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button bt=(Button)v;
            TextView tv1=(TextView)findViewById(R.id.textView);
            TextView tv2=(TextView)findViewById(R.id.textView2);
            TextView tv3=(TextView)findViewById(R.id.textView3);
            if(enabled) {
                switch (bt.getId()) {
                    case R.id.button:
                        setColor(tv1);
                        tvcolors[0]=color;
                        break;
                    case R.id.button2:
                        setColor(tv2);
                        tvcolors[1]=color;
                        break;
                    case R.id.button3:
                        setColor(tv3);
                        tvcolors[2]=color;
                        break;
                }
                if(tvcolors[0]==2&&tvcolors[1]==5&&tvcolors[2]==1){//蓝白红

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Congratulations!")
                            .setMessage("You just made a France flag!\n\nAnd this dialog is the 5th widget types~")
                            .setPositiveButton("OK, I'll do it again",dListener)
                            .show();
                }
            }
            else{
                Toast.makeText(v.getContext(),"Please enable the buttons.",Toast.LENGTH_SHORT).show();
            }
        }
    };
}
