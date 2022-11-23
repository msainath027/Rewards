package com.retailer.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.retailer.rewards.controller.RewardsController;
import com.retailer.rewards.entity.Customer;
import com.retailer.rewards.model.Rewards;
import com.retailer.rewards.repository.CustomerRepository;
import com.retailer.rewards.service.RewardsService;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RewardsControllerTest {

    @InjectMocks
    RewardsController rewardsController;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    RewardsService rewardsService;;

    @Test
    public void testGetRewardsByCustomerId()
    {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        final Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setCustomerName("Test1");
        when(customerRepository.findByCustomerId(1L)).thenReturn(customer);
        final Rewards rewards = new Rewards();
        when(rewardsService.getRewardsByCustomerId(1L)).thenReturn(rewards);
        final ResponseEntity<Rewards> responseEntity = rewardsController.getRewardsByCustomerId(1L);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test(expected = RuntimeException.class)
    public void testGetRewardsByNullCustomerId()
    {
        when(customerRepository.findByCustomerId(1L)).thenReturn(null);
        final ResponseEntity<Rewards> responseEntity = rewardsController.getRewardsByCustomerId(1L);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(500);
    }
}
