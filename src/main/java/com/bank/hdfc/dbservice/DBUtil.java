package com.bank.hdfc.dbservice;

import com.bank.hdfc.entity.JourneyStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import oracle.nosql.driver.NoSQLHandle;
import oracle.nosql.driver.ReadThrottlingException;
import oracle.nosql.driver.ops.*;
import oracle.nosql.driver.values.MapValue;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DBUtil {

    // public static Logger log = LoggerFactory.getLogger(DBUtil.class);

    private DBUtil() {
    }

    public static JourneyStatus setJourneyStatus(final JourneyStatus journeyStatus, final DBFactory dbFactory) {
        log.info("inside DBUtil set journey method !!");

        NoSQLHandle handle = null;
        try {
            if (journeyStatus != null && dbFactory != null) {
                handle = dbFactory.getHandle();
                PutRequest putRequest = new PutRequest();
                putRequest.setTableName("JourneyStatus");

                final MapValue mapValue = new MapValue();

                mapValue.put("JourneyName", journeyStatus.getJourneyName());
                mapValue.put("journeyId", journeyStatus.getJourneyId());
                mapValue.put("referenceNumber", journeyStatus.getReferenceNumber());
                mapValue.put("status", journeyStatus.getStatus());
                mapValue.put("mobileNumber", journeyStatus.getMobileNumber());
                mapValue.put("CC4Digits", journeyStatus.getCc4Digits());
                mapValue.put("DateOfBirth", journeyStatus.getDateOfBirth());
                mapValue.put("Aadhar", journeyStatus.getAadhar());
                mapValue.put("PAN", journeyStatus.getPan());
                mapValue.put("lmsReferenceNumber", journeyStatus.getLmsReferenceNumber());

                putRequest.setValue(mapValue);
                handle.put(putRequest);
                log.info("journey created");
            }
        } catch (Exception exception) {
            log.info("failed to create journey");
        }
        return journeyStatus;

    }

    public static JourneyStatus getJourneyStatus(String referenceNumber1, final DBFactory dbFactory) {
        log.info("inside DBUtil get journey method !!");
        ObjectMapper objectMapper = new ObjectMapper();
        NoSQLHandle handle = null;
        JourneyStatus list = null;//= new ArrayList<JourneyStatus>();
        try {
            if (dbFactory != null) {
                handle = dbFactory.getHandle();

                final MapValue key = new MapValue().put("referenceNumber", referenceNumber1);
                GetRequest getRequest = new GetRequest().setKey(key).setTableName("JourneyStatus");

                GetResult getResult = handle.get(getRequest);
                final MapValue mapValue = getResult.getValue();

                if (mapValue != null) {
                    String journeyName = mapValue.getString("JourneyName");
                    String journeyId = mapValue.getString("journeyId");
                    String referenceNumber = mapValue.getString("referenceNumber");
                    String status = mapValue.getString("status");
                    String mobileNumber = mapValue.getString("mobileNumber");
                    String cc4Digits = mapValue.getString("CC4Digits");
                    String dateOfBirth = mapValue.getString("DateOfBirth");
                    String aadhar = mapValue.getString("Aadhar");
                    String pan = mapValue.getString("PAN");
                    String lmsReferenceNumber = mapValue.getString("lmsReferenceNumber");

                    list = new JourneyStatus(journeyName, journeyId, referenceNumber, status,
                            mobileNumber, cc4Digits, dateOfBirth, aadhar, pan, lmsReferenceNumber);

                    //list.add(journeyStatus);
                }
                log.info("journey detail getting");
            }
        } catch (Exception ex) {
            log.info("failed to getting journey detail");
        }
        return list;
    }

    public static List<JourneyStatus> getAllJourneyStatus(final DBFactory dbFactory) {
        log.info("inside DBUtil get journey method !!");
        ObjectMapper objectMapper = new ObjectMapper();
        NoSQLHandle handle = null;
        List<MapValue> results = new ArrayList<>();
        List<JourneyStatus> list = new ArrayList<JourneyStatus>();
        try {
            if (dbFactory != null) {
                handle = dbFactory.getHandle();

                String query = "select * from JourneyStatus";
                PrepareRequest prepareRequest = new PrepareRequest().setStatement(query);
                PrepareResult prepareResult = handle.prepare(prepareRequest);
                QueryRequest queryRequest = new QueryRequest()
                        .setPreparedStatement(prepareResult.getPreparedStatement());

                try {
                    do {
                        QueryResult res = handle.query(queryRequest);
                        int numberOfRecords = res.getResults().size();
                        if (numberOfRecords > 0) {
                            results.addAll(res.getResults());
                        }
                    } while (!queryRequest.isDone());
                } catch (ReadThrottlingException rte) {
                    log.error("ReadThrottlingException in DBHandle.runQuery():  {}", rte);
                }

                log.info("all journey detail getting");
            }
        } catch (Exception ex) {
            log.info("failed to getting all journey detail");
        }
        for (MapValue mapValue : results) {

            String journeyName = mapValue.getString("JourneyName");
            String journeyId = mapValue.getString("journeyId");
            String referenceNumber = mapValue.getString("referenceNumber");
            String status = mapValue.getString("status");
            String mobileNumber = mapValue.getString("mobileNumber");
            String cc4Digits = mapValue.getString("CC4Digits");
            String dateOfBirth = mapValue.getString("DateOfBirth");
            String aadhar = mapValue.getString("Aadhar");
            String pan = mapValue.getString("PAN");
            String lmsReferenceNumber = mapValue.getString("lmsReferenceNumber");

            JourneyStatus journeyStatus = new JourneyStatus(journeyName, journeyId, referenceNumber, status,
                    mobileNumber, cc4Digits, dateOfBirth, aadhar, pan, lmsReferenceNumber);

            list.add(journeyStatus);
        }
        return list;
    }

}