package com.stan.stancommons.service;

import com.systemspecs.remita.vending.vendingcommon.dto.request.TransactionDTO;
import com.systemspecs.remita.vending.vendingcommon.dto.request.TransactionDataRequest;
import com.systemspecs.remita.vending.vendingcommon.dto.request.TransactionRequest;
import com.systemspecs.remita.vending.vendingcommon.dto.response.TransactionResponse;
import com.systemspecs.remita.vending.vendingcommon.dto.response.VerifyAccountResponse;
import com.systemspecs.remita.vending.vendingcommon.entity.*;
import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import com.systemspecs.remita.vending.vendingcommon.repository.ExtraTokenRepository;
import com.systemspecs.remita.vending.vendingcommon.repository.PostpaidSettlementRepository;
import com.systemspecs.remita.vending.vendingcommon.repository.TransactionDataRepository;
import com.systemspecs.remita.vending.vendingcommon.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.GenericTypeResolver;

import java.util.Date;
import java.util.Optional;

@Slf4j
public abstract class AbstractVendingService implements VendingService, VendingCommonService, InitializingBean, BeanNameAware {

    private String beanName;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionDataRepository transactionDataRepository;

    @Autowired
    private ExtraTokenRepository extraTokenRepository;

    @Autowired
    private PostpaidSettlementRepository postpaidSettlementRepository;


    private Class type;

