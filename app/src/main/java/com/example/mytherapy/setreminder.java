package com.example.mytherapy;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Random;

import static android.content.Context.ALARM_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class setreminder extends AppCompatDialogFragment {
    private EditText title,morning_pill,afternoon_pill,night_pill,description;
    private TextView startdate,enddate,mrtime,aftime,nttime;
    private Button button;
    private int hours,minutes;
    DatePickerDialog datePickerDialog;
    private int notification_id=1;
    private FirebaseAuth firebaseAuth;
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        final View view=layoutInflater.inflate(R.layout.activity_setreminder,null);
        builder.setView(view);
        title=view.findViewById(R.id.Titlename);
        morning_pill=view.findViewById(R.id.morning);
        afternoon_pill=view.findViewById(R.id.afternoon);
        night_pill=view.findViewById(R.id.night);
        description=view.findViewById(R.id.description);
        startdate=view.findViewById(R.id.startdate);
        enddate=view.findViewById(R.id.enddate);
        mrtime=view.findViewById(R.id.morningtime);
        aftime=view.findViewById(R.id.afternoontime);
        nttime=view.findViewById(R.id.nighttime);

        button=view.findViewById(R.id.setreminder);
        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calender();
            }

        });
        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calender1();
            }

        });
        mrtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timesetter();
            }

        });
        aftime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timesetter1();
            }

        });
        nttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timesetter2();
            }

        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((title.getText().toString()).isEmpty()
                        &(description.getText().toString()).isEmpty()
                        & (startdate.getText().toString()).isEmpty()
                        & (enddate.getText().toString()).isEmpty())
                {
                    Toast.makeText(getContext(),"Some fields are missing",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    storereminder rem=new storereminder(title.getText().toString(),description.getText().toString(),
                            morning_pill.getText().toString(),afternoon_pill.getText().toString(),
                            night_pill.getText().toString(),startdate.getText().toString(),enddate.getText().toString());
                    firebaseAuth=FirebaseAuth.getInstance();
                    Random random=new Random();
                    FirebaseDatabase.getInstance().getReference("Reminder")
                            .child(firebaseAuth.getCurrentUser().getUid()).child("Reminder"+title.getText().toString()).setValue(rem).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getContext(),"Reminder successfully set!!!",Toast.LENGTH_LONG).show();
                                notifyme();
                                getDialog().dismiss();
                            }
                            else
                            {
                                Toast.makeText(getContext(),"Error setting reminder!!!",Toast.LENGTH_LONG).show();
                            }
                        }

                });
                }
            }
        });
        return builder.create();
    }
    private void notifyme()
    {
        AlarmManager alarmManager=(AlarmManager)getContext().getSystemService(ALARM_SERVICE);
        Intent intent=new Intent(getActivity(),AlarmReciver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(getActivity(),1,intent,0);
        Calendar starttime=Calendar.getInstance();
        starttime.set(Calendar.HOUR_OF_DAY,hours);
        starttime.set(Calendar.MINUTE,minutes);
        starttime.set(Calendar.SECOND,0);
        long alarmstarttime=starttime.getTimeInMillis();
        alarmManager.set(AlarmManager.RTC_WAKEUP,alarmstarttime,pendingIntent);
        Toast.makeText(getActivity(),"Invalid time",Toast.LENGTH_SHORT).show();
    }

    private void timesetter() {
        TimePickerDialog time = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mrtime.setText(hourOfDay+":"+minute+"hrs");
                hours=hourOfDay;
                minutes=minute;

            }
        },Calendar.HOUR_OF_DAY,Calendar.MINUTE,true);
        time.show();
    }
    private void timesetter1() {
                TimePickerDialog time = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            aftime.setText(hourOfDay+":"+minute+"hrs");

                    }
                },Calendar.HOUR_OF_DAY,Calendar.MINUTE,true);
                time.show();

    }
    private void timesetter2() {
        TimePickerDialog time = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                nttime.setText(hourOfDay+":"+minute+"hrs");


            }
        },Calendar.HOUR_OF_DAY,Calendar.MINUTE,true);
        time.show();
    }

    public void calender()
    {

        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final Calendar newcalender=Calendar.getInstance();
                newcalender.set(year,month,dayOfMonth);
                 startdate.setText(dayOfMonth+"/"+(month+1)+"/"+year);

            }
        },year,month,day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
    public void calender1()
    {

        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final Calendar newcalender=Calendar.getInstance();
                newcalender.set(year,month,dayOfMonth);
                enddate.setText(dayOfMonth+"/"+(month+1)+"/"+year);

            }
        },year,month,day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
}
class storereminder
{
   public String title,description, morning_pill,afternoon_pill,night_pill, start_date, end_date;
    public storereminder()
    {

    }
    public storereminder(String title,String description,String morning_pill,String afternoon_pill,String night_pill,
                         String start_date,String end_date)
    {
        this.title=title;
        this.description=description;
        this.morning_pill=morning_pill;
        this.afternoon_pill=afternoon_pill;
        this.night_pill=night_pill;
        this.start_date=start_date;
        this.end_date=end_date;
    }
}