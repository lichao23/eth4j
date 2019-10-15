package io.nuls.usdi.eth;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthTransaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

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

    /**
     * 获取当前最新区块高度
     * @return
     * @throws IOException
     */
    BigInteger latestHeight() throws IOException;

    /**
     * 根据区块hash获取区块
     * @param blockHash         区块hash
     * @param returnFullTransactionObjects      是否返回完整交易
     * @return
     */
    EthBlock.Block getBlockByHash(String blockHash, boolean returnFullTransactionObjects) throws IOException;

    /**
     * 根据区块高度获取区块
     * @param blockHeight         区块高度
     * @param returnFullTransactionObjects      是否返回完整交易
     * @return
     */
    EthBlock.Block getBlockByHeight(BigInteger blockHeight, boolean returnFullTransactionObjects) throws IOException;

    boolean sendTransaction();

    String signMessage();

    EthTransaction getTransactionByHash();

    /**
     * 获取当前所有账户
     * @return
     * @throws IOException
     */
    List<String> getAccounts() throws IOException;

}
