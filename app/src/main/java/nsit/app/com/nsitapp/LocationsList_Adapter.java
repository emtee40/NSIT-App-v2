package nsit.app.com.nsitapp;

import android.content.Context;
<<<<<<< HEAD
import android.util.Log;
=======
>>>>>>> 16672fc86ebd5ca817d71f282fe923e68b08798d
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
<<<<<<< HEAD
=======
import android.widget.BaseAdapter;
>>>>>>> 16672fc86ebd5ca817d71f282fe923e68b08798d
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
<<<<<<< HEAD
=======
import android.widget.LinearLayout;
import android.widget.ListView;
>>>>>>> 16672fc86ebd5ca817d71f282fe923e68b08798d
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Sidharth Patro on 22-Jun-15.
 */
public class LocationsList_Adapter extends BaseExpandableListAdapter {

    Context context;
    ArrayList<Locations.LocationGroup> LocationItems = new ArrayList<>();
    ArrayList<Locations.Location> LocationGroupItems = new ArrayList<>();
    public int groupClicked;

    private static LayoutInflater inflater = null;
    ExpandableListView listView;
    public static int lastExpandedGroupPosition = -1;


    public void setGroupClicked(int groupPosition){
        groupClicked = groupPosition;
    }

    public LocationsList_Adapter(Context context, ArrayList<Locations.LocationGroup> LocationItems, ArrayList<Locations.Location> LocationGroupItems) {
        this.context = context;
        this.LocationItems = LocationItems;
        this.LocationGroupItems = LocationGroupItems;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getGroupCount() {
        return LocationItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.LocationItems.get(groupPosition).Locations.size();
    }

    @Override
    public Locations.LocationGroup getGroup(int groupPosition) {
        return LocationItems.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.LocationItems.get(groupPosition).Locations.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Locations.LocationGroup LocGroup = getGroup(groupPosition);
        String headerTitle = LocGroup.GroupHeader;
        String groupType = LocGroup.GroupType;
<<<<<<< HEAD
        listView = (ExpandableListView)parent;
=======
>>>>>>> 16672fc86ebd5ca817d71f282fe923e68b08798d
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.location_listitem, null);
        }
        this.listView = (ExpandableListView)parent;

        TextView GroupHeader = (TextView) convertView
                .findViewById(R.id.LocationsGroupHeader);
        GroupHeader.setText(headerTitle);
        ImageView GroupIcon = (ImageView)convertView.findViewById(R.id.GroupTypeImage);

        switch(groupType){
            case "College": GroupIcon.setImageResource(R.drawable.ic_school_black_24dp);
<<<<<<< HEAD
                            break;
=======
                break;
>>>>>>> 16672fc86ebd5ca817d71f282fe923e68b08798d
            case "Campus": GroupIcon.setImageResource(R.drawable.ic_business_black_24dp);
                break;
            case "Hostel": GroupIcon.setImageResource(R.drawable.ic_hotel_black_24dp);
                break;
            case "Canteen": GroupIcon.setImageResource(R.drawable.ic_local_cafe_black_24dp);
                break;
            case "Stationery": GroupIcon.setImageResource(R.drawable.ic_brush_black_24dp);
                break;
            case "ATM": GroupIcon.setImageResource(R.drawable.ic_credit_card_black_24dp);
                break;
            case "WiFi": GroupIcon.setImageResource(R.drawable.ic_network_wifi_black_24dp);
                break;
            case "Sports": GroupIcon.setImageResource(R.drawable.ic_directions_bike_black_24dp);
                break;
            case "Miscellaneous": GroupIcon.setImageResource(R.drawable.ic_public_black_24dp);
                break;
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = ((Locations.Location)getChild(groupPosition, childPosition)).Name;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.locationgroup_listitem, null);
        }

        TextView txtHeader = (TextView) convertView.findViewById(R.id.LocationItem);
        txtHeader.setText(childText);

<<<<<<< HEAD
        if(lastExpandedGroupPosition == groupPosition) {
            AnimationSet set = new AnimationSet(true);
            TranslateAnimation slide = new TranslateAnimation(0, 0, -50, 0);
            slide.setInterpolator(new DecelerateInterpolator(5.0f));
            slide.setDuration(100);
            Animation fade = new AlphaAnimation(0, 1.0f);
            fade.setInterpolator(new DecelerateInterpolator(5.0f));
            fade.setDuration(100);
            set.addAnimation(slide);
            set.addAnimation(fade);
            set.setStartOffset(childPosition * 100);
            convertView.startAnimation(set);
        }

            return convertView;
=======
        AnimationSet set = new AnimationSet(true);
        TranslateAnimation slide = new TranslateAnimation(0,0,-50,0);
        slide.setInterpolator(new DecelerateInterpolator(5.0f));
        slide.setDuration(100);
        Animation fade = new AlphaAnimation(0,1.0f);
        fade.setInterpolator(new DecelerateInterpolator(5.0f));
        fade.setDuration(100);
        set.addAnimation(slide);
        set.addAnimation(fade);
        set.setStartOffset(childPosition*100);
        convertView.startAnimation(set);
        return convertView;
>>>>>>> 16672fc86ebd5ca817d71f282fe923e68b08798d
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
<<<<<<< HEAD

    @Override
    public void onGroupExpanded(int groupPosition){
        lastExpandedGroupPosition = groupPosition;
    }
}

=======
}
>>>>>>> 16672fc86ebd5ca817d71f282fe923e68b08798d
