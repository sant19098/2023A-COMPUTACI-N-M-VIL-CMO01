package com.example.crudretrofit;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getAll();
        crearEmpleado();
        eliminarEmpleado();
        Actualizar();
//--------------------------------------------------------------------------------







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



                }

                @Override
                public void onFailure(Call<List<Empleado>> call, Throwable t) {
                    //System.out.println(t.getMessage());
                    Log.e("Throw error:", t.getMessage());
                }
            });
        }


    private void crearEmpleado() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.1.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNombre("Juan");
        nuevoEmpleado.setPassword("123");
        nuevoEmpleado.setEmail("juan@email.com");

        CrudEmpleadoInterface CrudEmpleadoInterface = retrofit.create(com.example.crudretrofit.sinterface.CrudEmpleadoInterface.class);
        Call<List<Empleado>> call = CrudEmpleadoInterface.getAll();

        CrudEmpleadoInterface.CrearEmpleado(nuevoEmpleado).enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if (response.isSuccessful()) {
                    Empleado empleadoCreado = response.body();
                    Empleado nuevoEmpleado = response.body();

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
    //Long id, String nombre, String password, String email




    private void Actualizar() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.1.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CrudEmpleadoInterface crudEmpleadosInterface = retrofit.create(CrudEmpleadoInterface.class);

        Empleado empleado = new Empleado();

        Call<Empleado> call = crudEmpleadosInterface.Actualizar(empleado);

        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if (response.isSuccessful()) {
                    Log.i("UPDATE", "Empleado actualizado con éxito");
                } else {
                    Log.e("UPDATE", "Error al actualizar el empleado");
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("UPDATE", t.getMessage());
            }
        });
    }


    private void eliminarEmpleado() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.1.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        Call<Void> call = CrudEmpleadoInterface.eliminarEmpleado(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Empleado eliminado correctamente", Toast.LENGTH_SHORT).show();
                    // Actualizar la lista de empleados para reflejar el cambio
                    getAll();
                } else {
                    Toast.makeText(MainActivity.this, "Error al eliminar el empleado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }


}