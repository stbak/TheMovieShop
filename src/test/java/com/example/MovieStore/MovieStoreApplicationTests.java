package com.example.MovieStore;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieStoreApplicationTests {

@Autowired
MemberRepository repository;

	@Test
	public void checkLogInMatchFails() {
		Assert.assertEquals(null, repository.MemberLoginMatch("Kalle@anka.com","KajsaAnka!"));
		Assert.assertEquals(null, repository.MemberLoginMatch("Kalle@anka.com","PWKajsaAnk"));
		Assert.assertEquals(null, repository.MemberLoginMatch("Kalle@anka.com","PWKajsaAnka!"));

	}

	@Test
	public void checkLogInMatchSuccess() {
		List<Member> memberList = new ArrayList<>(repository.memberList());
		Assert.assertEquals(memberList.get(0), repository.MemberLoginMatch("Kalle@anka.com","PWKajsaAnka!"));
		Assert.assertEquals(memberList.get(2), repository.MemberLoginMatch("tjorven@gmail.com","Båtsman"));

	}
}

