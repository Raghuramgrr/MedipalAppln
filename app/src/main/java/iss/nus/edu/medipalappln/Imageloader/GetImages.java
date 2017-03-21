package iss.nus.edu.medipalappln.Imageloader;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.TableLayout;

import iss.nus.edu.medipalappln.activity.Welcome;

public class GetImages extends AsyncTask<Void, Void, Void>
{
    TableLayout image_table=null;
    Welcome imgobj= new Welcome();
    public ProgressDialog progDialog=null;

    protected void onPreExecute()
    {
      //  progDialog=ProgressDialog.show(context, "", "Loading...",true);
    }

    public GetImages() {
    }

    @Override

    protected Void doInBackground(Void... params)
    {

       imgobj.image_drawable.clear();
        for(int i=0; i<imgobj.image_list.size(); i++)
        {
            Bitmap bitmap = BitmapFactory.decodeFile(imgobj.image_list.get(i).toString().trim());
            bitmap = Bitmap.createScaledBitmap(bitmap,500, 500, true);
            Drawable d=loadImagefromurl(bitmap);

            imgobj.image_drawable.add(d);
        }
        return null;
    }

    public Drawable loadImagefromurl(Bitmap icon)
    {
        Drawable d=new BitmapDrawable(icon);
        return d;
    }


    protected void onPostExecute(Void result)
    {

    }

    }


