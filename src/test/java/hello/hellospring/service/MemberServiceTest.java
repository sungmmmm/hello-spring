package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEcah() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        //DI라고 불리는 과정.
    }
    @AfterEach //매서드가 끝날때마다 어떤 일을 하는것 (callback method- 다른 인자로 이용, 이벤트에 의해 호출되어지는 함수)
    public void afterEach() {
        memberRepository.clearStore();
        //+ MemoryMemberRepositor에 afterEach추가, test 가 끝날때마다 repoistory를 clear해준다.
    }

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
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when


        memberService.join(member1);
        //command + option + v
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); //예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*     try {
         memberService.join(member2);
         fail();
     }catch (IllegalStateException e) {
         assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
     }
     //주석은 option + command + /
     */
    }
    //then
}
