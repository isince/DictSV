package nd.dictsv.Debug;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Since on 13/1/2558.
 */
public class Message {

    public static void longToast(Context context, String tag, String message)
    {
        Toast.makeText(context, tag + " : " + message, Toast.LENGTH_LONG).show();
    }

    public static void shortToast(Context context, String tag, String message)
    {
        Toast.makeText(context, tag + " : " + message, Toast.LENGTH_SHORT).show();
    }

    public static void toast2(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void LogE(String tag, String message){
        Log.e("Since",tag + " || " + message);
    }
}
