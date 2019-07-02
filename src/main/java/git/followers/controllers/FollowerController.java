package git.followers.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import git.followers.beans.Follower;
import git.followers.services.GitHubService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class FollowerController {
    @Autowired
    private GitHubService gitHubService;

    @RequestMapping(path = "/followers/{user}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Follower> getFollowers(@PathVariable String user,
            @RequestParam(required = false, defaultValue = "3") Integer level)
            throws InterruptedException, ExecutionException {
                return gitHubService.getFollowers(user, level);
    }

}