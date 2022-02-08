package com.bank.hdfc.service;

import com.bank.hdfc.entity.JourneyStatus;

import java.util.List;

public interface JourneyStatusService {

    public abstract JourneyStatus createJourney(JourneyStatus journeyStatus) throws Exception;

    public abstract JourneyStatus getJourneyDetail(String referenceNumber);

    public abstract List<JourneyStatus> getAllJourneyDetail();

}