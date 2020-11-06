package com.example.MovieStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MovieController {

    private static final int ITEMS_PER_PAGE = 20;

    @Autowired
    private MovieRepository repository;
    @Autowired
    private MemberRepository repositoryMember;



    @GetMapping("/")
    public String movies(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {

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
    public String loginPage(Model model) {
        return "signIn";
    }

    @PostMapping("/tryLogin")
    String form(@RequestParam Integer memberID, String password, Model model, HttpSession session) {
        Member member = MemberRepository.MemberLoginMatch(memberID, password);
        if(member!=null){
            session.setAttribute("member", member);
            return "SuccessLoginPage";
        }
        else{
            return "FailedLoginPage";
        }

    }

    @GetMapping("/logout")
    String logout(HttpSession session) {
        session.removeAttribute("member");
        return "LogInPage";
    }


    @GetMapping("/favourites")
    public String favourites(Model model, HttpSession session) {
        if(session.getAttribute("member")!=null){
           Member member = (Member) session.getAttribute("member");
            List<String> favouriteList = member.getFavouriteList();
            model.addAttribute("favouriteList", favouriteList);
            return "favourites";
        }else{
            return "LogInPage";
        }
    }


}
