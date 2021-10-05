package com.handey.web.service;

import com.handey.web.afterhistory.AfterHistoryRepository;
import com.handey.web.afterhistory.AfterHistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class AfterHistoryServiceIntegrationTest {

    @Autowired
    AfterHistoryService afterHistoryService;
    @Autowired
    AfterHistoryRepository afterHistoryRepository;

    @Test
    void createAfterList() {

    }

}
