package org.VarunSeemar.digital_library.entities.output;

import jakarta.persistence.*;
import lombok.*;
import org.VarunSeemar.digital_library.enums.MembershipPlan;
import org.VarunSeemar.digital_library.enums.MembershipStatus;

import java.time.Instant;

@Entity
@Table(name = "membership")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipOutputEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserOutputEntity userOutputEntity;

    @Column(name = "start_date",nullable = false)
    private Instant startDate;

    @Column(name = "end_date",nullable = false)
    private Instant endDate;

    @Column(name = "membership_status",nullable = false)
    private MembershipStatus status;

    @Column(name = "membership_plan",nullable = false)
    private MembershipPlan plan;
}
