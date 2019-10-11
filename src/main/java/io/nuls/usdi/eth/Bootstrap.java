package io.nuls.usdi.eth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

/**
 * 启动类
 *
 * @author captain
 * @version 1.0
 * @date 2019/10/10 17:28
 */
public class Bootstrap {

    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);

    private static String clientVersion = null;
    public static Web3j web3j = null;
    private static ConnectType type = ConnectType.TESTNET_ONLINE;
    private static EthService ethService = new EthServiceImpl();


    public static void main(String[] args) {
        try {
            init();
            loadWalletFile();
            test();
        } catch (IOException | CipherException e) {
            log.error("error!", e);
        }
    }

    private static void test() {

    }

    private static void loadWalletFile() throws IOException, CipherException {
        // We then need to load our Ethereum wallet file
        // FIXME: Generate a new wallet file using the web3j command line tools https://docs.web3j.io/command_line.html
        Credentials credentials = WalletUtils.loadCredentials("nuls123456", "E:\\eth_wallet\\UTC--2019-10-10T06-02-51.851307500Z--b4fc4b845c5377acbafcf28b934083c3856455b3.json");
        log.info("Credentials loaded");
        log.info("Address:" + credentials.getAddress());
        log.info("Balance:" + ethService.getBalance(credentials.getAddress()));
    }

    private static void init() throws IOException {
        // We start by creating a new web3j instance to connect to remote nodes on the network.
        // Note: if using web3j Android, use Web3jFactory.build(...
        switch (type) {
            case MAINNET_LOCAL:
                web3j = Web3j.build(new HttpService("http://192.168.1.150:8545"));
                break;
            case TESTNET_LOCAL:
                web3j = Web3j.build(new HttpService("http://192.168.1.151:8545"));
                break;
            case MAINNET_ONLINE:
                web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/eba53d9d59bd494ea7c8952cc36191cc"));  // FIXME: Enter your Infura token here;
                break;
            case TESTNET_ONLINE:
            default:
                web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/eba53d9d59bd494ea7c8952cc36191cc"));  // FIXME: Enter your Infura token here;
                break;
        }
        clientVersion = web3j.web3ClientVersion().send().getWeb3ClientVersion();
        log.info("Connected to Ethereum client version: " + clientVersion);
        log.info("Latest block height: " + web3j.ethBlockNumber().send().getBlockNumber());
    }

}
