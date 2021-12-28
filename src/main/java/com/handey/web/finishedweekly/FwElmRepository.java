package com.handey.web.finishedweekly;

import java.time.LocalDate;
import java.util.Optional;

public interface FwElmRepository {
    FwElm save(FwElm fwElm);
    Optional<FwElm> findById(Long id);
    Optional<FwElm> findByFwBoxIdAndWeeklyElmId(Long fwBoxId, Long weeklyElmId);
    void deleteById(Long id);
}
