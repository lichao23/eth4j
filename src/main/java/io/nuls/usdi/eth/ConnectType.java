package io.nuls.usdi.eth;

/**
 * 连接类型
 */
public enum ConnectType {

    /**
     * 主网、在线API
     */
    MAINNET_ONLINE,
    /**
     * 测试网、在线API
     */
    TESTNET_ONLINE,
    /**
     * 主网、本地节点
     */
    MAINNET_LOCAL,
    /**
     * 测试网、本地节点
     */
    TESTNET_LOCAL,

}
