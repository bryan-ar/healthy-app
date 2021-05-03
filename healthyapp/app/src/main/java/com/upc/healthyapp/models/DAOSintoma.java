package com.upc.healthyapp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.google.firebase.database.connection.HostInfo;
import com.upc.healthyapp.utilities.Constantes;
import com.upc.healthyapp.utilities.ManageOpenHelper;

import java.util.ArrayList;

public class DAOSintoma {
    private ManageOpenHelper dbConexion;

    public DAOSintoma(Context context){
        dbConexion = new ManageOpenHelper(context);
    }

    public ArrayList<Sintoma> listarSintomas(){
        ArrayList<Sintoma> listaSintomas = new ArrayList<>();
        SQLiteDatabase db = dbConexion.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+  Constantes.TABLA_SINTOMA, null );
        while(c.moveToNext()){
            listaSintomas.add(new Sintoma(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3)
            ));
        }

        return listaSintomas;
    }

    public Sintoma obtenerSintoma(int codigoSintoma){
        Sintoma oSintoma = new Sintoma();
        SQLiteDatabase db = dbConexion.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+  Constantes.TABLA_SINTOMA +
                " WHERE id = ?", new String[]{String.valueOf(codigoSintoma)} );
        while(c.moveToNext()){
            oSintoma = new Sintoma(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3)
            );
        }

        return oSintoma;
    }

    public int insertarSintoma(Sintoma oSintoma) {
        long id = 0;

        try {
            SQLiteDatabase db = dbConexion.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("descripcion", oSintoma.getDescripcion());
            cv.put("fecha", oSintoma.getFecha());
            cv.put("hora", oSintoma.getHora());

            id = db.insert(Constantes.TABLA_SINTOMA, null, cv);
        } catch (Exception ex) {
            System.out.println("Error no pudo insertar datos.....insertarSintoma......#######: " + ex.getMessage());
        }

        return (int)id;
    }

    public boolean actualizarSintoma(Sintoma oSintoma){
        ContentValues cv = new ContentValues();
        cv.put("descripcion", oSintoma.getDescripcion());
        cv.put("fecha", oSintoma.getFecha());
        cv.put("hora", oSintoma.getHora());

        SQLiteDatabase db = dbConexion.getWritableDatabase();
        return db.update(Constantes.TABLA_SINTOMA,
                cv,
                "id=?",
                new String[]{String.valueOf(oSintoma.getId())}) > 0;
    }

    public boolean eliminarSintoma(int id){
        SQLiteDatabase db = dbConexion.getWritableDatabase();
        return db.delete(Constantes.TABLA_SINTOMA,
                "id=?",
                new String[]{String.valueOf(id)}) > 0;
    }
}
