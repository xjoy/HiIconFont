package me.faso.hiiconfont;

import java.util.Locale;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.shamanland.fonticon.FontIconDrawable;


public class MainActivity extends ActionBarActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar bar = getSupportActionBar();

        bar.setDisplayShowTitleEnabled(true);
        //icon font as drawable
        bar.setIcon( FontIconDrawable.inflate(this, R.xml.ic_android) );

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        /** Defining a listener for pageChange */
        ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                navigateToPos(position);
            }
        };

        /** Setting the pageChange listener to the viewPager */
        mViewPager.setOnPageChangeListener(pageChangeListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        navigateTo(id);

        return super.onOptionsItemSelected(item);
    }

    private void navigateTo(int id){

        switch (id){
            case R.id.action_settings:
                mViewPager.setCurrentItem(2,true);

                break;
            case R.id.action_about:

                mViewPager.setCurrentItem(1,true);
                break;
            case android.R.id.home:

                mViewPager.setCurrentItem(0,true);

                break;
        }

    }

    private void navigateToPos(int idx){


        ActionBar abar = getSupportActionBar();
        abar.setHomeButtonEnabled(true);
        abar.setDisplayHomeAsUpEnabled(true);

        switch (idx){
            case 0:
                //home
                abar.setHomeButtonEnabled(false);
                abar.setDisplayHomeAsUpEnabled(false);
                abar.setTitle(getTitle());
                navigateTo(android.R.id.home);
                break;
            case 1:
                abar.setTitle("About");
                navigateTo(R.id.action_about);
                break;
            case 2:
                abar.setTitle("Settings");
                navigateTo(R.id.action_settings);
                break;
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            initView(rootView,getArguments().getInt(ARG_SECTION_NUMBER));

            return rootView;
        }

        private void initView(View rootView,int pos){
            final TextView tv = (TextView)rootView.findViewById(R.id.section_label);
            final Resources res = getResources();

            //reference and set our icon font
            Typeface font = Typeface.createFromAsset(getResources().getAssets(),"fontello.ttf");
            tv.setTypeface(font);

            switch (pos){
                case 1:
                    tv.setText("\uE803");//home
                    break;
                case 2:
                    //about
                    tv.setText("\uE800");//about
                    break;
                case 3:
                    //settings
                    tv.setText("\uE802");//settings
                    break;
            }

            //shadow
            SeekBar sb1 = (SeekBar) rootView.findViewById(R.id.seekbar_1);
            sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    int val = i - 5;

                    tv.setShadowLayer(
                            res.getInteger(R.integer.textShadow_Radius),
                            res.getInteger(R.integer.textShadow_x),
                            val,
                            R.color.gray
                    );

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });


            //color
            SeekBar sb2 = (SeekBar) rootView.findViewById(R.id.seekbar_2);
            final float[] hsv = new float[3];
            Color.colorToHSV(res.getColor(R.color.teal),hsv);

            sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


                    float val = (float)i;

                    //set hue
                    hsv[0] = val;

                    tv.setTextColor(Color.HSVToColor(hsv));

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });


            //size
            SeekBar sb3 = (SeekBar) rootView.findViewById(R.id.seekbar_3);

            sb3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


                    float val = (float)i+15;

                    tv.setTextSize(val);

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });



        }
    }

}
