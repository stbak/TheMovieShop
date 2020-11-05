package com.example.MovieStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    private static final int PAGE_SIZE = 10;

    @Autowired
    private MovieRepository repository;
    @Autowired
    private MemberRepository repositoryMember;



    @GetMapping("/")
    public String movies(Model model, @RequestParam(value="page", required=false, defaultValue="1") int page) {

        List<Movie> movies = repository.getPage(page-1, PAGE_SIZE);
        int pageCount = repository.numberOfPages(PAGE_SIZE);
        int[] pages = toArray(pageCount);

        model.addAttribute("movies", movies);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("showPrev", page > 1);
        model.addAttribute("showNext", page < pageCount);

        return "movies";
    }

    private int[] toArray(int num) {
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = i+1;
        }
        return result;
    }


    @GetMapping("/members")
    public String member(Model model) {

        List<Member> memberList = repositoryMember.memberList();
        model.addAttribute("members", memberList);
        return "members";
    }
}
