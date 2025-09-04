package service;

import model.Member;
import repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
        // adding a member
    public void addMember(Member member) {
        memberRepository.save(member);
    }
    // remove a member
    public void removeMember(Long id) {
        memberRepository.deleteById(id);
    }
    // give all members
    public List<Member> listMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

}
