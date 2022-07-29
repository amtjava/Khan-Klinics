package cn.nigel.boot.framework.common.exception.enums;

import cn.nigel.boot.framework.common.exception.ErrorCode;

/**
 * 全局错误码枚举
 * 0-999 系统异常编码保留
 * <p>
 * 一般情况下，使用 HTTP 响应状态码 https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Status
 * 虽然说，HTTP 响应状态码作为业务使用表达能力偏弱，但是使用在系统层面还是非常不错的
 * 比较特殊的是，因为之前一直使用 0 作为成功，就不使用 200 啦。
 */
public interface GlobalErrorCodeConstants {

    ErrorCode SUCCESS = new ErrorCode(0, "Success");

    // ========== 客户端错误段 ==========

    ErrorCode BAD_REQUEST = new ErrorCode(400, "The request parameters are incorrect");
    ErrorCode UNAUTHORIZED = new ErrorCode(401, "Account not logged in");
    ErrorCode FORBIDDEN = new ErrorCode(403, "You do not have the operation permission");
    ErrorCode NOT_FOUND = new ErrorCode(404, "Request not found");
    ErrorCode METHOD_NOT_ALLOWED = new ErrorCode(405, "The request method is incorrect");
    ErrorCode LOCKED = new ErrorCode(423, "The request failed. Please try again later"); // 并发请求，不允许
    ErrorCode TOO_MANY_REQUESTS = new ErrorCode(429, "The request is too frequent. Please try again later");

    // ========== 服务端错误段 ==========

    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500, "System error");

    // ========== 自定义错误段 ==========
    ErrorCode REPEATED_REQUESTS = new ErrorCode(900, "重复请求，请稍后重试"); // 重复请求
    ErrorCode DEMO_DENY = new ErrorCode(901, "演示模式，禁止写操作");

    ErrorCode UNKNOWN = new ErrorCode(999, "Unknown error");

    static boolean isMatch(Integer code) {
        return code != null
                && code >= SUCCESS.getCode() && code <= UNKNOWN.getCode();
    }

}
