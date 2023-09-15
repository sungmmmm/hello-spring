package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private  final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //외부에서 넣어주도록 만들어 준다.

    //command + N constructor 생성

    /**
     * 회원가입
     */
    public  Long join(Member member){ //option + enter (import class)
        //중복 이름 회원 불가
       // Optional<Member> result = 너무 길어져서 삭제해도 optional로 반환가능
         vaildateDuplicateMember(member); //command  + option + M (Extract Method)
        //중복회원검증
        memberRepository.save(member); //memberRepository에 해당하는 save를 호출
        return member.getId(); //id를 반환
    }

    private void vaildateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // ; optional안에 많은 메소드가 존재
// memberRepository.findByName(member.getName()); 에서 command+option+v로 변환
//result
    .ifPresent(m -> { //값이 있으면
        throw new IllegalStateException("이미 존재하는 회원입니다."); //이 내용으로 작동해라
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll(); //MemberRepository 에서 return 해주면 된다. (list(member))
    }
    public Optional<Member> findIOne(Long memberId) {
        return  memberRepository.findById(memberId);
    }

}
