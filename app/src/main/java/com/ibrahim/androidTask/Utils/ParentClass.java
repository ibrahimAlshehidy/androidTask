package com.ibrahim.androidTask.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.ibrahim.androidTask.R;
import com.onurkaganaldemir.ktoastlib.KToast;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class ParentClass extends AppCompatActivity {

    protected static LocationManager locationManager;

    public static FragmentManager manager;
    public static SharedPrefManager sharedPrefManager;
    public static List<String> fragments = new ArrayList<String>();
    public static Typeface typeface_bold;
    SharedPreferences sharedPreferences;
    public static android.app.FragmentManager manager1;

    public static List<Integer> imageList = new ArrayList<Integer>();
    public static FlipProgressDialog fpd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("title",MODE_PRIVATE);
        imageList.add(R.drawable.ic_infinite_white);
        manager1 = getFragmentManager();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        sharedPrefManager = new SharedPrefManager(this);
        manager = getSupportFragmentManager();
        typeface_bold = Typeface.createFromAsset(getAssets(),"fonts/Cairo-SemiBold.ttf");
    }

    public static Bitmap resizeImage(Bitmap originalImage,float maxImageSize,
                                     boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / originalImage.getWidth(),
                (float) maxImageSize / originalImage.getHeight());
        int width = Math.round((float) ratio * originalImage.getWidth());
        int height = Math.round((float) ratio * originalImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(originalImage,width,
                height,filter);
        return newBitmap;
    }


    public String getRealPathFromUri(Context context,Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri,proj,null,null,null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void imageBrowse(int PICK_IMAGE_REQUEST) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent,PICK_IMAGE_REQUEST);
    }


    public static void replaceFragment(Fragment fragment,FrameLayout frameLayout) {
        String backStateName = fragment.getClass().getName();
        Log.e("backStateName",backStateName);

        //fragment not in back stack, create it.
        FragmentTransaction ft = manager.beginTransaction();
        if (!fragments.contains(backStateName)) {
            Log.e("check","added");
            // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            ft.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_out_right,R.anim.enter_from_right,R.anim.exit_out_left);
            ft.replace(frameLayout.getId(),fragment);
            ft.addToBackStack(backStateName);
            ft.commit();

            fragments.add(backStateName);
            System.out.println("backStateName" + fragments);
        } else {
            try {

            } catch (Exception e) {

            }

            Log.e("check","not_added");
            ft.replace(frameLayout.getId(),fragment);
            ft.commit();

        }
    }


    public static void handleException(Context context,Throwable t) {
        if (t instanceof SocketTimeoutException)
            makeErrorToast(context,"خطأ فى الانترنت");
        else if (t instanceof UnknownHostException)
            makeErrorToast(context,"خطأ فى الاتصال");

        else if (t instanceof ConnectException)
            makeErrorToast(context,"خطأ فى الاتصال");
        else
            makeErrorToast(context,t.getLocalizedMessage());

    }

    public void dismiss_keyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public Bitmap roundCornerImage(Bitmap raw,float round) {
        int width = raw.getWidth();
        int height = raw.getHeight();
        Bitmap result = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawARGB(0,0,0,0);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#000000"));

        final Rect rect = new Rect(0,0,width,height);
        final RectF rectF = new RectF(rect);

        canvas.drawRoundRect(rectF,round,round,paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
        canvas.drawBitmap(raw,rect,rect,paint);

        return result;
    }

    public static void makeSuccessToast(Context context,String msg) {
        KToast.customBackgroudToast((Activity) context,msg,Gravity.TOP,KToast.LENGTH_AUTO,R.drawable.background_toast,null,R.drawable.ic_infinite_white);
    }

    public static void makeErrorToast(Context context,String msg) {
        KToast.customBackgroudToast((Activity) context,msg,Gravity.TOP,KToast.LENGTH_AUTO,R.drawable.background_error_toast,null,R.drawable.ic_infinite_white);
    }


    public static void storeLang(String ln,Context context) {
        SharedPreferences settings = context.getSharedPreferences("language",
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("language",ln);
        editor.commit();
    }

    public static String getLang(Context context) {
        String value = "ar";
        final SharedPreferences prefs = context.getSharedPreferences(
                "language",0);
        if (!prefs.getString("language","language").equals("language")) {
            return prefs.getString("language","language");
        }
        return value;
    }

    public static void setDefaultLang(String ln,Context context) {
        Resources res = context.getResources();

        Locale locale = new Locale(ln);

        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        res.updateConfiguration(config,res.getDisplayMetrics());
        storeLang(ln,context);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLocale();
    }

    private void setLocale() {
        Locale locale = new Locale(getLang(this));
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }


    public static void LoadImageWithPicasso(String url,Context context,ImageView imageView) {
        if (!url.equals("")) {
            Glide
                    .with(context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.default_image)
                    .into(imageView);

//            Picasso.with(context).load(url).error(R.drawable.default_image).into(imageView);
        } else {
            Glide
                    .with(context)
                    .load(R.drawable.default_image)
                    .centerCrop()
                    .placeholder(R.drawable.default_image)
                    .into(imageView);
//            Picasso.with(context).load(R.drawable.default_image).error(R.drawable.default_image).into(imageView);
        }
    }

    public static void showFlipDialog() {
        fpd = new FlipProgressDialog();

        fpd.setImageList(imageList);                              // *Set a imageList* [Have to. Transparent background png recommended]
        fpd.setCanceledOnTouchOutside(true);                      // If true, the dialog will be dismissed when user touch outside of the dialog. If false, the dialog won't be dismissed.
        fpd.setDimAmount(0.3f);                                   // Set a dim (How much dark outside of dialog)

// About dialog shape, color
        fpd.setBackgroundColor(Color.parseColor("#FFFFFF"));      // Set a background color of dialog
        fpd.setBackgroundAlpha(1f);                             // Set a alpha color of dialog
        fpd.setBorderStroke(0);                                   // Set a width of border stroke of dialog
        fpd.setBorderColor(-1);                                   // Set a border stroke color of dialog
        fpd.setCornerRadius(16);                                  // Set a corner radius

// About image
        fpd.setImageSize(200);                                    // Set an image size
        fpd.setImageMargin(10);                                   // Set a margin of image

// About rotation
        fpd.setOrientation("rotationY");                          // Set a flipping rotation
        fpd.setDuration(600);                                     // Set a duration time of flipping ratation
        fpd.setStartAngle(0.0f);                                  // Set an angle when flipping ratation start
        fpd.setEndAngle(180.0f);                                  // Set an angle when flipping ratation end
        fpd.setMinAlpha(0.0f);                                    // Set an alpha when flipping ratation start and end
        fpd.setMaxAlpha(1.0f);
        try {
            fpd.show(manager1,"");                        // Show flip-progress-dialg

        } catch (Exception e) {

        }
    }

    public static void dismissFlipDialog() {
        try {
//            fpd.dismiss();                                            // Dismiss flip-progress-dialg
        } catch (Exception e) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager1 = getFragmentManager();
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager1 = getFragmentManager();

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    public static int calculateNoOfColumns(Context context,int width) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / width);
        // Where 180 is the width of your grid item. You can change it as per your convention.
        return noOfColumns;
    }


}
