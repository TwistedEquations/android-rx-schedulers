package com.twistedequations.reddit.rsvp.screens.home.mvp;

import com.twistedequations.reddit.rsvp.network.reddit.RedditService;
import com.twistedequations.reddit.rsvp.network.reddit.models.RedditItem;
import com.twistedequations.reddit.rsvp.network.reddit.models.RedditListing;
import com.twistedequations.reddit.rsvp.screens.detail.PostActivity;
import com.twistedequations.reddit.rsvp.screens.home.HomeActivity;

import rx.Observable;

public class HomeModel {

    private final RedditService redditService;
    private final HomeActivity homeActivity;

    public HomeModel(RedditService redditService, HomeActivity homeActivity) {
        this.redditService = redditService;
        this.homeActivity = homeActivity;
    }

    public Observable<RedditListing> getSavedRedditListing() {
        return homeActivity.getPrevState()
                .map(bundle -> bundle.getParcelable("reddit_listing"));
    }

    public Observable<RedditListing> postsForAll() {
        return redditService.postsForAll();
    }

    public RedditListing saveRedditListing(RedditListing redditListing) {
        homeActivity.updateSaveState(bundle -> bundle.putParcelable("reddit_listing", redditListing));
        return redditListing;
    }

    public void startDetailActivity(RedditItem item) {
        PostActivity.start(homeActivity, item);
    }
}
