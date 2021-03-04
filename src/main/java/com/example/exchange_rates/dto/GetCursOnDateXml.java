package com.example.exchange_rates.dto;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlRootElement(name = "GetCursOnDateXML", namespace = "http://web.cbr.ru/")

public class GetCursOnDateXml {

    @XmlElement(name = "On_date", required = true, namespace = "http://web.cbr.ru/") //Указание на то в каком теге XML должно быть данное поле
    @XmlSchemaType(name = "dateTime") //Указание типа данных в XML
    @Setter //Геттеры и сеттеры
    protected XMLGregorianCalendar onDate;
}
