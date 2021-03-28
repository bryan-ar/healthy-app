package com.upc.healthyapp.utilities;

import android.app.Activity;
import android.content.Intent;

public class UtlFunciones {
    public static void IniciarActividad(Activity activityDesde, Class<?> claseHacia, boolean finalizarActivity) {
        Intent intentSplash = new Intent(activityDesde, claseHacia);
        activityDesde.startActivity(intentSplash);

        if (finalizarActivity)
            activityDesde.finish();
    }
}
