package com.stan.stancommons.movies.service;

import com.systemspecs.remita.vending.vendingcommon.entity.Product;
import com.systemspecs.remita.vending.vendingcommon.entity.Transaction;
import com.systemspecs.remita.vending.vendingcommon.enums.SystemProduct;
import com.systemspecs.remita.vending.vendingcommon.flight.repository.BookingValidationRepository;
import com.systemspecs.remita.vending.vendingcommon.flight.repository.FlightBookingRepository;
import com.systemspecs.remita.vending.vendingcommon.movies.dto.request.CompleteMovieBookingRequest;
import com.systemspecs.remita.vending.vendingcommon.movies.dto.request.MovieDto;
import com.systemspecs.remita.vending.vendingcommon.movies.dto.response.CompleteMovieBookingResult;
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
public abstract class MoviesProductAbstractVendingService implements MoviesVendingService, VendingCommonService, InitializingBean, BeanNameAware {

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

    public MoviesProductAbstractVendingService() {
        this.type = GenericTypeResolver.resolveTypeArgument(getClass(), MoviesProductAbstractVendingService.class);
    }

    public Transaction createMovieTransaction(CompleteMovieBookingResult response, MovieDto movieDto, CompleteMovieBookingRequest request, Product product){
        Transaction movieTransaction = new Transaction();
        log.info("CompleteMovieBookingResult inside createMovieTransaction is {} with MovieDto ...{}",response, movieDto);
        movieTransaction.setProductCode(product.getCode());
        movieTransaction.setStatus(response.getStatus());
        movieTransaction.setResponseCode(response.getCode());
        movieTransaction.setResponseMessage(response.getMessage());
        movieTransaction.setProcessorResponseCode(response.getProcessorResponseCode());
        movieTransaction.setProcessorResponseMessage(response.getProcessorResponseMessage());
        movieTransaction.setProcessorId(this.getProcessorId());
        movieTransaction.setInternalReference(movieDto.getInternalReference());
        movieTransaction.setClientReference(request.getPaymentIdentifier());
        movieTransaction.setCategoryCode(product.categoryCode());
        movieTransaction.setAmount(response.getAmount());
        movieTransaction.setReceiptNumber(response.getListingId());
        movieTransaction.setExternalReference(response.getExternalReference());
        movieTransaction.setCreatedAt(new Date());
        movieTransaction.setUserId(movieDto.getUserId());
        movieTransaction.setMerchantName(movieDto.getMerchantName());
        movieTransaction.setMerchantOrgId(movieDto.getMerchantOrgId());
        movieTransaction.setAccountNumber(movieDto.getAccountNumber());
        movieTransaction.setCommission(movieDto.getCommission());
        movieTransaction.setIpAddress(movieDto.getIpAddress());
        movieTransaction.setTenantId(movieDto.getTenantId());
        movieTransaction.setSubscriptionType(movieDto.getSubscriptionType());
        movieTransaction.setBankCode(movieDto.getBankCode());
        movieTransaction.setDiscountedAmount(movieDto.getDiscountedAmount());
        movieTransaction.setCaller(movieDto.getCaller());
        movieTransaction.setServiceName(movieDto.getServiceName());
        movieTransaction.setFundingType(movieDto.getFundingType());
        movieTransaction.setService(movieDto.getService());
        movieTransaction.setSystemProductType(SystemProduct.EVENT);

        log.info("Transaction created inside eventTransaction...{}",movieTransaction);

        return transactionRepository.save(movieTransaction);


    }

}
