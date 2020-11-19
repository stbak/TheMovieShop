package com.example.MovieStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberRepository {

    @Autowired
    private static DataSource dataSource;




   /*  public MemberRepository(){

        members = new ArrayList<>();
        members.add(new Member(234, "Kalle Anka", "Kalle@anka.com", "PW" ));
        members.add(new Member(235, "Pippi Långstrump", "Pippi@gmail.com", "lillaGubben123" ));
        members.add(new Member(236, "Tjorven", "tjorven@gmail.com", "Båtsman" ));
        members.add(new Member(237, "Astrid Lindgren", "astrid@gmail.com", "!lindgren!" ));

        for (int i = 1; i <= 5; i++) {
            members.add(new Member(20+i, "superMoviefan" + i, "email" + i + "@gmail.com", "Password" + i));

    } }*/

    public Member getMember(int memberid) {
        Member member =null;
        try(Connection conn=dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT M.MEMBERID, M.FULLNAME, M.EMAIL FROM MEMBER AS M WHERE M.ID = ?")){
            ps.setInt(1,memberid);
            ResultSet rs =ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }return member;
        /*
        for (Member member : members) {
            if (member.getMemberID() == memberid) {
                return member;
            }
        }
        return null;*/
    }

    public static List<Member> memberList (){
        List<Member> members = new ArrayList<>();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT M.MEMBERID, M.FULLNAME, M.EMAIL FROM MEMBER")){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                members.add(rsMember(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return members;
    }

    private static Member rsMember(ResultSet rs) throws SQLException {
        return new Member(rs.getInt("id"),
                rs.getString("fullname"),
                rs.getString("email"),
                rs.getString("password"));
    }


    public void addNewMember(String fullName, String email, String password) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO MEMBER (FULLNAME, EMAIL, PASSWORD) VALUES (?,?,?);")) {
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, password);
            ResultSet rs = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

       /* int memberID=createID();
            Member member1 = new Member(memberID, name,  email, password);
            members.add(member1);
    }*/



    public static Member MemberLoginMatch(int memberID, String password){
       Member member = null;
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT M.MEMBERID, M.FULLNAME, M.EMAIL FROM MEMBER WHERE M.MEMBERID=? AND M.PASSWORD=?")){
            ps.setInt(1, memberID);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return member;
        /*
        for(Member member : members){
            if(member.getMemberID() == memberID && member.getPassword().equals(password)){
                return member;
            }
        }return null;*/

    }
    
    public static Member MemberEmailMatch(String email, String password){
        Member member = null;
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT M.MEMBERID, M.FULLNAME, M.EMAIL FROM MEMBER WHERE M.EMAIL=? AND M.PASSWORD=?")){
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return member;
        /*
        for(Member member : members){
            if(member.getEmail().equals(email) && member.getPassword().equals(password)){
                return member;
            }
        }return null;
*/
    }

}
