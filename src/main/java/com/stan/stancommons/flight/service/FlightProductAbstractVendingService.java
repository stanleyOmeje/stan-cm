package com.stan.stancommons.flight.service;

import com.systemspecs.remita.vending.vendingcommon.dto.response.TransactionResponse;
import com.systemspecs.remita.vending.vendingcommon.entity.Product;
import com.systemspecs.remita.vending.vendingcommon.entity.Transaction;
import com.systemspecs.remita.vending.vendingcommon.enums.SystemProduct;
import com.systemspecs.remita.vending.vendingcommon.enums.TransactionStatus;
import com.systemspecs.remita.vending.vendingcommon.flight.dto.request.BookFlightRequest;
import com.systemspecs.remita.vending.vendingcommon.flight.dto.request.CabinClassRequest;
import com.systemspecs.remita.vending.vendingcommon.flight.dto.request.FlightDto;
import com.systemspecs.remita.vending.vendingcommon.flight.dto.response.BookingResult;
import com.systemspecs.remita.vending.vendingcommon.flight.dto.response.ConfirmPriceResponse;
import com.systemspecs.remita.vending.vendingcommon.flight.entity.BookingValidation;
import com.systemspecs.remita.vending.vendingcommon.flight.enums.BookingStatus;
import com.systemspecs.remita.vending.vendingcommon.flight.repository.BookingValidationRepository;
import com.systemspecs.remita.vending.vendingcommon.flight.repository.FlightBookingRepository;
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

import javax.validation.Valid;
import java.util.Date;

@Slf4j
public abstract class FlightProductAbstractVendingService implements FlightVendingService, VendingCommonService, InitializingBean, BeanNameAware {
    private String beanName;

    @Autowired
    private FlightBookingRepository flightBookingRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookingValidationRepository bookingValidationRepository;

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

    public FlightProductAbstractVendingService() {
        this.type = GenericTypeResolver.resolveTypeArgument(getClass(), FlightProductAbstractVendingService.class);
    }

    public BookingValidation createBookingValidation(ConfirmPriceResponse response, String productCode){
        log.info("Creating booking validation for ConfirmPriceResponse..{} and productCode...{}", response, productCode);
        BookingValidation bookingValidation = new BookingValidation();
        bookingValidation.setFlightId(response.getData().getFlightId());
        bookingValidation.setProductCode(productCode);
        bookingValidation.setBookingCode(response.getBookingCode());
        bookingValidation.setNumberOfAdult(response.getData().getAdults());
        bookingValidation.setNumberOfChildren(response.getData().getChildren());
        bookingValidation.setNumberOfInfant(response.getData().getInfants());
        bookingValidation.setBookingCode(response.getBookingCode());
        bookingValidation.setAmount(response.getData().getAmount());
        bookingValidation.setStatus(BookingStatus.PRICECONFIRMATION);
        bookingValidation.setCreatedAt(new Date());

        return bookingValidationRepository.save(bookingValidation);
    }

    public Transaction createFlightTransaction(BookingResult response, FlightDto flightDto, BookFlightRequest request, Product product){
        Transaction flightTransaction = new Transaction();
        log.info("BookingResult inside createFlightTransaction is {} with FlightDto ...{}",response, flightDto);
        flightTransaction.setProductCode(product.getCode());
        flightTransaction.setStatus(response.getStatus());
        flightTransaction.setResponseCode(response.getCode());
        flightTransaction.setResponseMessage(response.getMessage());
        flightTransaction.setProcessorResponseCode(response.getProcessorResponseCode());
        flightTransaction.setProcessorResponseMessage(response.getProcessorResponseMessage());
        flightTransaction.setProcessorId(this.getProcessorId());
        flightTransaction.setInternalReference(flightDto.getInternalReference());
        flightTransaction.setClientReference(request.getPaymentIdentifier());
        flightTransaction.setCategoryCode(product.categoryCode());
        flightTransaction.setAmount(response.getAmount());
        flightTransaction.setInternalReference(response.getBookingCode());
        flightTransaction.setReceiptNumber(response.getFlightId());
        flightTransaction.setExternalReference(response.getExternalReference());
        flightTransaction.setCreatedAt(new Date());
        flightTransaction.setUserId(flightDto.getUserId());
        flightTransaction.setMerchantName(flightDto.getMerchantName());
        flightTransaction.setMerchantOrgId(flightDto.getMerchantOrgId());
        flightTransaction.setAccountNumber(flightDto.getAccountNumber());
        flightTransaction.setCommission(flightDto.getCommission());
        flightTransaction.setIpAddress(flightDto.getIpAddress());
        flightTransaction.setTenantId(flightDto.getTenantId());
        flightTransaction.setSubscriptionType(flightDto.getSubscriptionType());
        flightTransaction.setBankCode(flightDto.getBankCode());
        flightTransaction.setDiscountedAmount(flightDto.getDiscountedAmount());
        flightTransaction.setCaller(flightDto.getCaller());
        flightTransaction.setServiceName(flightDto.getServiceName());
        flightTransaction.setFundingType(flightDto.getFundingType());
        flightTransaction.setService(flightDto.getService());
        flightTransaction.setSystemProductType(SystemProduct.FLIGHT);

        log.info("Transaction created inside flightTransaction...{}",flightTransaction);

        return transactionRepository.save(flightTransaction);


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

    @Override
    public Object fetchCabin() {
        return null;
    }

    @Override
    public Object createCabin(@Valid CabinClassRequest request) {
        return null;
    }
}
