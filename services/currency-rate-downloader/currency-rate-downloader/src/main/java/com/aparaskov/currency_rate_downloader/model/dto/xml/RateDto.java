package com.aparaskov.currency_rate_downloader.model.dto.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class RateDto {
    @XmlElement(name = "GOLD")
    private String gold;
    @XmlElement(name = "NAME_")
    private String name;
    @XmlElement(name = "CODE")
    private String code;
    @XmlElement(name = "RATIO")
    private String ratio;
    @XmlElement(name = "REVERSERATE")
    private String reverseRate;
    @XmlElement(name = "RATE")
    private String rate;
    @XmlElement(name = "CURR_DATE")
    private String currentDate;
    @XmlElement(name = "F_STAR")
    private String fStar;
}
