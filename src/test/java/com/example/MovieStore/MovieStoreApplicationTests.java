package com.example.MovieStore;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieStoreApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void checkLogInMatchFails() {
		Assert.assertEquals(false, MemberRepository.MemberLoginMatch(234123,"KajsaAnka!"));
		Assert.assertEquals(false, MemberRepository.MemberLoginMatch(234123,"PWKajsaAnk"));
		Assert.assertEquals(false, MemberRepository.MemberLoginMatch(234124,"PWKajsaAnka!"));

	}

	@Test
	public void checkLogInMatchSuccess() {
		Assert.assertEquals(true, MemberRepository.MemberLoginMatch(234123,"PWKajsaAnka!"));
		Assert.assertEquals(true, MemberRepository.MemberLoginMatch(236323,"Båtsman"));
	}
}

