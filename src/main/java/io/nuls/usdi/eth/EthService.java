package io.nuls.usdi.eth;

import org.web3j.crypto.Credentials;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 抽象接口类
 *
 * @author captain
 * @version 1.0
 * @date 2019/10/11 15:31
 */
public interface EthService {

    /**
     * 获取账户余额
     * @param account
     * @return
     */
    BigInteger getBalance(String account) throws IOException;

    /**
     * 给某个账户转账ETH
     * @param toAccount
     * @param amount
     * @return
     */
    void transferToAccount(Credentials credentials, String toAccount, BigDecimal amount) throws Exception;

}
