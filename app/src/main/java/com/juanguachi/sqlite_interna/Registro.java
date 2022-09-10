package com.juanguachi.sqlite_interna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    Button mibotonConfirmar ,mibotonLogin;
    private EditText name, email, password;
    FirebaseFirestore mFirestore;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        firebaseAuth= FirebaseAuth.getInstance();

        email =(EditText) findViewById(R.id.txtCorreoR);
        password=(EditText) findViewById(R.id.txtContraseñaR);

        mibotonConfirmar= (Button) findViewById(R.id.btnRegistro);
        mibotonLogin=(Button) findViewById(R.id.btnLogin);

        progressDialog=new ProgressDialog(this);

        mibotonConfirmar.setOnClickListener(this);
        mibotonLogin.setOnClickListener(this);
    }

    private void RegistrarUsuario(){
        //Obtenemos el correo y la contraseña de las cajas de texto
        String emailUser=email.getText().toString().trim();
        String passUser=password.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacios
        if(TextUtils.isEmpty(emailUser)){
            Toast.makeText(Registro.this, "Porfavor ingrese el email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passUser)){
            Toast.makeText(Registro.this, "Porfavor ingrese una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Realizando el registro...");
        progressDialog.show();

        ///creación de nuevo usuario
        firebaseAuth.createUserWithEmailAndPassword(emailUser,passUser)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                                Toast.makeText(Registro.this, "Se ha registrado el usuario con el email: " + email.getText(), Toast.LENGTH_SHORT).show();
                        }else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(Registro.this, "Este usuario ya esta registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Registro.this, "Ocurrio un error al registrar", Toast.LENGTH_SHORT).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void LoguearUsuario() {
        //Obtenemos el correo y la contraseña de las cajas de texto
        String emailUser=email.getText().toString().trim();
        String passUser=password.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacios
        if(TextUtils.isEmpty(emailUser)){
            Toast.makeText(Registro.this, "Porfavor ingrese el email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passUser)){
            Toast.makeText(Registro.this, "Porfavor ingrese una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Ingresando al sistema...");
        progressDialog.show();

        ///Consultar si el usuario existe y loguear usuario
        firebaseAuth.signInWithEmailAndPassword(emailUser,passUser)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(Registro.this,MainActivity.class));
                            Toast.makeText(Registro.this, "Bienvenido" + email.getText(), Toast.LENGTH_SHORT).show();
                        }else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(Registro.this, "Este usuario ya esta registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Registro.this, "Ocurrio un error al ingresar", Toast.LENGTH_SHORT).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnRegistro:
                //Se invoca al metodo de creación
                RegistrarUsuario();
                break;

            case R.id.btnLogin:
                LoguearUsuario();
                break;
        }

    }
}