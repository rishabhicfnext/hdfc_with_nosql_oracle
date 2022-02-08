package com.bank.hdfc.controller;

import com.bank.hdfc.dbservice.DBFactory;
import com.bank.hdfc.entity.JourneyStatus;
import com.bank.hdfc.service.JourneyStatusService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class JourneyStatusController {

    //public Logger log = LoggerFactory.getLogger(JourneyStatusController.class);

    @Autowired
    JourneyStatusService journeyStatusService;

    @Autowired
    private KafkaTemplate<String, JourneyStatus> kafkaTemplate;

    private static final String TOPIC = "rishabh";

    private ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value, String topic) {
        List<Header> recordHeaders = List.of(new RecordHeader("event-source", "scanner".getBytes()));
        return new ProducerRecord<>(topic, null, key, value, recordHeaders);
    }

    @PostMapping(value = "/v1/bookjourney")
    public ResponseEntity<JourneyStatus> bookJourney(@RequestBody JourneyStatus journeyStatus) throws Exception {
        log.info("create journey method called !!");
        JourneyStatus journeyStatus1 = journeyStatusService.createJourney(journeyStatus);
        kafkaTemplate.send(TOPIC, new JourneyStatus(journeyStatus1.getJourneyId(), journeyStatus1.getJourneyName(), journeyStatus1.getReferenceNumber(), journeyStatus1.getStatus(), journeyStatus1.getMobileNumber(), journeyStatus1.getCc4Digits(), journeyStatus1.getDateOfBirth(), journeyStatus1.getAadhar(), journeyStatus1.getPan(), journeyStatus1.getLmsReferenceNumber()));//        ObjectMapper objectMapper = null;

//        String obj = objectMapper.writeValueAsString(journeyStatus1);
//        //String obj = objectMapper.toString();
//        ListenableFuture listenableFuture ;
//        ProducerRecord<Integer, String> producerRecord = buildProducerRecord(null, obj, TOPIC);
//        listenableFuture = kafkaTemplate.send((Message<?>) producerRecord);
//        kafkaTemplate.send(TOPIC, journeyStatus);

        if (journeyStatus1 != null) {
            log.info("journey created successfully");
            return new ResponseEntity<JourneyStatus>(journeyStatus1, HttpStatus.CREATED);
        } else {
            log.error("journey not created");
            return new ResponseEntity<JourneyStatus>(journeyStatus1, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/v1/getjourney/{referenceNumber}")
    public ResponseEntity<Object> getJourney(@PathVariable String referenceNumber) throws Exception {
        JourneyStatus allJourney = journeyStatusService.getJourneyDetail(referenceNumber);
        log.info("getting journey controller called");
        if (allJourney != null) {
            log.info("Getting list of journey detail");
            kafkaTemplate.send(TOPIC, allJourney);
            return new ResponseEntity<>(allJourney, HttpStatus.OK);
        } else {
            log.error("There is no journey detail found with this referencenumber : " + referenceNumber);
            return new ResponseEntity<>("There is no journey details found with this reference number : " + referenceNumber, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/v1/getalljourney")
    public ResponseEntity<Object> getAllJourney() throws Exception {
        List<JourneyStatus> allJourney = journeyStatusService.getAllJourneyDetail();
        log.info("get all journey controller called");
//        Object o[] = allJourney.toArray();
//
//        JourneyStatus journeyStatus[] = new JourneyStatus[o.length];
//        for (int i = 0; i < o.length; i++) {
//            journeyStatus[i] = new JourneyStatus();
//        }
        if (allJourney != null) {
            log.info("Getting list of all journey detail");
            //kafkaTemplate.send(TOPIC,journeyStatus);
            return new ResponseEntity<>(allJourney, HttpStatus.OK);
        } else {
            log.error("list of journey detail is not found");
            return new ResponseEntity<>("There is no list of journey detail : " + allJourney, HttpStatus.BAD_REQUEST);
        }
    }

}