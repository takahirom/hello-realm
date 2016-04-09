package io.realm.handson2.twitter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.handson2.twitter.entity.Tweet;

public class FavoritedFragment extends TimelineFragment {
    @NonNull
    @Override
    protected RealmResults<Tweet> buildTweetList(Realm realm) {
        return realm.where(Tweet.class)
                .equalTo("favorited", true)
                .findAllSorted("createdAt", Sort.DESCENDING);
    }
}