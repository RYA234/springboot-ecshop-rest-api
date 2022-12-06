package com.example.restapi.domain.order;

import com.example.restapi.implement.order.OrderDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 *
 * @brief:  Order GET Responese
 *
 * @description  顧客がいままで注文した履歴をすべて取得するときに使われるレスポンスボディです。
 *               Page<Order>から変換される形でService層で使われます。
 *               Page<Order>から変換する理由としては、restapi側からするとPage<Order>がわかりにくいためです。
 *
 *
 * @Auther RYA234
 *
 * @See: {＠link  OrderRestController}
 */
@Data
@NoArgsConstructor
public class OrderResponse {
    private List<OrderDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public OrderResponse(int pageNo, int pageSize, long totalElements, int totalPages, boolean last) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public OrderResponse(List<OrderDto> content, int pageNo, int pageSize, long totalElements, int totalPages, boolean last) {
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }
}

