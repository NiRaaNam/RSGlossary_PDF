package gistda.glossary.rsglossary;

import android.os.Bundle;
import android.support.v4.text.HtmlCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
public class AboutApp extends AppCompatActivity{

    Toolbar mToolbar;

    String htmlText = "<h2><font color='red'>What is RS Glossary?</font></h2>\n" +
            "<p>Android is an open source and Linux-based <b>Operating System</b> for mobile devices such as smartphones and tablet computer. Android was developed by the <i>Open Handset Alliance</i>, led by Google, and other companies.</p>\n" +
            "<p>Android offers a unified approach to application development for mobile devices which means developers need only develop for Android, and their applications should be able to run on different devices powered by Android.</p>\n" +
            "<p>The first beta version of the Android Software Development Kit (SDK) was released by Google in 2007 whereas the first commercial version, Android 1.0, was released in September 2008.</p>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_app);
        setTitle(String.format("%s ","เกี่ยวกับแอพฯ RS Glossary"));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        TextView htmlToTextView = findViewById(R.id.htmlToTextView);
        htmlToTextView.setText(HtmlCompat.fromHtml(htmlText, 0));
    }
}