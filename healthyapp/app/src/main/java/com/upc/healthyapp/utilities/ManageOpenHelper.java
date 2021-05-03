package com.upc.healthyapp.utilities;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ManageOpenHelper extends SQLiteOpenHelper {

    public ManageOpenHelper(Context context){
        super(context, EspecificacionesDB.DB_NAME,null, EspecificacionesDB.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(EspecificacionesDB.CreateTableSintomas);
        }catch (SQLException ex){
            System.out.println("Error no se pudo crear TBL Sqlite......");
            throw ex;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try{
            db.execSQL(EspecificacionesDB.DropTableSintomas);
            db.execSQL(EspecificacionesDB.CreateTableSintomas);
        }catch (SQLException ex){
            System.out.println("Error no se pudo crear TBL Sqlite......");
            throw ex;
        }
    }

    public void limpiarBaseDatos() {
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            db.execSQL(EspecificacionesDB.DropTableSintomas);
            db.execSQL(EspecificacionesDB.CreateTableSintomas);
        }catch (SQLException ex){
            System.out.println("Error no se pudo crear TBL Sqlite......");
            throw ex;
        }

    }
}
