package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepository{

    private  static Map<Long, Member> store = new HashMap<>();
    private  static  long sequence = 0L; // sequence (0,1,2생성)
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member); //store에 저장 map 에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { //find id 는 스토에에서 꺼내줌
        return Optional.ofNullable(store.get(id)); //null값이라도 ofNullable을 해주면 가져올 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) { //
        store.values().stream()
                .filter(member -> member.getName().equals(name))//get name이 파라미터에 넘어온 name이랑 같은지 확인
                .findAny();//루프(맵)을 돌며 찾아 반환
    }

    @Override
    public List<Member> findAll() {//map이지만 list 로 반환
        return new ArrayList<>(store.values());//values(=members임)
    }
}
