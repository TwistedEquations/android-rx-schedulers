package com.twistedequations.reddit.rsvp.screens.detail.mvl;

import com.twistedequations.reddit.rsvp.network.reddit.RedditService;
import com.twistedequations.reddit.rsvp.network.reddit.models.RedditItem;
import com.twistedequations.reddit.rsvp.network.reddit.models.RedditListing;
import com.twistedequations.reddit.rsvp.screens.detail.PostActivity;
import com.twistedequations.rxstate.RxSaveState;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class PostModel {

    private static final String COMMENTS_STATE_KEY = "comments";

    private final RedditService redditService;
    private final PostActivity postActivity;

    public PostModel(RedditService redditService, PostActivity postActivity) {
        this.redditService = redditService;
        this.postActivity = postActivity;
    }

    public RedditItem getIntentRedditItem() {
        return postActivity.getIntent().getParcelableExtra(PostActivity.REDDIT_ITEM_KEY);
    }

    public Observable<List<RedditItem>> getCommentsFromState() {
        return RxSaveState.getSavedState(postActivity)
                .map(bundle -> bundle.getParcelable(COMMENTS_STATE_KEY));
    }

    public Observable<List<RedditListing>> getCommentsForPost(String subreddit, String postID) {
        return redditService.commentsForPost(subreddit, postID);
    }

    public void saveComentsState(List<RedditItem> redditListings) {
        RxSaveState.updateSaveState(postActivity, bundle -> bundle.putParcelableArrayList(COMMENTS_STATE_KEY, new ArrayList<>(redditListings)));
    }
}
