package com.upc.healthyapp.utilities;

public class EspecificacionesDB {
    public static final String DB_NAME = "healthy";
    public static final int DB_VERSION = 1;

    //Creación de tablas
    public static final String CreateTableSintomas = "CREATE TABLE " + Constantes.TABLA_SINTOMA +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " descripcion TEXT NOT NULL, "+
            " fecha TEXT NOT NULL, "+
            " hora TEXT NOT NULL); ";

    public static final String DropTableSintomas = "DROP TABLE IF EXISTS " + Constantes.TABLA_SINTOMA + ";";
}
