package com.example.MovieStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class MemberRepository {

    @Autowired
    private DataSource dataSource;

    public Member getMember(int memberID) {
        Member member =null;
        try(Connection conn=dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM MEMBER AS M WHERE M.MEMBERID = ?")){
            ps.setInt(1,memberID);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                member=(rsMember(rs));
            }
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

    public List<Member> memberList (){
        List<Member> members = new ArrayList<>();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM MEMBER")){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                members.add(rsMember(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return members;
    }

    private Member rsMember(ResultSet rs) throws SQLException {
        return new Member(rs.getInt("memberID"),
                rs.getString("fullname"),
                rs.getString("email"),
                rs.getString("Password"));
    }

    @Modifying
    public void addNewMember(String fullName, String email, String password) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO MEMBER (FULLNAME, EMAIL, PASSWORD) VALUES (?,?,?)")) {
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Member MemberLoginMatch(String email, String password){
       Member member = null;
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM MEMBER AS M WHERE M.Email=? AND M.Password=?")){
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                member=(rsMember(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(member==null){
            return null;
        }else{
            return member;
        }

        /*
        for(Member member : members){
            if(member.getMemberID() == memberID && member.getPassword().equals(password)){
                return member;
            }
        }return null;*/

    }

    public Member MemberEmailMatch(String email, String password){
        Member member = null;
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM MEMBER AS M WHERE M.EMAIL=? AND M.Password=?")){
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                member=(rsMember(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return member;
    }

}
