package repository;

import model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberRepository {
    private List<Member> members = new ArrayList<>();

    public void save(Member member) {
        members.add(member);
    }

    public void deleteById(Long id) {
        members.removeIf(m -> m.getMemberId().equals(id));
    }

    public Optional<Member> findById(Long id) {
        return members.stream()
                .filter(m -> m.getMemberId().equals(id))
                .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(members);
    }
}
