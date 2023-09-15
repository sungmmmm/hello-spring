package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    //MemoryMemberRepository 를 만 테스트 MemberRepository를 대체해서 써주기
    //+ MemoryMemberRepositor에 afterEach추가

    @AfterEach //매서드가 끝날때마다 어떤 일을 하는것 (callback method- 다른 인자로 이용, 이벤트에 의해 호출되어지는 함수)
    public void afterEach() {
    repository.clearStore();
        //+ MemoryMemberRepositor에 afterEach추가, test 가 끝날때마다 repoistory를 clear해준다.
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");//이름 셋팅 ,다음 단으로 바로 넘어가기 ( command + shift + enter)
        repository.save(member); //member 저장 후  아레 검증으로 넘어가기

        Member result = repository.findById(member.getId()).get();
        // 검증 optional 에서 값을 꺼낼 때 get으로 꺼낼 수 있음(바로 꺼내면 안좋지만 테스트는 그냥 하자)
        //Member ... = (option + command + v)
        //검증 - new 에서 저장한거와 db에서 저장한게 똑같으면 참임
     //System.out.println("result = " + (result == member));으로 할 수 있지만  글자로만 볼 수 없음 그럴때는 Assertions
        // soutv+tab
        //Assertions.assertEquals(member, result);
       // Assertions.assertThat(member).isEqualTo(result ); 를 아래 로 변경 하는 방법 : option+enter(Add on - 누르기)

        assertThat(member).isEqualTo(result);
        //위 설정 하면 import static org.assertj.core.api.Assertions.*;로 들어가 바로 사용 가능
    }
    @Test //이름으로 찾기
    public void findByName(){
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member(); //똑같은거 복사하고 shift + F6 rename 된다.
    member2.setName("spring2");
    repository.save(member2);

        Member result = repository.findByName("spring1").get();//option + command + v


        assertThat(result).isEqualTo(member1);

    }

    @Test
    public  void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //findall 이라 2개 이상 넣어줘야함
        member1.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll(); //option + command + v result 가 나온다
        assertThat(result.size()).isEqualTo(2); //검증 (3)은 에러 나온다

    }

}
