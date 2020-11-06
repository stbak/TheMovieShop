package com.example.MovieStore;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberRepository {

    private static List<Member> members;

    public MemberRepository(){
        members = new ArrayList<>();

        members.add(new Member(234, "Kalle Anka", "Kalle@anka.com", "PW" ));
        members.add(new Member(235, "Pippi Långstrump", "Pippi@gmail.com", "lillaGubben123" ));
        members.add(new Member(236, "Tjorven", "tjorven@gmail.com", "Båtsman" ));
        members.add(new Member(237, "Astrid Lindgren", "astrid@gmail.com", "!lindgren!" ));

        for (int i = 1; i <= 5; i++) {
            members.add(new Member(20+i, "superMoviefan" + i, "email" + i + "@gmail.com", "Password" + i));
        }
    }

    public Member getMember(int memberid) {
        for (Member member : members) {
            if (member.getMemberID() == memberid) {
                return member;
            }
        }
        return null;
    }

    public static List<Member> memberList (){return members;}

    public void addNewMember(int memberID, String password) {
            Member member1 = new Member(memberID, password);
            members.add(member1);
    }

    public static Member MemberLoginMatch(int memberID, String password){
        for(Member member : members){
            if(member.getMemberID() == memberID && member.getPassword().equals(password)){
                return member;
            }
        }return null;

    }

}
