package com.aparaskov.currency_rate_downloader.model.dto.xml;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@XmlRootElement(name = "ROWSET")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyRateDto {
    @XmlElement(name = "ROW")
    private List<RateDto> rates;
}
