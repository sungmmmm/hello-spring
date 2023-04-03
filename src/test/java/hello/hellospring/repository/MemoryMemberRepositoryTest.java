package hello.hellospring.repository;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Member;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " +(result ==member));

        @Test
        public void findAll() {
//given
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);
            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);
//when
            List<Member> result = repository.findAll();
//then
            assertThat(result.size()).isEqualTo(2);

    }
}
