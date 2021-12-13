package com.handey.web.finishedweekly;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FwBoxRepository {
    FwBox save(FwBox fwBox);
    Optional<FwBox> findById(Long id);
    Optional<FwBox> findByWeeklyBoxId(Long weeklyBoxId);
    List<FwBox> findByUserIdAndDate(Long userId, LocalDate saveDt);
    void deleteById(Long id);
    List<FwBox> findByUserId(Long userId);
}
