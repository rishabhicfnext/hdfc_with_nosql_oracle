package com.bank.hdfc.service.impl;

import com.bank.hdfc.dbservice.DBFactory;
import com.bank.hdfc.dbservice.DBUtil;
import com.bank.hdfc.entity.JourneyStatus;
import com.bank.hdfc.service.JourneyStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class JourneyStatusServiceImpl implements JourneyStatusService {

    //public Logger log = LoggerFactory.getLogger(JourneyStatusServiceImpl.class);

    @Autowired
    DBFactory dbFactory;

    @Override
    public JourneyStatus createJourney(JourneyStatus journeyStatus) throws Exception {

        log.info("Inside journeystatusimpl class !!");
        JourneyStatus journeyStatus1 = DBUtil.setJourneyStatus(journeyStatus, dbFactory);
        return journeyStatus1;
    }

    @Override
    public JourneyStatus getJourneyDetail(String referenceNumber) {
        JourneyStatus journeyStatus1 = DBUtil.getJourneyStatus(referenceNumber, dbFactory);
        return journeyStatus1;
    }

    @Override
    public List<JourneyStatus> getAllJourneyDetail() {
        List<JourneyStatus> journeyStatus2 = DBUtil.getAllJourneyStatus(dbFactory);
        return journeyStatus2;
    }

}
