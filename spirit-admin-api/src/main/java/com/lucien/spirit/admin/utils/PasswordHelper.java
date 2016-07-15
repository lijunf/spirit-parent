package com.lucien.spirit.admin.utils;

import java.util.UUID;

import org.apache.shiro.crypto.hash.Sha512Hash;

import com.lucien.spirit.admin.model.User;

/**
 * 用户密码生成帮助类.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:34:44
 * <p>Version: 1.0
 */
public class PasswordHelper {

    /**
     * 采用SHA算法生成用户密码.
     * @param user  用户对象
     * @return  用户对象
     */
    public static User generatePassword(User user) {
        byte[] passwordSalt = UUID.randomUUID().toString().getBytes();
        user.setPasswordSalt(passwordSalt);
        String passwordHash = new Sha512Hash(user.getPassword(), user.getName() + new String(passwordSalt), 99)
                .toString();
        user.setPasswordHash(passwordHash);
        return user;
    }
}
