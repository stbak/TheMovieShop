package com.example.MovieStore;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieStoreApplicationTests {


	@Test
	public void checkLogInMatchFails() {
		Assert.assertEquals(null, MemberRepository.MemberLoginMatch(234,"KajsaAnka!"));
		Assert.assertEquals(null, MemberRepository.MemberLoginMatch(234,"PWKajsaAnk"));
		Assert.assertEquals(null, MemberRepository.MemberLoginMatch(237,"PWKajsaAnka!"));

	}

	@Test
	public void checkLogInMatchSuccess() {
		List<Member> memberList = new ArrayList<>(MemberRepository.memberList());
		Assert.assertEquals(memberList.get(0), MemberRepository.MemberLoginMatch(234,"PWKajsaAnka!"));
		Assert.assertEquals(memberList.get(2), MemberRepository.MemberLoginMatch(236,"Båtsman"));
	}
}

