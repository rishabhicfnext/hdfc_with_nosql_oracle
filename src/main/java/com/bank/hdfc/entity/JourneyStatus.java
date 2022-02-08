package com.bank.hdfc.entity;

public class JourneyStatus {

    String journeyName;
    String journeyId;
    String referenceNumber;
    String status;
    String mobileNumber;
    String cc4Digits;
    String dateOfBirth;
    String aadhar;
    String pan;
    String lmsReferenceNumber;

    public JourneyStatus() {
    }

    public JourneyStatus(String journeyName, String journeyId, String referenceNumber, String status, String mobileNumber, String cc4Digits, String dateOfBirth, String aadhar, String pan, String lmsReferenceNumber) {
        this.journeyName = journeyName;
        this.journeyId = journeyId;
        this.referenceNumber = referenceNumber;
        this.status = status;
        this.mobileNumber = mobileNumber;
        this.cc4Digits = cc4Digits;
        this.dateOfBirth = dateOfBirth;
        this.aadhar = aadhar;
        this.pan = pan;
        this.lmsReferenceNumber = lmsReferenceNumber;
    }

    public String getJourneyName() {
        return journeyName;
    }

    public void setJourneyName(String journeyName) {
        this.journeyName = journeyName;
    }

    public String getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(String journeyId) {
        this.journeyId = journeyId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCc4Digits() {
        return cc4Digits;
    }

    public void setCc4Digits(String cc4Digits) {
        this.cc4Digits = cc4Digits;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getLmsReferenceNumber() {
        return lmsReferenceNumber;
    }

    public void setLmsReferenceNumber(String lmsReferenceNumber) {
        this.lmsReferenceNumber = lmsReferenceNumber;
    }

    @Override
    public String toString() {
        return "JourneyStatus{" +
                "journeyName='" + journeyName + '\'' +
                ", journeyId='" + journeyId + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                ", status='" + status + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", cc4Digits='" + cc4Digits + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", aadhar='" + aadhar + '\'' +
                ", pan='" + pan + '\'' +
                ", lmsReferenceNumber='" + lmsReferenceNumber + '\'' +
                '}';
    }
}