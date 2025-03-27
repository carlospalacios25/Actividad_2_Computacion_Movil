package com.edu.uniminuto.actividad2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etTask;
    private Button btn3;
    private ListView listar;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private String select;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.init();
        this.btn3.setOnClickListener(this::addTask);
        this.listar.setOnItemClickListener(this::selection);
    }
    private void selection(AdapterView<?> parent, View view, int position, long id) {
        this.select = this.arrayList.get(position);
        this.intent = new Intent(MainActivity.this, Modificar_tarea.class);
        this.intent.putExtra("item", select);
        this.intent.putExtra("position", position);
        startActivityForResult(intent, 1);;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            int position = data.getIntExtra("position", -1);
            if (position != -1) {
                if (data.hasExtra("delete")) {
                    arrayList.remove(position);
                } else {
                    String newTask = data.getStringExtra("new_task");
                    arrayList.set(position, newTask);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }
    private void addTask(View view){
        // capturar el texto
        String task = this.etTask.getText().toString().trim();
        if (!task.isEmpty()){
            this.arrayList.add(task);
            this.adapter.notifyDataSetChanged();
            this.etTask.setText("");
        }else{
            Toast.makeText(this,"Coloque una tarea",Toast.LENGTH_LONG).show();
        }
    }

    private void init(){
        this.etTask = findViewById(R.id.etTask);
        this.btn3 = findViewById(R.id.btn3);
        this.listar = findViewById(R.id.listar);
        this.arrayList = new ArrayList<>();
        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,this.arrayList);
        this.listar.setAdapter(this.adapter);
    }

}