package com.main.service;

import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.retailer.rewards.entity.Transaction;
import com.retailer.rewards.repository.TransactionRepository;
import com.retailer.rewards.service.RewardsService;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RewardsServiceTest {

    @InjectMocks
    RewardsService rewardsService;

    @Mock
    TransactionRepository transactionRepository;

    @Test
    public void testGetRewardsByCustomerId() {
        final List<Transaction> transactions = new ArrayList<>();
        final Transaction transaction = new Transaction();
        transaction.setCustomerId(1L);
        transaction.setTransactionAmount(0.0);
        transaction.setTransactionDate(new Timestamp(1L));
        transaction.setTransactionId(2L);
        transactions.add(transaction);
        when(transactionRepository.findAllByCustomerIdAndTransactionDateBetween(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(transactions);
        rewardsService.getRewardsByCustomerId(1L);
    }
}
