package com.example.exchange_rates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Operation {

    @JsonProperty("user_id")
    @ApiModelProperty(value = "id пользователя", example = "14321")
    private Long user_id;

    @JsonProperty("type")
    @ApiModelProperty(value = "тип операции", example = "spend")
    private String type;

    @JsonProperty("sum")
    @ApiModelProperty(value = "Сумма", example = "100.00")
    String sum;

    @JsonProperty("cDate")
    @ApiModelProperty(value = "Дата, время", example = "2021-03-13 13:41:41.176071")
    String cDate;

    public Operation(Long user_id, String type, String sum, String cDate) {
        this.user_id = user_id;
        this.type = type;
        this.sum = sum;
        this.cDate = cDate;
    }

    public Operation(Long user_id, String type, String sum) {
        this.user_id = user_id;
        this.type = type;
        this.sum = sum;
    }

}
