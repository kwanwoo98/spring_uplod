package org.zerock.spbt.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity{
    @Id
    private String mid;

    private String mpw;
    private String email;
    private boolean del;

    private boolean social;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<org.zerock.b01.domain.MemberRole> roleSet = new HashSet<>();

    public void changePassword(String mpw){
        this.mpw=mpw;
    }
    public void changeEmail(String email){
        this.email=email;
    }
    public void changeDel(boolean del){
        this.del=del;
    }
    public void changeSocial(boolean social){
        this.social=social;
    }
    public void addRole(org.zerock.b01.domain.MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void clearRoles(){
        this.roleSet.clear();
    }
}
