package com.example.emailverification.services;


import com.example.emailverification.daoRepositories.UserDao;
import com.example.emailverification.modelEntity.UserModel;
import com.example.emailverification.payload.LoginPayload;
import com.example.emailverification.payload.RegisterPayload;
import com.example.emailverification.utils.EmailUtil;
import com.example.emailverification.utils.OtpUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class UserService {


    @Autowired
    private OtpUtil otpUtil;


    @Autowired
    private EmailUtil emailUtil;


    @Autowired
    private UserDao userDao;

    public String register(RegisterPayload registerPayload) {

       String otp=otpUtil.generateOtp();

        try {
            emailUtil.sendOtpEmail(registerPayload.getEmail(),otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp, please try again");
        }

        UserModel userModel=new UserModel();

        userModel.setName(registerPayload.getName());

        userModel.setEmail(registerPayload.getEmail());

        userModel.setPassword(registerPayload.getPassword());

        userModel.setOtp(otp);

        userModel.setOtpGeneratedTime(LocalDateTime.now());

        userDao.save(userModel);

        return "User registered successfully";

    }

    public String verifyAccount(String email, String otp) {

        UserModel userModel=this.userDao.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found with this email :"+email));


        // account should be verified within 1 minute of otp generation

        // during verification if otp is wrong or otp is used after 1 min then verification will fail

        if(userModel.getOtp().equals(otp) && Duration.between(userModel.getOtpGeneratedTime(),LocalDateTime.now()).getSeconds()<(1*60)) {

            userModel.setActive(true);

            userDao.save(userModel);

            return "OTP verified, you can login";
        }

        return "Please regenerate OTP and try again";


    }

    public String regenerateOtp(String email) {

        UserModel userModel=this.userDao.findByEmail(email).orElseThrow(()->new RuntimeException("User not found with this email :"+email));

        String otp= otpUtil.generateOtp();

        try {
            emailUtil.sendOtpEmail(email,otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp, please try again");
        }

        userModel.setOtp(otp);
        userModel.setOtpGeneratedTime(LocalDateTime.now());
        userDao.save(userModel);

        return "Email sent .....please verify account witjin 1 minute";

    }

    public String userLogin(LoginPayload loginPayload) {

        UserModel userModel=this.userDao.findByEmail(loginPayload.getEmail()).orElseThrow(()-> new RuntimeException("User not found with this email :"+loginPayload.getEmail()));

        if(!loginPayload.getPassword().equals(userModel.getPassword())){
            return "Password is incorrect!";
        }
        else if(!userModel.isActive()){
            return "Your account is not verified";
        }

        return "Login successfully !";

    }
}
