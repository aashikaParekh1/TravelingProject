package com.example.listviewproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView cdc;
    TextView cdcL;

    ImageView image;
    TextView text;
    Button button;
    ListView portLand;
    TextView desc;

    TextView country;
    TextView level;


    String location;
    String description;
    int thrillLevel;

    //2 list views
    //TextView countryPort;
    //TextView levelPort;
    //TextView countryLand;
    //TextView levelLand;


    int selected = 0;
    int start = 0;

    public static final String starting = "start";
    public static final String theKey = "saved";
    public static final String select = "select";
    public static final String COUNTRY_KEY = "savedCountry";
    public static final String LEVEL_KEY = "savedLevel";
    public static final String DESC_KEY = "savedDesc";


    ArrayList<Adventure> list;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(theKey, list);
        outState.putInt(select, selected);
        outState.putInt(starting, start);
        outState.putString(COUNTRY_KEY, location);
        outState.putInt(LEVEL_KEY, thrillLevel);
        outState.putString(DESC_KEY, description);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        //links code
        cdc = findViewById(R.id.id_activity_link);
        cdc.setMovementMethod(LinkMovementMethod.getInstance());

        //cdcL = findViewById(R.id.activity_land_link);
        //cdcL.setMovementMethod(LinkMovementMethod.getInstance());



        image = findViewById(R.id.id_adapter_img);
        text = findViewById(R.id.id_adapter_text);
        button = findViewById(R.id.id_adapter_button);

        portLand = findViewById(R.id.id_listView);//list view

        country = findViewById(R.id.id_country); //country textview in portrait mode
        level = findViewById(R.id.id_port);    //thrill level textview in portrait mode
        desc = findViewById(R.id.id_landadpater_desc);



        Adventure NorthernLights = new Adventure((R.drawable.lights), "Northern Lights", "Iceland", 2, "Checkout the Northern Lights if you're able to bear the cold weather, and enjoy open skies wih stars and beautiful colors.");
        Adventure SwimWithSharks = new Adventure((R.drawable.sharks), "Swim with the Sharks", "Bahamas", 9, "Thinking of getting over your fear of sharks. Let them swim around you in open waters!");
        Adventure Maldives = new Adventure((R.drawable.mald), "Maldives", "Maldives, Asia", 4, "Want to spend a nice weekend away at a luxurious cabana with clear blue waters, and have your own private chef, check out the Maldives.");
        Adventure BungeeJumping = new Adventure((R.drawable.bungee), "Bungee Jumping", "Western Cape, South Africa", 8, "Interested in falling from a 216 meter bridge, looking straight down into a river, check out Bloukrans Bridge!");
        Adventure CliffJumping = new Adventure((R.drawable.cliff), "Cliff Diving", "Hawaii", 7, "If you're not scared of heights and jumping into waters 40 feet up above sea level, check out the massive cliff locations in Hawaii.");
        Adventure SleepInAnIgloo = new Adventure((R.drawable.igloo), "Sleep in an Igloo", "Finland", 3, "Curious to see spend a day living in a heated, luxury igloo, checkout Finlands gorgeous igloos.");
        Adventure MoraineLake = new Adventure((R.drawable.morlake), "Moraine Lake", "Canada", 3, "Interested in checking out one of the most tranquil landscapes in Canada, checkout the beautiful Moraine Lake.");
        Adventure SnowboardingInTheAlps = new Adventure((R.drawable.snowboard), "Snowboarding", "Alps (Switzerland)", 6, "Are U.S. slopes not difficult for you anymore, checkout the Alps in Switzerland. The Alps have breathtaking views and fresh powdered snow everyday!");
        Adventure SkyDiving = new Adventure((R.drawable.sky), "Sky Diving", "Palm Jumeirah, Dubai", 10, "Is bungee jumping not scary enough for you. Imagine looking out a plane 13,000 feet in the air!");
        Adventure SleepingInAnCanyon = new Adventure((R.drawable.grand), "Sleeping 500ft in the air", "Utah", 10, "If you really want to push your fear of heights, sleep 500ft above the ground by grappling a 10 ft hammock in the center of the cliff.");



        if (savedInstanceState != null) {
            list = (ArrayList<Adventure>) savedInstanceState.getSerializable(theKey);
            selected = savedInstanceState.getInt(select);
            location = savedInstanceState.getString(COUNTRY_KEY);
            thrillLevel = savedInstanceState.getInt(LEVEL_KEY);
            description = savedInstanceState.getString(DESC_KEY);

            country.setText("Country: " + location);
            level.setText("Thrill Level: " + thrillLevel);
            //desc.setText(description);

            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) //only if orientation is landscape will description output
            {
                desc.setText(description);
            }

        }
        else
        {

            list.add(NorthernLights);
            list.add(MoraineLake);
            list.add(SnowboardingInTheAlps);
            list.add(SwimWithSharks);
            list.add(Maldives);
            list.add(BungeeJumping);
            list.add(SkyDiving);
            list.add(CliffJumping);
            list.add(SleepInAnIgloo);
            list.add(SleepingInAnCanyon);
        }

        CustomAdapter adapter = new CustomAdapter(this, R.layout.adapter_listviewproj, list);
        portLand.setAdapter(adapter);
    }


    public class CustomAdapter extends ArrayAdapter<Adventure>
    {
        Context myContext;
        int xml;
        ArrayList<Adventure> list;


        public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Adventure> objects) {
            super(context, resource, objects);

            this.myContext = context;
            this.xml = resource;
            this.list = objects;
        }


        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterView = inflater.inflate(xml, null);

            TextView textView = adapterView.findViewById(R.id.id_adapter_text);
            Button button = adapterView.findViewById(R.id.id_adapter_button);
            ImageView img = adapterView.findViewById(R.id.id_adapter_img);
            textView.setText(list.get(position).getTitle());
            img.setImageResource(list.get(position).getImage());
            //System.out.println("AASHIKA PARKEH");


            button.setText("delete");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                    System.out.println("AASHIKAPAREKH");
                }



            });

            portLand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                    location = list.get(i).getLoc();
                    country.setText("Country: " + location);


                    thrillLevel = list.get(i).getThrill();
                    level.setText("Thrill Level: " + thrillLevel);

                    description = list.get(i).getDesc();

                    System.out.println("AASHIKAPAREKH" + location);
                    System.out.println("AASHIKAPAREKH" + thrillLevel);
                    System.out.println("AASHIKAPAREKH" + desc);

                    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) //only if orientation is landscape will description output
                    {
                        desc.setText(description);
                    }
                }
            });

            return adapterView;
        }

    }
}

