package com.stan.stancommons.eventsticketing.service;

import com.systemspecs.remita.vending.vendingcommon.dto.response.TransactionResponse;
import com.systemspecs.remita.vending.vendingcommon.entity.Product;
import com.systemspecs.remita.vending.vendingcommon.entity.Transaction;
import com.systemspecs.remita.vending.vendingcommon.enums.SystemProduct;
import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import com.systemspecs.remita.vending.vendingcommon.eventsticketing.dto.request.CompleteEventBookingRequest;
import com.systemspecs.remita.vending.vendingcommon.eventsticketing.dto.request.EventDto;
import com.systemspecs.remita.vending.vendingcommon.eventsticketing.dto.response.CompleteEventBookingResult;
import com.systemspecs.remita.vending.vendingcommon.repository.TransactionRepository;
import com.systemspecs.remita.vending.vendingcommon.service.VendingCommonService;
import com.systemspecs.remita.vending.vendingcommon.service.VendingServiceRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.GenericTypeResolver;

import java.util.Date;

@Slf4j
public abstract class EventProductAbstractVendingService implements EventVendingService, VendingCommonService, InitializingBean, BeanNameAware {
    private String beanName;


    @Autowired
    private TransactionRepository transactionRepository;


    @Autowired
    private ApplicationContext applicationContext;

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

    public EventProductAbstractVendingService() {
        this.type = GenericTypeResolver.resolveTypeArgument(getClass(), EventProductAbstractVendingService.class);
    }


    public Transaction createEventTransaction(CompleteEventBookingResult response, EventDto eventDto, CompleteEventBookingRequest request, Product product){
        Transaction eventTransaction = new Transaction();
        log.info("CompleteEventBookingResult inside createEventTransaction is {} with EventtDto ...{}",response, eventDto);
        eventTransaction.setProductCode(product.getCode());
        eventTransaction.setStatus(response.getStatus());
        eventTransaction.setResponseCode(response.getCode());
        eventTransaction.setResponseMessage(response.getMessage());
        eventTransaction.setProcessorResponseCode(response.getProcessorResponseCode());
        eventTransaction.setProcessorResponseMessage(response.getProcessorResponseMessage());
        eventTransaction.setProcessorId(this.getProcessorId());
        eventTransaction.setInternalReference(eventDto.getInternalReference());
        eventTransaction.setClientReference(request.getPaymentIdentifier());
        eventTransaction.setCategoryCode(product.categoryCode());
        eventTransaction.setAmount(response.getAmount());
        eventTransaction.setReceiptNumber(response.getListingId());
        eventTransaction.setExternalReference(response.getExternalReference());
        eventTransaction.setCreatedAt(new Date());
        eventTransaction.setUserId(eventDto.getUserId());
        eventTransaction.setMerchantName(eventDto.getMerchantName());
        eventTransaction.setMerchantOrgId(eventDto.getMerchantOrgId());
        eventTransaction.setAccountNumber(eventDto.getAccountNumber());
        eventTransaction.setCommission(eventDto.getCommission());
        eventTransaction.setIpAddress(eventDto.getIpAddress());
        eventTransaction.setTenantId(eventDto.getTenantId());
        eventTransaction.setSubscriptionType(eventDto.getSubscriptionType());
        eventTransaction.setBankCode(eventDto.getBankCode());
        eventTransaction.setDiscountedAmount(eventDto.getDiscountedAmount());
        eventTransaction.setCaller(eventDto.getCaller());
        eventTransaction.setServiceName(eventDto.getServiceName());
        eventTransaction.setFundingType(eventDto.getFundingType());
        eventTransaction.setService(eventDto.getService());
        eventTransaction.setSystemProductType(SystemProduct.EVENT);

        log.info("Transaction created inside eventTransaction...{}",eventTransaction);

        return transactionRepository.save(eventTransaction);


    }


    public Transaction updateTransaction(Transaction transaction, TransactionResponse response) {
        transaction.setUpdatedAt(new Date());
       if(TransactionStatus.SUCCESS.getCode().equals(response.getCode())){
        transaction.setStatus(TransactionStatus.SUCCESS);
        transaction.setResponseCode(response.getCode());
        transaction.setProcessorResponseMessage(response.getProcessorResponseMessage());
       }else{
           transaction.setStatus(TransactionStatus.TRANSACTION_FAILED);
       }
        transaction.setResponseMessage(response.getMessage());

        transaction = transactionRepository.save(transaction);
        return transaction;

    }
}
