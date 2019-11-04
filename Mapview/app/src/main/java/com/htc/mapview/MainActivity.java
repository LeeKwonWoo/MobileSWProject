package com.htc.mapview;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skt.Tmap.TMapView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String key = "97LYMB0fJkAg2B2u4fk76Z5ywPhmDYfaDo689Fir4D8K4aejesj9wCtEyiJBYyd6HsICyw1T7KvgExRf4L2RMg%3D%3D ";
    String data;
    EditText edit;
    TextView text;
    //LinearLayout linearLayout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         edit= findViewById(R.id.text1);
         text= findViewById(R.id.pars1);

       // linearLayout1 = (LinearLayout)findViewById(R.id.map1);


        TMapView tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey("0810776c-7c12-459f-87c1-84fad6d4e4c4");
        tMapView.setCompassMode(true);
        tMapView.setIconVisibility(true);
        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setTrackingMode(true);
        tMapView.setSightVisible(true);
        //linearLayout1.addView(tMapView);
    }
    public void mOnClick(View v)
    {
        switch (v.getId())
        {
            case  R.id.serch:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //TODO Auto-generatred method stub
                        data = getOpenData();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(data);
                            }
                        });
                    }
                }).start();
                break;
        }
    }

    String getOpenData()
    {
        StringBuffer buffer = new StringBuffer();

        String str = edit.getText().toString();
        String location = URLEncoder.encode(str);

        String queryUrl="http://open.ev.or.kr:8080/openapi/services/rest/EvChargerService?serviceKey=97LYMB0fJkAg2B2u4fk76Z5ywPhmDYfaDo689Fir4D8K4aejesj9wCtEyiJBYyd6HsICyw1T7KvgExRf4L2RMg%3D%3D"
                +"addr="+location
                +"&pageNo=1&numOFRows=1000&ServiceKey="+key;
        try {

            URL url = new URL(queryUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, StandardCharsets.UTF_8));

            String tag;

            xpp.next();
            int eventType=xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        break;

                        case XmlPullParser.START_TAG:
                            tag=xpp.getName();
                            if (tag.equals("item"));
                            else if(tag.equals("statNm"))
                            {
                                buffer.append("명칭: ");
                                xpp.next();
                                buffer.append(xpp.getText());
                                buffer.append("\n");
                            }
                            else if(tag.equals("addrDoro"))
                            {
                                buffer.append("주소: ");
                                xpp.next();
                                buffer.append(xpp.getText());
                                buffer.append("\n");
                            }
                            break;

                            case XmlPullParser.TEXT:
                                break;

                                case XmlPullParser.END_DOCUMENT:
                                    tag=xpp.getName();
                                    if (tag.equals("item")) buffer.append("\n");
                                    break;
                }
                eventType =xpp.next();
            }
        }catch (Exception e)
        {
         // TODO Auto-generated catch blocke.printStackTrace();
        }
        return  buffer.toString();
        }

    }
