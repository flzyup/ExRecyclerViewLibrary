package com.ligux.tools.exrecyclerview;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ligux.tools.exrecyclerview.db.ImageMetaData;


public class MyActivity extends ActionBarActivity {

    private static final int MSG_INIT = 0;

    private HandlerThread mJobThread;
    private JobHandler mJobHandler;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mJobThread = new HandlerThread("job");
        mJobThread.start();
        mJobHandler = new JobHandler(mJobThread.getLooper());

        mDialog = new ProgressDialog(this);
        mDialog.setIndeterminate(true);
        mDialog.setMessage("Init data...");
        mDialog.show();
        mJobHandler.sendEmptyMessage(MSG_INIT);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mJobThread != null && mJobThread.isAlive()) {
            try {
                mJobThread.getLooper().quit();
                mJobThread.quit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class JobHandler extends Handler {
        public JobHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_INIT:
                    Cursor c = null;

                    try {
                        c = getContentResolver().query(ImageMetaData.CONTENT_URI, new String[]{ImageMetaData._ID}, null, null, null);
                        if (c != null && c.getCount() > 0) {
                            // Since the table has data, skip init.
                            return;
                        } else {
                            // Insert test data
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Abstract_Owl-wallpaper-10151995.jpg", "http://rom.ligux.com/wallpaper/Abstract_Owl-wallpaper-10151995.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Apple-wallpaper-10093335.jpg", "http://rom.ligux.com/wallpaper/Apple-wallpaper-10093335.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Awesome_Quote-wallpaper-10125630.jpg", "http://rom.ligux.com/wallpaper/Awesome_Quote-wallpaper-10125630.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Beautiful_Cat-wallpaper-10213474.jpg", "http://rom.ligux.com/wallpaper/Beautiful_Cat-wallpaper-10213474.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Beautiful_Landscape-wallpaper-10143595.jpg", "http://rom.ligux.com/wallpaper/Beautiful_Landscape-wallpaper-10143595.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Blue_Drops-wallpaper-10047201.jpg", "http://rom.ligux.com/wallpaper/Blue_Drops-wallpaper-10047201.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Blue_Note_3_Mesh-wallpaper-10005078.jpg", "http://rom.ligux.com/wallpaper/Blue_Note_3_Mesh-wallpaper-10005078.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Boat-wallpaper-10206317.jpg", "http://rom.ligux.com/wallpaper/Boat-wallpaper-10206317.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Brick_Wallpaper-wallpaper-10189329.jpg", "http://rom.ligux.com/wallpaper/Brick_Wallpaper-wallpaper-10189329.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Bricked-wallpaper-10183113.jpg", "http://rom.ligux.com/wallpaper/Bricked-wallpaper-10183113.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Britney_Spears-wallpaper-10233359.jpg", "http://rom.ligux.com/wallpaper/Britney_Spears-wallpaper-10233359.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Colorful_Abstract-wallpaper-10154655.jpg", "http://rom.ligux.com/wallpaper/Colorful_Abstract-wallpaper-10154655.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Colorful_umbrellas-wallpaper-10173624.jpg", "http://rom.ligux.com/wallpaper/Colorful_umbrellas-wallpaper-10173624.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Cubic-wallpaper-10107751.jpg", "http://rom.ligux.com/wallpaper/Cubic-wallpaper-10107751.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Cute_Sad_Pug-wallpaper-10199653.jpg", "http://rom.ligux.com/wallpaper/Cute_Sad_Pug-wallpaper-10199653.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Cyan_Xperia-wallpaper-9963616.jpg", "http://rom.ligux.com/wallpaper/Cyan_Xperia-wallpaper-9963616.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Cyclone-wallpaper-10066585.jpg", "http://rom.ligux.com/wallpaper/Cyclone-wallpaper-10066585.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Dew-wallpaper-9723942.jpg", "http://rom.ligux.com/wallpaper/Dew-wallpaper-9723942.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Droid_Dna-wallpaper-9859900.jpg", "http://rom.ligux.com/wallpaper/Droid_Dna-wallpaper-9859900.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Drops-wallpaper-9858151.jpg", "http://rom.ligux.com/wallpaper/Drops-wallpaper-9858151.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Earth-wallpaper-10232094.jpg", "http://rom.ligux.com/wallpaper/Earth-wallpaper-10232094.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Feather-wallpaper-10150286.jpg", "http://rom.ligux.com/wallpaper/Feather-wallpaper-10150286.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Forest_Light-wallpaper-10180717.jpg", "http://rom.ligux.com/wallpaper/Forest_Light-wallpaper-10180717.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("G-Flex-wallpaper-10102021.jpg", "http://rom.ligux.com/wallpaper/G-Flex-wallpaper-10102021.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("GALAXY_HD-wallpaper-10085468.jpg", "http://rom.ligux.com/wallpaper/GALAXY_HD-wallpaper-10085468.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("GTA_V_Girl-wallpaper-10203286.jpg", "http://rom.ligux.com/wallpaper/GTA_V_Girl-wallpaper-10203286.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("G_Pro-wallpaper-9853583.jpg", "http://rom.ligux.com/wallpaper/G_Pro-wallpaper-9853583.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Gear_Heart-wallpaper-10198754.jpg", "http://rom.ligux.com/wallpaper/Gear_Heart-wallpaper-10198754.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Geometric_Shapes-wallpaper-9969381.jpg", "http://rom.ligux.com/wallpaper/Geometric_Shapes-wallpaper-9969381.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Great_mountain_view-wallpaper-10171220.jpg", "http://rom.ligux.com/wallpaper/Great_mountain_view-wallpaper-10171220.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Hd_Letters-wallpaper-9806155.jpg", "http://rom.ligux.com/wallpaper/Hd_Letters-wallpaper-9806155.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("IronMan-wallpaper-10173654.jpg", "http://rom.ligux.com/wallpaper/IronMan-wallpaper-10173654.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Jelly_Blurred-wallpaper-9932051.jpg", "http://rom.ligux.com/wallpaper/Jelly_Blurred-wallpaper-9932051.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("King_of_the_jungle-wallpaper-10287491.jpg", "http://rom.ligux.com/wallpaper/King_of_the_jungle-wallpaper-10287491.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Lake_view_hd-wallpaper-10167296.jpg", "http://rom.ligux.com/wallpaper/Lake_view_hd-wallpaper-10167296.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Landscape-wallpaper-10225062.jpg", "http://rom.ligux.com/wallpaper/Landscape-wallpaper-10225062.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Leopard-wallpaper-10147713.jpg", "http://rom.ligux.com/wallpaper/Leopard-wallpaper-10147713.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Lg_2013-wallpaper-9966847.jpg", "http://rom.ligux.com/wallpaper/Lg_2013-wallpaper-9966847.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Love-wallpaper-10218897.jpg", "http://rom.ligux.com/wallpaper/Love-wallpaper-10218897.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Love_is-wallpaper-10181437.jpg", "http://rom.ligux.com/wallpaper/Love_is-wallpaper-10181437.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Love_is-wallpaper-10181441.jpg", "http://rom.ligux.com/wallpaper/Love_is-wallpaper-10181441.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Love_is-wallpaper-10181444.jpg", "http://rom.ligux.com/wallpaper/Love_is-wallpaper-10181444.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Magic_Night-wallpaper-10083559.jpg", "http://rom.ligux.com/wallpaper/Magic_Night-wallpaper-10083559.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Maldives-wallpaper-10190025.jpg", "http://rom.ligux.com/wallpaper/Maldives-wallpaper-10190025.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Man_on_the_Moon-wallpaper-10078734.jpg", "http://rom.ligux.com/wallpaper/Man_on_the_Moon-wallpaper-10078734.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Mclaren_P1-wallpaper-10055655.jpg", "http://rom.ligux.com/wallpaper/Mclaren_P1-wallpaper-10055655.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Minion_Artist-wallpaper-10190044.jpg", "http://rom.ligux.com/wallpaper/Minion_Artist-wallpaper-10190044.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Minions-wallpaper-10233059.jpg", "http://rom.ligux.com/wallpaper/Minions-wallpaper-10233059.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Moon_Light_Night-wallpaper-10127912.jpg", "http://rom.ligux.com/wallpaper/Moon_Light_Night-wallpaper-10127912.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Mountain_Lake-wallpaper-9830992.jpg", "http://rom.ligux.com/wallpaper/Mountain_Lake-wallpaper-9830992.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Mountain_Landscape-wallpaper-10186033.jpg", "http://rom.ligux.com/wallpaper/Mountain_Landscape-wallpaper-10186033.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Nature-wallpaper-10230711.jpg", "http://rom.ligux.com/wallpaper/Nature-wallpaper-10230711.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Nebula_Dark-wallpaper-9796970.jpg", "http://rom.ligux.com/wallpaper/Nebula_Dark-wallpaper-9796970.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Nexus_Variation-wallpaper-10126489.jpg", "http://rom.ligux.com/wallpaper/Nexus_Variation-wallpaper-10126489.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Night-wallpaper-10199390.jpg", "http://rom.ligux.com/wallpaper/Night-wallpaper-10199390.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Owl-wallpaper-10377338.jpg", "http://rom.ligux.com/wallpaper/Owl-wallpaper-10377338.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Owl-wallpaper-10377400.jpg", "http://rom.ligux.com/wallpaper/Owl-wallpaper-10377400.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Pattern_stone-wallpaper-10020076.jpg", "http://rom.ligux.com/wallpaper/Pattern_stone-wallpaper-10020076.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Pikachu-wallpaper-10204220.jpg", "http://rom.ligux.com/wallpaper/Pikachu-wallpaper-10204220.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Pooh-wallpaper-10197419.jpg", "http://rom.ligux.com/wallpaper/Pooh-wallpaper-10197419.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Puma_logo-wallpaper-10219666.jpg", "http://rom.ligux.com/wallpaper/Puma_logo-wallpaper-10219666.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Rail-wallpaper-10345536.jpg", "http://rom.ligux.com/wallpaper/Rail-wallpaper-10345536.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Romance-wallpaper-10201820.jpg", "http://rom.ligux.com/wallpaper/Romance-wallpaper-10201820.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("S-series_For_S4-wallpaper-9788549.jpg", "http://rom.ligux.com/wallpaper/S-series_For_S4-wallpaper-9788549.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("S5_Texture_Boxes-wallpaper-10233446.jpg", "http://rom.ligux.com/wallpaper/S5_Texture_Boxes-wallpaper-10233446.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("San_Fransisco_Bay-wallpaper-10198162.jpg", "http://rom.ligux.com/wallpaper/San_Fransisco_Bay-wallpaper-10198162.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Scarlett_Johansson-wallpaper-10185952.jpg", "http://rom.ligux.com/wallpaper/Scarlett_Johansson-wallpaper-10185952.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Seascape-wallpaper-10219973.jpg", "http://rom.ligux.com/wallpaper/Seascape-wallpaper-10219973.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Sense_Field-wallpaper-9953980.jpg", "http://rom.ligux.com/wallpaper/Sense_Field-wallpaper-9953980.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Ship-wallpaper-10224066.jpg", "http://rom.ligux.com/wallpaper/Ship-wallpaper-10224066.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Sky-wallpaper-9785711.jpg", "http://rom.ligux.com/wallpaper/Sky-wallpaper-9785711.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Spirit_Bomb-wallpaper-10193006.jpg", "http://rom.ligux.com/wallpaper/Spirit_Bomb-wallpaper-10193006.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Stone_Landscape-wallpaper-10047780.jpg", "http://rom.ligux.com/wallpaper/Stone_Landscape-wallpaper-10047780.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Stones-wallpaper-10171015.jpg", "http://rom.ligux.com/wallpaper/Stones-wallpaper-10171015.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Story-wallpaper-10189726.jpg", "http://rom.ligux.com/wallpaper/Story-wallpaper-10189726.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Summer_Vacation-wallpaper-10228459.jpg", "http://rom.ligux.com/wallpaper/Summer_Vacation-wallpaper-10228459.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Sunset-wallpaper-10219943.jpg", "http://rom.ligux.com/wallpaper/Sunset-wallpaper-10219943.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Sunset_Road-wallpaper-10115865.jpg", "http://rom.ligux.com/wallpaper/Sunset_Road-wallpaper-10115865.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("TMNT_2014_Mikey-wallpaper-10374819.jpg", "http://rom.ligux.com/wallpaper/TMNT_2014_Mikey-wallpaper-10374819.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Ted-wallpaper-9797926.jpg", "http://rom.ligux.com/wallpaper/Ted-wallpaper-9797926.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Tiger-wallpaper-10051302.jpg", "http://rom.ligux.com/wallpaper/Tiger-wallpaper-10051302.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Tiger-wallpaper-10142265.jpg", "http://rom.ligux.com/wallpaper/Tiger-wallpaper-10142265.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Tiger-wallpaper-10258147.jpg", "http://rom.ligux.com/wallpaper/Tiger-wallpaper-10258147.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Tiger_Abstract-wallpaper-10353597.jpg", "http://rom.ligux.com/wallpaper/Tiger_Abstract-wallpaper-10353597.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Time_to_Relax-wallpaper-10051661.jpg", "http://rom.ligux.com/wallpaper/Time_to_Relax-wallpaper-10051661.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Tracks-wallpaper-10224677.jpg", "http://rom.ligux.com/wallpaper/Tracks-wallpaper-10224677.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("UK_road-wallpaper-10183609.jpg", "http://rom.ligux.com/wallpaper/UK_road-wallpaper-10183609.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Vision-wallpaper-10196354.jpg", "http://rom.ligux.com/wallpaper/Vision-wallpaper-10196354.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Watercolor-wallpaper-10214563.jpg", "http://rom.ligux.com/wallpaper/Watercolor-wallpaper-10214563.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("White_Tiger-wallpaper-9879078.jpg", "http://rom.ligux.com/wallpaper/White_Tiger-wallpaper-9879078.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Wisdom-wallpaper-10234787.jpg", "http://rom.ligux.com/wallpaper/Wisdom-wallpaper-10234787.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Xiaomi_2-wallpaper-10073908.jpg", "http://rom.ligux.com/wallpaper/Xiaomi_2-wallpaper-10073908.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("Xperia_Z2_Connect-wallpaper-10201855.jpg", "http://rom.ligux.com/wallpaper/Xperia_Z2_Connect-wallpaper-10201855.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("autumn_leaf-wallpaper-10226792.jpg", "http://rom.ligux.com/wallpaper/autumn_leaf-wallpaper-10226792.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("clark_kent-wallpaper-10228842.jpg", "http://rom.ligux.com/wallpaper/clark_kent-wallpaper-10228842.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("cute_cat-wallpaper-10067997.jpg", "http://rom.ligux.com/wallpaper/cute_cat-wallpaper-10067997.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("cyan_roar-wallpaper-10175378.jpg", "http://rom.ligux.com/wallpaper/cyan_roar-wallpaper-10175378.jpg").fill());
                            getContentResolver().insert(ImageMetaData.CONTENT_URI, new ImageMetaData("one_piece-wallpaper-10232702.jpg", "http://rom.ligux.com/wallpaper/one_piece-wallpaper-10232702.jpg").fill());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (c != null && !c.isClosed()) {
                            c.close();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MyActivity.this, "data init success", Toast.LENGTH_SHORT).show();
                                mDialog.dismiss();
                            }
                        });
                    }

                    break;
            }
        }
    }
}
