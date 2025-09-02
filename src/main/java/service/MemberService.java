package service;

import model.Member;
import repository.MemberRepository;

import java.util.List;

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
    // Lister tous les membres
    public List<Member> listMembers() {
        return memberRepository.findAll();
    }


}
