import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.scenario.effect.Blend;
import models.Post;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import javax.print.DocFlavor;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //get: show new post form
        get("/posts/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newpost-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new post form
        post("/posts/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<String, Object>();
            String content = request.queryParams("content");
            Post newPost = new Post(content);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show all posts
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Post> posts = Post.getAll();
            model.put("posts", posts);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual post
        get("/posts/:id", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            int idOfPostToFind = Integer.parseInt(request.params("id"));
            Post foundPost = Post.findById(idOfPostToFind);
            model.put("post", foundPost);
            return new ModelAndView(model, "post-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a post

        //post: process a form to update a post

        //get: delete and individual post

        //get: delete all posts
    }
}