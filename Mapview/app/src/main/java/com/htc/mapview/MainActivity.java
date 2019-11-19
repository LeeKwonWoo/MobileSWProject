package com.htc.mapview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    String key = "97LYMB0fJkAg2B2u4fk76Z5ywPhmDYfaDo689Fir4D8K4aejesj9wCtEyiJBYyd6HsICyw1T7KvgExRf4L2RMg%3D%3D ";
    String data;
    EditText edit;
    TextView text;
    LinearLayout linearLayout1;
    TMapGpsManager gps = null;
    Button btn1;
    String msg;

    boolean num=false;
    boolean eng=false;
    boolean kor=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         edit= findViewById(R.id.text1);
         //text= findViewById(R.id.pars1);

       linearLayout1 = (LinearLayout)findViewById(R.id.map1);


        TMapView tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey("0810776c-7c12-459f-87c1-84fad6d4e4c4");
        tMapView.setCompassMode(true);
        tMapView.setIconVisibility(true);
        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setTrackingMode(true);
        tMapView.setSightVisible(true);
        linearLayout1.addView(tMapView);

        final ArrayList arrayList = new ArrayList();
        arrayList.add(new TMapPoint(37.573611,126.976011));
        arrayList.add(new TMapPoint(37.571076,126.995880));
        arrayList.add(new TMapPoint(37.559352,127.002350));
        arrayList.add(new TMapPoint(37.5660935,127.0455256));
        arrayList.add(new TMapPoint(37.540085,127.002804));
        arrayList.add(new TMapPoint(37.580650,127.047938));
        arrayList.add(new TMapPoint(37.598454,127.061848));

        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
        for (int i=0; i<arrayList.size(); i++)
        {
            TMapMarkerItem mapMarkerItem1= new TMapMarkerItem();
            mapMarkerItem1.setIcon(bitmap);
            mapMarkerItem1.setTMapPoint((TMapPoint) arrayList.get(i));
            tMapView.addMarkerItem("전기차 충전소"+i,mapMarkerItem1);
        }
        tMapView.setTrackingMode(true);
        tMapView.setSightVisible(true);
    }
    public void btnclick(View v)
    {

        for(int i=0; i< edit.getText().length(); i++)
        {
            int index = edit.getText().charAt(i);
            if(index>= 48 && index <=57)
            {
                num=true;
            }
            else if (index >=65 && index <=122)
            {
                eng=true;
            }
            else
            {
                kor=true;
            }
        }
        if(num)
        {
            Toast.makeText(getApplicationContext(),"숫자입니다.",Toast.LENGTH_SHORT).show();
        }
        else if (eng)
        {
            Toast.makeText(getApplicationContext(),"영어 입니다.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"한글입니다.",Toast.LENGTH_SHORT).show();
        }

    }
//    public void mOnClick(View v)
//    {
//        switch (v.getId())
//        {
//            case  R.id.serch:
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        data = getOpenData();
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                text.setText(data);
//                            }
//                        });
//                    }
//                }).start();
//                break;
//        }
//    }

//    String getOpenData()
//    {
//        StringBuffer buffer = new StringBuffer();
//
//        String str = edit.getText().toString();
//        String location = URLEncoder.encode(str);
//
//        String queryUrl="http://open.ev.or.kr:8080/openapi/services/rest/EvChargerService?serviceKey=97LYMB0fJkAg2B2u4fk76Z5ywPhmDYfaDo689Fir4D8K4aejesj9wCtEyiJBYyd6HsICyw1T7KvgExRf4L2RMg%3D%3D"
//              +"addrDoro="+location +"&pageNo=1&numOFRows=1000&ServiceKey="+key;
//        try {
//
//            URL url = new URL(queryUrl);
//            InputStream is = url.openStream();
//
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            XmlPullParser xpp = factory.newPullParser();
//            xpp.setInput(new InputStreamReader(is, StandardCharsets.UTF_8));
//
//            String tag;
//
//            xpp.next();
//            int eventType=xpp.getEventType();
//            while (eventType != XmlPullParser.END_DOCUMENT)
//            {
//                switch (eventType)
//                {
//                    case XmlPullParser.START_DOCUMENT:
//                        break;
//
//                        case XmlPullParser.START_TAG:
//                            tag=xpp.getName();
//                            if (tag.equals("item"));
//                            else if(tag.equals("statNm"))
//                            {
//                                buffer.append("명칭: ");
//                                xpp.next();
//                                buffer.append(xpp.getText());
//                                buffer.append("\n");
//                            }
//                            else if(tag.equals("addrDoro"))
//                            {
//                                buffer.append("주소: ");
//                                xpp.next();
//                                buffer.append(xpp.getText());
//                                buffer.append("\n");
//                            }
//                            break;
//
//                            case XmlPullParser.TEXT:
//                                break;
//
//                                case XmlPullParser.END_DOCUMENT:
//                                    tag=xpp.getName();
//                                    if (tag.equals("item")) buffer.append("\n");
//                                    break;
//                }
//                eventType =xpp.next();
//            }
//        }catch (Exception e)
//        {
//        }
//        return  buffer.toString();
//        }

    }
