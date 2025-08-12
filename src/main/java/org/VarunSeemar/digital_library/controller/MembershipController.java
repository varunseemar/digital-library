package org.VarunSeemar.digital_library.controller;

import jakarta.validation.Valid;
import org.VarunSeemar.digital_library.adapter.MembershipAdapter;
import org.VarunSeemar.digital_library.entities.input.MembershipInputEntity;
import org.VarunSeemar.digital_library.entities.input.MembershipStatusUpdateEntity;
import org.VarunSeemar.digital_library.enums.MembershipStatus;
import org.VarunSeemar.digital_library.exceptions.MembershipException;
import org.VarunSeemar.digital_library.model.MembershipModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/membership")
public class MembershipController {

    private final MembershipAdapter membershipAdapter;

    @Autowired
    public MembershipController(MembershipAdapter membershipAdapter){
        this.membershipAdapter = membershipAdapter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMembership(@PathVariable long id){
        MembershipModel membershipModel = this.membershipAdapter.getMembership(id);
        if(membershipModel == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membership Cant Be Found.");
        }
        return ResponseEntity.ok(membershipModel);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMembership(@Valid @RequestBody MembershipInputEntity membershipInputEntity){
        try{
            return new ResponseEntity<>(this.membershipAdapter.save(membershipInputEntity), HttpStatus.CREATED);
        }
        catch (MembershipException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity<?> updateMembershipStatus(@Valid @RequestBody MembershipStatusUpdateEntity membershipStatusUpdateEntity){
        if(membershipStatusUpdateEntity.getStatus() == MembershipStatus.EXPIRED){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cant Update Status To Expired");
        }
        MembershipModel membershipModel = membershipAdapter.updateMembershipStatus(membershipStatusUpdateEntity.getUserId(),membershipStatusUpdateEntity.getStatus());
        if(membershipModel == null){
            throw new MembershipException("Membership of this user cant be found.");
        }
        return ResponseEntity.ok(membershipModel);
    }
}