    public abstract String getProcessorId();


    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Registering processor for {}", beanName);
        VendingServiceRegisteredEvent event = new VendingServiceRegisteredEvent(beanName);
        event.setProcessorId(this.getProcessorId());
        String aliasBeanName = this.getProcessorId().toLowerCase() + "ProcessorService";
        ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
        beanFactory.registerAlias(beanName, aliasBeanName);
        log.info("Done registering Vending Product Processor with name " + beanName);
    }

    public AbstractVendingService() {
        this.type = GenericTypeResolver.resolveTypeArgument(getClass(), AbstractVendingService.class);
    }

    public VerifyAccountResponse verifyAccount(String uniqueIdentifier, String productCode) {
        return null;
    }

    public Transaction createTransactionV2(TransactionResponse response, TransactionRequest request, Product product, TransactionDTO transactionDTO, String processorId, String internalReference) {
        log.info("Inside createTransaction with response " + response);
        Transaction transaction = new Transaction();
        transaction.setCreatedAt(new Date());
        transaction.setAmount(request.getAmount());
        transaction.setCategoryCode(product.categoryCode());
        transaction.setProcessorId(processorId);
        transaction.setInternalReference(internalReference);
        transaction.setExternalReference(response.getExternalReference());
        transaction.setProductCode(product.getCode());
        transaction.setUserId(String.valueOf(transactionDTO.getUserId()));
        transaction.setStatus(resolveStatus(response));
        transaction.setResponseMessage(response.getMessage());
        transaction.setResponseCode(response.getCode());
        transaction.setProcessorResponseCode(response.getProcessorResponseCode());
        transaction.setProcessorResponseMessage(response.getProcessorResponseMessage());
        transaction.setRetrialTimeInSec(getRetrialInterval(response));
        transaction.setClientReference(request.getClientReference());
        transaction.setProductType(response.getProductType());

        if (request.isBulkVending()) {
            transaction.setBulkVending(true);
        }
        transaction.setToken(response.getToken());
        transaction.setUnits(response.getUnits());
        transaction.setReceiptNumber(response.getReceiptNumber());
        transaction.setTax(response.getTax());
        transaction.setTariff(response.getTariff());
        transaction.setProcessedWithFallback(transactionDTO.getProcessedWithFallback());
        transaction.setFallbackProcessorId(transactionDTO.getFallbackProcessorId());
        transaction.setFallbackResponseMessage(transactionDTO.getFallbackResponseMessage());
        transaction.setFallbackResponseCode(transactionDTO.getFallbackResponseCode());
        if (request.isReVend()) {
            transaction.setReVend(true);
        }
        //set Merchant info
        transaction.setMerchantName(transactionDTO.getMerchantName());
        transaction.setMerchantOrgId(transactionDTO.getMerchantOrgId());
        transaction.setAccountNumber(transactionDTO.getAccountNumber());
        transaction.setIpAddress(transactionDTO.getIpAddress());
        transaction.setBankCode(transactionDTO.getBankCode());
        transaction.setSubscriptionType(transactionDTO.getSubscriptionType());
        transaction.setTenantId(transactionDTO.getTenantId());
        transaction.setDiscountedAmount(transactionDTO.getDiscountedAmount());
        transaction.setCommission(transactionDTO.getCommission());
        transaction.setCaller(transactionDTO.getCaller());
        transaction.setServiceName(transactionDTO.getServiceName());
        transaction.setService(transactionDTO.getService());
        transaction.setOrgId(transactionDTO.getOrgId());
        transaction.setFundingType(transactionDTO.getFundingType());
        transaction.setSubmittedForReversals(request.isSubmittedForReversals());
        transaction.setPlatformAmount(transactionDTO.getPlatformAmount());
        transaction.setDistributorAmount(transactionDTO.getDistributorAmount());

        transaction.setPlatformCommission(transactionDTO.getPlatformCommission());
        transaction.setMerchantPercentageCommission(transactionDTO.getMerchantPercentageCommission());
        transaction.setMerchantMinAmountCap(transactionDTO.getMerchantMinAmount());
        transaction.setMerchantMaxAmountCap(transactionDTO.getMerchantMaxAmount());
        transaction.setPlatformPercentageCommission(transactionDTO.getPlatformPercentageCommission());
        transaction.setPlatformMinAmountCap(transactionDTO.getPlatformMinAmount());
        transaction.setPlatformMaxAmountCap(transactionDTO.getPlatformMaxAmount());

        transaction = transactionRepository.save(transaction);

        transaction.setTransactionData(createMetadata(transaction, request.getData()));
        transaction.setExtraToken(createExtraToken(transaction, response));

        return transaction;

    }


    public Transaction createTransaction(TransactionResponse response, TransactionRequest request, Product product, TransactionDTO transactionDTO) {
        log.info("Inside createTransaction");
        Transaction transaction = new Transaction();
        transaction.setCreatedAt(new Date());
        transaction.setAmount(request.getAmount());
        transaction.setCategoryCode(product.categoryCode());
        transaction.setProcessorId(getProcessorId());
        transaction.setInternalReference(transactionDTO.getInternalReference());
        transaction.setExternalReference(response.getExternalReference());
        transaction.setProductCode(product.getCode());
        transaction.setUserId(String.valueOf(transactionDTO.getUserId()));
        transaction.setStatus(resolveStatus(response));
        transaction.setResponseMessage(response.getMessage());
        transaction.setResponseCode(response.getCode());
        transaction.setProcessorResponseCode(response.getProcessorResponseCode());
        transaction.setProcessorResponseMessage(response.getProcessorResponseMessage());
        transaction.setRetrialTimeInSec(getRetrialInterval(response));
        transaction.setClientReference(request.getClientReference());
        transaction.setProductType(response.getProductType());

        if (request.isBulkVending()) {
            transaction.setBulkVending(true);
        }
        transaction.setToken(response.getToken());
        transaction.setUnits(response.getUnits());
        transaction.setReceiptNumber(response.getReceiptNumber());
        transaction.setTax(response.getTax());
        transaction.setTariff(response.getTariff());
        transaction.setProcessedWithFallback(transactionDTO.getProcessedWithFallback());
        transaction.setFallbackProcessorId(transactionDTO.getFallbackProcessorId());
        transaction.setFallbackResponseMessage(transactionDTO.getFallbackResponseMessage());
        transaction.setFallbackResponseCode(transactionDTO.getFallbackResponseCode());
        if (request.isReVend()) {
            transaction.setReVend(true);
        }
        //set Merchant info
        transaction.setMerchantName(transactionDTO.getMerchantName());
        transaction.setMerchantOrgId(transactionDTO.getMerchantOrgId());
        transaction.setAccountNumber(transactionDTO.getAccountNumber());
        transaction.setIpAddress(transactionDTO.getIpAddress());
        transaction.setBankCode(transactionDTO.getBankCode());
        transaction.setSubscriptionType(transactionDTO.getSubscriptionType());
        transaction.setTenantId(transactionDTO.getTenantId());
        transaction.setDiscountedAmount(transactionDTO.getDiscountedAmount());
        transaction.setCommission(transactionDTO.getCommission());
        transaction.setCaller(transactionDTO.getCaller());
        transaction.setServiceName(transactionDTO.getServiceName());
        transaction.setService(transactionDTO.getService());
        transaction.setOrgId(transactionDTO.getOrgId());
        transaction.setFundingType(transactionDTO.getFundingType());
        transaction.setSubmittedForReversals(request.isSubmittedForReversals());
        transaction.setPlatformAmount(transactionDTO.getPlatformAmount());
        transaction.setDistributorAmount(transactionDTO.getDistributorAmount());

        transaction.setPlatformCommission(transactionDTO.getPlatformCommission());
        transaction.setMerchantPercentageCommission(transactionDTO.getMerchantPercentageCommission());
        transaction.setMerchantMinAmountCap(transactionDTO.getMerchantMinAmount());
        transaction.setMerchantMaxAmountCap(transactionDTO.getMerchantMaxAmount());
        transaction.setPlatformPercentageCommission(transactionDTO.getPlatformPercentageCommission());
        transaction.setPlatformMinAmountCap(transactionDTO.getPlatformMinAmount());
        transaction.setPlatformMaxAmountCap(transactionDTO.getPlatformMaxAmount());

        transaction = transactionRepository.save(transaction);

        transaction.setTransactionData(createMetadata(transaction, request.getData()));
        transaction.setExtraToken(createExtraToken(transaction, response));

        return transaction;

    }

    private ExtraToken createExtraToken(Transaction transaction, TransactionResponse response) {
        ExtraToken extraToken = new ExtraToken();
        if (response.getExtraData() == null) {
            return null;
        } else {
            extraToken.setBsstTokenValue(response.getExtraData().getBsstTokenValue());
            extraToken.setKct1(response.getExtraData().getKct1());
            extraToken.setStandardTokenValue(response.getExtraData().getStandardTokenValue());
            extraToken.setKct2(response.getExtraData().getKct2());
            extraToken.setTransaction(transaction);
            extraToken.setBsstTokenUnits(response.getExtraData().getBsstTokenUnits());
            extraToken.setStandardTokenUnits(response.getExtraData().getStandardTokenUnits());
            extraToken.setPin(response.getExtraData().getPin());
            extraToken.setAccountBalance(response.getExtraData().getAccountBalance());
            extraToken.setMeterNumber(response.getExtraData().getMeterNumber());
            extraToken.setCustomerName(response.getExtraData().getCustomerName());
            extraToken.setMapToken1(response.getExtraData().getMapToken1());
            extraToken.setMapToken2(response.getExtraData().getMapToken2());
            extraToken.setMapUnits(response.getExtraData().getMapUnits());
            extraToken.setAmountPaid(response.getExtraData().getAmountPaid());
            extraToken.setTariffClass(response.getExtraData().getTariffClass());
            extraToken.setTariffRate(response.getExtraData().getTariffRate());
            extraToken.setCostOfUnit(response.getExtraData().getCostOfUnit());
            extraToken.setAmountForDebt(response.getExtraData().getAmountForDebt());
            extraToken.setUnitsType(response.getExtraData().getUnitsType());
            extraToken.setMessage(response.getExtraData().getMessage() != null ? response.getExtraData().getMessage() : response.getMessage());
            extraToken.setAddress(response.getExtraData().getAddress());
            extraToken.setVat(response.getExtraData().getVat());
            extraToken.setReceiptNumber(response.getExtraData().getReceiptNumber());
            extraToken.setMeterType(response.getExtraData().getMeterType());
            extraToken.setAccountType(response.getExtraData().getAccountType());
            extraToken.setUnitsPurchased(response.getExtraData().getUnitsPurchased());
            extraToken.setMinVendAmount(response.getExtraData().getMinVendAmount());
            extraToken.setMaxVendAmount(response.getExtraData().getMaxVendAmount());
            extraToken.setRemainingDebt(response.getExtraData().getRemainingDebt());
            extraToken.setReplacementCost(response.getExtraData().getReplacementCost());
            extraToken.setOutstandingDebt(response.getExtraData().getOutstandingDebt());
            extraToken.setAdministrativeCharge(response.getExtraData().getAdministrativeCharge());
            extraToken.setFixedCharge(response.getExtraData().getFixedCharge());
            extraToken.setLossOfRevenue(response.getExtraData().getLossOfRevenue());
            extraToken.setPenalty(response.getExtraData().getPenalty());
            extraToken.setMeterServiceCharge(response.getExtraData().getMeterServiceCharge());
            extraToken.setMeterCost(response.getExtraData().getMeterCost());

            extraToken.setApplicationFee(response.getExtraData().getApplicationFee());
            extraToken.setReadingText(response.getExtraData().getReadingText());
            extraToken.setCBTRegistrationCharge(response.getExtraData().getCBTRegistrationCharge());
            extraToken.setCBTExaminationCharge(response.getExtraData().getCBTExaminationCharge());
            extraToken.setOptionalMock(response.getExtraData().getOptionalMock());

            extraToken.setStrisBrilliant(response.getExtraData().getStrisBrilliant());

            extraToken.setMapAmount(response.getExtraData().getMapAmount());
            extraToken.setRefundAmount(response.getExtraData().getRefundAmount());
            extraToken.setRefundUnits(response.getExtraData().getRefundUnits());
            extraToken.setAccount(response.getExtraData().getAccount());

            extraToken.setDistrictCode(response.getExtraData().getDistrictCode());
            extraToken.setDistrictName(response.getExtraData().getDistrictName());
            extraToken.setPaymentType(response.getExtraData().getPaymentType());
            extraToken.setBillType(response.getExtraData().getBillType());
            extraToken.setBillTypeDesc(response.getExtraData().getBillTypeDesc());
            extraToken.setPaymentAmount(response.getExtraData().getPaymentAmount());

            return extraTokenRepository.save(extraToken);
        }
    }

    private void updateExtraToken(Transaction transaction, TransactionResponse response) {
        try {
            if (response.getExtraData() != null && transaction.getExtraToken() != null) {
                ExtraToken extraToken = transaction.getExtraToken();
                extraToken.setBsstTokenValue(response.getExtraData().getBsstTokenValue());
                extraToken.setKct1(response.getExtraData().getKct1());
                extraToken.setStandardTokenValue(response.getExtraData().getStandardTokenValue());
                extraToken.setKct2(response.getExtraData().getKct2());
                extraToken.setTransaction(transaction);
                extraToken.setBsstTokenUnits(response.getExtraData().getBsstTokenUnits());
                extraToken.setStandardTokenUnits(response.getExtraData().getStandardTokenUnits());
                extraToken.setPin(response.getExtraData().getPin());
                extraToken.setAccountBalance(response.getExtraData().getAccountBalance());
                extraToken.setMeterNumber(response.getExtraData().getMeterNumber());
                extraToken.setCustomerName(response.getExtraData().getCustomerName());
                extraToken.setMapToken1(response.getExtraData().getMapToken1());
                extraToken.setMapToken2(response.getExtraData().getMapToken2());
                extraToken.setMapUnits(response.getExtraData().getMapUnits());
                extraToken.setAmountPaid(response.getExtraData().getAmountPaid());
                extraToken.setTariffClass(response.getExtraData().getTariffClass());
                extraToken.setTariffRate(response.getExtraData().getTariffRate());
                extraToken.setCostOfUnit(response.getExtraData().getCostOfUnit());
                extraToken.setAmountForDebt(response.getExtraData().getAmountForDebt());
                extraToken.setUnitsType(response.getExtraData().getUnitsType());
                extraToken.setMessage(response.getExtraData().getMessage() != null ? response.getExtraData().getMessage() : response.getMessage());
                extraToken.setAddress(response.getExtraData().getAddress());
                extraToken.setVat(response.getExtraData().getVat());
                extraToken.setReceiptNumber(response.getExtraData().getReceiptNumber());
                extraToken.setMeterType(response.getExtraData().getMeterType());
                extraToken.setAccountType(response.getExtraData().getAccountType());
                extraToken.setUnitsPurchased(response.getExtraData().getUnitsPurchased());
                extraToken.setMinVendAmount(response.getExtraData().getMinVendAmount());
                extraToken.setMaxVendAmount(response.getExtraData().getMaxVendAmount());
                extraToken.setRemainingDebt(response.getExtraData().getRemainingDebt());
                extraToken.setReplacementCost(response.getExtraData().getReplacementCost());
                extraToken.setOutstandingDebt(response.getExtraData().getOutstandingDebt());
                extraToken.setAdministrativeCharge(response.getExtraData().getAdministrativeCharge());
                extraToken.setFixedCharge(response.getExtraData().getFixedCharge());
                extraToken.setLossOfRevenue(response.getExtraData().getLossOfRevenue());
                extraToken.setPenalty(response.getExtraData().getPenalty());
                extraToken.setMeterServiceCharge(response.getExtraData().getMeterServiceCharge());
                extraToken.setMeterCost(response.getExtraData().getMeterCost());

                extraToken.setApplicationFee(response.getExtraData().getApplicationFee());
                extraToken.setReadingText(response.getExtraData().getReadingText());
                extraToken.setCBTRegistrationCharge(response.getExtraData().getCBTRegistrationCharge());
                extraToken.setCBTExaminationCharge(response.getExtraData().getCBTExaminationCharge());
                extraToken.setOptionalMock(response.getExtraData().getOptionalMock());

                extraToken.setStrisBrilliant(response.getExtraData().getStrisBrilliant());

                extraToken.setMapAmount(response.getExtraData().getMapAmount());
                extraToken.setRefundAmount(response.getExtraData().getRefundAmount());
                extraToken.setRefundUnits(response.getExtraData().getRefundUnits());
                extraToken.setAccount(response.getExtraData().getAccount());

                extraToken.setDistrictCode(response.getExtraData().getDistrictCode());
                extraToken.setDistrictName(response.getExtraData().getDistrictName());
                extraToken.setPaymentType(response.getExtraData().getPaymentType());
                extraToken.setBillType(response.getExtraData().getBillType());
                extraToken.setBillTypeDesc(response.getExtraData().getBillTypeDesc());
                extraToken.setPaymentAmount(response.getExtraData().getPaymentAmount());

                extraTokenRepository.save(extraToken);
                log.info("Extra token updated for transaction {}", transaction.getClientReference());
            }

        } catch (Exception e) {
            log.error("Error updating extra token for transaction {}", transaction.getClientReference(), e);
        }

    }


    public Transaction updateTransaction(Transaction transaction, TransactionResponse response) {
        log.info("in updateTransaction with response " + response);
        transaction.setUpdatedAt(new Date());
//        transaction.setExternalReference(response.getExternalReference());
        transaction.setStatus(resolveStatus(response));
        transaction.setResponseCode(response.getCode());
        transaction.setResponseMessage(response.getMessage());
        transaction.setProcessorResponseMessage(response.getProcessorResponseMessage());
        transaction.setToken(response.getToken());
        transaction.setUnits(response.getUnits());
        transaction.setReceiptNumber(response.getReceiptNumber());
        transaction.setTax(response.getTax());
        transaction.setTariff(response.getTariff());
        transaction = transactionRepository.save(transaction);
        log.info("Transaction updated...{}", transaction);
        updateExtraToken(transaction, response);
        return transaction;

    }

    public Transaction updateTransactionForReVend(Transaction transaction, TransactionResponse response, String processorId, TransactionRequest request) {
        transaction.setUpdatedAt(new Date());
        transaction.setExternalReference(response.getExternalReference());
        transaction.setStatus(resolveStatus(response));
        transaction.setResponseCode(response.getCode());
        transaction.setResponseMessage(response.getMessage());
        transaction.setProcessorResponseMessage(response.getProcessorResponseMessage());
        transaction.setToken(response.getToken());
        transaction.setUnits(response.getUnits());
        transaction.setReceiptNumber(response.getReceiptNumber());
        transaction.setTax(response.getTax());
        transaction.setReVend(true);
        transaction.setTariff(response.getTariff());
        transaction.setProcessorId(processorId);
        transaction.setProcessorResponseCode(response.getProcessorResponseCode());
        transaction = transactionRepository.save(transaction);


        Optional<TransactionData> optionalTransaction = transactionDataRepository.findByTransaction(transaction);
        TransactionData transactionData = optionalTransaction.get();
        transactionData.setAccountNumber(request.getData().getAccountNumber());
        transactionData.setPhoneNumber(request.getData().getPhoneNumber());
        transactionDataRepository.save(transactionData);
        transaction.setTransactionData(transactionData);

        return transaction;

    }

    public PostpaidSettlement createPostpaidTransaction(TransactionRequest request, TransactionDTO dto, Product product) {
        PostpaidSettlement postpaidSettlement = new PostpaidSettlement();
        postpaidSettlement.setCreatedAt(new Date());
        postpaidSettlement.setMerchantBankCode(dto.getBankCode());
        postpaidSettlement.setMerchantAccountNumber(dto.getAccountNumber());
        postpaidSettlement.setAmount(dto.getDiscountedAmount());
        postpaidSettlement.setPostPaymentRef(dto.getInternalReference());
        postpaidSettlement.setMerchantOrgId(dto.getMerchantOrgId());
        postpaidSettlement.setIpAddress(dto.getIpAddress());
        postpaidSettlement.setTenantId(dto.getTenantId());
        postpaidSettlement.setProcessorId(getProcessorId());
        postpaidSettlement.setProductCode(product.getCode());
        postpaidSettlement.setClientReference(request.getClientReference());

        postpaidSettlementRepository.save(postpaidSettlement);
        return postpaidSettlement;

    }

    public PostpaidSettlement createPostpaidTransactionFromRevend(Transaction transaction) {
        PostpaidSettlement postpaidSettlement = new PostpaidSettlement();
        postpaidSettlement.setCreatedAt(new Date());
        postpaidSettlement.setMerchantBankCode(transaction.getBankCode());
        postpaidSettlement.setMerchantAccountNumber(transaction.getAccountNumber());
        postpaidSettlement.setAmount(transaction.getAmount());
        postpaidSettlement.setPostPaymentRef(transaction.getInternalReference());
        postpaidSettlement.setMerchantOrgId(transaction.getMerchantOrgId());
        postpaidSettlement.setIpAddress(transaction.getIpAddress());
        postpaidSettlement.setTenantId(transaction.getTenantId());
        postpaidSettlement.setProcessorId(getProcessorId());
        postpaidSettlement.setProductCode(transaction.getProductCode());

        postpaidSettlementRepository.save(postpaidSettlement);
        return postpaidSettlement;

    }

    private TransactionData createMetadata(Transaction transaction, TransactionDataRequest data) {
        TransactionData transactionData = new TransactionData();
        transactionData.setAccountNumber(data.getAccountNumber());
        transactionData.setPhoneNumber(data.getPhoneNumber());
        transaction.setCreatedAt(new Date());
        transactionData.setTransaction(transaction);
        return transactionDataRepository.save(transactionData);
    }


    private int getRetrialInterval(TransactionResponse response) {
        if (response.getStatus() != TransactionStatus.PENDING) {
            return 0;
        }

        return response.getRetrialTimeInSec() == 0 ? 60 : response.getRetrialTimeInSec();
    }


    private TransactionStatus resolveStatus(TransactionResponse response) {
        TransactionStatus status = response.getStatus();

        if (status == TransactionStatus.COMPLETED || status == TransactionStatus.CONFIRMED ||
                status == TransactionStatus.SUCCESS || status == TransactionStatus.PENDING ||
                status == TransactionStatus.TRANSACTION_FAILED) {
            return status;
        } else {
            return TransactionStatus.TRANSACTION_FAILED;
        }
    }

}
