package git.followers.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import git.followers.beans.Follower;
import git.followers.utilities.Constants;

@Service
public class GitHubService {
  @Autowired
  private HttpClient httpClient;
  @Autowired
  private ObjectMapper objectMapper;

@SuppressWarnings("unchecked")
private CompletableFuture<List<Follower>> getFollowers(String user) {
     HttpRequest request = HttpRequest.newBuilder()
     .uri(URI.create("https://api.github.com/users/" + user +
     "/followers?per_page=5")).header("Authorization", "token "+Constants.API_TOKEN).build();

     return httpClient.sendAsync(request,
     BodyHandlers.ofString()).thenApply(HttpResponse::body).thenApply((content) ->
     {
     List<Follower> list = null;
     	try {
    	 	List<Follower> followers = (List<Follower>) objectMapper.readValue(content,
    		 objectMapper.getTypeFactory().constructCollectionType(List.class, Follower.class));
	     return followers;
	     } catch (Exception e) {
	    	 e.printStackTrace();
	     return new ArrayList<Follower>();
	     }
     });
  }

  private void recursive(List<Follower> users, int level) {
    if (level <= 0) {
      return;
    }
   
    for (int index = 0; users!=null && index < users.size(); index++) {
      Follower follower = (Follower) users.get(index);
      List<Follower> followers = null;
      try {
        followers = (List<Follower>) this.getFollowers(follower.getLogin()).get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }

      follower.setFollowers(followers);
      this.recursive(follower.getFollowers(), level-1);
    }
  }
    
  public List<Follower> getFollowers(String user, int level) {
    List<Follower> users = new ArrayList<Follower>();
    Follower follower = new Follower();
    follower.setLogin(user);
    users.add(follower);
    this.recursive(users, level);
    return users.get(0).getFollowers();
  }
}