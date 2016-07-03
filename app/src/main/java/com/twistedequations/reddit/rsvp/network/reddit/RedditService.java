package com.twistedequations.reddit.rsvp.network.reddit;

import com.twistedequations.reddit.rsvp.network.reddit.models.RedditListing;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface RedditService {

  @GET("/.json")
  Observable<RedditListing> postsForAll();

  @GET("/r/{subreddit}/comments/{postID}/.json")
  Observable<List<RedditListing>> commentsForPost(@Path("subreddit") String subreddit, @Path("postID") String postID);
}
