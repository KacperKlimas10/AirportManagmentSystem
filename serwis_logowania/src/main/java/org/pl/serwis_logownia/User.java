package org.pl.serwis_logownia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    String username;
    String password;
}