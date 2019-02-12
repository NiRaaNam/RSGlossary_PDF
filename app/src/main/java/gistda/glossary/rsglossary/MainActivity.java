package gistda.glossary.rsglossary;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnPageChangeListener,OnLoadCompleteListener,
        OnClickListener {

    public static final String SAMPLE_FILE = "RSG_Book_Final.pdf"; //your file path
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;

    Button button;

    Toolbar mToolbar;
    ArrayAdapter<String> mAdapter;
    ListView mListView;
    TextView mEmptyView;

    private long lastPressedTime;
    private static final int PERIOD = 2000;

    Map<String, Integer> mapIndex;

    LinearLayout indexLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(String.format("%s ","RS Glossary ....."));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                if (mListView.getVisibility() == View.VISIBLE) {
                    mListView.setVisibility(View.GONE);
                } else {
                    mListView.setVisibility(View.VISIBLE);
                }

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //For PDF Request
        pdfView = (PDFView) findViewById(R.id.pdfView);
        displayFromAsset(SAMPLE_FILE);

        //List of item Vocab
        mListView = (ListView) findViewById(R.id.list_vocabs);
        mEmptyView = (TextView) findViewById(R.id.emptyView);

        mAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.vocab_array));
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                //Condition Selected Vocabulary
                //displayFromAsset(SAMPLE_FILE);
                String WordSelect = adapterView.getItemAtPosition(i).toString();
                if (WordSelect.equals("Absorption")||WordSelect.equals("Absorption Band")||
                        WordSelect.equals("Absorptivity")||WordSelect.equals("Across-Track Scanner")||
                        WordSelect.equals("Active Sensor")){

                    pdfView.jumpTo(1+6);
                }else if (WordSelect.equals("Adaptive Filter")||WordSelect.equals("Adaptive Histogram Equalization (AHE)")||
                        WordSelect.equals("Additive Color")){

                    pdfView.jumpTo(2+6);
                }else if (WordSelect.equals("Aerial Photography")||WordSelect.equals("Aerosol")||
                        WordSelect.equals("Airborne Radar")||WordSelect.equals("Albedo")||
                        WordSelect.equals("Along-Track Scanner (Pushbroom)")){

                    pdfView.jumpTo(3+6);
                }else if (WordSelect.equals("Alternating Polarization")||WordSelect.equals("Altimeter")||
                        WordSelect.equals("Altimetry")||WordSelect.equals("Altitude")){

                    pdfView.jumpTo(4+6);
                }else{
                    //pdfView.jumpTo(1);
                }

                //WordSelect.equals("")




                mListView.setVisibility(View.GONE);
                closeKeyboard();
            }
        });

        mListView.setEmptyView(mEmptyView);

        indexLayout = (LinearLayout) findViewById(R.id.side_index);
        String[] fruits = getResources().getStringArray(R.array.vocab_array);
        Arrays.asList(fruits);
        getIndexList(fruits);
        displayIndex();


        //About PDF Config
        //pdfView = (PDFView) findViewById(R.id.pdfView);
        //displayFromAsset(SAMPLE_FILE);
        //pdfView.fromAsset("RSG_Book_Final.pdf").load();



    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;

        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))

                .load();
    }


    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        //setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
        //setTitle(String.format("%s %s / %s","RS Glossary ", page + 1, pageCount));
        setTitle(String.format("%s ","RS Glossary Ready"));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);

        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    if (event.getDownTime() - lastPressedTime < PERIOD) {
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "กดอีกครั้งเพื่อออก",
                                Toast.LENGTH_SHORT).show();
                        lastPressedTime = event.getEventTime();
                    }
                    return true;
            }
        }
        return false;
    }

    //Index Right Side Code
    private void getIndexList(String[] fruits) {
        mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < fruits.length; i++) {
            String fruit = fruits[i];
            String index = fruit.substring(0, 1);

            if (mapIndex.get(index) == null)
                mapIndex.put(index, i);
        }
    }

    private void displayIndex() {
        //LinearLayout indexLayout = (LinearLayout) findViewById(R.id.side_index);

        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) getLayoutInflater().inflate(
                    R.layout.side_index_item, null);
            textView.setText(index);
            textView.setOnClickListener(this);
            indexLayout.addView(textView);
        }
    }

    public void onClick(View view) {
        TextView selectedIndex = (TextView) view;
        mListView.setSelection(mapIndex.get(selectedIndex.getText()));

        //Config Index Click on each Alphabet Index

        //Toast.makeText(getApplicationContext(), mapIndex.get(selectedIndex.getText()).toString(), Toast.LENGTH_SHORT).show();
        String selectAlphabet = mapIndex.get(selectedIndex.getText()).toString();
        String showAlphabet = "";
        if(selectAlphabet.equals("0")){
            showAlphabet = "A"; pdfView.jumpTo(7);
        }else if(selectAlphabet.equals("44")){
            showAlphabet = "B"; pdfView.jumpTo(16);
        }else if(selectAlphabet.equals("68")){
            showAlphabet = "C"; pdfView.jumpTo(21);
        }else if(selectAlphabet.equals("94")){
            showAlphabet = "D"; pdfView.jumpTo(26);
        }else if(selectAlphabet.equals("136")){
            showAlphabet = "E"; pdfView.jumpTo(33);
        }else if(selectAlphabet.equals("152")){
            showAlphabet = "F"; pdfView.jumpTo(37);
        }else if(selectAlphabet.equals("176")){
            showAlphabet = "G"; pdfView.jumpTo(42);
        }else if(selectAlphabet.equals("198")){
            showAlphabet = "H"; pdfView.jumpTo(48);
        }else if(selectAlphabet.equals("218")){
            showAlphabet = "I"; pdfView.jumpTo(53);
        }else if(selectAlphabet.equals("283")){
            showAlphabet = "K"; pdfView.jumpTo(66);
        }else if(selectAlphabet.equals("290")){
            showAlphabet = "L"; pdfView.jumpTo(68);
        }else if(selectAlphabet.equals("318")){
            showAlphabet = "M"; pdfView.jumpTo(73);
        }else if(selectAlphabet.equals("357")){
            showAlphabet = "N"; pdfView.jumpTo(80);
        }else if(selectAlphabet.equals("378")){
            showAlphabet = "O"; pdfView.jumpTo(79+6);
        }else if(selectAlphabet.equals("394")){
            showAlphabet = "P"; pdfView.jumpTo(81+6);
        }else if(selectAlphabet.equals("441")){
            showAlphabet = "Q"; pdfView.jumpTo(90+6);
        }else if(selectAlphabet.equals("442")){
            showAlphabet = "R"; pdfView.jumpTo(91+6);
        }else if(selectAlphabet.equals("507")){
            showAlphabet = "S"; pdfView.jumpTo(101+6);
        }else if(selectAlphabet.equals("605")){
            showAlphabet = "T"; pdfView.jumpTo(120+6);
        }else if(selectAlphabet.equals("633")){
            showAlphabet = "U"; pdfView.jumpTo(125+6);
        }else if(selectAlphabet.equals("643")){
            showAlphabet = "V"; pdfView.jumpTo(127+6);
        }else if(selectAlphabet.equals("659")){
            showAlphabet = "W"; pdfView.jumpTo(130+6);
        }else if(selectAlphabet.equals("665")){
            showAlphabet = "X"; pdfView.jumpTo(132+6);
        }else if(selectAlphabet.equals("668")){
            showAlphabet = "Y"; pdfView.jumpTo(132+6);
        }else if(selectAlphabet.equals("669")){
            showAlphabet = "Z"; pdfView.jumpTo(132+6);
        }

        Toast toast = Toast.makeText(getApplicationContext(), showAlphabet, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0,0);
        toast.show();
    }


}
