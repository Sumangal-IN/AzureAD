package com.SpringBootAD.SpringBootADConnection.model;

import lombok.Data;

@Data
public class AuthRequest {
 private String jsessionID;
 private String returnURL;
}
