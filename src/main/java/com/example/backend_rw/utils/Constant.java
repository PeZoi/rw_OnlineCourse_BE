package com.example.backend_rw.utils;

public class Constant {
    public static final String SUBJECT_REGISTER = "Please verify your registration to continue";
    public static final String SUBJECT_RESET = "Here's the link to reset your password";
    public static final String EMAIL_TEMPLATE_REGISTER ="<div style=\"font-size: 16px; letter-spacing: normal;\">Dear [[name]]," +
            "</div><div style=\"font-size: 16px; letter-spacing: normal;\"><i><br></i></div>" +
            "<div style=\"font-size: 16px; letter-spacing: normal;\">" +
            "<i>Click the link below to verify your registration:</i></div>" +
            "<div style=\"font-size: 16px; letter-spacing: normal;\"><i><br></i></div>" +
            "<a href=\"[[URL]]\" target=\"_self\" style=\"color: rgb(0, 123, 255); background-color: " +
            "transparent; font-size: 16px; letter-spacing: normal;\">VERIFY</a><div style=\"font-size: 16px;" +
            " letter-spacing: normal;\"><span style=\"font-size: 18px;\"><span style=\"font-size: 24px;\">" +
            "<span style=\"font-weight: bolder;\"><font color=\"#ff0000\"></font></span></span>" +
            "</span></div><div style=\"font-size: 16px; letter-spacing: normal;\"><br></div>" +
            "<div style=\"font-size: 16px; letter-spacing: normal;\">Thanks,</div>" +
            "<div style=\"font-size: 16px; letter-spacing: normal;\">The Tech Courses Team</div>";

    public static final String CONTENT_RESET = "<p>Hello [[name]],</p>" +
            "<p>You have requested to reset your password.</p>" +
            "<p>Click the link below to change your password:</p>" +
            "<p><a href=\"[[URL]]\">Change my password</a></p>" +
            "<br>" +
            "<p>Ignore this email if you do remember your password, " +
            "or you have not made the request.</p>";


}
