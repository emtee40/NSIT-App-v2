package nsit.app.com.nsitapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.h6ah4i.android.materialshadowninepatch.MaterialShadowContainerView;

import org.json.JSONArray;
import org.json.JSONException;

import functions.ImageLoader;

/**
 * Created by Sidharth Patro on 22-Jun-15.
 */
public class VideoList_Adapter extends BaseAdapter {

    Context context;
    JSONArray FeedItems;
    ImageLoader imageLoader;
    private static LayoutInflater inflater = null;

    public VideoList_Adapter(Context context, JSONArray FeedItems) {
        this.context = context;
        this.FeedItems = FeedItems;
        imageLoader=new ImageLoader(context.getApplicationContext());
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return FeedItems.length();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        try {
            return FeedItems.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.video_listitem, null);

        MaterialShadowContainerView shadowView = (MaterialShadowContainerView) vi.findViewById(R.id.shadow_item_container);
        float density = vi.getResources().getDisplayMetrics().density;
        shadowView.setShadowTranslationZ(density * 7.0f); // 2.0 dp
        shadowView.setShadowElevation(density * 10.0f); // 4.0 dp
        Log.e("Shadow view: ", String.valueOf(density) +" "+ shadowView.getShadowElevation()+" "+shadowView.getShadowTranslationZ());

        TextView Title = (TextView) vi.findViewById(R.id.VideoTitle);
        TextView Description = (TextView) vi.findViewById(R.id.VideoDescription);
        String VideoId = null;
        ImageView VideoThumbnail =  (ImageView) vi.findViewById(R.id.VideoThumbnail);
        try {
            Title.setText(FeedItems.getJSONObject(position).getJSONObject("snippet").getString("title"));
            Description.setText(FeedItems.getJSONObject(position).getJSONObject("snippet").getString("description"));
            VideoId = FeedItems.getJSONObject(position).getJSONObject("snippet").getJSONObject("resourceId").getString("videoId");
            if (FeedItems.getJSONObject(position).getJSONObject("snippet").getJSONObject("thumbnails").has("maxres"))
            {
                imageLoader.DisplayImage(FeedItems.getJSONObject(position).getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("maxres").getString("url"), VideoThumbnail);
            }
            else{
                imageLoader.DisplayImage(FeedItems.getJSONObject(position).getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("default").getString("url"), VideoThumbnail);
            }
            Log.e("FeedItem",FeedItems.get(position).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout PlayCont = (LinearLayout)vi.findViewById(R.id.PlayContainer);
        final String finalVideoId = VideoId;
        PlayCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity)v.getContext();
                try {
                    Log.e("activity",activity.toString());
                    Log.e("finalVideoId",finalVideoId);
                    Intent intent = YouTubeStandalonePlayer.createVideoIntent(activity, "AIzaSyD-hWnEb2F-94y6XyaG5WlKXZKBpKr9PaE", finalVideoId);
                    activity.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                    alertDialog.setTitle("YouTube API not found.");
                    alertDialog.setMessage("This section needs YouTube app to be installed on your device. Please get it from the Google Play Store.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    e.printStackTrace();
                } catch (Exception e) {
                    AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                    alertDialog.setTitle("Oops!");
                    alertDialog.setMessage("The error exception is " + e.toString() + " ; Send this to us and we will get this fixed.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });

        return vi;
    }
}