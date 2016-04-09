package io.realm.handson2.twitter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.handson2.twitter.entity.Tweet;

public class TimelineFragment extends ListFragment {
    private Realm realm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ListAdapter adapter = new ArrayAdapter<String>(getContext(),
                R.layout.listitem_tweet,
                R.id.text,
                Arrays.asList("Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
                ));
        setListAdapter(adapter);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        realm = Realm.getDefaultInstance();
        final RealmResults<Tweet> tweets = buildTweetList();;
        final RealmBaseAdapter<Tweet> adapter = new RealmBaseAdapter<Tweet>(getContext(), tweets, true) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final Tweet tweet = getItem(position);
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.listitem_tweet, parent, false);
                }
// TODO 余裕があればViewHolderパターンを適用してください
                ((TextView) convertView.findViewById(R.id.screen_name)).setText(tweet.getScreenName());
                ((TextView) convertView.findViewById(R.id.text)).setText(tweet.getText());
                Glide.with(TimelineFragment.this).load(tweet.getIconUrl()).into((ImageView) convertView.findViewById(R.id.image));
                return convertView;
            }
        };
        setListAdapter(adapter);
    }

    @NonNull
    protected RealmResults<Tweet> buildTweetList() {
        return realm.allObjectsSorted(Tweet.class, "createdAt", Sort.DESCENDING);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((RealmBaseAdapter<?>) getListAdapter()).updateRealmResults(null);
        realm.close();
        realm = null;
    }
}