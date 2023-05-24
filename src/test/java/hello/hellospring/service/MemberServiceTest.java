package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService = new MemberService();


    @Test
    void join() { //(shift+f6)으로 한글 이름으로 변경해도 ㄱㅊ 실제로 빌드할때 code에 포함x ex. 회원가입
        //Given 먼가 주어지고
        Member member = new Member();
        member.setName("hello");


        //When 이걸 실행했을때
        Long saveId = memberService.join(member);


        //Then 이게 나와야해 (검증)
        Member findMember = memberService.findIOne(saveId).get();//optional로 받고 get으로 변경
        assertThat(member.getName()).isEqualTo(findMember.getName());//이름검증 이름을 찾는데 findMember와 이름이 getName(이름이 같다)한가.



    }
      @Test
      public void 중복_회원_예외(){
         //given
          Member member1 = new Member();
          member1.setName("spring");
          Member member2 = new Member();
          member2.setName("spring");

          //when

          memberService.join(member1);
          IllegalStateException e = assertThrows(IllegalStateException.class,
                  () -> memberService.join(member2)); //예외가 발생해야 한다.
          assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
      }
          //then
      }
    @Test
    void findMembers() {
        Member member = new Member();

    }

    @Test
    void findIOne() {
    }
}