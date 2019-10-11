package io.nuls.usdi.eth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import static io.nuls.usdi.eth.Bootstrap.web3j;

/**
 * 实现类
 *
 * @author captain
 * @version 1.0
 * @date 2019/10/11 16:09
 */
public class EthServiceImpl implements EthService {

    private static final Logger log = LoggerFactory.getLogger(EthServiceImpl.class);

    private static DefaultBlockParameter parameter = DefaultBlockParameterName.LATEST;

    @Override
    public BigInteger getBalance(String account) throws IOException {
        return web3j.ethGetBalance(account, parameter).send().getBalance();
    }

    @Override
    public void transferToAccount(Credentials credentials, String toAccount, BigDecimal amount) throws Exception {
        // FIXME: Request some Ether for the Rinkeby test network at https://www.rinkeby.io/#faucet
        log.info("Sending {} Ether ", amount);
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentials,
                toAccount,  // you can put any address here
                amount, Convert.Unit.ETHER)  // 1 wei = 10^-18 Ether
                .send();
        log.info("Transaction complete, view it at https://rinkeby.etherscan.io/tx/" + transferReceipt.getTransactionHash());
    }
}
