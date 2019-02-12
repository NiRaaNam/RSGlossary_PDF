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
                //displayFromAsset(SAMPLE_FILE); //displayFromAsset(SAMPLE_FILE);
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
                }else if (WordSelect.equals("Ambient Temperature")||WordSelect.equals("Analog Data")||
                        WordSelect.equals("Antenna")||WordSelect.equals("Antenna Array")||
                        WordSelect.equals("Antenna Radiation Pattern")){

                    pdfView.jumpTo(5+6);
                }else if (WordSelect.equals("Apparent Temperature")||WordSelect.equals("Array Sensor")||
                        WordSelect.equals("Artificial Neural Network")||WordSelect.equals("Aspect Angle")){

                    pdfView.jumpTo(6+6);
                }else if (WordSelect.equals("Atmospheric Absorption")||WordSelect.equals("Atmospheric Attenuation")||
                        WordSelect.equals("Atmospheric Effect")||WordSelect.equals("Atmospheric Emission")||
                        WordSelect.equals("Atmospheric Noise")||WordSelect.equals("Atmospheric Profile")){

                    pdfView.jumpTo(7+6);
                }else if (WordSelect.equals("Atmospheric Radiance")||WordSelect.equals("Atmospheric Reflection")||
                        WordSelect.equals("Atmospheric Scattering")||WordSelect.equals("Atmospheric Transmission")||
                        WordSelect.equals("Atmospheric Turbidity")||WordSelect.equals("Atmospheric Visibility")||
                        WordSelect.equals("Atmospheric Window")||WordSelect.equals("Automatic Classification")){

                    pdfView.jumpTo(8+6);
                }else if (WordSelect.equals("Automatic Picture Transmission (APT)")||WordSelect.equals("Azimuth")||
                        WordSelect.equals("Azimuth Direction")||WordSelect.equals("Azimuth Resolution")){

                    pdfView.jumpTo(9+6);
                }else if (WordSelect.equals("Backscatter")||WordSelect.equals("Balloon Sonde")||
                        WordSelect.equals("Band / Spectral Band")){

                    pdfView.jumpTo(10+6);
                }else if (WordSelect.equals("Band Interleaved by Line (BIL)")||WordSelect.equals("Band Interleaved by Pixel (BIP)")||
                        WordSelect.equals("Band Sequential (BSQ)")||WordSelect.equals("Bandwidth")||
                        WordSelect.equals("Baseline")||WordSelect.equals("Basemap")){

                    pdfView.jumpTo(11+6);
                }else if (WordSelect.equals("Base Height Ratio")||WordSelect.equals("Beam")||
                        WordSelect.equals("Beam Velocity")||WordSelect.equals("Beamwidth")){

                    pdfView.jumpTo(12+6);
                }else if (WordSelect.equals("Bearing")||WordSelect.equals("Benchmark")||
                        WordSelect.equals("Bi-Directional Reflectance Distribution Function (BRDF)")||
                        WordSelect.equals("Bilinear Interpolation")|| WordSelect.equals("Binary Mask")){

                    pdfView.jumpTo(13+6);
                }else if (WordSelect.equals("Bit Error")||WordSelect.equals("Black Body")||
                        WordSelect.equals("Block Adjustment")||WordSelect.equals("Brightness")||
                        WordSelect.equals("Brightness Temperature")||WordSelect.equals("Brown Leaf Area Index (BLAI)")||
                        WordSelect.equals("Buffer")){

                    pdfView.jumpTo(14+6);
                }else if (WordSelect.equals("Calibration")||WordSelect.equals("Cartographic Reference")||
                        WordSelect.equals("Cartography")||WordSelect.equals("C-Band")||
                        WordSelect.equals("Ceilometer")||WordSelect.equals("Change Detection")||
                        WordSelect.equals("Circular Polarization")||WordSelect.equals("Classification Accuracy")){

                    pdfView.jumpTo(15+6);
                }else if (WordSelect.equals("Clustering")||WordSelect.equals("Clutter")||
                        WordSelect.equals("Coastal Zone Color Scanner (CZCS)")||WordSelect.equals("Coherent Reflector")||
                        WordSelect.equals("Color Composite Image")){

                    pdfView.jumpTo(16+6);
                }else if (WordSelect.equals("Color Infrared (CIR) Image")||WordSelect.equals("Computer Assisted Mapping (CAM)")||
                        WordSelect.equals("Computer Compatible Tape (CCT)")||WordSelect.equals("Concave Slope")||
                        WordSelect.equals("Confusion Matrix")){

                    pdfView.jumpTo(17+6);
                }else if (WordSelect.equals("Contextual Classification")||WordSelect.equals("Contour")||
                        WordSelect.equals("Contrast Stretch")||WordSelect.equals("Convex Slope")){

                    pdfView.jumpTo(18+6);
                }else if (WordSelect.equals("Coordinate System")||WordSelect.equals("Cross Polarized Wave")||
                        WordSelect.equals("Cross Polarization Nulls (Cross-pol nulls or X-pol nulls)")||
                        WordSelect.equals("Cubic Convolution Interpolation")){

                    pdfView.jumpTo(19+6);
                }else if (WordSelect.equals("Data Coding")||WordSelect.equals("Data Compression")||
                        WordSelect.equals("Data Fusion")||WordSelect.equals("Data Interpretation")||
                        WordSelect.equals("Data Inversion")||WordSelect.equals("Data Normalization")||
                        WordSelect.equals("Data Processing")||WordSelect.equals("Data Quality")||
                        WordSelect.equals("Data Smoothing")){

                    pdfView.jumpTo(20+6);
                }else if (WordSelect.equals("Data Transformation")||WordSelect.equals("Data Transmission")||
                        WordSelect.equals("Data Verification")||WordSelect.equals("Datum")||
                        WordSelect.equals("Datum Transformation")||WordSelect.equals("Decibel (dB)")){

                    pdfView.jumpTo(21+6);
                }else if (WordSelect.equals("Decision Tree")||WordSelect.equals("Degree of Polarization")||
                        WordSelect.equals("Density Slicing")||WordSelect.equals("Depression Angle")){

                    pdfView.jumpTo(22+6);
                }else if (WordSelect.equals("Destriping")||WordSelect.equals("Detector")||
                        WordSelect.equals("Dielectric Constant")||WordSelect.equals("Difference Vegetation Index (DVI)")||
                        WordSelect.equals("Differential GPS (DGPS) / Differential Global Positioning System")){

                    pdfView.jumpTo(23+6);
                }else if (WordSelect.equals("Diffraction")||WordSelect.equals("Diffuse Reflection")||
                        WordSelect.equals("Digital Data")||WordSelect.equals("Digital Elevation Model (DEM)")){

                    pdfView.jumpTo(24+6);
                }else if (WordSelect.equals("Digital Filter")||WordSelect.equals("Digital Image")||
                        WordSelect.equals("Digital Mapping")||WordSelect.equals("Digital Number (DN)")||
                        WordSelect.equals("Digital Surface Model (DSM)")||WordSelect.equals("Digital Terrain Model (DTM)")){

                    pdfView.jumpTo(25+6);
                }else if (WordSelect.equals("Discrete Cosine Transform (DCT)")||WordSelect.equals("Discrete Fourier Transform (DFT)")||
                        WordSelect.equals("Distribution of Radar Signal")||WordSelect.equals("Doppler Bandwidth")||
                        WordSelect.equals("Doppler Domain")||WordSelect.equals("Doppler Radar")||
                        WordSelect.equals("Doppler Shift (Effect)")||WordSelect.equals("Dynamic Range")){

                    pdfView.jumpTo(26+6);
                }else if (WordSelect.equals("Earth Observation Satellite")||WordSelect.equals("Edge Detection")||
                        WordSelect.equals("Edge Enhancement")){

                    pdfView.jumpTo(27+6);
                }else if (WordSelect.equals("Edge Matching")||WordSelect.equals("Electromagnetic Energy / Electromagnetic Radiation / Electromagnetic Wave")||
                        WordSelect.equals("Electromagnetic Spectrum (EMS)")||WordSelect.equals("Electronic Noise")){

                    pdfView.jumpTo(28+6);
                }else if (WordSelect.equals("Elevation Displacement")||
                        WordSelect.equals("Elliptical Polarization")||
                        WordSelect.equals("Emissivity")||
                        WordSelect.equals("Emittance")||
                        WordSelect.equals("Enhanced Thematic Mapper (ETM) / Enhanced Thematic Mapper Plus (ETM+)")||
                        WordSelect.equals("Errors of Commission")||
                        WordSelect.equals("Errors of Omission")||
                        WordSelect.equals("Expert System")){

                    pdfView.jumpTo(29+6);
                }else if (WordSelect.equals("External Distortion")){

                    pdfView.jumpTo(30+6);
                }else if (WordSelect.equals("False Color Composite")||
                        WordSelect.equals("Far Infrared (FIR)")||
                        WordSelect.equals("Far Range")||
                        WordSelect.equals("Fast Fourier Transform (FFT)")||
                        WordSelect.equals("Feature Data")||
                        WordSelect.equals("Feature Extraction")){

                    pdfView.jumpTo(31+6);
                }else if(WordSelect.equals("Feature Selection")||
                        WordSelect.equals("Field of View (FOV)")||
                        WordSelect.equals("Filter")||
                        WordSelect.equals("Filtering (Image)")||
                        WordSelect.equals("Flight Path")||
                        WordSelect.equals("Fluorescence")){

                    pdfView.jumpTo(32+6);
                }else if(WordSelect.equals("Flux")||
                        WordSelect.equals("Foreshortening")||
                        WordSelect.equals("Forward Looking Infrared (FLIR)")||
                        WordSelect.equals("Fourier Transform (FT)")||
                        WordSelect.equals("Fractal Analysis")||
                        WordSelect.equals("Fraction of Photosynthetically Active Radiation (FPAR)")){

                    pdfView.jumpTo(33+6);
                }else if(WordSelect.equals("Frequency")||
                        WordSelect.equals("Frequency Assignment")||
                        WordSelect.equals("Frequency Domain")||
                        WordSelect.equals("Frequency Modulation (FM)")){

                    pdfView.jumpTo(34+6);
                }else if(WordSelect.equals("Furthest Neighbor Method")||
                        WordSelect.equals("Fuzzy Logic")){

                    pdfView.jumpTo(35+6);
                }else if(WordSelect.equals("Gain (Antenna)")||
                        WordSelect.equals("Gamma Ray")||
                        WordSelect.equals("Gaussian Filtering")||
                        WordSelect.equals("Generalization")||
                        WordSelect.equals("Geocoding")||
                        WordSelect.equals("Geodetic Coordinate")){

                    pdfView.jumpTo(36+6);
                }else if(WordSelect.equals("Geographic Information System (GIS)")||
                        WordSelect.equals("Geoinformation Technology / Geoinformatics / Geomatics")||
                        WordSelect.equals("Geometric Correction")||
                        WordSelect.equals("Geometric Distortion")){

                    pdfView.jumpTo(37+6);
                }else if(WordSelect.equals("Geometric Registration")||
                        WordSelect.equals("Geostationary Orbit")||
                        WordSelect.equals("Geostationary Satellite")||
                        WordSelect.equals("Global Navigation Satellite System (GNSS)")){

                    pdfView.jumpTo(39+6);
                }else if(WordSelect.equals("Grayscale")||
                        WordSelect.equals("Grazing Angle")||
                        WordSelect.equals("Greenness")||
                        WordSelect.equals("Ground Control Point (GCP)")||
                        WordSelect.equals("Ground Range")){

                    pdfView.jumpTo(40+6);
                }else if(WordSelect.equals("Ground Reflectance")||
                        WordSelect.equals("Ground Station")||
                        WordSelect.equals("Ground Truth")){

                    pdfView.jumpTo(41+6);
                }else if(WordSelect.equals("Haar Transform")||
                        WordSelect.equals("Haze")||
                        WordSelect.equals("Hertz (Hz)")||
                        WordSelect.equals("Hierarchical Clustering")||
                        WordSelect.equals("High Density Digital Tape (HDDT)")){

                    pdfView.jumpTo(42+6);
                }else if(WordSelect.equals("High Density Tape Recorder (HDTR)")||
                        WordSelect.equals("High Frequency (HF)")||
                        WordSelect.equals("High Pass Filter")||
                        WordSelect.equals("Hilbert Transform")||
                        WordSelect.equals("Histogram")||
                        WordSelect.equals("Histogram Stretch")){

                    pdfView.jumpTo(43+6);
                }else if(WordSelect.equals("Histogram Equalization")||
                        WordSelect.equals("Horizontal Polarization")){

                    pdfView.jumpTo(44+6);
                }else if(WordSelect.equals("Horizontal Transmit - Horizontal Receive Polarization (HH)")||
                        WordSelect.equals("Horizontal Transmit - Vertical Receive Polarization (HV)")||
                        WordSelect.equals("Hotspot")||
                        WordSelect.equals("Hotine Oblique Mercator Projection (HOM)")||
                        WordSelect.equals("Hough Transform")){

                    pdfView.jumpTo(45+6);
                }else if(WordSelect.equals("Hue, Intensity and Saturation (HIS)")||
                        WordSelect.equals("Hyperspectral Imaging")){

                    pdfView.jumpTo(46+6);
                }else if(WordSelect.equals("Illumination")||
                        WordSelect.equals("Illumination Angle")||
                        WordSelect.equals("Image Analysis")||
                        WordSelect.equals("Image Classification")){

                    pdfView.jumpTo(47+6);
                }else if(WordSelect.equals("Image Contrast")||
                        WordSelect.equals("Image Correction")){

                    pdfView.jumpTo(48+6);
                }else if(WordSelect.equals("Image Correlation")||
                        WordSelect.equals("Image Correlator")||
                        WordSelect.equals("Image Degradation")||
                        WordSelect.equals("Image Digitizer")||
                        WordSelect.equals("Image Distortion")||
                        WordSelect.equals("Image Encoding")||
                        WordSelect.equals("Image Enhancement")){

                    pdfView.jumpTo(49+6);
                }else if(WordSelect.equals("Image Filtering")||
                        WordSelect.equals("Image Geometry")||
                        WordSelect.equals("Image Ground Segment (IGS)")||
                        WordSelect.equals("Image Histogram")||
                        WordSelect.equals("Image Interpretation")||
                        WordSelect.equals("Image Map")||
                        WordSelect.equals("Image Noise")){

                    pdfView.jumpTo(50+6);
                }else if(WordSelect.equals("Image Processing")||
                        WordSelect.equals("Image Ratioing")||
                        WordSelect.equals("Image Rectification")||
                        WordSelect.equals("Image Resampling")){

                    pdfView.jumpTo(51+6);
                }else if(WordSelect.equals("Image Restoration")){

                    pdfView.jumpTo(52+6);
                }else if(WordSelect.equals("Image Scale")||
                        WordSelect.equals("Image Subtraction")||
                        WordSelect.equals("Image Texture")||
                        WordSelect.equals("Image to Image Correction")){

                    pdfView.jumpTo(53+6);
                }else if(WordSelect.equals("Image to Map Correction")||
                        WordSelect.equals("Image Transformation")){

                    pdfView.jumpTo(54+6);
                }else if(WordSelect.equals("Imaging")||
                        WordSelect.equals("Imaging Radar")||
                        WordSelect.equals("Imaging Spectrometry")||
                        WordSelect.equals("Incidence Angle")||
                        WordSelect.equals("Incident Energy")||
                        WordSelect.equals("Incoherent")||
                        WordSelect.equals("Indirect Interpretation")||
                        WordSelect.equals("Infrared Band")){

                    pdfView.jumpTo(55+6);
                }else if(WordSelect.equals("Infrared Imaging")||
                        WordSelect.equals("Infrared Image")||
                        WordSelect.equals("Infrared Radiometer")||
                        WordSelect.equals("Infrared Radiometry")||
                        WordSelect.equals("Infrared Scanner")||
                        WordSelect.equals("Infrared Scanning")){

                    pdfView.jumpTo(56+6);
                }else if(WordSelect.equals("Infrared Spectrometer")||
                        WordSelect.equals("Infrared Spectrometry")||
                        WordSelect.equals("Infrared Spectroscopy")||
                        WordSelect.equals("Instantaneous Field of View (IFOV)")||
                        WordSelect.equals("Intensity")||
                        WordSelect.equals("Interferometer")||
                        WordSelect.equals("Interferometry")){

                    pdfView.jumpTo(57+6);
                }else if(WordSelect.equals("Interferometric Synthetic Aperture Radar (InSAR)")||
                        WordSelect.equals("Internal Distortion")||
                        WordSelect.equals("International Standards Organization (ISO)")||
                        WordSelect.equals("Interoperability")||
                        WordSelect.equals("Interpolation / Spatial Interpolation")||
                        WordSelect.equals("Interpretation")||
                        WordSelect.equals("Interpretation Key")||
                        WordSelect.equals("Intersection")||
                        WordSelect.equals("Inverse Distance Weighting (IDW)")){

                    pdfView.jumpTo(58+6);
                }else if(WordSelect.equals("Inverse Synthetic Aperture Radar (ISAR)")||
                        WordSelect.equals("Isodensity")||
                        WordSelect.equals("Isopleth Map")||
                        WordSelect.equals("Isotropic")){

                    pdfView.jumpTo(59+6);
                }else if(WordSelect.equals("Ka-Band")||
                        WordSelect.equals("Kalman Filter")||
                        WordSelect.equals("Kappa Coefficient")||
                        WordSelect.equals("K-Band")||
                        WordSelect.equals("Kernel")||
                        WordSelect.equals("Kriging")){

                    pdfView.jumpTo(60+6);
                }else if(WordSelect.equals("Ku-Band")){

                    pdfView.jumpTo(61+6);
                }else if(WordSelect.equals("Labeling")||
                        WordSelect.equals("LADAR (Laser Detection and Ranging)")||
                        WordSelect.equals("Lambert Conformal Conic Projection (LCC)")||
                        WordSelect.equals("Land Information System (LIS)")||
                        WordSelect.equals("Large Scale Map")||
                        WordSelect.equals("LASER (Light Amplification by Stimulated Emission of Radiation)")||
                        WordSelect.equals("Latitudinal Effect")||
                        WordSelect.equals("Layover")){

                    pdfView.jumpTo(62+6);
                }else if(WordSelect.equals("L-Band")||
                        WordSelect.equals("L-Band Radiometer (LBR)")||
                        WordSelect.equals("Leaf Area Index (LAI)")||
                        WordSelect.equals("Lens Distortion")||
                        WordSelect.equals("LiDAR (Light Detection and Ranging)")){

                    pdfView.jumpTo(63+6);
                }else if(WordSelect.equals("Line Scanner")||
                        WordSelect.equals("Linear Contrast Stretch")||
                        WordSelect.equals("Linear Enhancement")||
                        WordSelect.equals("Linear Features")||
                        WordSelect.equals("Linear Polarization")||
                        WordSelect.equals("Local Incidence Angle")){

                    pdfView.jumpTo(64+6);
                }else if(WordSelect.equals("Logarithmic Stretch")||
                        WordSelect.equals("Logical Database Design")||
                        WordSelect.equals("Look Angle")||
                        WordSelect.equals("Look Direction")||
                        WordSelect.equals("Look-Up Table (LUT)")||
                        WordSelect.equals("Low Frequency (LF)")){

                    pdfView.jumpTo(65+6);
                }else if(WordSelect.equals("Low Level Wind Shear (LLWS)")||
                        WordSelect.equals("Low Pass Filter / Smoothing Filter")||
                        WordSelect.equals("Luminance")){

                    pdfView.jumpTo(66+6);
                }else if(WordSelect.equals("Magnetic Azimuth")||
                        WordSelect.equals("Magnetic North")||
                        WordSelect.equals("Magnetic Survey")||
                        WordSelect.equals("Magnitude")||
                        WordSelect.equals("Map Projection")){

                    pdfView.jumpTo(67+6);
                }else if(WordSelect.equals("Map Resolution")||
                        WordSelect.equals("Map Scale")||
                        WordSelect.equals("Maximum Likelihood Classification")){

                    pdfView.jumpTo(68+6);
                }else if(WordSelect.equals("Median Filter")||
                        WordSelect.equals("Medium Scale Map")||
                        WordSelect.equals("Metadata")||
                        WordSelect.equals("Microwave Band")||
                        WordSelect.equals("Microwave Backscatter")||
                        WordSelect.equals("Microwave Radiometer")||
                        WordSelect.equals("Microwave Radiometry")||
                        WordSelect.equals("Microwave Spectrometer")){

                    pdfView.jumpTo(69+6);
                }else if(WordSelect.equals("Microwave System")||
                        WordSelect.equals("Mid Infrared (MIR)")||
                        WordSelect.equals("Mie Scattering")||
                        WordSelect.equals("Minimum Distance (to Means) Classifier")||
                        WordSelect.equals("Mixed Pixel")||
                        WordSelect.equals("Modulation")){

                    pdfView.jumpTo(70+6);
                }else if(WordSelect.equals("Modulation Transfer Function (MTF)")||
                        WordSelect.equals("Mosaic")||
                        WordSelect.equals("Multibeam Altimeter")||
                        WordSelect.equals("Multibeam Scatterometer")||
                        WordSelect.equals("Multichannel System")){

                    pdfView.jumpTo(71+6);
                }else if(WordSelect.equals("Multifrequency Laser")||
                        WordSelect.equals("Multifrequency Radar")||
                        WordSelect.equals("Multi-polarization Radar")||
                        WordSelect.equals("Multiresolution Sensor")||
                        WordSelect.equals("Multispectral Analysis")||
                        WordSelect.equals("Multispectral Data / Image")||
                        WordSelect.equals("Multispectral Radiometer")||
                        WordSelect.equals("Multispectral Radiometry")){

                    pdfView.jumpTo(72+6);
                }else if(WordSelect.equals("Multispectral Scanner (MSS)")||
                        WordSelect.equals("Multispectral Scanning")||
                        WordSelect.equals("Multitemporal Analysis")||
                        WordSelect.equals("Multitemporal Image")){

                    pdfView.jumpTo(73+6);
                }else if(WordSelect.equals("Nadir Ambiguity")||
                        WordSelect.equals("Nadir Point")||
                        WordSelect.equals("Natural Color Composite / True Color Composite")){

                    pdfView.jumpTo(74+6);
                }else if(WordSelect.equals("Navigation Satellite")||
                        WordSelect.equals("Near Infrared (NIR)")||
                        WordSelect.equals("Near Range")||
                        WordSelect.equals("Near Range Edge")||
                        WordSelect.equals("Nearest Neighbor Interpolation")||
                        WordSelect.equals("Nearest Neighbor Method")){

                    pdfView.jumpTo(75+6);
                }else if(WordSelect.equals("Neatline")||
                        WordSelect.equals("Neighborhood Function")||
                        WordSelect.equals("Neighborhood Analysis")||
                        WordSelect.equals("Noise Removal")||
                        WordSelect.equals("Non-systematic Correction")||
                        WordSelect.equals("Non-adaptive Filter")){

                    pdfView.jumpTo(76+6);
                }else if(WordSelect.equals("Non-hierarchical Clustering")||
                        WordSelect.equals("Non-imaging Sensor")||
                        WordSelect.equals("Non-linear Contrast Stretch")||
                        WordSelect.equals("Non-selective Scattering")||
                        WordSelect.equals("Normal Distribution")||
                        WordSelect.equals("Normalized Difference Vegetation Index (NDVI)")){

                    pdfView.jumpTo(77+6);
                }else if(WordSelect.equals("Oblique Mercator Projection")||
                        WordSelect.equals("Oblique Sensing")||
                        WordSelect.equals("Onboard Processing")||
                        WordSelect.equals("Optical Density")||
                        WordSelect.equals("Optical Depth")||
                        WordSelect.equals("Optical Digital System")||
                        WordSelect.equals("Optical Distortion")||
                        WordSelect.equals("Optical Filter")){

                    pdfView.jumpTo(79+6);
                }else if(WordSelect.equals("Optical Modulator")||
                        WordSelect.equals("Optical Scatterometer")||
                        WordSelect.equals("Optical Sensing")||
                        WordSelect.equals("Optimum Polarization")||
                        WordSelect.equals("Orthophoto / Orthoimage")||
                        WordSelect.equals("Outgoing Longwave Radiation (OLR)")||
                        WordSelect.equals("Overall Accuracy")||
                        WordSelect.equals("Over-the-horizon Radar")){

                    pdfView.jumpTo(80+6);
                }else if(WordSelect.equals("Panchromatic Film")||
                        WordSelect.equals("Panchromatic Image")||
                        WordSelect.equals("Parallax")||
                        WordSelect.equals("Parallelepiped Classifier")){

                    pdfView.jumpTo(81+6);
                }else if(WordSelect.equals("Passive Sensor")||
                        WordSelect.equals("Pattern Recognition")||
                        WordSelect.equals("Payload")||
                        WordSelect.equals("P-Band")||
                        WordSelect.equals("Phase")){

                    pdfView.jumpTo(82+6);
                }else if(WordSelect.equals("Photoelectric Device")||
                        WordSelect.equals("Photoelectric Tube")||
                        WordSelect.equals("Photogeology")||
                        WordSelect.equals("Photogrammetry")||
                        WordSelect.equals("Photometer")||
                        WordSelect.equals("Photosynthetically Active Radiation (PAR)")||
                        WordSelect.equals("Piecewise Linear Contrast Stretch")){

                    pdfView.jumpTo(83+6);
                }else if(WordSelect.equals("Pitch")||
                        WordSelect.equals("Pixel")||
                        WordSelect.equals("Pixel Value")||
                        WordSelect.equals("Plan Position Indicator (PPI)")){

                    pdfView.jumpTo(84+6);
                }else if(WordSelect.equals("Plane Wave")||
                        WordSelect.equals("Planimetry")||
                        WordSelect.equals("Platform")||
                        WordSelect.equals("Point Data")||
                        WordSelect.equals("Polar Stereographic Projection")||
                        WordSelect.equals("Polarimeter")){

                    pdfView.jumpTo(85+6);
                }else if(WordSelect.equals("Polarimetric Active RadarCalibrator(PARC)")||
                        WordSelect.equals("Polarimetric Radar")||
                        WordSelect.equals("Polarimetry")||
                        WordSelect.equals("Polarization / Polarization Basic")||
                        WordSelect.equals("Polarization Efficiency")||
                        WordSelect.equals("Polarization Pattern")){

                    pdfView.jumpTo(86+6);
                }else if(WordSelect.equals("Polarization Signature")||
                        WordSelect.equals("Polarization State")||
                        WordSelect.equals("Polarization Matched Antenna")||
                        WordSelect.equals("Post Processing")||
                        WordSelect.equals("Power Spectral Density (PSD)")||
                        WordSelect.equals("Pre Processing")||
                        WordSelect.equals("Principal Component Analysis (PCA)")){

                    pdfView.jumpTo(87+6);
                }else if(WordSelect.equals("Principal Component Transform (PCT)")||
                        WordSelect.equals("Probability Density Function (PDF)")||
                        WordSelect.equals("Producer’s Accuracy")||
                        WordSelect.equals("Pseudo Color Image")){

                    pdfView.jumpTo(88+6);
                }else if(WordSelect.equals("Pulse")||
                        WordSelect.equals("Pulse Code Modulation (PCM)")||
                        WordSelect.equals("Pulse Repetition Frequency (PRF)")||
                        WordSelect.equals("Pulse Width")){

                    pdfView.jumpTo(89+6);
                }else if(WordSelect.equals("Quadrature Polarization Radar (Quad Pol Radar)")){

                    pdfView.jumpTo(90+6);
                }else if(WordSelect.equals("Radar (Radio Detection And Ranging)")||
                        WordSelect.equals("Radar Altimeter")||
                        WordSelect.equals("Radar Band / Radar Frequency Band")||
                        WordSelect.equals("Radar Beam")||
                        WordSelect.equals("Radar Brightness")||
                        WordSelect.equals("Radar Cross Section (RCS)")||
                        WordSelect.equals("Radar Detection")||
                        WordSelect.equals("Radar Equation")||
                        WordSelect.equals("Radar Image")){

                    pdfView.jumpTo(91+6);
                }else if(WordSelect.equals("Radar Penetration")||
                        WordSelect.equals("Radar Phase / Phase")||
                        WordSelect.equals("Radar Polarimetry")||
                        WordSelect.equals("Radar Polarization")){

                    pdfView.jumpTo(92+6);
                }else if(WordSelect.equals("Radar Processing")||
                        WordSelect.equals("Radar Resolution")||
                        WordSelect.equals("Radar Swath")||
                        WordSelect.equals("Radar Transmission")){

                    pdfView.jumpTo(93+6);
                }else if(WordSelect.equals("Radargrammetry")||
                        WordSelect.equals("Radiance")||
                        WordSelect.equals("Radiation")||
                        WordSelect.equals("Radiation Attenuation")||
                        WordSelect.equals("Radiative Transfer")||
                        WordSelect.equals("Radio Band")||
                        WordSelect.equals("Radio Echo")||
                        WordSelect.equals("Radio Frequency (RF)")||
                        WordSelect.equals("Radiometer")||
                        WordSelect.equals("Radiometric Correction")){

                    pdfView.jumpTo(94+6);
                }else if(WordSelect.equals("Radiometric Enhancement")||
                        WordSelect.equals("Radiometric Resolution")||
                        WordSelect.equals("Radiometry")||
                        WordSelect.equals("Range")||
                        WordSelect.equals("Range Ambiguity")||
                        WordSelect.equals("Range Curvature")||
                        WordSelect.equals("Range Direction")||
                        WordSelect.equals("Range Height Indicator (RHI)")||
                        WordSelect.equals("Range Resolution")||
                        WordSelect.equals("Raster Data")||
                        WordSelect.equals("Ratio Image")||
                        WordSelect.equals("Rayleigh Scattering")){

                    pdfView.jumpTo(95+6);
                }else if(WordSelect.equals("Real Aperture Radar (RAR)")||
                        WordSelect.equals("Receiver (Radar)")||
                        WordSelect.equals("Reclassification")||
                        WordSelect.equals("Reference Image")||
                        WordSelect.equals("Reflectance")||
                        WordSelect.equals("Reflected Energy Peak")){

                    pdfView.jumpTo(96+6);
                }else if(WordSelect.equals("Reflected IR Band")||
                        WordSelect.equals("Reflection")||
                        WordSelect.equals("Reflection Angle")||
                        WordSelect.equals("Reflectivity")||
                        WordSelect.equals("Reflectometer")||
                        WordSelect.equals("Reflectometry")||
                        WordSelect.equals("Reflector")||
                        WordSelect.equals("Refraction")||
                        WordSelect.equals("Relative Positioning")||
                        WordSelect.equals("Relief Displacement")){

                    pdfView.jumpTo(97+6);
                }else if(WordSelect.equals("Remote Sensing (RS)")||
                        WordSelect.equals("Remote Sensing Online Retrieval System (RESORS)")||
                        WordSelect.equals("Repeat Pass Interferometry")||
                        WordSelect.equals("Resolution")){

                    pdfView.jumpTo(98+6);
                }else if(WordSelect.equals("Resolution Cell")||
                        WordSelect.equals("Resolving Power")||
                        WordSelect.equals("Roll")||
                        WordSelect.equals("Rotor-SAR (ROSAR)")||
                        WordSelect.equals("Roughness")||
                        WordSelect.equals("Row")){

                    pdfView.jumpTo(100+6);
                }else if(WordSelect.equals("SAR Focusing")||
                        WordSelect.equals("SAR Image Quality")||
                        WordSelect.equals("Satellite")||
                        WordSelect.equals("Satellite Image")){

                    pdfView.jumpTo(101+6);
                }else if(WordSelect.equals("Satellite Orbit")){

                    pdfView.jumpTo(102+6);
                }else if(WordSelect.equals("Satellite Remote Sensing /Satellite Sensing")||
                        WordSelect.equals("Saturation")||
                        WordSelect.equals("S-Band")||
                        WordSelect.equals("Scale Factor")||
                        WordSelect.equals("Scanner")||
                        WordSelect.equals("Scanning Radiometer")||
                        WordSelect.equals("Scanning Synthetic Aperture Radar (ScanSAR)")){

                    pdfView.jumpTo(103+6);
                }else if(WordSelect.equals("Scattering")||
                        WordSelect.equals("Scattering Coefficient")){

                    pdfView.jumpTo(104+6);
                }else if(WordSelect.equals("Scattering Matrix")||
                        WordSelect.equals("Scatterometer")||
                        WordSelect.equals("Scatterometry")||
                        WordSelect.equals("Scene")||
                        WordSelect.equals("Segmentation")||
                        WordSelect.equals("Sensitivity")||
                        WordSelect.equals("Sensor")||
                        WordSelect.equals("Sensor Noise")||
                        WordSelect.equals("Sensor Web")||
                        WordSelect.equals("Shadow")||
                        WordSelect.equals("Shadow (Radar)")||
                        WordSelect.equals("Shadow Enhancement")){

                    pdfView.jumpTo(105+6);
                }else if(WordSelect.equals("Ship Detection")||
                        WordSelect.equals("Short Pulse Radar")||
                        WordSelect.equals("Shuttle Imaging Radar (SIR)")||
                        WordSelect.equals("Side-Looking Airborne Radar (SLAR)")||
                        WordSelect.equals("Sidelooking Radiometry")||
                        WordSelect.equals("Sigma")||
                        WordSelect.equals("Sigma Nought ( σ°)")||
                        WordSelect.equals("Signal")||
                        WordSelect.equals("Signal Enhancement")||
                        WordSelect.equals("Signal Noise / Noise")){

                    pdfView.jumpTo(106+6);
                }else if(WordSelect.equals("Signal Processing")||
                        WordSelect.equals("Signal Variation")||
                        WordSelect.equals("Signal to Interference Ratio (SIR)")||
                        WordSelect.equals("Signature Extension")||
                        WordSelect.equals("Slant Range")||
                        WordSelect.equals("Small Scale Map")||
                        WordSelect.equals("Soil Adjustment Vegetation Index (SAVI)")||
                        WordSelect.equals("Soil Reflectance")||
                        WordSelect.equals("Space Law")||
                        WordSelect.equals("Space Oblique Mercator Projection (SOM)")){

                    pdfView.jumpTo(107+6);
                }else if(WordSelect.equals("Space Photography")||
                        WordSelect.equals("Space Shuttle")||
                        WordSelect.equals("Space Station")||
                        WordSelect.equals("Spatial Analysis")||
                        WordSelect.equals("Spatial Autocorrelation")){

                    pdfView.jumpTo(108+6);
                }else if(WordSelect.equals("Spatial Characteristic")||
                        WordSelect.equals("Spatial Data")||
                        WordSelect.equals("Spatial Effective Resolution Element (SERE)")||
                        WordSelect.equals("Spatial Enhancement")||
                        WordSelect.equals("Spatial Filter")){

                    pdfView.jumpTo(109+6);
                }else if(WordSelect.equals("Spatial Frequency")||
                        WordSelect.equals("Spatial Overlay")){

                    pdfView.jumpTo(110+6);
                }else if(WordSelect.equals("Spatial Resolution")||
                        WordSelect.equals("Speckle Filter")||
                        WordSelect.equals("Speckle Noise")||
                        WordSelect.equals("Spectral Analysis")||
                        WordSelect.equals("Spectral Enhancement")||
                        WordSelect.equals("Spectral Filter")||
                        WordSelect.equals("Spectral Irradiance")||
                        WordSelect.equals("Spectral Radiance")){

                    pdfView.jumpTo(111+6);
                }else if(WordSelect.equals("Spectral Resolution")||
                        WordSelect.equals("Spectral Signature / Signature")){

                    pdfView.jumpTo(112+6);
                }else if(WordSelect.equals("Spectrometer")||
                        WordSelect.equals("Spectrometry")||
                        WordSelect.equals("Spectrophotometer")||
                        WordSelect.equals("Spectrophotometry")||
                        WordSelect.equals("Spectroradiometer")){

                    pdfView.jumpTo(113+6);
                }else if(WordSelect.equals("Spectroradiometry")||
                        WordSelect.equals("Specular Reflection")||
                        WordSelect.equals("Speed of Light")||
                        WordSelect.equals("Spin Scanner")||
                        WordSelect.equals("Spotlight SAR")){

                    pdfView.jumpTo(114+6);
                }else if(WordSelect.equals("Squint Mode")||
                        WordSelect.equals("Standard Parallel")||
                        WordSelect.equals("Static Survey")||
                        WordSelect.equals("Stereo Analysis")||
                        WordSelect.equals("Stereo Pair")||
                        WordSelect.equals("Stereo Sensing")||
                        WordSelect.equals("Stereophotogrammetry")){

                    pdfView.jumpTo(115+6);
                }else if(WordSelect.equals("Stereoplotter")||
                        WordSelect.equals("Stereoscope")||
                        WordSelect.equals("Stereoscopic Parallax")||
                        WordSelect.equals("Strip Map")||
                        WordSelect.equals("Striping Noise")||
                        WordSelect.equals("Subsetting")){

                    pdfView.jumpTo(116+6);
                }else if(WordSelect.equals("Subtractive Color")||
                        WordSelect.equals("Sun-synchronous Orbit")||
                        WordSelect.equals("Supervised Classification")||
                        WordSelect.equals("Surveying")||
                        WordSelect.equals("Synthetic Aperture Radar (SAR)")){

                    pdfView.jumpTo(117+6);
                }else if(WordSelect.equals("Synthetic Aperture Strip Map (SASM)")||
                        WordSelect.equals("Systematic Correction")){

                    pdfView.jumpTo(118+6);
                }else if(WordSelect.equals("Telemetry")||
                        WordSelect.equals("Temperature Gradient")||
                        WordSelect.equals("Template Matching")||
                        WordSelect.equals("Temporal Characteristics")||
                        WordSelect.equals("Temporal Resolution")||
                        WordSelect.equals("Textural Signature")||
                        WordSelect.equals("Texture Analysis")||
                        WordSelect.equals("Thematic Map")){

                    pdfView.jumpTo(120+6);
                }else if(WordSelect.equals("Thematic Mapper (TM)")||
                        WordSelect.equals("Thermal Emittance")||
                        WordSelect.equals("Thermal Imaging")||
                        WordSelect.equals("Thermal Infrared Band")||
                        WordSelect.equals("Thermal Mapper")||
                        WordSelect.equals("Threshold")||
                        WordSelect.equals("Tone")){

                    pdfView.jumpTo(121+6);
                }else if(WordSelect.equals("Topographic Map")||
                        WordSelect.equals("Training Area")||
                        WordSelect.equals("Transformation VegetationIndex (TVI)")||
                        WordSelect.equals("TransformedSoil AdjustedVegetation Index (TSAVI)")){

                    pdfView.jumpTo(122+6);
                }else if(WordSelect.equals("Transmission")||
                        WordSelect.equals("Transmissometer")||
                        WordSelect.equals("Transmittance")||
                        WordSelect.equals("Transponder")||
                        WordSelect.equals("Trend Surface Analysis")||
                        WordSelect.equals("Triangulated Irregular Network (TIN)")||
                        WordSelect.equals("Triangulation")){

                    pdfView.jumpTo(123+6);
                }else if(WordSelect.equals("True Color Composite")||
                        WordSelect.equals("True North")){

                    pdfView.jumpTo(124+6);
                }else if(WordSelect.equals("Unmanned Aerial Vehicle (UAV)")||
                        WordSelect.equals("Unsupervised Classification")||
                        WordSelect.equals("Unpolarized Wave")||
                        WordSelect.equals("Ultra Low Frequency (ULF)")||
                        WordSelect.equals("Ultra High Frequency (UHF)")||
                        WordSelect.equals("Universal Transverse Mercator Projection (UTM)")){

                    pdfView.jumpTo(125+6);
                }else if(WordSelect.equals("Ultralight Aircraft")||
                        WordSelect.equals("Ultraviolet (UV)")||
                        WordSelect.equals("Ultraviolet Photography")||
                        WordSelect.equals("User’s Accuracy")){

                    pdfView.jumpTo(126+6);
                }else if(WordSelect.equals("Vector Data")||
                        WordSelect.equals("Vegetation Condition Index (VCI)")||
                        WordSelect.equals("Vegetation Index (VI)")||
                        WordSelect.equals("Vegetation Reflectance")||
                        WordSelect.equals("Velocimeter")||
                        WordSelect.equals("Vertical Polarization")||
                        WordSelect.equals("Vertical Transmit- Horizontal Receive Polarization (VH)")||
                        WordSelect.equals("Vertical Transmit - Vertical Receive Polarization (VV)")||
                        WordSelect.equals("Very High Frequency (VHF)")||
                        WordSelect.equals("Very Low Frequency (VLF)")||
                        WordSelect.equals("Viewing Angle")){

                    pdfView.jumpTo(127+6);
                }else if(WordSelect.equals("Vignetting")||
                        WordSelect.equals("Visible Light")||
                        WordSelect.equals("Visual Interpretation")){

                    pdfView.jumpTo(128+6);
                }else if(WordSelect.equals("Volume Scattering")||
                        WordSelect.equals("Voxel")){

                    pdfView.jumpTo(129+6);
                }else if(WordSelect.equals("Water Absorption Band")||
                        WordSelect.equals("Water Reflectance")||
                        WordSelect.equals("Wave")||
                        WordSelect.equals("Wavelength")||
                        WordSelect.equals("Wavelet / Wavelet Transform")){

                    pdfView.jumpTo(130+6);
                }else if(WordSelect.equals("World Geodetic System 84 (WGS 84)")){

                    pdfView.jumpTo(131+6);
                }else if(WordSelect.equals("X-Band")||
                        WordSelect.equals("X-Band Antenna")||
                        WordSelect.equals("X-Ray")||
                        WordSelect.equals("Yaw")||
                        WordSelect.equals("Zero Correction")){

                    pdfView.jumpTo(132+6);
                }else{
                    pdfView.jumpTo(0);
                }

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
        }else if(selectAlphabet.equals("69")){
            showAlphabet = "C"; pdfView.jumpTo(21);
        }else if(selectAlphabet.equals("95")){
            showAlphabet = "D"; pdfView.jumpTo(26);
        }else if(selectAlphabet.equals("137")){
            showAlphabet = "E"; pdfView.jumpTo(33);
        }else if(selectAlphabet.equals("153")){
            showAlphabet = "F"; pdfView.jumpTo(37);
        }else if(selectAlphabet.equals("177")){
            showAlphabet = "G"; pdfView.jumpTo(42);
        }else if(selectAlphabet.equals("199")){
            showAlphabet = "H"; pdfView.jumpTo(48);
        }else if(selectAlphabet.equals("219")){
            showAlphabet = "I"; pdfView.jumpTo(53);
        }else if(selectAlphabet.equals("284")){
            showAlphabet = "K"; pdfView.jumpTo(66);
        }else if(selectAlphabet.equals("291")){
            showAlphabet = "L"; pdfView.jumpTo(68);
        }else if(selectAlphabet.equals("319")){
            showAlphabet = "M"; pdfView.jumpTo(73);
        }else if(selectAlphabet.equals("358")){
            showAlphabet = "N"; pdfView.jumpTo(80);
        }else if(selectAlphabet.equals("379")){
            showAlphabet = "O"; pdfView.jumpTo(79+6);
        }else if(selectAlphabet.equals("395")){
            showAlphabet = "P"; pdfView.jumpTo(81+6);
        }else if(selectAlphabet.equals("442")){
            showAlphabet = "Q"; pdfView.jumpTo(90+6);
        }else if(selectAlphabet.equals("443")){
            showAlphabet = "R"; pdfView.jumpTo(91+6);
        }else if(selectAlphabet.equals("508")){
            showAlphabet = "S"; pdfView.jumpTo(101+6);
        }else if(selectAlphabet.equals("606")){
            showAlphabet = "T"; pdfView.jumpTo(120+6);
        }else if(selectAlphabet.equals("634")){
            showAlphabet = "U"; pdfView.jumpTo(125+6);
        }else if(selectAlphabet.equals("644")){
            showAlphabet = "V"; pdfView.jumpTo(127+6);
        }else if(selectAlphabet.equals("660")){
            showAlphabet = "W"; pdfView.jumpTo(130+6);
        }else if(selectAlphabet.equals("666")){
            showAlphabet = "X"; pdfView.jumpTo(132+6);
        }else if(selectAlphabet.equals("669")){
            showAlphabet = "Y"; pdfView.jumpTo(132+6);
        }else if(selectAlphabet.equals("670")){
            showAlphabet = "Z"; pdfView.jumpTo(132+6);
        }

        Toast toast = Toast.makeText(getApplicationContext(), showAlphabet, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0,0);
        toast.show();
    }


}
