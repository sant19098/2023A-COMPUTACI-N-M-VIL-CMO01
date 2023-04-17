package com.example.crudretrofit.sinterface;
import  com.example.crudretrofit.model.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.DELETE;
import retrofit2.http.*;


public interface CrudEmpleadoInterface {

    @GET("/consultarAll")
    Call<List<Empleado>> getAll();


    // @POST("/empleados")
    //  Call<Empleado> crearEmpleado(@Body Empleado empleado);
    @POST("empleados")
    static Call<Empleado> crearEmpleado(@Body Empleado empleado) {
        return null;
    }

    @DELETE("empleados/{id}")
    static Call<Void> eliminarEmpleado(@Path("id") Long id) {
        return null;
    }


    @PUT("empleados/{id}")
    static Call<Empleado> actualizarEmpleado(@Path("id") Long id, @Body Empleado empleado) {
        return null;
    }


}


