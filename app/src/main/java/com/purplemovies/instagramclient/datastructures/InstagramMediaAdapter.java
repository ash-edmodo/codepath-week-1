package com.purplemovies.instagramclient.datastructures;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.purplemovies.instagramclient.DeviceDimensionsHelper;
import com.purplemovies.instagramclient.R;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

/**
 * Created by ash on 2/3/15.
 */
public class InstagramMediaAdapter extends ArrayAdapter<InstagramMedia> {

    public InstagramMediaAdapter(Context context, List<InstagramMedia> objects) {

        super(context, R.layout.photo_item, objects);
    }

    @Override
    public View getView(int position, View convView, ViewGroup parGroup) {

        // get item
        InstagramMedia item = getItem(position);

        final Context context = getContext();
        if (convView == null) {
            convView = LayoutInflater.from(context).inflate(R.layout.photo_item, parGroup, false);
        }

        ImageView imageView = (ImageView) convView.findViewById(R.id.ivPhoto);
        TextView captionTextView = (TextView) convView.findViewById(R.id.caption_text_view);
        TextView usernameTextView = (TextView) convView.findViewById(R.id.username_text_view);
        ImageView profileImageView = (ImageView) convView.findViewById(R.id.profile_image_view);
        TextView timeStampTextView = (TextView) convView.findViewById(R.id.timestamp_text_view);
        TextView likesTextView = (TextView) convView.findViewById(R.id.likes_text_view);

        imageView.setImageResource(0);
        Picasso.with(context)
                .load(item.getImageUrl())
                .placeholder(R.drawable.loading_indicator)
                .resize(DeviceDimensionsHelper.getDisplayWidth(context),0).into(imageView);

        Picasso.with(context)
                .load(item.getmProfileImageUrl())
                .into(profileImageView);
        
        final Date createTime = item.getCreateTime();
        final String createTimeFormatted = DateUtils.getRelativeTimeSpanString(createTime.getTime()).toString();
        captionTextView.setText(item.getCaption());
        usernameTextView.setText(item.getAuthorName());
        timeStampTextView.setText(createTimeFormatted);
        likesTextView.setText(String.format(context.getString(R.string.n_likes), item.getLikesCount()));
        return convView;
    }
}
