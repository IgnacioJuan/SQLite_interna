package com.juanguachi.sqlite_interna;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.juanguachi.sqlite_interna.model.Cliente;

public class MainActivity extends AppCompatActivity {

    EditText txtid, txtnombre, txtapellido;
    Button btnagregar,btnlistar,btnactualizar,btneliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtid = (EditText) findViewById(R.id.txt_id);
        txtnombre = (EditText) findViewById(R.id.txt_nombre);
        txtapellido = (EditText) findViewById(R.id.txta_apellido);

        btnagregar = (Button) findViewById(R.id.btn_agregar);
        btnagregar.setOnClickListener(guardarListener);
        btnlistar  = (Button) findViewById(R.id.btn_listar);
        btnlistar.setOnClickListener(listarListener);
        btnactualizar  = (Button) findViewById(R.id.btn_editar);
        btnactualizar.setOnClickListener(actualizarListener);
        btneliminar  = (Button) findViewById(R.id.btn_eliminar);
        btneliminar.setOnClickListener(eliminarListener);
    }


    View.OnClickListener listarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ListView listView=(ListView) findViewById(R.id.listclientes);
            Cursor cursor=Cliente.listarClientes(getApplicationContext());
            String[] desde = new String[]{"id","nombre","apellido"};
            int[] hasta = new int[]{R.id.txtID,R.id.txtNOMBRE,R.id.txtAPELLIDO};

            CursorAdapter cursorAdapter= new SimpleCursorAdapter(
                    getApplicationContext(),
                    R.layout.detalle_clientes,
                    cursor,
                    desde,
                    hasta,0
            );
            listView.setAdapter(cursorAdapter);
        }
    };
    View.OnClickListener guardarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cliente cliente = new Cliente();
            cliente.setId(txtid.getText().toString());
            cliente.setNombre(txtnombre.getText().toString());
            cliente.setApellido(txtapellido.getText().toString());

            cliente.agregarCliente(getApplicationContext());
            limpiar();
        }
    };

    View.OnClickListener actualizarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cliente cliente = new Cliente();
            cliente.setId(txtid.getText().toString());
            cliente.setNombre(txtnombre.getText().toString());
            cliente.setApellido(txtapellido.getText().toString());

            cliente.actualizarCliente(getApplicationContext());
            limpiar();
        }
    };
    View.OnClickListener eliminarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cliente cliente = new Cliente();
            cliente.setId(txtid.getText().toString());

            cliente.eliminarCliente(getApplicationContext());
            limpiar();
        }
    };

    public void limpiar(){
        txtid.setText("");
        txtnombre.setText("");
        txtapellido.setText("");

    }
}