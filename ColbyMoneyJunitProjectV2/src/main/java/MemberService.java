import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.TMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service component scan
public class MemberService {

    //private final TMemberRespository memberRespository = new TMemberRespository();
    private final MemberRepository memberRepository;


    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){

        validateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateMember(Member member){
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {throw new IllegalStateException("Name is already exists");});
    }


    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


    // Dependency injection

}