package com.example.crudretrofit.sinterface;
import  com.example.crudretrofit.model.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.DELETE;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface CrudEmpleadoInterface {
    @GET("/consultarAll")
    Call<List<Empleado>> getAll();

    @POST("/guardar")//insertar
    Call<Empleado>CrearEmpleado(@Body Empleado empleado);

    @GET("/actualizar/{id}")
    Call<Empleado> Actualizar(@Path("id") Empleado id);


    @DELETE("/user/{id}")
    static Call<Void> eliminarEmpleado(@Path("id") Long id);

    @PATCH("actualizar/{id}")
    Call<Empleado> Actualizar(@Path("id") Long id, @Body Empleado empleado);




    /**
    @POST("/guardar")
    Call<List<Empleado>> crearEmpleado(@Body Empleado empleado) ;


    // static Call<Empleado> crearEmpleado(@Body Empleado empleado) {        return null;    }

    //@DELETE("/empleados/{id}")
   // static Call<Void> eliminarEmpleado(@Path("id") Long id) {        return null;    }

    @DELETE("/user/{id}")
    Call<Void> eliminarEmpleado(@Path("id") Long id);

    @PUT("/empleados/{id}")
    static Call<Empleado> actualizarEmpleado(@Path("id") Long id, @Body Empleado empleado) {
        return null;
    }

     **/



}


