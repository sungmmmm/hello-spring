package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {//interface 로 생성하기
    Member save(Member member); //회원 저장
    Optional<Member> findById(Long id); //null바로 반환 보다 optional로 감싸서 하는걸 선호 (자바 8에 들어가는 기능)
    Optional<Member> findByName(String name);//자바 8에 들어가는 기능
    List<Member> findAll(); // 모든 리스트 반환
}
