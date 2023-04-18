package com.example.crudretrofit;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.crudretrofit.model.Empleado;
import com.example.crudretrofit.sinterface.CrudEmpleadoInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    //private CrudEmpleadoInterface CrudEmpleadoInterface;
   // private ListView Empleado;
    private TextView txtId;
    private TextView txtNom;
    private TextView txtPass;
    private TextView txtMail;
    private Button btnver;
    private Button btnAgregar;
    private Button btnEliminar;
    private Button btnActualizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAll();
        CrearEmpleado();
        eliminarEmpleado();
        Actualizar();
//--------------------------------------------------------------------------------

        // Inicializar los elementos de la interfaz de usuario
        txtId = findViewById(R.id.txt_id);
        txtNom = findViewById(R.id.txt_nom);
        txtPass = findViewById(R.id.txt_pass);
        txtMail = findViewById(R.id.txt_mail);
        btnver = findViewById(R.id.btn_ver);
        btnAgregar = findViewById(R.id.btn_agregar);
        btnEliminar = findViewById(R.id.btn_eliminar);
        btnActualizar = findViewById(R.id.btn_actualizar);

        // Configurar los clics en los botones de acción
        btnver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAll();
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearEmpleado();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarEmpleado();
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actualizar();
            }
        });







    }//---------------------------------------------------

        private void getAll () {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://127.0.1.1:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            //Crea una instancia del servicio: Crea una instancia de la interfaz de servicio utilizando el objeto Retrofit:

            CrudEmpleadoInterface CrudEmpleadoInterface = retrofit.create(com.example.crudretrofit.sinterface.CrudEmpleadoInterface.class);
            Call<List<Empleado>> call = CrudEmpleadoInterface.getAll();

            call.enqueue(new Callback<List<Empleado>>() {
                @Override
                public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                    if (!response.isSuccessful()) {
                        Log.e("response err:,", response.message());
                        return;
                    }
                    List<Empleado> listEmpleado = response.body();
                    listEmpleado.forEach(p -> Log.i("Empleados:", p.toString()));

                    Empleado empleado = (Empleado) response.body();
                    txtId.setText("ID: " + empleado.getId());
                    txtNom.setText("Nombre: " + empleado.getNombre());
                    txtPass.setText("Contraseña: " + empleado.getPassword());
                    txtMail.setText("Correo electrónico: " + empleado.getEmail());

                }

                @Override
                public void onFailure(Call<List<Empleado>> call, Throwable t) {
                    //System.out.println(t.getMessage());
                    Log.e("Throw error:", t.getMessage());
                }
            });
        }


        private void CrearEmpleado () {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://127.0.1.1:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            CrudEmpleadoInterface CrudEmpleadoInterface = retrofit.create(com.example.crudretrofit.sinterface.CrudEmpleadoInterface.class);

            // Obtener los datos del nuevo empleado desde la interfaz de usuario
            String nom = txtNom.getText().toString();
            String pass = txtPass.getText().toString();
            String mail = txtMail.getText().toString();

            // Validar los datos del nuevo empleado
            if (TextUtils.isEmpty(nom) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(mail)) {
                Toast.makeText(MainActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            // Crear un objeto Empleado con los datos del nuevo empleado
           // Empleado empleado = new Empleado(null, nom, pass, mail);

            Empleado nuevoEmpleado = new Empleado();
            nuevoEmpleado.setNombre("Juan");
            nuevoEmpleado.setPassword("123");
            nuevoEmpleado.setEmail("juan@email.com");

           // Empleado nuevoEmpleado = new Empleado(2L, "John", "password123", "john@example.com");
            Call<Empleado> call = CrudEmpleadoInterface.CrearEmpleado(nuevoEmpleado);
            call.enqueue(new Callback<Empleado>() {
                @Override
                public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                    if (response.isSuccessful()) {
                        Empleado nuevoEmpleado = response.body();
                        Toast.makeText(MainActivity.this, "Empleado creado correctamente", Toast.LENGTH_SHORT).show();
                        // Actualizar los detalles del empleado actual para mostrar el nuevo empleado creado
                        getAll();
                        Log.i("POST", "Empleado creado con éxito");


                    } else {
                        Toast.makeText(MainActivity.this, "Error al crear el empleado", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Empleado> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                }
            });


        }

        private void eliminarEmpleado () {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://127.0.1.1:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            CrudEmpleadoInterface CrudEmpleadoInterface = retrofit.create(com.example.crudretrofit.sinterface.CrudEmpleadoInterface.class);
            // Obtener el ID del empleado actual desde la interfaz de usuario
            String id = txtId.getText().toString().substring(4);

//            Long idEmpleadoAEliminar = Long.valueOf(1);


            Call<Void> call = CrudEmpleadoInterface.eliminarEmpleado(Long.valueOf(id));
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        // El empleado se eliminó exitosamente
                        Toast.makeText(MainActivity.this, "Empleado eliminado correctamente", Toast.LENGTH_SHORT).show();
                        Log.i("DELETE", "Empleado eliminado con éxito");
                    }


                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error al realizar la solicitud", Toast.LENGTH_SHORT).show();
                }
            });

        }

        private void Actualizar () {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://127.0.1.1:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            CrudEmpleadoInterface CrudEmpleadoInterface = retrofit.create(com.example.crudretrofit.sinterface.CrudEmpleadoInterface.class);


            // Obtener los datos del nuevo empleado desde la interfaz de usuario
            String id = txtId.getText().toString().substring(4);
            String nom = txtNom.getText().toString();
            String pass = txtPass.getText().toString();
            String mail = txtMail.getText().toString();

            // Crear una instancia del objeto Empleado con los datos actualizados
            Empleado empleado = new Empleado(Long.parseLong(id), nom, pass, mail);

            //Long idEmpleadoAActualizar = Long.valueOf(1);
            // Empleado empleadoActualizado = new Empleado(1L, "Juan", "123", "juan@email.com");


            Call<Empleado> call = CrudEmpleadoInterface.Actualizar(Long.valueOf(id), empleado);
            call.enqueue(new Callback<Empleado>() {
                @Override
                public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                    if (response.isSuccessful()) {
                        Empleado empleadoActualizado = response.body();
                        txtNom.setText(empleadoActualizado.getNombre());
                        txtPass.setText(empleadoActualizado.getPassword());
                        txtMail.setText(empleadoActualizado.getEmail());



                        Toast.makeText(MainActivity.this, "Empleado actualizado correctamente", Toast.LENGTH_SHORT).show();
                        Log.i("UPDATE", "Empleado actualizado con éxito");


                    } else {
                        // Mostrar un error
                    }
                }

                @Override
                public void onFailure(Call<Empleado> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error al conectarse al servidor", Toast.LENGTH_SHORT).show();                }
            });

        }










    }

