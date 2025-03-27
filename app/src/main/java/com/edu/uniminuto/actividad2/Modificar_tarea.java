package com.edu.uniminuto.actividad2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class Modificar_tarea extends AppCompatActivity {
    private TextView tv_tarea;
    private String selectedItem;
    private EditText etModify;
    private Button btnModify;
    private Button btnDelete;
    private Intent returnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_tarea);

        this.reference();
        this.show_item();
        this.btnModify.setOnClickListener(this::sendModifiedTask);
        this.btnDelete.setOnClickListener(this::deleteTask);
    }
    private void show_item(){
        this.selectedItem = getIntent().getStringExtra("item");
        this.tv_tarea.setText("Tarea a modificar: " + selectedItem);
    }
    private void sendModifiedTask(View view) {
        String newTask = etModify.getText().toString().trim();
        if (!newTask.isEmpty()) {
            this.returnIntent = new Intent();
            returnIntent.putExtra("new_task", newTask);
            returnIntent.putExtra("position", getIntent().getIntExtra("position", -1));
            setResult(RESULT_OK, returnIntent);
            Toast.makeText(this, "Tarea Modificada", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Ingrese una tarea válida", Toast.LENGTH_LONG).show();
        }
    }
    private void deleteTask(View view) {
        this.returnIntent = new Intent();
        returnIntent.putExtra("position", getIntent().getIntExtra("position", -1));
        returnIntent.putExtra("delete", true);
        setResult(RESULT_OK, returnIntent);
        Toast.makeText(this, "Tarea Eliminda", Toast.LENGTH_LONG).show();
        finish();
    }
    private void reference(){
        this.tv_tarea = findViewById(R.id.tv_tarea);
        this.etModify = findViewById(R.id.etModify);
        this.btnModify = findViewById(R.id.btnModify);
        this.btnDelete = findViewById(R.id.btnDelete);
    }
}
