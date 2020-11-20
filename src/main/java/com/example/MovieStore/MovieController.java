package com.example.MovieStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MovieController {

    private static final int ITEMS_PER_PAGE = 20;

    @Autowired
    private MovieRepository repository;

    @Autowired
    private MemberRepository repositoryMember;
    @Autowired
    private Cart shoppingCart;

    private List<Movie> items = new ArrayList<>();

    @GetMapping("/")
    public String index(){
        return "index";
    }



    @GetMapping("/imovie")
    public String movies(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) throws IOException {

        List<Movie> movies = repository.getPage(page - 1, ITEMS_PER_PAGE);
        int pageCount = repository.numberOfPages(ITEMS_PER_PAGE);
        int[] pages = toArray(pageCount);

        model.addAttribute("movies", movies);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("firstPage", 1);
        model.addAttribute("lastPage", page + 5);
        if (page > 5) {
            model.addAttribute("firstPage", page-5);
        } else if (page > pageCount-5) {
            model.addAttribute("lastPage", pageCount);
        }
        model.addAttribute("showPrev", page > 1);
        model.addAttribute("showNext", page < pageCount);
        model.addAttribute("totalNoPages", pageCount-1);

        return "imovie";
    }

    @GetMapping("/cart")
    public String cart(Model shop, @RequestParam(value = "movie", required = false, defaultValue = "1") Integer id) throws IOException {

        Movie movie = repository.getMovie(id);
        items.add(movie);
        System.out.println(id);
        shop.addAttribute("mycart", items);
        return "cart";

    }

    private int[] toArray(int num) {
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = i + 1;
        }
        return result;
    }

    @GetMapping("/members")
    public String member(Model model) {
        List<Member> memberList = repositoryMember.memberList();
        model.addAttribute("members", memberList);
        return "members";
    }

    @GetMapping("/memberlogin")
    public String loginPage(Model model, HttpSession session) {
        if (session.getAttribute("newMember") != null) {
            session.removeAttribute("newMember");
        }
        return "signIn";
    }

    @PostMapping("/tryLogin")
    String form(@RequestParam String email, String password, Model model, HttpSession session) {
        Member member = repositoryMember.MemberLoginMatch(email, password);

        if(member!=null){
            session.setAttribute("member", member);
            return "index";
        }
        else{
            model.addAttribute("message", "Wrong email or password, please try again");
            return "signIn";

        }

    }

    @GetMapping("/logout")
    String logout(HttpSession session) {
        session.removeAttribute("member");
        session.removeAttribute("newMember");
        return "signIn";
    }

    @GetMapping("/favourites")
    public String favourites(Model model, HttpSession session) {
        if(session.getAttribute("member")!=null){
           Member member = (Member) session.getAttribute("member");
            List<String> favouriteList = member.getFavouriteList();
            model.addAttribute("favouriteList", favouriteList);
            return "favourites";
        }else{
            return "signIn";
        }
    }

    @GetMapping("/signup")
    public String signup(Model model, HttpSession session) {

        return "signUp";
    }

    @PostMapping("/newMember")
    String newMember(@RequestParam String name, String email, String password, Model model, HttpSession session) {
      repositoryMember.addNewMember(name, email, password);
        Member newMember = repositoryMember.MemberEmailMatch(email, password);
        if (session.getAttribute("newMember") != null) {
            session.removeAttribute("newMember");
        }

        session.setAttribute("newMember", newMember);
        return "signIn";

    }

    @GetMapping("/payment")
    public String payment(Model model, String price) {
        model.addAttribute("price", price);
        return "payment";
    }

   /* @GetMapping("/orderConfirmation")
    public String orderConfirmation(Model model, String price) {
        model.addAttribute("price", price);
        return "orderConfirmation";
    }*/

   @GetMapping("/orderConfirmation")
    public String orderConfirmation() {
        return "orderConfirmation";
    }

}
