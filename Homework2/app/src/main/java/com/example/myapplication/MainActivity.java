package com.example.myapplication;

import static com.example.myapplication.Human.makeCalendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String FILE_NAME = "humans.json";
    private  List<Human> humans = new ArrayList<>();
    private HumanAdapter adapter;
    private static int normalColor = Color.WHITE;
    private static int selectedColor = Color.rgb(252, 219, 3);
    private static int currentItem = -1;
    private static View currentView = null;
    private boolean edit = false;
    private static Bitmap man;
    private static Bitmap woman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readFile();

        ListView listView = findViewById(R.id.lv);
        try{
            AssetManager manager = this.getAssets();
            InputStream stream = manager.open("man.png");
            man = BitmapFactory.decodeStream(stream);
            stream = manager.open("woman.png");
            woman = BitmapFactory.decodeStream(stream);
            stream.close();

        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        adapter = new HumanAdapter(
                this,
                R.layout.item_human,
                R.id.human_first_name
        );
        adapter.addAll(humans);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(currentItem != -1){
                    currentView.setBackgroundColor(normalColor);
                }
                currentItem = position;
                currentView = view;
                currentView.setBackgroundColor(selectedColor);
            }
        });
    }

    public static class HumanAdapter extends ArrayAdapter<Human>{

        public HumanAdapter(@NonNull Context context, int resource, int textViewResourceId) {
            super(context, resource, textViewResourceId);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            TextView humanFirstName = view.findViewById(R.id.human_first_name);
            TextView humanLastName = view.findViewById(R.id.human_last_name);
            TextView humanBirthDay = view.findViewById(R.id.human_bd);
            ImageView imageView = view.findViewById(R.id.human_img);

            Human human = getItem(position);
            humanFirstName.setText(human.getFirstName());
            humanLastName.setText(human.getLastName());
            humanBirthDay.setText(human.getBirthDayString());
            if(human.isGender())
            {
                imageView.setImageBitmap(woman);
            } else {
                imageView.setImageBitmap(man);
            }

            if (position == currentItem)
            {
                view.setBackgroundColor(selectedColor);
                currentView = view;
            }
            else
            {
                view.setBackgroundColor(normalColor);
            }

            return view;
        }
    }

    private void showCustomDialog(){

        View layoutInflater = getLayoutInflater().inflate(R.layout.dialog_add, null);
        EditText firstNameById = layoutInflater.findViewById(R.id.add_first_name);
        EditText lastNameById = layoutInflater.findViewById(R.id.add_last_name);
        TextView sexNameById = layoutInflater.findViewById(R.id.add_sex_text_view);
        ImageView image = layoutInflater.findViewById(R.id.add_pic);
        image.setImageBitmap(woman);
        DatePicker birthDay = layoutInflater.findViewById(R.id.add_bd);

        LinearLayout linearAddLayout = layoutInflater.findViewById(R.id.add_sex_linear_layout);
        linearAddLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sexNameById.getText().toString() == "Женщина")
                {
                    sexNameById.setText("Мужчина");
                    image.setImageBitmap(man);
                } else {
                    sexNameById.setText("Женщина");
                    image.setImageBitmap(woman);
                }
            }
        });

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        if(currentItem != -1 && edit == true)
        {
            firstNameById.setText(humans.get(currentItem).getFirstName());
            lastNameById.setText(humans.get(currentItem).getLastName());
            if(humans.get(currentItem).isGender())
            {
                sexNameById.setText("Женщина");
                image.setImageBitmap(woman);
            }
            else
            {
                sexNameById.setText("Мужчина");
                image.setImageBitmap(man);
            }
            String date = humans.get(currentItem).getBirthDayString();
            String[] dateArray = date.split("/");
            birthDay.init(Integer.parseInt(dateArray[2]),
                    Integer.parseInt(dateArray[1]) - 1,
                    Integer.parseInt(dateArray[0]),
                    new DatePicker.OnDateChangedListener() {
                        @Override
                        public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        }
                    });
        }

        alertDialog.setTitle("Добавить сотрудника")
                .setView(layoutInflater)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        boolean sex;
                        if(sexNameById.getText().toString().equals("Женщина")){
                            sex = true;
                        } else {
                            sex = false;
                        }
                        String firstName = firstNameById.getText().toString();
                        String lastName = lastNameById.getText().toString();
                        Human newHuman = new Human(firstName,
                                lastName,
                                sex,
                                makeCalendar(birthDay.getDayOfMonth(), birthDay.getMonth()+1,birthDay.getYear()));
                        if(edit) {
                            humans.set(currentItem, newHuman);
                            adapter.clear();
                            adapter.addAll(humans);
                        }
                        else{
                            humans.add(newHuman);
                            adapter.clear();
                            adapter.addAll(humans);
                        }
                        edit = false;
                        writeFile();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(edit)
                        {
                            edit = false;
                        }
                    }
                })
                .setCancelable(false)
                .create();
        alertDialog.show();
    }

    private void removeItem(){
        if(currentItem!= -1 && !humans.isEmpty())
        {
            humans.remove(currentItem);
            writeFile();
            adapter.clear();
            adapter.addAll(humans);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_add:
                showCustomDialog();
                return true;
            case R.id.item_remove:
                removeItem();
                return true;
            case R.id.item_edit:
                if(currentItem!=-1)
                {
                    edit = true;
                    showCustomDialog();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private void writeFile() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(humans);

        try (FileOutputStream outputStream = this.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            outputStream.write(jsonString.getBytes());
        } catch (Exception e) {
            Log.e("TAG", "Error", e);
        }

    }

    private void readFile(){
        Type itemsListType = new TypeToken<List<Human>>(){}.getType();
        try(FileInputStream fileInputStream = this.openFileInput(FILE_NAME);
            InputStreamReader streamReader = new InputStreamReader(fileInputStream)){
                Gson gson = new Gson();
                humans = gson.fromJson(streamReader, itemsListType);
        }
        catch (IOException ex){
            humans.add(new Human("Bill", "White", false, makeCalendar(13, 4, 1980)));
            humans.add(new Human("Marry", "Gray", true, makeCalendar(25, 10, 1985)));
            humans.add(new Human("Tom", "Black", false, makeCalendar(4, 12, 1982)));
            humans.add(new Human("Linda", "Blue", true, makeCalendar(17, 3, 1987)));
        }
    }
}