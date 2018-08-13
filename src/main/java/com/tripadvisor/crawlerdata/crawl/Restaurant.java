package com.tripadvisor.crawlerdata.crawl;
/*
 * Restaurant.java
 *
 * Project: crawler-data
 *
 * Copyright 2017 by Canton de Vaud
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Canton de Vaud. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license
 * agreement you entered into with Canton de Vaud.
 */


/*
 *@author xhbq
 */

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Restaurant {
    public String name;
    public String email;
    public String website;
    public String facebookPage;
    public String address;

}
