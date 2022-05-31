package ta.project.wisata.panorama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import ta.project.wisata.R;
import ta.project.wisata.panorama.ImageLoaderTask;

public class PanoActivity extends AppCompatActivity {

    private VrPanoramaView panoWidgetView;
    private ImageLoaderTask backgroundImageLoaderTask;
    String gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pano);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        panoWidgetView = (VrPanoramaView) findViewById(R.id.panoView);

        Intent intent = getIntent();
        gambar = intent.getStringExtra("gambar");

        loadPanoImage();


    }

    private synchronized void loadPanoImage() {
        ImageLoaderTask task = backgroundImageLoaderTask;
        if (task != null && !task.isCancelled()) {
            // Cancel any task from a previous loading.
            task.cancel(true);
        }

        // pass in the name of the image to load from assets.
        VrPanoramaView.Options viewOptions = new VrPanoramaView.Options();
        viewOptions.inputType = VrPanoramaView.Options.TYPE_MONO;

        // use the name of the image in the assets/ directory.
        String panoImageName = "tc.jpg";

        // create the task passing the widget view and call execute to start.
        task = new ImageLoaderTask(panoWidgetView, viewOptions, gambar);
        task.execute(this.getAssets());
        backgroundImageLoaderTask = task;
    }
}