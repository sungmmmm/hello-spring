package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepository{
     //option+enter 하면 implement methods
    // repository의 구현체
    /*
     * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
     */


    private  static Map<Long, Member> store = new HashMap<>();
    //저장 할 때 (save) 저장체 (아이디가 Long, 값은 Member) , 임포트 해야함 - control+ 스페이스 or option + enter
    private  static  long sequence = 0L; // sequence (0,1,2 키값을 생성)
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //member 를 save 할 때 id를 셋팅
        store.put(member.getId(),member); //store에 저장 map 에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { //find id 는 스토에에서 꺼내줌
        return Optional.ofNullable(store.get(id)); //null값이라도 ofNullable을 해주면 가져올 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))//*람다식 사용 (->)
                // name이 파라미터에 넘어온 name이랑 같은지 확인
                .findAny();
                //루프(맵)을 돌며 찾아 반환
    }

    @Override
    public List<Member> findAll() {//map 이지만 list 로 반환
        return new ArrayList<>(store.values());
        //values(=members임)
    }
    public void clearStore() {
        store.clear();
    }

}
