package com.alfa.alfabattle.bargaining.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Media to store information about waiting time in queue.
 */
@Entity
@Table(name = "queue_log")
public class QueueWait {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "branches_id")
    private Long branchId;

    @Column(name = "data")
    private Date data;

    @Column(name = "start_time_of_wait")
    private Date start;

    @Column(name = "end_time_of_wait")
    private Date end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
