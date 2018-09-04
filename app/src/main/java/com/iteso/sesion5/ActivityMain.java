package com.iteso.sesion5;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityMain extends AppCompatActivity {
    EditText name;
    EditText phone;
    TextView school_year;
    Spinner grado;
    TextView gender;
    RadioGroup gender_buttons;
    RadioButton fem;
    RadioButton masc;
    TextView book;
    AutoCompleteTextView book_name;
    CheckBox sport;
    Button clean;

    String gen = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.activity_main_names);
        phone = (EditText) findViewById(R.id.activity_main_phonenum);
        school_year = (TextView) findViewById(R.id.activity_main_school);
        grado = (Spinner) findViewById(R.id.activity_main_grado);
        gender = (TextView) findViewById(R.id.activity_main_gender);
        gender_buttons = (RadioGroup) findViewById(R.id.activity_main_buttons);
        fem = (RadioButton) findViewById(R.id.activity_main_fememino);
        masc = (RadioButton) findViewById(R.id.activity_main_masculino);
        book = (TextView) findViewById(R.id.activity_main_libro);
        book_name = (AutoCompleteTextView) findViewById(R.id.activity_main_bookname);
        sport = (CheckBox) findViewById(R.id.activity_main_deporte);
        clean = (Button) findViewById(R.id.activity_main_limpiar);

        //Spinner
        ArrayAdapter<CharSequence> schoolAdapter = ArrayAdapter.createFromResource(this,
                R.array.school_array, android.R.layout.simple_spinner_item);

        schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        grado.setAdapter(schoolAdapter);


        //AutoCompleteTextView
        String[] books = getResources().getStringArray(R.array.books_array);

        ArrayAdapter<String> bookAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, books);

        book_name.setAdapter(bookAdapter);


        //limpiar*********
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //mostrar diálogo
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMain.this);
                builder.setMessage(R.string.popup_menu_clean)
                        .setPositiveButton(R.string.popup_positive, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name.setText(R.string.activity_main_name);
                                phone.setText(R.string.activity_main_phone);
                                grado.setSelection(0);
                                gender_buttons.clearCheck();
                                book_name.setText("");
                                sport.setChecked(false);
                            }
                        })
                        .setNegativeButton(R.string.popup_negative, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
            }
        });
    }

    //Radio Buttons
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.activity_main_fememino:
                if (checked) {
                    gen = "Femenino";
                    break;
                }
            case R.id.activity_main_masculino:
                if (checked) {
                    gen = "Masculino";
                    break;
                }
        }
    }

    //checkbox
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.activity_main_deporte:
                if (checked) {
                    //                   checked = !checked;
                } else {
                    //                   checked = !checked;
                }
                break;
        }
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }

    //bo´ton de guardar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String yn = "";
        if (sport.isChecked()) {
            yn = "Sí";
        } else
            yn = "no";

        switch (item.getItemId()) {
            case R.id.activity_main_save:
                Toast save = Toast.makeText(ActivityMain.this,
                        "Nombre: " + name.getText()
                                + "\nTeléfono: " + phone.getText()
                                + "\nEscolaridad: " + (String)grado.getSelectedItem()
                                + "\nGénero: " + gen
                                + "\nLibro favorito: " + book_name.getText()
                                + "\nPractica deporte: " + yn
                         , Toast.LENGTH_LONG);
                save.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
