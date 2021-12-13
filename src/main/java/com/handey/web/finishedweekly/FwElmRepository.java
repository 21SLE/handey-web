package com.handey.web.finishedweekly;

import java.util.Optional;

public interface FwElmRepository {
    FwElm save(FwElm fwElm);
    Optional<FwElm> findById(Long id);
    Optional<FwElm> findByWeeklyElmId(Long weeklyElmId);
    void deleteById(Long id);
}
